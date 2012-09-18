import hypermedia.video.OpenCV;

import java.awt.Rectangle;
import java.util.ArrayList;
import netP5.NetAddress;
import oscP5.OscMessage;
import oscP5.OscP5;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

/**
 * Client that can detect faces via OpenCV. Displays it's computer vision in a applet window.
 * NOTE, that the run configuration for this applet *MUST* have -d32 as a VM Argument to run as 32bit!
 * Otherwise, there will be this error at runtime:
 *     !!! required library not found : /Users/zlot/github/Eclipse Workspace/DC32_MConvo/libraries/OpenCV/libOpenCV.jnilib:  no suitable image found.  Did find:  /Users/zlot/github/Eclipse Workspace/DC32_MConvo/libraries/OpenCV/libOpenCV.jnilib: no matching architecture in universal wrapper
 *     Verify that the java.library.path property is correctly set and the opencv.framework exists in '/Library/Frameworks' folder
 *     Exception in thread "Animation Thread" java.lang.UnsatisfiedLinkError: hypermedia.video.OpenCV.capture(III)V
 * Also, in build path for OpenCV.jar, Native Library Location must be set to relative folder {{project name}}/libraries/OpenCV
 * 
 * @author zlot
 *
 */
public class Client_OpenCV extends PApplet {
	private static final long serialVersionUID = 1L;

	public static void main(String args[]) {
		PApplet.main(new String[] { "--present", "PSketch" });
	}

	// Core Variables
	int swidth, sheight;
	final int FRAME_RATE = 30;
	int rootFrame = 0;
	PFont font;
	int fontSize = 12;
	// Communication
	OscP5 oscP5;
	NetAddress myTargetLocation;
	int target_port = 12000;
	String target_ip0 = "127.0.0.1";
	String target_ip1 = "192.168.0.194";
	String target_ip2 = "172.19.19.109";
	String target_ip = target_ip0;
	int home_port = 13000 + (int) random(1000);
	String home_ip;
	// OpenCV
	OpenCV opencv;
	int opencv_contrast = 120;
	int opencv_brightness = 54;
	boolean humanIsPresent = false;

	
	public void setup() {
		swidth = 320;
		sheight = 240;
		size(swidth, sheight);
		colorMode(HSB, 100);
		smooth();
	    noFill();
	    frameRate(FRAME_RATE);

	    // init communication
	    oscP5 = new OscP5(this, home_port);
	    home_ip = oscP5.ip();
	    myTargetLocation = new NetAddress(target_ip, target_port);
	    this.sendMyLocationToTarget();
	    // init font
	    font = new PFont(); font = loadFont("../resources/AndaleMono-12.vlw"); textFont(font, fontSize);	    
		// init openCV
		opencv = new OpenCV(this);
		opencv.capture(320, 240); // open video stream
	    opencv.cascade( OpenCV.CASCADE_FRONTALFACE_ALT );  // load detection description, here-> front face detection : "haarcascade_frontalface_alt.xml"

	}

	// -----------------------------------------------------------------------------
	public void draw() {
		drawOpenCV();
	}
	
	// -----------------------------------------------------------------------------
	// OPENCV.
	public void drawOpenCV() {
		opencv.read();
		opencv.convert(GRAY);
		// detect faces
		Rectangle[] faces = opencv.detect(1.2f, 2, OpenCV.HAAR_DO_CANNY_PRUNING, 40, 40);
		opencv.contrast(opencv_contrast);
		opencv.brightness(opencv_brightness);
		// display the image
		// if human detected, tint red, slowly fade tint out
		PImage img = opencv.image();
//		image(img, 0, 0);
//		tint(100,100,100,100-(timeout/FRAME_RATE*6));
		image(img, 0, 0);	
		// draw face area(s)
		pushStyle();
		stroke(100,100,50);
		for(int i=0; i<faces.length; i++) {
			rect(faces[i].x, faces[i].y, faces[i].width, faces[i].height); 
		}
		popStyle();
		checkForHuman(faces);
	}

	int timeout = 0;
	public void checkForHuman(Rectangle[] faces) {
		// check faces array, if not empty (more than 0), send panic msg to server
		if(!humanIsPresent && faces.length != 0) {
			sendHumanIsPresent("0 \t How unfortunate. A human comes close. \t 3 \n");
			humanIsPresent = true;
		}
		// if faces.length is empty for more than 6 seconds (arbitrary atm), then reset humanIsPresent alert.
		if(faces.length == 0) {
			timeout++;
			textToScreen(timeout);
			if(timeout > FRAME_RATE*6) {
				humanIsPresent = false;
				timeout = 0;
			}
		} else { // face appeared again, reset the timeout
			timeout = 0;
		}
	}
	
	
	public void stop() {
	    opencv.stop();
	    super.stop();
	}
	
	
	public void mouseDragged() {
	    opencv_contrast   = (int) map(mouseX, 0, width, -128, 128);
	    opencv_brightness = (int) map(mouseY, 0, width, -128, 128);
	}
	
	
	// -----------------------------------------------------------------------------
	// COMMUNICATION.
	public void sendMyLocationToTarget() {
		OscMessage myMessage = new OscMessage("/myLocation");
		myMessage.add(home_ip);
		myMessage.add(home_port);
		oscP5.send(myMessage, myTargetLocation);
	}
	// for some reason, OSC isn't liking sending boolean values via OscMessage at all.
//	public void sendHumanPresent(boolean humanPresent) {
//		OscMessage myMessage = new OscMessage("/humanDetection");
//		myMessage.add(true);
//		oscP5.send(myMessage, myTargetLocation);
//	}
	/**
	 * Send alert to server that a human has been detected.
	 * 
	 * @param humanIsPresent
	 */
	public void sendHumanIsPresent(String humanIsPresent) {
		OscMessage myMessage = new OscMessage("/humanDetection");
		myMessage.add(humanIsPresent);
		oscP5.send(myMessage, myTargetLocation);
	}
	
	
	/**
	 * Communications. OSC listener that handles incoming messages.
	 * @param theOscMessage incoming message to be parsed.
	 */
	public void oscEvent(OscMessage theOscMessage) {
		String addrPattern = theOscMessage.addrPattern();

	}
	
	// -----------------------------------------------------------------------------
	// HELPER METHODS
	/**
	 * Helper method for drawing text to screen. Send in a mixed arraylist, it doesn't mind.
	 * @param a ArrayList of mixed types to be printed to screen
	 */
	public void textToScreen(ArrayList a) {
		pushStyle();
		fill(40,100,50);
		int textYAxis = 20;
		for(Object o : a) {
			if (o instanceof String) {
				text((String) o, 20, textYAxis);
			}
			if (o instanceof Integer) {
				text((Integer) o, 20, textYAxis);
			}
			if (o instanceof Float) {
				text((Float) o, 20, textYAxis);
			}
			if (o instanceof Boolean) {
				text(((Boolean) o).toString(), 20, textYAxis);
			}
			textYAxis += fontSize+5;
		}
		popStyle();
	}
	/**
	 * Helper method for drawing text to screen.
	 * @param o Object of type String, int, boolean or float.
	 */
	public void textToScreen(Object o) {
		pushStyle();
		fill(40,100,50);
		int textYAxis = 20;
			if (o instanceof String) {
				text((String) o, 20, textYAxis);
			}
			if (o instanceof Integer) {
				text((Integer) o, 20, textYAxis);
			}
			if (o instanceof Float) {
				text((Float) o, 20, textYAxis);
			}
			if (o instanceof Boolean) {
				text(((Boolean) o).toString(), 20, textYAxis);
			}
			textYAxis += fontSize+5;
		popStyle();
	}	
}
