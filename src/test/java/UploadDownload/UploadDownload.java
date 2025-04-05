package UploadDownload;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;

import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UploadDownload {
	ChromeDriver driver = new ChromeDriver();
	String fruitName = "Apple";
	String updatedValue = "599";

	static String filename = "C:\\Users\\RB00993323\\Downloads\\download(7).xlsx";

	@Test
	public void download() throws IOException {

		driver.get("https://rahulshettyacademy.com/upload-download-test/index.html");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		driver.manage().window().maximize();

		driver.findElement(By.id("downloadButton")).click();

		int col = getColumnNumber("price");
		int row = getRowNumber("Apple");
		Assert.assertTrue(updateCell(filename, row, col, updatedValue));

		WebElement upload = driver.findElement(By.id("fileinput"));
		upload.sendKeys("C:\\Users\\RB00993323\\Downloads\\download (7).xlsx");
		By toastLocator = By.xpath("//div[@class ='Toastify__toast-body']/div[2]");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		wait.until(ExpectedConditions.visibilityOfElementLocated(toastLocator));
		String toastText = driver.findElement(toastLocator).getText();
		Assert.assertEquals("Updated Excel Data Successfully.", toastText);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(toastLocator));
		String priceColumn = driver.findElement(By.xpath("//div[text()='Price']")).getDomAttribute("data-column-id");
		String actualPrice = driver.findElement(By.xpath("//div[text()='" + fruitName
				+ "']/parent::div/parent::div/div[@id='cell-" + priceColumn + "-undefined']")).getText();

		Assert.assertEquals(updatedValue, actualPrice);

	}

	private boolean updateCell(String filename, int row, int col, String updatedvalue) throws IOException {
		// TODO Auto-generated method stub
		FileInputStream fis = new FileInputStream(filename);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		Row rowfield = sheet.getRow(row - 1);
		Cell cellfield = rowfield.getCell(col - 1);
		cellfield.setCellValue(updatedvalue);
		FileOutputStream fos = new FileOutputStream(filename);
		workbook.write(fos);
		workbook.close();
		fis.close();
		return true;
	}

	private int getRowNumber(String text) throws IOException {

		// TODO Auto-generated method stub

		FileInputStream fis = new FileInputStream(filename);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Sheet1");

		Iterator<Row> rows = sheet.iterator();
		int k = 1;
		int rowindex = -1;
		while (rows.hasNext()) {
			Row row = rows.next();
			Iterator<Cell> cells = row.cellIterator();

			while (cells.hasNext()) {
				Cell cell = cells.next();

				if (cell.getCellType() == CellType.STRING && cell.getStringCellValue().equalsIgnoreCase(text)) {

					rowindex = k;

				}

			}
			k++;

		}
		workbook.close();
		fis.close();
		return rowindex;
	}

	private int getColumnNumber(String price) throws IOException {

		FileInputStream fis = new FileInputStream(filename);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		int col = 0;
		Iterator<Row> rows = sheet.iterator();
		Row firstRow = rows.next();
		Iterator<Cell> cells = firstRow.cellIterator();
		int k = 1;

		while (cells.hasNext()) {

			Cell value = cells.next();
			if (value.getStringCellValue().equalsIgnoreCase(price)) {

				col = k;

			}
			k++;
		}

		System.out.println(col);
		workbook.close();
		fis.close();
		return col;
	}

}
