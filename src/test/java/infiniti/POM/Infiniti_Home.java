package infiniti.POM;

import java.util.*;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import test.Utilities.Framework_Base;

public class Infiniti_Home extends Framework_Base
{
	WebDriverWait Wait ;
	public Infiniti_Home( WebDriver D, Actions A, JavascriptExecutor E, WebDriverWait W )
	{
		PageFactory.initElements(D, this );
		Wait = W;
	}
	
	//Services			
	@FindBy ( xpath = "//div[@class='products']/a[3]" )
	private WebElement Health_Check_Icon;
	
	
	//For HC service selected
	@FindBy ( xpath = "//input[@placeholder='Select a category']" )
	private WebElement Select_Category;
	
	@FindBy ( xpath = "//*[@*='Select a category']/following-sibling::ul/li/a" )
	private List<WebElement> Categories;
	
	@FindBy ( xpath = "//div[@*='showExplorePage(keyWords)']/*[@class='mm mm-search']" )
	private WebElement Search_Category;

	
	//Banners
	@FindBy ( linkText = "Talk to Doctor Now" )
	private WebElement Talk_To_Doctor;

	@FindBy ( xpath = "//a[@aria-label='Medicine']" )
	private WebElement Order_Medicine;

	@FindBy ( xpath = "//a[@aria-label='ReferFriend']" )
	private WebElement Reffer_And_Earn;
	
	
	//Actions
	public void selectService( String Product ) throws InterruptedException 
	{	
		//Common.click_Not_Now();
		
		if( Product.equalsIgnoreCase( "Health Check" ) )
			this.Health_Check_Icon.click();
	}

	public void selectCategory( String Category )
	{
		//Common.click_Not_Now();
		
		this.Select_Category.click();
		
		for( WebElement C : this.Categories )
		{
			if( C.getText().equalsIgnoreCase( Category ) )
			{
				C.click();
				break;
			}
		}
	}
	
	public void typeCategory( String Category )
	{
		//Common.click_Not_Now();
	
		this.Select_Category.sendKeys( Category );
	}
	
	public List<String> searchHC( String[] Categories )
	{
		//Common.click_Not_Now();
		
		List<String> failed = new ArrayList<String>();
		
		for( int i = 0 ; i < Categories.length ; i++ )
		{
			for( int j = 0 ; j < Categories[i].length() ; j++ )
			{
				this.Select_Category.sendKeys( Categories[i].substring( 0, i+1 ) );
				
				Wait.until( ExpectedConditions.visibilityOfAllElements( this.Categories ) );
				for( WebElement A :  this.Categories )
				{
					if( ! A.getText().contains( Categories[i].substring( 0, i+1 ) ))
					{
						failed.add( Categories[i].substring( 0, i+1 ) ) ;
						break;
					}
				}
			}
		}
		
		return failed ;
	}
	
	public void clickBanner( String Banner )
	{
		//Common.click_Not_Now();
		
		if( Banner == "Order Medicine" )
			this.Order_Medicine.click();
		if( Banner == "Reffer And Earn" )
			this.Reffer_And_Earn.click();
	}
	
}
