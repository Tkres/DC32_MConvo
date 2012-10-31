import hypermedia.video.OpenCV;

import java.awt.Rectangle;
import java.util.ArrayList;
import netP5.NetAddress;
import oscP5.OscMessage;
import oscP5.OscP5;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;
import processing.serial.Serial;

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
	int swidth, sheight, cwidth, cheight;
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
	Rectangle[] faces;
	PImage img; // image captured from webcam
	int opencv_contrast = 120;
	int opencv_brightness = 54;
	boolean humanIsPresent = false;
	int agitation = 0; // agitation variable!
	
	int realServoPosX = 70; // 70 because its center.
	
	// serial to arduino
	Serial serialPort;
	
	
	public void setup() {
		swidth = 640;
		sheight = 480;
		cwidth = 320; cheight = 240; //capture width, height
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
		opencv.capture(cwidth, cheight, 6); // open video stream. index=6 is the A4tech webcam
	    opencv.cascade( OpenCV.CASCADE_FRONTALFACE_ALT );  // load detection description, here-> front face detection : "haarcascade_frontalface_alt.xml"
	    // init serialPort to arduino
	    serialPort = new Serial(this, Serial.list()[5], 9600); // marks computer uses [6]	
	 // get this object to read the serial port and maybe flush it also instead of the arduino?
	    // said ollie.
	}

	// -----------------------------------------------------------------------------
	public void draw() {
		drawOpenCV();
	}
	
	
	// -----------------------------------------------------------------------------
	// SERVOS.
	
	int counter = 0;
	public void moveServos() {
		/* uses Arduino sketch "Pan_Tilt_Servos"
		 * servo1 is x-axis, connected to digital out pin 9 (analogue)
		 * servo2 is y-axis, connected to digital out pin 10 (analogue)
		 */
		int captureXCenter = cwidth/2;
		int captureYCenter = cheight/2;
		if(faces.length != 0) {
			int sPosXUnmapped = faces[0].x+faces[0].width/2;
			int sPosYUnmapped = faces[0].y+faces[0].height/2;
			
			
			//trying to get the webcam on top of the pan/tilt motor to move so to make face the center
			
			// 70 is center!
			// 0 is on right, 180 is on left. 
			
			counter++;
			if(counter >= 2) {
				if(map(sPosXUnmapped,0,cwidth,0,180) < 70) {
					realServoPosX+=2;
				}
				if(map(sPosXUnmapped,0,cwidth,0,180) > 70) {
					realServoPosX-=2;
				}
				//println("realServoPosX sent. New realServoPosX is: " + realServoPosX);
				counter = 0;
			}
			realServoPosX = constrain(realServoPosX, 10, 160); //10, 160 arbitrary
			
			
			int sPosY = round(map(sPosYUnmapped, 0, cheight, 0, 50));
			//int sPosY2 = round(map(sPosYUnmapped, 0, cheight, 0, 50)); // sPosY2 is diff just cause of the way I created the pan/tilt.
			
			if(frameCount % 4 == 0) {
				println("realServoPosX: " + realServoPosX);
				
//				serialPort.write(new byte[]{'s'});
//				serialPort.write(realServoPosX);
				serialPort.write("s" + realServoPosX); // s being sentient1 x-axis
				
				
			//	serialPort.write(sPosY + "w"); // w being sentient1 y-axis
				//serialPort.write(140 + "w"); //testing only!
				
			} else {
//				serialPort.write(sPosX + "k");
//				serialPort.write(sPosY2 + "l");
			}
		}
		
		delay(100);
		
		
	}
	
	
	// -----------------------------------------------------------------------------
	// OPENCV.
	public void drawOpenCV() {
		opencv.read();
		opencv.flip(OpenCV.FLIP_HORIZONTAL);
		opencv.convert(GRAY);
		// pretty optimum settings, taken from my Dreams in the Witch House project
        opencv.brightness(38);
        opencv.contrast(41);
		// detect faces
	//	Rectangle[] faces = opencv.detect(1.2f, 2, OpenCV.HAAR_DO_CANNY_PRUNING, 40, 40);
		faces = opencv.detect(1.2f, 2, OpenCV.HAAR_DO_ROUGH_SEARCH, 40, 40); // supposed to be faster, HAAR_FIND_BIGGEST_OBJECT is supposed to also be set. 
		
		// display the image
		img = opencv.image();
		// tile the screen once a human is present.
		// opportunity here for the agitation curve. The screen tiles more, gets more annoyed 
		// the close you get or the longer you stay.
		if(!humanIsPresent && faces.length != 0) {
			tileScreen();
			image(img,0,0,swidth,sheight); // temporary only
		}
		if(humanIsPresent) {
			drawTiledImages(faces);
			moveServos();
		} else {
			image(img,0,0, swidth, sheight);
		}
		
		checkAgitation(faces);
		checkForHuman(faces);
	}
	
	ArrayList<Integer> facesAverage = new ArrayList<Integer>();
	ArrayList<Integer> facesAverage2 = new ArrayList<Integer>();
	
	private void checkAgitation(Rectangle[] faces) {
		// have to: take the area size of face rectangle per frame, 
		// average it out to compensate for face detection discrepancies (grab chunks of maybe 30frames?)
		// compare to the same average 2 seconds into the future
		// if gotten bigger, human is closer, computer is more annoyed!
		if(faces.length != 0) {
			if(facesAverage.size() < 15) {
				int avg = faces[0].width * faces[0].height;
				facesAverage.add(avg);				
			} else {
				if(facesAverage2.size() < 15) {
					int avg = faces[0].width * faces[0].height;
					facesAverage2.add(avg);				
				}
			}
		}
		// once facesAverage2.size() hits 15, compare. if bigger, flag agitation to ++!
		if(humanIsPresent && facesAverage.size() == 15 && facesAverage2.size() == 15) {
			int avg1 = 0, avg2 = 0;
			for(Integer i : facesAverage) {
				avg1 += i;
			}
			for(Integer i : facesAverage2) {
				avg2 += i;
			}
//			System.out.println("avg1: " + avg1 + ", avg2: " + avg2);
			if(avg1/facesAverage.size() <= avg2/facesAverage2.size()) {
				// human has moved closer. React!
				System.out.println("avg1: " + avg1 + ", avg2: " + avg2 + ", diff: " + (avg2-avg1));
				agitation++;
				tileScreen(4+agitation*2); // *2 arbitrary.
				facesAverage.clear(); facesAverage2.clear();
			}
		}
		// if agitation is more than a certain threshold, go a bit crazy with dividing.
		if(agitation > 3) { // 6 is arbitrary.
			tileScreen(round(random(15)));
		}
		
		// if human is no longer present, wipe facesAverage and facesAverage2 lists.
		if(!humanIsPresent || frameCount % 60 == 0) {
			facesAverage.clear(); facesAverage2.clear();
		}
	}

	int timeout = 0;
	public void checkForHuman(Rectangle[] faces) {
		// check faces array, if not empty (more than 0), send panic msg to server
		if(!humanIsPresent && faces.length != 0) {
			sendHumanIsPresent("0 \t How unfortunate. A human comes close. \t 3 \n");
			humanIsPresent = true;
		}
		// if faces.length is empty for more than 4 seconds (arbitrary atm), then reset humanIsPresent alert.
		if(faces.length == 0) {
			timeout++;
			textToScreen(timeout, 20);
			if(timeout > FRAME_RATE*4) {
				humanIsPresent = false;
				timeout = 0;
				//reset agitation
				agitation = 1;
				tileScreen(1);
			}
		} else { // face appeared again, reset the timeout
			timeout = 0;
		}
	}
	
	
	// -----------------------------------------------------------------------------
	// SCREEN TILING.
	/**
	 * following recursive tile code code modified, original by Benedikt Gro§ & Julia Laub<br>
	 * benedikt Št looksgood dot de / julia Št generative-systeme dot de<br>
	 */
	ArrayList<Integer> coordList = new ArrayList<Integer>();
	
	public void tileScreen() {
		coordList.clear();
		divide(0, 0, swidth, sheight, 4); // 4 is arbitrary.
	}
	public void tileScreen(int depth) {
		coordList.clear();
		divide(0, 0, swidth, sheight, depth);
	}
	private void divide(int x1, int y1, int x2, int y2, int depth) {
	  // BASE CASE.
	  if (depth<1)
	    return;

	  float horOrVert = random(1); // decide to tile vertical or horicontal
	  int count = round(random(1,2));//each tile divide in n parts
	  depth -= 1;//increment

	  //tiling vertical or horicontal
	  if (horOrVert <= .5f) {
	    int x_width = abs(x2-x1)/count;
	    if (x_width < 1.8) count = 1;
	    for (int i=0; i<count; i++) {
	      x_width = abs(x2-x1)/count;
	      int xA = x1+x_width*i;
	      int xB = x1+x_width*(i+1);
	      divide(xA, y1, xB, y2, depth);
	    }
	  } else {
	    int y_height = abs(y2-y1)/count;
	    if (y_height < 0.4) count = 1;
	    for (int i=0; i<count; i++) {
	      y_height = abs(y2-y1)/count;
	      int yA = y1+y_height*i;
	      int yB = y1+y_height*(i+1);
	      divide(x1, yA, x2, yB, depth);
	    }
	  }
	  // reached max recursion depth.
	  if (depth<1) {
		// place all these inputs into an array for future placement,
		// so as not have to run the recursive algorithm every frame.
		coordList.add(x1);
		coordList.add(y1);
		coordList.add(x2-x1);
		coordList.add(y2-y1);
	  }
	}

	public void drawTiledImages(Rectangle[] faces) {
		// coordList.size() divided by 4 as that's how many images there will be!
		// steps through the arrayList in sets of 4. See the adding of coordLists in divide().
		for(int i=0; i<coordList.size()/4; i++) { 
			int x1 = coordList.get(4*i);
			int y1 = coordList.get(4*i+1);
			int w = coordList.get(4*i+2);
			int h = coordList.get(4*i+3);
			image(img,x1,y1,w,h);
			// draw face detection rect
			for(int j=0; j<faces.length; j++) {
				float facex = map(faces[j].x,0,cwidth,x1,x1+w);
				float facey = map(faces[j].y,0,cheight,y1,y1+h);
				float facew = map(faces[j].width,0,cwidth,0,w);
				float faceh = map(faces[j].height,0,cheight,0,h);
				pushStyle();
				stroke(100,100,50);
				rect(facex,facey,facew,faceh);
				popStyle();
			}
			//draw 	
			filter(INVERT);
		}
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
		//String addrPattern = theOscMessage.addrPattern();
	}
	

	public void stop() {
	    opencv.stop();
	    super.stop();
	}
	
	// -----------------------------------------------------------------------------
	// INTERACTION
	public void mouseDragged() {
	    opencv_contrast   = (int) map(mouseX, 0, width, -128, 128);
	    opencv_brightness = (int) map(mouseY, 0, width, -128, 128);
	    textToScreen(opencv_contrast, 40);
	    textToScreen(opencv_brightness, 60);
	}
	
	// -----------------------------------------------------------------------------
	// HELPER METHODS
	/**
	 * Helper method for drawing text to screen. Send in a mixed arraylist, it doesn't mind.
	 * @param a ArrayList of mixed types to be printed to screen
	 */
	public void textToScreen(ArrayList<Object> a) {
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
	public void textToScreen(Object o, int textYAxis) {
		pushStyle();
		fill(40,100,50);
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
