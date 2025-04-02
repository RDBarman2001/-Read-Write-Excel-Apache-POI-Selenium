package ExcelDriven;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class dataDriven {

	public static void main(String[] args) throws IOException {

		int col = 0;
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\RB00993323\\Desktop\\Rupak Java\\ApachePOI\\demodata.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		int sheetNumber = workbook.getNumberOfSheets();
		for (int i = 0; i < sheetNumber; i++) {
			if (workbook.getSheetName(i).equalsIgnoreCase("testdata")) {
				XSSFSheet sheet = workbook.getSheetAt(i);

				Iterator<Row> rows = sheet.iterator();
				Row firstRow = rows.next();
				Iterator<Cell> cells = firstRow.cellIterator();
				int k = 0;

				while (cells.hasNext()) {

					Cell value = cells.next();
					if (value.getStringCellValue().equalsIgnoreCase("TestCases")) {

						col = k;
						

					}
					k++;
				}

				System.out.println(col);
				
				
				while (rows.hasNext()) {

					Row r = rows.next();
				if(	r.getCell(col).getStringCellValue().equalsIgnoreCase("purchase")) {
					
					Iterator<Cell> cv = r.cellIterator();
					
					while (cv.hasNext()) {

					System.out.println(cv.next().getStringCellValue());
						
							

						}
					
					
				}

				}

			}

		}

	}

}
