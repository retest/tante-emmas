package net.amygdalum.tanteemmas;

import java.io.File;

import javax.imageio.ImageIO;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import de.retest.recheck.image.ExactImageDifferenceCalculator;
import de.retest.web.ScreenshotProvider;
import io.vertx.core.Vertx;
import net.amygdalum.tanteemmas.server.Server;
import net.amygdalum.tanteemmas.testutils.ChromeDriverFactory;
import net.amygdalum.tanteemmas.testutils.TestHelper;

public class PixelDiffTest {

	private Vertx vertx;
	private int port;
	private WebDriver driver;

	@Before
	public void setup() {
		port = TestHelper.generateRandomPort();
		vertx = Server.deployServer( port );

		driver = ChromeDriverFactory.createNewInstance();
	}

	@Ignore
	@Test
	public void check_order() throws Exception {
		driver.get( "http://localhost:" + port + "/logout" );
		// login
		driver.findElement( By.name( "customer" ) ).sendKeys( "Max" );
		driver.findElement( By.name( "login" ) ).click();

		// test
		driver.findElement( By.xpath( "//tr[4]/td[3]/a" ) ).click();
		driver.findElement( By.xpath( "//tr[4]/td[3]/a" ) ).click();
		driver.findElement( By.xpath( "//tr[2]/td[3]/a" ) ).click();
		driver.findElement( By.xpath( "//tr[3]/td[3]/a" ) ).click();
		driver.findElement( By.xpath( "//tr[2]/td[3]/a" ) ).click();
		driver.findElement( By.xpath( "//tr[7]/td[3]/a" ) ).click();
		driver.findElement( By.xpath( "//tr[6]/td[3]/a" ) ).click();
		driver.findElement( By.xpath( "//tr[5]/td[3]/a" ) ).click();
		driver.findElement( By.xpath( "//tr[3]/td[3]/a" ) ).click();

		// Check
		final String run = "target/test-classes/checks/PixelDiffTest.check_order-run.jpg";
		final String original = "src/test/resources/checks/PixelDiffTest.check_order.jpg";

		// To update the master locally, just use `original` instead of `run`:
		ImageIO.write( ScreenshotProvider.shootFullPage( driver ), "PNG", new File( run ) );

		final ExactImageDifferenceCalculator pixelDiff = new ExactImageDifferenceCalculator();
		Assert.assertEquals( 1.0, pixelDiff.compare( original, run ).getMatch(), 0.01 );
	}

	@After
	public void tearDown() {
		driver.quit();
		vertx.close();
	}
}
