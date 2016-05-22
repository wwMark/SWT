package jUnitTests.jUnitTest;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.imageio.ImageIO;

import org.iMage.edge.detection.sobel.filter.SobelFilter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestSobelFilter {
	BufferedImage bI;
	BufferedImage bIA;

	/*
	 * Initialize a filter and process an image
	 */
	@Before
	public void setUp() throws Exception {
		bI = ImageIO.read(new File(
				"E:\\Windows\\EclipseWorkSpace\\SWT\\iMage.edge-detection-sobel\\src\\test\\resources\\camera_obscura.png"));
		SobelFilter b = new SobelFilter();
		bIA = b.applyFilter(bI);
	}

	/*
	 * Output the processed image
	 */
	@After
	public void tearDown() throws Exception {
		SimpleDateFormat sDF = new SimpleDateFormat("_HHmmss_SSS");
		String name = "SobelFilter" + sDF.format(System.currentTimeMillis()).toString();
		File file = new File(
				"E:\\Windows\\EclipseWorkSpace\\SWT\\iMage.edge-detection-sobel\\src\\test\\resources\\"
						+ name + ".png");
		if (bI != null) {
			try {
				ImageIO.write(bIA, "png", file);
			} catch (IOException e) {
				System.out.println("There's something goes wrong!");
			}
		}
	}

	/*
	 * To test whether the processed image has the same height of the original
	 * image
	 */
	@Test
	public void testApplyFilter1() {
		assertEquals(bI.getHeight(), bIA.getHeight());
	}

	/*
	 * To test whether the processed image has the same width of the original
	 * image
	 */
	@Test
	public void testApplyFilter2() {
		assertEquals(bI.getWidth(), bIA.getWidth());
	}

}
