import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class File_IO {

	public void read(String path, AVL<Records> tree) {
		DateTimeFormatter formatterr = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line = br.readLine();
			if (line != null && line.startsWith("Date")) {
				line = br.readLine();
			}
			while (line != null) {
				try {
					String[] p = line.split(",");
					LocalDate date = LocalDate.parse(p[0], formatterr);
					Records record = new Records(date, Double.parseDouble(p[1]), Double.parseDouble(p[2]),
							Double.parseDouble(p[3]), Double.parseDouble(p[4]), Double.parseDouble(p[5]),
							Double.parseDouble(p[6]), Double.parseDouble(p[7]));
					tree.insert(record);
				} catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
					System.out.println("Skipping invalid line: " + line);
				}
				line = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeToFile(stack_linkedlist<Records> rStack, String path) throws IOException {
		try (FileWriter fr = new FileWriter(path)) {
			fr.write(
					"Date,IsraeliLines,GazaPowerPlant,EgyptianLines,TotalDailySupply,OverallDemand,PowerCutsHours,Temperature\n");

			while (!rStack.isEmpty()) {
				Records record = rStack.pop();
				String line = formatRecordForFile(record);
				fr.write(line + "\n");
			}
		}
	}

	private String formatRecordForFile(Records record) {
		return record.getDate().format(DateTimeFormatter.ofPattern("yyyy-mm-dd")) + "," + record.getIsraeliLines() + ","
				+ record.getGazaPowerPlant() + "," + record.getEgyptianLines() + "," + record.getTotalDailySupply()
				+ "," + record.getOverallDemand() + "," + record.getPowerCutsHours() + "," + record.getTemperature()
				+ "\n";
	}

}
