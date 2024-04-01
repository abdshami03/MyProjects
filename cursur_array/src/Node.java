
public class Node {
	private Object data;
	private int next;

	public Node(Object data, int next) {
		this.data = data;
		this.next = next;
	}

	public Node(Object data) {
		this.data = data;
		this.next = 0;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public int getNext() {
		return next;
	}

	public void setNext(int next) {
		this.next = next;
	}

	@Override
	public String toString() {
		return "Node [data=" + data + ", next=" + next + "]\n";
	}

}
