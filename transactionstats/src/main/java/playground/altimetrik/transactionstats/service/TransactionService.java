package playground.altimetrik.transactionstats.service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import playground.altimetrik.transactionstats.domain.Statistics;
import playground.altimetrik.transactionstats.domain.Transaction;
import playground.altimetrik.transactionstats.enumerator.StatisticsTypeEnum;
import playground.altimetrik.transactionstats.exception.InvalidTransactionException;
import playground.altimetrik.transactionstats.exception.TransactionsNotFoundException;
import playground.altimetrik.transactionstats.helper.StatisticsHelper;
import playground.altimetrik.transactionstats.repository.TransactionRepository;
import playground.altimetrik.transactionstats.util.DateUtil;
import playground.altimetrik.transactionstats.validator.TransactionValidator;

@Service
public class TransactionService {

	@Autowired
	private TransactionValidator validator;

	@Autowired
	private TransactionRepository repository;

	public Transaction saveTransaction(Transaction transaction) {

		validator.validate(transaction);

		ZonedDateTime userEnteredTime = DateUtil.convertStringToZoneDateTime(transaction.getTime());

		ZonedDateTime currentTime = ZonedDateTime.now(ZoneId.of("UTC"));
		
		if(userEnteredTime.compareTo(currentTime)>0) {
			throw new InvalidTransactionException("Time passed is after the current time");
		}
		ZonedDateTime currentTimeMinusOne = currentTime.minusMinutes(1);

		if (currentTimeMinusOne.compareTo(userEnteredTime) > 0) {
			throw new InvalidTransactionException("Time passed is before the current time");
		}

		transaction.setUuid(UUID.randomUUID());
		transaction.setZonedDateTime(userEnteredTime);
		repository.addTransaction(transaction);
		return transaction;
	}

	public void deleteAllTransactions() {
		repository.deleteAllTransactions();
	}

	public Statistics pullStatistics(StatisticsTypeEnum statisticsTypeEnum) {
		// get current time as end time
		ZonedDateTime endTime = ZonedDateTime.now(ZoneId.of("UTC"));
		ZonedDateTime startTime = endTime.minusMinutes(1);
		List<Transaction> transactionList = repository.getTransactionByDateRange(startTime, endTime);
		if(CollectionUtils.isEmpty(transactionList)) {
			throw new TransactionsNotFoundException("No Transactions found in the last one minute");
		}
		Statistics statistics = StatisticsHelper.getStatistics(transactionList, statisticsTypeEnum);
		return statistics;

	}

}
