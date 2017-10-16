/**
 * 
 */
package com.statistics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.statistics.model.StatisticsSummary;
import com.statistics.service.StatisticsService;

/**
 * REST API to get Statistics per minute
 * 
 * @author arijit nandi
 *
 */
@RestController
@RequestMapping("/statistics")
public class StatisticsController {
	
	@Autowired
	StatisticsService statisticsService;
	
	@GetMapping
	public StatisticsSummary getStatistics(){
		
		return statisticsService.getStatisticsInLastSixtySeconds();
	}

}
