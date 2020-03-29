package test.Scripts;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.DataTable;
import cucumber.api.java.en.*;
import infiniti.POM.*;
import test.Utilities.Framework_Base;

public class Infinity_HC_Booking extends Framework_Base
{
	//Session properties
	protected WebDriver Driver ;
	protected Actions Action ;
	protected WebDriverWait Wait ;
	protected FluentWait<WebDriver> Fluent_Wait ;
	protected JavascriptExecutor Executor;
	
	//Flags
	protected boolean RejectNotifications ;
	protected boolean Flag ;
	
	//POM class objects
	protected Common_For_All_Pages Common ;
	protected Infiniti_Home I_Home ;
	protected Health_Check_Details HC_Details ;
	protected Health_Check_Listing HC_Listing ;
	protected Booking_Page Booking ;
	
	
	//Step Definitions
	@Given("^User opens Browser and navigates to URL$")
	public void User_opens_Browser_and_navigates_to_URL( DataTable Table ) throws Throwable
	{
		List<List <String> > Data = Table.raw() ;
		String Browser = Data.get( 0 ).get( 0 ) ;
		String URL= Data.get( 0 ).get( 1 ) ;
		
		super.init_Driver( Browser );
		
		super.launch_Session( super.Driver, URL, 20, 5 );
	}

	@Then("^Login with (.*) and (.*)$")
	public void login_with_Valid_Username_and_Valid_Password( String UN, String PW ) throws Throwable
	{	
		Common = new Common_For_All_Pages( super.Driver, super.Action, super.Executor, super.Wait );

		//Common.click_Not_Now();
		
		Common.login( UN, PW );
	}

	@Then("^Click on (.*) Icon from Dashboard$")
	public void click_on_Product_Icon_from_Dashboard( String Product ) throws Throwable
	{
		I_Home = new Infiniti_Home( super.Driver, super.Action, super.Executor, super.Wait );

		I_Home.selectService( Product );
	}

	@Then("^Select (.*) and (.*)$")
	public void select_City_and_Category( String City, String Category ) throws Throwable
	{
		I_Home = new Infiniti_Home( super.Driver, super.Action, super.Executor, super.Wait );
		
		Common = new Common_For_All_Pages( super.Driver, super.Action, super.Executor, super.Wait );
		
		Common.selectCity( City );
		
		I_Home.selectCategory( Category );
	}

	@Then("^Click on View Package button of Package (\\d+)$")
	public void click_on_View_Package_button_of_Nth_Package( int N ) throws Throwable
	{
		HC_Listing = new Health_Check_Listing( super.Driver, super.Action, super.Executor, super.Wait );
		
		HC_Listing.view_Package( N );
	}

	@Then("^Click on Book Now button of Package$")
	public void click_on_Book_Now_button_of_Package() throws Throwable
	{
		HC_Details = new Health_Check_Details( super.Driver, super.Action, super.Executor, super.Wait );
		
		HC_Details.book_Now();
	}

	@Then("^Select Appointment on (\\d+) days from today at (.*)$")
	public void select_Appointment_on_N_days_from_today_at_Time( int Days, String Time_Slot ) throws Throwable
	{
		HC_Details = new Health_Check_Details( super.Driver, super.Action, super.Executor, super.Wait );
		
		Flag = HC_Details.select_Appointment( Days, Time_Slot );
		
		if( Flag )
			HC_Details.click_Continue();
	}

	@Then("^Fill Patient's (.*), (.*), (.*), (.*) and (.*)$")
	public void fill_Patient_s_Name_Gender_DOB_and_MOB( String Name, String Gender, String DOB, String MOB, String Email ) throws Throwable
	{
		Booking = new Booking_Page( super.Driver, super.Action, super.Executor, super.Wait );
		
		Booking.fill_patient_Details(Name, Gender, DOB, MOB, Email);
	}

	@Then("^Select (.*) for test sample pickup$")
	public void select_Address_for_test_sample_pickup( String Address )
	{
		Booking = new Booking_Page( super.Driver, super.Action, super.Executor, super.Wait );
		
		Booking.select_Address( Address );
	}
	
	@Then("^Click on Continue Booking button for (.*) Order$")
	public void click_on_Continue_Booking_button_for_Product_Order( String Product ) throws Throwable
	{
		Booking = new Booking_Page( super.Driver, super.Action, super.Executor, super.Wait );
		
		Flag = Booking.proceed_To_Pay( Product );
	}
	
	@Then("^Apply required (.*)$")
	public void apply_required_Benefit( String PromoCode ) throws Throwable
	{
		Booking = new Booking_Page( super.Driver, super.Action, super.Executor, super.Wait );
		
		if( Flag )
		{
			Booking.apply_Prome_Code( PromoCode , "Type" );
			
			//Booking.apply_Prome_Code( PromoCode, "Pick" );
		}
	}
	
	@Then("^Click on Confirm button and Make Payment$")
	public void click_on_Confirm__button_and_Make_Payment() throws Throwable
	{
		
	}
	
	@Then("^User Verify the Order in Track Orders$")
	public void user_Verify_the_Order_in_Track_Orders() throws Throwable 
	{
		
	}

	@Then("^User signout from application$")
	public void user_signout_from_application() throws Throwable
	{
		super.Driver.close();
	}
}
