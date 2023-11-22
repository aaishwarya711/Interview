import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class testing {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		//System.setProperty("webdriver.chrome.driver" , "/iCloud Drive/Documents/chromedriver");
		WebDriver driver = new ChromeDriver();
		
		//driver().manage().window().maximize();
		
		driver.get("https://target.com/");
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	
		System.out.println(driver.getTitle()); // put assertions part of testng
		System.out.println(driver.getCurrentUrl());
		
		String productName = "Bananas";
		
		driver.findElement(By.id("search")).sendKeys(productName);
		driver.findElement(By.xpath("//button[text()=\"search\"]")).click();
		
		for (int j=0;j<5;j++) {
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		js.executeScript("window.scrollBy(0,6000)");
		
		List<WebElement> products=driver.findElements(By.xpath("//div[@data-test='product-grid']//section/div/div//div[@title]/a"));
		
		
		System.out.println(products.size());
		
		
		//First page dropdown :
		driver.findElement(By.xpath("//button[@id=\"select-custom-button-id\"]")).click();
		
		
		for(int i=0;i<products.size();i++)
		{
			
		String name = products.get(i).getText();
		
		System.out.println("stored");
		if (i==products.size()-1) {
			//Add name[products.size()-1] to cart
			driver.findElement(By.xpath("//button[text()='Pick it up']")).click();
		}
		
		//View cart  : //a[text()='View cart & check out'] - CSS 
		
		int quantity = 5;
		
		WebElement Staticdropdown = driver.findElement(By.id(":ra:-Gerber 2nd Foods Wonderfoods, Banana, Plum &#38; Grape Puree Tub - 8oz/2pk"));
		
		Select dropdown = new Select(Staticdropdown);
		
		//dropdown.selectByIndex(5);
		
		dropdown.selectByValue("quantity");
		
		//Price check
		
		String pricecheck = driver.findElement(By.xpath("//p[@data-test='cartItem-price']")).getText();
		float p=Float.parseFloat(pricecheck);  
		
		float pricecompare = quantity*p;
		//SoftAssert a = new SoftAssert();
		
		//Assert.assertTrue(pricecompare==pricecheck,"The prices matched");
	
		
		
		
	
		
		
		if(name==null || name=="")
		{
		
			System.out.println("product "+i+"does not have title");
		}
		
		else
		{
			System.out.println("product"+i+" have title");
		}
		

		
	}
		
		//Thread.sleep(200);
		//WebElement click = driver.findElement(By.xpath("//div[@data-test='pagination']//div[3]/button"));
		//click.click();
		
		Thread.sleep(5000);
		Boolean Enabled = driver.findElement(By.xpath("//div[@data-test='pagination']//div[3]/button")).isEnabled();
		if (Enabled) {
		Actions act =  new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//div[@data-test='pagination']//div[3]/button"))).click().perform();
		//driver.close();
		}
		else {
			//j = 5;
			//Add item
			
			
		}
		}
	}

	private static WebDriver driver() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/* List<WebElement> allLinks = driver.findElements(By.tagName("a"));
	 
	 for(WebElement link:allLinks){
		 System.out.println(link.getText() + " - " + link.getAttribute("href"));
		 }*/
}



