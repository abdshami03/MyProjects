
public class DlinkedList {
	private Node front, back;
	private int size;

	public DlinkedList() {
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

	@Override
	public String toString() {
		return "linkedList [front=" + front + ", back=" + back + ", size=" + size + "]";
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

	public void traverse(Node curr) {
		while (curr != null) {
			System.out.println(curr.getData());
			curr = curr.getNext();
		}
	}

	public boolean removeFirst() {
		if (size == 0)
			return false;
		else if (size == 1)
			front = back = null;
		else
			front = front.getNext();
		size--;
		return true;
	}

	public boolean removeLast() {
		if (size == 0) {
			return false;
		} else if (size == 1) {
			front = back = null;
		} else {
			Node curr = back.getPrev();
			curr.setNext(null);
			back = curr;
		}
		size--;
		return true;
	}

	public boolean remove(int index) {
		if (size == 0)
			return false;
		else if (index == 0)
			return removeFirst();
		else if (index == size - 1)
			return removeLast();
		else if (index > 0 && index < size - 1) {
			Node current = front;
			for (int i = 0; i < index - 1; i++)
				current = current.getNext();
			current.setNext(current.getNext().getNext());
			size--;
			return true;
		} else
			return false;
	}

	public Object remove_object(int index) {
		if (size == 0 || index < 0 || index >= size)
			return null;

		Object removedObj;

		if (index == 0) {
			removedObj = front.getData();
			removeFirst();
		} else if (index == size - 1) {
			removedObj = back.getData();
			removeLast();
		} else {
			Node curr = front;
			for (int i = 0; i < index - 1; i++)
				curr = curr.getNext();

			removedObj = curr.getNext().getData();
			curr.setNext(curr.getNext().getNext());
			size--;
		}

		return removedObj;
	}

	public Object removeFirst_object() {
		if (size == 0)
			return null;

		Object removedObj = front.getData();
		removeFirst();
		return removedObj;
	}

	public Object removeLast_object() {
		if (size == 0)
			return null;

		Object removedObj = back.getData();
		removeLast();
		return removedObj;
	}

	public void clear() {
		front = back = null;

	}

	public int find(Object o) {
		Node curr = front;
		int index = 0;

		while (curr != null) {
			if (curr.getData().equals(o)) {
				return index;
			}
			curr = curr.getNext();
			index++;
		}
		return -1;
	}

}
