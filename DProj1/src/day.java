
public class day<T> implements Comparable<day<T>> {
	int d;
	day next;
	Records r;

	public day() {
		this.d = d;
		this.r = r;
		this.next = null;
	}

	public int getD() {
		return d;
	}

	public void setD(int d) {
		this.d = d;
	}

	public day getNext() {
		return next;
	}

	public void setNext(day next) {
		this.next = next;
	}

	public Records getR() {
		return r;
	}

	public void setR(Records data) {
		this.r = data;
	}

	@Override
	public int compareTo(day<T> other) {
		return Integer.compare(this.d, other.d);
	}
}
