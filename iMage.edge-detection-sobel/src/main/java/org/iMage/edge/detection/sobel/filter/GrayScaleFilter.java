package org.iMage.edge.detection.sobel.filter;

import java.awt.image.BufferedImage;

import org.iMage.edge.detection.base.ImageFilter;

/**
 * Implements the GrayScaleFilter as requested on worksheet 2.
 */
public class GrayScaleFilter implements ImageFilter {

	/** Default constructor must be available! */
	public GrayScaleFilter() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * To change an image into an gray scale image
	 * 
	 * @param image the image to be processed
	 * 
	 * @return image return a processed image
	 */
	public BufferedImage applyFilter(BufferedImage image) {
		int height = image.getHeight();
		int width = image.getWidth();
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
				// The information of Alpha canal will be kept
				a += average << 16;
				a += average << 8;
				a += average;
				image.setRGB(j, i, a);
			}
		}
		return image;
	}
}
