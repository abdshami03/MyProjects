
public class cursor<T> {
	int stack_index;
	Node<stack_linkedlist<T>> head;

	public cursor() {
		this.stack_index = -1;
		this.head = null;
	}

	public void new_stack() {
		stack_linkedlist<T> newS = new stack_linkedlist<>();
		Node<stack_linkedlist<T>> newNode = new Node<>(newS);
		if (head == null) {
			head = newNode;
		} else {
			Node<stack_linkedlist<T>> curr = head;
			while (curr.next != null) {
				curr = curr.next;
			}
			curr.next = newNode;
		}

		stack_index++;
	}

	public stack_linkedlist<T> getCurrStack() {
		Node<stack_linkedlist<T>> curr = head;
		for (int i = 0; i < stack_index; i++) {
			if (curr == null) {
				System.out.println("Index out of bounds");
			}
			curr = curr.next;
		}

		if (curr == null) {
			System.out.println("Index out of bounds");
		}

		return curr.data;
	}

	public void nextStack() {
		if (stack_index < size() - 1) {
			stack_index++;
		}
	}

	public void moveToPrevStack() {
		if (stack_index > 0) {
			stack_index--;
		}
	}

	public int size() {
		int count = 0;
		Node<stack_linkedlist<T>> curr = head;
		while (curr != null) {
			count++;
			curr = curr.next;
		}
		return count;
	}

}
