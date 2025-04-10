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
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login_out {

	public static void main(String[] args) throws IOException {
		
		ChromeOptions opt = new ChromeOptions();
		opt.addArguments("--disable-notifications");
		opt.addArguments("--disable-password-manager-reauthentication");
		opt.addArguments("--disable-save-password-bubble");
		WebDriver driver = new ChromeDriver(opt);
		driver.get("https://www.saucedemo.com/");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		
		FileInputStream reader = new FileInputStream("D:\\quash\\RelatedDoc\\Framework_repos\\sauceDemo.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(reader);
		XSSFSheet sheet = workbook.getSheet("login");
		
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			XSSFRow row = sheet.getRow(i);
			XSSFCell username = row.getCell(0);
			XSSFCell password = row.getCell(1);
			XSSFCell result = row.createCell(2);
			
			System.out.println("Username: "+username.toString()+" Password : "+password.toString() );
			
			try {
			WebElement us =  wait.until(ExpectedConditions.presenceOfElementLocated(By.id("user-name")));
			us.clear();
			us.sendKeys(username.toString());
			
			WebElement up = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("password")));
			up.clear();
			up.sendKeys(password.toString());
			
			WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(By.id("login-button")));
			btn.click();
			try {
			
			WebElement bur_menu = wait.until(ExpectedConditions.elementToBeClickable(By.id("react-burger-menu-btn")));
			bur_menu.click();
		
			
			WebElement logout = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div/div[1]/div[1]/div[1]/div/div[2]/div[1]/nav/a[3]")));
			logout.click();
			result.setCellValue("PASS");
			}
			catch(Exception j){
				result.setCellValue("FAIL");
			}
			}
			catch (Exception e) {
				
				result.setCellValue("Fail");
				System.out.println("Error :"+e);
				
			}
			FileOutputStream writer = new FileOutputStream("D:\\quash\\RelatedDoc\\Framework_repos\\sauceDemo.xlsx");
			workbook.write(writer);
			writer.close();
		}
		workbook.close();
		reader.close();
		driver.quit();
		

	}

}
