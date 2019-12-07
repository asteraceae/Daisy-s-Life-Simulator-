import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Event {
	/** EVENTS
	 *create constructor 1 which is blank
	 *create constructor 2 which actually generates event
	 	*import obj player, stats, method chanceID
	 *method chanceID
	 	*int ID, double c = math random
	 	*30% chance for event
	 		*generate new c
	 			*90% chance for menial event, IDs 1-5
	 			*9% chance for moderate event, ID 9
	 			*1% for catastrophic event, ID 10
	 			*plug all IDS into getEvent
	 *method getEvent
	 	*switch ID 
	 		*each case activates corresponding event
	 **/
	//8 methods 12/3
	
	//vars
		//stats
		//player
		//killedbyevent
		//scanner
	private Statistics stats;
	private Player player;
	private boolean killedByEvent;
	private Scanner k = new Scanner(System.in);
	
	public Event() {
		
	}
	public Event(Player player, Statistics stats) throws FileNotFoundException {
		this.player = player;
		this.stats = stats;
		chanceID();
	}
	public void chanceID() throws FileNotFoundException {
		int ID;
		double c = Math.random();
		if (c <= 0.3) {
			c = Math.random();
			if (c<=0.9) {
				ID = (int)(Math.random() * ((5-1) + 1)) + 1;
				getEvent(ID);
			}
			else if (c > 0.9 && c <= 0.99) {
				ID = (int)(Math.random() * ((9-9) + 1)) + 9;
				getEvent(ID);
			}
			else if (c > 0.99) {
				ID = 10;
				//ID = (int)(Math.random() * ((1-11) + 1)) + 11;
				getEvent(ID);
			}
		}
	}
	public void getEvent(int ID) throws FileNotFoundException {
		switch (ID) { 
			case 1: 
				approach();	
				break;
			case 2: 
				randomMoney();
				break;
			case 3: 
				concert();
				break;
			case 4:
				news();
				break;
			case 5:
				meet();
				break;
			case 9:
				randomBigMoney();
				break;
			case 10:
				catastrophic();
				break;
		
		}
	}
	//event approach
		//generate a stranger from txt file
		//set scanner
		//"you have been approached by 'stranger'
			//options give money, fight, ignore
		//int variables for response
		//try catch
		//while true loop for responses
			//1
				//if money < 0 
					//tell user no money
					//activate consequences case 1
				//else generate monetary amount and deduct from wallet
				//consequences 0
			//2 
				//tell user they lowered reputation
				//lower favor of relations
				//consequences 2
			//3 
				//ignore
				//consequences 0
			//4 error checking
	public void approach() throws FileNotFoundException {
		String who; int res;
		if (player.getAge() >= 12) {
			Scanner getWho = new Scanner(new File("strangers.txt"));
			ArrayList<String> list = new ArrayList<String>();
			while (getWho.hasNextLine()){
				list.add(getWho.nextLine());
			}
			getWho.close();
			who = list.get(new Random().nextInt(list.size()));
		
			k = new Scanner(System.in);
			System.out.println("You have been approached by " + who + ", what will you do? \n 1. Give money \n 2. Fight \n 3. Ignore them");
			try {
			res = k.nextInt();
				} 
				catch (InputMismatchException E) {
				res = 10;
				}
			while(true) {
				if (res == 1) {
					if (player.getMoney() <= 0) {
						System.out.println("You don't have any money!");
						consequences(1);
						break;
					}
					else {
						int give = (int)(Math.random() * ((150-1) + 1)) + 1;
						System.out.println("You gave them $" + give);
						player.giveMoney(give);
						consequences(0);
						break;
					}
				}
				else if (res == 2) {
					System.out.println("You hurt the stranger! \nThis lowers your reputation!");
					player.setPFavor(-20);
					if (player.isDating() == true) {
						player.setPartnerFavor(-20);
					}
					consequences(2);
					break;
				}
				else if (res == 3) {
					System.out.println("You ignored them.");
					consequences(0);
					break;
				}
				else if (res > 3 || res < 1) {
					System.out.println("That doesn't seem to be an option, try again.");
					continue;
				}
			}
				
		}
	}
//random money + randomBigmoney
	//works only if player age >12
	//int give, generate monetary value
	//tell user they found value, add to wallet
	public void randomMoney() {
		if (player.getAge() >= 12) {
			int give = (int)(Math.random() * ((100-1) + 1)) + 1;
			System.out.println("You were walking on the street and randomly found $" + give + " on the ground.");
			player.giveMoney(-give);
		}
	}
	public void randomBigMoney(){
		int give = (int)(Math.random() * ((10000-1) + 1)) + 1;
		System.out.println("A distant relative suddenly gifts you $" + give + " after you hear news of their passing.");
		player.giveMoney(-give);
	}
//concert
	//works if age > 12
	//tell user they got tickets, raise happiness by 15
	public void concert() {
		if (player.getAge() >= 12) {
		System.out.println("You were given concert tickets by a friend.  You had a great time.");
		player.setHappiness(15);
		}
	}
//news
	//pulls random event from txt file and prints
	public void news() {
		
	}
//meet random person at coffee shop
	//works if age > 12
	//generate relation partner
	// while true for error checking
	// yes/no option 
	public void meet() throws FileNotFoundException {
		String res;
		//Scanner p = new Scanner(System.in);
		if (player.getAge() >= 12) {
			Relation partner = new Relation(stats, player, player.getSexuality(), "partner");
			System.out.println("You have met " + partner.getName() + " at a coffee shop. \nAppearance: " + partner.getAppearance() + " \nAge: " + partner.getAge() +"\nWould you like to date?  (yes/no)");
			while (true) {
				res = k.nextLine();
				res = res.toLowerCase();
				if (res.equals("yes")) {
					player.setPartner(partner);
					System.out.println("You are now dating " + partner.getName() + ".");
					player.setHappiness(5);
					break;
				}
				else if (res.equals("no")) {
					System.out.println("You chose not to date " + partner.getName() + ".");
					break;
				}
				else {
					System.out.println("That doesn't seem to be an option, try again.");
					continue;
				}
			}
		}
	}

	public void consequences(int a) throws FileNotFoundException {
		double c = Math.random();
		int d = (int)Math.random();
		if (a == 0) {
				if (c <= 0.005) {
					int n = (int)(Math.random() * ((3-0) + 1)) + 0;
					String ouch0, ouch1, ouch2;
					if (n == 1) {
						Scanner getOuch0 = new Scanner(new File("ouch.txt"), "UTF-8");
						ArrayList<String> list = new ArrayList<String>();
						while (getOuch0.hasNextLine()){
						list.add(getOuch0.nextLine());
						}
						getOuch0.close();
						ouch0 = list.get(new Random().nextInt(list.size()));
						System.out.println(ouch0);
					}
					else if (n==2) {
						Scanner getOuch0 = new Scanner(new File("ouch.txt"), "UTF-8");
						ArrayList<String> list = new ArrayList<String>();
						while (getOuch0.hasNextLine()){
						list.add(getOuch0.nextLine());
						}
						getOuch0.close();
						ouch0 = list.get(new Random().nextInt(list.size()));
						ouch1 = list.get(new Random().nextInt(list.size()));
						System.out.println(ouch0);
						System.out.println(ouch1);
					}
					else if (n ==3) {
						Scanner getOuch0 = new Scanner(new File("ouch.txt"), "UTF-8");
						ArrayList<String> list = new ArrayList<String>();
						while (getOuch0.hasNextLine()){
						list.add(getOuch0.nextLine());
						}
						getOuch0.close();
						ouch0 = list.get(new Random().nextInt(list.size()));
						ouch1 = list.get(new Random().nextInt(list.size()));
						ouch2 = list.get(new Random().nextInt(list.size()));
						System.out.println(ouch0);
						System.out.println(ouch1);
						System.out.println(ouch2);
					}
					System.out.println("You got beat up!");
					if (d <= 0.3) {
						player.kill();
						player.setCauseDeath("getting beat up");
						killedByEvent = true;
					}
				}
			}
			else if (a == 1) { 
				if (c <= 0.1) {
					int n = (int)(Math.random() * ((3-0) + 1)) + 0;
					String ouch0 = "", ouch1 = "", ouch2 = "";
					System.out.println("You got beat up!");
					
					if (n == 1) {
						Scanner getOuch0 = new Scanner(new File("ouch.txt"), "UTF-8");
						ArrayList<String> list = new ArrayList<String>();
						while (getOuch0.hasNextLine()){
						list.add(getOuch0.nextLine());
						}
						getOuch0.close();
						ouch0 = list.get(new Random().nextInt(list.size()));
						System.out.println(ouch0);
					}
					else if (n==2) {
						Scanner getOuch0 = new Scanner(new File("ouch.txt"), "UTF-8");
						ArrayList<String> list = new ArrayList<String>();
						while (getOuch0.hasNextLine()){
						list.add(getOuch0.nextLine());
						}
						getOuch0.close();
						ouch0 = list.get(new Random().nextInt(list.size()));
						ouch1 = list.get(new Random().nextInt(list.size()));
						System.out.println(ouch0);
						System.out.println(ouch1);
					}
					else if (n ==3) {
						Scanner getOuch0 = new Scanner(new File("ouch.txt"), "UTF-8");
						ArrayList<String> list = new ArrayList<String>();
						while (getOuch0.hasNextLine()){
						list.add(getOuch0.nextLine());
						}
						getOuch0.close();
						ouch0 = list.get(new Random().nextInt(list.size()));
						ouch1 = list.get(new Random().nextInt(list.size()));
						ouch2 = list.get(new Random().nextInt(list.size()));
						System.out.println(ouch0);
						System.out.println(ouch1);
						System.out.println(ouch2);
					}
					if (d <= 0.3) {
						player.kill();
						player.setCauseDeath("getting beat up");
						killedByEvent = true;
					}
				}
			}
			else if (a == 2) {
				if (c <= 0.5) {
					int n = (int)(Math.random() * ((3-0) + 1)) + 0;
					String ouch0, ouch1, ouch2;
					System.out.println("You got beat up!");
					if (n == 1) {
						Scanner getOuch0 = new Scanner(new File("ouch.txt"), "UTF-8");
						ArrayList<String> list = new ArrayList<String>();
						while (getOuch0.hasNextLine()){
						list.add(getOuch0.nextLine());
						}
						getOuch0.close();
						ouch0 = list.get(new Random().nextInt(list.size()));
						System.out.println(ouch0);
					}
					else if (n==2) {
						Scanner getOuch0 = new Scanner(new File("ouch.txt"), "UTF-8");
						ArrayList<String> list = new ArrayList<String>();
						while (getOuch0.hasNextLine()){
						list.add(getOuch0.nextLine());
						}
						getOuch0.close();
						ouch0 = list.get(new Random().nextInt(list.size()));
						ouch1 = list.get(new Random().nextInt(list.size()));
						System.out.println(ouch0);
						System.out.println(ouch1);
					}
					else if (n ==3) {
						Scanner getOuch0 = new Scanner(new File("ouch.txt"), "UTF-8");
						ArrayList<String> list = new ArrayList<String>();
						while (getOuch0.hasNextLine()){
						list.add(getOuch0.nextLine());
						}
						getOuch0.close();
						ouch0 = list.get(new Random().nextInt(list.size()));
						ouch1 = list.get(new Random().nextInt(list.size()));
						ouch2 = list.get(new Random().nextInt(list.size()));
						System.out.println(ouch0);
						System.out.println(ouch1);
						System.out.println(ouch2);
					}
					if (d <= 0.3) {
						player.kill();
						player.setCauseDeath("getting beat up");
						killedByEvent = true;
					}
			}
		}
	}
	public void catastrophic() {
		double c = Math.random();
			if (c < 0.001) {
				player.kill();
				player.setCauseDeath("being struck by a meteor");
				killedByEvent = true;
			}
		}
	public boolean checkEventKill() {
		return killedByEvent;
	}
}

