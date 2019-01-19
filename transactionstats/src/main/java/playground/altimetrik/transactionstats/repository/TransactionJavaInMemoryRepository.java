package playground.altimetrik.transactionstats.repository;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import playground.altimetrik.transactionstats.domain.Transaction;

@Component
public class TransactionJavaInMemoryRepository implements TransactionRepository {

	private List<Transaction> transactionList = new ArrayList<Transaction>();

	@Override
	public boolean addTransaction(Transaction transaction) {
		transactionList.add(transaction);
		return true;
	}

	@Override
	public List<Transaction> getAllTransactions() {
		return transactionList;
	}

	@Override
	public Transaction getByTransaction(UUID uuid) {
		throw new UnsupportedOperationException("Not supported now");
	}

	@Override
	public List<Transaction> getTransactionByDateRange(ZonedDateTime startDate, ZonedDateTime endDate) {
		List<Transaction> transactionFilteredList = transactionList.stream()
				.filter(trans -> trans.getZonedDateTime().compareTo(startDate) >= 0
						&& trans.getZonedDateTime().compareTo(endDate) <= 0)
				.collect(Collectors.toList());
		return transactionFilteredList;
	}

	@Override
	public boolean deleteAllTransactions() {
		transactionList.clear();
		return true;
	}

}
