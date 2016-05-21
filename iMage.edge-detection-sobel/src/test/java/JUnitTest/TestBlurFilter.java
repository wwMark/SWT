package JUnitTest;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestBlurFilter {
	BufferedImage bI;

	@Before
	public void setUp() throws Exception {
		bI = ImageIO.read(new File(
				"E:\\Windows\\EclipseWorkSpace\\SWT\\iMage.edge-detection-sobel\\src\\test\\resources\\camera_obscura.png"));
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testApplyFilter() {
		fail("Not yet implemented");
	}

}
