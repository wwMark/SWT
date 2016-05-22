package jUnitTests.jUnitTest;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.imageio.ImageIO;

import org.iMage.edge.detection.sobel.filter.GrayScaleFilter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestGrayScaleFilter {
	BufferedImage bI;
	BufferedImage bIA;

	/*
	 * Initialize a filter and process an image
	 */
	@Before
	public void setUp() throws Exception {
		bI = ImageIO.read(new File(
				"E:\\Windows\\EclipseWorkSpace\\SWT\\iMage.edge-detection-sobel\\src\\test\\resources\\camera_obscura.png"));
		GrayScaleFilter b = new GrayScaleFilter();
		bIA = b.applyFilter(bI);
	}

	/*
	 * Output the processed image
	 */
	@After
	public void tearDown() throws Exception {
		SimpleDateFormat sDF = new SimpleDateFormat("_HHmmss_SSS");
		String name = "GrayScaleFilter" + sDF.format(System.currentTimeMillis()).toString();
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
	 * To test whether the information of a pixel have the same value in
	 * RGB channel
	 */
	@Test
	public void testApplyFilter1() {
		int testHeight = bIA.getHeight() / 3;
		int testWidth = bIA.getWidth() / 3;
		int bIAPixel = bIA.getRGB(testWidth, testHeight);
		int r = (bIAPixel & 0x00ff0000) >> 16;
		int g = (bIAPixel & 0x0000ff00) >> 8;
		int b = (bIAPixel & 0x000000ff);
		assertEquals(r, g, b);
	}

}
