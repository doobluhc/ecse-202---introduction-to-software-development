import java.awt.Color;
import acm.graphics.GObject;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;
public class bSim extends GraphicsProgram{
	private static final int WIDTH = 1200; // n.b. screen coords
		private static final int HEIGHT = 600; 
		private static final int OFFSET = 200; 
		private static final int NUMBALLS = 100; // # balls to sim.
		private static final double MINSIZE = 3; // Min ball size
		private static final double MAXSIZE = 20; // Max ball size
		private static final double XMIN = 10; // Min X start loc
		private static final double XMAX = 50; // Max X start loc
		private static final double YMIN = 50; // Min Y start loc
		private static final double YMAX = 100; // Max Y start loc
		private static final double EMIN = 0.1; // Min loss coeff.
		private static final double EMAX = 0.3; // Max loss coeff.
		private static final double VMIN = 0.5; // Min X velocity
		private static final double VMAX = 3.0; // Max Y velocity
		//random generator
		private RandomGenerator rgen = RandomGenerator.getInstance();
		
		
		public void run() {
			this.resize(WIDTH,HEIGHT);// resize the display screen
			//draw the gound
			GRect ground = new GRect(0,600,1200,2);
			ground.setFilled(true);
			ground.setColor(Color.BLACK);
			add(ground);
			
			gBall[] myball = new gBall [NUMBALLS];
			
			// randomly generate parameters and draw the ball
			for(int i=0; i< myball.length; i++) {
			double Yi = rgen.nextDouble(YMIN,YMAX);
			double bSize = rgen.nextDouble(MINSIZE,MAXSIZE);
			double Xi = rgen.nextDouble(XMIN,XMAX);
			double bLoss = rgen.nextDouble(EMIN,EMAX);
			double bVel = rgen.nextDouble(VMIN,VMAX);
			Color bColor = rgen.nextColor();	
			myball[i] = new gBall(Xi,Yi,bSize,bColor,bLoss,bVel);
			}
			for(int i=0; i< myball.length; i++)
			{	
			add(myball[i].balls);
			myball[i].start();
			}
			
		}      


}
