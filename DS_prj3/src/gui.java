import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class gui extends Application {
	private AVL<Records> rTree;
	File_IO f = new File_IO();
	TextArea Display = new TextArea();

	@Override
	public void start(Stage stage) {
		rTree = new AVL<>();
		BorderPane bp = new BorderPane();
		MenuBar mb = new MenuBar();
		Menu fm = new Menu("File");
		MenuItem loadFile = new MenuItem("Load Electricity Data");
		MenuItem saveFile = new MenuItem("Save Data");
		fm.getItems().addAll(loadFile, saveFile);
		mb.getMenus().add(fm);

		loadFile.setOnAction(e -> {
			try {
				loadFile(stage);
			} catch (Exception e1) {

				e1.printStackTrace();
			}
		});

		saveFile.setOnAction(e -> {
			try {
				saveFile(stage);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		VBox ms = MScreen();
		VBox ss = createStatisticsScreen();

		bp.setTop(mb);
		bp.setLeft(ms);
		bp.setCenter(ss);

		Scene s = new Scene(bp, 800, 600);
		stage.setTitle("Electricity Data");
		stage.setScene(s);
		stage.show();
	}

	private void loadFile(Stage stage) throws IOException {
		FileChooser fc = new FileChooser();
		File chosenF = fc.showOpenDialog(stage);
		if (chosenF != null) {
			try {
				f.read(chosenF.getAbsolutePath(), rTree);
				alert("Success", "File loaded successfully.");
			} catch (Exception e) {
				alert("Error", "Failed to load file: " + e.getMessage());
			}
		}
	}

	private void saveFile(Stage stage) {
		if (rTree == null || rTree.isEmpty()) {
			alert("Error", "No data to save.");
			return;
		}
		FileChooser fc = new FileChooser();
		fc.setTitle("Save CSV File");
		fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
		fc.setInitialFileName("ElectricityData.csv");

		File file = fc.showSaveDialog(stage);
		if (file != null) {
			try {
				stack_linkedlist<Records> RS = new stack_linkedlist<>();
				RS.convertT_To_S(rTree);
				f.writeToFile(RS, file.getAbsolutePath());
				alert("Success", "File saved successfully.");
			} catch (IOException e) {
				alert("Error", "Failed to write to file: " + e.getMessage());
				e.printStackTrace();
			}
		}
	}

	private VBox MScreen() {
		VBox vbox = new VBox(10);
		vbox.setPadding(new Insets(10));

		Button insertB = new Button("Insert New Record");
		insertB.setOnAction(e -> {
			Records r = new Records();
			LocalDate date = r.getDate();
			double israeliLine = r.getIsraeliLines();
			double gazaPower = r.getGazaPowerPlant();
			double egyptianLine = r.getEgyptianLines();
			double totalDailySupply = r.getTotalDailySupply();
			double overallDemand = r.getOverallDemand();
			double powerCutsHours = r.getPowerCutsHours();
			double temperature = r.getTemperature();
			Records newR = new Records(date, israeliLine, gazaPower, egyptianLine, totalDailySupply, overallDemand,
					powerCutsHours, temperature);
			if (newR != null) {
				rTree.insert(newR);
			}
		});

		Button updateB = new Button("Update Record");
		updateB.setOnAction(e -> {
			DatePicker dp = new DatePicker();
			dp.setPromptText("Select a date");

		});
		DatePicker dp = new DatePicker();

		Button deleteB = new Button("Delete Record");
		TextField deleteField = new TextField();
		deleteField.setPromptText("Enter record key");
		deleteB.setOnAction(e -> {
			String key = deleteField.getText().trim();
			if (key.isEmpty() || key == null) {
				boolean isDeleted = deleteByKey(key);

				if (isDeleted) {
					alert("Success", "Record deleted successfully.");
				} else {
					alert("Not Found", "Record not found or invalid key format.");
				}
			} else {
				alert("Input Required", "Please enter a record key.");
			}

		});

		Button searchB = new Button("Search By Date");
		dp.setPromptText("Select a date");

		Display = new TextArea();
		Display.setEditable(false);
		searchB.setOnAction(e -> {
			LocalDate dateToSearch = dp.getValue();
			if (dateToSearch != null) {
				searchAndDisplay(dateToSearch);
			} else {
				Display.setText("Please select a date to search.");
			}
		});

		Button traverseB = new Button("Traverse Trees");
		traverseB.setOnAction(e -> {
			rTree.traverseInOrder();
		});

		Button HeightB = new Button("Display Tree Heights");
		HeightB.setOnAction(e -> {
			if (rTree != null) {
				int treeHeight = rTree.height();
				alert("Tree Height", "The height of the AVL tree is: " + treeHeight);
			}
		});

		vbox.getChildren().addAll(insertB, updateB, deleteB, searchB, traverseB, HeightB);
		return vbox;
	}

	private void searchAndDisplay(LocalDate dateToSearch) {
		Display.clear();

	}

	private boolean deleteByKey(String key) {
		LocalDate dateKey;
		try {
			dateKey = LocalDate.parse(key, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		} catch (DateTimeParseException e) {
			System.out.println("Invalid date format");
			return false;
		}
		Records delrec = new Records();
		delrec.setDate(dateKey);
		TNode<Records> deletedN = rTree.delete(delrec);
		return deletedN != null;
	}

	private VBox createStatisticsScreen() {
		VBox vbox = new VBox(10);
		vbox.setPadding(new Insets(10));

		Label statL = new Label("Statistics:");
		ComboBox<String> statO = new ComboBox<>();
		statO.getItems().addAll("Day", "Month", "Year", "Total");
		Button showStatB = new Button("Show Statistics");
		Display.setEditable(false);

		showStatB.setOnAction(e -> {
			String so = statO.getValue();
			if (so == null) {
				alert("Error", "Please select a statistic option.");
				return;
			}
			String o = "";

			switch (so) {
			case "Day":
				o = DayStat();
				break;
			case "Month":
				o = MonthStat();
				break;
			case "Year":
				o = YearStat();
				break;
			case "Total":
				o = TotalStat();
				break;
			default:
				o = "Please select a valid option.";
				break;
			}

			Display.setText(o);
		});

		vbox.getChildren().addAll(statL, statO, showStatB, Display);
		return vbox;
	}

	private String DayStat() {
		return "Day statistics are ";
	}

	private String MonthStat() {
		return "Month stat are ";
	}

	private String YearStat() {
		return "Year stat are ";
	}

	private String TotalStat() {
		return "Total stat are ";
	}

	private void alert(String title, String message) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
