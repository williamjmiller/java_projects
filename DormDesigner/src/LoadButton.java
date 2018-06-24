import java.util.Scanner;
import java.io.*;

/* 
 * The LoadButton class reads the .ddd file coordinates to reproduce the furniture output on the GUI
 */

public class LoadButton {
	private static final int WIDTH = 96;
	private static final int HEIGHT = 32;
	 
	private PApplet processing;
	private float[] position;
	private String label;
	
	// class constructor
	public LoadButton(float x, float y, PApplet processing) {
		this.processing = processing;
		position = new float[2];
		position[0] = x;
		position[1] = y; 
		label = "Load Room";
	}
	/*
	 * Here the update method is used to make the button interactive with the user so that he knows when he is able to 
	 * click on the button
	 */
	public void update() {
		if (isMouseOver() == true) { 
			processing.fill(100);
		} else { 
			processing.fill(200);
		}
		
		processing.rect(position[0] - (WIDTH/2), position[1] + (HEIGHT/2),
				position[0] + (WIDTH/2),  position[1] - (HEIGHT/2));
		processing.fill(0);
		processing.text(label,  position[0], position[1]);
	}
	/*
	 * mouseDown hosts the functionality of the button. It first checks if it is true that the user is hovering
	 * over the button accordingly, and when pressed it reads the given file path's coordinates and outputs them to the 
	 * GUI interface
	 */
	public void mouseDown(Furniture[] furniture) { 
		if (isMouseOver()) {
			 // The name of the file to open.
	        String file = "RoomData.ddd";

	        // This will reference one line at a time
	        String line = null;
	        
	        FileInputStream fileReader = null;

	        try {
	            fileReader = new FileInputStream(file);
	            Scanner in = new Scanner(fileReader);
	            
	            int i = 0;
	            while (in.hasNextLine()) { 
	                line = in.nextLine();
	                if (line.trim().length() == 0) { 
	                	continue;
	                } else {
	                	if (furniture[i] == null) {
	                	String[] data1 = line.split(":");
	                	String[] data2 = data1[1].split(",");
	                	
	                	String type = data1[0].trim();
	                	float x = 0;
	                	float y = 0;
	                	int rot = 0;
	                	
	                	// Error #4 handling for NumberMismatchException
	                	try {             	          
	                		x = Float.parseFloat(data2[0].trim());
	                		y = Float.parseFloat(data2[1].trim());
	                		rot = Integer.parseInt(data2[2].trim());
	                		
	                	} catch (NumberFormatException e) { 
	                		System.out.println("WARNING: Found incorrectly formatted line in file: <" + line + ">");
	                		continue;
	                	}
	                		
	                		// Error #4 handling for when the furniture object image does not exist
	                		File imageFile = new File("images/" + type + ".png");
	                			if (!(imageFile.exists())) {
	                				System.out.println("WARNING: Could not find an image for a furniture object of "
	                						+ "type: <FURNITURE_NAME>");
	                				continue;
	                			}
	                			
	                	Furniture newFurniture = new Furniture(type, processing, rot, x, y);
	                	furniture[i] = newFurniture;
	                	i++;
	                	
	                	// Error #5 handling an excess amount of furniture in the furniture array
	                	if (in.hasNextLine() && i == furniture.length - 1) { 
            				System.out.println("WARNING: Unable to load more furniture.");
            				break;
            			}
	                	}
	                }
	               
	            }  

	            in.close();         
	        }
	        catch(FileNotFoundException e) {
	        	System.out.print("WARNING: Could not load room contents from file RoomData.ddd.");   
	        } finally {
	        	try {
	        		if (fileReader != null) {
						fileReader.close();
	        		}
	        	} catch (IOException e) {
	        		e.printStackTrace();
	        	}
	        }
	    }
	}
	/*
	 * isMouseOver checks if the mouse is hovering over the button's, thus allowing the user to access the button's 
	 * functionality when clicked
	 */	
	public boolean isMouseOver() { 
		if (processing.mouseX >= position[0] - WIDTH/2 & processing.mouseY >= position[1] - HEIGHT/2
			&& processing.mouseX <= position[0] + WIDTH/2 && processing.mouseY <= position[1] + HEIGHT/2) {
			return true;
		} else {
			return false;
			
		}
		
	}	
	
}

