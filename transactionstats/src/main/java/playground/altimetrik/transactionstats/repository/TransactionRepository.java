package playground.altimetrik.transactionstats.repository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import playground.altimetrik.transactionstats.domain.Transaction;

public interface TransactionRepository {
	
	
	public boolean addTransaction(Transaction transaction);
	public List<Transaction> getAllTransactions();
	public Transaction getByTransaction(UUID uuid);
	public List<Transaction> getTransactionByDateRange(ZonedDateTime startDate, ZonedDateTime endDate);
	public boolean deleteAllTransactions();
	
	

}
