
public class year<T> implements Comparable<year<T>> {
	int y;
	year next;
	month month;

	public year() {
		this.y = y;
		this.month = null;
		this.next = null;
	}

	public year(int y) {
		this.y = y;
		this.month = null;
		this.next = null;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public year getNext() {
		return next;
	}

	public void setNext(year next) {
		this.next = next;
	}

	public month getMonth() {
		return month;
	}

	public void setMonth(month month) {
		this.month = month;
	}

	@Override
	public int compareTo(year<T> other) {
		return Integer.compare(this.y, other.y);
	}

}
