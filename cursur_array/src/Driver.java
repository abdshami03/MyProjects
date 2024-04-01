
public class Driver {

	public static void main(String[] args) {
		cursor c = new cursor();
		c.initialization();
		int l = c.createList();
		c.insertAtTail(50, l);
		c.insertAtTail(60, l);
		c.insertAtTail(70, l);
		c.insertAtTail(80, l);
		System.out.println(c);

	}

}
