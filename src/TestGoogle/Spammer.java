package TestGoogle;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Spammer {

	public Spammer(EventFiringWebDriver driver) {
		// TODO Auto-generated constructor stub

		// WebDriverBackedSelenium selenium = new WebDriverBackedSelenium
		// (driver, myURL);
		/*
		 * selenium.open("/"); selenium.type("q", "reddit");
		 * selenium.waitForPageToLoad("80000");
		 */
		System.out.println(driver.getTitle());
		
		WebElement gmail = driver.findElement(By.id("gb_23"));
		gmail.click();

		int i = 0;
		boolean failed = true;
		
		while (i < 3 && failed == true) {

			WebElement username = (new WebDriverWait(driver, 5))
					.until(new ExpectedCondition<WebElement>() {
						@Override
						public WebElement apply(WebDriver d) {
							return d.findElement(By.id("Email"));
						}
					});
			username.clear();
			username.sendKeys("myUserName" + i);

			WebElement password = driver.findElement(By.id("Passwd"));
			password.clear();
			password.sendKeys("myPW" + i);

			WebElement bSignIn = driver.findElement(By.id("signIn"));
			System.out.println(bSignIn.toString() + i);
			bSignIn.click();
			
			//highlightElement(driver, bSignIn);

			failed = driver.findElements(By.id("errormsg_0_Passwd")).size() != 0;

			i++;
		}
		
		driver.executeScript("alert('lol failed')");
	}
	
	/*
	public void highlightElement(WebDriver driver, WebElement element) {
	    for (int i = 0; i < 2; i++) {
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("arguments[0].setAttribute('style', arguments[1]);",
	                element, "color: yellow; border: 2px solid yellow;");
	        js.executeScript("arguments[0].setAttribute('style', arguments[1]);",
	                element, "");
	    }
	}*/

}
