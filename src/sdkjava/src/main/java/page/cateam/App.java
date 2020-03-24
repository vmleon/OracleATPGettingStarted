package page.cateam;


import java.io.IOException;

public class App {
	public static void main(String[] args) {
		Config config = new Config("config.properties");
		SDK atp = new SDK(config);
		try {
			atp.printAvailabilityDomains();
			atp.printATPs();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
