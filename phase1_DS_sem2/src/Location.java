
public class Location {
	private String name;
	private Martyr_List martyrs;
	private Node<Location> next;

	public Location(String name) {
		this.name = name;
		this.next = null;
		this.martyrs = new Martyr_List();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Martyr_List getMartyrs() {
		return martyrs;
	}

	public void setMartyrs(Martyr_List martyrs) {
		this.martyrs = martyrs;
	}

	public Node<Location> getNext() {
		return next;
	}

	public void setNext(Node<Location> next) {
		this.next = next;
	}

	@Override
	public String toString() {
		return "Location [name=" + name + ", martyrs=" + martyrs + ", next=" + next + "]";
	}
}
