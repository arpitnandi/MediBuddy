package infiniti.POM;

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

public class Health_Check_Listing extends Framework_Base
{
	JavascriptExecutor Executor ;
	WebDriverWait Wait ;
	
	public Health_Check_Listing ( WebDriver D, Actions A, JavascriptExecutor E, WebDriverWait W )
	{
		PageFactory.initElements( D, this );
		Wait = W ;
		Executor = E ;
	}
	
	//HC_Cards
	@FindBy( xpath ="//button[text()='End tour']")
	private WebElement End_tour;

	@FindBy( className = "product-name")
	private List<WebElement> Package_Names;
	
	@FindBy( xpath = "//*[contains(text(),'View Package')]")
	private List<WebElement> View_Package_Btn;
	
	//Actions
	public void view_Package( int n)
	{
		this.end_Tour();

		Wait.until( ExpectedConditions.visibilityOf( this.View_Package_Btn.get( n-1 ) ) );
		Executor.executeScript( "arguments[0].scrollIntoView(true)", this.View_Package_Btn.get( n-1 ) );
		
		Wait.until( ExpectedConditions.elementToBeClickable( this.View_Package_Btn.get( n-1 ) ) );
		this.View_Package_Btn.get(n-1).click();
	}
	
	public void end_Tour() 
	{
		if( this.End_tour.isDisplayed() )
			this.End_tour.click();
	}
}
