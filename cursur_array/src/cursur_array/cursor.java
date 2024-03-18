package cursur_array;

public class cursor<T extends Comparable<T>> {
	Node<T>[] cursorArray;

	public int initialization() {
		for (int i = 0; i < cursorArray.length - 1; i++) {
			cursorArray[i] = new Node<>(null, i + 1);
		}
		cursorArray[cursorArray.length - 1] = new Node<>(null, 0);
		return 0;
	}

	public int malloc() {
		int p = cursorArray[0].next;
		cursorArray[0].next = cursorArray[p].next;
		return p;
	}

	public void free(int p) {
		cursorArray[p] = new Node(null, cursorArray[0].next);
		cursorArray[0].next = p;
	}

	public boolean isNull(int l) {
		return cursorArray[l] == null;
	}

	public boolean isEmpty(int l) {
		return cursorArray[l].next == 0;
	}

	public boolean isLast(int p) {
		return cursorArray[p].next == 0;
	}

	public int createList() {
		int l = malloc();
		if (l == 0)
			System.out.println("Error: Out of space!!!");
		else
			cursorArray[l] = new Node("-", 0);
		return l;
	}

	public void insertAtHead(T data, int l) {
		if (isNull(l))
			return;
		int p = malloc();
		if (p != 0) {
			cursorArray[p] = new Node(data, cursorArray[l].next);
			cursorArray[l].next = p;
		} else
			System.out.println("Error: Out of space!!!");
	}

	public void traversList(int l) {
		System.out.print("list_" + l + "-->");
		while (!isNull(l) && !isEmpty(l)) {
			l = cursorArray[l].next;
			System.out.print(cursorArray[l] + "-->");
		}
		System.out.println("null");

	}

	public int find(T data, int l) {
		while (!isNull(l) && !isEmpty(l)) {
			l = cursorArray[l].next;
			if (cursorArray[l].data.equals(data))
				return l;
		}
		return -1;
	}

	public int findPrevious(T data, int l) {
		while (!isNull(l) && !isEmpty(l)) {
			if (cursorArray[cursorArray[l].next].data.equals(data))
				return l;
			l = cursorArray[l].next;
		}
		return -1;
	}

	public Node delete(T data, int l) {
		int p = findPrevious(data, l);
		if (p != -1) {
			int c = cursorArray[p].next;
			Node temp = cursorArray[c];
			cursorArray[p].next = temp.next;
			free(c);
		}
		return null;
	}

	public void insertAtTail(T data, int l) {
		Node<T> tail;
		if (isNull(l)) {
			return;
		}
		int p = malloc();
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

	public void traverselist_recursion(int l) {
		if (isEmpty(l)) {
			System.out.println("null");
			return;
			
		}
		System.out.println(cursorArray[l]+"-->");
		traverselist_recursion(cursorArray[l].next);
	}

	public void traverselist_recursion_helper(int l) {
		traverselist_recursion(cursorArray[l].getNext());
	}

}
