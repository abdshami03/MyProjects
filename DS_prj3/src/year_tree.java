
public class year_tree<T extends Comparable<year_tree>> implements Comparable<year_tree> {
	int year;
	month_tree month;

	public year_tree(int year, month_tree month) {
		this.year = year;
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public month_tree getMonth() {
		return month;
	}

	public void setMonth(month_tree month) {
		this.month = month;
	}

	@Override
	public int compareTo(year_tree o) {
		return Integer.compare(this.year, o.year);
	}

}
