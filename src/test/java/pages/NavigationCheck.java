package pages;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NavigationCheck {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.saucedemo.com/");
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		
		FileInputStream reader = new FileInputStream("D:\\quash\\RelatedDoc\\Framework_repos\\sauceDemo.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(reader);
		XSSFSheet sheet = workbook.getSheet("navigation");
		
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			XSSFRow row = sheet.getRow(i);
			XSSFCell steps = row.getCell(0);
			
			switch (steps.toString()) {
			case "username":
				WebElement us = driver.findElement(By.id("user-name"));
				us.sendKeys("standard_user");
				break;
            case "password":
            	WebElement up = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("password")));
    			up.sendKeys("secret_sauce");
				break;	
			case "login":
				WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(By.id("login-button")));
				btn.click();
				break;
            case "menu":
            	WebElement bur_menu = wait.until(ExpectedConditions.elementToBeClickable(By.id("react-burger-menu-btn")));
    			bur_menu.click();
				break;	
			case "close"://react-burger-cross-btn
				WebElement close = wait.until(ExpectedConditions.elementToBeClickable(By.id("react-burger-cross-btn")));
    			close.click();
				break;
            case "about":
            	driver.findElement(By.id("react-burger-menu-btn")).click();
            	WebElement about = wait.until(ExpectedConditions.elementToBeClickable(By.id("about_sidebar_link")));
    			about.click();
				break;	
			case "resetApp":
				driver.navigate().back();
				driver.findElement(By.id("react-burger-menu-btn")).click();
            	WebElement reset = wait.until(ExpectedConditions.elementToBeClickable(By.id("reset_sidebar_link")));
    			reset.click();
				break;
            case "addToCart":  //add-to-cart-sauce-labs-fleece-jacket
            	driver.findElement(By.id("react-burger-cross-btn")).click();
				WebElement addItem01 = wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-sauce-labs-fleece-jacket")));
				addItem01.click();
    			WebElement addItem02 = wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-sauce-labs-backpack")));
    			addItem02.click();
				break;	
			case "cart":
				System.out.println("cat");
				WebElement cart = driver.findElement(By.className("shopping_cart_link"));
				cart.click();
				Thread.sleep(2000);
				break;
            case "continueShopping": //continue-shopping
            	System.err.println("backs");
            	WebElement shopping = wait.until(ExpectedConditions.elementToBeClickable(By.id("continue-shopping")));
				shopping.click();
				break;	
			
            case "sorting":
            	//product_sort_container
            	WebElement dropdown = driver.findElement(By.className("product_sort_container"));
            	Select option = new Select(dropdown);
            	option.selectByValue("lohi");
            	Thread.sleep(2000);
            	Select option2 = new Select(driver.findElement(By.className("product_sort_container")));
            	option2.selectByValue("hilo");
            
            	Thread.sleep(2000);
            	Select option3 = new Select(driver.findElement(By.className("product_sort_container")));
            	option3.selectByValue("az");
            	Thread.sleep(2000);
            	Select option4 = new Select(driver.findElement(By.className("product_sort_container")));
            	option4.selectByValue("za");
            	break;
           
            	
			default:
				System.out.println("Not For Elenment"+steps.toString());
				break;
			}
		}

	}

}
