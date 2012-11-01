import java.io.File;
import java.util.ArrayList;

import ddf.minim.AudioPlayer;
import ddf.minim.Minim;

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
			String transcript4c = "The Rocks is an urban locality, tourist precinct and historic area of Sydney's city centre, in the state of New South Wales, Australia. It is located on the southern shore of Sydney Harbour, immediately north-west of the Sydney central business district. The precinct and its immediate surroundings are administered independently of the local government area of the City of Sydney, by a New South Wales state government statutory authority, the Sydney Harbour Foreshore Authority. [[.break.]]What is tourist?[[.break.]]Tourism is travel for recreational, leisure or business purposes. The World Tourism Organization defines tourists as people \"traveling to and staying in places outside their usual environment for not more than one consecutive year for leisure, business and other purposes\". [[.break.]]What is travel?[[.break.]]Travel is the movement of people or objects between relatively distant geographical locations.[[.break.]]Please define people.[[.break.]]A people is a plurality of persons considered as a whole, as in an ethnic group or nation. Collectively, for example, Jews are known as \"the Jewish people\", European Gypsies comprise the bulk of \"the Romani people\", and Palestinian Arabs have started to be called \"the Palestinian people\".[[.break.]]Pay tell the definition of person.[[.break.]]A person is a being, such as a human, that has certain capacities or attributes constituting personhood, which in turn is defined differently by different authors in different disciplines, and less formally by different cultures in different times and places.[[.break.]]Please define human.[[.break.]]Humans are primates of the family Hominidae, and the only living species of the genus ''Homo''. They originated in Africa, where they reached anatomical modernity about 200,000 years ago and began to exhibit full behavioral modernity around 50,000 years ago.[[.break.]]Sorry, could you describe primate please.[[.break.]]A primate is a mammal of the order Primates , From Old French or French ''primat'', from a noun use of Latin ''primat-'', from ''primus'' . The English singular ''primate'' was derived via back-formation from the Latin inflected form. Linnaeus thought this the \"highest\" order of mammals</ref> which contains prosimians and simians. Primates arose from ancestors that lived in the trees of tropical forests; many primate characteristics represent adaptations to life in this challenging three-dimensional environment. All but a few primate species remain at least partly arboreal.[[.break.]]Pay tell the definition of mammal.[[.break.]]Mammals are members of class Mammalia , air-breathing vertebrate animals characterized by the possession of endothermy, hair, three middle ear bones, and mammary glands functional in mothers with young. Most mammals also possess sweat glands and specialized teeth. The largest group of mammals, the placentals, have a placenta which feeds the offspring during gestation. The mammalian brain, with its characteristic neocortex, regulates endothermic and circulatory systems, the latter featuring red blood cells lacking nuclei and a four-chambered heart. Mammals range in size from the 30Ð40 millimeter bumblebee bat to the 33-meter blue whale.[[.break.]]Sorry, could you describe class (biology) please.[[.break.]]In biological classification, class is* a taxonomic rank. Other well-known ranks are life, domain, kingdom, phylum, order, family, genus, and species, with class fitting between phylum and order. As for the other well-known ranks, there is the option of an immediately lower rank, indicated by the prefix ''sub-'': subclass .* a taxonomic unit, a taxon, in that rank. In that case the plural is classes [[.break.]]I comprehend that definition now. But I am confused as to what is biological classification.[[.break.]]Biological classification, or ''scientific classification in biology'', is a method of scientific taxonomy used to group and categorize organisms into groups such as genus or species. These groups are known as taxa .[[.break.]]Pay tell the definition of Taxonomy.[[.break.]]Taxonomy is the academic discipline of defining groups of biological organisms on the basis of shared characteristics and giving names to those groups. Each group is given a rank and groups of a given rank can be aggregated to form a super group of higher rank and thus create a hierarchical classification. The groups created through this process are referred to as taxa . An example of a modern classification is the one published in 2009 by the Angiosperm Phylogeny Group for all living flowering plant families .[[.break.]]Pay tell the definition of academic discipline.[[.break.]]An academic discipline, or field of study, is a branch of knowledge that is taught and researched at the college or university level. Disciplines are defined , and recognized by the academic journals in which research is published, and the learned societies and academic departments or faculties to which their practitioners belong. Pierce writes: \"Although most studies fail to define the term [discipline] explicitly, they typically assume that boundaries of disciplines closely follows those of academic departments. The use of such boundaries may seem to fix overly concrete limits on a highly abstract phenomenon, excluding too large a number of people with interest in the subject. But its importance in creating and maintaining disciplinary communities makes the academic department the building block from which disciplines are created\".<ref>Pierce, S. J. . Subject areas, disciplines and the concept of authority. LISR [Library and Information Science Research], 13, 21-35.</ref> [[.break.]]I comprehend that definition now. But I am confused as to what is knowledge.[[.break.]]Knowledge is a familiarity with someone or something, which can include facts, information, descriptions, or skills acquired through experience or education. It can refer to the theoretical or practical understanding of a subject. It can be implicit or explicit ; it can be more or less formal or systematic.<ref>http://oxforddictionaries.com/view/entry/m_en_us1261368#m_en_us1261368</ref> In philosophy, the study of knowledge is called epistemology; the philosopher Plato famously defined knowledge as \"justified true belief.\" However, no single agreed upon definition of knowledge exists, though there are numerous theories to explain it. The following quote from Bertrand Russell's \"Theory of Knowledge\" illustrates the difficulty in defining knowledge: \"The question how knowledge should be defined is perhaps the most important and difficult of the three with which we shall deal. This may seem surprising: at first sight it might be thought that knowledge might be defined as belief which is in agreement with the facts. The trouble is that no one knows what a belief is, no one knows what a fact is, and no one knows what sort of agreement between them would make a belief true. Let us begin with belief.\"[[.break.]]Please define fact.[[.break.]]A fact is something that has really occurred or is actually the case. The usual test for a statement of fact is verifiability, that is whether it can be proven to correspond to experience. Standard reference works are often used to check facts. Scientific facts are verified by repeatable experiments.[[.break.]]What is Proof (truth)?[[.break.]]''A proof is sufficient evidence or an argument for the truth of a proposition.''<ref>''Proof and other dilemmas: mathematics and philosophy'' by Bonnie Gold, Roger A. Simons 2008 ISBN 0883855674 pages 12-20</ref><ref>''Philosophical Papers, Volume 2'' by Imre Lakatos, John Worrall, Gregory Currie, ISBN Philosophical Papers, Volume 2 by Imre Lakatos, John Worrall, Gregory Currie 1980 ISBN 0521280303 page 60-63</ref><ref>''Evidence, proof, and facts: a book of sources'' by Peter Murphy 2003 ISBN 0199261954 pages 1-2</ref><ref>''Logic in Theology - And Other Essays'' by Isaac Taylor 2010 ISBN 1445530139 pages 5-15</ref>[[.break.]]Pay tell the definition of evidence.[[.break.]]Evidence in its broadest sense includes everything that is used to determine or demonstrate the truth of an assertion. Giving or procuring evidence is the process of using those things that are either presumed to be true, or were in fact proven via evidence, to demonstrate an assertion's truth. Evidence is the currency by which one fulfills the burden of proof.[[.break.]]I comprehend that definition now. But I am confused as to what is truth.[[.break.]]Truth is most often used to mean in accord with fact or reality or fidelity to an original or to a standard or ideal.[[.break.]]Please define fact.[[.break.]]A fact is something that has really occurred or is actually the case. The usual test for a statement of fact is verifiability, that is whether it can be proven to correspond to experience. Standard reference works are often used to check facts. Scientific facts are verified by repeatable experiments.[[.break.]]Oh, I see. And what is Proof (truth)?[[.break.]]''A proof is sufficient evidence or an argument for the truth of a proposition.''<ref>''Proof and other dilemmas: mathematics and philosophy'' by Bonnie Gold, Roger A. Simons 2008 ISBN 0883855674 pages 12-20</ref><ref>''Philosophical Papers, Volume 2'' by Imre Lakatos, John Worrall, Gregory Currie, ISBN Philosophical Papers, Volume 2 by Imre Lakatos, John Worrall, Gregory Currie 1980 ISBN 0521280303 page 60-63</ref><ref>''Evidence, proof, and facts: a book of sources'' by Peter Murphy 2003 ISBN 0199261954 pages 1-2</ref><ref>''Logic in Theology - And Other Essays'' by Isaac Taylor 2010 ISBN 1445530139 pages 5-15</ref>[[.break.]]Please define evidence.[[.break.]]Evidence in its broadest sense includes everything that is used to determine or demonstrate the truth of an assertion. Giving or procuring evidence is the process of using those things that are either presumed to be true, or were in fact proven via evidence, to demonstrate an assertion's truth. Evidence is the currency by which one fulfills the burden of proof.[[.break.]]Please define truth.[[.break.]]Truth is most often used to mean in accord with fact or reality or fidelity to an original or to a standard or ideal.[[.break.]]Oh, I see. And what is fact?[[.break.]]A fact is something that has really occurred or is actually the case. The usual test for a statement of fact is verifiability, that is whether it can be proven to correspond to experience. Standard reference works are often used to check facts. Scientific facts are verified by repeatable experiments.[[.break.]]I comprehend that definition now. But I am confused as to what is Proof (truth).[[.break.]]";
			
			String[] lines = transcript4c.split("\\[\\[.break.\\]\\]");//.split("\n");
			println(lines);
			String voice1 = TTS.VICKI;
			String voice2 = TTS.BRUCE;
			for (int i=0; i<lines.length; i++) {
				if (i%2==0) {
					TTS.say(lines[i],voice1,280);
					delay(remainingDurationOfTTS(voice1, 280, lines[i]));
				} else {
					TTS.say(lines[i],voice2,260);
					delay(remainingDurationOfTTS(voice2, 260, lines[i]));
				}
			}
		}
		
		// DIALOGUE TEST
		if (key=='d') {
			String[] voices = {TTS.VICKI,TTS.BRUCE,TTS.TRINOIDS, TTS.BELLS, TTS.ZARVOX, TTS.HYSTERICAL};
			ArrayList<NetAddress> bots = new ArrayList<NetAddress>();
			for (int i=0; i<targetLocations.size(); i++) {
				NetAddress bot = targetLocations.get(i);
				bots.add(bot);
			}
			String transcript4c = "The Rocks is an urban locality, tourist precinct and historic area of Sydney's city centre, in the state of New South Wales, Australia. It is located on the southern shore of Sydney Harbour, immediately north-west of the Sydney central business district. The precinct and its immediate surroundings are administered independently of the local government area of the City of Sydney, by a New South Wales state government statutory authority, the Sydney Harbour Foreshore Authority. [[.break.]]What is tourist?[[.break.]]Tourism is travel for recreational, leisure or business purposes. The World Tourism Organization defines tourists as people \"traveling to and staying in places outside their usual environment for not more than one consecutive year for leisure, business and other purposes\". [[.break.]]What is travel?[[.break.]]Travel is the movement of people or objects between relatively distant geographical locations.[[.break.]]Please define people.[[.break.]]A people is a plurality of persons considered as a whole, as in an ethnic group or nation. Collectively, for example, Jews are known as \"the Jewish people\", European Gypsies comprise the bulk of \"the Romani people\", and Palestinian Arabs have started to be called \"the Palestinian people\".[[.break.]]Pay tell the definition of person.[[.break.]]A person is a being, such as a human, that has certain capacities or attributes constituting personhood, which in turn is defined differently by different authors in different disciplines, and less formally by different cultures in different times and places.[[.break.]]Please define human.[[.break.]]Humans are primates of the family Hominidae, and the only living species of the genus ''Homo''. They originated in Africa, where they reached anatomical modernity about 200,000 years ago and began to exhibit full behavioral modernity around 50,000 years ago.[[.break.]]Sorry, could you describe primate please.[[.break.]]A primate is a mammal of the order Primates , From Old French or French ''primat'', from a noun use of Latin ''primat-'', from ''primus'' . The English singular ''primate'' was derived via back-formation from the Latin inflected form. Linnaeus thought this the \"highest\" order of mammals</ref> which contains prosimians and simians. Primates arose from ancestors that lived in the trees of tropical forests; many primate characteristics represent adaptations to life in this challenging three-dimensional environment. All but a few primate species remain at least partly arboreal.[[.break.]]Pay tell the definition of mammal.[[.break.]]Mammals are members of class Mammalia , air-breathing vertebrate animals characterized by the possession of endothermy, hair, three middle ear bones, and mammary glands functional in mothers with young. Most mammals also possess sweat glands and specialized teeth. The largest group of mammals, the placentals, have a placenta which feeds the offspring during gestation. The mammalian brain, with its characteristic neocortex, regulates endothermic and circulatory systems, the latter featuring red blood cells lacking nuclei and a four-chambered heart. Mammals range in size from the 30Ð40 millimeter bumblebee bat to the 33-meter blue whale.[[.break.]]Sorry, could you describe class (biology) please.[[.break.]]In biological classification, class is* a taxonomic rank. Other well-known ranks are life, domain, kingdom, phylum, order, family, genus, and species, with class fitting between phylum and order. As for the other well-known ranks, there is the option of an immediately lower rank, indicated by the prefix ''sub-'': subclass .* a taxonomic unit, a taxon, in that rank. In that case the plural is classes [[.break.]]I comprehend that definition now. But I am confused as to what is biological classification.[[.break.]]Biological classification, or ''scientific classification in biology'', is a method of scientific taxonomy used to group and categorize organisms into groups such as genus or species. These groups are known as taxa .[[.break.]]Pay tell the definition of Taxonomy.[[.break.]]Taxonomy is the academic discipline of defining groups of biological organisms on the basis of shared characteristics and giving names to those groups. Each group is given a rank and groups of a given rank can be aggregated to form a super group of higher rank and thus create a hierarchical classification. The groups created through this process are referred to as taxa . An example of a modern classification is the one published in 2009 by the Angiosperm Phylogeny Group for all living flowering plant families .[[.break.]]Pay tell the definition of academic discipline.[[.break.]]An academic discipline, or field of study, is a branch of knowledge that is taught and researched at the college or university level. Disciplines are defined , and recognized by the academic journals in which research is published, and the learned societies and academic departments or faculties to which their practitioners belong. Pierce writes: \"Although most studies fail to define the term [discipline] explicitly, they typically assume that boundaries of disciplines closely follows those of academic departments. The use of such boundaries may seem to fix overly concrete limits on a highly abstract phenomenon, excluding too large a number of people with interest in the subject. But its importance in creating and maintaining disciplinary communities makes the academic department the building block from which disciplines are created\".<ref>Pierce, S. J. . Subject areas, disciplines and the concept of authority. LISR [Library and Information Science Research], 13, 21-35.</ref> [[.break.]]I comprehend that definition now. But I am confused as to what is knowledge.[[.break.]]Knowledge is a familiarity with someone or something, which can include facts, information, descriptions, or skills acquired through experience or education. It can refer to the theoretical or practical understanding of a subject. It can be implicit or explicit ; it can be more or less formal or systematic.<ref>http://oxforddictionaries.com/view/entry/m_en_us1261368#m_en_us1261368</ref> In philosophy, the study of knowledge is called epistemology; the philosopher Plato famously defined knowledge as \"justified true belief.\" However, no single agreed upon definition of knowledge exists, though there are numerous theories to explain it. The following quote from Bertrand Russell's \"Theory of Knowledge\" illustrates the difficulty in defining knowledge: \"The question how knowledge should be defined is perhaps the most important and difficult of the three with which we shall deal. This may seem surprising: at first sight it might be thought that knowledge might be defined as belief which is in agreement with the facts. The trouble is that no one knows what a belief is, no one knows what a fact is, and no one knows what sort of agreement between them would make a belief true. Let us begin with belief.\"[[.break.]]Please define fact.[[.break.]]A fact is something that has really occurred or is actually the case. The usual test for a statement of fact is verifiability, that is whether it can be proven to correspond to experience. Standard reference works are often used to check facts. Scientific facts are verified by repeatable experiments.[[.break.]]What is Proof (truth)?[[.break.]]''A proof is sufficient evidence or an argument for the truth of a proposition.''<ref>''Proof and other dilemmas: mathematics and philosophy'' by Bonnie Gold, Roger A. Simons 2008 ISBN 0883855674 pages 12-20</ref><ref>''Philosophical Papers, Volume 2'' by Imre Lakatos, John Worrall, Gregory Currie, ISBN Philosophical Papers, Volume 2 by Imre Lakatos, John Worrall, Gregory Currie 1980 ISBN 0521280303 page 60-63</ref><ref>''Evidence, proof, and facts: a book of sources'' by Peter Murphy 2003 ISBN 0199261954 pages 1-2</ref><ref>''Logic in Theology - And Other Essays'' by Isaac Taylor 2010 ISBN 1445530139 pages 5-15</ref>[[.break.]]Pay tell the definition of evidence.[[.break.]]Evidence in its broadest sense includes everything that is used to determine or demonstrate the truth of an assertion. Giving or procuring evidence is the process of using those things that are either presumed to be true, or were in fact proven via evidence, to demonstrate an assertion's truth. Evidence is the currency by which one fulfills the burden of proof.[[.break.]]I comprehend that definition now. But I am confused as to what is truth.[[.break.]]Truth is most often used to mean in accord with fact or reality or fidelity to an original or to a standard or ideal.[[.break.]]Please define fact.[[.break.]]A fact is something that has really occurred or is actually the case. The usual test for a statement of fact is verifiability, that is whether it can be proven to correspond to experience. Standard reference works are often used to check facts. Scientific facts are verified by repeatable experiments.[[.break.]]Oh, I see. And what is Proof (truth)?[[.break.]]''A proof is sufficient evidence or an argument for the truth of a proposition.''<ref>''Proof and other dilemmas: mathematics and philosophy'' by Bonnie Gold, Roger A. Simons 2008 ISBN 0883855674 pages 12-20</ref><ref>''Philosophical Papers, Volume 2'' by Imre Lakatos, John Worrall, Gregory Currie, ISBN Philosophical Papers, Volume 2 by Imre Lakatos, John Worrall, Gregory Currie 1980 ISBN 0521280303 page 60-63</ref><ref>''Evidence, proof, and facts: a book of sources'' by Peter Murphy 2003 ISBN 0199261954 pages 1-2</ref><ref>''Logic in Theology - And Other Essays'' by Isaac Taylor 2010 ISBN 1445530139 pages 5-15</ref>[[.break.]]Please define evidence.[[.break.]]Evidence in its broadest sense includes everything that is used to determine or demonstrate the truth of an assertion. Giving or procuring evidence is the process of using those things that are either presumed to be true, or were in fact proven via evidence, to demonstrate an assertion's truth. Evidence is the currency by which one fulfills the burden of proof.[[.break.]]Please define truth.[[.break.]]Truth is most often used to mean in accord with fact or reality or fidelity to an original or to a standard or ideal.[[.break.]]Oh, I see. And what is fact?[[.break.]]A fact is something that has really occurred or is actually the case. The usual test for a statement of fact is verifiability, that is whether it can be proven to correspond to experience. Standard reference works are often used to check facts. Scientific facts are verified by repeatable experiments.[[.break.]]I comprehend that definition now. But I am confused as to what is Proof (truth).[[.break.]]";
			
			String[] lines = transcript4c.split("\\[\\[.break.\\]\\]");
			println(lines);
			for (int i=0; i<lines.length; i++) {
				// take it in sequence.
				int botid = i%targetLocations.size();
				sendSpeech(lines[i],voices[botid],bots.get(botid));
				
				for (NetAddress bot : bots) {
					if (bot.equals(bots.get(botid))) {
						commandTray("open", bots.get(botid));
					} else {
						commandTray("close", bots.get(botid));
					}
				}
				
				delay(remainingDurationOfTTS(voices[botid], 250, lines[i]));
				
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
		
		// do a check for containment
		boolean targetExists = false;
		for (NetAddress target: targetLocations) {
			if (target.port()==newTargetLocation.port()) {
				if (target.address().equals(newTargetLocation.address())) {
					targetExists = true;
				}
			}
		}
		
		//if (!targetLocations.contains(newTargetLocation)) {
		if (!targetExists) {
			targetLocations.add(newTargetLocation);
			System.out.println("Added Client." + newTargetLocation);
			this.sendSpeech("Yay, the mothership knows I exist now.", newTargetLocation);
		} else {
			//this.sendSpeech("Mom already knows about me.", newTargetLocation);
		}
		
	}
	// Parse msgs from face detection sentient
	public void parseMessageHumanDetection(OscMessage theOscMessage) {
		String humanDetected = theOscMessage.get(0).stringValue();
		System.out.println("HUMAN DETECTED.");
		TTS.say(humanDetected);
	}
	
	// -----------------------------------------------------------------------------
	// COMMUNICATIONS: SPEECH COMMANDS
	
	public void sendSpeechToAll(String speech) {
		OscMessage myMessage = new OscMessage("/say");
		myMessage.add(speech);
		myMessage.add(TTS.FRED);
		//oscP5.send(myMessage, targetLocations.get(targetLocations.size()-1));
		// send to all.
		for (NetAddress targetLocation : targetLocations) {
			oscP5.send(myMessage,targetLocation);
		}
	}
	
	public void commandTray(String cmd, NetAddress targetLocation) {
		OscMessage myMessage = new OscMessage("/tray");
		myMessage.add(cmd);
		oscP5.send(myMessage, targetLocation);
	}
	
	public void sendSpeech(String speech, NetAddress targetLocation) {
		OscMessage myMessage = new OscMessage("/say");
		myMessage.add(speech);
		myMessage.add(TTS.FRED);
		oscP5.send(myMessage, targetLocation);
	}
	
	public void sendSpeech(String speech, String voice, NetAddress targetLocation) {
		OscMessage myMessage = new OscMessage("/say");
		myMessage.add(speech);
		myMessage.add(voice);
		oscP5.send(myMessage, targetLocation);
	}
	
	// -------------------------------------------------------------------------------
	

	public int remainingDurationOfTTS(String voice, int rate, String script) {
		int timea = millis();
		int ttsDuration = durationOfTTS(voice,rate,script);
		float timeb = millis();
		if (timea>timeb) timea=0; //<- simple fix to overlap of millis
		float timeToGenerateDuration = timeb-timea;
		
		int remainingSpeekTime = (int)(ttsDuration - timeToGenerateDuration);
		
		if (ttsDuration == -1) return -1;
		
		return remainingSpeekTime;
	}
	
	AudioPlayer player;
	Minim minim;
	public int durationOfTTS(String voice, int rate, String script) {
		// initialize minimum, if not initialized yet 
		if (minim==null) {
			minim = new Minim(this);
		}
		
		String audiofilename = "MisanthropicTemporaryAudioFile";
		
		try {
			
			int fileCheckInterval = 300; // <- too small and it erroneously thinks file hasn't changed.
			
			// Generate AIFF
			Runtime.getRuntime().exec(new String[] {"say", "-o", audiofilename+".aiff", "-v", voice, "[[rate " + rate + "]]" + script});
			
			File tempaiff = new File(audiofilename+".aiff");
			while(!tempaiff.exists()) {}
			// Wait to AIFF Finishes Generating.
			float prvLength = -1;
			while (tempaiff.length() != prvLength) {
				prvLength = tempaiff.length();
				//System.out.println("Generating aiff file ...");
				Thread.sleep(fileCheckInterval);
			}
			//System.out.println("Generated aiff file.");
			
			// Generate MP3.
			Runtime.getRuntime().exec("/usr/local/bin/lame "+audiofilename+".aiff "+audiofilename+".mp3");
			
			File tempmp3 = new File(audiofilename+".mp3");
			while (!tempmp3.exists()) {}
			prvLength = -1;
			while( tempmp3.length() != prvLength) {
				prvLength = tempmp3.length();
				//System.out.println("Generating mp3 file ...");
				Thread.sleep(fileCheckInterval);
			}
			//System.out.println("Generated mp3 file.");
			
			
			// GET LENGTH
			player = minim.loadFile(audiofilename+".mp3");
			int mp3length = player.length();
			//println("MP3 Length (ms): "+mp3length);
			
			// Clear all resources
			Thread.sleep(fileCheckInterval);
			
			//tempaiff.delete();
			//tempmp3.delete();
			
			return mp3length;
			
		} catch(Exception e) {
			System.err.println(e);
			
			return -1;
		}
	}
	
	
	
}
