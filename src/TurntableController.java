import processing.core.PApplet;
import processing.serial.Serial;

public class TurntableController  {

	Serial turntableSerial;
	
	PApplet p;
	
	public TurntableController(PApplet papplet, int port) {
		p = papplet;
		setupSerialWithPort(port);
	}
	
	public String[] getSerialList() {
		return Serial.list();
	}
	
	public void setupSerialWithPort(int port) {
		try {
			turntableSerial = new Serial(p, Serial.list()[port], 9600);
		} catch(Exception e) {
			//System.err.println(e);
		}
	}
	
	public void sendMessage(String message) {
		turntableSerial.write(message);
	}
}
