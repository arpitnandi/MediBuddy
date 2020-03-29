package infiniti.POM;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import test.Utilities.Framework_Base;

public class Common_For_All_Pages extends Framework_Base
{
	WebDriver Driver ;
	
	public Common_For_All_Pages( WebDriver D, Actions A, JavascriptExecutor E, WebDriverWait W )
	{
		PageFactory.initElements( D, this );
		Driver = D;
	}
	
	//User section
	@FindBy ( xpath = "//*[@*='assets/icons/male-user.svg']" )
	private WebElement Avatar_icon;
	
	@FindBy ( xpath = "//a[@ng-click='goToProfile()']" )
	private WebElement Profile;
	
	@FindBy( xpath = "//*[contains(@class,'modal-login')]" )
	private WebElement Login_Modal;
	
	@FindBy ( xpath = "//button[@class='btn btn-primary']" )
	private WebElement Login_Btn;
	
	@FindBy ( xpath = "//a[@id='hc_dLabel']" )
	private WebElement Select_City;
	
	@FindBy ( id="wzrk-cancel" )
	private WebElement Not_Now;
	
	@FindBy ( xpath = "//a[text()='Log in']" )
	private WebElement Log_in;
	
	@FindBy ( name = "userName" )
	private WebElement Usrename;
	
	@FindBy ( name = "password" )
	private WebElement Password;

	@FindBy ( xpath = "//*[@ng-click='signout()']" )
	private WebElement Sing_Out;
	
	//Cities
	@FindBy ( xpath = "//*[contains(@class,'top-cities')]" )
	private List<WebElement> Top_Cities;
	
	//Actions
	public void selectCity( String City )
	{
		this.Select_City.click();
		
		//this.click_Not_Now();
		
		for( int i = 0 ; true ; i++ )
		{
			if( Top_Cities.get(i).getText().equalsIgnoreCase(City) )
			{
				Top_Cities.get(i).click();
				break;
			}
		}
	}
	
	public void login( String UN, String PW ) 
	{
		if( ! this.Login_Modal.isDisplayed() )
			this.Log_in.click();

		if( this.Login_Modal.isDisplayed() )
		{
			this.Usrename.sendKeys( UN );
			this.Password.sendKeys( PW );
			
			this.Login_Btn.click();
		}
	}

	public void click_Not_Now()
	{
		if( super.RejectNotifications == false )
		{
			Not_Now.click();
			super.RejectNotifications = true ;
		}
	}

	public void openProfile( WebDriver D, Actions A, WebDriverWait W, String Page ) 
	{
		W.until( ExpectedConditions.invisibilityOf( D.findElement( By.xpath( "//a[@class='btn btn-primary btn-block btn-booking']" ) ) ));
		this.Avatar_icon.click();
		
		if( Page.equalsIgnoreCase( "Same" ))
			this.Profile.click();
		if( Page.equalsIgnoreCase( "New" ))
			A.moveToElement( this.Profile ).contextClick().sendKeys(Keys.DOWN).sendKeys(Keys.DOWN).sendKeys(Keys.ENTER).build().perform();
	}
	
	public void signout() 
	{
		this.Avatar_icon.click();
		
		this.Sing_Out.click();
	}
}
