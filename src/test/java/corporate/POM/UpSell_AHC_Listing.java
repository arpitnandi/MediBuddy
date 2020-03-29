package corporate.POM;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import test.Utilities.Framework_Base;

public class UpSell_AHC_Listing extends Framework_Base
{
	WebDriver Driver ;
	Actions Action ;
	
	public UpSell_AHC_Listing( WebDriver D, Actions A, JavascriptExecutor E, WebDriverWait W ) 
	{
		PageFactory.initElements( D , this );
		Driver = D;
		Action = A;
	}
	
	//Elements
	@FindBy( className ="configLoading" )
	public WebElement Config_Loading ;
	
	@FindBy( xpath ="//*[@*='modal-content']" )
	public WebElement Select_City_Modal ;
	
	@FindBy( xpath ="//*[@class='m-t-10 smTxt']" )
	public List<WebElement> Top_Cities ;
	
	@FindBy( xpath ="//*[contains(@class,'all-city')]/div" )
	public List<WebElement> All_Cities ;

	@FindBy( xpath = "//*[@*='mdbTxt m-b-5 bold']" )
	public List<WebElement> AHC_Names ;
	
	@FindBy( xpath = "//*[text()='View Package']" )
	public List<WebElement> View_Package_Btns;
	
	
	//Action methods
	public void  select_City( String City )
	{
		while( true )
		{
			try {
				this.Select_City_Modal.isDisplayed();
				for( int i = 0 ; i < this.Top_Cities.size() ; i++ )
				{
					if( this.Top_Cities.get( i ).getText().equalsIgnoreCase( City ) ||  i == this.Top_Cities.size()-1 )
					{
						this.Top_Cities.get( i ).click();
						break;
					}	
				}
				break;
			}
			catch( Exception E )
			{
				Driver.navigate().refresh();
			}
		}
	}
	
	public void view_Package( String AHC )
	{
		for( int i = 0 ; i < this.View_Package_Btns.size() ; i++ )
		{
			if( this.AHC_Names.get( i ).getText().equalsIgnoreCase( AHC ) )
			{
				Action.moveToElement( this.View_Package_Btns.get( i ) ).perform();
				this.View_Package_Btns.get( i ).click();
				break ;
			}
		}
	}
}
