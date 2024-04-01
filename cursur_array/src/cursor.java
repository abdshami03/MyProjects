import java.util.Arrays;

public class cursor {
	private Node[] cursorArray;
	private final static int MAX_SIZE = 10;

	public void initialization() {
		cursorArray = new Node[MAX_SIZE];
		for (int i = 0; i < MAX_SIZE; i++) {
			cursorArray[i] = new Node(null, i + 1);
		}
		cursorArray[MAX_SIZE - 1].setNext(0);
	}

	public int cursorAlloc() {
		int p = cursorArray[0].getNext();
		cursorArray[0].setNext(cursorArray[p].getNext());
		return p;
	}

	public int createList() {
		int l = cursorAlloc();
		if (l == 0)
			System.out.println("Error: Out of space!!!");
		else
			cursorArray[l] = new Node("-", 0);
		return l;
	}

	public void free(int p) {
		cursorArray[p] = new Node(null, cursorArray[0].getNext());
		cursorArray[0].setNext(p);
	}

	public boolean isNull(int l) {
		return cursorArray[l] == null;
	}

	public boolean isEmpty(int l) {
		return cursorArray[l].getNext() == 0;
	}

	public boolean isLast(int p) {
		return cursorArray[p].getNext() == 0;
	}

	public void insertAtHead(Object data, int l) {
		if (isNull(l))
			return;
		int p = cursorAlloc();
		if (p != 0) {
			cursorArray[p] = new Node(data, cursorArray[l].getNext());
			cursorArray[l].setNext(p);
		} else
			System.out.println("Error: Out of space!!!");
	}

	public int find(Object data, int l) {
		int p = cursorArray[l].getNext();
		while ((p != 0) && !cursorArray[p].getData().equals(data)) {
			p = cursorArray[p].getNext();
		}
		return p;
	}

	public void traversList(int l) {
		System.out.print("list_" + l + "-->");
		while (!isNull(l) && !isEmpty(l)) {
			l = cursorArray[l].getNext();
			System.out.print(cursorArray[l] + "-->");
		}
		System.out.println("null");

	}

	public int findPrevious(Object data, int l) {
		while (!isNull(l) && !isEmpty(l)) {
			if (cursorArray[cursorArray[l].getNext()].getData().equals(data))
				return l;
			l = cursorArray[l].getNext();
		}
		return -1;
	}

	public Node remove(Object data, int l) {
		int p = findPrevious(data, l);
		if (p != -1) {
			int c = cursorArray[p].getNext();
			Node temp = cursorArray[c];
			cursorArray[p].setNext(temp.getNext());
			free(c);
		}
		return null;
	}

	public void insertAtTail(Object data, int l) {
		Node tail;
		if (isNull(l)) {
			return;
		}
		int p = cursorAlloc();
		if (p != 0) {
			while (!isLast(l)) {
				l = cursorArray[l].getNext();
			}
			cursorArray[p] = new Node(data, 0);
			cursorArray[l].setNext(p);
		} else {
			System.out.println("Error: Out of space!!!");
		}
	}

	@Override
	public String toString() {
		return "cursorArray=" + Arrays.toString(cursorArray);
	}

}
