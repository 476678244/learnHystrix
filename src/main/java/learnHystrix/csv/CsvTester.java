package learnHystrix.csv;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang.StringUtils;

public class CsvTester {

	public static final String FILEA = "/Users/wuzonghan/workspace/learn/csvfiles/roq_result_base.csv";
	public static final String FILEB = "/Users/wuzonghan/workspace/learn/csvfiles/fix/roq_result_base.csv";
	public static final String FILEC = "/Users/wuzonghan/workspace/learn/csvfiles/onerule/roq_result_base.csv";
	
	public static final String FILEAPPEND_SKU = "/Users/wuzonghan/workspace/learn/csvfiles/fix/append_sku.csv";
	
	private static final Double LARGE_NUMBER = 99999.0;

	private static final long ONE_DAY_TIME = 1000 * 60 * 60 * 24;
	private static final Double FOUR_WEEK_DAYS = 28.0;
	private static final Double DEFAULT_WEEKLY_DEMAND = 0.0;

	private static boolean emptyFound = false;

	private static int failCount = 0;

	public static void main(String[] args) throws IOException, ParseException {
		Reader in = new FileReader(FILEAPPEND_SKU);
		Iterable<CSVRecord> records = CSVFormat.RFC4180.parse(in);
		Set<String> appendSkus = new HashSet<String>();
		for (CSVRecord record : records) {
			appendSkus.add(record.get(2));
		}
		in.close();
		//
		in = new FileReader(FILEB);
		records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
		for (CSVRecord record : records) {
			Double inventoryS = Double.valueOf(record.get("inventory"));
			Double openOrderS = Double.valueOf(record.get("open_order"));
			String skuS = record.get("sku");
			Double roqS = Double.valueOf(record.get("roq"));
			Double w1S = StringUtils.isEmpty(record.get("weekly_demand_w-1")) ? null
					: Double.valueOf(record.get("weekly_demand_w-1"));
			Double w2S = StringUtils.isEmpty(record.get("weekly_demand_w-2")) ? null
					: Double.valueOf(record.get("weekly_demand_w-2"));
			Double w3S = StringUtils.isEmpty(record.get("weekly_demand_w-3")) ? null
					: Double.valueOf(record.get("weekly_demand_w-3"));
			Double w4S = StringUtils.isEmpty(record.get("weekly_demand_w-4")) ? null
					: Double.valueOf(record.get("weekly_demand_w-4"));
			String fstInboundDyS = record.get("fstInboundDy");
			Double tipPeriodS = StringUtils.isEmpty(record.get("tip_period")) ? 0.0
					: Double.valueOf(record.get("tip_period"));
			Long caseQuantity = StringUtils.isEmpty(record.get("case_quantity")) ? 0L
					: Long.valueOf(record.get("case_quantity"));
			if (StringUtils.isEmpty(record.get("tip_period"))) {
				emptyFound = true;
			}
			if (getDaysSinceFstInboundDy(fstInboundDyS) > 28
					&& getDoc28MinusTipPeriod(w1S, w2S, w3S, w4S, inventoryS, openOrderS, tipPeriodS) > 0) {
				if (roqS == 0.0) {
					// System.out.print("Success! ");
					continue;
				} else {
					if (appendSkus.contains(skuS)) {
						continue;
					}
					System.out.print("Fail! ");
					getDoc28MinusTipPeriod(w1S, w2S, w3S, w4S, inventoryS, openOrderS, tipPeriodS);
					failCount++;
				}
			} else {
				continue;
			}
			System.out.println("sku: " + skuS + " roq: " + roqS);
			System.out
					.println("inventory: " + inventoryS + " open_order: " + openOrderS + " tip_period: " + tipPeriodS);
			System.out.println("fstInboundDy: " + fstInboundDyS + " caseQuantity:" + caseQuantity);
			System.out.println("w1S: " + w1S + " w2S: " + w2S + " w3S: " + w3S + " w4S: " + w4S);
		}
		in.close();
		System.out.println("emptyFound:" + emptyFound);
		System.out.println("failCount:" + failCount);
	}

	/**
	 * TIP-62 this is to determine whether not new sku empty fstInboundDay means
	 * definitely not new sku, so return a large number to > 0
	 */
	public static Long getDaysSinceFstInboundDy(String fstInboundDyS) throws ParseException {
		// TODO: null safe check
		return StringUtils.isEmpty(fstInboundDyS) ? LARGE_NUMBER.longValue()
				: (new Date().getTime() - new SimpleDateFormat("yyyyMMdd").parse(fstInboundDyS).getTime())
						/ ONE_DAY_TIME;
	}

	/**
	 * TIP-62 to determine whether the current inventory DOC (28-day-basis) can
	 * last until the next EID days 0 avgDemand means definitely meats
	 * requirements, so return large number to > 0
	 */
	public static Double getDoc28MinusTipPeriod(Double w1S, Double w2S, Double w3S, Double w4S, Double inventoryS,
			Double openOrderS, Double tipPeriodS) {
		Double weeklyDemandW1Double = (null == w1S) ? DEFAULT_WEEKLY_DEMAND : w1S;
		Double weeklyDemandW2Double = (null == w2S) ? DEFAULT_WEEKLY_DEMAND : w2S;
		Double weeklyDemandW3Double = (null == w3S) ? DEFAULT_WEEKLY_DEMAND : w3S;
		Double weeklyDemandW4 = (null == w4S) ? DEFAULT_WEEKLY_DEMAND : w4S;
		final Double avgDemand = (weeklyDemandW1Double + weeklyDemandW2Double + weeklyDemandW3Double + weeklyDemandW4)
				/ FOUR_WEEK_DAYS;
		return avgDemand == 0 ? LARGE_NUMBER : (inventoryS + openOrderS) / avgDemand - tipPeriodS;
	}

}
