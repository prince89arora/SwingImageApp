package com.nexteon.swing.img.ui.action;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
/**
 * Main Image processing handler
 * @author rakesh.kumar
 */
public class ImageProcess {

	public BufferedImage initProcess(String filePath) throws IOException{
		
		  BufferedImage sourceImage = ImageIO.read(new File(filePath));
		  
		  float[] factors = new float[] {
		       1.4f, 1.4f, 1.4f
		   };
		   float[] offsets = new float[] {
		       0.0f, 0.0f, 30.0f
		   };
		  
		  RescaleOp rescaleOp = new RescaleOp(/*scalefactor:contrast*/2.7f, /*offset:brightness*/70.0f, null);
		  sourceImage = rescaleOp.filter(sourceImage, null);
		  
		  float[] sharpen = new float[] {
		    0.0f, -1.0f, 0.0f,
		    -1.0f, 5.0f, -1.0f,
		    0.0f, -1.0f, 0.0f
		   };
		  BufferedImage output = new
		    BufferedImage(sourceImage.getWidth(),sourceImage.getHeight(),sourceImage.getType());
		  
		  Graphics2D g2 = output.createGraphics();
		  g2.drawImage(sourceImage, 0, 0, null);
		  g2.dispose();
		  
		  Kernel kernel = new Kernel(3, 3, sharpen);
		  
		  BufferedImageOp op = new ConvolveOp(kernel,ConvolveOp.EDGE_NO_OP, null);

		  BufferedImage dstImage = op.filter(output, null);
		 
		  return dstImage;
	}
	
}
