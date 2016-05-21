package org.iMage.edge.detection.sobel.filter;

import java.awt.image.BufferedImage;

import org.iMage.edge.detection.base.ImageFilter;

/**
 * Implements the blur filter as requested on worksheet 2.
 */
public class BlurFilter implements ImageFilter {

	/** Default constructor must be available! */
	public BlurFilter() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public BufferedImage applyFilter(BufferedImage image) {
		int range = 1;
		int width = image.getWidth();
		int height = image.getHeight();
		int[][] temp = new int[height][width];
		// The following two loop will save the RGB information of the range in
		// the matrix and won't change them
		for (int i = 0; i < range; i++) {
			for (int j = 0; j < width; j++) {
				temp[i][j] = image.getRGB(j, i);
				temp[height - 1 - i][j] = image.getRGB(j, height - 1 - i);
			}
		}
		for (int j = 0; j < range; j++) {
			for (int i = range; i < height - range; i++) {
				temp[i][j] = image.getRGB(j, i);
				temp[i][width - 1 - j] = image.getRGB(width - 1 - j, i);
			}
		}
		// The following loop is to calculate the average value of the four
		// canals of the pixel and save them to the temporary array
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (i < range || i > height - 1 - range || j < range || j > width - 1 - range) {
					continue;
				}
				int x = i - range;
				int y = j - range;
				// Initialize an array to save all the pixel canal information
				// around a pixel
				int[] pInfo = new int[(2 * range + 1) * (2 * range + 1)];
				int index = 0;
				for (int k = 0; k <= 2 * range; k++) {
					for (int l = 0; l <= 2 * range; l++) {
						pInfo[index] = image.getRGB(y + l, x + k);
						index++;
					}
				}
				// To calculate all the canal information and save it to a
				// temporary array
				int a = 0;
				int r = 0;
				int g = 0;
				int b = 0;
				for (int k = 0; k < pInfo.length; k++) {
					int singelP = pInfo[k];
					a += (singelP & 0xff000000) >> 24;
					r += (singelP & 0x00ff0000) >> 16;
					g += (singelP & 0x0000ff00) >> 8;
					b += (singelP & 0x000000ff);
				}
				a = a / pInfo.length;
				r = r / pInfo.length;
				g = g / pInfo.length;
				b = b / pInfo.length;
				temp[i][j] = (a << 24) + (r << 16) + (g << 8) + b;
			}
		}
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				image.setRGB(j, i, temp[i][j]);
			}
		}
		return image;
	}

}
