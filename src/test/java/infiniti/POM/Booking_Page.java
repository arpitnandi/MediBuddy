package infiniti.POM;

import java.util.*;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Booking_Page 
{
	WebDriver Driver ;
	JavascriptExecutor Executor ;
	WebDriverWait Wait ;
	
	public Booking_Page( WebDriver D, Actions A, JavascriptExecutor E, WebDriverWait W ) 
	{
		PageFactory.initElements( D, this);
		Driver = D ;
		Executor = E ;
		Wait = W ;
	}
	
	//Headings
	@FindBy( xpath = "//*[@*='panel panel-primary']/*[contains(@class,'panel-collapse')]" )
	private List<WebElement> Heading_Panels;
	
	//Patient Details section
	@FindBy( id = "single-button" )
	private WebElement Select_Beneficiary;
	
	@FindBy( xpath = "//*[@id='single-button']/following-sibling::ul/li[*]/a" )
	private List<WebElement> Beneficiary_Relations;
	
	@FindBy( xpath = "//*[@id='single-button']/following-sibling::ul/li[*]/a/span" )
	private List<WebElement> Beneficiary_Names;
	
	@FindBy( className = "customerName" )
	private WebElement Customer_Name;
	
	@FindBy( name = "patientName" )
	private WebElement Name_Field;
	
	@FindBy( className ="info" )
	private List<WebElement> Error_Symbols;

	@FindBy( xpath = "//*[contains(@class='danger')]" )
	private List<WebElement> Error_Messages;
	
	@FindBy( id = "optionsRadios1" )
	private WebElement Male_Radio_Btn;
	
	@FindBy( id = "optionsRadios2" )
	private WebElement Female_Radio_Btn;
	
	@FindBy( xpath = "//div[@class='row']/div[1]/select" )
	private WebElement Select_Year;

	@FindBy( xpath = "//div[@class='row']/div[2]/select" )
	private WebElement Select_Month;
	
	@FindBy( xpath = "//div[@class='row']/div[3]/select" )
	private WebElement Select_Day;
	
	@FindBy( className = "input-group-addon" )
	private WebElement Countory_Code;
	
	@FindBy( name = "phoneNumber" )
	private WebElement Phone_Number_Field;

	@FindBy( name = "email" )
	private WebElement E_Mail_Field;
	
	@FindBy( className = "address-card add" )
	private WebElement Add_Address_Btn;
	
	@FindBy( xpath = "//*[text()='Manage address book']" )
	private WebElement Manage_Address;
	
	@FindBy( xpath = "//*[contains(@class,'address-card')]" )
	private List<WebElement> Address_Cards;

	@FindBy( xpath = "//*[contains(@class,'address-card')]/" )
	private List<WebElement> Address_Card_Ticks;
	
	@FindBy( xpath = "//*[text()='Continue booking']" )
	private WebElement Continue_Booking_Btn;
	
	@FindBy( xpath = "//*[text()='Apply Coupon']" )
	private WebElement Apply_Coupon_Code ;
	
	@FindBy( xpath = "//*[@*='txtCouponCode']" )
	private WebElement Type_Coupon_Code ;
	
	@FindBy( xpath = "//*[text()='Available coupons']/following-sibling::*/div/a" )
	private WebElement Apply_Code_Btn ;
	
	@FindBy( xpath = "//*[text()='Available coupons']/following-sibling::*/div/div" )
	private List<WebElement> Available_Coupons ;

	@FindBy( xpath = "//*[text()='Available coupons']/following-sibling::*/h5" )
	private List<WebElement> Available_Coupon_Discounts ;

	@FindBy( xpath = "//*[text()='Available coupons']/following-sibling::*/h5/small" )
	private List<WebElement> Available_Coupon_Details ;
	
	@FindBy( xpath = "//*[contains(@*,'validateCoupon')]" )
	private List<WebElement> Apply_Available_Coupon_Btn ;
	
	
	
	//flag
	boolean proceed ;
	
	//Actions
	public void fill_patient_Details( String Name, String Gender, String DOB, String MOB, String Email ) 
	{
		Select S ;
		//Fills Patient Name
		try {
			Wait.until( ExpectedConditions.invisibilityOf( Driver.findElement( By.className( "form-loading" ) ) ) );
			if( this.Select_Beneficiary.isDisplayed() )
				this.select_Benefitiary( "Others" );
		}
		catch( Exception E )
		{}
		if( ! this.Name_Field.getAttribute( "placeholder" ).equalsIgnoreCase( Name ) )
		{
			this.Name_Field.clear();
			this.Name_Field.sendKeys( Name );
		}
		
		//Selects Patient Gender
		if( Gender.equalsIgnoreCase( "Male" ) )
			this.Male_Radio_Btn.click();
		else if( Gender.equalsIgnoreCase( "Female" ) )
			this.Female_Radio_Btn.click();
		
		String[] Date = DOB.split( "_" );
		
		//Selects Patient's Year of Birth
		S = new Select( this.Select_Year );
		S.selectByVisibleText( Date[2] );

		//Selects Patient's Month of Birth
		S = new Select( this.Select_Month );
		S.selectByVisibleText( Date[1]);

		//Selects Patient's Day of Birth
		S = new Select( this.Select_Day );
		S.selectByVisibleText( Date[0] );
		
		//Fills Patient's Contact Number
		this.Phone_Number_Field.clear();
		this.Phone_Number_Field.sendKeys( MOB );
		
		//Fills Patient's Email ID
		this.E_Mail_Field.clear();
		this.E_Mail_Field.sendKeys( Email );
	}
	
	public boolean select_Benefitiary( String Match ) 
	{
		boolean Available = false ;
		
		try {
			if( this.Select_Beneficiary.isDisplayed() )
			{
				this.Select_Beneficiary.click();
				for( int i = 0 ; i < this.Beneficiary_Names.size() ; i++ )
				{
					if( this.Beneficiary_Names.get( i ).getText().contains( Match ) )
					{
						this.Beneficiary_Names.get( i ).click();
						Available = true ;
						break;
					}
				}
				for( int i = 0 ; i < this.Beneficiary_Relations.size() ; i++ )
				{
					if( this.Beneficiary_Relations.get( i ).getText().contains( Match ) )
					{
						this.Beneficiary_Relations.get( i ).click();
						Available = true ;
						break;
					}
				}
			}
		}
		catch( Exception Error )
		{}
		
		return Available ;
	}
	
	public boolean verify_Filled_Info( Map<String, String> Info, String Beneficiary ) 
	{
		boolean Filled_Details ;
		Select S ;
		
		//Checks all field values for "Others" as beneficiary selected
		if( Beneficiary.equalsIgnoreCase( "Others" ) )
		{
			Filled_Details = this.Customer_Name.getText().equals( null ) ;
			
			Filled_Details = this.Name_Field.getAttribute( "placeholder" ).equals( null );

			Filled_Details = this.Male_Radio_Btn.getClass().toString().contains( "ng-empty" );
			
			Filled_Details = this.Female_Radio_Btn.getClass().toString().contains( "ng-empty" );
			
			S = new Select( this.Select_Year );
			Filled_Details = S.getFirstSelectedOption().getText().equalsIgnoreCase( "Year" );
			S = new Select( this.Select_Month );
			Filled_Details = S.getFirstSelectedOption().getText().equalsIgnoreCase( "Month" );
			S = new Select( this.Select_Day );
			Filled_Details = S.getFirstSelectedOption().getText().equalsIgnoreCase( "Date" );

			Filled_Details = this.Phone_Number_Field.getClass().toString().contains( "ng-not-empty" ) && this.Phone_Number_Field.getClass().toString().contains( "ng-valid-pattern" ) && this.Countory_Code.equals( "91" );
			
			Filled_Details = this.E_Mail_Field.getClass().toString().contains( "ng-not-empty" ) && this.Phone_Number_Field.getClass().toString().contains( "ng-valid-pattern" );
		}
		
		//Checks that all field values are changing for the selected "Beneficiary" or for user without "Beneficiary" field
		else
		{
			Filled_Details = this.Customer_Name.getText().equals( Info.get( "Name" ) ) ;
					
			Filled_Details = this.Name_Field.getAttribute( "placeholder" ).equals( Info.get( "Name" ) );
					
			if( Info.get( "Gender" ).equalsIgnoreCase( "Male" ) )
				Filled_Details = this.Male_Radio_Btn.getClass().toString().contains( "ng-not-empty" ) && ! this.Female_Radio_Btn.getClass().toString().contains( "ng-empty" );
			else if( Info.get( "Gender" ).equalsIgnoreCase( "Female" ) )
				Filled_Details = this.Male_Radio_Btn.getClass().toString().contains( "ng-empty" ) && ! this.Female_Radio_Btn.getClass().toString().contains( "ng-not-empty" );
					
			S = new Select( this.Select_Year );
			Filled_Details = S.getFirstSelectedOption().getText().equalsIgnoreCase( Info.get( "Year" ) );		
			S = new Select( this.Select_Month );
			Filled_Details = S.getFirstSelectedOption().getText().equalsIgnoreCase( Info.get( "Month" ) );
			S = new Select( this.Select_Day );
			Filled_Details = S.getFirstSelectedOption().getText().equalsIgnoreCase( Info.get( "Day" ) );
					
			Filled_Details = this.Phone_Number_Field.getClass().toString().contains( "ng-empty" ) && this.Phone_Number_Field.getClass().toString().contains( "valid-pattern" );
					
			Filled_Details = this.E_Mail_Field.getClass().toString().contains( "ng-empty" ) && this.Phone_Number_Field.getClass().toString().contains( "valid-pattern" );
		}
		
		return Filled_Details ;
	}

	//Selects Address for home collection of Patient's test samples
	public void select_Address( String Address ) 
	{
		if( this.Address_Cards.size() > 0 )
		{
			for( int i = 0 ; true ; i++)
				if( this.Address_Cards.get( i ).getText().contains( Address ) )
				{
					Executor.executeScript( "arguments[0].scrollIntoView(true)" , this.Address_Cards.get( i ) );
					this.Address_Cards.get( i ).click();
					break;
				}
		}
	}
	
	//Add new address
	public void add_Address() 
	{
		if( this.Add_Address_Btn.isDisplayed() )
			this.Add_Address_Btn.click();
	}
	
	
	public boolean proceed_To_Pay( String Product ) 
	{
		boolean Filled_Mendatory, Continue_Booking = false ;
		
		Filled_Mendatory = ! this.Name_Field.getAttribute( "placeholder" ).isEmpty();
		
		Filled_Mendatory = ! this.Male_Radio_Btn.getClass().toString().contains( "ng-empty" );
		
		Filled_Mendatory = ! this.Female_Radio_Btn.getClass().toString().contains( "ng-empty" );
		
		Filled_Mendatory = ! this.Select_Year.getClass().toString().contains( "ng-empty" );
		
		Filled_Mendatory = ! this.Select_Month.getClass().toString().contains( "ng-empty" );
		
		Filled_Mendatory = ! this.Select_Day.getClass().toString().contains( "ng-empty" );
		
		Filled_Mendatory = ! this.Phone_Number_Field.getClass().toString().contains( "invalid-pattern" );
		
		Filled_Mendatory = ! this.E_Mail_Field.getClass().toString().contains( "invalid-pattern" );
		
		if( Product.equalsIgnoreCase( "Masters" ) || Product.equalsIgnoreCase( "Medicine" ) || Product.equalsIgnoreCase( "Preventive" ) )
		{
			for( int i = 0 ; i < this.Address_Cards.size() ; i++ )
			{
				Filled_Mendatory = this.Address_Cards.get( i ).getAttribute( "Class" ).toString().contains( "active" );
				if( Filled_Mendatory )
					break;
			}
		}
		
		if( Filled_Mendatory == true )
		{
			this.Continue_Booking_Btn.click();
			Continue_Booking = true ;
		}
		
		return Continue_Booking ;
	}
	
	public void apply_Prome_Code( String  Code, String WayOfSelection ) 
	{
		Wait.until( ExpectedConditions.attributeContains( this.Heading_Panels.get( 1 ), "aria-expanded", "true" ) );
		try {
			Executor.executeScript( "arguments[0].scrollIntoView(true)", this.Apply_Coupon_Code );
			this.Apply_Coupon_Code.click();
			
			if( WayOfSelection.equalsIgnoreCase( "Type" ) )
			{
				this.Type_Coupon_Code.sendKeys( Code );
				this.Apply_Code_Btn.click();
			}
			else if( WayOfSelection.equalsIgnoreCase( "Pick" ) )
			{
				for( int i = 0 ; i < this.Available_Coupons.size() ; i++ )
				{
					if( this.Available_Coupons.get( i ).getText().equalsIgnoreCase( Code ) )
						this.Apply_Available_Coupon_Btn.get( i ).click();
				}
			}
		}
		catch( Exception E )
		{
			E.printStackTrace();
		}
	}
	
	public boolean check_Field_Validations( Map<String, String> Info ) 
	{
		boolean Validation = false ;
		List<String> Values = new ArrayList<String>( Info.keySet() );
		for( String V : Values )
		{
			this.Name_Field.sendKeys( Info.get( V ) );
			for( int i = 0 ; i < Info.get(V).length() ; i++ )
			{
				if( Info.get( V ).charAt( i ) > 64 ||  Info.get( V ).charAt( i ) < 91 || Info.get( V ).charAt( i ) > 96 || Info.get( V ).charAt( i ) < 123 )
					Validation = this.Customer_Name.getText().equals( Info.get( V ));
				else
					Validation = this.Error_Symbols.get( 0 ).isDisplayed();
			}
		}
		return Validation ;
	}
}
