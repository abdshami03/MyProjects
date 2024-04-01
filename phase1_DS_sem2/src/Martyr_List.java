
public class Martyr_List {
	private Node<Martyr> front;

	public Martyr_List() {
		this.front = null;
	}

	public Node<Martyr> getHead() {
		return front;
	}

	public void setHead(Node<Martyr> head) {
		this.front = head;
	}

	public void addMartyr(String name, String date, int age, String loc, String dist, String gender) {
		Martyr new_mar = new Martyr(name, date, age, loc, dist, gender);
		Node<Martyr> newNode = new Node<>(new_mar);
		if (front == null) {
			front = newNode;
		} else if (age < front.getData().getAge()) {
			newNode.setNext(newNode);
			front = newNode;
		} else {
			Node<Martyr> curr = front;
			while (curr.getNext() != null && age >= curr.getNext().getData().getAge()) {
				curr = curr.getNext();
			}
			newNode.setNext(curr.getNext());
			curr.setNext(newNode);
		}
	}

	@Override
	public String toString() {
		return "MartyrList [head=" + front + "]";
	}

}
