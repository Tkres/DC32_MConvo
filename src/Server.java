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
				String voice1 = TextToSpeechMac.SAMANTHA;
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
				String[] lines = transcript3.split("\n");
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
