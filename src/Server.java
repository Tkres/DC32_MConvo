import java.util.ArrayList;

import netP5.NetAddress;
import oscP5.OscMessage;
import oscP5.OscP5;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PVector;

public class Server extends PApplet {
	private static final long serialVersionUID = 1L;
	public static void main(String args[]) {
		PApplet.main(new String[] { "--present", "PSketch" });
	}
	
	int swidth, sheight;
	
	OscP5 oscP5;
	int home_port = 12000;
	String home_ip;
	int target_port = 13001;
	String target_ip = "127.0.0.1";
	ArrayList<NetAddress> targetLocations;
	

	// -----------------------------------------------------------------------------
	// SETUP.
	
	public void setup() {
		swidth = 800; 
		sheight = 600;
		size(swidth, sheight);
		colorMode(HSB,100);
		smooth();
		
		targetLocations = new ArrayList<NetAddress>();
		
		oscP5 = new OscP5(this,home_port);
		home_ip = oscP5.ip();
		println("Server IP: "+home_ip);
		println("Server Port: "+home_port);
		
	}
	
	
	// -----------------------------------------------------------------------------
	
	public void draw() {
		
	}
	
	
	// -----------------------------------------------------------------------------
	// COMMUNICATIONS.
	// COMMUNICATIONS: HANDLE INCOMING MESSAGES
	public void oscEvent(OscMessage theOscMessage) {
	  String addrPattern = theOscMessage.addrPattern();
	  if (addrPattern.equals("/myLocation")) {
		  this.parseMessageMyLocation(theOscMessage);
	  }
	  if (addrPattern.equals("/humanDetection")) {
		  this.parseMessageHumanDetection(theOscMessage);
	  }
	}
	
	// COMMUNICATIONS: PARSE MESSAGES
	public void parseMessageMyLocation(OscMessage theOscMessage) {
		// Add Client to list of targetLocations.
		String client_ip = theOscMessage.get(0).stringValue();
		int client_port = theOscMessage.get(1).intValue();
		NetAddress newTargetLocation = new NetAddress(client_ip, client_port);
		targetLocations.add(newTargetLocation);
		System.out.println("Added Client." + newTargetLocation);
	}
	public void parseMessageHumanDetection(OscMessage theOscMessage) {
		String humanDetected = theOscMessage.get(0).stringValue();
		System.out.println(humanDetected);
	}
	
}
