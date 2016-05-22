package jUnitTests.jUnitTest;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.imageio.ImageIO;

import org.iMage.edge.detection.sobel.filter.LowerThresholdFilter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestLowerThresholdFilter {
	BufferedImage bI;
	BufferedImage bIA;

	/*
	 * Initialize a filter and process an image
	 */
	@Before
	public void setUp() throws Exception {
		bI = ImageIO.read(new File(
				"E:\\Windows\\EclipseWorkSpace\\SWT\\iMage.edge-detection-sobel\\src\\test\\resources\\camera_obscura.png"));
		LowerThresholdFilter b = new LowerThresholdFilter();
		bIA = b.applyFilter(bI);
	}

	/*
	 * Output the processed image
	 */
	@After
	public void tearDown() throws Exception {
		SimpleDateFormat sDF = new SimpleDateFormat("_HHmmss_SSS");
		String name = "LowerThresholdFilter" + sDF.format(System.currentTimeMillis()).toString();
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
	 * To test whether the average value of the R, G, B channels from a pixel is
	 * not smaller than the threshold
	 */
	@Test
	public void testApplyFilter2() {
		int testHeight = bIA.getHeight();
		int testWidth = bIA.getHeight();
		int testPixel = 0;
		for (int i = 0; i < testHeight; i++) {
			for (int j = 0; j < testWidth; j++) {
				testPixel = bIA.getRGB(j, i) & 0x00ffffff;
				if (testPixel != 0) {
					testHeight = i;
					testWidth = j;
				}
			}
		}
		int r = (testPixel & 0x00ff0000) >> 16;
		int g = (testPixel & 0x0000ff00) >> 8;
		int b = testPixel & 0x000000ff;
		assertTrue((r + g + b) / 3 >= 127);
	}

}
