package test.Utilities;

import java.util.*;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Framework_Base 
{
	//Directory to browser language binders 
	protected String LanguageBinders = "D:\\eclipse\\Resources\\Language_Binders\\";
	
	//Driver Session properties
	protected List<String> Sessions;
	protected List<Object> Obj;
	protected WebDriver Driver ;
	protected Actions Action ;
	protected WebDriverWait Wait ;
	protected FluentWait<WebDriver> FluentWait ;
	protected JavascriptExecutor Executor;
	
	
	//Global flags
	protected boolean RejectNotifications ;
	
	protected void init_Driver( String Browser ) 
	{
		if( Browser.equalsIgnoreCase( "chrome" ) )
		{
			System.setProperty("webdriver.chrome.driver", LanguageBinders + "chromedriver.exe" );
			this.Driver = new ChromeDriver() ;
		}
		if( Browser.equalsIgnoreCase( "firefox" ) )
		{
			System.setProperty("webdriver.gecko.driver", LanguageBinders + "geckodriver.exe" );
			this.Driver = new FirefoxDriver() ;
		}
		if( Browser.equalsIgnoreCase( "ie" ) )
		{
			System.setProperty("webdriver.ie.driver", LanguageBinders + "IEDriverServer.exe" );
			this.Driver = new InternetExplorerDriver();
		}
		if( Browser.equalsIgnoreCase( "edge" ) )
		{
			System.setProperty("webdriver.edge.driver", LanguageBinders + "msedgedriver.exe" );
			this.Driver = new EdgeDriver();
		}
	}

	protected void launch_Session( WebDriver Driver, String URL, int ImplicitWait, int WebDriverWait )
	{
		Driver.get( URL );
		
		Driver.manage().window().maximize();

		Driver.manage().timeouts().implicitlyWait(ImplicitWait, TimeUnit.SECONDS);
		
		this.Wait = new WebDriverWait( Driver, WebDriverWait );
		this.FluentWait = new FluentWait<WebDriver>( Driver );
		this.Action = new Actions( Driver );
		this.Executor = (JavascriptExecutor) Driver;
	}
	
	protected void end_Test(  )
	{
		
	}
	
}
