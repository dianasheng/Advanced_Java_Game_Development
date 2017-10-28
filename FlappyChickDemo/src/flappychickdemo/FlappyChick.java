package flappychickdemo;

import java.awt.Color;
import java.util.ArrayList;

import processing.core.PApplet;

public class FlappyChick extends PApplet {

	private ArrayList<Rectangle> pipes = new ArrayList<Rectangle>();
	private ImageBall chick = new ImageBall(this, width/2, height/2, 34, Color.BLACK, 0,
			3);
	private boolean start = false;
	private int score = 0;
	private float upKeyVelocity = -7;
	
	public void setup() {
		size(800, 600);
		constructStumps();
		startUp();  

	}

	public void keyPressed() {
		if (key == 's') {
			start = true;
		}
	}

	public void startUp() {
		background(Color.CYAN.getRGB());
		for (Rectangle r : pipes) {
			r.Display();
		}
		chick.setxPos(width/2);
		chick.setyPos(height/2);
		chick.Display();
		textSize(20);
		fill(Color.BLUE.getRGB());
		text("Press 's' to start", width / 2 - 100, height / 2 + 100);
		
		
		// score should increase when a rectangle moves to the left of the screen
	}

	public void draw() {

		if (start) {
			background(Color.CYAN.getRGB());
			
			
			chick.Display();
			for (Rectangle r : pipes) {
				r.Display();
			}
			for (Rectangle r : pipes) {
				r.Move();
				if(r.getX() == 0) {
					score++;
				}
			}
			fill(Color.RED.getRGB());
			text("Score: " + score, 20,20);

			chick.Move();
			moveChickUp();
			runGamePlay();
		}

	}

	public void constructStumps() {
		for (int i = 0; i < 5; i++) {
			pipes.add(new Rectangle(this, 150 * i, 0, 50, random(100, 300),
					Color.BLACK));
		}

		for (int j = 0; j < 5; j++) {
			float rHeight = random(130, 250);
			pipes.add(new Rectangle(this, 150 * j + 50, height - rHeight, 50,
					rHeight, Color.black));
		}
	}

	public void reStart() {
		System.out.println("you lose");
		fill(0);	
		chick.setxPos(width/2);
		chick.setyPos(height/2);
		text("Press 's' to restart", width / 2 - 100, height / 2);
		score = 0;
		start = false;
	}
	
	public void runGamePlay() {
		for (int i = 0; i < pipes.size(); i++) {
			Rectangle r = pipes.get(i);
			if (collideCircleandRect(chick, r)) {
				reStart();
			}
		}
		
	}

	public void moveChickUp() {

		if (keyPressed && key == ' ' || (keyPressed && key == CODED && keyCode == UP)) {
			chick.setyPos(chick.getyPos() + upKeyVelocity);
		}
	}

	private boolean collideCircleandRect(ImageBall ball, Rectangle rect) {
		// There are four cases that we must handle

		// #1 - ball has crossed the left side of the rectangle
		// #2 - ball has crossed the right side of the rectangle
		// #3 - ball has crossed the top of the rectangle
		// #4 - ball has crossed the bottom of rectangle
		if (ball.getxPos() + (ball.getSize() / 2) > rect.getX()
				&& ball.getxPos() + (ball.getSize() / 2) < rect.getX()
						+ rect.getWidth()
				&& ball.getyPos() + (ball.getSize() / 2) > rect.getY()
				&& ball.getyPos() - (ball.getSize() / 2) < rect.getY()
						+ rect.getHeight()) {
			return true;
		} else {
			return false;
		}
	}
}
