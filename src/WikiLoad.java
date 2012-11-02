import java.util.ArrayList;
import java.util.Stack;

import processing.core.PApplet;
import processing.xml.XMLElement;

public class WikiLoad extends PApplet {
	private static final long serialVersionUID = 1L;

	public static void main(String args[]) {
		PApplet.main(new String[] { "--present", "WikiLoad" });
	}

	

	int swidth, sheight;
	
	public String randomSentenceGenerator(String previousTopic, String nextTopic) {
		ArrayList<String> sentences = new ArrayList<String>();
		String s = "";
		s = "What is [[.nextTopic.]]?";
		sentences.add(s);
		s = "Please define [[.nextTopic.]].";
		sentences.add(s);
		s = "Sorry, could you describe [[.nextTopic.]] please.";
		sentences.add(s);
		s = "Oh, I see. And what is [[.nextTopic.]]?";
		sentences.add(s);
		s = "Pray tell the definition of [[.nextTopic.]].";
		sentences.add(s);
		s = "Ah, thankyou. And what is [[.nextTopic.]].";
		sentences.add(s);
		s = "I comprehend that definition now. But I am confused as to what is [[.nextTopic.]].";
		sentences.add(s);
		
		String sentence = sentences.get(min((int)(Math.random()*sentences.size()),sentences.size()-1));
		sentence = sentence.replaceAll("\\[\\[.nextTopic.\\]\\]", nextTopic);
		return sentence;
	}

	public void setup() {
		swidth = 400;
		sheight = 400;
		size(swidth, sheight);
		colorMode(HSB, 100);
		
		String a = "";
		a = "Asclepiodotus_(consul_423)";
		
		a = "centralized_government";
		a = "Einar_Thorsrud";
		a = "List_of_shipwrecks_in_1933";
		a = "Electroshock_(wrestler)";
		a = "Sydney";
		a = "The_Rocks,_Sydney";
		
		String xml_url = "http://en.wikipedia.org/wiki/Special:Export/"+a;
		
		//String xml_url = "http://en.wikipedia.org/wiki/Special:Export/";
		
		
		for (int i=0; i<100; i++) {
			//println("[[ "+i+" > "+xml_url+"]]");
			Entry entry = new Entry(this, xml_url);
			//println(entry.snippet);
			
			String speechSegment = ommitNestedSquareBrackets(entry.snippet);
			speechSegment = speechSegment.replaceAll("'''","");
			speechSegment = speechSegment.replaceAll("  "," ");
			println(speechSegment);
			
			println("[[.break.]]");
			
			if (entry.links.size()!=0) {
				xml_url = entry.links.get(0).linkUrl;
				
				String nextTopic = entry.links.get(0).linkTitle;
				String randomSentence = this.randomSentenceGenerator("", nextTopic);
				println(randomSentence);
				println("[[.break.]]");
				
			} else {
				println("[[ No Links found in Entry ]]");
				break;
			}
			println("\n");
		}
		/*
		String listOfRandomTitles = "Lemon, Dark, Light , Black, High , Low, Cellphone, Cat, Dog, Penguin, Japan, Color, White, One, Brain, Pills, Pencil, Dragon, Mint, Chocolate, Pink, Green, Brush, Handle , Door, Knob, Mask, Knife, Speaker, Wood, Orient, Love";
		String listOfRandomTitles2 = "sap, sat, sad, rat, rap, ram, rag, nap, Nat, mat, map, mad, lap, lag, lad, fat, fan, fad, fin, fit, lid, lip, lit, mid, mitt, nit, nip, rid, rig, rim, rip, Sid, sin, sip, log, mom, mop, nod, rod, Ron, rot, sod, fun, mud, mum, nut, rug, rut, sum, sun, fed, led, leg, met, Ned, net, bag, bad, bam, bat, cap, cab, dad, Dan, gas, gag, ham, hat, jab, jam, pan, pat, tab, tag, tan, tap, bid, dig, dip, hid, hit, hip, Jim, jig, kin, kid, pin, pit, pig, tin, tip, Tim, cop, con, Don, dog, hop, hog, job, jog, pot, pop, top, Tom, bug, bud, bum, cup, cub, dud, dug, Gus, gun, hum, jug, pup, tub, tug, beg, bed, bet, hen, jet, Ken, pen, pet, peg, pep, the, of, and, a, to, in, is, you, that, it, he, was, for, on, are, as, with, his, they, I, at, be, this, have, from, or, one, had, by, word, but, not, what, all, were, we, when, your, can, said, there, use, an, each, which, she, do, how, their, if, will, up, other, about, out, many, then, them, these, so, some, her, would, make, like, him, into, time, has, look, two, more, write, go, see, number, no, way, could, people, my, than, first, water, been, call, who, oil, its, now, find, long, down, day, did, get, come, made, may, part, over, new, sound, take, only, little, work, know, place, year, live, me, back, give, most, very, after, thing, our, just, name, good, sentence, man, think, say, great, where, help, through, much, before, line, right, too, mean, old, any, same, tell, boy, follow, came, want, show, also, around, farm, three, small, set, put, end, does, another, well, large, must, big, even, such, because, turn, here, why, ask, went, men, read, need, land, different, home, us, move, try, kind, hand, picture, again, change, off, play, spell, air, away, animal, house, point, page, letter, mother, answer, found, study, still, learn, should, America, world, high, every, near, add, food, between, own, below, country, plant, last, school, father, keep, tree, never, start, city, earth, eye, light, thought, head, under, story, saw, left, don't, few, while, along, might, chose, something, seem, next, hard, open, example, begin, life, always, those, both, paper, together, got, group, often, run, important, until, children, side, feet, car, mile, night, walk, white, sea, began, grow, took, river, four, carry, state, once, book, hear, stop, without, second, late, miss, idea, enough, eat, face, watch, far, Indian, really, almost, let, above, girl, sometimes, mountain, cut, young, talk, soon, list, song, being, leave, family, it's, am, ate, best, better, black, blue, bring, brown, buy, clean, cold, done, draw, drink, eight, fall, fast, five, fly, full, funny, gave, giving, goes, green, hold, hot, hurt, jump, laugh, myself, pick, please, pretty, pull, ran, red, ride, round, seven, shall, sing, sit, six, sleep, ten, thank, today, upon, warm, wash, wish, yellow, yes, act, ant, bake, band, bank, bell, belt, Ben, bend, bent, Bess, bike, bit, bite, blast, bled, blend, blimp, blink, bliss, block, blond, blot, bluff, blunt, bone, brag, brand, brass, brat, bred, bride, brig, brim, broke, brunt, brute, bump, bun, bunt, bust, camp, cane, can't, cape, cast, cat, clad, clam, clamp, clan, clap, clasp, class, cliff, cling, clink, clip, close, clot, club, clump, clung, cone, crab, craft, cram, cramp, crib, crime, crisp, crop, crust, cure, cute, dam, damp, den, dent, dim, dime, dine, dire, dive, dope, draft, drag, drank, dress, drift, drill, drip, drop, drove, drug, drum, dump, dust, eve, fact, fade, fell, felt, file, fill, film, fine, fire, fist, flag, flap, flat, fled, fling, flip, flop, flung, flunk, frame, frank, frill, frisk, frog, frost, froze, fume, gasp, gaze, glad, gland, glass, glint, globe, glum, golf, grab, grade, gram, gramp, grand, grant, grape, grasp, grass, grill, grim, grin, grip, gripe, grump, grunt, gulp, gust, gut, hate, held, hide, hint, hire, hole, honk, hope, hose, hug, hung, hunt, hut, ink, Jan, Jane, joke, junk, kept, kit, kite, lamp, lick, lift, lime, limp, lock, luck, lump, Mack, mask, mass, mast, mate, melt, mend, Mick, Mike, milk, mill, mint, mist, mite, mope, mule, mute, neck, nest, nine, nose, note, pane, pant, pass, past, pest, Pete, pike, pile, pill, pine, plan, plane, plank, plate, plop, plot, plug, plum, plump, plus, poke, pole, pomp, pond, pope, prank, press, pride, prime, print, prize, prop, punk, pure, raft, ripe, robe, rock, rode, romp, rope, rose, Runs, runt, rust, sack, sake, Sam, sand, sank, save, scab, scale, scalp, scan, scare, scat, scope, scram, scrap, script, self, sell, send, sent, sick, site, size, skate, skid, skill, skim, skin, skip, skit, skunk, slam, slang, slant, slap, slat, slate, slave, sled, slept, slide, slim, sling, slip, slob, slope, slot, slug, slum, slump, smack, smell, smile, smog, smoke, smug, snack, snag, snake, snap, sniff, snip, snipe, snub, snug, sock, soft, span, spank, spat, sped, spend, spent, spill, spin, spine, spit, spite, splat, splint, split, spoke, spot, spun, spunk, stab, stack, stale, stamp, stand, stem, step, stiff, sting, stink, stomp, stone, stove, strand, strap, strip, stripe, struck, strung, stuck, stump, stun, suck, sung, swam, swang, swell, swift, swim, swing, swung, tack, tam, tame, tape, tent, test, tide, tile, till, tilt, trade, tramp, trap, trend, trick, trim, trip, trot, trunk, trust, twang, twig, twin, twist, van, vane, wave, weld, wet, win, wind, wine, wire, yoke, absent, admit, album, ball, bang, banging, basket, bathtub, bedbug, bench, bib, bill, blank, blasted, blended, blush, bobsled, box, branch, brave, brush, bunch, bus, camping, care, case, catnip, cave, chest, chill, chin, chip, chipmunk, chop, chunk, clock, cloth, contest, crack, crash, crashing, crept, cross, crush, cuff, dash, deck, dentist, dish, disrupt, disrupted, dot, drinking, dusted, expanded, fang, fib, finishing, fish, fix, flute, fog, fox, Fran, fuss, gift, goblin, gum, hall, hang, Hank, himself, hotrod, huff, hunted, index, insisted, insisting, insulted, invent, invented, Jack, jumping, king, kiss, lane, lapdog, lasted, lending, loft, lost, lug, lunch, lung, mall, mascot, math, mess, mob, mug, napkin, pack, Pam, path, picnic, pigpen, pinball, pinch, planted, plastic, problem, public, publishing, puff, punishing, quake, rake, rash, rented, rest, rested, rich, ring, ringing, rub, safe, sale, sang, sash, shack, shed, shelf, shell, shifted, shine, ship, shop, shrimp, shrinking, shrunk, shut, sink, sinking, sits, splash, splashing, squinted, standing, Steve, stub, stuff, stunt, sub, sunfish, sunk, sunlit, sunset, suntan, swishing, talented, tall, tank, throne, thud, tick, tilted, tiptop, toss, trusted, twisted, upset, vent, vest, wag, wall, wax, whiff, whip, whiplash, wig, wing, wink, wipe, wise, yell, zap, zigzag, called, circle, friend, Mr., Mrs., nothing, says, Ann, Beth, Bing, Blanch, bong, Buck, burnt, Burt, Carmen, Cass, caterpillar, Chad, cheer, chomp, Chuck, chuckle, chug, Clark, click, climb, clown, cluck, coal, complain, croak, crunch, Curtis, curve, darts, dinner, diver, doctor, doll, door, Doris, Ellen, fellow, fiddle, firm, fishing, Fitch, foam, forever, forgot, forth, front, Gert, gobble, gone, gosh, granddad, grandpa, grinned, grumble, hatch, Herb, hill, horse, hush, insect, Jeff, jiggle, Jill, Jill's, Josh, jumble, kick, Kim, Kirk, larch, library, lived, lives, market, Mitch, mix, napper, nibble, Nick, Norm, Oh, onto, owner, patch, peck, perfect, ping, Pip, pong, quick, quill, quilt, Quinn, quit, reward, Rex, Rick, Rivera, roam, ruff, Sanchez, served, Seth, sister, Sloan, smash, snort, snuggle, soup, sparkle, sprinkle, squirt, stick, sudden, sunburn, surprise, swimmer, Tad, tadpole, Ted, Tess, Texas, tickle, toad, Todd, turf, twinkle, twitch, umbrella, uncle, wham, whirl, whisper, whistle, wiggle, window, Winkle, writing, yet, York, zing, zip, able, ace, added, afraid, afternoon, age, ahead, annoy, anything, anyway, anywhere, ape, applaud, arm, artist, attack, attic, auto, avoid, awesome, awful, awning, babble, baby, baffle, bald, ballplayer, bark, base, baseball, basin, basketball, batch, bath, battle, beach, bead, beam, bean, beanstalk, beast, beat, bedtime, bee, beef, beehive, beet, begging, behind, bird, birthday, bleach, blew, blind, bloom, blow, blown, bluebird, blueprint, blur, boast, boat, bob, body, boil, bold, bonnet, bonus, boost, boot, born, bottle, bowl, brace, braid, brain, brainstorm, brake, brawl, bread, breakfast, breath, brick, bright, broil, broiler, broom, bruise, bubble, buddy, built, bundle, bunk, bunny, burn, burst, buses, butterfly, button, buzz, cabin, cage, cake, camel, candle, candy, careful, cart, catch, cattle, cell, cent, chain, chalk, champ, charge, chart, chase, chat, check, cheerful, cheese, chess, chew, chick, child, chime, chirp, choice, chore, church, churn, claim, classmate, clay, clerk, clever, clue, clutch, coach, coat, coax, coil, coin, collect, colorful, cool, core, cork, corn, cowboy, cozy, crate, crawl, cream, crew, crinkle, crow, cruise, cry, cuddle, cupcake, curb, curl, dab, daddy, dangle, Danny, dark, dart, date, dawn, daylight, dead, deaf, dealt, decent, deep, delight, desk, die, dimple, dirt, ditch, doghouse, double, dragon, dragonfly, drain, dread, dream, drew, driveway, droop, dry, duck, due, dunk, dusk, easel, easy, egg, elbow, enjoy, ever, evergreen, everyone, everything, everywhere, explore, fabric, fail, faithful, fame, fault, fawn, feast, feed, feel, fence, fern, fetch, fifty, fight, finish, firefighter, flagpole, flash, flashlight, flaunt, flaw, flight, float, flow, fluffy, foal, foil, fold, fool, forest, forget, fork, form, fort, fraud, fray, free, fresh, Friday, fried, fright, frozen, fruit, fry, fur, game, garden, gate, gel, gem, germ, giggle, ginger, giraffe, gleam, glean, glow, gluestick, goat, going, gold, goose, Grace, graceful, grain, grapefruit, grasshopper, grateful, gray, grew, groan, grown, grown-up, growth, gumball, habit, hail, handwriting, happen, happy, harm, harmful, harp, haul, haunt, hawk, hay, health, heat, heavy, heel, hello, helpful, herself, hi, hidden, highway, hoist, homemade, homework, hoot, hopeful, hopscotch, horn, host, houseboat, housefly, huddle, human, humid, hummingbird, hump, hungry, hunk, hurdle, hurl, ice, inch, inside, instead, itch, Jake, jar, jaw, Jean, jeep, jelly, jellyfish, Jen, jerk, Jimmy, jingle, Jo, Joan, join, joint, joy, juggle, juice, jungle, kickball, kingfish, kits, kitten, kitty, knack, knee, kneecap, kneel, knelt, knew, knife, knight, knit, knob, knock, knockout, knot, knothole, known, lace, lack, lake, latch, launch, law, lawn, lay, lazy, leap, leather, Lee, lemon, less, lie, lighthouse, limit, link, lipstick, living, load, loaf, loop, loose, lot, low, lunchbox, Mabel, mail, main, mane, march, mark, match, Matt, meant, meat, meet, mice, middle, mild, mind, mine, mitten, moan, model, moist, mold, moment, Monday, moo, mood, motel, mow, munch, music, muzzle, nab, nail, nanny, Nate, neat, necktie, nerve, newscast, newspaper, nice, nickname, nighttime, nips, noise, noon, north, nowhere, oak, oat, oatmeal, object, oink, ooze, outside, ox, pace, paddle, pail, pain, painful, paint, pal, pancake, paperback, park, pause, paw, pawn, pay, peace, peaceful, peach, peak, peddle, peep, pencil, pennies, penny, perch, piano, pie, pillow, pilot, pink, pipe, pitch, ploy, poison, pony, pooch, poodle, pool, popcorn, porch, port, post, poster, potpie, pretzel, price, proof, pupil, puppy, purple, purse, puzzle, quack, queen, quicksand, rabbit, race, rack, rage, rail, rain, rainbow, ranch, rang, rattle, raw, ray, reach, ready, recycle, refund, renew, restful, return, ribbon, rice, riddle, rind, rink, rise, road, roast, rob, robin, robot, room, roost, row, Roy, royal, ruled, rumble, runway, rush, saddle, sag, sail, sailboat, salad, sample, sandbox, sandpaper, sandy, Saturday, sauce, saucer, scarf, scold, scorch, score, scrape, scratch, scream, screen, screw, scrub, seal, seat, serve, settle, shade, shadow, shake, shaking, shameful, shape, share, shark, sharp, sheep, shipwreck, shirt, shore, short, shrink, shrub, shrug, shy, sidewalk, sigh, sight, silence, silly, simple, sips, sir, siren, sky, slice, slick, slight, slow, slur, sly, smart, smiling, smooth, snail, sneak, snooze, snore, snow, snowball, snowflake, snowman, soak, soap, sofa, soil, someone, somewhere, sore, sort, soy, space, spark, speak, splendid, splotch, spoil, spool, spoon, sport, sprain, sprawl, spray, spread, spring, sprint, spruce, spur, squawk, stage, star, startle, stay, steam, steep, stern, stiffer, stir, stool, store, stork, storm, strain, strange, straw, stray, stream, street, stretch, strict, strike, string, strong, stung, sue, suit, suitcase, sunblock, Sunday, sunflower, sunny, sunrise, sunshine, surf, sway, sweat, sweep, sweet, swerve, swimming, swirl, switch, tablet, tail, taps, tattle, taught, tea, teach, teacher, team, tease, teeth, tennis, thankful, thick, thigh, thimble, thin, thinner, thinnest, third, thirst, thorn, thread, threat, thrill, throat, throw, Thursday, tie, tiger, tight, tired, toast, toil, told, tool, tooth, topcoat, torch, torn, tot, tow, toy, trace, traffic, train, trash, travel, tray, tread, treetop, trouble, truck, trumpet, Tuesday, tugboat, tulip, tumble, tummy, turtle, tusk, twirl, ugly, unzip, useful, value, vanish, verb, verse, vine, visit, visitor, voice, void, vote, waddle, wage, wagon, wait, wake, waves, wealth, weather, weave, web, Wednesday, weed, week, weekend, wettest, whack, whale, whatever, wheat, wheel, whenever, whisk, whiz, wide, wife, wild, windmill, wonderful, wore, worn, wrap, wreath, wreck, wrench, wriggle, wrinkle, wrist, wristwatch, written, wrong, wrote, yak, yard, yardstick, yarn, yawn, yum, Zack, zebra, zoo, zoom, ban, bar, winter, bay, wear, bye, mitten, aid, aim, arc, art, ash, axe, cog, cot, cow, dew, ear, eel, elf, elk, elm, era, fee, fig, fir, flu, gap, guy, icy, inn, ivy, Jay, key, nag, oar, odd, owe, owl, pea, pod, rib, sew, ski, sob, son, sow, spa, spy, tar, tax, toe, vet, war, won, yap, zen, ax, bait, barn, bawl, beak, birth, blab, blade, blame, blaze, bleed, bless, blip, blurb, blurt, bog, bond, boo, books, booth, bow, boys, brad, bran, brawn, brisk, brook, broth, brother, card, cash, cask, caw, cheap, cheek, chow, clack, clang, clash, claw, cloak, clod, coast, cob, cod, code, cook, coop, cord, cost, count, coy, crane, crick, crock, crook, crown, crude, crumb, cube, cue, dear, disk, dock, doze, drake, drape, drawn, drive, dude, east, Ed, faint, flake, flame, flesh, flick, flirt, flit, flock, floor, flour, flown, flush, fond, foot, fowl, Fred, fret, frizz, frying, fuse, gang, glade, glaze, glee, glen, glide, glob, gloss, goal, gown, grate, greet, grit, grouch, growl, gruff, heal, heap, hem, hike, hood, hoof, hook, hoop, howl, huge, husk, imp, intern, jail, jib, June, jut, laid, lame, leak, lend, lent, lien, lint, loan, loin, lone, loot, lop, lord, loud, lurk, lute, maid, maze, meal, mirth, mock, mole, moon, mouse, mouth, murk, musk, nook, nurse, pad, paid, peek, peel, pelt, perk, plain, plants, player, plod, plow, plums, pork, prance, pray, prayer, present, prim, prince, probe, prod, program, proud, prowl, pry, punt, raid, rant, real, rent, risk, Rome, roof, rude, rung, scald, scar, scoop, scoot, scout, scowl, scuff, scum, seek, seen, shawl, sheet, shirk, shook, shoot, shot, shout, shown, sift, silk, sixth, skips, skirt, skull, slash, slaw, slay, slid, slit, slop, smear, smith, smock, smoky, snare, snatch, sneeze, snob, snoop, snuff, soot, sour, south, speck, speech, speed, spike, spry, spud, spurn, spurt, stain, stall, Stan, steal, stood, sulk, swamp, swan, swap, swarm, swat, swatch, swept, swig, swine, swish, swoop, sworn, swum, tale, task, tend, tenth, term, thumb, tint, tock, town, toys, track, trail, tram, trek, tribe, trod, Troy, tune, tweed, tweet, twelve, twenty, twice, twine, twitter, urge, urn, uses, vase, vow, wade, wed, wept, west, whine, whir, wick, wilt, woke, wood, woof, wool, wove, wow, zest, zone, against, American, among, asked, course, days, didn't, during, eyes, general, given, however, later, order, possible, president, rather, since, states, system, things, though, toward, united, used, years, color, suddenly, zipper, ah, ho, Ma, Pa, TV, ago, baa, God, hoe, I'd, I'm, sis, ill, O.K, ache, aunt, bare, bear, boom, busy, dare, lose, love, none, rule, sure, tire, tube, copy, edge, else, fizz, glue, hair, half, hour, I'll, I've, July, kill, lady, lamb, leaf, liar, lion, mama, mash, meow, mush, obey, okay, ouch, oven, papa, pour, push, roll, shoe, soda, tiny, worm, TRUE, U.S., agree, alive, apple, April, awake, blood, break, chair, cloud, cough, cover, dance, erase, phone, piece, whole, whose, adult, angry, belly, death, eagle, empty, extra, hurry, maybe, money, movie, nasty, party, pizza, quiet, sorry, stair, sugar, table, taste, threw, touch, towel, truth, waist, waste, woman, women, won't, worse, anyone, arrive, asleep, August, avenue, behave, bridge, carpet, cereal, choose, cookie, corner, crayon, danger, minute, banana, bucket, carrot, dollar, finger, flower, gentle, handle, listen, mirror, monkey, nickel, nobody, orange, parent, person, pocket, potato, puppet, rocket, search, secret, seesaw, shovel, shower, silent, spider, sponge, squash, squeak, sticky, summer, supper, they'd, ticket, tiptoe, tissue, tomato, tongue, turkey, unfair, wonder, U.S.A., airport, anybody, balloon, bedroom, bicycle, brownie, cartoon, ceiling, channel, chicken, garbage, promise, squeeze, address, blanket, earache, excited, good-bye, grandma, grocery, indoors, January, kitchen, lullaby, monster, morning, naughty, October, pajamas, pretend, quarter, shampoo, stomach, there's, they'll, they're, they've, tonight, unhappy, airplane, alphabet, bathroom, favorite, medicine, December, dinosaur, elephant, February, football, forehead, headache, hospital, lollipop, November, outdoors, question, railroad, remember, sandwich, scissors, shoulder, softball, tomorrow, upstairs, vacation, restroom, astronaut, beautiful, bumblebee, cardboard, chocolate, Christmas, classroom, cranberry, drugstore, furniture, milkshake, nightmare, telephone, difficult, everybody, hamburger, September, spaceship, spaghetti, stoplight, underwear, yesterday, ice cream, automobile, blackboard, downstairs, photograph, strawberry, television, toothbrush, toothpaste, baby-sitter, post office, grandfather, grandmother, potato chip, upside down, kindergarten, refrigerator, Thanksgiving, hide-and-seek, United States, pumpkin, salt, melted, handed, printed, landed, wanted, filled, showed, hugged, tugged, planned, jogged, spilled, smelled, grilled, slammed, rushed, spelled, saved, baked, named, lined, smiled, closed, helped, jumped, looked, clapped, tapped, kicked, dropped, zipped, wished, pitched, missed, walked, worked, she'll, you'll, he'll, we're, she's, he's, you're, we're, jazz";
		String[] list = listOfRandomTitles2.split(",");
		
		for (int j=753; j<list.length; j++) { //753 - last one before leaving - no error thus far.
			String title = list[j];
			title = title.trim();
			title = title.replace(" ", "_");
			
			String xml_url = "http://en.wikipedia.org/wiki/Special:Export/"+title;
			
			for (int i=0; i<10; i++) {
				//println("[[ "+j+":"+i+" > "+xml_url+"]]");
				Entry entry = new Entry(this, xml_url);
				
				if (entry.rawtext==null) {
					println("[[Entry not found.]]");
					println();
					break;
				}
				
				//println("::: "+entry.snippet);
				String speechSegment = ommitNestedSquareBrackets(entry.snippet);
				speechSegment = speechSegment.replaceAll("'''","");
				speechSegment = speechSegment.replaceAll("  "," ");
				println(speechSegment);
				
				if (entry.links.size()!=0) {
					xml_url = entry.links.get(0).linkUrl;
					
					String nextTopic = entry.links.get(0).linkTitle;
					String randomSentence = this.randomSentenceGenerator("", nextTopic);
					println(randomSentence);
					println("[[.break.]]");
					
				} else {
					println("[[No Links found in Entry]]");
					println();
					break;
				}
				println();
			}
		}
		*/
		
		//String b = " '''asdf'''([[this should not exist]]) pablo [[(syd)]] [[ a [[b]] ]] (asdf (bee) ) bbb fbee (ccc) -";
		//println(b);
		//println(ommitUnprotectedParenthesis(b));
		
	}
	
	// Gawd, I really have to learn GREP.
	String ommitUnprotectedParenthesis(String rawtext) {
		String text = rawtext;
		
		String parsedtext = "";
		String remainingtext = text;
		
		//println("OmmitUnprotected(): RawText: "+rawtext);
		
		
		while (true) {
			int indexProtected = remainingtext.indexOf("[[");
			int indexParenthesis = remainingtext.indexOf("(");
			if (indexProtected == -1) indexProtected=Integer.MAX_VALUE;
			if (indexParenthesis == -1) indexParenthesis = Integer.MAX_VALUE;
			
			int closestSymbol = min(indexProtected,indexParenthesis);
			if (closestSymbol == Integer.MAX_VALUE) {
				parsedtext += remainingtext;
				return parsedtext;
			}
			
			if (closestSymbol == indexParenthesis) {
				String firstNestedParenthesis = findFirstNestingString("(",")",remainingtext);
				remainingtext = remainingtext.replace(firstNestedParenthesis, "");
			}
			if (closestSymbol == indexProtected) {
				
				String protectedNest = findFirstNestingString("[[","]]",remainingtext);
				
				String subtext = remainingtext.substring(0,indexProtected);
				parsedtext += subtext + protectedNest;
				int cutIndex = indexProtected+protectedNest.length();
				remainingtext = remainingtext.substring(min(cutIndex,remainingtext.length())); 
				
			}
		}
		
	}

	
	// Note, [[File: , is a special case that has to be dealt with.
		// some [['s are nested - and have to be treated accordingly.
	// Note, Not all XML elements have been excluded.
	public class Entry {
		String rawtext;
		String snippet; //first paragraph.
		ArrayList<EntryLink> links = new ArrayList<EntryLink>();
		Entry(PApplet p, String xml_url) {
			//println("\tLoading XML...");
			initWithXMLURL(p, xml_url);
			
		}
		
		void initWithXMLURL(PApplet p, String xml_url) {
			
			XMLElement xml = new XMLElement(p, xml_url);
			//println("\tLoaded.");
			XMLElement textElement = xml.getChild("page/revision/text");
			if (textElement == null) {
				rawtext = null;
				links = null;
				snippet = null;
				return;
			}
			String page_text = textElement.getContent();
			
			
			
			if (page_text.toUpperCase().indexOf("#REDIRECT")==0) {
				// parse redirect link
				String firstNesting = findFirstNestingString("[[","]]",page_text);
				if (firstNesting.equals(page_text)) System.err.println("no redirect link found");

				EntryLink link = new EntryLink(firstNesting);
				initWithXMLURL(p, link.linkUrl);
				return;
			}
			
			
			String page_minimal = page_text;
			
			page_minimal = ommitNestedBrackets("{{", "}}", page_minimal);
			page_minimal = ommitNestedBrackets("<!--", "-->", page_minimal);
			page_minimal = ommitNestedMediaBrackets(page_minimal);
			page_minimal = ommitAllRefs(page_minimal);
			page_minimal = ommitUnprotectedParenthesis(page_minimal);
			
			page_minimal = ommitNestedBrackets("<noinclude>", "</noinclude>", page_minimal);
			page_minimal = page_minimal.replaceAll("__NOTOC__", "");
			page_minimal = page_minimal.trim();
			
			//println(page_minimal.substring(0,min(page_minimal.length(),1000)));
			
			initWithRawText(page_minimal);
		}
		
		void initWithRawText(String rawinputtext) {
			rawtext = rawinputtext;
			
			int indexOfFirstLink = rawtext.indexOf("[[");
			if (indexOfFirstLink==-1) indexOfFirstLink=0;
			String subtext = rawtext.substring(indexOfFirstLink);
			int indexOfNextBreak = subtext.indexOf("\n\n");
			if (indexOfNextBreak == -1) {
				indexOfNextBreak = rawtext.length();
			} else {
				indexOfNextBreak = indexOfFirstLink + indexOfNextBreak;
			}
			
			snippet = rawtext.substring(0, indexOfNextBreak);
			snippet = parseRawtext(snippet);
			
			
		}
		
		
		Entry(String rawinputtext) {
			initWithRawText(rawinputtext);
		}
		
		
		
		String parseRawtext(String textToParse) {
			String parsedtext = "";
			String remainingtext = textToParse;
			while (true) {
				String firstNesting = findFirstNestingString("[[","]]",remainingtext);
				if (firstNesting.equals(remainingtext)) {
					parsedtext += remainingtext;
					break;
				}
				
				EntryLink link = new EntryLink(firstNesting);
				links.add(link);
				
				String newNesting = link.rawLink+link.linkText;
				
				int firstNestingIndex = remainingtext.indexOf(firstNesting);
				parsedtext += remainingtext.substring(0,firstNestingIndex);
				parsedtext += newNesting;
				
				int endIndex = firstNestingIndex + firstNesting.length();
				remainingtext = remainingtext.substring(endIndex);
			}
			return parsedtext;
		}
	}
	
	public class EntryLink {
		int linkPositionInParent;
		String linkUrl;
		String linkTitle;
		String linkText;
		String rawLink;
		EntryLink() {
		}
		EntryLink(String rawLink) {
			this.rawLink = rawLink;
			this.parseRawLink();
		}
		void parseRawLink() {
			String cleanLink = rawLink.substring(2,rawLink.length()-2);
			
			int pipeIndex = cleanLink.indexOf("|");
			if (pipeIndex!=-1) {
				this.linkTitle = cleanLink.substring(0,pipeIndex);
				this.linkText = cleanLink.substring(pipeIndex+1);
			} else {
				this.linkTitle = cleanLink;
				this.linkText = cleanLink;
			}
			String append = this.linkTitle.replaceAll(" ", "_");
			this.linkUrl = "http://en.wikipedia.org/wiki/Special:Export/"+append;
		}
		public String toString() {
			String s = "";
			s += rawLink +"\n";
			s += "\t"+linkTitle+"_|_"+linkText;
			return s;
		}
	}

	public void draw() {

	}

	public void keyPressed() {

	}

	// --------------------------------------------------------------------------------------------
	// STRING PARSING FUNCTIONS - Would be much simpler if I knew regex/grep
	// though.

	/**
	 * really not the most efficient way of doing this, but good enough for now
	 */
	public String ommitAllRefs(String text) {
		String prvText = text;
		while (true) {
			String curText = ommitFirstRemainingRef(prvText);
			if (curText.equals(prvText)) {
				return curText;
			} else {
				prvText = curText;
			}
		}
	}

	public String ommitFirstRemainingRef(String text) {
		String cleanedText = text;

		String head = "<ref";
		String tail1 = "/>";
		String tail2 = "</ref>";
		int indexA = text.indexOf(head);
		if (indexA == -1)
			return cleanedText;

		String subtext = text.substring(indexA);
		int indexB1 = subtext.indexOf(tail1);
		int indexB2 = subtext.indexOf(tail2);

		if (indexB1 == -1)
			indexB1 = Integer.MIN_VALUE;
		if (indexB2 == -1)
			indexB2 = Integer.MIN_VALUE;

		int indexB = min(abs(indexB1), abs(indexB2));
		String tail = tail1;
		if (indexB == indexB2)
			tail = tail2;
		if (indexB == abs(Integer.MIN_VALUE))
			return cleanedText;

		indexB = indexB + indexA + tail.length();

		String newPrefix = cleanedText.substring(0, indexA);
		String newSuffix = cleanedText.substring(indexB);
		String newString = newPrefix + newSuffix;
		cleanedText = newString;

		return cleanedText;
	}
	
	public String ommitNestedSquareBrackets(String rawtext) {
		String text = rawtext;
		while(true) {
			int indexA1 = text.indexOf("[[");
			int indexA2 = text.indexOf("[[");
			
			if(indexA1==-1) indexA1 = Integer.MAX_VALUE;
			if(indexA2==-1) indexA2 = Integer.MAX_VALUE;
			int indexA = min(indexA1, indexA2);
			
			if (indexA==Integer.MAX_VALUE) return text;
			String subtext = text.substring(indexA);
			String nest = findFirstNestingString("[[", "]]", subtext);
			//println(nest);
			text = text.replace(nest, "");
		}
	}
	
	public String ommitNestedMediaBrackets(String rawtext) {
		String text = rawtext;
		
		// find index of first [[File:
			// cut substring to this index
				// find first nestedstring (assured to start at zero)
		// with text file, find and ommit nestedstring.
		while(true) {
			int indexA1 = text.indexOf("[[File:");
			int indexA2 = text.indexOf("[[Image:");
			
			if(indexA1==-1) indexA1 = Integer.MAX_VALUE;
			if(indexA2==-1) indexA2 = Integer.MAX_VALUE;
			int indexA = min(indexA1, indexA2);
			
			if (indexA==Integer.MAX_VALUE) return text;
			String subtext = text.substring(indexA);
			String nest = findFirstNestingString("[[", "]]", subtext);
			//println(nest);
			text = text.replace(nest, "");
			
			//println("------");
			//println(text);
//			
//			try {
//				Thread.sleep(5000L);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
		}
		
		//return text;
		
	}

	public String findFirstNestingString(String frontBracket, String backBracket, String text) {
		
		Stack<Integer> bracketStack = new Stack<Integer>();
		int initIndex = text.indexOf(frontBracket);
		if (initIndex == -1) return text;
		bracketStack.add(1);
		
		int currIndex = initIndex + frontBracket.length();
		
		
		while(bracketStack.size() != 0) {
			String substring = text.substring(currIndex);
			int nextFront = substring.indexOf(frontBracket);
			int nextBack = substring.indexOf(backBracket);
			if (nextFront == -1) nextFront = Integer.MAX_VALUE;
			if (nextBack == -1) nextBack = Integer.MAX_VALUE;
			int nextBracket = min(nextFront,nextBack); //<- Note: apparently abs isn't polarising Integer.MIN_VALUE;
			if (nextBracket == Integer.MAX_VALUE) return text;
			
			if (nextBracket == nextFront) {
				bracketStack.add(1);
				currIndex = currIndex + nextBracket + frontBracket.length();
			} else if (nextBracket == nextBack) {
				bracketStack.pop();
				currIndex = currIndex + nextBracket + backBracket.length();
			}
		}
		
		int finiIndex = currIndex;
		
		String selectedString = text.substring(initIndex, finiIndex);
		
		return selectedString; //returns original string if none found
	}
	
	public String ommitNestedBrackets(String frontBracket, String backBracket, String text) {
		String cleanedText = text;
		
		Stack<Integer> doubleCurlyStack = new Stack<Integer>();
		while (cleanedText.indexOf(frontBracket) != -1) { // while
			doubleCurlyStack.add(1);

			int curlyInitIndex = cleanedText.indexOf(frontBracket);
			int curlyFiniIndex = -1; //
			int curlyCurrIndex = curlyInitIndex + frontBracket.length();

			while (doubleCurlyStack.size() != 0) {
				String substring = cleanedText.substring(curlyCurrIndex);
				int nextCurlyFront = substring.indexOf(frontBracket);
				int nextCurlyEnd = substring.indexOf(backBracket);
				if (nextCurlyFront == -1)
					nextCurlyFront = Integer.MAX_VALUE;
				if (nextCurlyEnd == -1)
					nextCurlyEnd = Integer.MAX_VALUE;
				int nextCurlySign = min(nextCurlyFront, nextCurlyEnd);
				if (nextCurlySign == Integer.MAX_VALUE) {
					return cleanedText;
				}
				
				
				
				if (nextCurlySign == nextCurlyFront) {
					doubleCurlyStack.add(1);
					curlyCurrIndex = curlyCurrIndex + nextCurlySign + frontBracket.length();
				} else if (nextCurlySign == nextCurlyEnd) {
					doubleCurlyStack.pop();
					curlyCurrIndex = curlyCurrIndex + nextCurlySign + backBracket.length();
				}

			}
			curlyFiniIndex = curlyCurrIndex;
			
			String newPrefix = cleanedText.substring(0, curlyInitIndex);
			String newSuffix = cleanedText.substring(curlyFiniIndex);
			String newString = newPrefix + newSuffix;
			cleanedText = newString;

		}

		return cleanedText;
	}

	// --------------------------------------------------------------------------------------------
	// FUNCTION ARCHIVE (NOT USED, BUT MAY BE USEFUL IN THE FUTURE)

	/**
	 * All because regex/grep couldn't be figured out.
	 */
	public String ommitAllSections(String head, String tail, String text) {
		String prvText = text;

		while (true) {
			String curText = ommitFirstSection(head, tail, prvText);

			if (curText.equals(prvText)) {
				return curText;
			} else {
				prvText = curText;
			}
		}
	}

	public String ommitFirstSection(String head, String tail, String text) {
		String cleanedText = text;

		int indexA = text.indexOf(head);
		if (indexA == -1)
			return cleanedText;

		String subtext = text.substring(indexA);
		int indexB = subtext.indexOf(tail);
		if (indexB == -1)
			return cleanedText;

		indexB = indexB + indexA + tail.length();

		String newPrefix = cleanedText.substring(0, indexA);
		String newSuffix = cleanedText.substring(indexB);
		String newString = newPrefix + newSuffix;
		cleanedText = newString;

		return cleanedText;
	}

}
