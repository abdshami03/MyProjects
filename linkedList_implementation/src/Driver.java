import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {

		linkedList list = new linkedList();
		list.addFirst(3);
		list.addLast(7);
		list.addLast(9);
		list.addFirst(1);
		list.addLast(11);
		System.out.println(list.toString());
		System.out.println(list.getFirst());
		System.out.println(list.getLast());

		linkedList studentList = new linkedList();

		String filePath = "students.txt";
		try {
			File file = new File(filePath);
			Scanner sc = new Scanner(file);

			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] parts = line.split(",");

				if (parts.length == 3) {
					String name = parts[0];
					int age = Integer.parseInt(parts[1]);
					String major = parts[2];

					Student student = new Student(name, age, major);
					studentList.addStudent(student);
				} else {
					System.out.println("Invalid data format: " + line);
				}
			}
			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + filePath);
		}
		System.out.println("Student List:");
		studentList.traverse(studentList.getFront());

	}

}
