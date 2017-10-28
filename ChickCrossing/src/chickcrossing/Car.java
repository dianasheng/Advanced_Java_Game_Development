package chickcrossing;
import java.awt.Color;

import processing.core.PApplet;

//These are the blueprints for a Car
public class Car {
	
	//global variables
	private Color carColor;
	private float xPos;
	private float yPos;
	private float xSpeed;
	private PApplet appletCar;
	private float carBodyWidth;
	private float carBodyHeight;
	

	//constructor 
	//creates a basic object and notice bodywidth and height are no
	public Car(PApplet tempApplet, Color tempCarColor, 
			float xStart, float yStart, float tempSpeed) {
		appletCar = tempApplet;
		carColor = tempCarColor;
		xPos = xStart;
		yPos = yStart;
		xSpeed = tempSpeed;
		carBodyWidth = 30;
		carBodyHeight = 15;
	}
	
	public void move() {
		xPos = xPos + xSpeed;
		if(xPos > appletCar.width) {
			xPos = 0;
		}
	}
	
	public void display() {
		//car body
		appletCar.stroke(0);
		appletCar.strokeWeight(2);
		appletCar.fill(carColor.getRGB());
		appletCar.rectMode(appletCar.CENTER);
		appletCar.rect(xPos, yPos, carBodyWidth, carBodyHeight);
		//antenna
		appletCar.fill(Color.RED.getRGB());
		appletCar.ellipse(xPos, yPos - 20, 7, 7);
		appletCar.line(xPos , yPos -7, xPos, yPos-18);
		appletCar.fill(Color.BLACK.getRGB());
		//window and wheels
		appletCar.rect(xPos +10, yPos, 5,5);
		appletCar.ellipse(xPos + 10, yPos + 15, 18, 18);
		appletCar.ellipse(xPos - 12, yPos + 15, 18, 18);
		appletCar.fill(255);
		appletCar.ellipse(xPos + 10, yPos + 15, 6, 6);
		appletCar.ellipse(xPos - 12, yPos + 15, 6, 6);
	}
	
	public void stop() {
		xPos = xPos + 0;
	}
	
	public void reverse() {
		xPos = xPos - xSpeed;
		if (xPos + 30 < 0) {
			xPos = appletCar.width;
		}
		
	}
	
	public void setSpeed(float newSpeed){
		xSpeed = newSpeed;
	}
	
	
	public float getX() {
		// TODO Auto-generated method stub
		return xPos;
	}

	public float getY() {
		// TODO Auto-generated method stub
		return yPos;
	}

	public float getWidth() {
		// TODO Auto-generated method stub
		return carBodyWidth + 5;
	}
	
	public float getHeight(){
		return carBodyHeight + 7;
	}
	
	
	
}
