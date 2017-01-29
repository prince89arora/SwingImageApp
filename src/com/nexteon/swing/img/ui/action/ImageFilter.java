package com.nexteon.swing.img.ui.action;

import java.io.File;

import com.nexteon.swing.img.ui.utils.Util;
/**
 * @author prince.arora
 */
public class ImageFilter {

	/**
	 * Check if selected file is an image.
	 * Returns true if is an image type
	 * (jpg, png, gif, bnp) handled.
	 *   
	 * @param fileSelected 
	 * 				File object to be checked
	 * @return
	 */
	public boolean checkFile(File fileSelected) {
		if(fileSelected.isDirectory()){
			return false;
		}else if(Util.getExtension(fileSelected).equals(Util.JPEG) ||
				Util.getExtension(fileSelected).equals(Util.JPG) ||
				Util.getExtension(fileSelected).equals(Util.PNG) ||
				Util.getExtension(fileSelected).equals(Util.GIF) ||
				Util.getExtension(fileSelected).equals(Util.BMP)){
			return true;
		}else{
			return false;
		}
	}

}
