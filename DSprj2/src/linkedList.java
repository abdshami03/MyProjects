
public class linkedList<T> {

	Node<T> head;
	int size;

	public linkedList() {
		this.head = null;
		this.size = 0;
	}

	public void add(T data) {
		Node<T> newNode = new Node<>(data);
		if (head != null) {
			Node<T> curr = head;
			while (curr.next != null) {
				curr = curr.next;
			}
			curr.next = newNode;

		} else {
			head = newNode;
		}
		size++;
	}

	public T get(int index) {
		if (index < 0 || index >= size) {
			System.out.println("Index out of bounds");
		}

		Node<T> curr = head;
		for (int i = 0; i < index; i++) {
			curr = curr.next;
		}

		return curr.data;
	}

	public int size() {
		return size;
	}
}
