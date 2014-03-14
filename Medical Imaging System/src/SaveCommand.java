import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;

/**
 *
 *
 *@author Matthew Witte
 *
 *Version:
 *$Id$
 *
 *Revisions:
 *$Log$
 */

public class SaveCommand {

	private String oldName;
	private String newName;
	private static final String CONFIG_FILE = "study.cfg";
	
	/**
	 * Initializes a SaveCommand object with the old filepath
	 * and new filepath
	 * @param oldName - filepath that is being saved under a new name
	 * @param newName - new filepath name to be saved
	 */
	public SaveCommand(String oldName, String newName) {
		this.oldName = oldName;
		this.newName = newName;
	}
	
	/**
	 * Makes a new directory and copies the CFG file and all images
	 * from the old directory into the new directory.
	 * @throws Exception 
	 */
	public void save() throws Exception {
		//Makes new directory
		File newFile = new File(newName);
		newFile.mkdir();
		
		String configPath = oldName + "\\study.cfg";
		
		//Reads the old CFG file and makes a new one with all of the
		//same contents in the new directory
		PrintWriter writer = null;
		Scanner sc = null;
		try{
			sc = new Scanner(new File(configPath));
			String[] nextLine = new String[2];
			for (int i = 0; i < 2; i++) {
				if (sc.hasNextLine()) {
					nextLine[i] = sc.nextLine();
				}
			}
			
			writer = new PrintWriter(
				newFile.getAbsolutePath() + "\\" + CONFIG_FILE, "UTF-8"
			);
			writer.println(nextLine[0]);
			writer.println(nextLine[1]);
			writer.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			writer.close();
		}
		
		//Loops through all images in the old directory and copies them into new directory
		try {
			OpenCommand openCommand = new OpenCommand(new File(oldName));
			ArrayList<Image> images = openCommand.getImages();
			
			for (int i = 0; i < images.size() - 1; i++) {
				Image image = images.get(i);
				String name = image.getName();
				File file = new File(name);
				String fileName = file.getName().toLowerCase();
				String newImagePath = newFile.getAbsolutePath() + "\\" + name;
				
				if (fileName.endsWith(".jpg")) {
					ImageIO.write(image.getImage(), "jpg", new File(newImagePath));
				}
				else if (fileName.endsWith(".jpeg")) {
					ImageIO.write(image.getImage(), "jpeg", new File(newImagePath));
				}
				else if (fileName.endsWith(".acr")) {
					ImageIO.write(image.getImage(), "acr", new File(newImagePath));
				}
				else {
					throw new Exception();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
