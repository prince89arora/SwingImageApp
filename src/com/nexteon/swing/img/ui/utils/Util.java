package com.nexteon.swing.img.ui.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
/**
 * Utility class for image or file processing.
 * @author prince.arora
 */
public class Util {

	public static String JPEG = "jpeg";
	public static String JPG = "jpg";
	public static String PNG = "png";
	public static String GIF = "gif";
	public static String BMP = "bmp";
	
	public static int FRAME_W = 800;
	public static int FRAME_h = 700;
	public static int PREVIEW_W = 800;
	public static int PREVIEW_h = 500;
	
	/**
	 * Get Extension for any file type object.
	 * @param file
	 *         A File object to get extension
	 * @return String
	 */
	public static String getExtension(File file){
		String name = file.getName();
		return name.substring(name.lastIndexOf(".") + 1, name.length()); 
	}
	
	/**
	 * Resize any image from File object with provided
	 * width and height.
	 *  
	 * @param file
	 *          The File Object
	 * @param width
	 *          width to be set for image
	 * @param height
	 *          height to be set for image 
	 * @return Image
	 */
	public static Image resizeImage(File file, int width, int height){
		BufferedImage img = null;
		try {
		    img = ImageIO.read(file);
		} catch (IOException e) {
		    e.printStackTrace();
		}
		int diff = img.getWidth() - width;
		height = img.getHeight() - diff;
		
		return img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	}
	
	/**
	 * Resize any image from BufferedImage with provided
	 * width and height.
	 *  
	 * @param img
	 *          The BufferedImage
	 * @param width
	 *          width to be set for image
	 * @param height
	 *          height to be set for image 
	 * @return Image
	 */
	public static Image resizeImage(BufferedImage img, int width, int height){
		int diff = img.getWidth() - width;
		height = img.getHeight() - diff;
		return img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	}
}
