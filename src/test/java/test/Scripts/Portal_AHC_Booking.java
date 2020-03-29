package test.Scripts;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;

import cucumber.api.DataTable;
import cucumber.api.java.en.*
;
import test.Utilities.Framework_Base;
import corporate.POM.*;

public class Portal_AHC_Booking extends Framework_Base
{
	protected List<Object> Obj;
	protected boolean flag ;
	protected WebDriver Driver ;
	protected Actions Action ;
	protected WebDriverWait Wait ;
	protected FluentWait<WebDriver> Fluent_Wait ;
	protected JavascriptExecutor Executor ;
	
	
	//Portal POM Class Objects
	protected Corporte_Portal_Home Portal_Home;
	protected UpSell_AHC_Listing Listing ;
	protected UpSell_AHC_Description Description ;
	protected UpSell_AHC_Checkout Checkout ;
	
	
	//Test Scripts
	@Given("^User opens Browser and navigates to MediBuddy Portal$")
	public void User_opens_Browser_and_navigates_to_URL( DataTable Table ) throws Throwable
	{	
		List<List<String>> Data = Table.raw() ;
		
		String Browser = Data.get( 0 ).get( 0 ) ;
		String URL = Data.get( 0 ).get( 1 ) ;
		
		super.init_Driver( Browser );
		
		super.launch_Session( super.Driver, URL, 30, 10);
	}

	@Then("^User login with (.*) and (.*) in Portal$")
	public void User_login_with_Username_and_Password_in_Portal( String UN, String PW ) 
	{
		Portal_Home = new Corporte_Portal_Home( super.Driver, super.Action, super.Executor, super.Wait );
		
		Portal_Home.User_Name.sendKeys( UN );
		Portal_Home.Password.sendKeys( PW );
		Portal_Home.Sign_In_Btn.click();
	}
	
	@Then("^Redirecting to revaped AHC flow for (.*)$")
	public void Redirecting_to_revaped_AHC_flow_for_Corporate( String Corporate )
	{
		Portal_Home = new Corporte_Portal_Home( super.Driver, super.Action, super.Executor, super.Wait );
		
		Portal_Home.redirection( Corporate , "" );
	}
	
	@Then("^Select (.*) in Corporate portal$")
	public void Select_City_in_Corporate_portal( String City )
	{
		Listing = new UpSell_AHC_Listing( super.Driver, super.Action, super.Executor, super.Wait );
		
		Listing.select_City( City );
	}
	
	@Then("^Click on View Package for (.*)$")
	public void Click_on_View_Package_for_AHC( String AHC )
	{
		Listing = new UpSell_AHC_Listing( super.Driver, super.Action, super.Executor, super.Wait );
		//Listing.View_Package_Btns.get( 0 ).click();
		Listing.view_Package( AHC );
	}
	
	@Then("^Click on Book Appointment for the selected (.*)$")
	public void Click_on_Book_Now_for_the_selected_AHC( String AHC )
	{
		Description = new UpSell_AHC_Description( super.Driver, super.Action, super.Executor, super.Wait );

		while( true )
		{
			try {
				super.Action.moveToElement( Description.Book_Appointment_Btn ).build().perform();
				Description.Book_Appointment_Btn.click();
				break;
			}
			catch( Exception E )
			{
				super.Driver.navigate().refresh();
			}
		}
	}
	
	String Date, Required_Slot, Provider ;
	
	@Then("^Chose Appointment for (.*) on (\\d+) days from today at (.*)$")  
	public void Chose_Appointment_for_Provider_on_N_days_from_today_at_Time(  String Provider, int Days, String Required_Slot )
	{
		this.Required_Slot = Required_Slot ;
		this.Provider = Provider ;
	
		Checkout = new UpSell_AHC_Checkout( super.Driver, super.Action, super.Executor, super.Wait );
		
		//Assert.assertFalse( Checkout.Continue_Btn.isEnabled() );
		
		this.Date = Checkout.select_Date( Days ) ;
		
		this.Provider = Checkout.select_Timeslot( Provider, Required_Slot );
		
		//Assert.assertTrue( Checkout.Continue_Btn.isEnabled() );
	}
	
	@Then("^Verify the selected Appointment details$")
	public void Verify_the_selected_Appointment_details()
	{
		Checkout = new UpSell_AHC_Checkout( super.Driver, super.Action, super.Executor, super.Wait );
		
		Assert.assertTrue( Checkout.Selected_Provider.getText().contains( this.Provider ) );
		
		Assert.assertTrue( Checkout.Selected_Slot.getText().equals( this.Date + " at " + this.Required_Slot ) );
		
		flag = Checkout.Selected_Provider.getText().contains( this.Provider ) && Checkout.Selected_Slot.getText().contains( " at " + this.Required_Slot );
		
		if( flag )
		{
			super.Action.moveToElement( Checkout.Continue_Btn ).build().perform();
			Checkout.Continue_Btn.click();
		}
	}
	
	@Then("^Add required members$")
	public void Add_required_members( DataTable Members )
	{
		Checkout = new UpSell_AHC_Checkout( super.Driver, super.Action, super.Executor, super.Wait );
		
		Checkout.Continue_Btn.click();
	}
	
	@Then("^Verify Contact number is (.*) then Click on Confirm Booking$")
	public void Verify_Contact_number_is_MOB_then_Click_on_Confirm_Booking( String MOB )
	{
		Checkout = new UpSell_AHC_Checkout( super.Driver, super.Action, super.Executor, super.Wait );
		
		Checkout.verify_Mobile( MOB ) ;
	}
	
	@Then("^Verify the booked AHC details are same as given$")
	public void Verify_the_booked_AHC_details_are_same_as_given()
	{
		
	}
	
	@Then("^User Sign out and proceed to Portal login page$")
	public void User_Sign_out_and_proceed_to_Portal_login_page() throws Exception
	{
		Thread.sleep( 2000 );
		super.Driver.close();
	}
}