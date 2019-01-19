package playground.altimetrik.transactionstats.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import playground.altimetrik.transactionstats.domain.Statistics;
import playground.altimetrik.transactionstats.enumerator.StatisticsTypeEnum;
import playground.altimetrik.transactionstats.service.TransactionService;

public class TransactionControllerTest {

	@InjectMocks
	private TransactionController controller;

	@Before

	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Mock
	private TransactionService transactionService;

	@Test
	public void testTransaction() {
		Statistics statistics = new Statistics();
		statistics.setCount(5L);
		when(transactionService.pullStatistics(any(StatisticsTypeEnum.class))).thenReturn(statistics);
		Statistics stats = controller.getStatistics(StatisticsTypeEnum.ALL);
		assertEquals(stats.getCount(), statistics.getCount());

	}

}
