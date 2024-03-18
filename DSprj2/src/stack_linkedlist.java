
public class stack_linkedlist<T> {

	Node<T> topNode;

	public Node<T> getTopNode() {
		return topNode;
	}

	public void setTopNode(Node<T> topNode) {
		this.topNode = topNode;
	}

	public void push(T data) {
		Node<T> newNode = new Node<>(data);
		newNode.next = topNode;
		topNode = newNode;
	}

	public Node<T> pop() {
		Node<T> toDel = topNode;
		if (!isEmpty())
			topNode = topNode.getNext();
		return toDel;

	}

	public boolean isEmpty() {
		return topNode == null;
	}

	public T peek() {
		if (isEmpty()) {
			System.out.println("the stack is empty");
		}

		return topNode.data;
	}

}
