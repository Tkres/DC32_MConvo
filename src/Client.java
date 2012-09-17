import java.io.IOException;

import netP5.NetAddress;
import oscP5.OscMessage;
import oscP5.OscP5;
import processing.core.PApplet;

public class Client extends PApplet {
	private static final long serialVersionUID = 1L;

	public static void main(String args[]) {
		PApplet.main(new String[] { "--present", "PSketch" });
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
	String target_ip2 = "172.19.19.109";
	String target_ip = target_ip0;
	int home_port = 13000 + (int) random(1000);
	String home_ip;
	
	public void setup() {
		swidth = 640;
		sheight = 480;
		size(swidth, sheight);
		colorMode(HSB, 100);
		smooth();

		// initialize communication
		oscP5 = new OscP5(this, home_port);
		home_ip = oscP5.ip();
		myTargetLocation = new NetAddress(target_ip, target_port);
		this.sendMyLocationToTarget();
		
		TextToSpeechMac.say("Hello there!", "Alex", 250);
		
	}


	public void draw() {
		
	}
	
	// -----------------------------------------------------------------------------
	// COMMUNICATION.
	public void sendMyLocationToTarget() {
		OscMessage myMessage = new OscMessage("/myLocation");
		myMessage.add(home_ip);
		myMessage.add(home_port);
		oscP5.send(myMessage, myTargetLocation);
	}
	
	public void oscEvent(OscMessage theOscMessage) {
		String addrPattern = theOscMessage.addrPattern();

	}
	
	
}
