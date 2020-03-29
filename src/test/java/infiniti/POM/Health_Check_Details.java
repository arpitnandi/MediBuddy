package infiniti.POM;

import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import test.Utilities.Framework_Base;

public class Health_Check_Details extends Framework_Base
{
	WebDriver Driver ;
	WebDriverWait Wait ;
	
	public Health_Check_Details( WebDriver D, Actions A, JavascriptExecutor E, WebDriverWait W )
	{
		PageFactory.initElements( D, this );
		Driver = D;
		Wait = W;
	}
	
	//Select check up Appointment
	@FindBy( linkText="Book Now" )
	private WebElement Book_Now_btn;
	
	@FindBy( xpath = "//*[contains(@class,'timeslider-days')]/ul/div/div" )
	private List<WebElement> Date_Tiles;

	@FindBy( xpath = "//*[contains(@class,'timeslider-days')]/ul/div/div/li" )
	private List<WebElement> Dates;
	
	@FindBy( xpath = "//*[@class='preferredTimeBox']/ul/li[*]/label" )
	private List<WebElement> Time_Slots;
	
	@FindBy( xpath = "//*[text()='Continue' and @class='btn btn-primary btn-sm']" )
	private WebElement Continie_btn;
	
	
	//Activity Flags
	boolean Appointment = false;
	
	
	//Actions
	public void book_Now()
	{
		Wait.until( ExpectedConditions.visibilityOf( this.Book_Now_btn ) );
		this.Book_Now_btn.click();
	}
	
	public boolean select_Appointment( int Days, String Required_Slot )
	{
		//Date selecting mechanism
		Calendar Cal = Calendar.getInstance();
		Cal.add(Calendar.DATE, Days);
		
		String Date = Cal.getTime().toString(); //Taking time in => "www mon DD HH:MM:SS IST YYYY" format

		int Required_Day = Integer.parseInt( Date.substring( 8, 10) );
		
		for( int num = 0 ; num < Date_Tiles.size() ; num++ )
		{
			WebElement Selected_Tile = this.Dates.get( num );
			
			String[] Selected = Selected_Tile.getText().toString().split(" ");
			
			Date = Selected[0] ;
			
			int Selected_Day = Integer.parseInt( Date.substring( Date.length() - 2 , Date.length() ) );
			
			if( Selected_Day == Required_Day )
			{
				if( this.Date_Tiles.get( num ).getAttribute( "class" ).contains( "active" ) )
					this.Date_Tiles.get( num ).click();
				else
					this.Date_Tiles.get( num + 1 ).click();
				Appointment = true;
				break;
			}
		}

		//Time selecting mechanism
		for( int num = 0 ; num < Time_Slots.size() ; num++ )
		{
			String Selected_Slot = Time_Slots.get( num ).getText().toString();
			
			if( Required_Slot.equalsIgnoreCase( Selected_Slot  ))
			{
				Time_Slots.get( num ).click();
				Appointment = true;
				break;
			}
		}
		
		return Appointment;
	}
	
	public void click_Continue() 
	{
		if( this.Continie_btn.isEnabled() )
			this.Continie_btn.click();
	}
}
