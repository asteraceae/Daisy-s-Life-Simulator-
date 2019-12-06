import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class lifeSimulatorMain {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		//intro
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
		String answer;
		Scanner input = new Scanner(System.in);
		
		do {
		
		//generate new stats
		Statistics stats = new Statistics();
		Relation mother = new Relation(true, true, stats, "mother");
		Relation father = new Relation(stats, mother);
		Player player = new Player(stats, mother, father);
		Menu menu = new Menu(player, stats);
		mother.importPlayer(player);
		father.importPlayer(player);
		Event event = new Event();
		
		//generate a year
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
			//if alive, increase chances of death
			} 
			//makes sure name isnt printed every loop
			if (loops == 0) {
				System.out.println(player.toString(response));
					}
			
			//sets chances person will suddenly die
			player.chanceDeath();
			mother.chanceDeath();
			father.chanceDeath();
			player.checkEducation();
			if (player.getAge() == 5 && player.checkDropout() == false) {
				System.out.println("You started school.");
			}
			if (player.getAge() == 14 && player.checkDropout() == false) {
				System.out.println("You graduated elementary school and started high school.");
			}
			if (player.getAge() == 18 && player.checkDropout() == false) {
				int r, res;
				System.out.println("Congratulations! You graduated high school!\n Will you go to university? \n 1. Yes \n 2. No");
				res = input.nextInt();
				if (res == 1) {
					System.out.println("Choose a major: \n 1. Nursing \n 2. Biology \n 3. Computer Science \n 4. English \n 5. Business");
					r = input.nextInt();
					if (r == 1) {
						player.setMajor("Nursing");
						System.out.println("You chose Nursing.");
					}
					else if (r == 2) {
						player.setMajor("Biology");
						System.out.println("You chose Biology.");
					}
					else if (r == 3) {
						player.setMajor("Computer Science");
						System.out.println("You chose Computer Science.");
					}
					else if (r == 4) {
						player.setMajor("English");
						System.out.println("You chose English.");	
					}
					else if (r == 5) {
						player.setMajor("Business");
						System.out.println("You chose Business.");	
					}
				}
				else if (res == 2) {
					System.out.println("You did not go to University.");	
					player.dropout();
					player.checkEducation();
				}
			if (player.getAge() == 22 && player.checkDropout() == false) {
				System.out.println("I graduated college with a degree of " + player.getMajor() + ".");
				}
			}
			if (player.getAge() == 12) {
				System.out.println("You're now old enough to start thinking about crushes.\nYou like:\n 1. girls\n 2. boys\n 3. both");
				while (true) {
					int choice = 0;
				/*double c = Math.random();
				if (c <= 0.33) {
					player.setSexuality("heterosexual");
				} else if (c <=0.66) {
					player.setSexuality("homosexual");
				}
				else if (c <=1) {
					player.setSexuality("bisexual");
				}*/
				
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
			if (loops != 0) {
				event = new Event(player, stats);
				}
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
						//suicide function
					}
					else if (response == 4) {
						player.checkEducation();
						menu.openEducationMenu();
						continue;
					}
				
			//suicide function
					else if (response == 5) {
						//if person too young, don't kill
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
					//allow death
						}
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
					else if (response == 6) {
						menu.lottery();
						continue;
					}
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
					else if (response >= 8){
						System.out.println("That doesn't seem to be an option, try again.");
						continue;
					}	
				if (response == 1 || player.isDead() == true) {
					break;
				}
				else {
					continue;
				}
			}
			loops++;
			continue;
		}
		//death
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
