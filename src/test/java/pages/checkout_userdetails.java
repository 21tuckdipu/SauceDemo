package pages;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class checkout_userdetails {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		//GOAL TO CHECK THE ENTRY WITH DIFFERENT VALUES ( DDF)
		//FILE OPERATION : READ AND WRITE
		
		//USERDATA CORRECT
		String A = "";
		String B = "";
		String C = "";
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.saucedemo.com/");
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(35));
		
		WebElement us = driver.findElement(By.id("user-name"));
		us.sendKeys("standard_user");
		WebElement up = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("password")));
		up.sendKeys("secret_sauce");
		WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(By.id("login-button")));
		btn.click();
		
		wait.until(ExpectedConditions.urlContains("inventory.html"));
		WebElement item01 = wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-sauce-labs-bolt-t-shirt")));
		item01.click();
		WebElement item02 = wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-sauce-labs-onesie")));
		item02.click();
		
		WebElement cart = wait.until(ExpectedConditions.elementToBeClickable(By.className("shopping_cart_link")));
		cart.click();
		
		WebElement checkout = wait.until(ExpectedConditions.elementToBeClickable(By.id("checkout")));
		checkout.click();
		
		FileInputStream reader = new FileInputStream("D:\\quash\\RelatedDoc\\Framework_repos\\sauceDemo.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(reader);
		System.out.println(workbook);
		XSSFSheet sheet = workbook.getSheet("userdetailsCheckout");
		
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			
			XSSFRow row = sheet.getRow(i);
			XSSFCell username = row.getCell(0);
			XSSFCell password = row.getCell(1);
			XSSFCell zip = row.getCell(2);
			XSSFCell  result = row.createCell(3);
			XSSFCell  compare = row.getCell(4);
		
			
			WebElement first_name = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("first-name")));
			first_name.clear();
			first_name.sendKeys(username.toString());	
			WebElement last_name = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("last-name")));
			last_name.clear();
			last_name.sendKeys(password.toString());	
			WebElement zip_code = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("postal-code")));
			zip_code.clear();
			zip_code.sendKeys(zip.toString());
			try {
				WebElement cont = wait.until(ExpectedConditions.elementToBeClickable(By.id("continue")));
				cont.click();
				A = username.toString();
				B = password.toString();
				C = zip.toString();
				
				driver.navigate().back();
				System.out.println("USERNAME : "+username+"PASSWORD : "+password+" ZIPCODE : "+zip);
				
				//HERE WE WILL VALIDATE THE OPTIONS
				
				String compare_value = compare.toString();
				if (compare_value.equalsIgnoreCase("PASS")) {
					result.setCellValue("PASS");
				}
				else {
					result.setCellValue("FAIL");
				}
				
				
			}
			catch (Exception e) {
				System.out.println("FAILS");
				System.out.println("USERNAME : "+username+"PASSWORD : "+password+" ZIPCODE : "+zip);
			}
			
			
			
			FileOutputStream writer = new FileOutputStream("D:\\quash\\RelatedDoc\\Framework_repos\\sauceDemo.xlsx");
			workbook.write(writer);
			
		}
		
		System.out.println("USERNAME : "+A+"PASSWORD : "+B+" ZIPCODE : "+C);
		Thread.sleep(2000);
		
		driver.findElement(By.id("first-name")).sendKeys(A);
		driver.findElement(By.id("last-name")).sendKeys(B);
		driver.findElement(By.id("postal-code")).sendKeys(C);
		
		WebElement contI = wait.until(ExpectedConditions.elementToBeClickable(By.id("continue")));
		contI.click();
		WebElement finish = wait.until(ExpectedConditions.elementToBeClickable(By.id("finish")));
		finish.click();	
		WebElement back = wait.until(ExpectedConditions.elementToBeClickable(By.id("back-to-products")));
		back.click();	
		

	}

}
