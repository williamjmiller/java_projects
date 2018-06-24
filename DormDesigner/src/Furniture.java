/*
 * The Furniture class creates interactive furniture objects to be manipulated by the user and implemented in the 
 * Main class
 */
public class Furniture implements DormGUI {
	
	private PApplet processing;
	private PImage image;
	private float[] position;
	private boolean isDragging;
	private int rotations;
	private String furnitureType;

	//initializes the fields of a new bed object positioned in the center of the display
	public Furniture(String furniture, PApplet processing) { 
		furnitureType = furniture;
		isDragging = false;
		rotations = 0;
		this.processing = processing;
		
		
		image = processing.loadImage("images/" + furnitureType + ".png");
			
		position = new float[2]; // initialize array for bed positioning
		
		position[0] = processing.width/2;
		position[1] = processing.height/2;
		
	}
	
	//constructor overloading...
	public Furniture(String furniture, PApplet processing, int rotations, float positionX, float positionY) { 
		furnitureType = furniture;
		isDragging = false;
		this.processing = processing;
		this.rotations = rotations;
		position = new float[2];
		position[0] = positionX;
		position[1] = positionY;
		
		image = processing.loadImage("images/" + furnitureType + ".png");
	}
	
	//draws this bed at its current position
	public void update() { 
		processing.image(image, position[0], position[1], rotations * PApplet.PI / 2);
	
		if (isDragging == true) { // stores position in array when the bed is dragged
				
				position[0] = processing.mouseX;
				position[1] = processing.mouseY;
		}
	}
	// Accessor methods... (used to aid the saveButton class)
	
	public float getXPosition() { 
		return position[0];
	}

	public float getYPosition() { 
		return position[1];
	}
	
	public int getOrientation() { 
		return rotations;
	}
	
	public String getType() { 
		return furnitureType;
	}
	
	//used to start dragging the bed, when the mouse is over this bed when it is pressed
	public void mouseDown(Furniture[] furniture) { 
		if (isMouseOver() == true) { 
			isDragging = true;
			position[0] = processing.mouseX;
			position[1] = processing.mouseY;
		}
	}
	
	//used to indicate that the bed is no longer being dragged
	public void mouseUp() { 
		isDragging = false;
		
	}
	
	//helper method to determine whether the mouse is currently over this bed
	public boolean isMouseOver() { 		
		if (rotations % 2 == 0 || rotations == 0) {
			if (processing.mouseX >= position[0] - image.width/2 &&
					processing.mouseY >= position[1] - image.height/2 &&
					processing.mouseX <= position[0] + image.width/2 &&
					processing.mouseY <= position[1] + image.height/2) { 
				return true;
			}
		
		} else if (rotations % 2 == 1) {
			if (processing.mouseX >= position[0] - image.height/2 &&
					processing.mouseY >= position[1] - image.width/2 &&
					processing.mouseX <= position[0] + image.height/2 &&
					processing.mouseY <= position[1] + image.width/2) { 
				return true;
			}
		}
		return false;
	}
	public void rotate() { 
		rotations += 1;
	}
}


	
