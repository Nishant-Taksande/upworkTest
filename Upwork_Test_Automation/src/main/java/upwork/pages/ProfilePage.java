package upwork.pages;

import org.openqa.selenium.WebDriver;

import upwork.beans.UserDetailsBean;
import upwork.library.AppLibrary;

public class ProfilePage {

	private AppLibrary appLibrary;
	private WebDriver driver;

	// Profile Details
	public String parent = "xpath://div[@id='oProfilePage']";
	public static String profileName = "//span[@itemprop='name']"; // data-freelancer-ciphertext
	public String title = "//span[contains(@class,'fe-job-title')]";
	public String hourlyRate = "//span[@itemprop='pricerange']";
	public String earnings = "//li[div[contains(text(),'earned')]]";
	public String jobSuccessRate = "//div[cfe-job-success]";
	public String country = "//span[@itemprop='country-name']";
	public String description = "//p[@itemprop='description']";
	public String skills = "//div[contains(@class,'o-profile-skills')]";
	public String skillsAdditional = "//cfe-profile-skills";
	public String tests = "//o-profile-tests//table//tr";
	public String portfolios = "//o-profile-portfolio//up-project-thumb[@project='portfolio']";

	public ProfilePage(AppLibrary appLibrary) {
		super();
		this.appLibrary = appLibrary;
		this.driver = appLibrary.getCurrentDriverInstance();
	}

	/*
	 * This method scrapes profile data from the side panel, encapsulates them in a
	 * bean and returns the bean
	 */
	public UserDetailsBean fetchData() {

		AppLibrary.sleep(5000);
		LandingPage lp = new LandingPage(appLibrary);
		String profileNameVal = lp.getProfileParameterData(parent + profileName, "");
		String titleVal = lp.getProfileParameterData(parent + title, "");
		String hourlyRateVal = lp.getProfileParameterData(parent + hourlyRate, "");
		String earningsVal = lp.getProfileParameterData(parent + earnings, "");
		String jobSuccessRateVal = lp.getProfileParameterData(parent + jobSuccessRate, "");
		String countryVal = lp.getProfileParameterData(parent + country, "");
		String descriptionVal = lp.getProfileParameterData(parent + description, "");

		// Skills locator changes on profile template
		String skillsVal = new String("No Data");
		if (AppLibrary.verifyElement(driver, parent + skills, 5)) {
			skillsVal = lp.getProfileParameterData(parent + skills, "");
		} else if (AppLibrary.verifyElement(driver, parent + skillsAdditional, 5)) {
			skillsVal = lp.getProfileParameterData(parent + skillsAdditional, "");
		}

		String testsVal = lp.getProfileParameterData(parent + tests, "");
		String portfolioVal = lp.getProfileParameterData(parent + portfolios, "");
		String cipherTextVal = driver.getCurrentUrl();

		UserDetailsBean udb = new UserDetailsBean();

		udb.setUserDetailsBean(cipherTextVal, profileNameVal, titleVal, hourlyRateVal, earningsVal, jobSuccessRateVal,
				countryVal, descriptionVal, skillsVal, testsVal, portfolioVal);

		return udb;

	}

}
