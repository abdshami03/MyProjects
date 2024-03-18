package student_lab;

import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
	    Scanner sc =new Scanner(System.in);
		Faculty faculty = new Faculty();
		Course course1 = new Course(123, "java");
		Course course2 = new Course(234, "digital");
		Student student1 = new Student("abd", "ramallah");
		Student student2 = new Student("shadi", "bethlehem");

		student1.addCourse(course1);
		student1.addCourse(course2);
		student2.addCourse(course1);
		course1.addStudent(student1);
		course1.addStudent(student2);
		course2.addStudent(student1);
		faculty.addCourse(course1);
		faculty.addCourse(course2);
		faculty.addStudent(student1);
		faculty.addStudent(student2);
		

		System.out.println("course info");
		for (Course course : faculty.getCourses()) {
			System.out.println("course no " + course.getCourseNo());
			System.out.println("course title " + course.getTitle());
			System.out.println("students ");
			for (Student student : course.getStudents()) {
				System.out.println(student.getName());
			}

		}

		System.out.println("student info ");
		for (Student student : faculty.getStudents()) {
			System.out.println("student name " + student.getName());
			System.out.println("address " + student.getAddress());
			System.out.println("courses ");
			for (Course course : student.getCourses()) {
				System.out.println(course.getTitle());
			}
			System.out.println();
		}
		System.out.println("enter the book name ");
		Course scourse = student1.searchCourse(sc.next());
		if (scourse != null) {
			System.out.println("Found course: " + scourse.getTitle());
		} else {
			System.out.println("Course not found.");
		}
	}
}
