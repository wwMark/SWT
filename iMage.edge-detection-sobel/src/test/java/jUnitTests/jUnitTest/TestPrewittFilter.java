package jUnitTest;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.imageio.ImageIO;

import org.iMage.edge.detection.sobel.filter.PrewittFilter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestPrewittFilter {
	BufferedImage bI;
	BufferedImage bIA;

	@Before
	public void setUp() throws Exception {
		bI = ImageIO.read(new File(
				"E:\\Windows\\EclipseWorkSpace\\SWT\\iMage.edge-detection-sobel\\src\\test\\resources\\camera_obscura.png"));
		PrewittFilter b = new PrewittFilter();
		bIA = b.applyFilter(bI);
	}

	@After
	public void tearDown() throws Exception {
		SimpleDateFormat sDF = new SimpleDateFormat("_HHmmss_SSS");
		String name = "PrewittFilter" + sDF.format(System.currentTimeMillis()).toString();
		File file = new File("E:\\Windows\\EclipseWorkSpace\\SWT\\iMage.edge-detection-sobel\\src\\test\\resources\\"
				+ name + ".png");
		if (bI != null) {
			try {
				ImageIO.write(bIA, "png", file);
			} catch (IOException e) {
				System.out.println("There's something goes wrong!");
			}
		}
	}

	@Test
	public void testApplyFilter1() {
		assertEquals(bI.getHeight(), bIA.getHeight());
	}
	
	@Test
	public void testApplyFilter2() {
		assertEquals(bI.getWidth(), bIA.getWidth());
	}

}
