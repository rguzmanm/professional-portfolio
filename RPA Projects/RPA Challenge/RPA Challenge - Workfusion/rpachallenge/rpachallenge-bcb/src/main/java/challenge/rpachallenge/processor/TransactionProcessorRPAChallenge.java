package challenge.rpachallenge.processor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;

import com.workfusion.odf.api.domain.Transaction;
import com.workfusion.odf.processor.TransactionProcessor;

import challenge.rpachallenge.config.RPAChallengeConfig;
import challenge.rpachallenge.config.SelectorsConfig;
import challenge.rpachallenge.rpa.RPAChallenge;
import challenge.rpachallenge.utils.ExcelOperations;
import challenge.rpachallenge.utils.FileUtils;

public class TransactionProcessorRPAChallenge implements TransactionProcessor {

	private Logger logger;
	private RPAChallengeConfig configValues;
	private SelectorsConfig selectorValues;

	@Inject
	public TransactionProcessorRPAChallenge(Logger logger, RPAChallengeConfig configValues, SelectorsConfig selectorValues) {
		this.logger = logger;
		this.configValues = configValues;
		this.selectorValues = selectorValues;
	}

	@Override
	public Transaction transform(Transaction transaction) {		
		
		String inputFilePath = transaction.getAttribute("inputFilePath");
		
		FileUtils fileUtils = new FileUtils(logger);
		RPAChallenge challengePage = new RPAChallenge(logger);
		
		boolean challengeDataRetrieved = false;
		List<Map<String, String>> challengeData = new ArrayList<>();
		
		try {
			challengeData = ExcelOperations.readExcel(inputFilePath);
			challengeDataRetrieved = true;
			logger.info("Excel file information was retrieved successfully");
		} catch (IOException e) {
			logger.error("Failed to read the input Excel file.");
			e.printStackTrace();
		}
		
		if(!challengeDataRetrieved) 
			return transaction;
		
		challengePage.openRPAChallengePage(configValues);
		
		challengePage.startRPAChallenge(selectorValues);
		
		for (Map<String, String> employeeData : challengeData) {
			challengePage.fillForm(employeeData, selectorValues);
		}
		
		fileUtils.takeScreenshot();
		boolean fileDeleted = FileUtils.deleteFile(inputFilePath);
		
		if (fileDeleted)
			logger.info("File was deleted " + inputFilePath);
		else
			logger.error("Failed to delete file " + inputFilePath);
		
		return transaction;
	}

}
