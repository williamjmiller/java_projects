import java.io.*; 
/*
 * SaveButton is used to save the dorm room created by the user in a .ddd file. The coordinates of the furniture objects
 * are saved so that they can be loaded later on.
 */
public class SaveButton {
	private static final int WIDTH = 96;
	private static final int HEIGHT = 32;
	 
	private PApplet processing;
	private float[] position;
	private String label;

	// constructor method for the class
	public SaveButton(float x, float y, PApplet processing) {
		this.processing = processing;
		position = new float[2];
		position[0] = x;
		position[1] = y; 
		label = "Save Room";
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
	 * over the button accordingly, and when pressed it creates a new .ddd file stored with the coordinates of the 
	 * furniture in the room.
	 */
	public void mouseDown(Furniture[] furniture) { 
		if (isMouseOver()) {
			FileOutputStream fop = null;
			PrintWriter pw = null;
			
			try { 
				
				File file = new File("RoomData.ddd");
				fop = new FileOutputStream(file);
				pw = new PrintWriter(fop);
				
				for (int i = 0; i < furniture.length; i++) {
					if (furniture[i] != null) {
						pw.println(furniture[i].getType() + ":" + furniture[i].getXPosition() + "," + 
							furniture[i].getYPosition() + "," + furniture[i].getOrientation());
					} else {
						pw.println();
					}
				}
			
			} catch (IOException e) {
				System.out.print("WARNING: Could not save room contents to file RoomData.ddd.");
			} finally {
				if (pw != null) {
					pw.flush();
					pw.close(); // the PrintWriter needs to be closed to oproduce output
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

