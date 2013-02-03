package TestGoogle;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.internal.selenesedriver.FindElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.thoughtworks.selenium.Wait;

public class test1 {

	static WebElement box;
	static String myURL = "http://en.wikipedia.org/wiki/List_of_social_networking_websites";
	
	public static void main(String[] args) throws InterruptedException {

		ProfilesIni allProfs = new ProfilesIni();
		FirefoxProfile myProf = allProfs.getProfile("tester");
		
		WebDriver d = new FirefoxDriver(myProf);
		EventFiringWebDriver driver = new EventFiringWebDriver(d);
		driver.get(myURL);
		//Spammer mySpammer = new Spammer(driver);
		
		Thread.sleep(2000L); //wait for page to completely load
		WebElement heading = driver.findElement(By.xpath("//*[@id='firstHeading']/span"));
		System.out.println(heading.getText());//print out heading of the page
		
		driver.executeScript("scroll(0,250)"); //scroll down a bit

		box = (new WebDriverWait(driver, 5))
				.until(new ExpectedCondition<WebElement>() {
					@Override
					public WebElement apply(WebDriver driver) {
						return driver.findElement(By.xpath("//*[@id='mw-content-text']/table[2]/tbody"));
					}
				});
		
		//findUsingTagMethod(); //find every element with html tag "a"
		
		parseLoopMethod(driver);
		driver.close();
	}
	
	private static void parseLoopMethod(EventFiringWebDriver driver) {
		
		int i = 1;
		List <WebElement> nameList = new ArrayList<WebElement>();
		
		while(true) {
			try{
				//select each element from the name column and store it into nameList
				WebElement name = driver.findElement(By.xpath("//*[@id='mw-content-text']/table[2]/tbody/tr[" + i + "]/th/a"));
				nameList.add(name);
				System.out.println(name.getText());
				i++;
			} 
			catch (Throwable t) {
				System.out.println("%%%%%%%%%%% SCAN FINISHED -- TOTAL: " + (i-1) + " %%%%%%%%%%% CATCH:");
				System.out.println(t.toString());
				break;
			}
		}	
	}

	public static void findUsingTagMethod() {
		List<WebElement> names = box.findElements(By.tagName("a"));
		System.out.println("Total names: "+ names.size());
		
		for (int j = 0; j < names.size(); j ++ ) {
			System.out.println(names.get(j).getText());
		}
	}
}
