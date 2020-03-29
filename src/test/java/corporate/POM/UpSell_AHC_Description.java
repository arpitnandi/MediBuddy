package corporate.POM;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UpSell_AHC_Description 
{
	WebDriver Driver ;
	WebDriverWait Wait ;
	
	public UpSell_AHC_Description( WebDriver D, Actions A, JavascriptExecutor E, WebDriverWait W ) 
	{
		PageFactory.initElements( D , this );
		Driver = D ;
		Wait = W ;
	}
	
	@FindBy( xpath ="//*[text()='Book Appointment']" )
	public WebElement Book_Appointment_Btn;
}
