package net.amygdalum.tanteemmas;

import org.approvaltests.Approvals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.vertx.core.Vertx;
import net.amygdalum.tanteemmas.server.Server;
import net.amygdalum.tanteemmas.testutils.ChromeDriverFactory;
import net.amygdalum.tanteemmas.testutils.TestHelper;

public class ApprovalsTest {

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
		driver.findElement(By.xpath("//tr[4]/td[3]/a")).click();
		driver.findElement(By.xpath("//tr[2]/td[3]/a")).click();
		driver.findElement(By.xpath("//tr[3]/td[3]/a")).click();
		driver.findElement(By.xpath("//tr[2]/td[3]/a")).click();
		driver.findElement(By.xpath("//tr[7]/td[3]/a")).click();
		driver.findElement(By.xpath("//tr[6]/td[3]/a")).click();
		driver.findElement(By.xpath("//tr[5]/td[3]/a")).click();
		driver.findElement(By.xpath("//tr[3]/td[3]/a")).click();

		Approvals.verifyHtml(driver.getPageSource());
	}

	@After
	public void tearDown() throws InterruptedException {
		driver.quit();
		vertx.close();
	}
}
