package upwork.regression;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import upwork.beans.UserDetailsBean;
import upwork.library.AppLibrary;
import upwork.library.TestBase;
import upwork.pages.LandingPage;
import upwork.pages.ProfilePage;

public class UpworkTestScript extends TestBase {

	public Logger logger;

	@BeforeClass
	public void setUp() throws IOException {
		appLibrary = new AppLibrary();
		System.out.println("TEST STARTS");
	}

	@Test
	public void UpworkSearchTest() throws Exception {
		driver = appLibrary.getDriverInstance();
		appLibrary.launchApp();

		// Fetch Keyword
		String keyword = loadUserProperties().getProperty("keyword");
		System.out.println("Fetching Keyword:" + keyword);

		// Search with keyword
		LandingPage landingPage = new LandingPage(appLibrary);
		System.out.println("Searching keyword:" + keyword);
		landingPage.search("Freelancers", keyword);

		// Fetch Search Results
		System.out.println("Fetching Search Results");
		List<UserDetailsBean> data = landingPage.fetchData(keyword);

		// Search data for keyword and echo data and results on console
		System.out.println("---------------------------------------------");
		for (UserDetailsBean udb : data) {
			keywordComparison(keyword, udb);
		}

		// Select random profile
		int rand = AppLibrary.randIntDigits(1, AppLibrary.findElements(driver, LandingPage.srItems).size());
		System.out.println("Random profile selected: " + rand);

		// Open user profile in side panel ("Click on title")
		System.out.println("Open user profile in side panel");
		ProfilePage profilepage = landingPage.openUserProfile(rand);

		// Fetch and Save data from user details side panel
		UserDetailsBean sourceUdb = data.get(rand - 1);
		System.out.println("Fetching Profile data from profile details side panel");
		UserDetailsBean destUdb = profilepage.fetchData();

		// Search data for keyword and echo data and results on console
		keywordComparison(keyword, destUdb);

		// Compare and display data
		System.out.println("Compare data");
		System.out.println("-----------------------------------------------------------");
		displayResults("Cipher Text", sourceUdb.getCipherText(), destUdb.getCipherText());
		displayResults("Profile Name", sourceUdb.getProfileName(), destUdb.getProfileName());
		displayResults("Title", sourceUdb.getTitle(), destUdb.getTitle());
		displayResults("Hourly Rate", sourceUdb.getHourlyRate(), destUdb.getHourlyRate());
		displayResults("Earnings", sourceUdb.getEarnings(), destUdb.getEarnings());
		displayResults("Job Success Rate", sourceUdb.getJobSuccessRate(), destUdb.getJobSuccessRate());
		displayResults("Country", sourceUdb.getCountry(), destUdb.getCountry());
		displayResults("Description", sourceUdb.getDescription(), destUdb.getDescription());
		displayResults("Skills", sourceUdb.getSkills(), destUdb.getSkills());
		displayResults("Tests", sourceUdb.getTests(), destUdb.getTests());
		displayResults("Portfolios", sourceUdb.getPortfolios(), destUdb.getPortfolios());

	}

	/* compare two strings as equals, A contains B, B contains A */
	public String compareStringData(String src, String dest) {

		if (src.equalsIgnoreCase(dest)) {
			return "exact match";
		} else if (src.contains(dest)) {
			return "source contains destination";
		} else if (dest.contains(src)) {
			return "destination contains source";
		} else {
			return "match failed";
		}
	}

	/* compare two strings case in-sensitive */
	public boolean getComparison(String src, String dest) {

		if (Pattern.compile(Pattern.quote(src), Pattern.CASE_INSENSITIVE).matcher(dest).find()) {
			return true;
		} else {
			return Pattern.compile(Pattern.quote(dest), Pattern.CASE_INSENSITIVE).matcher(src).find();

		}
	}

	/* Prints Label, Src Data, Dest Data and comparison Result */
	public void displayResults(String label, String source, String dest) {
		System.out.println(label);
		System.out.println("Search Results Data:" + source);
		System.out.println("Profile Details Panel Data:" + dest);
		System.out.println("Result:" + compareStringData(source, dest));
		System.out.println("-----------------------------------------------------------");

	}

	/* Prints parameter name, keyword comparison result */
	public void keywordComparison(String keyword, UserDetailsBean udb) {

		System.out.println("Cipher:" + udb.getCipherText() + "|" + getComparison(keyword, udb.getCipherText()));
		System.out.println("Profile Name:" + udb.getProfileName() + "|" + getComparison(keyword, udb.getProfileName()));
		System.out.println("Title:" + udb.getTitle() + "|" + getComparison(keyword, udb.getTitle()));
		System.out.println("Hourly Rate:" + udb.getHourlyRate().replace("\n", " ") + "|"
				+ getComparison(keyword, udb.getHourlyRate()));
		System.out.println("Earnings:" + udb.getEarnings() + "|" + getComparison(keyword, udb.getEarnings()));
		System.out.println(
				"Job Success Rate:" + udb.getJobSuccessRate() + "|" + getComparison(keyword, udb.getJobSuccessRate()));
		System.out.println("Country:" + udb.getCountry() + "|" + getComparison(keyword, udb.getCountry()));
		System.out.println("Description:" + udb.getDescription().replace("\n", " ") + "|"
				+ getComparison(keyword, udb.getDescription()));
		System.out.println("Skills:" + udb.getSkills() + "|" + getComparison(keyword, udb.getSkills()));
		System.out.println("Tests:" + udb.getTests() + "|" + getComparison(keyword, udb.getTests()));
		System.out.println("Portfolios:" + udb.getPortfolios() + "|" + getComparison(keyword, udb.getPortfolios()));
		System.out.println("---------------------------------------------");
	}

}
