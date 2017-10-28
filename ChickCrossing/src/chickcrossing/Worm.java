 package chickcrossing;
import java.awt.Color;

import processing.core.PApplet;
import processing.core.PImage;

//even though we have filled out this template on how to build a Rectangle, these are only instructions
//we haven't actually built a rectangle anywhere. To do that, we have to create an instance of this class.
// we'll do this in the Shapes class. 

public class Worm{

	private PApplet p;
	private float startingX;
	private float startingY;
	private float width;
	private float height;
	PImage wormImage;
	private float vx;
	
	public Worm(PApplet newApplet, float newStartingX, float newStartingY) {
		p = newApplet;
		startingX = newStartingX;
		startingY = newStartingY;
		width = 15;
		height = 10;
		wormImage = p.loadImage("worm.png");
		vx = .5f;
	}
	
	public void display()
	{
		p.image(wormImage, startingX, startingY);
		
	}
	
	public void move() {
		startingX = (float) (startingX - vx);
		if (startingX < 0) {
			startingX = p.width;
		}
	}
	
	public float getX(){
		return startingX;
	}
	
	public float getY(){
		return startingY;
	}
	
	public float getHeight(){
		return height;
	}
	
	public float getWidth(){
		return width;
	}
	
	public void setX(float newX) {
		startingX = newX;
		
	}
	public void setY(float newY){
		startingY = newY;
	}
	
	public void setWidth(float w) {
		width = w;
	}	
}
