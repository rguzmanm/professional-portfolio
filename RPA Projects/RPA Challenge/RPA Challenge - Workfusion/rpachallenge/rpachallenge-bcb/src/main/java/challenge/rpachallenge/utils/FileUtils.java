package challenge.rpachallenge.utils;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

import javax.imageio.ImageIO;

import org.slf4j.Logger;


public class FileUtils {
	private final Logger logger;

	public FileUtils(Logger logger) {
		this.logger = logger;
	}

	public String takeScreenshot() {
		
		String resultsFolder = getDesktopPath() + "\\RPA Challenge Results";
		new File(resultsFolder).mkdirs(); //Create the results folder if it doesn't exist
		
		String screenshotPath = resultsFolder + "\\Result Screenshot " + getCurrentTimestamp("dd-MMM-yy hhmm") + ".png";
		
		try {
			File screenshotFile = new File(screenshotPath);

			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			Rectangle screenRectangle = new Rectangle(0, 0, screenSize.width, screenSize.height);
			BufferedImage screenFullImage = new Robot().createScreenCapture(screenRectangle);
			ImageIO.write(screenFullImage, "png", screenshotFile);

			logger.info("Results screenshot was taken succesfully " + screenshotPath);

		} catch (AWTException | IOException e) {
			logger.error("An exception ocurred while taking screenshot");
			e.printStackTrace();
		}
		return screenshotPath;
	}

	public static String getCurrentTimestamp(String format) {
		DateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(new Date());
	}
	
	/**
	 * Wait until a file exists or the timeout is met.
	 * @param filePath The file path to be monitored
	 * @param timeout Max number of seconds to wait for the file to exist
	 * @param pollInterval Milliseconds between each file-existence verification
	 */
	public static boolean waitFileExists(String filePath, long timeout, long pollInterval) {
		
		File downloadedFile = new File(filePath);
		Instant start = Instant.now();
		
		while (!downloadedFile.exists()) {
			try {
				Thread.sleep(pollInterval);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			Duration timeElapsed = Duration.between(start, Instant.now());
			if (timeElapsed.toMillis()*1000 >= timeout) break;			
		}
		
		return downloadedFile.exists();
	}

	public static boolean deleteFile(String filePath) {
		File inputFile = new File(filePath);
		return inputFile.delete();
	}
	
 	private String getDesktopPath() {
		Map<String, String> envVars = System.getenv();
		return (envVars.containsKey("OneDrive") ? envVars.get("OneDrive") : System.getProperty("user.home")) + "\\Desktop";
	}
}

