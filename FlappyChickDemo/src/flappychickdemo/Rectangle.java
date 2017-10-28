package flappychickdemo;
import java.awt.Color;

import processing.core.PApplet;

//even though we have filled out this template on how to build a Rectangle, these are only instructions
//we haven't actually built a rectangle anywhere. To do that, we have to create an instance of this class.
 

public class Rectangle {

	private PApplet p;
	private float startingX;
	private float startingY;
	private float rwidth;
	private float rheight;
	private Color rectangleColor;
	private float vx;
	
	public Rectangle(PApplet newApplet, float newStartingX, float newStartingY, float newWidth, 
			float newHeight, Color newRectangleColor) {
		p = newApplet;
		startingX = newStartingX;
		startingY = newStartingY;
		rwidth = newWidth;
		rheight = newHeight;
		rectangleColor = newRectangleColor;
		vx = -4;
	}
	
	public void Display()
	{
		p.fill(rectangleColor.getRGB());
		p.rect(startingX, startingY, rwidth, rheight);
		
	}
	
	public void Move(){
		startingX = startingX + vx;
		if(startingX + rwidth < 0) {
			startingX = p.width;
		}
	}
	
	public float getVx() {
		return vx;
	}

	public void setVx(float newVx) {
		vx = newVx;
	}
	
	public float getX(){
		return startingX;
	}
	
	public float getY(){
		return startingY;
	}
	
	public float getHeight(){
		return rheight;
	}
	
	public float getWidth(){
		return rwidth;
	}
	
	public Color getColor(){
		return rectangleColor;
	}
	
	public void setX(float newX) {
		startingX = newX;
		
	}
	public void setY(float newY){
		startingY = newY;
	}
	
	public void setColor(Color c){
		rectangleColor = c;
	}
	
	public void setWidth(float w) {
		rwidth = w;
	}

	
	
}
