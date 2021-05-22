package utils;

import java.nio.IntBuffer;

import javafx.scene.image.Image;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.WritableImage;
import javafx.scene.image.WritablePixelFormat;

/**
 * Utility for font
 */
public class ImageUtil {
	/**
	 * Load image from link
	 * @param url path to picture
	 * @return Image object at path
	 */
	public static Image ImageLoader(String url) {
		return new Image(ClassLoader.getSystemResource(url).toString());
	}
	/**
	 * Load image from link with size
	 * @param url path to picture
	 * @param size At most size of picture
	 * @return Image object at path
	 */
	public static Image ImageLoader(String url, int size) {
		Image img = new Image(ClassLoader.getSystemResource(url).toString());
		return scale(img, size);
	}
	
	/**
	 * To scale pixel art without blur
	 * @param image Image to scale
	 * @param size At most size of picture
	 * @return Scaled pixel art or image
	 */
	public static Image scale(Image image, int size) {
		int width = (int)image.getWidth();
		int height = (int)image.getHeight();
		int z = size/ Math.min(width,height) + 1;// 2, 4, 8, 16 (I only tested for powers of two)
		IntBuffer src = IntBuffer.allocate(width * height);
		WritablePixelFormat<IntBuffer> pf = PixelFormat.getIntArgbInstance();
		image.getPixelReader().getPixels(0, 0, width, height, pf, src, width);
		int newWidth = width * z;
		int newHeight = height * z;
		int[] dst = new int[newWidth * newHeight];
		int index = 0;
		for (int y = 0; y < height; y++) {
		index = y * newWidth * z;
		for (int x = 0; x < width; x++) {
		int pixel = src.get();
		for (int i = 0; i < z; i++) {
		for (int j = 0; j < z; j++) {
		dst[index + i + (newWidth * j)] = pixel;
		}
		}
		index += z;
		}
		}
		WritableImage bigImage = new WritableImage(newWidth, newHeight);
		bigImage.getPixelWriter().setPixels(0, 0, newWidth, newHeight, pf, dst, 0, newWidth);
		return (Image) bigImage;
	}
}
