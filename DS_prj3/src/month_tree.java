
public class month_tree<T extends Comparable<T>> implements Comparable<month_tree<T>> {
	int year;
	String month;

	public month_tree(int year, String month) {
		this.year = year;
		this.month = month;
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

	@Override
	public int compareTo(month_tree o) {
		return this.month.compareTo(month);
	}
}
