/*
 * The CreateBedButton method creates the bed button and functionality of the bed button to generate a bed in the room
 */
public class CreateBedButton {
	private static final int WIDTH = 96;
	private static final int HEIGHT = 32;
	 
	private PApplet processing;
	private float[] position;
	private String label;

	//Constructor method for the class 
	public CreateBedButton(float x, float y, PApplet processing) {
		this.processing = processing;
		position = new float[2];
		position[0] = x;
		position[1] = y; 
		label = "Create Bed";
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
	 * mouseDown hosts the functionality of the button. It first checks if it is true that the use is hovering
	 * over the button accordingly, and when pressed it creates a new "bed" furniture object with the help of the 
	 * furniture class
	 */
	public Furniture mouseDown() { 
		if (isMouseOver()) {
			Furniture furniture = new Furniture("bed", processing);
			return furniture;
		}
		else {
			Furniture furniture = null;
			return furniture;
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
