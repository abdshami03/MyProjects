
public class District {
	private String name;
	private Location locs;
	private Node<District> next;
	private Node<District> prev;

	public District(String name) {
		this.name = name;
		this.locs = null;
		this.next = null;
		this.prev = null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Location getLocs() {
		return locs;
	}

	public void setLocs(Location locs) {
		this.locs = locs;
	}

	public Node<District> getNext() {
		return next;
	}

	public void setNext(Node<District> next) {
		this.next = next;
	}

	public Node<District> getPrev() {
		return prev;
	}

	public void setPrev(Node<District> prev) {
		this.prev = prev;
	}

	@Override
	public String toString() {
		return "District [name=" + name + ", locs=" + locs + ", next=" + next + ", prev=" + prev + "]";
	}

}
