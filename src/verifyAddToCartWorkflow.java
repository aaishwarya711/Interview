import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class verifyAddToCartWorkflow  {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws InterruptedException, ParseException {
		// TODO Auto-generated method stub
		
		WebDriver driver = new ChromeDriver();
		
		//1. Invoking application URL and verifying it
		
		String ApplicationURL = "https://target.com/";
		driver.get(ApplicationURL);
		System.out.println(driver.getTitle());
		System.out.println(driver.getCurrentUrl());
		
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//2. Product search initialized which has more than one page of results
		
		String productName = "Bananas";
		
		WebElement productsearch = driver.findElement(By.id("search"));
		WebElement searchbutton = driver.findElement(By.xpath("//button[text()=\"search\"]"));
		productsearch.sendKeys(productName);
		searchbutton.click();
		
		Boolean Enabled = true;
		do {
			
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("window.scrollBy(0,6000)");
			
			List<WebElement> products=driver.findElements(By.xpath("//div[@data-test='product-grid']//section/div/div//div[@title]/a"));
			//System.out.println(products.size());
			for(int i=0;i<products.size();i++)
			{	
			
			String retrievedproducts = products.get(i).getText();
			if(retrievedproducts == null || retrievedproducts =="")
			{
			
				System.out.println(retrievedproducts + " does not have title");
			}
			else
			{
				System.out.println(retrievedproducts + " has title");
			}
			}
			
		//3. Loop for pagination
			
			Enabled = driver.findElement(By.xpath("//div[@data-test='pagination']//div[3]/button")).isEnabled();
			Thread.sleep(5000);
			if (Enabled) {
				Actions act =  new Actions(driver);
				act.moveToElement(driver.findElement(By.xpath("//div[@data-test='pagination']//div[3]/button"))).click().perform();
				}
			}while(Enabled);
			Thread.sleep(5000);
			
		//4.Loop for the last found object and adding to cart
			
			List<WebElement> lastpageproducts=driver.findElements(By.xpath("//button[text()='Pick it up' or text()='Add to cart']"));
			lastpageproducts.get(lastpageproducts.size()-1).click();
			WebElement cartcheckout = driver.findElement(By.xpath("//div[@data-test='content-wrapper']//div[3]/a"));
			cartcheckout.click();
	
			
		//5.Increasing the cart quantity and validation
			
			int quantity = 6;
			WebElement Staticdropdown = driver.findElement(By.xpath("//select[@data-test='cartItem-qty']"));
			Select dropdown = new Select(Staticdropdown);
			dropdown.selectByIndex(quantity);
			
		//6.Price check
			
			String pricecheck = driver.findElement(By.xpath("//p[@data-test='cartItem-price']")).getAttribute("textContent");
			
			NumberFormat format = NumberFormat.getCurrencyInstance();
			Number trimprice = format.parse(pricecheck);
			
			String s= trimprice.toString();
			float f = Float.parseFloat(s);
			float quantity_f = quantity;
			float calculatedprice = (quantity_f-1) * f;
			Thread.sleep(2000);
			String totalamountCart = driver.findElement(By.xpath("//p[@data-test='cartItem-price']")).getAttribute("textContent");
			NumberFormat removecurrency = NumberFormat.getCurrencyInstance();
			Number trimcurrency = removecurrency.parse(totalamountCart);
			String s1= trimcurrency.toString();
			float finalcartprice = Float.parseFloat(s1);
			
			if(calculatedprice==finalcartprice) {
				System.out.println("Price matched");
			}
			else
			{
				System.out.println("Price not matched");
			}
			
		driver.close();
	
		
	}
}
		
		
	
	





