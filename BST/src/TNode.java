
public class TNode<T extends Comparable<T>> {
	T data;
	TNode left;
	TNode right;

	public TNode(T data) {
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public TNode getLeft() {
		return left;
	}

	public void setLeft(TNode<T> left) {
		this.left = left;
	}

	public TNode getRight() {
		return right;
	}

	public void setRight(TNode<T> right) {
		this.right = right;
	}

	@Override
	public String toString() {
		return "TNode [data=" + data + ", left=" + left + ", right=" + right + "]";
	}

	public boolean isLeaf() {
		return (left == null && right == null);
	}

	public boolean hasLeft() {
		return left != null;
	}

	public boolean hasRight() {
		return right != null;
	}

}
