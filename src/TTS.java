import java.io.IOException;

// Commented so it ommits voices higher than OSX10.4

public class TTS {
		  // Store the voices, makes for nice auto-complete in Eclipse

		  // nuance voices - on OSX 10.8 ONLY.
		/*
		  static final String TING_TING = "Ting-Ting"; //CHINESE
		  static final String SIN_JI = "Sin-Ji"; //HK
		  static final String LEE = "Lee"; //Australian
		  static final String KAREN = "Karen"; //Australian
		  static final String SAMANTHA = "Samantha"; // US-Female
		  */
	
		  // male voices
		  //static final String ALEX = "Alex";
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
		  static String[] voices = {
		    //TING_TING, SIN_JI, LEE, SAMANTHA, KAREN, ALEX, 
		     BRUCE, FRED, JUNIOR, RALPH, AGNES, KATHY,
		    PRINCESS, VICKI, VICTORIA, ALBERT, BAD_NEWS, BAHH,
		    BELLS, BOING, BUBBLES, CELLOS, DERANGED, GOOD_NEWS,
		    HYSTERICAL, PIPE_ORGAN, TRINOIDS, WHISPER, ZARVOX
		  };
		  
		  public static void say(String script) {
			    // 200 seems like a resonable default speed
			    say(script, BRUCE, 200);
			  }
		
		  public static void say(String script, String voice, int speed) {
			  try {
				  Runtime.getRuntime().exec(new String[] {"say", "-v", voice, "[[rate " + speed + "]]" + script});
			  }
			  catch (IOException e) {
				  System.err.println("IOException");
			  }
		  }
}
