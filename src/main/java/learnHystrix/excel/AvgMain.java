package learnHystrix.excel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.collect.Maps;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class AvgMain {

	public static void main(String[] args) throws Exception {

		// poi读取excel
		// 创建要读入的文件的输入流
		InputStream inp = new FileInputStream("/Users/wuzonghan/Downloads/数据透视.xlsm");

		// 根据上述创建的输入流 创建工作簿对象
		Workbook wb = WorkbookFactory.create(inp);

		//

		createWorkBook(wb);

		//		// 得到第一页 sheet
		//		// 页Sheet是从0开始索引的
		//		Sheet sheet = wb.getSheetAt(0);
		//		// 利用foreach循环 遍历sheet中的所有行
		//		for (Row row : sheet) {
		//			if (row.getRowNum() < 5)
		//				continue;
		//			if (row.getCell(0) != null && row.getCell(0).toString().contains("工"))
		//				continue;
		//			// 遍历row中的所有方格
		//			sheet.createRow(row.getRowNum());
		//			for (Cell cell : row) {
		//				Pattern p = Pattern.compile("(\\d{2}):(\\d{2})");
		//				Matcher m = p.matcher(cell.toString());
		//				if (m.find()) {
		//					String[] times = cell.toString().split("\n");
		//					if (times.length > 1) {
		//
		//					}
		//					System.out.print(cell.toString() + "  ");
		//				}
		//			}
		//			// 每一个行输出之后换行
		//			System.out.println();
		//		}
		// 关闭输入流
		inp.close();

	}

	//使用POI创建excel工作簿
	public static void createWorkBook(Workbook wb) throws IOException {
		//		for (int s = 0; s < 1; s++) {
		//			Sheet sheet = wb.getSheetAt(s);
		//			Sheet sheetNew = wbNew.createSheet(sheet.getSheetName());
		//			for (int r = 0; r < sheet.getPhysicalNumberOfRows(); r++) {
		//				Row row = sheet.getRow(r);
		//				Row rowNew = sheetNew.createRow(r);
		//				if (row == null)
		//					continue;
		//				for (int c = 0; c < row.getPhysicalNumberOfCells(); c++) {
		//					Cell cell = row.getCell(c);
		//					Cell cellNew = rowNew.createCell(c);
		//					if (cell == null)
		//						continue;
		//					cellNew.setCellValue(cell.toString());
		//					//
		//					if (!StringUtils.isEmpty(cell.toString())) {
		//						Pattern p = Pattern.compile("(\\d{2}):(\\d{2})");
		//						Matcher m = p.matcher(cell.toString());
		//						if (m.find()) {
		//							String[] times = cell.toString().split("\n");
		//							System.out.print(cell.toString() + "  ");
		//						}
		//					}
		//				}
		//			}
		//		}
		for (int s = 0; s < 1; s++) {
			Sheet sheet = wb.getSheetAt(s);
			Iterator<Row> rowIt = sheet.iterator();
			while (rowIt.hasNext()) {
				Row row = rowIt.next();
				if (matchTimeRow(row)) {
					Row fNewRow = sheet.createRow(row.getRowNum() + 1);
					Row sNewRow = sheet.createRow(row.getRowNum() + 2);
					Iterator<Cell> cellIterator = row.cellIterator();
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						Cell fCell = fNewRow.createCell(cell.getColumnIndex());
						fCell.setCellValue(amTime(row, cell.getColumnIndex()));
						Cell sCell = sNewRow.createCell(cell.getColumnIndex());
						sCell.setCellValue(pmTime(row, cell.getColumnIndex()));
					}
					rowIt.next();
					rowIt.next();
				}
			}
			//创建一个文件 命名为workbook.xls
			FileOutputStream fileOut = new FileOutputStream("/Users/wuzonghan/Downloads/workbook.xlsm");
			// 把上面创建的工作簿输出到文件中
			wb.write(fileOut);
			//关闭输出流
			fileOut.close();
		}
	}

	public static boolean matchTimeRow(Row r) {
		if (r.getRowNum() < 5)
			return false;
		//		if (matchTime(r.getCell(0)) || matchTime(r.getCell(1)) || matchTime(r.getCell(2)) || matchTime(r.getCell(3)))
		//			return true;
		for (int i = 0; i < r.getPhysicalNumberOfCells(); i++) {
			if (matchTime(r.getCell(i)))
				return true;
		}
		return false;
	}

	public static boolean matchTime(Cell cell) {
		if (cell == null)
			return false;
		if (StringUtils.isEmpty(cell.toString()))
			return false;
		Pattern p = Pattern.compile("(\\d{2}):(\\d{2})");
		Matcher m = p.matcher(cell.toString());
		return m.find();
	}

	public static String[] splitTime(String cellValue) {
		return cellValue.split("\n");
	}

	public static String amTime(Row row, int columnIndex) {
		if (row.getCell(columnIndex) == null)
			return "";
		if (StringUtils.isEmpty(row.getCell(columnIndex).toString()))
			return "";
		String[] times = row.getCell(columnIndex).toString().split("\n");
		if (times.length == 0)
			return "";
		else
			return times[0];
	}

	public static String pmTime(Row row, int columnIndex) {
		if (row.getCell(columnIndex) == null)
			return "";
		if (StringUtils.isEmpty(row.getCell(columnIndex).toString()))
			return "";
		String[] times = row.getCell(columnIndex).toString().split("\n");
		if (times.length < 2)
			return "";
		else
			return times[1];
	}

}
