import java.io.IOException;

import netP5.NetAddress;
import oscP5.OscMessage;
import oscP5.OscP5;
import processing.core.PApplet;


TextToSpeechMac TextToSpeechMac;

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

  }

  public void draw() {
    
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
      TextToSpeechMac.say(text, voice, 250);
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
  
  
  


public class TextToSpeechMac {
      // Store the voices, makes for nice auto-complete in Eclipse

      // nuance voices - on OSX 10.8 ONLY.
      static final String TING_TING = "Ting-Ting"; //CHINESE
      static final String SIN_JI = "Sin-Ji"; //HK
      static final String LEE = "Lee"; //Australian
      
      // male voices
      static final String ALEX = "Alex";
      static final String BRUCE = "Bruce";
      static final String FRED = "Fred";
      static final String JUNIOR = "Junior";
      static final String RALPH = "Ralph";

      // female voices
      static final String AGNES = "Agnes";
      static final String KATHY = "Kathy";
      static final String PRINCESS = "Princess";
      static final String VICKI = "Vicki";
      static final String VICTORIA = "Victoria";

      // novelty voices
      static final String ALBERT = "Albert";
      static final String BAD_NEWS = "Bad News";
      static final String BAHH = "Bahh";
      static final String BELLS = "Bells";
      static final String BOING = "Boing";
      static final String BUBBLES = "Bubbles";
      static final String CELLOS = "Cellos";
      static final String DERANGED = "Deranged";
      static final String GOOD_NEWS = "Good News";
      static final String HYSTERICAL = "Hysterical";
      static final String PIPE_ORGAN = "Pipe Organ";
      static final String TRINOIDS = "Trinoids";
      static final String WHISPER = "Whisper";
      static final String ZARVOX = "Zarvox";

      // throw them in an array so we can iterate over them / pick at random
      String[] voices = {
        TING_TING, SIN_JI, LEE, 
        ALEX, BRUCE, FRED, JUNIOR, RALPH, AGNES, KATHY,
        PRINCESS, VICKI, VICTORIA, ALBERT, BAD_NEWS, BAHH,
        BELLS, BOING, BUBBLES, CELLOS, DERANGED, GOOD_NEWS,
        HYSTERICAL, PIPE_ORGAN, TRINOIDS, WHISPER, ZARVOX
      };
      
      public void say(String script) {
          // 200 seems like a resonable default speed
          say(script, ALEX, 200);
        }
    
      public void say(String script, String voice, int speed) {
        try {
          Runtime.getRuntime().exec(new String[] {"say", "-v", voice, "[[rate " + speed + "]]" + script});
        }
        catch (IOException e) {
          System.err.println("IOException");
        }
      }
}

	
	
