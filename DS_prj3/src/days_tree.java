
public class days_tree<T extends Comparable<days_tree>> implements Comparable<days_tree> {
	int year;
	String month;
	int day;

	public days_tree() {
		this.year = year;
		this.month = month;
		this.day = day;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	@Override
	public int compareTo(days_tree o) {
		return Integer.compare(this.day, o.day);
	}

}
