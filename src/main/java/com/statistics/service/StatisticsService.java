/**
 * 
 */
package com.statistics.service;

import com.statistics.model.StatisticsSummary;
import com.statistics.model.Transaction;

/**
 * @author arijit nandi
 *
 */
public interface StatisticsService {
	
	void computeStatistics(Transaction transaction);
	
	StatisticsSummary getStatisticsInLastSixtySeconds();

}
