
public class countryinfo {
	private String country;
	private double internetUsage;

	public countryinfo(String country, double internetUsage) {
		this.country = country;
		this.internetUsage = internetUsage;
	}

	public String getCountry() {
		return country;
	}

	public double getInternetUsage() {
		return internetUsage;
	}
}
