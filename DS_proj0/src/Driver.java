import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Driver {
	

	public static void main(String[] args) {

		internet_users list = new internet_users();

		try (BufferedReader reader = new BufferedReader(new FileReader("internet_2020.csv"))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(",");
				if (parts.length == 2) {
					String country = parts[0];
					double usage = Double.parseDouble(parts[1]);
					list.addRecord(country, usage);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Example usage of the list operations
		int index = list.findRecord("United States");
		if (index != -1) {
			System.out.println("Internet usage for United States: " + list.getInternetUsage("United States") + "%");
		} else {
			System.out.println("United States not found in the list.");
		}

		double averageUsage = list.avguse();
		System.out.println("Average Internet usage: " + averageUsage + "%");
	}
}
