package challenge.rpachallenge.rpa;

import com.workfusion.rpa.helpers.Window;
import com.workfusion.rpa.helpers.RPA;

import java.util.Map;
import java.util.Set;

import org.openqa.selenium.Keys;
import org.openqa.selenium.By;
import org.slf4j.Logger;

import groovy.lang.Closure;

import challenge.rpachallenge.config.RPAChallengeConfig;
import challenge.rpachallenge.config.SelectorsConfig;
import challenge.rpachallenge.rpa.RobotDriverWrapper;
import challenge.rpachallenge.utils.FileUtils;;

public class RPAChallenge extends RobotDriverWrapper {

	public RPAChallenge(Logger logger) {
		super(logger);
	}

	/**
	 * Open the Chrome browser and navigate to the RPA Challenge site
	 * 
	 * @param configValues
	 */
	@SuppressWarnings({ "rawtypes", "serial", "unused" })
	public void openRPAChallengePage(RPAChallengeConfig configValues) {

		RPA.inChrome(new Closure(null) {
			public void doCall() {
				RPA.open(configValues.getProperty("RPACHALLENGE_URL"));
			}
		});

		logger.info("RPA Challenge page was open");
	}

	/**
	 * <pre>
	 * Downloads the Excel file provided by the RPA Challenge site and stores it in
	 * the local downloads folder.<\pre>
	 * 
	 * Pre-condition: RPA Challenge site must have been previously open
	 * 
	 * @param selectorValues Xpath selectors of the RPA Challenge page elements
	 */
	@SuppressWarnings({ "rawtypes", "serial", "unused" })
	public String downloadInputFile(SelectorsConfig selectorValues) {
		
		String downloadedFilePath = System.getProperty("user.home") + "\\Downloads\\RPAChallenge " + FileUtils.getCurrentTimestamp("dd-MMM-yy hhmm") + ".xlsx";

		RPA.inChrome(new Closure(null) {
			public void doCall() {
				RPA.$(By.xpath(selectorValues.getProperty("DOWNLOAD_FILE").replace("\"", ""))).click();
			}
		});

		RPA.inDesktop(new Closure(null) {
			public void doCall() {
				int findWindowRetryCounter = 0;
				int findWindowMaxRetries = 10;
				boolean stopCondition = false;

				do {
					Set<String> windowHandles = Window.windowHandles(selectorValues.getProperty("SAVE_AS_WINDOW"));
					stopCondition = windowHandles.size() > 0 | (findWindowRetryCounter >= findWindowMaxRetries);
					findWindowRetryCounter++;
					RPA.sleep(1000);
				} while (!stopCondition);

				RPA.switchToExistingWindow(selectorValues.getProperty("SAVE_AS_WINDOW"), 5000L);
				RPA.$(selectorValues.getProperty("FILENAME_BAR")).sendKeys(downloadedFilePath + Keys.ENTER);
			}
		});

		logger.info("Waiting download to complete");

		boolean downloadSuccessful = FileUtils.waitFileExists(downloadedFilePath, 90, 5000L);
		
		if(downloadSuccessful)
			logger.info("Input file was downloaded correctly");
		else
			logger.info("Input file was downloaded correctly");

		return downloadedFilePath;
	}

	/**
	 * Clicks the start button in the RPA Challenge site
	 * 
	 * @param selectorValues Xpath selectors of the RPA Challenge page elements
	 */
	@SuppressWarnings({ "rawtypes", "serial", "unused" })
	public void startRPAChallenge(SelectorsConfig selectorValues) {

		RPA.inChrome(new Closure(null) {
			public void doCall() {
				RPA.$(By.xpath(selectorValues.getProperty("START_BUTTON"))).click();
			}
		});

		logger.info("RPA Challenge was started");
	}

	/**
	 * Fill the information of one employee in the RPA Challenge form.
	 * 
	 * @param employeeData Map of data which will be put in the form
	 * @param selectorValues Xpath selectors of the RPA Challenge page elements 
	 */
	@SuppressWarnings({ "rawtypes", "serial", "unused" })
	public void fillForm(Map<String, String> employeeData, SelectorsConfig selectorValues) {

		RPA.inChrome(new Closure(null) {
			public void doCall() {

				RPA.$(By.xpath(selectorValues.getProperty("FIRST_NAME_INPUT"))).sendKeys(employeeData.get("First Name"));
				RPA.$(By.xpath(selectorValues.getProperty("LAST_NAME_INPUT"))).sendKeys(employeeData.get("Last Name"));
				RPA.$(By.xpath(selectorValues.getProperty("COMPANY_INPUT"))).sendKeys(employeeData.get("Company Name"));
				RPA.$(By.xpath(selectorValues.getProperty("ROLE_INPUT"))).sendKeys(employeeData.get("Role in Company"));
				RPA.$(By.xpath(selectorValues.getProperty("ADDRESS_INPUT"))).sendKeys(employeeData.get("Address"));
				RPA.$(By.xpath(selectorValues.getProperty("EMAIL_INPUT"))).sendKeys(employeeData.get("Email"));
				RPA.$(By.xpath(selectorValues.getProperty("PHONE_INPUT"))).sendKeys(employeeData.get("Phone Number"));

				RPA.$(By.xpath(selectorValues.getProperty("SUBMIT_BUTTON"))).click();

				logger.info("Form was populated for employee: " + employeeData.get("First Name") + " " + employeeData.get("Last Name"));
			}
		});
	}


}