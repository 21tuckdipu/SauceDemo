package pages;


import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
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

public class Checkout_info {

	public static void main(String[] args) throws IOException {
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.saucedemo.com/");
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(35));
		
		//database Query
		String sql_user = "";
		String sql_pass = "";
		
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/project","root","password");
			Statement st = conn.createStatement();
			String query = "select * from saucedemo ;";
			ResultSet res = st.executeQuery(query);
			
			while (res.next()) {
				sql_user = res.getString(1);
				sql_pass = res.getString(2);
			}
			st.close();
			conn.close();
		}
		catch (Exception e) {
			System.out.println("Connection not Established");
			System.out.println(e);
		}
		
		FileInputStream reader = new FileInputStream("D:\\quash\\RelatedDoc\\Framework_repos\\sauceDemo.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(reader);
		System.out.println(workbook);
		XSSFSheet sheet = workbook.getSheet("infoKDF");
		XSSFSheet sheet02 = workbook.getSheet("infoCheckout");
		System.out.println(sheet02);
		
		
		for (int i = 1; i <=sheet.getLastRowNum() ; i++) {
			XSSFRow row = sheet.getRow(i);
			XSSFCell steps = row.getCell(0);
			System.out.println(steps.toString());
			
			switch (steps.toString()) {
			case "username":
				WebElement us = driver.findElement(By.id("user-name"));
				us.clear();
				us.sendKeys(sql_user);
			break;
			case "password":
				WebElement up = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("password")));
				up.clear();
				up.sendKeys(sql_pass);
				
				break;
			case "login":
				WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(By.id("login-button")));
				btn.click();
				
				break;
			case "addToCart":
				wait.until(ExpectedConditions.urlContains("inventory.html"));
				WebElement item01 = wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-sauce-labs-bolt-t-shirt")));
				item01.click();
				WebElement item02 = wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-sauce-labs-onesie")));
				item02.click();
				break;
			case "Cart":
				WebElement cart = wait.until(ExpectedConditions.elementToBeClickable(By.className("shopping_cart_link")));
				cart.click();
				break;
				
			
			
				
			case "checkOut":
				WebElement checkout = wait.until(ExpectedConditions.elementToBeClickable(By.id("checkout")));
				checkout.click();
				
				
				break;
			case "Firstname":

				WebElement first_name = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("first-name")));
				first_name.sendKeys("Dipu ");	
				break;
			case "lastname":
				WebElement last_name = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("last-name")));
				last_name.sendKeys("Sonar");		
				break;
			case "zipcode":
				WebElement zip_code = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("postal-code")));
				zip_code.sendKeys("324566");			
				break;
			case "continue":
				WebElement cont = wait.until(ExpectedConditions.elementToBeClickable(By.id("continue")));
				cont.click();				
				break;
			case "finish":
				WebElement finish = wait.until(ExpectedConditions.elementToBeClickable(By.id("finish")));
				finish.click();						
				break;
			case "backHome":
				WebElement back = wait.until(ExpectedConditions.elementToBeClickable(By.id("back-to-products")));
				back.click();						
			    break;
				
			
				

			default:
				break;
			}
			
			
		}
		
		
		

	}

}
