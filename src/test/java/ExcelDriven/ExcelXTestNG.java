package ExcelDriven;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ExcelXTestNG {
	DataFormatter formatter = new DataFormatter();
	@Test(dataProvider ="exceldata")
	public void logIn (String website,String username , String password) {
		
		System.out.println("The Website is "+ website);
		System.out.println("The Username is "+ username);
		System.out.println("The Password is "+ password);
		System.out.println("========================================");
		
		
	}
	
	
	
	@DataProvider(name = "exceldata")
	public Object[][] getdata() throws IOException{
		
		DataFormatter formatter = new DataFormatter();
		FileInputStream excel = new FileInputStream("C:\\Users\\RB00993323\\Desktop\\Rupak Java\\ApachePOI\\Password-List-Template-TemplateLab.com_.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(excel);
		
		XSSFSheet sheet = wb.getSheet("Password List Template");
		int rowCount = sheet.getPhysicalNumberOfRows();
		XSSFRow row = sheet.getRow(0);
		int colCount = row.getLastCellNum();
		 Object data[][] = new Object[rowCount][colCount];
		for(int i =0 ; i<rowCount-1;i++) {
			
			row = sheet.getRow(i+1);
			
			for(int j = 0;j<colCount;j++) {
				
				XSSFCell cell = row.getCell(j);
				data[i][j] = formatter.formatCellValue(cell);
				
			}
			
		}
		
		return data;
		
		
	}
	
	
}
