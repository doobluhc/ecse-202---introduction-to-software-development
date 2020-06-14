import java.awt.Color;
import acm.graphics.GOval;
import acm.program.GraphicsProgram;
import acm.graphics.*;
public class gBall extends Thread{
	double Xi; //the initial X position of the center of the ball
	double Yi; //the initial Y position of the center of the ball 
	double bSize; //The radius of the ball in simulation units
	Color bColor; //The initial color of the ball
	double bLoss; //Fraction [0,1] of the energy lost on each bounce
	double bVel; //X velocity of the ball
	GOval balls;
	private static final double g = 9.8; // gravitational acceleration: unit is m's^2
	private static final double TimeOut = 1200; // unit in seconds
	private static final double IntervalTime = 0.01; // unit in seconds
	
	
	public gBall(double Xi, double Yi, double bSize, Color bColor, double bLoss, double bVel) {
		this.Xi = Xi;  //get simulation parameters
		this.Yi = Yi; 
		this.bSize = bSize; 
		this.bColor = bColor; 
		this.bLoss = bLoss; 
		this.bVel = bVel;
		
		//initialize the parameters
		//create a instance of GOval
		balls = new GOval(Xi,Yi,bSize,bSize);
		balls.setFilled(true);
		balls.setColor(bColor);
	}

	//this run function simulates the ball movement from assignment 1 
	public void run() {	
		 double h0=Yi;
		 double l=bLoss;
	  	 double totalTime=0;
	  	 boolean directionUp=false;
	  	 double height;
	  	 double time=0;
	  	 double initialUpPosition=0;
	  	 double Xposition=0;
	  	 double vx=bVel;
	  	 double vt = Math.sqrt(2*g*Yi);	
		while(totalTime < TimeOut){
			if(!directionUp) {
				height = h0 - 0.5*g*Math.pow(time,2);
				if(height<=0) {
					h0 = height;
					initialUpPosition = height;
					directionUp = true;
					time = 0;
					vt = vt * Math.sqrt(1-l);

				}
			}
			else {
				height = initialUpPosition + vt*time - 0.5*g*Math.pow(time, 2);
				if(height > h0) {
					h0 = height; //  the last highest point
				}
				else { // start to go down
					directionUp = false;
					time = 0; 

				}
			}
			Xposition = Xposition +vx*IntervalTime;
			time+=IntervalTime;
			totalTime+=IntervalTime;
			try {
	      		  // pause for 10 milliseconds
				Thread.sleep(10);
	      		  } catch (InterruptedException e) {
	      		  e.printStackTrace(); }
			if (vt==0) break;
			balls.setLocation(Xposition, 600-(height+bSize));

		}

		}
	}
