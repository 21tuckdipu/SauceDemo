package pages;

import java.io.FileInputStream;
import java.io.IOException;
//import java.lang.classfile.instruction.SwitchCase;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.Duration;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByClassName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckOut {

	public static void main(String[] args) throws IOException, InterruptedException {
		ChromeOptions opt = new ChromeOptions() ;
		opt.addArguments("--disable-notifications");
		WebDriver driver = new ChromeDriver(opt);
		driver.get("https://www.saucedemo.com/");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));
		
		String sql_username = "" ;
		String sql_password = "";
		
		FileInputStream reader = new FileInputStream("D:\\quash\\RelatedDoc\\Framework_repos\\sauceDemo.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(reader);
		System.out.println(workbook);
		XSSFSheet sheet = workbook.getSheet("checkout");
		
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","password");
			Statement st = (Statement) conn.createStatement();
			
			String query = "select * from saucedemo";
			ResultSet res = st.executeQuery(query);
			
		    while (res.next()) {
				sql_username = res.getString(1);
				sql_password = res.getString(2);
				System.out.println("USERNAME : "+sql_username+" PASSWORD : "+sql_password);
			}
	        st.close();
	        conn.close();
			}
		catch (Exception e) {
			System.out.println("Cannot able to Connect to Database");
		}
		
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			XSSFRow row = sheet.getRow(i);
			XSSFCell steps = row.getCell(0);
			System.out.println(steps.toString());
			
			switch (steps.toString()) {
			case "login":
				WebElement us = driver.findElement(By.id("user-name"));
				us.clear();
				us.sendKeys(sql_username);
				
				WebElement up = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("password")));
				up.clear();
				up.sendKeys(sql_password);
				
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
			case "clickCart":
				WebElement cart = wait.until(ExpectedConditions.elementToBeClickable(By.className("shopping_cart_link")));
				cart.click();
				break;
			case "checkOut":
				WebElement checkout = wait.until(ExpectedConditions.elementToBeClickable(By.id("checkout")));
				checkout.click();
				Thread.sleep(2000);
				System.out.println("checkoutI am Done");
			    break;
			case "firstName":
				
				WebElement first_name = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("first-name")));
				first_name.sendKeys("Dipu ");
			    break;
			case "LastName":
				WebElement last_name = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("last-name")));
				last_name.sendKeys("Sonar");
				break;
			case "ZipCode":
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
