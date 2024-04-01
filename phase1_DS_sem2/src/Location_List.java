
public class Location_List {
	private Node<Location> front;

	public Location_List() {
		this.front = null;
	}

	public void addLocation(String name) {
		Location new_loc = new Location(name);
		Node<Location> newNode = new Node<>(new_loc);
		if (front == null) {
			front = newNode;
		} else {
			Node<Location> curr = front;
			while (curr.getNext() != null) {
				curr = curr.getNext();
			}
			curr.setNext(newNode);
		}
	}

	public Node<Location> getHead() {
		return front;
	}

	public void setHead(Node<Location> head) {
		this.front = head;
	}

	@Override
	public String toString() {
		return "LocationList [head=" + front + "]";
	}

}
