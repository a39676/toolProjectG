package tool_package.io_tools;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.queue.CircularFifoQueue;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import utils.sql_utils.MySqlTools;

/* 
 * build a read function
 * */
public class IOToolWithExcl {

	public static void main(String[] args) {

		// String path = "d:" + File.separator + "auxiliary" + File.separator +
		// "tmp" + File.separator;
		// String file = "test01.xlsx";

		String path = "src/main/resources/tmp/test01.xlsx";

		Workbook workbook = null;
		try {
			workbook = new XSSFWorkbook(new FileInputStream(new File(path)));
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (workbook != null) {
			findElementsTest(workbook, 0, "ta");
		}
	}

	public static void readExcl() {
		String path = "d:" + File.separator + "auxiliary" + File.separator + "tmp" + File.separator;
		String file = "test01.xlsx";

		try {
			Workbook workbook = new XSSFWorkbook(new FileInputStream(new File(path + file)));
			Sheet firstSheet = workbook.getSheetAt(0);
			Cell tmpCell = null;
			Row row01 = firstSheet.getRow(0);
			for (int i = 0; i < 3; i++) {
				if (row01.getCell(i) != null) {
					tmpCell = row01.getCell(i);
				} else {
					tmpCell.setCellValue("");
				}
				System.out.println(tmpCell.toString());
			}

//			workbook.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void findElements(Workbook workbook, int targetSheet, String targetWord) {
		Sheet sheet = workbook.getSheetAt(targetSheet);
		Iterator<Row> rowIterator = sheet.iterator();

		String tableCommentMather = "(\\d.\\d.\\d.)(?:\\s*)(\\S{1,40})";
		String tableNameMather = "表名：(?:\\s{0,3})(?:\\w{1,40})";

		while (rowIterator.hasNext()) {
			Row nextRow = rowIterator.next();

			Iterator<Cell> cellIterator = nextRow.cellIterator();
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				if (cell.toString().contains(targetWord)) {
					System.out.println("found" + cell.getRowIndex() + "," + cell.getColumnIndex());
				}
			}
		}
	}

	public static void findElementsTest(Workbook workbook, int targetSheet, String targetWord) {

		Sheet sheet = workbook.getSheetAt(targetSheet);
		Iterator<Row> rowIterator = sheet.iterator();

		boolean breakFlag = false;
		int breakCount = 0;

		while (rowIterator.hasNext()) {
			Row nextRow = rowIterator.next();

			for (int i = 0; i < 10; i++) {
				Cell tmpCell = nextRow.getCell(i);
				if (tmpCell != null) {

				} else {

				}
			}
		}
	}

	

	

	

}
