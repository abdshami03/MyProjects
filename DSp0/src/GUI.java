import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;
import java.util.Optional;

public class GUI extends Application {
	private MartyrList martyrList;

	private TextField name_f, age_f, location_f, dod_f, gender_f;
	private Button add_B, delete_B, search_B, count_B, load_B;
	private TextArea outputArea;

	@Override
	public void start(Stage primaryStage) {
		martyrList = new MartyrList();

		primaryStage.setTitle("GUI");

		VBox root = new VBox(10);
		root.setPadding(new Insets(10));

		name_f = new TextField();
		age_f = new TextField();
		location_f = new TextField();
		dod_f = new TextField();
		gender_f = new TextField();

		add_B = new Button("Add");
		delete_B = new Button("Delete");
		search_B = new Button("Search");
		count_B = new Button("Count");
		load_B = new Button("Load");

		outputArea = new TextArea();
		outputArea.setEditable(false);

		add_B.setOnAction(e -> add());
		delete_B.setOnAction(e -> delete());
		search_B.setOnAction(e -> search());
		count_B.setOnAction(e -> count());
		load_B.setOnAction(e -> loadData());

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(5);
		grid.addRow(0, new Label("Name:"), name_f);
		grid.addRow(1, new Label("Age:"), age_f);
		grid.addRow(2, new Label("Location:"), location_f);
		grid.addRow(3, new Label("Date of Death:"), dod_f);
		grid.addRow(4, new Label("Gender:"), gender_f);

		HBox buttons = new HBox(10);
		buttons.getChildren().addAll(add_B, delete_B, search_B, count_B, load_B);

		root.getChildren().addAll(grid, buttons, outputArea);

		Scene scene = new Scene(root, 400, 300);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void add() {
		try {
			String name = name_f.getText();
			int age = Integer.parseInt(age_f.getText());
			String location = location_f.getText();
			String dod = dod_f.getText();
			char gender = gender_f.getText().charAt(0);

			Record record = new Record(name, age, location, dod, gender);
			martyrList.add(record);

			outputArea.setText("Record added ");
		} catch (NumberFormatException e) {
			outputArea.setText("Invalid age");
		}
	}

	private void delete() {
		String name = name_f.getText();
		boolean deleted = martyrList.delete(name);

		if (deleted) {
			outputArea.setText("Record deleted");
		} else {
			outputArea.setText("Record not found.");
		}
	}

	private void search() {
		String name = name_f.getText();
		Record record = martyrList.search(name);

		if (record != null) {
			outputArea.setText("Record found:\n" + record.toString());
		} else {
			outputArea.setText("Record not found.");
		}
	}

	private void count() {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Count Records");
		dialog.setHeaderText("Enter 'age', 'gender', or 'date' to count records by:");
		Optional<String> result = dialog.showAndWait();

		result.ifPresent(action -> {
			TextInputDialog inputDialog = new TextInputDialog();
			inputDialog.setTitle("Count Records");
			switch (action.toLowerCase()) {
			case "age":
				inputDialog.setHeaderText("Enter age:");
				break;
			case "gender":
				inputDialog.setHeaderText("Enter gender (e.g., 'm', 'f'):");
				break;
			case "date":
				inputDialog.setHeaderText("Enter date (YYYY-MM-DD):");
				break;
			default:
				outputArea.setText("Invalid action.");
				return;
			}

			Optional<String> inputResult = inputDialog.showAndWait();
			inputResult.ifPresent(input -> {
				switch (action.toLowerCase()) {
				case "age":
					try {
						int age = Integer.parseInt(input);
						int countByAge = martyrList.countByAge(age);
						outputArea.setText("Number of Deathes with age " + age + ": " + countByAge);
					} catch (NumberFormatException e) {
						outputArea.setText("Invalid age format.");
					}
					break;
				case "gender":
					int countByGender = martyrList.countByGender(input.charAt(0));
					outputArea.setText("Number of Deathes with gender '" + input.charAt(0) + "': " + countByGender);
					break;
				case "date":
					int countByDate = martyrList.countByDate(input);
					outputArea.setText("Number of Deathes with date '" + input + "': " + countByDate);
					break;
				}
			});
		});
	}

	private void loadData() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Select File");
		File selectedFile = fileChooser.showOpenDialog(null);
		if (selectedFile != null) {
			try (BufferedReader br = new BufferedReader(new FileReader(selectedFile))) {
				String line;
				boolean firstLineSkipped = false;
				while ((line = br.readLine()) != null) {
					if (!firstLineSkipped) {
						firstLineSkipped = true;
						continue;
					}
					String[] parts = line.split(",");
					if (parts.length == 5) {
						String name = parts[0];
						String ageStr = parts[1];
						String location = parts[2];
						String dod = parts[3];
						char gender = parts[4].charAt(0);

						if (!ageStr.isEmpty()) {
							try {
								int age = Integer.parseInt(ageStr);
								Record r = new Record(name, age, location, dod, gender);
								martyrList.add(r);
							} catch (NumberFormatException e) {
								outputArea.setText("Error loading data: Invalid age format.");
								return;
							}
						} else {
							continue;
						}
					}
				}
				outputArea.setText("Data loaded");
			} catch (IOException e) {
				outputArea.setText("Error loading data: " + e.getMessage());
			}
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
