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
import org.openqa.selenium.support.ui.WebDriverWait;

public class User_Profile 
{
	private WebDriver Driver ;
	private WebDriverWait Wait ;
	
	public User_Profile( WebDriver D, Actions A, JavascriptExecutor E, WebDriverWait W ) 
	{
		PageFactory.initElements( D, this );
		Driver = D;
		Wait = W;
	}
	
	//User Info
	@FindBy( className = "block" )
	private WebElement User_Block;
	
	@FindBy( xpath = "//*[text()='Full name']/../p" )
	private WebElement Full_Name;
	
	
	//Fatch User Info
	public Map<String, String> get_Beneficiary_Info( String B )
	{
		Map<String, String> Info = new HashMap<String, String>();
		
		Wait.until( ExpectedConditions.invisibilityOfAllElements( Driver.findElement(By.xpath("//*[@*='form-loading']") ) ) );
		this.User_Block.click();
		
		Info.put( "Name" , this.Full_Name.getText() );
		
		return Info ;
	}

	public Map<String, String> get_Beneficiary_Info() 
	{
		Map<String, String> R = new HashMap<String, String>();
		
		R.put( "Name", this.Full_Name.getText() );
		
		return R;
	}
}
