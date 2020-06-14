import java.awt.Color;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;

public class Chen_260775674_A1 extends GraphicsProgram{
	private static final double g = 9.8; // gravitational acceleration: unit is m's^2
	private static final double TimeOut = 30; // unit in seconds
	private static final double IntervalTime = 0.01; // unit in seconds
	public void run() {
		double InputHeight = readDouble("Enter the initial height of the ball in meters [0,60]: ");

		while(InputHeight <0 || InputHeight > 60 ) {
			System.out.println("Please enter a number betweeen 0 and 60");
			InputHeight = readDouble("Enter the initial height of the ball in meters [0,60]: ");	
		}


		double h0 = InputHeight*10;
		double l = readDouble("Enter the energy loss in fraction [0,1]: ");
		while(l <0 || l > 1 ) {
			System.out.println("Please enter a number betweeen 0 and 1");
			l = readDouble("Enter the energy loss in fraction [0,1]: ");	
		}
		double vt = Math.sqrt(2*g*h0);
		double totalTime = 0;
		boolean directionUp = false;
		double height; 
		double time = 0; 
		double initialUpPosition = 0;
		double Xposition = 0;
		double vx = 8;

		this.resize(1000,1000);
		GOval ball = new GOval(80,h0,60,60);
		add(ball);
		ball.setFilled(true);
		ball.setColor(Color.RED);
		GRect ground = new GRect(0,700,1200,2);
		ground.setFilled(true);
		ground.setColor(Color.BLACK);
		add(ground);

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
			System.out.println("Time: " + time + "X: " + Xposition +"Y: " + height );
			time+=IntervalTime;
			pause(IntervalTime * 300); //units in ms
			add(new GOval (Xposition+30, 670-height, 1, 1));
			ball.setLocation(Xposition, 640-height);

		}

	}

}
