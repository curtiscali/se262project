import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class ImageWindowCommand extends UndoableCommand implements Command {
	private ArrayList<Image> imagesToManipulate;
	private int lowCutoff, highCutoff;
	private ArrayList<BufferedImage> manipulatedImages;
	
	public ImageWindowCommand(int low, int high, ArrayList<Image> images) {
		this.lowCutoff = low;
		this.highCutoff = high;
		this.imagesToManipulate = images;
		this.manipulatedImages = new ArrayList<BufferedImage>();
	}
	
	@Override
	public void execute() {
		for (Image img : this.imagesToManipulate) {
			BufferedImage imgData = (BufferedImage) img.getImages().get(0);
			
			for(int x = 0; x < imgData.getWidth(); x++) {
				for(int y = 0; y < imgData.getHeight(); y++) {
					Color color = new Color(imgData.getRGB(x, y));
					
					int redIntensity = color.getRed();
					int greenIntensity = color.getGreen();
					int blueIntensity = color.getBlue();
					int newRed;
					int newGreen;
					int newBlue;
						
					if(redIntensity < this.lowCutoff) {
						newRed = 0;
					} else if(redIntensity > this.highCutoff) {
						newRed = 255;
					} else {
						newRed = ((this.highCutoff - this.lowCutoff) * (redIntensity / 255)) + this.lowCutoff;
					}
					
					if(greenIntensity < this.lowCutoff) {
						newGreen = 0;
					} else if(greenIntensity > this.highCutoff) {
						newGreen = 255;
					} else {
						newGreen = ((this.highCutoff - this.lowCutoff) * (greenIntensity / 255)) + this.lowCutoff;
					}
					
					if(blueIntensity < this.lowCutoff) {
						newBlue = 0;
					} else if(blueIntensity > this.highCutoff) {
						newBlue = 255;
					} else {
						newBlue = ((this.highCutoff - this.lowCutoff) * (blueIntensity / 255)) + this.lowCutoff;
					}
					
					Color newColor = new Color(newRed, newGreen, newBlue);
					int rgb = newColor.getRGB();
					imgData.setRGB(x, y, rgb);
				}
			}
			manipulatedImages.add(imgData);
		}

	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		
	}
	
	public ArrayList<BufferedImage> getWindowedImages() {
		return this.manipulatedImages;
	}
}