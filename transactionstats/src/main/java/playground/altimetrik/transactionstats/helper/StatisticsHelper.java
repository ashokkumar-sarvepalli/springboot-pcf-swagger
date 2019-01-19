package playground.altimetrik.transactionstats.helper;

import java.util.List;
import java.util.stream.Collectors;

import playground.altimetrik.transactionstats.domain.Statistics;
import playground.altimetrik.transactionstats.domain.Transaction;
import playground.altimetrik.transactionstats.enumerator.StatisticsTypeEnum;

public class StatisticsHelper {

	public static Statistics getStatistics(List<Transaction> transactionList, StatisticsTypeEnum statisticsEnum) {

		Statistics statistics = new Statistics();
		statistics.setCount(Long.valueOf(transactionList.size()));

		List<Double> valuesList = transactionList.stream().map(trans -> trans.getAmount()).collect(Collectors.toList());
		if (statisticsEnum == StatisticsTypeEnum.ALL) {
			populateStatisticsIterativeApproach(valuesList, statistics);
		} else if (statisticsEnum == StatisticsTypeEnum.MAX) {
			statistics.setMax(valuesList.stream().mapToDouble(s -> s).max().getAsDouble());
		} else if (statisticsEnum == StatisticsTypeEnum.MIN) {
			statistics.setMin(valuesList.stream().mapToDouble(s -> s).min().getAsDouble());
		} else if (statisticsEnum == StatisticsTypeEnum.SUM) {
			statistics.setSum(valuesList.stream().mapToDouble(s -> s).sum());
		} else if (statisticsEnum == StatisticsTypeEnum.AVG) {
			statistics.setAvg(valuesList.stream().mapToDouble(s -> s).average().getAsDouble());
		}

		return statistics;

	}

	/*
	 * Good to go with iterative approach if we need all statistics
	 */
	private static void populateStatisticsIterativeApproach(List<Double> transactionList, Statistics statistics) {

		double min = transactionList.get(0);
		double max = transactionList.get(0);
		double sum = transactionList.get(0);

		for (int i = 1; i < transactionList.size(); i++) {
			sum = sum + transactionList.get(i);

			if (min > transactionList.get(i)) {
				min = transactionList.get(i);
			}

			if (max < transactionList.get(i)) {
				max = transactionList.get(i);
			}

		}

		statistics.setMax(max);
		statistics.setMin(min);
		statistics.setSum(sum);
		statistics.setAvg((sum / transactionList.size()));

	}
}
