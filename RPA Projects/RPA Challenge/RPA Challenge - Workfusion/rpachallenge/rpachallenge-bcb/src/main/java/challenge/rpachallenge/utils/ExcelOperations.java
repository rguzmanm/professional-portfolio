package challenge.rpachallenge.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelOperations {

	public static List<Map<String, String>> readExcel(String path) throws IOException {
		
		FileInputStream fis = new FileInputStream(new File(path));
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(0);
		List<Map<String, String>> excel_data = new ArrayList<Map<String, String>>();
		List<String> columnNames = new ArrayList<String>();
		Iterator<Row> itr = sheet.iterator();

		// Populate map with headers and empty list
		if (itr.hasNext()) {
			Row row = itr.next();
			Iterator<Cell> headerIterator = row.cellIterator();
			while (headerIterator.hasNext()) {
				Cell cell = headerIterator.next();
				columnNames.add(getCellValue(cell).toString().trim());
			}
		}
		while (itr.hasNext()) {
			int i = 0;
			// Get the list iterator every row to start from first list
			Row row = itr.next();
			Iterator<Cell> cellIterator = row.cellIterator(); // Iterating over each column
			Map<String, String> row_data = new HashMap<String, String>();
			try {
				String id = "";
				try {
					id = row.getCell(0).getStringCellValue();
				} catch (Exception e1) {
					id = String.valueOf(row.getCell(0).getNumericCellValue());
				}
				if (!id.isEmpty()) {
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						row_data.put(columnNames.get(i).trim(), getCellValue(cell).toString());
						i++;
					}
					excel_data.add(row_data);
				}
			} catch (Exception e) {
				System.out.println("Exception while reading excel: " + e.getMessage());
			}
		}
		wb.close();
		return excel_data;
	}

	@SuppressWarnings("deprecation")
	public static Object getCellValue(Cell cell) {
		
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING: // Field that represents string cell type
			return cell.getStringCellValue();
		case Cell.CELL_TYPE_NUMERIC: // Field that represents number cell type
			Long value = (long) cell.getNumericCellValue();
			return value;
		default:
			return "";
		}
	}

}
