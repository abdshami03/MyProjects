
public class linkedList {
	private Node front, back;
	private int size;

	public linkedList() {
		this.front = null;
		this.back = null;
		this.size = 0;
	}

	public Node getFront() {
		return front;
	}

	public void setFront(Node front) {
		this.front = front;
	}

	public Node getBack() {
		return back;
	}

	public void setBack(Node back) {
		this.back = back;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void addFirst(Object data) {
		Node newNode = new Node(data);
		if (size == 0) {
			front = back = newNode;
		} else {
			newNode.setNext(front);
			front = newNode;
		}
		size++;
	}

	public void addLast(Object data) {
		Node newNode;
		newNode = new Node(data);
		if (size == 0) {
			front = back = null;
		} else {
			back.setNext(newNode);
			back = newNode;
		}
		size++;
	}

	public Object getFirst() {
		if (size == 0) {
			return null;
		} else {
			return front.getData();
		}
	}

	public Object getLast() {
		if (size == 0) {
			return null;
		} else {
			return back.getData();
		}
	}

	public Object get(int index) {
		if (size == 0) {
			return null;
		} else if (index == 0) {
			return getFirst();
		} else if (index == size - 1) {
			return getLast();
		} else if (index > 0 && index < size - 1) {
			Node curr = front;
			for (int i = 0; i < index; i++)
				curr = curr.getNext();
			return curr.getData();

		} else {
			return null;
		}
	}

	public void helper_add(Object data) {
		add(getSize(), data);
	}

	public void add(int index, Object data) {
		if (index == 0)
			addFirst(data);
		else if (index >= getSize())
			addLast(data);
		else {
			Node newNode = new Node(data);
			Node curr = front;
			for (int i = 0; i < index - 1; i++)
				curr = curr.getNext();
			newNode.setNext(curr.getNext());
			curr.setNext(newNode);
			size++;
		}
	}

	@Override
	public String toString() {
		return "linkedList [front=" + front + ", back=" + back + ", size=" + size + "]"+"\n";
	}

	public void traverse(Node curr) {
		while (curr != null) {
			System.out.println(curr.getData());
			curr = curr.getNext();
		}
	}

	public void addStudent(Student data) {
		Node newNode = new Node(data);
		if (size == 0 || data.compareTo((Student) front.getData()) <= 0) {
			addFirst(data);
			return;
		}

		if (data.compareTo((Student) back.getData()) >= 0) {
			addLast(data);
			return;
		}

		Node curr = front;
		while (curr.getNext() != null && data.compareTo((Student) curr.getNext().getData()) > 0) {
			curr = curr.getNext();
		}
		newNode.setNext(curr.getNext());
		curr.setNext(newNode);
		size++;

	}

}
