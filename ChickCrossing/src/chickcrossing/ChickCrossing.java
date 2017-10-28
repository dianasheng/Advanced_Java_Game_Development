package chickcrossing;

import java.awt.Color;
import java.util.ArrayList;
import ddf.minim.*;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;
import processing.core.PImage;

public class ChickCrossing extends PApplet {
	// global variables can be accessed anywhere inside this class
	// local variables can only be accessed inside the function where it was
	// created.
	Minim minimBack;
	AudioPlayer playerBack;
	AudioInput inputBack;
	
	Minim minimFry;
	AudioPlayer playerFry;
	AudioInput inputFry;
	
	Minim minimChirp;
	AudioPlayer playerChirp;
	AudioInput inputChirp;
	
	Minim minimHorn;
	AudioPlayer playerHorn;
	AudioInput inputHorn;
	
	private PImage egg;
	private ImageBall chickBall;
	private Car Car1; // declare a Car object
	private Car Car2; // declare a Car object
	private Car Car3; // declare a Car object
	private Car Car4; // declare a Car object
	
	private ArrayList<Worm> wormList = new ArrayList<Worm>();
	private ArrayList<Car> carList = new ArrayList<Car>();
	

	// Using a user-defined class
	// 1.Declare the class as an object;
	// 2.Call the constructor
	// 3.Call functions using the "dot" notation.

	public void setup() {
		size(600, 600);
		//sound
		
		minimBack = new Minim(this);
		playerBack = minimBack.loadFile("FlappyChick.wav");
		inputBack = minimBack.getLineIn();
		
		playerBack.loop();
		
		minimFry = new Minim(this);
		playerFry = minimFry.loadFile("fry.wav");
		inputFry = minimFry.getLineIn();
		
		minimChirp = new Minim(this);
		playerChirp = minimChirp.loadFile("Chirp.mp3");
		inputChirp = minimChirp.getLineIn();

		minimHorn = new Minim(this);
		playerHorn = minimHorn.loadFile("carhorn.wav");
		inputHorn = minimHorn.getLineIn();
		
		egg = loadImage("egg.png");
		//Construct objects
		constructCars();
		chickBall = new ImageBall(this, width - 200, height - 30, 30, 
				Color.WHITE, 0,0);

		constructWorms();
	}

	public void constructCars() {
		Car1 = new Car(this, Color.PINK, width / 2, 350, 2);// call the constructor
		Car2 = new Car(this, Color.RED, width / 2, 400, 4);// call a different
															// // constructor
		Car3 = new Car(this, Color.CYAN, width / 2, 520, 2);
		Car4 = new Car(this, Color.MAGENTA, width / 2, 470, 3);
		carList.add(Car1);
		carList.add(Car2);
		carList.add(Car3);
		carList.add(Car4);
	}
	
	void constructWorms() {
		for(int i = 0; i < 10; i++){
			wormList.add(new Worm(this, random(10, width - 10), random(height/2 - 60, height - 20)));
		}
	}
	
	public void draw() {

		background(255);
		makeLandscape();
		
		chickBall.Display();
		chickBall.Move();
			
		//display worms
		for (int i = 0; i < wormList.size(); i++) {
			wormList.get(i).display();
		}
		//display car s
		for (int i = 0; i < carList.size(); i++) {
			carList.get(i).display();
		}
		//move worms
		for(Worm w:wormList) {
			w.move();
		}
		for(Car c: carList){
			c.move();
		}
		

		moveChick();
		gamePlay();
		
	}

	public void moveChick() {

		if(keyPressed && key == CODED && keyCode == UP){
			chickBall.setyPos(chickBall.getyPos() - 3);
		}
		
		if(keyPressed && key == CODED && keyCode == DOWN){
			chickBall.setyPos(chickBall.getyPos() + 3);
		}
		if(keyPressed && key == CODED && keyCode == LEFT){
			chickBall.setxPos(chickBall.getxPos() - 3);
		}
		
		if(keyPressed && key == CODED && keyCode == RIGHT){
			chickBall.setxPos(chickBall.getxPos() + 3);
		}
	}
	
	private void gamePlay() {

		//chick and worms interactions
		for(int i = 0; i < wormList.size(); i++) {
			Worm m = wormList.get(i);
			if(collideCircleandRect(chickBall,m)) {
				wormList.remove(m);
				playerChirp.play();
				playerChirp.rewind();
			}
		}
		//chick and car collide, you lose! play a sound! try again
		for(int i = 0; i < carList.size(); i++) {
			Car c = carList.get(i);
			if(collideCircleandRect(chickBall,c)){
				for(Car x:carList){
					x.setSpeed(0);
					textSize(30);
					fill(Color.red.getRGB());
					text("You lose!", width/2 - 60, 150);
					playerBack.pause();
					playerChirp.close();
					playerHorn.play();
					
				}
			}
		}
		
		//win condition
		if(wormList.size() == 0) {
			for(Car c:carList){
				c.setSpeed(0);
				textSize(30);
				fill(Color.red.getRGB());
				text("You won!", width/2 - 60, 150);
				
			}
		}
		 
		//if chick hits the sun
		if(chickBall.getxPos() < 180 && chickBall.getyPos() < 150)
		{
			System.out.println("AHHH!");
			float x = chickBall.getxPos();
			float y = chickBall.getyPos();
			image(egg, x - 22,y - 15);
			playSound(playerFry);
					
		} else {//rewindSound(playerFry);
		}
		
		
	}
	
	void driveCarActions() {

		if (keyPressed == true && key == 'r') {
			for (Car c : carList) {
				c.reverse();
			}

		} else if (keyPressed == true && key == 's') {
			for (Car c : carList) {
				Car1.stop();
			}
		}

		else {
			for (Car c : carList) {
				c.move();
			}
		}
	}
	
	private boolean collideCircleandRect(ImageBall ball, Car rect) {
		//There are four cases that we must handle
		
		//#1 - ball has crossed the left side of the rectangle
		//#2 - ball has crossed the right side of the rectangle
		//#3 - ball has crossed the top of the rectangle
		//#4 - ball has crossed the bottom of rectangle
		if(ball.getxPos() + (ball.getSize()/2) > rect.getX()  
	      && ball.getxPos() + (ball.getSize()/2) < rect.getX() + rect.getWidth() 
	      && ball.getyPos() + (ball.getSize()/2) > rect.getY() 
	      && ball.getyPos() - (ball.getSize()/2) < rect.getY() + rect.getHeight()) { 
			return true;
		} else {
			return false;
		}		
	}
	
	private boolean collideCircleandRect(ImageBall chick, Worm worm1) {
		//There are four cases that we must handle
		
		//#1 - ball has crossed the left side of the rectangle
		//#2 - ball has crossed the right side of the rectangle
		//#3 - ball has crossed the top of the rectangle
		//#4 - ball has crossed the bottom of rectangle
		if(chick.getxPos() + chick.getSize()/2  > worm1.getX()  
	      && chick.getxPos() - chick.getSize()/2 < worm1.getX() + worm1.getWidth() 
	      && chick.getyPos() + chick.getSize()/2  > worm1.getY() 
	      && chick.getyPos() - chick.getSize() < worm1.getY() + worm1.getHeight()) { 
			return true;
		} else {
			return false;
		}		
	}
	
	private void makeLandscape() {
		// make a street
		stroke(0);
		rectMode(CORNER);
		fill(Color.GRAY.getRGB());
		rect(0, height/2 +18 , width , 250);
		fill(Color.YELLOW.getRGB());
		rect(80, 440, 90, 15);
		rect(250, 440, 90, 15);
		rect(420, 440, 90, 15);
		// end street
		//grass
		fill(Color.GREEN.getRGB());
		rect(0, height - 50, width + 500, 100);
		rect(0, height/2 - 70, width + 500, 100);
		// sun
		fill(Color.CYAN.getRGB());
		rect(0, 0, width, 250);
		fill(Color.YELLOW.getRGB());
		ellipse(100, 90, 80, 80);
	}
	
	public void playSound(AudioPlayer player) 
	{
		player.play();
		player.rewind();
	}
	
}
