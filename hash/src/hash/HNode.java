package hash;

public class HNode<T extends Comparable<T>> {
	T data;

	final char D = 'D';
	final char E = 'E';
	final char F = 'F';
	char flag = E;

	public HNode(T data) {
		this.data = data;
		flag = F;
	}

	public HNode() {

	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public char getFlag() {
		return flag;
	}

	public void setFlag(char flag) {
		if (flag == D || flag == E || flag == F) {
			this.flag = flag;

		} else {
			System.out.println("the flag has to be F or D or E");
		}
	}

	public boolean isEmpty() {
		return flag == E || flag == D;
	}

	public boolean isFull() {
		return flag == F;
	}

	public boolean isDeleted() {
		return flag == D;
	}

	public void setDeleted() {
		flag = D;
	}

	@Override
	public String toString() {
		return "[" + data + "," + flag + "]";
	}

}
