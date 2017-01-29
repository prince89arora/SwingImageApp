package com.nexteon.swing.img.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.nexteon.swing.img.ui.action.ImageFilter;
import com.nexteon.swing.img.ui.action.ImageProcess;
import com.nexteon.swing.img.ui.utils.Util;
/**
 * Creates swing frame with menu bar and button to process image.
 * Menu items will be Open and Exit. Process button can be
 * used to process the image selected.    
 * 
 * @author prince.arora
 *
 */
public class SwingBaseFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6627341144927762730L;

	 
	JLabel label = new JLabel();
	JButton processButton = new JButton("Process");
	JLabel imagePreview = new JLabel("Image Prieview", SwingConstants.CENTER);

	JPanel browsePanel = new JPanel();

	public SwingBaseFrame(){
		initFrame();
	}
    
	/**
	 * Initializing the JFrame for visiblity and apperance
	 */
	private void initFrame(){
		createLayout();
		setTitle("Image Processing");
        setSize(Util.FRAME_W, Util.FRAME_h);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
    /**
     * Creating layout for JFrame and adding all the comonents
     * with Layout manager.
     */
	private void createLayout(){
		processButton.addActionListener(new ActionsHandler());
		imagePreview.setPreferredSize(new Dimension(Util.PREVIEW_W, Util.PREVIEW_h));
			
		browsePanel.setLayout(new FlowLayout());
		label.setVisible(false);
		browsePanel.add(label);
		browsePanel.add(imagePreview);
		this.setJMenuBar(createMenu());
		browsePanel.add(processButton);
		
		this.add(browsePanel);
	}
	
	/**
	 * Creating menu bar with Open and Exit menu item
	 * Open will be used to browse a file.
	 * Exit will be used to exit from application.
	 *    
	 * @return JMenuBar
	 */
	private JMenuBar createMenu(){
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu();
		menu.setText("File");
		
		JMenuItem open = new JMenuItem("open");
		open.setToolTipText("Open An Image");
		open.addActionListener(new ActionsHandler());
		
		JMenuItem exit = new JMenuItem("exit");
		exit.setToolTipText("Exit Application");
		exit.addActionListener(new ActionsHandler());
		
		menu.add(open);
		menu.add(exit);
		menuBar.add(menu);
		return menuBar;
	}
	
	/**
	 * Action handler to handle Browse, Exit and Process 
	 * @author prince.arora
	 */
	class ActionsHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {
			case "Process": processSelectedImage();
				break;
				
			case "open" : try {
					showOpenFileDialog();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			    break;
            
			case "exit" : System.exit(0);
				break;
				
			default:
				break;
			}
		}
		
		/**
		 * Show open dialog box to browse and select an image
		 * when open menu item is clicked.
		 * 
		 * @throws IOException
		 */
		private void showOpenFileDialog() throws IOException {
	        JFileChooser fileChooser = new JFileChooser();
	        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
	        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
	        
	        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Images (jpg, png, gif, bmp)", "jpg", "png", "gif", "bmp"));
	        fileChooser.setAcceptAllFileFilterUsed(true);
	        int result = fileChooser.showOpenDialog(fileChooser);
	        if (result == JFileChooser.APPROVE_OPTION) {
	            File selectedFile = fileChooser.getSelectedFile();
	            /*
	             * Removed validation for now
	             */
	            //if(new ImageFilter().checkFile(selectedFile)){
            	label.setText(selectedFile.getAbsolutePath());
	            imagePreview.setText("");
	            if(ImageIO.read(selectedFile).getWidth() > imagePreview.getWidth()){
	            	imagePreview.setIcon(new ImageIcon(Util.resizeImage(selectedFile, Util.PREVIEW_W, Util.PREVIEW_h)));
	            }else{
	            	imagePreview.setIcon(new ImageIcon(selectedFile.getAbsolutePath()));
	            }
	            //}else{
	            	//JOptionPane.showMessageDialog(new JFrame(), "Please select an image", "Error", JOptionPane.ERROR_MESSAGE);
	            //}
	        }else{
	        }
	    }
		
		/**
		 * Process Image further 
		 */
		private void processSelectedImage(){
			BufferedImage bimage = null;
			if(!label.getText().isEmpty()){
				ImageProcess processonr = new ImageProcess();
				try {
					bimage = processonr.initProcess(label.getText());
				} catch (IOException e) {
					e.printStackTrace();
				}
				new ResultFrame(bimage).setVisible(true);
			}else{
            	JOptionPane.showMessageDialog(new JFrame(), "Please select an image", "Error", JOptionPane.ERROR_MESSAGE);
            }
		}
		
	}
	
}
