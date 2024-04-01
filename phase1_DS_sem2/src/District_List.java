
public class District_List {
	private Node<District> front;

	public District_List() {
		this.front = null;
	}

	public void addDistrict(String name) {
		District new_dist = new District(name);
		Node<District> newNode = new Node<>(new_dist);
		if (front == null) {
			front = newNode;
		} else {
			Node<District> curr = front;
			while (curr.getNext() != null) {
				curr = curr.getNext();
			}
			curr.setNext(newNode);
			newNode.setPrev(curr);
		}
	}

	public Node<District> getHead() {
		return front;
	}

	public void setHead(Node<District> head) {
		this.front = head;
	}

	@Override
	public String toString() {
		return "DistrictList [head=" + front + "]";
	}

}
