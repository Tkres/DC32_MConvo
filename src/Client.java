import java.io.IOException;

import netP5.NetAddress;
import oscP5.OscMessage;
import oscP5.OscP5;
import processing.core.PApplet;

public class Client extends PApplet {
	private static final long serialVersionUID = 1L;

	public static void main(String args[]) {
		PApplet.main(new String[] { "--present", "Client" }); //NOTE <- It was trying to reference a PSketch previously. Changed to client
	}

	// Core Variables
	int swidth, sheight;
	int rootFrame = 0;

	// Communication
	OscP5 oscP5;
	NetAddress myTargetLocation;
	int target_port = 12000;
	String target_ip0 = "127.0.0.1";
	String target_ip1 = "192.168.0.194";
	String target_ip = target_ip0;
	int home_port = 13000 + (int) random(1000);
	String home_ip;
	
	int setFramerate = 30;
	
	ContinuousServerPinger continuousServerPinger = new ContinuousServerPinger();
	
	public class ContinuousServerPinger {
		int serverCheckInterval = 30*setFramerate; //every 30 seconds
		int timer = 0;
		void run() {
			if (timer==serverCheckInterval) {
				sendMyLocationToTarget();
				timer = 0;
			}
			timer++;
		}
	}
	
	public void setup() {
		swidth = 50;
		sheight = 50;
		size(swidth, sheight);
		colorMode(HSB, 100);
		background(30,50,90);
		smooth();

		// initialize communication
		oscP5 = new OscP5(this, home_port);
		home_ip = oscP5.ip();
		myTargetLocation = new NetAddress(target_ip, target_port);
		this.sendMyLocationToTarget();
		
		frameRate(setFramerate);
	}
	
	
	public void draw() {
		
		continuousServerPinger.run();
		
	}
	
	public void mousePressed() {
		this.sendMyLocationToTarget();
	}

	public void keyPressed() {
		// resend location to server
		if (key=='s') {
			this.sendMyLocationToTarget();
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

	int iPlayTranscript = 0;
	public void oscEvent(OscMessage theOscMessage) {
		String addrPattern = theOscMessage.addrPattern();
		
		if (addrPattern.equals("/say")) {
			String text = theOscMessage.get(0).stringValue();
			String voice = theOscMessage.get(1).stringValue();
			TTS.say(text, voice, 250);
		}
		
		if (addrPattern.equals("/tray")) {
			String cmd = theOscMessage.get(0).stringValue();
			commandTray(cmd);
		}

	}
	
	
	// -----------------------------------------------------------------------------
	// OTHER FUNCTIONS.
	public void commandTray(String command) {
		// command equals open or close
		try {
			  Runtime.getRuntime().exec(new String[] {"drutil", "tray", command});
		  }
		  catch (IOException e) {
			  System.err.println("Error commanding tray.");
			  System.err.println("IOException: "+e);
		  }
	}
	
	
	
}
