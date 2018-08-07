package net.amygdalum.tanteemmas;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import de.retest.recheck.Recheck;
import de.retest.recheck.RecheckImpl;
import io.vertx.core.Vertx;
import net.amygdalum.tanteemmas.server.Server;
import net.amygdalum.tanteemmas.testutils.ChromeDriverFactory;

public class RecheckTest {

	private Vertx vertx;
	private WebDriver driver;
	private Recheck re;

	@Before
	public void setup() {
		vertx = Server.deployServer(8080);

		driver = ChromeDriverFactory.createNewInstance();

		re = new RecheckImpl();
	}

	@Test
	public void check_order() throws Exception {
		re.startTest("check_order");
		driver.get("http://localhost:8080/logout");
		// login
		driver.findElement(By.name("customer")).sendKeys("Max");
		driver.findElement(By.name("login")).click();

		re.check(driver, "initial");
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

		re.check(driver, "final");
		re.capTest();
	}

	@After
	public void tearDown() throws InterruptedException {
		driver.quit();
		vertx.close();
		re.cap();
	}
}
