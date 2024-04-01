
public class Martyr {
	private String name;
	private String date;
	private int age;
	private String loc;
	private String dist;
	private String gender;
	private Node<Martyr> next;

	public Martyr(String name, String date, int age, String loc, String dist, String gender) {
		this.name = name;
		this.date = date;
		this.age = age;
		this.loc = loc;
		this.dist = dist;
		this.gender = gender;
		this.next = null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getLocation() {
		return loc;
	}

	public void setLocation(String location) {
		this.loc = location;
	}

	public String getDistrict() {
		return dist;
	}

	public void setDistrict(String district) {
		this.dist = district;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Node<Martyr> getNext() {
		return next;
	}

	public void setNext(Node<Martyr> next) {
		this.next = next;
	}

	@Override
	public String toString() {
		return "Martyr [name=" + name + ", date=" + date + ", age=" + age + ", location=" + loc + ", district=" + dist
				+ ", gender=" + gender + ", next=" + next + "]";
	}

}
