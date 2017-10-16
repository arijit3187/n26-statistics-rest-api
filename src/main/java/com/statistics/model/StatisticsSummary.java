/**
 * 
 */
package com.statistics.model;

import com.statistics.model.Statistics;

/**
 * Model for statistics summary
 *
 * @author arijit nandi
 */
public class StatisticsSummary {
	
	private double sum = 0.0;
    private long count = 0l;
    private double max = Double.MIN_VALUE;
    private double min = Double.MAX_VALUE;
    private double avg;

    public StatisticsSummary() {
    }

    public StatisticsSummary(Statistics statistics) {
        this.sum = statistics.getSum();
        this.count = statistics.getCount();
        this.max = statistics.getMax();
        this.min = statistics.getMin();
    }

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public double getMax() {
		return max;
	}

	public void setMax(double max) {
		this.max = max;
	}

	public double getMin() {
		return min;
	}

	public void setMin(double min) {
		this.min = min;
	}

	public double getAvg() {
		return avg;
	}

	public void setAvg(double avg) {
		this.avg = avg;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(avg);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (int) (count ^ (count >>> 32));
		temp = Double.doubleToLongBits(max);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(min);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(sum);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StatisticsSummary other = (StatisticsSummary) obj;
		if (Double.doubleToLongBits(avg) != Double.doubleToLongBits(other.avg))
			return false;
		if (count != other.count)
			return false;
		if (Double.doubleToLongBits(max) != Double.doubleToLongBits(other.max))
			return false;
		if (Double.doubleToLongBits(min) != Double.doubleToLongBits(other.min))
			return false;
		if (Double.doubleToLongBits(sum) != Double.doubleToLongBits(other.sum))
			return false;
		return true;
	}

	@Override
	public String toString(){
		
		return "StatisticsSummary::" +
                "sum=" + sum +
                ", count=" + count +
                ", max=" + max +
                ", min=" + min +
                ", avg=" + avg;
		
	}

}
