
public class BST<T extends Comparable<T>> {
	TNode<T> root;

	public void insert(T data) {
		if (isEmpty())
			root = new TNode<T>(data);
		else
			insert(data, root);
	}

	public void insert(T data, TNode<T> node) {
		if (data.compareTo((T) node.data) >= 0) {
			if (!node.hasRight())
				node.setRight(new TNode(data));
			else
				insert(data, node.right);
		} else {
			if (!node.hasLeft())
				node.left = new TNode<T>(data);
			else
				insert(data, node.left);
		}
	}

	private boolean isEmpty() {
		return root == null;
	}

	public void traverseInOrder() {
		traverseInOrder(root);
	}

	private void traverseInOrder(TNode node) {
		if (node != null) {
			if (!node.hasLeft())
				traverseInOrder(node.left);
			System.out.print(node + " ");
			if (node.right != null)
				traverseInOrder(node.right);
		}
	}

	public int size() {
		return size(root);
	}

	private int size(TNode<T> node) {
		if (node == null)
			return 0;
		return 1 + size(node.getLeft()) + size(node.getRight());
	}

	public int countParents() {
		return countParents(root);
	}

	private int countParents(TNode<T> node) {
		if (node == null || node.isLeaf())
			return 0;

		return 1 + countParents(node.getLeft()) + countParents(node.getRight());
	}

	public TNode delete(T data) {

		TNode current = root;
		TNode parent = root;
		boolean isLeftChild = false;

		if (isEmpty())
			return null; // tree is empty
		while (current != null && !current.data.equals(data)) {
			parent = current;
			if (data.compareTo((T) current.data) < 0) {
				current = current.left;
				isLeftChild = true;
			} else {
				current = current.right;
				isLeftChild = false;
			}
		}

		if (current == null)
			return null; // node to be deleted not found
		// case 1: node is a leaf
		if (!current.hasLeft() && !current.hasRight()) {
			if (current == root) // tree has one node
				root = null;
			else {
				if (isLeftChild)
					parent.left = null;
				else
					parent.right = null;
			}
			return current;
		} else if (current.hasLeft()) { // current has left child only
			if (current == root) {
				root = current.left;
			} else if (isLeftChild) {
				parent.left = current.left;
			} else {
				parent.right = current.left;
			}
		} else if (current.hasRight()) { // current has right child only
			if (current == root) {
				root = current.right;
			} else if (isLeftChild) {
				parent.left = current.right;
			} else {
				parent.right = current.right;
			}
		} else if (current.hasLeft()) { // current has left child only
			if (current == root) {
				root = current.left;
			} else if (isLeftChild) {
				parent.left = current.left;
			} else {
				parent.right = current.left;
			}
		} else if (current.hasRight()) { // current has right child only
			if (current == root) {
				root = current.right;
			} else if (isLeftChild) {
				parent.left = current.right;
			} else {
				parent.right = current.right;
			}
		} else {
			TNode successor = getSuccessor(current);
			if (current == root)
				root = successor;
			else if (isLeftChild) {
				parent.left = successor;
			} else {
				parent.right = successor;
			}
			successor.left = current.left;
		}
		return getSuccessor(current);

	}

	private TNode getSuccessor(TNode node) {
		TNode parentOfSuccessor = node;
		TNode successor = node;
		TNode current = node.right;
		while (current != null) {
			parentOfSuccessor = successor;
			successor = current;
			current = current.left;
		}
		if (successor != node.right) {
			parentOfSuccessor.left = successor.right;
			successor.right = node.right;
		}
		return successor;
	}

	public boolean isFull() {
		return isFull(root);
	}

	private boolean isFull(TNode<T> node) {
		if (node == null) {
			return true;
		} else if (node.isLeaf()) {
			return true;
		} else if (node.hasLeft() && node.hasRight()) {
			return isFull(node.getLeft()) && isFull(node.getRight());
		} else {
			return false;
		}
	}

	public TNode search(T data) {
		return search(data, root);
	}

	public TNode search(T data, TNode node) {
		if (node != null) {
			int comp = node.data.compareTo(data);
			if (comp == 0)
				return node;
			else if (comp > 0 && node.hasLeft())
				return search(data, node.left);
			else if (comp < 0 && node.hasRight())
				return search(data, node.right);
		}
		return null;
	}

	public int height() {
		return height(root);
	}

	public int height(TNode node) {
		if (node == null)
			return 0;
		if (node.isLeaf())
			return 1;
		int left = 0;
		int right = 0;
		if (node.hasLeft())
			left = height(node.left);
		if (node.hasRight())
			right = height(node.right);
		if (left > right) {
			return (left + 1);
		} else {
			return (right + 1);
		}

	}

	public boolean isComplete() {
		int index = 0;
		int nodeCount = countParents(root);
		return isComplete(root, index, nodeCount);
	}

	private boolean isComplete(TNode<T> node, int index, int nodeCount) {
		if (node == null) {
			return true;
		}

		if (index >= nodeCount) {
			return false;
		}

		return isComplete(node.getLeft(), 2 * index + 1, nodeCount)
				&& isComplete(node.getRight(), 2 * index + 2, nodeCount);
	}

	public void postOrder() {
		postOrder(root);
	}

	private void postOrder(TNode<T> node) {
		if (node != null) {
			postOrder(node.getLeft());
			postOrder(node.getRight());
			System.out.print(node.getData() + " ");
		}
	}

	public void preOrder() {
		preOrder(root);
	}

	private void preOrder(TNode<T> node) {
		if (node != null) {
			System.out.print(node.getData() + " ");
			preOrder(node.getLeft());
			preOrder(node.getRight());
		}
	}

	public void levelOrderTraversal() {
		if (root == null) {
			System.out.println("Tree is empty.");
			return;
		}

		int height = height(root);

		for (int level = 1; level <= height; level++) {
			printLevel(root, level);
		}
	}

	private void printLevel(TNode<T> node, int level) {
		if (node == null) {
			return;
		}
		if (level == 1) {
			System.out.print(node.getData() + " ");
		} else if (level > 1) {
			printLevel(node.getLeft(), level - 1);
			printLevel(node.getRight(), level - 1);
		}
	}
}