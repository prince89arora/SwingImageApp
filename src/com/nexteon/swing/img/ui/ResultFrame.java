package com.nexteon.swing.img.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.nexteon.swing.img.ui.utils.Util;
/**
 * The Result frame to display result image and option to save
 * @author prince.arora
 */
public class ResultFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4645587615189047418L;

	JLabel resultImage = new JLabel();
	JButton close = new JButton("Close");
	JButton save = new JButton("Save");
	
	public ResultFrame(BufferedImage bufImage){
		initUI(bufImage);
	}
	
	/**
	 * Initialize the user interface with Jframe
	 * @param bufImage
	 */
	private void initUI(final BufferedImage bufImage){
		
		resultImage.setPreferredSize(new Dimension(Util.PREVIEW_W, Util.PREVIEW_h));
		JPanel panel = new JPanel();
		
		close.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				closeWindow();
			}
		});
		
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveResultImage(bufImage);
			}
		});
		setResultImage(bufImage);
		
		panel.setLayout(new FlowLayout());
		panel.add(resultImage);
		panel.add(save);
		panel.add(close);
		
		this.add(panel);
		setTitle("Image Result");
        setSize(Util.FRAME_W, Util.FRAME_h);
        setLocationRelativeTo(null);
	}
	
	/**
	 * Close current window/JFrame
	 */
	private void closeWindow(){
		this.setVisible(false);
	}
	
	/**
	 * Set outpur image after processing in panel
	 * @param bufImage
	 *            BufferedImage form image processor  
	 */
	private void setResultImage(BufferedImage bufImage){
		resultImage.setIcon(new ImageIcon(Util.resizeImage(bufImage, Util.PREVIEW_W, Util.PREVIEW_h)));
	}
	
	/**
	 * Display save dialog to user and write the ooutput
	 * Image at selected location and filename
	 * @param bufImage
	 */
	private void saveResultImage(BufferedImage bufImage){
		JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        
        int result = fileChooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION){
        	System.out.println("selected Path is: "+ fileChooser.getSelectedFile().getAbsolutePath());
        	String filePath = fileChooser.getSelectedFile().getAbsolutePath();
        	String fileFormat = Util.getExtension(fileChooser.getSelectedFile().getAbsoluteFile());
        	try{
        		ImageIO.write(bufImage, fileFormat, ImageIO
            		    .createImageOutputStream(new File(filePath)));
        		JOptionPane.showMessageDialog(new JFrame(), "File is saved successfully " 
            		    , "Output", JOptionPane.INFORMATION_MESSAGE);
        	}catch(Exception e){
        		JOptionPane.showMessageDialog(new JFrame(), "An Error occured wile saving the file: " + 
        													 e.getMessage()	, "Error", JOptionPane.ERROR_MESSAGE); 
        	}
        }
	}
	
}
