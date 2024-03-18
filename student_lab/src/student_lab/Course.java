package student_lab;

import java.util.ArrayList;

public class Course {
	private int courseNo;
	private String title;
	private ArrayList<Student> students;
	private ArrayList<Faculty> facultyMembers;

	public Course(int courseNo, String title) {
		this.courseNo = courseNo;
		this.title = title;
		this.students = new ArrayList<>();
		this.facultyMembers = new ArrayList<>();
	}

	public void addStudent(Student student) {
		students.add(student);
	}

	public void removeStudent(Student student) {
		students.remove(student);
	}

	public void addFaculty(Faculty faculty) {
		facultyMembers.add(faculty);
	}

	public void removeFaculty(Faculty faculty) {
		facultyMembers.remove(faculty);
	}

	public int getCourseNo() {
		return courseNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ArrayList<Student> getStudents() {
		return students;
	}

	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}
}
