package net.amygdalum.tanteemmas;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.vertx.core.Vertx;
import net.amygdalum.tanteemmas.server.Server;
import net.amygdalum.tanteemmas.testutils.ChromeDriverFactory;
import net.amygdalum.tanteemmas.testutils.TestHelper;

public class SeleniumTest {

	private Vertx vertx;
	private int port;
	private WebDriver driver;

	@Before
	public void setup() {
		port = TestHelper.generateRandomPort();
		vertx = Server.deployServer(port);

		driver = ChromeDriverFactory.createNewInstance();
	}

	@Test
	public void check_order() throws Exception {
		driver.get("http://localhost:" + port + "/logout");
		// login
		driver.findElement(By.name("customer")).sendKeys("Max");
		driver.findElement(By.name("login")).click();

		// test
		driver.findElement(By.xpath("//tr[4]/td[3]/a")).click();

		assertEquals("sunscreen 2.02", driver.findElement(By.xpath("(//table//tr[2])[2]")).getText());
	}

	@After
	public void tearDown() throws InterruptedException {
		driver.quit();
		vertx.close();
	}
}
