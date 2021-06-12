package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class AmazoneSearch {
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String category = null;
		String searchVal = null;
		
		System.setProperty("webdriver.chrome.driver","chromedriver");
WebDriver driver = new ChromeDriver();
		
		driver.get("https://www.amazon.in/");
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
		
		try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
             Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce","root","root");
             
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select * from Amazon");
             
             while (rs.next()) {
            	 
            	 System.out.println(rs.getString(2)+" "+rs.getString(3));
            	 
            	 category = rs.getString(2);
            	 searchVal= rs.getString(3);
             }
			
		
	   } catch (ClassNotFoundException e) {
           // TODO Auto-generated catch block
		   System.out.println("Class not found");
       } catch (SQLException e) {
           // TODO Auto-generated catch block
    	   System.out.println("SQL Exception");

	}
		WebElement searchDropDown = driver.findElement(By.xpath("//*[@id ='searchDropdownBox'] "));
		Select Electronics = new Select(searchDropDown);
		Electronics.selectByVisibleText(category);
		
		WebElement mobile = driver.findElement(By.xpath("//*[@id ='twotabsearchtextbox'] "));
		mobile.sendKeys(searchVal);
		WebElement searchbtn = driver.findElement(By.xpath("//*[@id ='nav-search-submit-button'] "));
		searchbtn.click();
		List<WebElement> results = driver.findElements(By.xpath("//h2[@class='a-size-mini a-spacing-none a-color-base s-line-clamp-2']"));
		System.out.println("total results are: "+results.size());
		
		for (WebElement e :results) {
			System.out.println(e.getText());
		}
		
		driver.close();
		
			
		
		
		}
}
