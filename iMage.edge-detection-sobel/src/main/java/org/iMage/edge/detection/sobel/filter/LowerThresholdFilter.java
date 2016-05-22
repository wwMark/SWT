package org.iMage.edge.detection.sobel.filter;

import java.awt.image.BufferedImage;

import org.iMage.edge.detection.base.ImageFilter;

/**
 * Filters all pixels that have a grayscale color below a certain threshold and
 * sets them to 0 (makes them black). Pixels above the threshold are converted
 * to grayscale normally (as defined in {@link GrayScaleFilter}).
 */
public class LowerThresholdFilter implements ImageFilter {

	/** Default constructor must be available! */
	public LowerThresholdFilter() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * To change the pixel which is unter the threshold into black pixel
	 * 
	 * @param image the image to be processed
	 * 
	 * @return image return a processed image
	 */
	public BufferedImage applyFilter(BufferedImage image) {
		int height = image.getHeight();
		int width = image.getWidth();
		int threshold = 127;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				int old = image.getRGB(j, i);
				// Get the A, R, G, B information from the integer "old" through
				// bit manipulation
				int a = old & 0xff000000;
				int r = (old & 0x00ff0000) >> 16;
				int g = (old & 0x0000ff00) >> 8;
				int b = old & 0x000000ff;
				int average = (r + g + b) / 3;
				// If the result is lower than the threshold, denn it will be
				// turn into a black pixel, otherwise it will be kept in the
				// image
				if (average < threshold) {
					image.setRGB(j, i, a);
				} else {
					a += average << 16;
					a += average << 8;
					a += average;
					image.setRGB(j, i, a);
				}
			}
		}
		return image;
	}
}
