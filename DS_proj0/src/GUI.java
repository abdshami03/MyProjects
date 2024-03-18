import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class GUI extends Application {
	public static void main(String[] args) {
        launch(args);
    }

	private TableView<countryinfo> tableView;
	private ObservableList<countryinfo> data;
	private List<countryinfo> records;

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Internet Usage Records");

		tableView = new TableView<countryinfo>();
		data = FXCollections.observableArrayList();

		TableColumn<countryinfo, String> countryColumn = new TableColumn<>("Country");
		countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));

		TableColumn<countryinfo, Double> internetUsageColumn = new TableColumn<>("Internet Usage (%)");
		internetUsageColumn.setCellValueFactory(new PropertyValueFactory<>("internetUsage"));

		tableView.getColumns().addAll(countryColumn, internetUsageColumn);

		Button loadButton = new Button("Load Data");
		loadButton.setOnAction(e -> loadCSV());

		Button addButton = new Button("Add ");
		addButton.setOnAction(e -> add());

		Button deleteButton = new Button("Delete");
		deleteButton.setOnAction(e -> delete());

		Button searchButton = new Button("Search ");
		searchButton.setOnAction(e -> search());

		Button filterButton = new Button("Filter Records");
		filterButton.setOnAction(e -> filterRecords());

		HBox buttonBox = new HBox(loadButton, addButton, deleteButton, searchButton, filterButton);

		VBox layout = new VBox(tableView, buttonBox);

		Scene scene = new Scene(layout, 800, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void loadCSV() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
		File selectedFile = fileChooser.showOpenDialog(null);

		if (selectedFile != null) {
			records = readCSV(selectedFile);
			data.setAll(records);
		}
	}

	private List<countryinfo> readCSV(File file) {
		List<countryinfo> records = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(",");
				if (parts.length == 2) {
					String country = parts[0];
					double usage = Double.parseDouble(parts[1]);
					records.add(new countryinfo(country, usage));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return records;
	}

	private void add() {
	}

	private void delete() {
	}

	private void search() {
	}

	private void filterRecords() {
	}

}
