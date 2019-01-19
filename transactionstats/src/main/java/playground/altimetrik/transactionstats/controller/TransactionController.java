package playground.altimetrik.transactionstats.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import playground.altimetrik.transactionstats.domain.Statistics;
import playground.altimetrik.transactionstats.domain.Transaction;
import playground.altimetrik.transactionstats.enumerator.StatisticsTypeEnum;
import playground.altimetrik.transactionstats.service.TransactionService;

@RestController
@RequestMapping("/v1/xyzbank/transactions")
@Api(tags = { "Transaction Statistics" })
@SwaggerDefinition(tags = { @Tag(name = "Transaction Statistics", description = "This API Includes Saving a transaction, clearing all transactions, Getting transactions Statistics for the current minute") })
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	@ApiOperation(notes = "Save Transaction", value = "This Operation saves the transaction , The timestamp should not be 1 minute less than current time , also the timestamp cannot have future time(greater than current time)") 
	@PostMapping
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	public Transaction saveTransaction(@RequestBody Transaction transaction) {
		Transaction addedTransaction = transactionService.saveTransaction(transaction);
		return addedTransaction;

	}

	@ApiOperation(notes = "Clear All Transactions", value = "This operation clears all the transactions") 
	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void clearAllTransactions() {
		transactionService.deleteAllTransactions();
	}
	
	@ApiOperation(notes = "Statistics", value = "This operation based on statisticsType it fetches the statistics of the transactions for the last 1 minute") 
	@GetMapping
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public Statistics getStatistics(@RequestParam StatisticsTypeEnum statisticsType) {
		return transactionService.pullStatistics(statisticsType);
	}

}
