package page.cateam;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
	private Properties properties;

	public Properties getProperties() {
		return properties;
	}

	public Config(String configFileName) {
		properties = new Properties();

		try (InputStream input = Config.class.getClassLoader().getResourceAsStream(configFileName)) {

			if (input == null) {
				System.out.println(String.format("Sorry, unable to find %s", configFileName));
				return;
			}

			properties.load(input);
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}
}
