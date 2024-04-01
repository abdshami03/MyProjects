import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUI extends Application{
	private DlinkedList studentList;
    private Node curr;
	@Override
	public void start(Stage arg0) throws Exception {
		TextArea textArea = new TextArea();
        Button nextButton = new Button("next");
        Button previousButton = new Button("prev");
        load("students.txt");
        curr = studentList.getFront();
        
        display(textArea);
        
        
        nextButton.setOnAction(event -> {
            if (curr != null && curr.getNext() != null) {
                curr = curr.getNext();
                display(textArea);
            }
        });
        
        previousButton.setOnAction(event -> {
            if (curr != null && curr.getPrev() != null) {
                curr = curr.getPrev();
                display(textArea);
            }
        });
        VBox root = new VBox();
        root.getChildren().addAll(textArea, nextButton, previousButton);

        Scene scene = new Scene(root, 300, 200);
        arg0.setScene(scene);
        arg0.setTitle("Doubly Linked List GUI");
        arg0.show();
	}
	
	private void display(TextArea textArea) {
        if (curr != null) {
            textArea.setText(curr.getData().toString());
        } else {
            textArea.setText("No data available.");
        }
    }

	 private void load(String filePath) {
	        studentList = new DlinkedList();

	        try {
	            File file = new File(filePath);
	            Scanner scanner = new Scanner(file);

	            while (scanner.hasNextLine()) {
	                String line = scanner.nextLine();
	                String[] parts = line.split(",");

	                if (parts.length == 3) {
	                    String name = parts[0];
	                    int age = Integer.parseInt(parts[1]);
	                    String major = parts[2];

	                    Student student = new Student(name, age, major);
	                    studentList.addLast(student);
	                } else {
	                    System.out.println("Invalid data format: " + line);
	                }
	            }
	            scanner.close();
	        } catch (FileNotFoundException e) {
	            System.out.println("File not found: " + filePath);
	        }
	    }

	public static void main(String[] args) {
		launch(args);
	}

}
