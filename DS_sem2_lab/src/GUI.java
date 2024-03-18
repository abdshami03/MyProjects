import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.Scanner;

public class GUI extends Application {

	private TextArea textArea;

	@Override
	public void start(Stage primaryStage) {
		BorderPane root = new BorderPane();

		MenuBar menuBar = new MenuBar();
		Menu fileMenu = new Menu("File");
		MenuItem loadItem = new MenuItem("Load");
		MenuItem saveItem = new MenuItem("Save");
		Menu mathMenu = new Menu("Math Operations");
		MenuItem palindromeItem = new MenuItem("Check Palindrome");
		MenuItem factItem = new MenuItem("Factorial");
		MenuItem reverseItem = new MenuItem("Reverse Word");
		MenuItem hanoiItem = new MenuItem("Tower of Hanoi");

		textArea = new TextArea();
		textArea.setPrefHeight(200);
		textArea.setEditable(true);

		palindromeItem.setOnAction(e -> {
			String input = TextInputDialog("Enter a string:");
			if (input != null) {
				if (isPalindrome(input))
					appendToTextArea(input + " is a palindrome.");
				else
					appendToTextArea(input + " is not a palindrome.");
			}
		});

		factItem.setOnAction(e -> {
			try {
				int n = Integer.parseInt(TextInputDialog("Enter a number:"));
				if (n >= 0) {
					long factorial = calculateFactorial(n);
					appendToTextArea("Factorial of " + n + " is: " + factorial);
				} else {
					appendToTextArea("Factorial is not defined for negative numbers.");
				}
			} catch (NumberFormatException ex) {
				appendToTextArea("Invalid input. Please enter a valid integer.");
			}
		});

		reverseItem.setOnAction(e -> {
			String input = TextInputDialog("Enter a string:");
			if (input != null) {
				String reversed = reverseString(input);
				appendToTextArea("Reversed word: " + reversed);
			}
		});

		hanoiItem.setOnAction(e -> {
		});//?????????????????????????????????????????????????????//

		loadItem.setOnAction(e -> loadFromFile(primaryStage));
		saveItem.setOnAction(e -> saveToFile(primaryStage));

		mathMenu.getItems().addAll(palindromeItem, factItem, reverseItem, hanoiItem);
		fileMenu.getItems().addAll(loadItem, saveItem);
		menuBar.getMenus().addAll(fileMenu, mathMenu);

		root.setTop(menuBar);
		root.setCenter(textArea);

		Scene scene = new Scene(root, 400, 300);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Math Operations GUI");
		primaryStage.show();
	}

	private String TextInputDialog(String m) {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setContentText(m);
		dialog.showAndWait();
		return dialog.getResult();
	}

	private void appendToTextArea(String message) {
		textArea.appendText(message + "\n");
	}

	private boolean isPalindrome(String str) {
		String reversed = new StringBuilder(str).reverse().toString();
		return str.equalsIgnoreCase(reversed);
	}

	private long calculateFactorial(int n) {
		if (n == 0)
			return 1;
		else if (n < 0)
			throw new IllegalArgumentException("Factorial is not defined for negative numbers.");

		long factorial = 1;
		for (int i = 1; i <= n; ++i) {
			factorial *= i;
		}
		return factorial;
	}

	private String reverseString(String str) {
		return new StringBuilder(str).reverse().toString();
	}

	private void loadFromFile(Stage primaryStage) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open File");
		File file = fileChooser.showOpenDialog(primaryStage);
		if (file != null) {
			try (Scanner scanner = new Scanner(file)) {
				StringBuilder content = new StringBuilder();
				while (scanner.hasNextLine()) {
					content.append(scanner.nextLine()).append("\n");
				}
				textArea.setText(content.toString());
			} catch (FileNotFoundException e) {
				showAlert("Error", "File not found.");
			}
		}
	}

	private void saveToFile(Stage primaryStage) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save File");
		File file = fileChooser.showSaveDialog(primaryStage);
		if (file != null) {
			try (PrintWriter writer = new PrintWriter(file)) {
				writer.write(textArea.getText());
			} catch (IOException e) {
				showAlert("Error", "Error saving file.");
			}
		}
	}

	private void showAlert(String title, String message) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
