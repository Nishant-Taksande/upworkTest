package upwork.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import upwork.beans.UserDetailsBean;
import upwork.library.AppLibrary;

public class LandingPage {

	private AppLibrary appLibrary;
	private WebDriver driver;

	// Search Widget
	public String searchParent = "xpath://div[contains(@class,'navbar-fixed-subnav')]//div[@class='navbar-form']";
	public String searchDropdownButton = searchParent + "//button[@aria-expanded='false']";
	public String searchDropdownItem = searchParent + "//ul[@id='search-dropdown']//li[@data-label='REPLACE']";
	public String searchInput = searchParent + "//input[@type='search'][@name='q']";
	public String searchButton = searchParent + "//button[@type='submit']";

	// Search Results
	public String searchResults = "id:oContractorResults";
	public static String srItems = "xpath://section[@id='oContractorResults']//section[contains(@class,'air-card-hover')]";

	// Profile Details
	public static String profileName = "//h4//a[@class='freelancer-tile-name']"; // data-freelancer-ciphertext
	public String title = "//h4[@data-qa='tile_title']";
	public String hourlyRate = "//div[@data-freelancer-rate]";
	public String earnings = "//div[@data-freelancer-earnings]";
	public String jobSuccessRate = "//div[@data-freelancer-job-success-score]//div[contains(@class,'progress-bar-complimentary')]";
	public String country = "//div[@data-freelancer-location]";
	public String description = "//div[@data-freelancer-description][@class='d-lg-none']";
	public String skills = "//div[@class='skills-section']";
	public String tests = "//small[@data-qa='tile_tests']//a";
	public String portfolios = "//small[@data-qa='tile_portfolios']//a";

	public LandingPage(AppLibrary appLibrary) {
		super();
		this.appLibrary = appLibrary;
		this.driver = appLibrary.getCurrentDriverInstance();
	}

	/*
	 * This method, selects type from the dropdown then enters keyword and triggers
	 * search
	 */
	public LandingPage search(String type, String keyword) {

		AppLibrary.findElement(driver, searchDropdownButton).click();
		AppLibrary.findElement(driver, searchDropdownItem.replace("REPLACE", type)).click();
		AppLibrary.enterText(driver, searchInput, keyword);
		AppLibrary.findElement(driver, searchButton).click();
		return new LandingPage(appLibrary);
	}

	/*
	 * This method scrapes all displayed profiles data, encapsulates them in a bean,
	 * stores the bean object in a array list; finally returns the array list
	 */
	public List<UserDetailsBean> fetchData(String keyword) {

		AppLibrary.findElement(driver, searchResults);
		AppLibrary.sleep(10000);
		int dataCount = AppLibrary.findElements(driver, srItems).size();
		List<UserDetailsBean> allProfiles = new ArrayList<UserDetailsBean>();

		String cipherTextVal = new String();
		String profileNameVal = new String();
		String titleVal = new String();
		String hourlyRateVal = new String();
		String earningsVal = new String(); // may be private
		String jobSuccessRateVal = new String(); // may be private
		String countryVal = new String();
		String descriptionVal = new String();
		String skillsVal = new String();
		String testsVal = new String();
		String portfolioVal = new String();
		String parent = new String();

		for (int i = 1; i <= dataCount; i++) {

			UserDetailsBean udb = new UserDetailsBean();
			parent = srItems + "[" + i + "]";

			// Cipher Text
			cipherTextVal = getProfileParameterData(parent + profileName, "data-freelancer-ciphertext");
			profileNameVal = getProfileParameterData(parent + profileName, "");
			titleVal = getProfileParameterData(parent + title, "");
			hourlyRateVal = getProfileParameterData(parent + hourlyRate, "");
			earningsVal = getProfileParameterData(parent + earnings, "");
			jobSuccessRateVal = getProfileParameterData(parent + jobSuccessRate, "");
			countryVal = getProfileParameterData(parent + country, "");
			descriptionVal = getProfileParameterData(parent + description, "data-profile-description");
			skillsVal = getProfileParameterData(parent + skills, "");
			testsVal = getProfileParameterData(parent + tests, "");
			portfolioVal = getProfileParameterData(parent + portfolios, "");

			udb.setUserDetailsBean(cipherTextVal, profileNameVal, titleVal, hourlyRateVal, earningsVal,
					jobSuccessRateVal, countryVal, descriptionVal, skillsVal, testsVal, portfolioVal);

			allProfiles.add(udb);
		}

		return allProfiles;
	}

	/* This is a generic method to fetch text/attribute data of the element */
	public String getProfileParameterData(String locator, String parameterName) {

		if (AppLibrary.verifyElement(driver, locator, 5)) {

			WebElement element = AppLibrary.findElement(driver, locator);

			if (parameterName.equalsIgnoreCase("")) {
				return element.getText().trim();
			} else {
				return element.getAttribute(parameterName).trim();
			}
		}
		return "Not Displayed";
	}

	/* This method clicks on the profile title specified by the sequence id */
	public ProfilePage openUserProfile(int id) {

		AppLibrary.findElement(driver, srItems + "[" + id + "]" + title).click();

		AppLibrary.sleep(5000);

		return new ProfilePage(appLibrary);
	}

}
