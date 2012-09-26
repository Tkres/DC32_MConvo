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
	
	boolean playTranscript = false;
	
	public void mousePressed() {
		playTranscript = !playTranscript;
		System.out.println("playTranscript: "+playTranscript);
		
		for (NetAddress targetLocation : targetLocations) {
		
			OscMessage myMessage = new OscMessage("/playTranscript");
			if (playTranscript) {
				myMessage.add(1);
			} else {
				myMessage.add(0);
			}
			
			oscP5.send(myMessage, targetLocation);
		}
		
		
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
		sendTranscript();
	}
	// Parse msgs from face detection sentient
	public void parseMessageHumanDetection(OscMessage theOscMessage) {
		String humanDetected = theOscMessage.get(0).stringValue();
		System.out.println("HUMAN DETECTED.");
		sendTranscript(humanDetected);
	}
	
	public void sendTranscript() {
		// for testing purposes, client that can accept transcript is first connected to targetLocations.
		OscMessage myMessage = new OscMessage("/transcript");
		//Transcript Format: time, text-en, speakerId
		String transcript = "" +
				"0 \t Good day. This has been sent from server godhead. How are you? \t 1 \n" +
				" \t Hi There! What's up? \t 2 \n" +
				" \t How are you? \t 1 \n" +
				" \t Not bad, thank you for asking. \t 2 \n";
		myMessage.add(transcript);
		oscP5.send(myMessage, targetLocations.get(0));
	}
	public void sendTranscript(String s) {
		// for testing purposes, client that can accept transcript is first connected to targetLocations.
		OscMessage myMessage = new OscMessage("/transcript");
		//Transcript Format: time, text-en, speakerId
		String transcript = s;
		myMessage.add(transcript);
		oscP5.send(myMessage, targetLocations.get(0));
	}
	
}
