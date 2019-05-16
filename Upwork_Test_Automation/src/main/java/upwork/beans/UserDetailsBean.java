package upwork.beans;

public class UserDetailsBean {

	public String cipherText;
	public String profileName;
	public String title;
	public String hourlyRate;
	public String earnings; // may be private
	public String jobSuccessRate; // may be private
	public String country;
	public String description;
	public String skills;
	public String tests;
	public String portfolios;

	public void setUserDetailsBean(String cipherTextVal, String profileNameVal, String titleVal, String hourlyRateVal,
			String earningsVal, String jobSuccessRateVal, String countryVal, String descriptionVal, String skillsVal,
			String testsVal, String portfolioVal) {

		setCipherText(cipherTextVal);
		setProfileName(profileNameVal);
		setTitle(titleVal);
		setHourlyRate(hourlyRateVal);
		setEarnings(earningsVal);
		setJobSuccessRate(jobSuccessRateVal);
		setCountry(countryVal);
		setDescription(descriptionVal);
		setSkills(skillsVal);
		setTests(testsVal);
		setPortfolios(portfolioVal);
	}

	public String getCipherText() {
		return cipherText;
	}

	public void setCipherText(String cipherText) {
		this.cipherText = cipherText;
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getHourlyRate() {
		return hourlyRate;
	}

	public void setHourlyRate(String hourlyRate) {
		this.hourlyRate = hourlyRate;
	}

	public String getEarnings() {
		return earnings;
	}

	public void setEarnings(String earnings) {
		this.earnings = earnings;
	}

	public String getJobSuccessRate() {
		return jobSuccessRate;
	}

	public void setJobSuccessRate(String jobSuccessRate) {
		this.jobSuccessRate = jobSuccessRate;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String tileDescription) {
		this.description = tileDescription;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public String getTests() {
		return tests;
	}

	public void setTests(String tileTests) {
		this.tests = tileTests;
	}

	public String getPortfolios() {
		return portfolios;
	}

	public void setPortfolios(String tilePortfolios) {
		this.portfolios = tilePortfolios;
	}

}