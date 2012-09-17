import processing.core.*;

public class PSketch extends PApplet {
	private static final long serialVersionUID = 1L;
	public static void main(String args[]) {
		PApplet.main(new String[] { "--present", "PSketch" });
	}
	
	int swidth, sheight;
	public void setup() {
		swidth = 400; sheight = 400;
		size(swidth, sheight);
		colorMode(HSB,100);
		
	}
	
	public void draw() {
		
	}
}
