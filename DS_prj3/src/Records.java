import java.time.LocalDate;

public class Records implements Comparable<Records> {
	private LocalDate date;
	private double israeliLines;
	private double gazaPowerPlant;
	private double egyptianLines;
	private double totalDailySupply;
	private double overallDemand;
	private double powerCutsHours;
	private double temperature;

	public Records(LocalDate date, double israeliLine, double gazaPower, double egyptianLine, double totalDailySupply,
			double overallDemand, double powerCutsHours, double temperature) {

		this.date = date;
		this.israeliLines = israeliLine;
		this.gazaPowerPlant = gazaPower;
		this.egyptianLines = egyptianLine;
		this.totalDailySupply = totalDailySupply;
		this.overallDemand = overallDemand;
		this.powerCutsHours = powerCutsHours;
		this.temperature = temperature;
	}

	public Records(LocalDate date) {
		this.date = date;
	}


	public Records() {
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public double getIsraeliLines() {
		return israeliLines;
	}

	public void setIsraeliLines(double israeliLines) {
		this.israeliLines = israeliLines;
	}

	public double getGazaPowerPlant() {
		return gazaPowerPlant;
	}

	public void setGazaPowerPlant(double gazaPowerPlant) {
		this.gazaPowerPlant = gazaPowerPlant;
	}

	public double getEgyptianLines() {
		return egyptianLines;
	}

	public void setEgyptianLines(double egyptianLines) {
		this.egyptianLines = egyptianLines;
	}

	public double getTotalDailySupply() {
		return totalDailySupply;
	}

	public void setTotalDailySupply(double totalDailySupply) {
		this.totalDailySupply = totalDailySupply;
	}

	public double getOverallDemand() {
		return overallDemand;
	}

	public void setOverallDemand(double overallDemand) {
		this.overallDemand = overallDemand;
	}

	public double getPowerCutsHours() {
		return powerCutsHours;
	}

	public void setPowerCutsHours(double powerCutsHours) {
		this.powerCutsHours = powerCutsHours;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	@Override
	public String toString() {
		return "Records [date=" + date + ", israeliLines=" + israeliLines + ", gazaPowerPlant=" + gazaPowerPlant
				+ ", egyptianLines=" + egyptianLines + ", totalDailySupply=" + totalDailySupply + ", overallDemand="
				+ overallDemand + ", powerCutsHours=" + powerCutsHours + ", temperature=" + temperature + "]" + "\n";
	}

	@Override
	public int compareTo(Records other) {
		if (this.date == null && other.getDate() == null) {
			return 0;
		}
		if (this.date == null) {
			return -1;
		}
		if (other.getDate() == null) {
			return 1;
		}
		return this.date.compareTo(other.getDate());
	}
}
