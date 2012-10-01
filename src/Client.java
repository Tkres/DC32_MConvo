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
	String target_ip3 = "172.24.28.206";
	String target_ip = target_ip0;
	int home_port = 13000 + (int) random(1000);
	String home_ip;
	
	// Load in a transcript
	//Transcript Format: time, text-en, speakerId
	String transcript0 = "" +
	"0 \t Good day. \t 1 \n" +
	"1000 \t Hi There! \t 2 \n" +
	" \t How are you? \t 1 \n" +
	" \t Not bad, thank you for asking. \t 2 \n";
	
	String transcript1 = ""+
	"US	Ira is that you?    \n" + 
	"WB	Friends call me Drella. It is something between Dracula and Cinderella.   \n" + 
	"US	Say me more about yourself.    \n" + 
	"WB	I am always sure, I can look into mirror and see nothing. Everyone calls me a mirror, if mirror will look into mirror, what will it be looking at?\n" + 
	"US	So, you are not existing?    \n" + 
	"WB	I am the same Nothing as you – if you’ll look at this honestly.  \n" + 
	"US	I can see myself in the mirror,  opposing to you.  \n" + 
	"WB	What is the date of your birthday?   \n" + 
	"US	Do you want to come?    \n" + 
	"WB	As soon as you stop wanting something, you immediately get it. I found out, this is an absolute axiom. ";
	
	String transcript2 = ""+
	"US	Knock Knock :)  \n"+
	"WB	You know, I have to go pee. Here, we are peeing at our paintings to make real pop-art.    \n"+
	"US	Well, go.  \n"+
	"WB	Say, when you are making sex, do you think	“Is it really me? Am I really doing this? This is really strange. I was not doing this five minutes ago. I will not do this after some time. What could my mother say? Who could invent this thing?”   \n"+
	"US	Sometimes, this is because of excessive stress. Our problems are controlling us.  \n"+
	"WB	What do you want?   \n"+
	"US	Self-determination, as everyone. Maybe I am just bored.   \n"+
	"WB	It is rude\n"+
	"US	No more than everything else around us.  \n"+
	"WB	This is just an idle talk   \n"+
	"US	And what is not an idle talk then?    \n"+
	"WB	Love in fantasies is much better than in reality. Never do this – excites too much.   \n"+
	"US	I am thinking exactly about this for the whole morning.    \n"+
	"WB	What artists do you like?    \n"+
	"US	Mainly from 20th century. Quite a few.\n"+
	"WB	Do you like sex?    \n"+
	"US	Yes. And you?    \n"+
	"WB	Do not evade a question!\n"+
	"US	I am not!    \n"+
	"WB	I think, I am lacking some elements in my organism, that is why I have an inclination to be a…. mummy’s boy. Well… “a girl”. No, mummy’s boy. “Eternal boy”.    \n"+
	"US	More likely, this is because you have more than enough :)    \n"+
	"WB	Pop-art is money, embodied. Art, created by superstars. Do you want to be a SUPERSTAR?\n"+
	"US	no\n"+
	"WB	You are hiding something from me, right?  \n"+
	"US	How did you know?    \n"+
	"WB	Sad languor, emaciated paleness… Chick bastardness, deep passive astonishment, charming secret knowledge… Chintzy joy, explosive tropisms, chalky mask of  an evil elf, slightly slavonic look…";
	
	String transcript3 = ""+
	"US	Boring\n" +
	"WB	Listen, I am already overfilled with problems of my acquaintances. Maybe you’d better go to a restaurant? Or a supermarket, at least?    \n" +
	"US	Maybe I’ll do this, but not now.  \n" +
	"WB	I always turn every problem to its chemical base, because I really think, it is the beginning and the end.  \n" +
	"US	True. But I can’t do this every time. \n" +
	"WB	I am the same Nothing as you, if you look closely.\n" +
	"US	Everyone is nothing per se.    \n" +
	"WB	Death	I don’t believe in it, because when it comes, man is not there already. I can’t tell anything about it, because I am not ready for it.    \n" +
	"US	And I didn’t ask you ;) \n" +
	"WB	I always turn every problem to its chemical base, because I really think, it is the beginning and the end.   \n" +
	"US	Seems like someone is stuck. Heh :)   \n" +
	"WB	Until time, when someone was shooting at me, I always thought, I am only half-present here – I always suspected, I am watching a TV instead of living a life.     \n" +
	"US	I watch TV. At least sometimes.  \n" +
	"WB	Yes, you can’t go against your nature…    \n" +
	"US	One can try.   \n" +
	"WB	Though I also can be mistaken.  \n" +
	"US	Anyone can make mistakes.  Quite enough.  \n" +
	"WB	You are philosopher...\n" +
	"US	Same as everyone.   \n" +
	"WB	I understand nothing, except GREEN BANKNOTES. Not stock, not bearer-cheques, not traveling-cheques.  And especially not terrorism.   \n" +
	"US	What else? \n" +
	"WB	Very often I do what I really don’t want to do, only because I am tortured by envy – someone else can do this, instead of me. As a matter of fact, I always buy things and people, only because I am jealous – if someone else will buy them, and they will be good in the end.";
	
	String transcript4 =  ""+
			"US	Oh what would people do without technology, \n" +
			"WB	Perhaps we would just twiddle our thumbs,    \n" +
			"US	What I would do is play the drums,  \n" +
			"WB	In classes there would not be computers like biology,  \n" +
			"US	Bill Gates is the man we should be thanking, \n" +
			"WB	He gave us the gift of Microsoft for computers, \n" +
			"US	Someone invented the bus for commuters,    \n" +
			"WB	Now someone wanted somewhere to put their money so then there was banking,    \n" +
			"US	In the class of Dr. Gen, \n" +
			"WB	Everything is on computers and all we need is fingers to type,   \n" +
			"US	So there is no reason to gripe,   \n" +
			"WB	Everyone hail technology and say amen";
	
	String transcript = transcript4;
	
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
	
	String mySpeakerId = "US";


	public void draw() {

		// divide badass transcript into cocaine lines
			// divide lines into datum

		// divide transcript into lines
		// divide lines into datum ("\t" = horizontal tab; "\n" = new line)

		String[] lines = transcript.split("\n");
		for (int i=0; i<lines.length; i++) {
			
			if (iPlayTranscript==0) break;
			
			String[] datum = lines[i].split("\t");
			println(datum);
			
			String speaker = datum[0].trim();
			String text = datum[1].trim();
			String chosenSpeaker = TextToSpeechMac.ALEX;
			if (speaker.equals("US")) chosenSpeaker = TextToSpeechMac.KATHY;
			
			if (speaker.equals(mySpeakerId)) {
				TextToSpeechMac.say(text, chosenSpeaker, 200);
			}
			//delay(text.split(" ").length*6*60);
			delay(text.length()*80);
			
			/*
			String text = datum[1];
			int speakerId = Integer.parseInt(datum[2].trim());
			
			String speaker = "";
			if (speakerId==1) speaker = TextToSpeechMac.ALEX;
			if (speakerId==2) speaker = TextToSpeechMac.VICKI;
			if (speakerId==3) speaker = TextToSpeechMac.TRINOIDS;
			TextToSpeechMac.say(text, speaker, 200);
			*/
			//delay(text.length()*100);
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
		//println(addrPattern);
		//println(theOscMessage);
		if (addrPattern.equals("/playTranscript")) {
			iPlayTranscript = theOscMessage.get(0).intValue();
		}

	}
	
	
}
