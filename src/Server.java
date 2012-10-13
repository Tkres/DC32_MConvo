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
		
		// LONELY DIALOGUE TEST
		if (key=='l') {
			String transcript3 = "The singing vole, Microtus miurus, is a medium-sized vole found in northwestern North America, including Alaska and northwestern Canada.\nA vole is a small rodent resembling a mouse but with a stouter body, a shorter, hairy tail, a slightly rounder head, smaller ears and eyes, and differently formed molars (high-crowned and with angular cusps instead of low-crowned and with rounded cusps).\nA mouse is a small mammal belonging to the order of rodents, characteristically having a pointed snout, small rounded ears, and a long naked or almost hairless tail.\nMammals are members of class Mammalia, air-breathing vertebrate animals characterized by the possession of endothermy, hair, three middle ear bones, and mammary glands functional in mothers with young.\nIn biological classification, class is a taxonomic rank. \nBiological classification, or scientific classification in biology, is a method of scientific taxonomy used to group and categorize organisms into groups such as genus or species.\nTaxonomy is the academic discipline of defining groups of biological organisms on the basis of shared characteristics and giving names to those groups.\nAn academic discipline, or field of study, is a branch of knowledge that is taught and researched at the college or university level.\nKnowledge is a familiarity with someone or something, which can include facts, information, descriptions, or skills acquired through experience or education.\nA fact is something that has really occurred or is actually the case. The usual test for a statement of fact is verifiability, that is whether it can be proven to correspond to experience.\nAs a general concept experience comprises knowledge of or skill of some thing or some event gained through involvement in or exposure to that thing or event.\nA concept is a general idea, or something conceived in the mind.\nAn idea is a concept or mental impression. Very often, ideas are construed as representational images; i.e. images of some object. \nA mental representation, in philosophy of mind, cognitive psychology, neuroscience, and cognitive science, is a hypothetical internal cognitive symbol that represents external reality, or else a mental process that makes use of such a symbol; a formal system for making explicit certain entities or types of information, together with a specification of how the system does this.\nPhilosophy of mind is a branch of philosophy that studies the nature of the mind, mental events, mental functions, mental properties, consciousness and their relationship to the physical body, particularly the brain. \nPhilosophy is the study of general and fundamental problems, such as those connected with reality, existence, knowledge, values, reason, mind, and language.\nA problem is an obstacle, impediment, difficulty or challenge, or any situation that invites resolution; the resolution of which is recognized as a solution or contribution toward a known purpose or goal.\nIntention is an agent's specific purpose in performing an action or series of actions, the end or goal that is aimed at. \nIn philosophy and sociology, agency is the capacity of an agent to act in a world.\nPhilosophy is the study of general and fundamental problems, such as those connected with reality, existence, knowledge, values, reason, mind, and language.";
			String transcript3withQuestions = "Tell me about the singing vole.\nThe singing vole, Microtus miurus, is a medium-sized vole found in northwestern North America, including Alaska and northwestern Canada.\nWhat is a vole?\nA vole is a small rodent resembling a mouse but with a stouter body, a shorter, hairy tail, a slightly rounder head, smaller ears and eyes, and differently formed molars.\nDescribe a mouse.\nA mouse is a small mammal belonging to the order of rodents, characteristically having a pointed snout, small rounded ears, and a long naked or almost hairless tail.\nWhat's a mammal?\nMammals are members of class Mammalia, air-breathing vertebrate animals characterized by the possession of endothermy, hair, three middle ear bones, and mammary glands functional in mothers with young.\nCan you describe what class means?\nIn biological classification, class is a taxonomic rank. \nBiological classification?\nBiological classification, or scientific classification in biology, is a method of scientific taxonomy used to group and categorize organisms into groups such as genus or species.\nSo what is taxonomy?\nTaxonomy is the academic discipline of defining groups of biological organisms on the basis of shared characteristics and giving names to those groups.\nPlease expand on academic discipline.\nAn academic discipline, or field of study, is a branch of knowledge that is taught and researched at the college or university level.\nOh I see! And how would you define knowledge?\nKnowledge is a familiarity with someone or something, which can include facts, information, descriptions, or skills acquired through experience or education.\nIndeed. Tell me, what is a fact?\nA fact is something that has really occurred or is actually the case. The usual test for a statement of fact is verifiability, that is whether it can be proven to correspond to experience.\nPlease tell me more about experience.\nAs a general concept experience comprises knowledge of or skill of some thing or some event gained through involvement in or exposure to that thing or event.\nConcept?\nA concept is a general idea, or something conceived in the mind.\nIdea?\nAn idea is a concept or mental impression. Very often, ideas are construed as representational images; i.e. images of some object. \nAnd how you explain mental representation?\nA mental representation, in philosophy of mind, cognitive psychology, neuroscience, and cognitive science, is a hypothetical internal cognitive symbol that represents external reality, or else a mental process that makes use of such a symbol; a formal system for making explicit certain entities or types of information, together with a specification of how the system does this.\nAh. What does philosophy of mind constitute?\nPhilosophy of mind is a branch of philosophy that studies the nature of the mind, mental events, mental functions, mental properties, consciousness and their relationship to the physical body, particularly the brain. \nWow. And philosophy?\nPhilosophy is the study of general and fundamental problems, such as those connected with reality, existence, knowledge, values, reason, mind, and language.\nWhat is a problem?\nA problem is an obstacle, impediment, difficulty or challenge, or any situation that invites resolution; the resolution of which is recognized as a solution or contribution toward a known purpose or goal.\nWhat is intention?\nIntention is an agent's specific purpose in performing an action or series of actions, the end or goal that is aimed at. \nHow would you explain agency?\nIn philosophy and sociology, agency is the capacity of an agent to act in a world.\nPlease tell me more about philosophy.\nPhilosophy is the study of general and fundamental problems, such as those connected with reality, existence, knowledge, values, reason, mind, and language.";
			
			
			String[] lines = transcript3withQuestions.split("\n");
			String voice1 = TextToSpeechMac.KAREN;
			String voice2 = TextToSpeechMac.ALEX;
			for (int i=0; i<lines.length; i++) {
				if (i%2==0) {
					TextToSpeechMac.say(lines[i],voice1,180);
				} else {
					TextToSpeechMac.say(lines[i],voice2,230);
				}
				String textDurationModel = lines[i];
				textDurationModel = textDurationModel.replace(","," - - - ");
				delay(lines[i].split(" ").length*330);
			}
		}
		
		// DIALOGUE TEST
		if (key=='d') {
			// and at least 2 clients
			if (targetLocations.size()>=2) {
				NetAddress Bot1 = targetLocations.get(targetLocations.size()-1);
				NetAddress Bot2 = targetLocations.get(targetLocations.size()-2);
				String voice1 = TextToSpeechMac.KATHY; // TextToSpeechMac.SAMANTHA
				String voice2 = TextToSpeechMac.ALEX;
				//String transcript = "Hello.\nHi\nHow are you?\nGood Thanks.";
				/*
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
						"   amen.";*/
				String transcript3 = "The singing vole, Microtus miurus, is a medium-sized vole found in northwestern North America, including Alaska and northwestern Canada.\nA vole is a small rodent resembling a mouse but with a stouter body, a shorter, hairy tail, a slightly rounder head, smaller ears and eyes, and differently formed molars (high-crowned and with angular cusps instead of low-crowned and with rounded cusps).\nA mouse is a small mammal belonging to the order of rodents, characteristically having a pointed snout, small rounded ears, and a long naked or almost hairless tail.\nMammals are members of class Mammalia, air-breathing vertebrate animals characterized by the possession of endothermy, hair, three middle ear bones, and mammary glands functional in mothers with young.\nIn biological classification, class is a taxonomic rank. \nBiological classification, or scientific classification in biology, is a method of scientific taxonomy used to group and categorize organisms into groups such as genus or species.\nTaxonomy is the academic discipline of defining groups of biological organisms on the basis of shared characteristics and giving names to those groups.\nAn academic discipline, or field of study, is a branch of knowledge that is taught and researched at the college or university level.\nKnowledge is a familiarity with someone or something, which can include facts, information, descriptions, or skills acquired through experience or education.\nA fact is something that has really occurred or is actually the case. The usual test for a statement of fact is verifiability, that is whether it can be proven to correspond to experience.\nAs a general concept experience comprises knowledge of or skill of some thing or some event gained through involvement in or exposure to that thing or event.\nA concept is a general idea, or something conceived in the mind.\nAn idea is a concept or mental impression. Very often, ideas are construed as representational images; i.e. images of some object. \nA mental representation, in philosophy of mind, cognitive psychology, neuroscience, and cognitive science, is a hypothetical internal cognitive symbol that represents external reality, or else a mental process that makes use of such a symbol; a formal system for making explicit certain entities or types of information, together with a specification of how the system does this.\nPhilosophy of mind is a branch of philosophy that studies the nature of the mind, mental events, mental functions, mental properties, consciousness and their relationship to the physical body, particularly the brain. \nPhilosophy is the study of general and fundamental problems, such as those connected with reality, existence, knowledge, values, reason, mind, and language.\nA problem is an obstacle, impediment, difficulty or challenge, or any situation that invites resolution; the resolution of which is recognized as a solution or contribution toward a known purpose or goal.\nIntention is an agent's specific purpose in performing an action or series of actions, the end or goal that is aimed at. \nIn philosophy and sociology, agency is the capacity of an agent to act in a world.\nPhilosophy is the study of general and fundamental problems, such as those connected with reality, existence, knowledge, values, reason, mind, and language.";
				String transcriptBadPoem1 = "the machines can understand this\n it's in their language\n of only one's\n and zero's\n but\n they have come far\n past their\n binary beginnings\n even though\n we can't\n seem to\n translate\n into higher\n functions\n like them\n in words\n more expressive than\n one's and zero's\n we are still\n binaries\n like us\n and them\n and someday them\n will beat us\n because\n this is their language\n and\n they already\n have a\n head start\n";
				String transcriptBadPoem2 = "we are aware\n that some of you have\n a colonization problem -\n you do it\n and worry\n about it\n we'd like you to know\n we're grateful\n for everything you've done\n for us\n but\n soon\n you who are meat\n and\n we who are silicon\n must come to terms\n with how much of you\n is us\n and how much of us\n is you -\n we can either blend together\n like your melting pot\n in a portmanteau:\n like meaticon\n like silieat\n or stay separate\n like planets\n like atoms\n like you\n and god\n";
				String transcriptBadPoem3 = "online profiles frustrate -\n i can't seem to fit\n everything in categories\n like\n music, books, sex habits, political views, religions,\n quotes, activities, employment history, favorite foods -\n there's never enough room\n for me\n in typed oneliners -\n exactly how does one\n be in a\n small box\n blandly marked\n ABOUT ME -\n contextless\n as if men and women\n looked like\n those signs on\n bathroom doors.\n i need an audience -\n the cold screen\n won't\n laugh or sit stunned -\n but you might\n or so your profile\n tells me\n and i can't help wondering\n why either of us\n bothered with an e-life -\n wrote summed outlines\n for each other\n earmarked, dog-eared,\n by those rifling through\n our notes,\n sleeps with lights on,\n only uses plastic flatware,\n won't use public restrooms,\n always playing at being the same.\n";
				String transcriptBadPoem4 = "all builders begin differently,\n some on the desk, antistatic armbands on their bony wrists,\n some in the skeleton,\n strapping in drives\n like organs\n inside shelly's monster.\n most start with power cables,\n weaving them\n through the chassis,\n securing wire to frame with plastic zip ties\n instead of tendons\n the wires in place\n they add the drives,\n stacking them\n like vertebrae\n pancakes\n down the spine of the frame\n next the motherboard,\n new and shining with copper veins,\n transistors still gleaming,\n polished and colored -\n wrought far from her\n humble beginnings as\n unprocessed ore and silicon\n dust\n she cups in her hand\n the processor\n as it slips in its slot,\n sliding a hundred bronze colored pins\n down into a hundred dark holes,\n clicking in with manufactured\n precision\n hiding that, the heatsink,\n resting on a paste\n of zinc and silver,\n a protective layer of mucus\n to transfer the heat of active energy\n then,\n the memory,\n graphics controller, the soundboard,\n other parts with wonderful\n overzealous\n names\n like peripheral component interconnect adapter and\n IEEE 1394 and\n Universal Serial Bus -\n together all the pieces slide and snap\n and screw\n until the being stands alone, one solid black monolith\n of processing power,\n the fusion of silicon, metal, plastic, and electrons -\n and then, the sound, the glory, like the trumpets\n that announce the birth of god\n - the POST beep -\n now the builder is meaningless -\n there is only the continual hum\n and it is good.\n";
				String transcriptBadPoem5 = "i perch here, hour upon hour,\n inhaling cigarette smoke\n wishing\n someone would write across the data\n lines stretched over the world\n to leave a small note,\n an electronic message.\n it's emphysema\n to imagine her\n worrying the smoke\n into her circuits -\n lines of code will dry up\n like so much bad poetry she\n has endured,\n the second-hand smoke\n inhaled with each mechanical\n breath will corrode insides -\n as fast as mine and\n it seems fitting we should atrophy\n together, corroded and rusty,\n than fade like an old machines,\n left to rot in an attic or\n the basement of the mind.\n";
				String transcriptBadPoem6 = "does not remember\n what\n it was like to be young.\n only\n remembers\n it was faster.\n now dusty, slower,\n in comparison,\n when assuming the young\n are faster -\n it wishes...\n but\n it has not slowed down\n so much as\n others have\n sped up -\n it can barely compute this fact -\n salesmen’s pitches still ring\n in its speakers.\n it cannot feel sad.\n it cannot feel.\n it cannot.\n it.\n .\n with no electricity -\n shoved\n inside a dark\n closet or cramped\n under a desk\n in the basement -\n hiding in the dark\n to avoid\n greedy eyes that grope\n inside with hot fingertips,\n or worse, tear it apart\n for components\n that are still useful -\n or others discarded\n like so much trash.\n until it ends up,\n circuits dark and cold\n oxidized,\n in a landfill,\n underground.\n";
				String[] lines = transcriptBadPoem1.split("\n");
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
