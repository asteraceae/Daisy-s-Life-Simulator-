import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class lifeSimulatorMain {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		//introduction
		System.out.println("");
		System.out.println("########################################################################");
		System.out.println("");
		System.out.println("\r\n" + 
				"             \r\n" + 
				"                %%%;       *                      *\r\n" + 
				"   |  %%%;     %%%~%%%;               .                     .     *\r\n" + 
				" # |__/__%%%____/_/~%;%                           .\r\n" + 
				"     ___%%;______%%;%          .            *            *\r\n" + 
				"\" \" /~ %-//  \\ \\__%#%%_-%%;`\r\n" + 
				"   |  ~%-/_%` \\ \\_/%%#%%`                                            .\r\n" + 
				"#  | %%%#%     \\__/%%#%%;%`,\r\n" + 
				"  \"| ;%%%;`                              *                  .\r\n" + 
				"   |                            *                  (\r\n" + 
				"| #|            *        .   LIFE SIMULATOR                              .\r\n" + 
				"  ||         .                        . .        .\r\n" + 
				"   |                .                ` ' `               *\r\n" + 
				"#  |                             .'''. ' .'''.                   *\r\n" + 
				"  \"|  *           .                .. ' ' ..      .\r\n" + 
				"'  |                         *    '  '.'.'  '              .\r\n" + 
				"   |                              .'''.'.'''.\r\n" + 
				" \" |       .----------.          ' .''.'.''. '\r\n" + 
				"   |       |__________|            . . : . .\r\n" + 
				"   |_{}_{}/|__________|\\{}_{}_{} _'___':'___'_ {}_{}_{}_{}_{}_{}_{}_{}\r\n" + 
				"' #| || ||/____________\\|| || ||(_____________)|| || || || || || || ||\r\n" + 
				"lc'\\\"\"\"\"\"\"||          ||\"\"\"\"\"\"\"\"\"\"\"\"(     )\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\r\n" + 
				"\"\"\"\"\"     |            |            _)   (_             .^-^.  ~\"\"~\r\n" + 
				"                         ~\"\"~      (_______)~~\"\"\"~~     '._.'\r\n" + 
				"    ~~\"\"~~                     ~\"\"~                     .' '.\r\n" + 
				"                                                        '.,.'\r\n" + 
				"                                                           `'`'");
		System.out.println("                     a prototype program by daisy");
		System.out.println("");
		System.out.println("########################################################################");
		System.out.println("");
		
	//set some variables + objects
		//string answer for do / while loop
		//scanner for user input
		
		String answer;
		Scanner input = new Scanner(System.in);
	//set loops
		//do/while - to begin/end game
			//while (true) 1 - new year
			//while true 2 - establish menu options again
		do {
		
		//generate new stats - establish new stats object
		//generate mother - blank relation
		//generate father - using mother's parameters
		//generate player
		//generate menu options
		//import player into mother/father
		//generate blank event - avoid errors
		
		
		Statistics stats = new Statistics();
		Relation mother = new Relation(true, true, stats, "mother");
		Relation father = new Relation(stats, mother);
		Player player = new Player(stats, mother, father);
		Menu menu = new Menu(player, stats);
		mother.importPlayer(player);
		father.importPlayer(player);
		Event event = new Event();
		
		//generate a year
		//set int response for user input, loops for logistics
		
		stats.setYear();
		int response = 0, loops = 0;
		while(true) {
			
			
			//make sure person isnt dead before setting year
			if (player.checkDeath() == false) {
			System.out.println("Year " + stats.getYear() + " - Age " + player.getAge()); 
			System.out.println("");
			}
			//if person is dead, break the loop
			if (player.checkDeath() == true) {
				break;
				
			} 
			//makes sure name isnt printed every loop
			if (loops == 0) {
				System.out.println(player.toString(response));
					}
			
			//sets chances person + relations will suddenly die
			player.chanceDeath();
			mother.chanceDeath();
			father.chanceDeath();
			//check player education status for milestones
			player.checkEducation();
			//tell user "started school" if player turns 5
			if (player.getAge() == 5 && player.checkDropout() == false) {
				System.out.println("You started school.");
			}
			//tell user they started elementary if age = 14
			if (player.getAge() == 14 && player.checkDropout() == false) {
				System.out.println("You graduated elementary school and started high school.");
			}
			//prompt user for college major when age = 18
				//nursing, biology, cs, eng, business
			if (player.getAge() == 18 && player.checkDropout() == false) {
				int r, res;
				System.out.println("Congratulations! You graduated high school!\n Will you go to university? \n 1. Yes \n 2. No");
				while (true) {
					try {
						res = input.nextInt();
					} catch (InputMismatchException E) {
						res = 10;
					}
					if (res == 1) {
						while(true) {
							System.out.println("Choose a major: \n 1. Nursing \n 2. Biology \n 3. Computer Science \n 4. English \n 5. Business");
							try {
								r = input.nextInt();
							} catch (InputMismatchException E) {
								r = 10;
							}
							if (r == 1) {
								player.setMajor("Nursing");
								System.out.println("You chose Nursing.");	
								break;
							}
							else if (r == 2) {
								player.setMajor("Biology");
								System.out.println("You chose Biology.");
								break;
							}
							else if (r == 3) {
								player.setMajor("Computer Science");
								System.out.println("You chose Computer Science.");
								break;
							}	
							else if (r == 4) {
								player.setMajor("English");
								System.out.println("You chose English.");	
								break;
							}
							else if (r == 5) {
								player.setMajor("Business");
								System.out.println("You chose Business.");	
								break;
							}
							else if (r < 1 || r > 5) {
								System.out.println("That doesn't seem to be an option, try again.");
								input.nextLine();
								continue;
							}
						}
						break;
					}
				//else tell player they didnt go to university
					//set dropout = true, check education to establish
					else if (res == 2) {
						System.out.println("You did not go to University.");	
						player.dropout();
						player.checkEducation();
						break;
					}
					else if (res < 1 || res > 2) {
						System.out.println("That doesn't seem to be an option, try again.");
						input.nextLine();
						continue;
					}
				}
			}
			//if age = 22, tell user they graduated college with major x
			if (player.getAge() == 22 && player.checkDropout() == false) {
				System.out.println("I graduated college with a degree of " + player.getMajor() + ".");
				}
			// age = 12, establish sexuality
				//options girls, boys, both
				//while true loop for options based on user input
			if (player.getAge() == 12) {
				System.out.println("You're now old enough to start thinking about crushes.\nYou like:\n 1. girls\n 2. boys\n 3. both");
				while (true) {
					int choice = 0;
					try {
						choice = input.nextInt();
					} catch (InputMismatchException E) {
						System.out.println("That doesn't seem to be an option, try again.");
						input.nextLine();
					} 
					if ( (choice == 1 && player.getGender() == false) || (choice == 2 && player.getGender() == true) ) {
						player.setSexuality("heterosexual");
						break;
					} else if ((choice == 2 && player.getGender() == false) || (choice == 1 && player.getGender() == true) ){
						player.setSexuality("homosexual");
						break;
					}
					else if (choice == 3) {
						player.setSexuality("bisexual");
						break;
					}
					else if (choice >= 4) {
						System.out.println("That doesn't seem to be an option, try again.");
						input.nextLine();
						continue;
					}
				}
			}
			
			//action loop
			//if loop isnt the first, generate an event
			if (loops != 0) {
				event = new Event(player, stats);
				}
			
			//menu options = age, check stats, check relationships, open education, end life, try lottery, partner menu, job menu
			//latter 2 opens only if player is age >12 and >!6
			while (true) {
				System.out.println("");
				System.out.println("What would you like to do? \n 1. Age! \n 2. Check Stats \n 3. Check Relationships \n 4. Open Education Menu \n 5. End Life \n 6. Try the lottery.");
				if (player.getAge() >= 12) {
					System.out.println(" 7. Open Partner Menu");
				}
				if (player.getAge() >= 16) {
					System.out.println(" 8. Open Job Menu");
				}
				
				System.out.println("");
				//try/catch, if response isnt an int, set response to something out of menu so that it generates error message
					try {
						response = input.nextInt();
					}catch (InputMismatchException E) {
						//	System.out.println("That doesn't seem to be an option, try again.");
						response = 10;
						input.nextLine();
					} 
				
				//age
					if (response == 1) {
						player.age();
						mother.age();
						father.age();
						if (player.isDating() == true) {
							player.agePartner();
							}
						stats.increaseYear();
						break;
				//prints stats
					} else if (response == 2) {
						System.out.println(player.toString(response));
						continue;
				//relationships
					} else if (response == 3) {
						System.out.println("Mother: " + mother + "\nFather: " + father);	
						if (player.isDating() == true) {
						System.out.println("Partner: " + player.getPartner());
						}
						
					}
				//education
					//check then open
					else if (response == 4) {
						player.checkEducation();
						menu.openEducationMenu();
						continue;
					}
				
			//death function
					else if (response == 5) {
						//if person too young, don't kill
						//import failure message from txt file
						if (player.getAge() < 12) {
							String msg;
							Scanner a = new Scanner(new File("childNoSuicide.txt"));
							ArrayList<String> a2 = new ArrayList<String>();
							while (a.hasNextLine()){
								a2.add(a.nextLine());
							}
							a.close();
							msg = a2.get(new Random().nextInt(a2.size()));
							System.out.println(msg);
							System.out.println("You don't even understand life and death yet. Choose something else.");
							continue;
						
						}
						//allow chance of death
						//chance of failure is 40%
						//import failure message from txt file
						else {
						stats.chanceRandom();
							if (stats.chanceRandom() <= 0.4) {
								String msg;
								Scanner a = new Scanner(new File("noSuicide.txt"));
								ArrayList<String> a2 = new ArrayList<String>();
								while (a.hasNextLine()){
									a2.add(a.nextLine());
								}
								a.close();
								msg = a2.get(new Random().nextInt(a2.size()));
								System.out.println(msg);
								continue;
							}
							else {
								player.commitSuicide();
								break;
							}
						}
						
					}
				//lottery
					else if (response == 6) {
						menu.lottery();
						continue;
					}
				//partner menu
					else if (response == 7){
						if (player.getAge() < 12) {
							System.out.println("That doesn't seem to be an option, try again.");
							continue;
						}
						else {
						player.checkDating();
						menu.openPartnerMenu();
						continue;

						}
					}
				//job menu
					else if (response == 8){
						if (player.getAge() < 12) {
							System.out.println("That doesn't seem to be an option, try again.");
							continue;
						}
						else {
						menu.openJobMenu();
						continue;
						}
					}
				//error message
					else if (response >= 8 || response < 1){
						System.out.println("That doesn't seem to be an option, try again.");
						continue;
					}
				//safechecking so that loop doesnt repeat
				if (response == 1 || player.isDead() == true) {
					break;
				}
				else {
					continue;
				}
			}
			//add 1 to loop
			loops++;
			continue;
		}
	//death
		//decide who goes to funeral
		//determine what cause of death was
		//print the obituary
		
	//new while true loop to determine if player wants to start new life
	//answer = input next converted to lowercase
	//include error checking
		player.whoAttendsFuneral(); 
		player.setCauseDeath(event);
		System.out.println(player.getObituary(stats.getYear(), stats.getDayMonth()));
		//prompt new life
		while(true) {
		System.out.println("Start a new life? Choose Yes/No");
		answer = input.next();
		answer = answer.toLowerCase();
		if (!(answer.equals("yes") || answer.equals("no"))) {
			System.out.println("That doesn't seem to be an option, try again.");
			continue;
			}
			break;
			}
		}while (answer.equals("yes"));
		input.close();
	}
}
