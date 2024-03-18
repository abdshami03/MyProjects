package cursur_array;

public class Driver {

	public static void main(String[] args) {
		cursor<Integer> c =new cursor<>();
		int l=c.createList();
		c.insertAtTail(50, l);
		c.insertAtTail(60, l);
		c.insertAtTail(70, l);
		c.insertAtTail(80, l);

	}

}
