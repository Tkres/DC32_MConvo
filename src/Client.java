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
	
	// Load in a transcript
	//Transcript Format: time, text-en, speakerId
	String transcript = "" +
	"0 \t Good day. \t 1 \n" +
	"1000 \t Hi There! \t 2 \n" +
	" \t How are you? \t 1 \n" +
	" \t Not bad, thank you for asking. \t 2 \n";
	
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
		
		//TextToSpeechMac.say("Hello there!", "Alex", 250);
	}


	public void draw() {
		// divide transcript into lines
		// divide lines into datum ("\t" = horizontal tab; "\n" = new line)
		String[] lines = transcript.split("\n");
		for (int i=0; i<lines.length; i++) {
			String[] datum = lines[i].split("\t");
			
			String text = datum[1];
			int speakerId = Integer.parseInt(datum[2].trim());
			
			String speaker = "";
			if (speakerId==1) speaker = TextToSpeechMac.ALEX;
			if (speakerId==2) speaker = TextToSpeechMac.VICKI;
			if (speakerId==3) speaker = TextToSpeechMac.TRINOIDS;
			TextToSpeechMac.say(text, speaker, 200);
			
			delay(text.length()*100);
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
	
	/**
	 * Communications. OSC listener that handles incoming messages.
	 * @param theOscMessage incoming message to be parsed.
	 */
	public void oscEvent(OscMessage theOscMessage) {
		String addrPattern = theOscMessage.addrPattern();
		if(addrPattern.equals("/transcript")) {
			transcript = theOscMessage.get(0).stringValue(); 
		}

	}
	
	
}
