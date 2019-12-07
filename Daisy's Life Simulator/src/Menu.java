import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Menu {
	private Player player;
	private Statistics stats;
	private static final Scanner s = new Scanner(System.in);
	
	//4 methods 12/3
	public Menu(Player player, Statistics stats) {
		this.player = player;
		this.stats = stats;
	}
	public void openPartnerMenu() throws FileNotFoundException {
		int choice = 0;
		while (true) {
			System.out.println("Partner Menu Options:\n 1. Find love \n 2. Leave partner \n 3. Propose \n 4. Assault\n 5. Exit");
			try {
				choice = s.nextInt();
				s.nextLine();
			}
			catch (InputMismatchException E) {
				choice = 10;
			} 
			if (choice == 1) {
				String res;
				while(true) {
					Relation partner = new Relation(stats, player, player.getSexuality(), "partner");
					System.out.println("You have met " + partner.getName() + ". \nAppearance: " + partner.getAppearance() + " \nAge: " + partner.getAge() +"\nWould you like to date?  (yes/no)");
					res = s.nextLine();
					res = res.toLowerCase();
					if (res.equals("yes")) {
						player.setPartner(partner);
						System.out.println("You are now dating " + partner.getName() + ".");
						player.setHappiness(5);
						break;	
					}
					else if (res.equals("no")) {
						System.out.println("You chose not to date " + partner.getName() + "." + "\n Seek someone else? (yes/no)");
						res = s.nextLine();
						res = res.toLowerCase();
						if (res.equals("yes")) {
							continue;
						}
						else if (res.equals("no")) {
							break;
						}
					}
					else {
						System.out.println("That doesn't seem to be an option, try again.");
						continue;
					}
				}
			} 
			else if (choice == 2) {
				player.checkDating();
				if (player.isDating() == true) {
					System.out.println("Are you sure you want to break up with " + player.getPartnerName() + "?");
					String res;
					res = s.nextLine();
					res = res.toLowerCase();
					if (res.equals("yes")) {
						System.out.println("You broke up with " + player.getPartnerName()+ "." );
						player.setHappiness(-20);
						player.breakup();
						break;
					}	
					else if (res.equals("no")) {
						System.out.println("You are still with " + player.getPartnerName() + ".");
						break;
					}
				}
				else if (player.isDating() == false) {
					System.out.println("You are not dating anyone.");
					continue;
				}
			} 
			else if (choice == 3) {
				System.out.println("You proposed to ");
			}
			else if (choice == 4) {
				if (player.isDating() == true) {
					player.setPFavor(-10);
					player.setPartnerFavor(-20);
					System.out.println("You hurt your partner!\n This hurts your reputation.");
					break;
				}
				else if (player.isDating() == false) {
					System.out.println("You are not dating anyone.");
					continue;
				}
			}
			else if (choice == 5) {
				break;
			}
			else if (choice < 1 || choice > 5) {
				System.out.println("That doesn't seem to be an option, try again.");
				continue;
			}
			continue;
		}
	}
	public void openEducationMenu() {
		int choice = 0;
		while (true) {
			System.out.println("");
			System.out.println("Education Menu Options:\n 1. View education \n 2. Drop out \n 3. Change study \n 4. Exit");
			try {
				choice = s.nextInt();
				}
				catch (InputMismatchException E) {
					choice = 10;
					s.nextLine();
				} 
			if (choice == 1) {
				player.checkEducation();
				System.out.println("Here are your educational achievements:");
				System.out.println(" Elementary school: " + player.getElementary() + "\n High School: " + player.getSecondary() + "\n College: " + player.getCollege()
				+ " | Major : " + player.getMajor());
				continue;
			}
			else if (choice == 2) {
				player.dropout();
				System.out.println("You dropped out of school. :(");
				break;
			}
			else if (choice == 3) {
				int res;
				System.out.println("Choose a major: \n 1. Nursing \n 2. Biology \n 3. Computer Science \n 4. English \n 5. Business");
				try {
					res = s.nextInt();
					} 
					catch (InputMismatchException E) {
						res = 10;
					}
					if (player.checkDropout() == false && player.getSecondary() == true && player.getCollege() == false && player.checkGraduated() == false) {
						if (res == 1) {
							player.setMajor("Nursing");
							System.out.println("Major changed!");
							continue;
						}
						else if (res == 2) {
							player.setMajor("Biology");
							System.out.println("Major changed!");
							continue;
						}
						else if (res == 3) {
							player.setMajor("Computer Science");
							System.out.println("Major changed!");
							continue;
						}
						else if (res == 4) {
							player.setMajor("English");
							System.out.println("Major changed!");
							continue;
						}
						else if (res == 5) {
							player.setMajor("Business");
							System.out.println("Major changed!");
							continue;
						}
						else if  (res > 5) {
							System.out.println("That doesn't seem to be an option, try again.");
							continue;
						}
					}
					else {
						System.out.println("You can't change your major because you've either dropped out, aren't in college, or graduated.");
						continue;
					}
				}
			
			else if (choice == 4) {
				break;
			}
			else if (choice < 1 || choice > 4) {
				System.out.println("That doesn't seem to be an option, try again.");
				continue;
			}
		}
	}
	public void openJobMenu() throws FileNotFoundException {
		int choice = 0;
		while (true) {
			System.out.println("");
			System.out.println("Job Menu Options:\n 1. Look for a job \n 2. Quit your job \n 3. Ask for a raise \n 4. Exit");
			try {
				choice = s.nextInt();
				}
				catch (InputMismatchException E) {
					choice = 10;
				} 
			if (choice == 1) {
				//job levels
				//	1. menial / retail - 15k max
				//	2. entry level - 25k max
				//	3. intermediate - 50k max
				//  4. high level - 100k max
				
				//algorithm
				//assign major to levels 2-4
				//randomly select an attribute for jobs 5 - 16
				
				String job1, job2, job3, job4, job5, job6, job7, job8, job9, job10, job11, job12, job13, job14, job15, job16;
				String job5a, job6a, job7a, job8a, job9a, job10a, job11a, job12a, job13a, job14a, job15a, job16a;
				
				ArrayList<String> attlist = new ArrayList<String>();
				attlist.add("Nursing");
				attlist.add("Biology");
				attlist.add("Computer Science");
				attlist.add("English");
				attlist.add("Business");
				job5a = attlist.get(new Random().nextInt(attlist.size()));
				job6a = attlist.get(new Random().nextInt(attlist.size()));
				job7a = attlist.get(new Random().nextInt(attlist.size()));
				job8a = attlist.get(new Random().nextInt(attlist.size()));
				job9a = attlist.get(new Random().nextInt(attlist.size()));
				job10a = attlist.get(new Random().nextInt(attlist.size()));
				job11a = attlist.get(new Random().nextInt(attlist.size()));
				job12a = attlist.get(new Random().nextInt(attlist.size()));
				job13a = attlist.get(new Random().nextInt(attlist.size()));
				job14a = attlist.get(new Random().nextInt(attlist.size()));
				job15a = attlist.get(new Random().nextInt(attlist.size()));
				job16a = attlist.get(new Random().nextInt(attlist.size()));
				
				Scanner getJob1 = new Scanner(new File("menial.txt"));
				ArrayList<String> job1g = new ArrayList<String>();
				while (getJob1.hasNextLine()){
					job1g.add(getJob1.nextLine());
					}
				getJob1.close();
				job1 = job1g.get(new Random().nextInt(job1g.size()));
				
				Scanner getJob2 = new Scanner(new File("menial.txt"));
				ArrayList<String> job2g = new ArrayList<String>();
				while (getJob2.hasNextLine()){
					job2g.add(getJob2.nextLine());
					}
				getJob2.close();
				job2 = job2g.get(new Random().nextInt(job2g.size()));
				
				Scanner getJob3 = new Scanner(new File("menial.txt"));
				ArrayList<String> job3g = new ArrayList<String>();
				while (getJob3.hasNextLine()){
					job3g.add(getJob3.nextLine());
					}
				getJob3.close();
				job3 = job3g.get(new Random().nextInt(job3g.size()));
				
				Scanner getJob4 = new Scanner(new File("menial.txt"), "UTF-8");
				ArrayList<String> job4g = new ArrayList<String>();
				while (getJob4.hasNextLine()){
					job4g.add(getJob4.nextLine());
					}
				getJob4.close();
				job4 = job4g.get(new Random().nextInt(job4g.size()));
				
				Scanner getJob5 = new Scanner(new File(job5a + "Entrylevel.txt"), "UTF-8");
				ArrayList<String> job5g = new ArrayList<String>();
				while (getJob5.hasNextLine()){
					job5g.add(getJob5.nextLine());
					}
				getJob5.close();
				job5 = job5g.get(new Random().nextInt(job5g.size()));
				
				Scanner getJob6 = new Scanner(new File(job6a + "Entrylevel.txt"), "UTF-8");
				ArrayList<String> job6g = new ArrayList<String>();
				while (getJob6.hasNextLine()){
					job6g.add(getJob6.nextLine());
					}
				getJob6.close();
				job6 = job6g.get(new Random().nextInt(job6g.size()));
				
				Scanner getJob7 = new Scanner(new File(job7a + "Entrylevel.txt"), "UTF-8");
				ArrayList<String> job7g = new ArrayList<String>();
				while (getJob7.hasNextLine()){
					job7g.add(getJob7.nextLine());
					}
				getJob7.close();
				job7 = job7g.get(new Random().nextInt(job7g.size()));
				
				Scanner getJob8 = new Scanner(new File(job8a + "Entrylevel.txt"), "UTF-8");
				ArrayList<String> job8g = new ArrayList<String>();
				while (getJob8.hasNextLine()){
					job8g.add(getJob8.nextLine());
					}
				getJob8.close();
				job8 = job8g.get(new Random().nextInt(job8g.size()));
				
				Scanner getJob9 = new Scanner(new File(job9a + "Intermediate.txt"), "UTF-8");
				ArrayList<String> job9g = new ArrayList<String>();
				while (getJob9.hasNextLine()){
					job9g.add(getJob9.nextLine());
					}
				getJob9.close();
				job9 = job9g.get(new Random().nextInt(job9g.size()));
				
				Scanner getJob10 = new Scanner(new File(job10a + "Intermediate.txt"), "UTF-8");
				ArrayList<String> job10g = new ArrayList<String>();
				while (getJob10.hasNextLine()){
					job10g.add(getJob10.nextLine());
					}
				getJob10.close();
				job10 = job10g.get(new Random().nextInt(job10g.size()));
				
				Scanner getJob11 = new Scanner(new File(job11a + "Intermediate.txt"), "UTF-8");
				ArrayList<String> job11g = new ArrayList<String>();
				while (getJob11.hasNextLine()){
					job11g.add(getJob11.nextLine());
					}
				getJob11.close();
				job11 = job11g.get(new Random().nextInt(job11g.size()));
				
				Scanner getJob12 = new Scanner(new File(job12a + "Intermediate.txt"), "UTF-8");
				ArrayList<String> job12g = new ArrayList<String>();
				while (getJob12.hasNextLine()){
					job12g.add(getJob12.nextLine());
					}
				getJob12.close();
				job12 = job12g.get(new Random().nextInt(job12g.size()));
				
				Scanner getJob13 = new Scanner(new File(job13a + "Highlevel.txt"), "UTF-8");
				ArrayList<String> job13g = new ArrayList<String>();
				while (getJob13.hasNextLine()){
					job13g.add(getJob13.nextLine());
					}
				getJob13.close();
				job13 = job13g.get(new Random().nextInt(job13g.size()));
				
				Scanner getJob14 = new Scanner(new File(job14a + "Highlevel.txt"), "UTF-8");
				ArrayList<String> job14g = new ArrayList<String>();
				while (getJob14.hasNextLine()){
					job14g.add(getJob14.nextLine());
					}
				getJob14.close();
				job14 = job14g.get(new Random().nextInt(job14g.size()));
				
				Scanner getJob15 = new Scanner(new File(job15a + "Highlevel.txt"), "UTF-8");
				ArrayList<String> job15g = new ArrayList<String>();
				while (getJob15.hasNextLine()){
					job15g.add(getJob15.nextLine());
					}
				getJob15.close();
				job15 = job15g.get(new Random().nextInt(job15g.size()));
				
				Scanner getJob16 = new Scanner(new File(job16a + "Highlevel.txt"), "UTF-8");
				ArrayList<String> job16g = new ArrayList<String>();
				while (getJob16.hasNextLine()){
					job16g.add(getJob16.nextLine());
					}
				getJob16.close();
				job16 = job16g.get(new Random().nextInt(job16g.size()));
				
				int salary1 = (int)(Math.random() * ((15000-8000) + 1)) + 8000;
				int salary2 = (int)(Math.random() * ((15000-8000) + 1)) + 8000;
				int salary3 = (int)(Math.random() * ((15000-8000) + 1)) + 8000;
				int salary4 = (int)(Math.random() * ((15000-8000) + 1)) + 8000;
				int salary5 = (int)(Math.random() * ((30000-15000) + 1)) + 15000;
				int salary6 = (int)(Math.random() * ((30000-15000) + 1)) + 15000;
				int salary7 = (int)(Math.random() * ((30000-15000) + 1)) + 15000;
				int salary8 = (int)(Math.random() * ((30000-15000) + 1)) + 15000;
				int salary9 = (int)(Math.random() * ((60000-30000) + 1)) + 30000;
				int salary10 = (int)(Math.random() * ((60000-30000) + 1)) + 30000;
				int salary11 = (int)(Math.random() * ((60000-30000) + 1)) + 30000;
				int salary12 = (int)(Math.random() * ((60000-30000) + 1)) + 30000;
				int salary13 = (int)(Math.random() * ((125000-60000) + 1)) + 60000;
				int salary14 = (int)(Math.random() * ((125000-60000) + 1)) + 60000;
				int salary15 = (int)(Math.random() * ((125000-60000) + 1)) + 60000;
				int salary16 = (int)(Math.random() * ((125000-60000) + 1)) + 60000;
				
				player.checkEducation();
				player.checkJobEligbility();
				int res;
				System.out.println("Here are the current job postings: ");
				System.out.println(" 1. " + job14 + " | Salary: " + salary14 + "\n 2. " + job3 + " | Salary: " + salary3 + "\n 3. " + job2 + " | Salary: " + salary2 + "\n 4. "+ job10 + " | Salary: " + salary10 +  "\n 5. " + job8 + " | Salary: " + salary8 + "\n 6. " + 
				job1 +  " | Salary: " + salary1 + "\n 7. " + job15 + " | Salary: " + salary15 +  "\n 8. " + job12 + " | Salary: " + salary12 +  "\n 9. " + 
				job16 + " | Salary: " + salary16 + "\n 10. " + job4 + " | Salary: " + salary4 + "\n 11. " + job7 +" | Salary: " + salary7 +  "\n 12. " + job6 + " | Salary: " + salary16 +  "\n 13. " + job5
				+ " | Salary: " + salary5 + "\n 14. " + job13 + " | Salary: " + salary13 + "\n 15. " + job9 + " | Salary: " + salary9 +  "\n 16. " + job11 + " | Salary: " + salary11 );
				res = s.nextInt();
				
				if (res == 1) {
					if (player.getHighlevel() == true && player.getMajor() == job14a) {
							player.setJob(job14, salary14);
							continue;
					}
					else {
						System.out.println("You aren't qualified for this position. This job requires a " + job14a + " degree and many years of experience.");
						continue;
					}
					
				}
				else if (res == 2) {
					player.setJob(job3, salary3);
					continue;
				}
				else if (res == 3) {
					player.setJob(job2, salary2);
					continue;
				}
				else if (res == 4) {
					if (player.getIntermediate() == true && player.getMajor() == job10a) {
						player.setJob(job10, salary10);
						continue;
					}
					else {
						System.out.println("You aren't qualified for this position. This job requires a " + job10a + " degree and some years of experience.");
						continue;
					}
				}
				else if (res == 5) {
					if (player.getEntrylevel() == true && player.getMajor() == job8a) {
						player.setJob(job8, salary8);
						continue;
					}
					else {
						System.out.println("You aren't qualified for this position. This job requires a " + job8a + " degree.");
						continue;
					}
				}
				else if (res == 6) {
					player.setJob(job1, salary1);
					continue;
				}
				else if (res == 7) {
					if (player.getHighlevel() == true && player.getMajor() == job15a) {
						player.setJob(job15, salary15);
						continue;
					}
					else {
						System.out.println("You aren't qualified for this position. This job requires a " + job15a + " degree and many years of experience.");
						continue;
					}
				}
				else if (res == 8) {
					if (player.getIntermediate() == true && player.getMajor() == job12a) {
						player.setJob(job12, salary12);
						continue;
					}
					else {
						System.out.println("You aren't qualified for this position. This job requires a " + job12a + " degree and some years of experience.");
					}
				}
				else if (res == 9) {
					if (player.getHighlevel() == true && player.getMajor() == job16a) {
						player.setJob(job16, salary16);
					}
					else {
						System.out.println("You aren't qualified for this position. This job requires a " + job16a + " degree and many years of experience.");
						continue;
					}
				}
				else if (res == 10) {
					player.setJob(job4, salary4);
					continue;
				}
				else if (res == 11) {
					if (player.getEntrylevel() == true && player.getMajor() == job7a) {
						player.setJob(job7, salary7);
						continue;
					}
					else {
						System.out.println("You aren't qualified for this position. This job requires a " + job7a + " degree.");
						continue;
					}
				}
				else if (res == 12) {
					if (player.getEntrylevel() == true && player.getMajor() == job6a) {
						player.setJob(job6, salary6);
						continue;
					}
					else {
						System.out.println("You aren't qualified for this position. This job requires a " + job6a + " degree.");
						continue;
					}
				}
				else if (res == 13) {
					if (player.getEntrylevel() == true && player.getMajor() == job5a) {
						player.setJob(job5, salary5);
						continue;
					}
					else {
						System.out.println("You aren't qualified for this position. This job requires a " + job5a + " degree.");
						continue;
					}
				}
				else if (res == 14) {
					if (player.getHighlevel() == true && player.getMajor() == job13a) {
						player.setJob(job13, salary13);
						continue;
					}
					else {
						System.out.println("You aren't qualified for this position. This job requires a " + job13a + " degree and many years of experience.");
						continue;
					}
				}
				else if (res == 15) {
					if (player.getIntermediate() == true && player.getMajor() == job9a) {
						player.setJob(job9, salary9);
						continue;
					}
					else {
						System.out.println("You aren't qualified for this position. This job requires a " + job9a + " degree and some years of experience.");
						continue;
					}
				}
				else if (res == 16) {
					if (player.getIntermediate() == true && player.getMajor() == job11a) {
						player.setJob(job11, salary11);
						continue;
					}
					else {
						System.out.println("You aren't qualified for this position. This job requires a " + job11a + " degree and some years of experience.");
						continue;
					}
				}
				
			}
			else if (choice == 2) {
				if (player.hasJob() == true) {
					player.quitJob();
					System.out.println("You quit your job.");
					continue;
				}
				else {
					System.out.println("You don't have a job to quit.");
					continue;
				}
			}
			else if (choice == 3) {
				if (player.hasJob() == true) {
					double c = Math.random();
					if (c <= 0.2) {
						System.out.println("You were approved for a raise!");
						player.setHappiness(20);
						int raise = (int)(Math.random() * ((5000-500) + 1)) + 500;
						player.setRaise(raise);
						continue;
					}
					else {
						System.out.println("Your boss denied you a raise.");
						player.setHappiness(-5);
						continue;
					}
				}
				else {
					System.out.println("You don't have a job.");
					continue;
				}
				
			}
			else if (choice == 4) {
				break;
			}
			else if (choice > 4 || choice < 1) {
				System.out.println("That doesn't seem to be an option, try again.");
				continue;
			}
		
		}
	}
	public void lottery() {
		if (player.getAge() < 18) {
			System.out.println("You're too young to play the lottery!");
		}
		else {
			System.out.println("You put down $5 and scratch off a lottery card.");
			double c = Math.random();
			if (c < 0.0001) {
				player.giveMoney(5);
				int prize = (int)(Math.random() * ((10000000-10000) + 1)) + 10000;
				System.out.println("You won the prize of $" + prize + "!");
				player.giveMoney(-prize);
			}
			else {
				player.giveMoney(5);
				int prize = (int)(Math.random() * ((10000000-10000) + 1)) + 10000;
				System.out.println("You didn't win the prize of $" + prize + ".");
			}
		}
	}
}
