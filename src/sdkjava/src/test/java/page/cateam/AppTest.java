package page.cateam;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {
	@Test
	void appHasAGreeting() {
		App app = new App();
		assertNotNull(app, "app is not null");
	}
}
