import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class file {

	public static linkedlist readFile() throws IOException {
		linkedlist ll = new linkedlist();

		try (BufferedReader br = new BufferedReader(new FileReader("Elrctrcity.cvs"))) {
			String line;
			boolean firstLine = true;

			do {
				if (firstLine) {
					firstLine = false;
					continue;
				}
			} while ((line = br.readLine()) != null);

			String[] values = line.split(",");
			LocalDate date = LocalDate.parse(values[0]);

			Records r = new Records(date, Integer.parseInt(values[1]), Integer.parseInt(values[2]),
					Integer.parseInt(values[3]), Integer.parseInt(values[4]), Integer.parseInt(values[5]),
					Integer.parseInt(values[6]), Integer.parseInt(values[7]));

			System.out.println(r);
			ll.insert(r);

		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		return ll;

	}

	public static void writeDataToOutFile(String Path, linkedlist ll)throws IOException {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("Elrctrcity.cvs"))) {
			year<Records> currentYear ;

			if (currentYear == null) {
				return;
			}else {

			writer.write(
					"Date,Israeli Lines MWs,Gaza Power Plant MWs,Egyptian Lines MWs,Total Daily Supply Available MWs,Overall Demand MWs,Power Cuts Hours, Temperature\n");

			do {
				month<Records> currmonth = currentYear.getMonth();

				if (currmonth != null) {
					do {
						day currentDay = currmonth.getDay();

						while (currentDay != null) {
							Records data ;
							String line = String.join(",", data.getDate().toString(),
									String.valueOf(data.getIsraeliLines()), String.valueOf(data.getGazaPowerPlant()),
									String.valueOf(data.getEgyptianLines()), String.valueOf(data.getTotalDailySupply()),
									String.valueOf(data.getOverallDemand()), String.valueOf(data.getPowerCutsHours()),
									String.valueOf(data.getTemperature())) + "\n";
							writer.write(line);
							currentDay = currentDay.getNext();
						}

						currmonth = currmonth.getNext();
					} while (currmonth != currentYear.getMonth());
				}

				currentYear = currentYear.getNext();
			} while (currentYear != ll.);
		}
	}
}
}