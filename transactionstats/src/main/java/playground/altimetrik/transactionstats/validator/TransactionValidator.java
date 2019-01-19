package playground.altimetrik.transactionstats.validator;

import java.time.Instant;

import org.springframework.stereotype.Component;

import playground.altimetrik.transactionstats.constants.TransactionConstants;
import playground.altimetrik.transactionstats.domain.Transaction;
import playground.altimetrik.transactionstats.exception.InvalidRequestException;

@Component
public class TransactionValidator {

	public void validate(Transaction transaction) {

		if (transaction.getTime() == null) {
			throw new InvalidRequestException("Time is a required parameter");
		}

		try {
			Instant.parse(transaction.getTime());

		} catch (Exception exception) {
			exception.printStackTrace();
			throw new InvalidRequestException(
					"Invalid date format , correct format is " + TransactionConstants.DATE_FORMAT_TZ);
		}
	}

}
