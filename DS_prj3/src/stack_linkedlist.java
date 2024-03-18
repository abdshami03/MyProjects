import java.util.EmptyStackException;

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

	public T pop() throws EmptyStackException {

		Node<T> toDel = topNode;
		if (!isEmpty())
			topNode = topNode.getNext();
		return toDel.getData();

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

	private void traverseToStack(TNode<Records> node, stack_linkedlist<Records> stack) {
		if (node != null) {
			traverseToStack(node.getRight(), stack);

			stack.push(node.getData());
			traverseToStack(node.getLeft(), stack);
		}
	}

	public stack_linkedlist<Records> convertT_To_S(AVL<Records> tree) {
		stack_linkedlist<Records> s = new stack_linkedlist<>();
		traverseToStack(tree.getRoot(), s);
		return s;
	}

}
