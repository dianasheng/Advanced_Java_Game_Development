package flappychickdemo;

import java.awt.Color;

import processing.core.PApplet;
import processing.core.PImage;

public class ImageBall {
	// global variables
	private PApplet bApplet;
	private float xPos;
	private float yPos;
	private float size;
	private Color ballColor;
	private float vx;
	private float vy;
	private PImage ballImage;
	
	// constructor
	public ImageBall(PApplet newApplet, float newxPos, float newyPos, float newSize,
			Color newBallColor, float newVx, float newVy) {

		bApplet = newApplet;
		xPos = newxPos;
		yPos = newyPos;
		size = newSize;
		ballColor = newBallColor;
		vx = newVx;
		vy = newVy;
		ballImage = bApplet.loadImage("chick.png");
	}
 
	public void Display() {
		bApplet.stroke(255);
		bApplet.noStroke();
		bApplet.fill(ballColor.getRGB(),10);//transparency
		bApplet.ellipse(xPos, yPos, size, size);
		bApplet.image(ballImage, xPos - 18, yPos - 12);
		
	}

	public void Move() {
		xPos = xPos + vx;// moves ball in x-direction
		yPos = yPos + vy;// moves ball in y-direction

		/*
		if (xPos + size / 2 > bApplet.width || xPos - size / 2 < 0) {
			vx = -vx;
		}

		if (yPos + size / 2 > bApplet.height || yPos - size / 2 < 0) {
			vy = -vy;
		}*/
		
	}

	// getters - gets values of the Ball
	// requests info and gets value back
	public Color getBallColor() {
		return ballColor;
	}

	public float getVx() {
		return vx;
	}

	public float getVy() {
		return vy;
	}

	public float getSize() {
		return size;
	}

	public float getxPos() {
		return xPos;
	}

	public float getyPos() {
		return yPos;
	}

	// setters - functions to set values
	public void setBallColor(Color newColor) {
		ballColor = newColor;
	}

	public void setVx(float newVx) {
		vx = newVx;
	}

	public void setVy(float newVy) {
		vy = newVy;
	}

	public void setSize(float newSize) {
		size = newSize;
	}

	public void setxPos(float newxPos) {
		xPos = newxPos;
	}

	public void setyPos(float newyPos) {
		yPos = newyPos;
	}

}
