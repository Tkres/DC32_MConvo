import java.util.ArrayList;

import netP5.NetAddress;
import oscP5.OscMessage;
import oscP5.OscP5;
import processing.core.PApplet;

public class Server extends PApplet {
	private static final long serialVersionUID = 1L;
	public static void main(String args[]) {
		PApplet.main(new String[] { "--present", "Server" });
	}
	int swidth, sheight;
	
	OscP5 oscP5;
	int home_port = 12000;
	String home_ip;
	int target_port = 13001;
	String localhost = "127.0.0.1";
	String target_ip = localhost;
	ArrayList<NetAddress> targetLocations;
	

	// -----------------------------------------------------------------------------
	// SETUP.
	
	public void setup() {
		swidth = 50; 
		sheight = 50;
		size(swidth, sheight);
		colorMode(HSB,100);
		background(30,70,50);
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
	
	public void mousePressed() {
		this.sendSpeechToAll("mouse pressed");
	}
	
	public void keyPressed() {
		
		// DIALOGUE TEST
		if (key=='d') {
			// and at least 2 clients
			if (targetLocations.size()>=2) {
				NetAddress Bot1 = targetLocations.get(targetLocations.size()-1);
				NetAddress Bot2 = targetLocations.get(targetLocations.size()-2);
				String voice1 = TextToSpeechMac.VICKI;
				String voice2 = TextToSpeechMac.ALEX;
				//String transcript = "Hello.\nHi\nHow are you?\nGood Thanks.";
				String transcript2 = "	Oh what would people do without technology, \n" +
						"	Perhaps we would just twiddle our thumbs,    \n" +
						"	What I would do is play the drums,  \n" +
						"	In classes there would not be computers like biology,  \n" +
						"	Bill Gates is the man we should be thanking, \n" +
						"	He gave  the gift of Microsoft for computers, \n" +
						"	Someone invented the bus for commuters,    \n" +
						"	Now someone wanted somewhere to put their money so then there was banking,    \n" +
						"	In the class of Dr. Gen, \n" +
						"	Everything is on computers and all we need is fingers to type,   \n" +
						"	So there is no reason to gripe,   \n" +
						"	Everyone hail technology and say amen. \n" +
						"   amen.";
				String[] lines = transcript2.split("\n");
				for (int i=0; i<lines.length; i++) {
					if (i%2==0) {
						sendSpeech(lines[i], voice1, Bot1);
					} else {
						sendSpeech(lines[i], voice2, Bot2);
					}
					delay(lines[i].split(" ").length*300);
					//delay(lines[i].length()*80);
				}
			} else {
				TextToSpeechMac.say("Unfortunately only "+targetLocations.size()+" clients were found. At least two is needed for dialogue.");
			}
		}
		// ID Test (say what id you are).
		if (key=='i') {
			// may be a useful tool
			for (NetAddress targetLocation : targetLocations) {
				String text = "I am number "+targetLocations.indexOf(targetLocation);
				sendSpeech(text, targetLocation);
				delay(text.split(" ").length*300);
			}
		}
		
		// OTHER KEYS TEST.
		String speech = "key pressed";
		if (key=='a') {
			speech = "Aloha!";
			sendSpeechToAll(speech);
		}
		if (key=='t') {
			speech = "" +
					"0 \t Good day. This has been sent from server godhead. How are you? \t 1 \n" +
					" \t Hi There! What's up? \t 2 \n" +
					" \t How are you? \t 1 \n" +
					" \t Not bad, thank you for asking. \t 2 \n";
			sendSpeechToAll(speech);
			// Ok it seems that short lengths of text via osc are possible. Will have to test this setup on screens (and if it doesn't work for longer scripts-they'll have to be transcripts).
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
		if (!targetLocations.contains(newTargetLocation)) {
			targetLocations.add(newTargetLocation);
			System.out.println("Added Client." + newTargetLocation);
			this.sendSpeech("Yay, Mom knows I exist now.", newTargetLocation);
		} else {
			this.sendSpeech("Mom already knows about me.", newTargetLocation);
		}
		
	}
	// Parse msgs from face detection sentient
	public void parseMessageHumanDetection(OscMessage theOscMessage) {
		String humanDetected = theOscMessage.get(0).stringValue();
		System.out.println("HUMAN DETECTED.");
		TextToSpeechMac.say(humanDetected);
	}
	
	// -----------------------------------------------------------------------------
	// COMMUNICATIONS: SPEECH COMMANDS
	
	public void sendSpeechToAll(String speech) {
		OscMessage myMessage = new OscMessage("/say");
		myMessage.add(speech);
		myMessage.add(TextToSpeechMac.ALEX);
		//oscP5.send(myMessage, targetLocations.get(targetLocations.size()-1));
		// send to all.
		for (NetAddress targetLocation : targetLocations) {
			oscP5.send(myMessage,targetLocation);
		}
	}
	
	public void sendSpeech(String speech, NetAddress targetLocation) {
		OscMessage myMessage = new OscMessage("/say");
		myMessage.add(speech);
		myMessage.add(TextToSpeechMac.ALEX);
		oscP5.send(myMessage, targetLocation);
	}
	
	public void sendSpeech(String speech, String voice, NetAddress targetLocation) {
		OscMessage myMessage = new OscMessage("/say");
		myMessage.add(speech);
		myMessage.add(voice);
		oscP5.send(myMessage, targetLocation);
	}
	
	
}
