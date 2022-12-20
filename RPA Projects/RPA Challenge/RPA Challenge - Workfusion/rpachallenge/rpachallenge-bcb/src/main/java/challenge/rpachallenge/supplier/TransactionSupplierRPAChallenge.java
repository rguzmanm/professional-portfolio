package challenge.rpachallenge.supplier;

import com.workfusion.odf.api.connector.TransactionSupplier;
import com.workfusion.odf.api.domain.Transaction;

import challenge.rpachallenge.config.RPAChallengeConfig;
import challenge.rpachallenge.config.SelectorsConfig;
import challenge.rpachallenge.rpa.RPAChallenge;

import org.slf4j.Logger;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class TransactionSupplierRPAChallenge implements TransactionSupplier {

	private final Logger logger;
	private RPAChallengeConfig configValues;
	private SelectorsConfig selectorValues;

	@Inject
	public TransactionSupplierRPAChallenge(final Logger logger, RPAChallengeConfig configValues,
			SelectorsConfig selectorValues) {
		this.logger = logger;
		this.configValues = configValues;
		this.selectorValues = selectorValues;
	}

	@Override
	public Collection<Transaction> get() {

		Collection<Transaction> transactions = new ArrayList<>();

		// Open RPA Challenge Page
		RPAChallenge MainPage = new RPAChallenge(logger);
		MainPage.openRPAChallengePage(configValues);

		// Download input file
		String downloadedfilePath = MainPage.downloadInputFile(selectorValues);

		Transaction transaction = new Transaction();
		transaction.setId(uuid());
		transaction.putAttribute("inputFilePath", downloadedfilePath);
		transactions.add(transaction);

		return transactions;
	}

	private String uuid() {
		return UUID.randomUUID().toString();
	}

}
