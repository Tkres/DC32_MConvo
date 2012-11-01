import processing.core.PApplet;
import processing.serial.Serial;

public class PrinterController  {

	Serial printerSerial;
	
	PApplet p;
	
	int defaultPrinterSerialPort = 4;
	
	public PrinterController(PApplet papplet) {
		p = papplet;
		setupSerialWithPort(defaultPrinterSerialPort);
	}
	
	public PrinterController(PApplet papplet, int port) {
		p = papplet;
		setupSerialWithPort(port);
	}
	
	public String[] getSerialList() {
		return Serial.list();
	}
	
	public void setupSerialWithPort(int port) {
		printerSerial = new Serial(p, Serial.list()[port], 9600);
	}
	
	public void sendMessage(String message) {
		printerSerial.write(message);
	}
}
