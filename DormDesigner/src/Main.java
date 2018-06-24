//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           DormDesigner	 
// Files:           DormDesigner.jar
// Course:          CS 300, Spring 2018 
//
// Author:          William Miller
// Email:           wmiller5@wisc.edu
// Lecturer's Name: Alexander Brooks 
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates 
// strangers, etc do.  If you received no outside help from either type of 
// source, then please explicitly indicate NONE.
//
// Persons:         (identify each person and describe their help in detail)
// Online Sources:  (identify each URL and describe their assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

public class Main {
	
	private PApplet processing;
	private PImage backgroundImage;  
	private Furniture furniture[]; 
	private CreateBedButton bedButton;
	private CreateSofaButton sofaButton;
	private LoadButton loadButton;
	private SaveButton saveButton;
	
	public Main(PApplet processing) { 
		
		this.processing = processing;
		backgroundImage = processing.loadImage("images/background.png");
		
		furniture = new Furniture[6]; // initialize array for furniture positioning
		
		for (int i = 0; i < furniture.length; i++) { // set initial values of the array to null
			furniture[i] = null;
		}
		bedButton = new CreateBedButton(50, 24, processing);
		sofaButton = new CreateSofaButton(150, 24, processing);
		saveButton = new SaveButton(650, 24, processing);
		loadButton = new LoadButton(750, 24, processing);	
	}

	public static void main(String[] args) { 
		Utility.startApplication();	  
	}
	
	/*
	 * This method allows the user to interact with the images (the furniture) in the program. The image position is updated 
	 * accordingly to maintain prompt user interaction
	 */
	
	public void update() {
		processing.background(100,150,200);
		processing.image(backgroundImage, processing.width/2, processing.height/2);
		
		bedButton.update();
		sofaButton.update();
		saveButton.update();
		loadButton.update();
		
		for (int i = 0; i < furniture.length; i++) { 
			if (furniture[i] != null) { // updates the image 
				furniture[i].update();
			
			}
		}
	}
	/*
	 * This method works when the user presses the mouse down and indicates to the program that the furniture can then 
	 * be moved accordingly
	 */
	
	public void mouseDown() { 
		for (int i = furniture.length - 1; i >= 0; i--) {
			if (furniture[i] != null) { 
				if (furniture[i].isMouseOver()) {
					furniture[i].mouseDown();
					break;
					
				}
			}
		}
		// button functionality
		for (int j = furniture.length - 1; j >= 0; j--) {
			if (furniture[j] == null) { 
				if (bedButton.mouseDown() != null) {
					furniture[j] = bedButton.mouseDown();
					break;
				
				} else if (sofaButton.mouseDown() != null) {
					furniture[j] = sofaButton.mouseDown();
					break;
				
				} else if (saveButton.isMouseOver() == true) {
					saveButton.mouseDown(furniture);
					break;
				
				} else if (loadButton.isMouseOver() == true) {
					loadButton.mouseDown(furniture);
					break;
				}
			}
		}
					
}
	/*
	 * This method releases the grip of the furniture when the user lets go of the button on the mouse
	 */
	
	public void mouseUp() { 
		for (int i = 0; i < furniture.length; i++) {
			if (furniture[i] != null) { 
				furniture[i].mouseUp();
			}
		}
	}
	/*
	 * This method allows the user to add a furniture into the room with a press of the 'b' on the keypad
	 */
	public void keyPressed() { 
		
		if (processing.key == 'D' || processing.key == 'd') { 
			for (int i = 0; i < furniture.length; i++) {
				if (furniture[i] != null) { 
					if (furniture[i].isMouseOver() == true) { 
						furniture[i] = null;
						break;
					}
				}
			}
		}
		if (processing.key == 'r' || processing.key == 'R') { 
			for (int i = 0; i < furniture.length; i++) {
				if (furniture[i] != null) { 
					if (furniture[i].isMouseOver() == true) { 
						furniture[i].rotate();
						break;
					}
						
				}
			}
		}
				
	}
}
