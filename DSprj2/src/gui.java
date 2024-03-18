import java.io.File;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class gui extends Application {
	private equation_processor<String> equationProcessor = new equation_processor<>();

	private TextField file;
	private TextField equation_section;
	private TextArea output;

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Equation Processor");

		GridPane grid = new GridPane();
		grid.setPadding(new Insets(20, 20, 20, 20));
		grid.setVgap(10);
		grid.setHgap(10);

		file = new TextField();
		file.setPromptText("Enter file path");
		GridPane.setConstraints(file, 0, 0, 6, 1);

		Button load = new Button("Load");
		GridPane.setConstraints(load, 6, 0);
		load.setOnAction(e -> handleLoadButton());

		Label equation_Label = new Label("Equation Section:");
		GridPane.setConstraints(equation_Label, 0, 2);

		equation_section = new TextField();
		equation_section.setEditable(false);
		equation_section.setMinHeight(40);
		GridPane.setConstraints(equation_section, 1, 2, 5, 1);

		output = new TextArea();
		output.setEditable(false);
		output.setWrapText(true);
		output.setMinHeight(100);
		GridPane.setConstraints(output, 0, 3, 7, 1);

		Button prev = new Button("Prev");
		GridPane.setConstraints(prev, 0, 4);
		prev.setOnAction(e -> handlePrevButton());

		Button next = new Button("Next");
		GridPane.setConstraints(next, 6, 4);
		next.setOnAction(e -> handleNextButton());

		grid.getChildren().addAll(file, load, equation_Label, output, equation_section, prev, next);

		Scene scene = new Scene(grid, 600, 400);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void handleLoadButton() {
		FileChooser file_chooser = new FileChooser();
		file_chooser.setTitle("Choose File");
		File chosen_file = file_chooser.showOpenDialog(null);

		if (chosen_file != null) {
			String filePath = chosen_file.getAbsolutePath();
			file.setText(filePath);

			equationProcessor.load(filePath);
			show();
		}
	}

	private void handlePrevButton() {
		equationProcessor.prev();
		show();
	}

	private void handleNextButton() {
		equationProcessor.next();
		show();
	}

	private void show() {
		equation_section.setText(String.valueOf(equationProcessor.getIndex() + 1));
		String eq = equationProcessor.getequation();
		output.setText("Current Equation: " + eq + "\nEvaluation: " + equationProcessor.infix_to_postfix(eq));
	}

	public static void main(String[] args) {
		launch(args);
	}
}
