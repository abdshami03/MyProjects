package student_lab;

import java.util.ArrayList;

public class Student {
	private String name;
	private String address;
	private ArrayList<Course> courses;

	public Student(String name, String address) {
		this.setName(name);
		this.setAddress(address);
		this.courses = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public ArrayList<Course> getCourses() {
		return courses;
	}

	public void setCourses(ArrayList<Course> courses) {
		this.courses = courses;
	}

	public void addCourse(Course course) {
		courses.add(course);
	}

	public void updateCourse(Course course, String title1) {
		course.setTitle(title1);
	}

	public void removeCourse(Course course) {
		courses.remove(course);
	}

	public Course searchCourse(String ctitle) {
		for (Course course : courses) {
			if (course.getTitle().contains(ctitle) || String.valueOf(course.getCourseNo()).equals(ctitle)) {
				return course;
			}
		}
		return null;
	}
}
