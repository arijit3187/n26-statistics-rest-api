/**
 * 
 */
package com.statistics.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.statistics.model.Statistics;
import com.statistics.model.StatisticsSummary;
import com.statistics.model.Transaction;

/**
 * @author arijit nandi
 *
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {

	private static final int SECONDS = 60;
	private static final Map<Integer, Statistics> statisticsInLastMinute = new ConcurrentHashMap<>(SECONDS);

	/**
	 * Computes statistics of only last 60 seconds Transaction information as
	 * provided by user. For each second of last minute new Statistics entry is created or
	 * existing one is updated with latest statistics for that second.
	 *
	 * Application holds constant memory storage (initial capacity 60 entries) about
	 * statistics of last minute --- > memory complexity is O(1)
	 *
	 * @param Transaction
	 *            transaction
	 */
	@Override
	public void computeStatistics(Transaction transaction) {

		int second = LocalDateTime.ofInstant(Instant.ofEpochMilli(transaction.getTimestamp()), ZoneId.systemDefault())
				.getSecond();
		statisticsInLastMinute.compute(second, (key, value) -> {
			if (value == null || (System.currentTimeMillis() - value.getTimestamp()) / 1000 >= SECONDS) {
				value = new Statistics();
				value.setTimestamp(transaction.getTimestamp());
				value.setSum(transaction.getAmount());
				value.setMax(transaction.getAmount());
				value.setMin(transaction.getAmount());
				value.setCount(1l);
				return value;
			}

			value.setCount(value.getCount() + 1);
			value.setSum(value.getSum() + transaction.getAmount());
			if (Double.compare(transaction.getAmount(), value.getMax()) > 0)
				value.setMax(transaction.getAmount());
			if (Double.compare(transaction.getAmount(), value.getMin()) < 0)
				value.setMin(transaction.getAmount());
			return value;
		});

	}

	/**
	 * Calculates and returns statistics summary based on statistics map
	 * (statisticsInLastMin).
	 *
	 * Calculation is made in constant time by only combining already calculated
	 * statistics, which means method runs with O(1) complexity
	 *
	 * @return StatisticsSummary
	 */

	@Override
	public StatisticsSummary getStatisticsInLastSixtySeconds() {
		StatisticsSummary summary = statisticsInLastMinute.values().stream()
				.filter(s -> (System.currentTimeMillis() - s.getTimestamp()) / 1000 < SECONDS)
				.map(StatisticsSummary::new).reduce(new StatisticsSummary(), (s1, s2) -> {
					s1.setSum(s1.getSum() + s2.getSum());
					s1.setCount(s1.getCount() + s2.getCount());
					s1.setMax(Double.compare(s1.getMax(), s2.getMax()) > 0 ? s1.getMax() : s2.getMax());
					s1.setMin(Double.compare(s1.getMin(), s2.getMin()) < 0 ? s1.getMin() : s2.getMin());
					return s1;
				});

		summary.setMin(Double.compare(summary.getMin(), Double.MAX_VALUE) == 0 ? 0.0 : summary.getMin());
		summary.setMax(Double.compare(summary.getMax(), Double.MIN_VALUE) == 0 ? 0.0 : summary.getMax());
		summary.setAvg(summary.getCount() > 0l ? summary.getSum() / summary.getCount() : 0.0);

		return summary;
	}

}
