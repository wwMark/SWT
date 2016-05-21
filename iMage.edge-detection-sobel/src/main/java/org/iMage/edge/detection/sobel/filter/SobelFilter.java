package org.iMage.edge.detection.sobel.filter;

import java.awt.image.BufferedImage;

import org.iMage.edge.detection.base.ImageFilter;

/**
 * Detects edges via the Sobel filter operator.
 */
public class SobelFilter implements ImageFilter {

	/** Default constructor must be available! */
	public SobelFilter() {
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
		// The following loop is to calculate the value of the four
		// canals of the pixel with the sobelx and sobely matrix and save them
		// to the temporary array
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
				// Initialize the sobel x and y matrix
				int[][] sobelX = new int[2 * range + 1][2 * range + 1];
				int[][] sobelY = new int[2 * range + 1][2 * range + 1];
				sobelX[0][0] = -1;
				sobelX[0][1] = 0;
				sobelX[0][2] = 1;
				sobelX[1][0] = -2;
				sobelX[1][1] = 0;
				sobelX[1][2] = 2;
				sobelX[2][0] = -1;
				sobelX[2][1] = 0;
				sobelX[2][2] = 1;
				for (int k = 0; k < range; k++) {
					for (int l = 0; l < range; l++) {
						sobelY[k][l] = sobelX[l][k];
					}
				}
				// To calculate all the canal information and save it to a
				// temporary array
				int r1 = 0;
				int g1 = 0;
				int b1 = 0;
				int r2 = 0;
				int g2 = 0;
				int b2 = 0;
				int indexP = 0;
				for (int k = 0; k < range; k++) {
					for (int l = 0; l < range; l++) {
						int p = pInfo[indexP];
						int r = (p & 0x00ff0000) >> 16;
						int g = (p & 0x0000ff00) >> 8;
						int b = (p & 0x000000ff);
						r1 += r * sobelX[k][l];
						g1 += g * sobelX[k][l];
						b1 += b * sobelX[k][l];
						r2 += r * sobelY[k][l];
						g2 += g * sobelY[k][l];
						b2 += b * sobelY[k][l];
						indexP++;
					}
				}
				int rz = (int) Math.floor(Math.sqrt(r1 * r1 + r2 * r2));
				int gz = (int) Math.floor(Math.sqrt(g1 * g1 + g2 * g2));
				int bz = (int) Math.floor(Math.sqrt(b1 * b1 + b2 * b2));
				int average = (rz + gz + bz) / 3;
				int p = (image.getRGB(j, i) & 0xff000000) + (average << 16) + (average << 8) + average;
				temp[i][j] = p;
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