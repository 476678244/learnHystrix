package learnHystrix.csv;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by wuzonghan on 17/2/13.
 */
public class NoSalesDaysItems {

	public static final String FILEA = "/Users/wuzonghan/Downloads/part-00000-1";


	public static void main(String[] args) throws IOException, ParseException {
		int noSalesItems = 0;
		int hasSalesItems = 0;

		Reader in = new FileReader(FILEA);
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);
		for (CSVRecord record : records) {
			if (noSalesRecord(record)) {
				noSalesItems ++;
			} else {
				hasSalesItems ++;
			}
		}

		System.out.println("noSalesItems: " + noSalesItems);
		System.out.println("hasSalesItems: " + hasSalesItems);
	}

	public static boolean noSaleString(String s) {
		return s.startsWith("0");
	}

	public static boolean noSalesRecord(CSVRecord record) {
		for (int i = 7 ; i < record.size() ; i ++) {
			if (!noSaleString(record.get(i))) {
				return false;
			}
		}
		return true;
	}
}
