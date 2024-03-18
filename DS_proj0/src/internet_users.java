
public class internet_users {
	private static String[] countries;
	private static double[] intuse;
	private static int size;

	public internet_users() {
		countries = new String[16];
		intuse = new double[16];
		size = 0;
	}

	public void addRecord(String country, double usage) {
		if (size == countries.length) {
			resize();
		}
		countries[size] = country;
		intuse[size] = usage;
		size++;
	}

	private void resize() {
		int newCapacity = countries.length * 2;
		String[] newCountries = new String[newCapacity];
		double[] newInternetUsage = new double[newCapacity];
		for (int i = 0; i < size; i++) {
			newCountries[i] = countries[i];
			newInternetUsage[i] = intuse[i];
		}
		countries = newCountries;
		intuse = newInternetUsage;
	}

	public int findRecord(String country) {
		for (int i = 0; i < size; i++) {
			if (countries[i].equalsIgnoreCase(country)) {
				return i;
			}
		}
		return -1;
	}

	public double getInternetUsage(String country) {
		int index = findRecord(country);
		if (index != -1) {
			return intuse[index];
		}
		return -1;
	}

	public double avguse() {
		if (size == 0) {
			return 0.0;
		}
		double alluse = 0.0;
		for (int i = 0; i < size; i++) {
			alluse += intuse[i];
		}
		return (alluse / size);
	}

}
