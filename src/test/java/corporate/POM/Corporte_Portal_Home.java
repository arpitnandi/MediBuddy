package corporate.POM;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import test.Utilities.Framework_Base;

public class Corporte_Portal_Home extends Framework_Base
{
	WebDriver Driver ;
	Actions Action ;
	JavascriptExecutor Executor ;
	WebDriverWait Wait ;
	
	public Corporte_Portal_Home( WebDriver D, Actions A, JavascriptExecutor E, WebDriverWait W ) 
	{
		PageFactory.initElements( D, this );
		Driver = D ;
		Action = A ;
		Executor = E ;
		Wait = W ;
	}
	
	//Home page Eelements
	@FindBy( id = "username" )
	public WebElement User_Name ;
	
	@FindBy( id = "password" )
	public WebElement Password ;
	
	@FindBy( id = "signinBtn" )
	public WebElement Sign_In_Btn;
	
	@FindBy( xpath = "//*[@*='col-md-12 multipleRoles']//input" )
	public List<WebElement> Multiple_Role_Access ;
	
	@FindBy( xpath = "//*[text()='Confirm']" )
	public WebElement Confirm_Role ;
	
	@FindBy( className = "clearfix" )
	public WebElement Your_health_buddy_on_the_go_Popup ;
	
	@FindBy( xpath = "//*[text()='OUTPATIENT SERVICES?']" )
	public WebElement OUTPATIENT_SERVICES_Popup ;
	
	@FindBy( xpath = "//*[text()='sponsored Health check ']" )
	public WebElement Sponsored_Health_check_Popup ;
	
	@FindBy( xpath ="//*[contains(text(),'sponsored')]/../following-sibling::*//a" )
	public WebElement Book_Now_Sponsored_Btn ;
	
	@FindBy( xpath = "//*[text()='Close']" )
	public WebElement Close_Your_health_buddy_on_the_go;

	@FindBy( xpath = "//*[@class='close']/span" )
	public WebElement Close_Outpaitent_Services ;

	@FindBy( xpath = "//*[text()='Wellness']/preceding-sibling::*" )
	public WebElement Wellness_Banner ;
	
	@FindBy( xpath = "//*[text()='Health Check']/.." )
	public WebElement Heartbeat_Logo;
	
	@FindBy( xpath = "//*[text()='Wellness']/following-sibling::div/*[text()='Book Appointment']" )
	public WebElement Book_Appintment_Btn ;
	
	@FindBy( xpath ="//*[text()='Health Check']/following-sibling::*/*[text()='Book now']" )
	public WebElement Book_Now_Btn ;
	
	@FindBy( id = "btnHealthCheck" )
	public WebElement Step1_Health_Check ;
	

	@FindBy( xpath ="//*[@*='vaswellnessahc.aspx']" )
	public WebElement OK_Step1_Health_Check ;
	
	@FindBy( id = "liMenu" )
	public WebElement Menu ;
	
	@FindBy( xpath = "//*[text()='Wellness']" )
	public WebElement Wellness_Menu_Option ;
	
	
	//Corporates method-wise
	String[] M_1 = { "CB&I", "Microchip", "Ashok Leyland", "3M", "L&T", "Cognizant", "Amadeus", "SBI", "Monsanto", "Arcadis", "ABG", "JDTL" };
	String[] M_2 = { "Texas", "Vistaar", "Lummus", "Real Page", "Pilkington", "LM Windpower", "Spra Banking", "Sopra Steria", "Yokogawa", "Aveva" };
	String[] M_3 = { "Analog", "TCS" };
	String[] M_4 = { "Microchip", "CB&I", "Vistaar", "Lummus", "Real Page", "Pilkington", "Applaus", "Sopra Banking", "Sopra Steria", "LM Windpower", "JDTL", "Aveva" };
	String[] M_5 = { "Amadeus", "Cognizant", "Adobe", "BOB" };
	String[] M_6 = { "Schneider", "Citi Bank" };
	String[] M_7 = { "NOS", "BOB", "GE", "HCCB" };
	String[] M_8 = { "IBM" };
	String[] M_9 = { "GE", "HCCB" };
	String[] M_10 = { "Tech M", "JP Morgan", "Wipro" };
	

	//Flags
	boolean Corp_found ;
	
	
	//Action methods
	public String redirection( String Corporate , String Method )
	{
		for( int j = 0 ; j < 10 ; j++ )
		{
			for( int i = 0 ; i < M_1.length-1 ; i++ )
			{
				if( M_1[ i ].equalsIgnoreCase( Corporate ) || Method.contains( "1" ) || Method.isEmpty() )
				{
					Corp_found = this.method_1( Corporate );
					Method = "1" ;
					break;
				}
			}
			
			for( int i = 0 ; i < M_2.length-1 ; i++ )
			{
				if( M_2[ i ].equalsIgnoreCase( Corporate ) || Method.contains( "2" ) || Method.isEmpty()  )
				{
					Corp_found = this.method_2( Corporate );
					Method = "2" ;
					break;
				}
			}

			for( int i = 0 ; i < M_3.length-1 ; i++ )
			{
				if( M_3[ i ].equalsIgnoreCase( Corporate ) || Method.contains( "3" ) || Method.isEmpty()  )
				{
					Corp_found = this.method_3( Corporate );
					Method = "3" ;
					break;
				}
			}
			
			for( int i = 0 ; i < M_4.length-1 ; i++ )
			{
				if( M_4[ i ].equalsIgnoreCase( Corporate ) || Method.contains( "4" ) || Method.isEmpty()  )
				{
					Corp_found = this.method_4( Corporate );
					Method = "4" ;
					break;
				}
			}

			for( int i = 0 ; i < M_5.length-1 ; i++ )
			{
				if( M_5[ i ].equalsIgnoreCase( Corporate ) || Method.contains( "5" ) || Method.isEmpty()  )
				{
					Corp_found = this.method_5( Corporate );
					Method = "5" ;
					break;
				}
			}

			for( int i = 0 ; i < M_6.length-1 ; i++ )
			{
				if( M_6[ i ].equalsIgnoreCase( Corporate ) || Method.contains( "6" ) || Method.isEmpty()  )
				{
					Corp_found = this.method_6( Corporate );
					Method = "6" ;
					break;
				}
			}

			for( int i = 0 ; i < M_7.length-1 ; i++ )
			{
				if( M_7[ i ].equalsIgnoreCase( Corporate ) || Method.contains( "7" ) || Method.isEmpty()  )
				{
					Corp_found = this.method_7( Corporate );
					Method = "7" ;
					break;
				}
			}

			for( int i = 0 ; i < M_8.length-1 ; i++ )
			{
				if( M_8[ i ].equalsIgnoreCase( Corporate ) || Method.contains( "8" ) || Method.isEmpty()  )
				{
					Corp_found = this.method_8( Corporate );
					Method = "8" ;
					break;
				}
			}

			for( int i = 0 ; i < M_9.length-1 ; i++ )
			{
				if( M_9[ i ].equalsIgnoreCase( Corporate ) || Method.contains( "9" ) || Method.isEmpty()  )
				{
					Corp_found = this.method_9( Corporate );
					Method = "9" ;
					break;
				}
			}

			for( int i = 0 ; i < M_10.length-1 ; i++ )
			{
				if( M_10[ i ].equalsIgnoreCase( Corporate ) || Method.contains( "10" ) || Method.isEmpty()  )
				{
					Corp_found = this.method_10( Corporate );
					Method = "10" ;
					break;
				}
			}
			
			if( Corp_found )
				break;
		}
		
		return Method ; 
	}
	
	public void select_Role( String Role )
	{
		if( Role.equalsIgnoreCase( "Employee") )
			this.Multiple_Role_Access.get( 0 ).click();
		if( Role.equalsIgnoreCase( "HR" ) )
			this.Multiple_Role_Access.get( 1 ).click();
	}
	
	public void cancel_Popups( String Popup ) 
	{
		if( Popup.equalsIgnoreCase( "Your_health_buddy" ) )
		{
			Wait.until( ExpectedConditions.visibilityOf( this.Your_health_buddy_on_the_go_Popup ) );
			this.Close_Your_health_buddy_on_the_go.click();
		}
		
		if( Popup.equalsIgnoreCase( "OUTPATIENT SERVICES" ) )
		{
			Wait.until( ExpectedConditions.visibilityOf( this.OUTPATIENT_SERVICES_Popup ) );
			this.Close_Outpaitent_Services.click();
		}
		
		if( Popup.equalsIgnoreCase( "Sponsored AHC" ))
		{
			Wait.until( ExpectedConditions.visibilityOf( this.Sponsored_Health_check_Popup ) );
			Driver.findElement( By.id( "mobilepopup" ) ).click();
		}
	}
	
	
	//Redirection methods
	public boolean method_1( String Corporate ) 
	{
		if( Corporate.equalsIgnoreCase( "Cognizant" ) || Corporate.equalsIgnoreCase( "Amadeus" ) )
			this.cancel_Popups( "Sponsored AHC" );
		else
			this.cancel_Popups( "Your_health_buddy" );

		Action.moveToElement( this.Heartbeat_Logo ).build().perform();
		
		if( Corporate.equalsIgnoreCase( "Arcadis") )
			this.Book_Appintment_Btn.click();
		else
			this.Book_Now_Btn.click();
		
		return true ;
	}
	
	public boolean method_2( String Corporate ) 
	{	
		this.cancel_Popups( "Your_health_buddy" );
		
		Wait.until( ExpectedConditions.invisibilityOf( Driver.findElement( By.xpath( "//div[@ class='modal-backdrop fade']") ) ) );
		
		this.Wellness_Banner.click();
		
		while( true )
		{
			try {
				Action.moveToElement( this.Heartbeat_Logo ).build().perform();
				this.Book_Now_Btn.click();
				break;
			}
			catch( Exception E )
			{
				Driver.navigate().refresh();
			}
		}
		
		return true ;
	}
	
	public boolean method_3( String Corporate )
	{
		if( Corporate.equalsIgnoreCase( "NOS" ) )
		{
			this.Multiple_Role_Access.get( 0 ).click();
			this.Confirm_Role.click();
			this.cancel_Popups( "OUTPATIENT SERVICES" );
		}
		else
			this.cancel_Popups( "Your_health_buddy" );
		
		this.Menu.click();
		
		this.Wellness_Menu_Option.click();
		
		return true ;
	}

	public boolean method_4( String Corporate )
	{
		this.cancel_Popups( "Your_health_buddy" );
		
		this.Menu.click();
		
		this.Wellness_Menu_Option.click();
		
		Action.moveToElement( this.Heartbeat_Logo ).build().perform();
		
		this.Book_Now_Btn.click();
		
		return true ;
	}
	
 	public boolean method_5( String Corporate ) 
	{
		Wait.until( ExpectedConditions.visibilityOf( this.Sponsored_Health_check_Popup ) );
		
		this.Book_Now_Sponsored_Btn.click();
 		
		return true ;
	}
	
	public boolean method_6( String Corporate ) 
	{
		Wait.until( ExpectedConditions.visibilityOf( this.Step1_Health_Check ) );
		this.Step1_Health_Check.click();
		
		if( Corporate.equalsIgnoreCase( "Schneider" ) )
			this.OK_Step1_Health_Check.click();
		
		return true ;
	}
	
	public boolean method_7( String Corporate )
	{
		
		
		return true ;
	}
	
	public boolean method_8( String Corporate )
	{

		
		return true ;
	}
	
	public boolean method_9( String Corporate )
	{

		
		return true ;
	}
	
	public boolean method_10( String Corporate )
	{

		
		return true ;
	}

}
