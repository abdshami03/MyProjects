import java.time.LocalDate;
import java.util.Date;

public class Records {
	private LocalDate date;
	private int israeliLines;
	private int gazaPowerPlant;
	private int egyptianLines;
	private int totalDailySupply;
	private int overallDemand;
	private int powerCutsHours;
	private int temperature;

	public Records(LocalDate date, int israeliLine, int gazaPower, int egyptianLine, int totalDailySupply,
			int overallDemand, int powerCutsHours, int temperature) {

		this.date = date;
		this.israeliLines = israeliLine;
		this.gazaPowerPlant = gazaPower;
		this.egyptianLines = egyptianLine;
		this.totalDailySupply = totalDailySupply;
		this.overallDemand = overallDemand;
		this.powerCutsHours = powerCutsHours;
		this.temperature = temperature;
	}

	public Records() {
	}

	public Records(LocalDate date) {
		this.date = date;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getIsraeliLines() {
		return israeliLines;
	}

	public void setIsraeliLines(int israeliLines) {
		this.israeliLines = israeliLines;
	}

	public int getGazaPowerPlant() {
		return gazaPowerPlant;
	}

	public void setGazaPowerPlant(int gazaPowerPlant) {
		this.gazaPowerPlant = gazaPowerPlant;
	}

	public int getEgyptianLines() {
		return egyptianLines;
	}

	public void setEgyptianLines(int egyptianLines) {
		this.egyptianLines = egyptianLines;
	}

	public int getTotalDailySupply() {
		return totalDailySupply;
	}

	public void setTotalDailySupply(int totalDailySupply) {
		this.totalDailySupply = totalDailySupply;
	}

	public int getOverallDemand() {
		return overallDemand;
	}

	public void setOverallDemand(int overallDemand) {
		this.overallDemand = overallDemand;
	}

	public int getPowerCutsHours() {
		return powerCutsHours;
	}

	public void setPowerCutsHours(int powerCutsHours) {
		this.powerCutsHours = powerCutsHours;
	}

	public int getTemperature() {
		return temperature;
	}

	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}

	@Override
	public String toString() {
		return "Records [date=" + date + ", israeliLines=" + israeliLines + ", gazaPowerPlant=" + gazaPowerPlant
				+ ", egyptianLines=" + egyptianLines + ", totalDailySupply=" + totalDailySupply + ", overallDemand="
				+ overallDemand + ", powerCutsHours=" + powerCutsHours + ", temperature=" + temperature + "]";
	}

}
