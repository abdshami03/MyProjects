
public class AVL<T extends Comparable<T>> {
	TNode<Records> root;

	public TNode<Records> getRoot() {
		return root;
	}

	public void setRoot(TNode<Records> root) {
		this.root = root;
	}

	public AVL() {
		this.root = null;
	}

	public TNode<Records> rotateRight(TNode<Records> nodeN) {
		TNode<Records> nodeC = nodeN.getLeft();
		nodeN.setLeft(nodeC.getRight());
		nodeC.setRight(nodeN);

		return nodeC;
	}

	public TNode<Records> rotateLeft(TNode<Records> nodeN) {
		TNode<Records> nodeC = nodeN.getRight();
		nodeN.setRight(nodeC.getLeft());
		nodeC.setLeft(nodeN);

		return nodeC;
	}

	public TNode<Records> rotateRightLeft(TNode<Records> nodeN) {
		nodeN.setRight(rotateRight(nodeN.getRight()));
		return rotateLeft(nodeN);
	}

	public TNode<Records> rotateLeftRight(TNode<Records> nodeN) {
		nodeN.setLeft(rotateLeft(nodeN.getLeft()));
		return rotateRight(nodeN);
	}

	private TNode<Records> rebalance(TNode<Records> nodeN) {
		int diff = getHeightDifference(nodeN);
		if (diff > 1) {
			if (getHeightDifference(nodeN.left) > 0)
				nodeN = rotateRight(nodeN);
			else
				nodeN = rotateLeftRight(nodeN);
		} else if (diff < -1) {
			if (getHeightDifference(nodeN.right) < 0)
				nodeN = rotateLeft(nodeN);
			else
				nodeN = rotateRightLeft(nodeN);
		}
		return nodeN;
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
		return (left > right) ? (left + 1) : (right + 1);
	}

	private int getHeightDifference(TNode<Records> node) {
		if (node == null) {
			return 0;
		}
		return height(node.getLeft()) - height(node.getRight());
	}

	public void addEntry(Records data, TNode<Records> rootNode) {
		assert rootNode != null;

		if (data.compareTo(rootNode.getData()) < 0) {

			if (rootNode.hasLeft()) {

				TNode leftChild = rootNode.left;
				addEntry(data, leftChild);
				rootNode.left = rebalance(leftChild);
			} else
				rootNode.left = new TNode(data);
		} else {
			if (rootNode.hasRight()) {
				TNode rightChild = rootNode.right;
				addEntry(data, rightChild);
				rootNode.right = rebalance(rightChild);
			} else
				rootNode.right = new TNode(data);
		}
	}

	public boolean isEmpty() {
		return root == null;
	}

	public void insert(Records data) {
		if (isEmpty())
			root = new TNode<>(data);
		else {
			TNode<Records> rootNode = root;
			addEntry(data, rootNode);
			root = rebalance(rootNode);
		}
	}

	public TNode<T> delete(T data) {

		TNode curr = root;
		TNode parent = root;
		boolean isLeftChild = false;
		if (isEmpty())
			return null;
		while (curr != null && !curr.data.equals(data)) {
			parent = curr;
			if (data.compareTo((T) curr.data) < 0) {
				curr = curr.left;
				isLeftChild = true;
			} else {
				curr = curr.right;
				isLeftChild = false;
			}
		}

		if (curr == null)
			return null;
		// case 1: node is a leaf
		if (!curr.hasLeft() && !curr.hasRight()) {
			if (curr == root)
				root = null;
			else {
				if (isLeftChild)
					parent.left = null;
				else
					parent.right = null;
			}
			return curr;
		} else if (curr.hasLeft()) {
			if (curr == root) {
				root = curr.left;
			} else if (isLeftChild) {
				parent.left = curr.left;
			} else {
				parent.right = curr.left;
			}
		} else if (curr.hasRight()) {
			if (curr == root) {
				root = curr.right;
			} else if (isLeftChild) {
				parent.left = curr.right;
			} else {
				parent.right = curr.right;
			}
		} else if (curr.hasLeft()) {
			if (curr == root) {
				root = curr.left;
			} else if (isLeftChild) {
				parent.left = curr.left;
			} else {
				parent.right = curr.left;
			}
		} else if (curr.hasRight()) {
			if (curr == root) {
				root = curr.right;
			} else if (isLeftChild) {
				parent.left = curr.right;
			} else {
				parent.right = curr.right;
			}
		} else {
			TNode successor = getSuccessor(curr);
			if (curr == root)
				root = successor;
			else if (isLeftChild) {
				parent.left = successor;
			} else {
				parent.right = successor;
			}
			successor.left = curr.left;
		}
		return getSuccessor(curr);

	}

	private TNode<Records> getSuccessor(TNode<Records> node) {
		TNode<Records> parent = node;
		TNode<Records> successor = node;
		TNode<Records> curr = node.getRight();
		while (curr != null) {
			parent = successor;
			successor = curr;
			curr = curr.left;
		}
		if (successor != node.right) {
			parent.left = successor.right;
			successor.right = node.right;
		}
		return successor;
	}

	public TNode<T> search(T data, TNode node) {
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

	public boolean update(T oldData, T newData) {
		TNode<T> update_node = search(oldData, root);
		if (update_node != null) {
			update_node.data = newData;
			root = rebalance(root);
			return true;
		}
		return false;
	}

	public void traverseInOrder() {
		traverseInOrder(root);
	}

	private void traverseInOrder(TNode node) {
		if (node != null) {
			if (node.hasLeft())
				traverseInOrder(node.left);
			System.out.print(node + " ");
			if (node.right != null)
				traverseInOrder(node.right);
		}
	}

}
