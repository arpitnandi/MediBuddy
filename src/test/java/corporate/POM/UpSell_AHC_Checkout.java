package corporate.POM;

import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UpSell_AHC_Checkout 
{

	WebDriver Driver ;
	Actions Action ;
	JavascriptExecutor Executor ;
	WebDriverWait Wait ;
	
	public UpSell_AHC_Checkout( WebDriver D, Actions A, JavascriptExecutor E, WebDriverWait W ) 
	{
		PageFactory.initElements( D , this );
		Driver = D ;
		Action = A ;
		Executor = E ;
		Wait = W ;
	}
	
	//Elements
	@FindBy( xpath = "//*[contains(@*,'dateArray')]" )
	public List<WebElement> Date_Tiles ;
	
	@FindBy( xpath = "//div[@class='timeslider-days glider-contain multiple']//li/span" )
	public List<WebElement> Date_Availability ;
	
	@FindBy( xpath = "//div[@class='timeslider-days glider-contain multiple']//li/div" )
	public List<WebElement> Dates ;
	
	@FindBy( xpath = "//*[contains(@class,'action-card')]" )
	public List<WebElement> Provider_Slot_Cards ;
	
	@FindBy( xpath = "//*[contains(@class,'action-card')]//b" )
	public List<WebElement> Provider_Names ;
	
	@FindBy( xpath = "//*[contains(@class,'location')]" )
	public List<WebElement> Test_Center_Location ;
	
	@FindBy( xpath = "//*[contains(@class,'Timeline')]" )
	public List<WebElement> Test_Center_Timing ;

	@FindBy( className = "smTxt elipsis" )
	public List<WebElement> Test_Center_Address ;

	@FindBy( className = "pull-right" )
	public List<WebElement> Test_Center_Location_Link ;
	
	@FindBy( xpath = "//*[@class='preferredTimeBox']//li/label" )
	public List<WebElement> Time_Slots ;

	@FindBy( xpath = "//*[@*='col-xs-3 p-l-0 p-r-0']//div/div" )
	public List<WebElement> Availability_Status ;
	
	@FindBy( xpath = "//*[@*='col-xs-3 p-l-0 p-r-0']//span/a" )
	public List<WebElement> Select_Slot_Btn ;
	
	@FindBy( xpath = "//*[text()='Confirm slot']" )
	public WebElement Confirm_Slot_Btn ;
	
	@FindBy( xpath = "//*[@*='bold place-det-bg']" )
	public WebElement Selected_Slot ;

	@FindBy( xpath ="//*[@*='bold m-b-5']" )
	public WebElement Selected_Provider ;
	
	@FindBy( xpath = "//button/*[text()='Continue']/.." )
	public WebElement Continue_Btn ;
	
	@FindBy( xpath ="//button/*[text()='Confirm Booking']/.." )
	public WebElement Confirm_Booking_Btn ;
	
	@FindBy( xpath = "//*[@class='action-card m-b-20 md']//button" )
	public List<WebElement> Add_Beneficiery_Btns ;
	
	
	//Actions
	public String select_Date( int Days )
	{
		String Today, Date = null ;
		
		Calendar Cal = Calendar.getInstance();
		Cal.add(Calendar.DATE, Days);
		
		Today = Cal.getTime().toString(); //Taking date-time into this "www mon DD HH:MM:SS IST YYYY" format

		int Required_Day = Integer.parseInt( Today.substring( 8, 10) );
		
		for( int num = 0 ; num < Date_Tiles.size() ; num++ )
		{
			WebElement Selected_Date = this.Dates.get( num );
			
			int Selected_Day = Integer.parseInt( Selected_Date.getText().substring( 5 , 7 ) );

			if( Selected_Day == Required_Day )
			{
				if( this.Date_Availability.get( num ).getText().equalsIgnoreCase( "Available" ) )
				{
					this.Date_Tiles.get( num ).click();
					Date = Today.substring( 0, 3 ) + ", " + Today.substring( 8, 10 ) + " " + Today.substring( 4, 7 ) ;
				}
				else if( this.Date_Availability.get( num ).getText().equalsIgnoreCase( "Unavailiable" ) )
				{
					this.Date_Tiles.get( num + 1 ).click();
					Cal.add(Calendar.DATE, Days+1 );
					Today = Cal.getTime().toString();
					Date = Today.substring( 0, 3 ) + ", " + Today.substring( 8, 10 ) + Today.substring( 4, 7 ) ;
				}
				break;
			}
		}
		
		return Date ;
	}
	
	public String select_Timeslot( String Provider, String Slot ) 
	{
		for( int i = 0 ; i < this.Select_Slot_Btn.size() ; i++ )
		{
			if( this.Provider_Names.get( i ).getText().equals( Provider ) )
			{
				if( this.Select_Slot_Btn.get( i ).isDisplayed() )
					this.Select_Slot_Btn.get( i ).click();
				if( this.Availability_Status.get( i ).getText().equals( "Not Available" ) )
				{
					this.Select_Slot_Btn.get( i ).click();
					Provider = this.Provider_Names.get( i+1 ).getText() ;
				}
				for( int j = 0 ; j < this.Time_Slots.size() ; j++ )
				{
					if( this.Time_Slots.get( j ).getText().equals( Slot ) )
					{
						this.Time_Slots.get( j ).click();
						this.Confirm_Slot_Btn.click();
						break;
					}
				}
				break;
			}
		}
		return Provider ;
	}
	
	public boolean verify_Provider_Cards() 
	{
		boolean Provider_Content = false ;
		for( int n = 0 ; n < this.Provider_Slot_Cards.size() ; n++ )
		{
			Provider_Content = this.Provider_Names.get(n).isDisplayed() ;
			Assert.assertTrue( Provider_Content );
			if( this.Provider_Names.get(n).isDisplayed() )
				System.out.println( " | Provider Name     = " + this.Provider_Names.get( n ).getText() );
			
			Provider_Content = this.Test_Center_Location.get(n).isDisplayed() ;
			Assert.assertTrue( Provider_Content );
			if( this.Test_Center_Location.get(n).isDisplayed() )
				System.out.println( " | Provider Location = " + this.Test_Center_Location.get( n ).getText() );
			
			Provider_Content = this.Test_Center_Timing.get(n).isDisplayed() ;
			Assert.assertTrue( Provider_Content );
			if( this.Test_Center_Timing.get(n).isDisplayed() )
				System.out.println( " | Provider Timing   = " + this.Test_Center_Timing.get( n ).getText() );
			
			Provider_Content = this.Test_Center_Address.get(n).isDisplayed() ;
			Assert.assertTrue( Provider_Content );
			if( this.Test_Center_Address.get(n).isDisplayed() )
				System.out.println( " | Provider Address  = " + this.Test_Center_Address.get( n ).getText() );
			
			Provider_Content = this.Test_Center_Location_Link.get(n).isDisplayed() ;
			Assert.assertTrue( Provider_Content );
			if( this.Test_Center_Location_Link.get(n).isEnabled() )
			{
				this.Test_Center_Location_Link.get(n).click();
				System.out.println( "Title of G_Map window = " + Driver.getTitle() );
				Driver.close();
				Driver.switchTo().defaultContent();
			}
		}
		return Provider_Content ;
	}
	
	public boolean verify_Mobile( String Mobile )
	{
		System.out.println( Executor.executeScript( "docment.getElementByTagName('Input')[whole_number].value;" ) );
		
		Assert.assertEquals( Mobile , Executor.executeScript( "docment.getElementByTagName('Input')[whole_number].value;" ) );
		
		return true ;
	}
}
