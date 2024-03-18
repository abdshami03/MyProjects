
public class Record {
	String name;
	int age;
	String location;
	String DOD;
	char gender;

	public Record(String name, int age, String location, String dOD, char gender) {
		super();
		this.name = name;
		this.age = age;
		this.location = location;
		DOD = dOD;
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDOD() {
		return DOD;
	}

	public void setDOD(String dOD) {
		DOD = dOD;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "Record [name=" + name + ", age=" + age + ", location=" + location + ", DOD=" + DOD + ", gender="
				+ gender + "]";
	}

}