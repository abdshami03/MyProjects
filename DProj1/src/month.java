
public class month<T> implements Comparable<month<T>> {
	int m;
	month next;
	day day;

	public month(int m) {
		this.m = m;
	}

	public int getM() {
		return m;
	}

	public void setM(int m) {
		this.m = m;
	}

	public month getNext() {
		return next;
	}

	public void setNext(month next) {
		this.next = next;
	}

	public day getDay() {
		return day;
	}

	public void setDay(day day) {
		this.day = day;
	}

	@Override
	public int compareTo(month<T> other) {
		return Integer.compare(this.m, other.m);
	}

}
