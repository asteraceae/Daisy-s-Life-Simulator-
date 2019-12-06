import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Player {
	private int appearance;
	private int creativity;
	private int fitness;
	private int happiness;
	private boolean isDating;
	private String sexuality;
	private boolean atf;
	private boolean atm;
	private boolean dead;
	private boolean suicide;
	private String cause;
	private int age;
	private String name;
	private boolean female;
	private String fAttendees;
	private boolean elementary;
	private boolean secondary;
	private boolean college;
	private boolean dropout;
	private boolean graduated;
	private String major;
	private Statistics stats;
	private Relation mother;
	private Relation father;
	private Relation partner;
	private int money;
	private boolean menial;
	private boolean Entrylevel;
	private boolean Intermediate;
	private boolean Highlevel;
	private boolean hasJob;
	private String job;
	private int salary;

	//60 methods 12/3
	public Player(Statistics stats, Relation mother, Relation father) throws FileNotFoundException {
		this.stats = stats;
		this.mother = mother;
		this.father = father;
		dropout = false;
		money = 0;
		hasJob = false;
		setGender();
		setName();
		setAppearance();
		setCreativity();
		setFitness();
		setHappiness();
		sexuality = "unknown";
		isDating = false;
	}
	public String toString(int response) {
		String s = "";
		if (response != 2) {
			s = " \nYour name is " + getName() + ".\nYou are a " + getGenderString(1) + ", born in " + stats.getCountry() + ".\nYou are the " + getGenderString(2) + " of your mother, " + mother.getName() + ", " + mother.getAge() + ", and your father, " + father.getName() + ", " + father.getAge() + ".";
		} 
		else if (response == 2) {
			s = "########################################################################\nName: " + getName() +"\nGender: " + getGenderString(1) + "\nAge: " + getAge() + "\nMoney:  " + money + "\nNation: " + stats.getCountry()
			+ "\nHappiness: " + getHappiness() + "\nAppearance: " + getAppearance() + "\nFitness: " + getFitness() +"\nCreativity: " + getCreativity() + "\nSexuality: " + getSexuality();
			if (this.hasJob() == true) {
				s = s + "\nJob: " + job + " | Salary: " + salary;
			}
			if (this.isDating() == true) {
				s = s + "\nPartner: " + partner.getName();
			}
			s = s + "\n########################################################################";
		}
		return s;
	}
	public int getAppearance() {
		return appearance;
	}
	public void setAppearance() {
		appearance = (int)(Math.random() * ((100-0) + 1)) + 0;
		}
	public void setGender(){
		double chance = Math.random();
		if (chance <= 0.5) {
			female = true;
		} else {
			female = false;
		}
	}
	public void setPartner(Relation partner) {
		this.partner = partner;
	}
	public Relation getPartner() {
		return partner;
	}
	public void agePartner() {
		partner.age();
	}
	public void breakup() {
		this.partner = null;
	}
	public boolean getGender() {
		return female;
	}
	public String getGenderString(int a) {
		String f = "female", m = "male", fd = "daughter", fm = "son";
		if (a == 1) {
			if (female == true) {
				return f;
			} else {
				return m;
			}
		}
		if (a == 2) {
			if (female == true) {
				return fd;
			} else {
				return fm;
			}
		}
		return f;
	}
	
	public void setName() throws FileNotFoundException {
		//player name
		String fname, suffix;
		if (female == true) {
			suffix = "F.txt";
		} else {
			suffix = "M.txt";
		}
		
		//player 1st name	
		Scanner getFname = new Scanner(new File(stats.getCountry() + suffix), "UTF-8");
		ArrayList<String> item = new ArrayList<String>();
		while (getFname.hasNext()){
			item.add(getFname.next());
			}
		getFname.close();
		fname = item.get(new Random().nextInt(item.size()));
		name = fname + " " + mother.getLname();
	}
	public String getName() {
		return name;
	}
	public String getPartnerName() {
		return partner.getName();
	}

	public void setPartnerFavor(int a) {
		partner.setFavor(a);
	}
	public int getFitness() {
		return fitness;
	}
	public void setFitness() {
			fitness = (int)(Math.random() * ((100-0) + 1)) + 0;
		}
	
	public void setCreativity() {
		creativity = (int)(Math.random() * ((100-0) + 1)) + 0;
		
	}
	public int getCreativity() {
		return creativity;
	}
	public int getHappiness() {
		return happiness;
	}
	public void setHappiness() {
			happiness = 100;
		}
	public void setHappiness(int a) {
		happiness = happiness + a;
		if (happiness < 0 ) {
			happiness = 0;
		}
	}
	public void checkJobEligbility() {
		menial = true;
		if (college == true) {
			Entrylevel = true;
		}
		if (college == true && age >=25) {
			Intermediate = true;
		}
		if (college == true && age >=40) {
			Highlevel = true;
		}
		
	}
	public boolean getEntrylevel() {
		return Entrylevel;
	}
	public boolean getIntermediate() {
		return Intermediate;
	}
	public boolean getHighlevel() {
		return Highlevel;
	}
	public void setJob(String job, int salary) {
		double c = Math.random();
		if (c < 0.4) {
			this.job = job;
			hasJob = true;
			System.out.println("Congratulations! You got the job!");
			this.setSalary(salary);
		}
		else {
			System.out.println("You didn't get an interview...");
		}
	}
	public void quitJob() {
		this.job = null;
		hasJob = false;
	}
	public void giveMoney(int a) {
		money = money - a;
	}
	public int getMoney() {
		return money;
	}
	public void setSexuality(String s) {
		this.sexuality = s;
		if (female == true) {
			if (s.equals("heterosexual")) {
				atm = true;
			}
			else if (s.equals("homosexual")) {
				atf = true;
			}
			else {
				atf = true;
				atm = true;	
			}
		}
		if (female == false) {
			if (s.equals("heterosexual")) {
				atf = true;
			}
			else if (s.equals("homosexual")) {
				atm = true;
			}
			else {
				atf = true;
				atm = true;	
			}
		}
	}
	public String getSexuality(){
		return sexuality;
	}
	public String getSexuality(int a) {
		String s = "";
		if (atf == true && atm == false) {
			s = "girls";
		} else if (atm == true && atf == false) {
			s = "boys";
		}
		else if (atm == true && atf == true) {
			s = "both girls and boys";
		}
		return s;
	}
	public void age() {
		age++;
		if (hasJob == true) {
			money = money + salary;
		}
	}
	public void setAge() {
		age = 0;
	}
	public int getAge() {
		return age;
	}
	public void checkEducation() {
		if (dropout == false) {
			if (age < 14) {
				elementary = false;
				secondary = false;
				college = false;
			}
			else if (age >= 14 && age < 18) {
				elementary = true;
				secondary = false;
				college = false;
			}
			else if (age >= 18 && age <=22) {
				elementary = true;
				secondary = true;
				college = false;
			}
			else if (age == 22) {
				elementary = true;
				secondary = true;
				college = true;
				graduated = true;
			}
			else if (age > 22) {
				elementary = true;
				secondary = true;
				college = true;
			}
		}
	}
	public boolean getElementary() {
		return elementary;
	}
	public boolean getSecondary() {
		return secondary;
	}
	
	public boolean getCollege() {
		return college;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public void setRaise(int raise) {
		salary = salary + raise;
	}
	public boolean hasJob() {
		if (job != null) {
			hasJob = true;
		}
		else {
			hasJob = false;
		}
		return hasJob;
	}
	public boolean checkGraduated() {
		return graduated;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getMajor() {
		return major;
	}
	public void dropout() {
		dropout = true;
	}
	public boolean checkDropout() {
		return dropout;
	}
	public void kill() {
		dead = true;
	}
	public boolean isDead() {
		return dead;
	}
	public void checkDating() {
		if (partner == null) {
			isDating = false;
			System.out.println("You are dating: nobody");
		}
		else if (partner != null) {
			isDating = true;
			System.out.println("You are dating: " + partner.getName());
		}
	}
	public boolean isDating() {
		if (partner == null) {
			isDating = false;
		}
		else if (partner != null) {
			isDating = true;
		}
		return isDating;
	}
	public void chanceDeath() {
		double d = Math.random();
		if (age <= 10) {
			if (d < 0.005) {
				dead = true;
			} else {
				dead = false;
			}
		} 
		else if (age <= 20) {
			if (d < 0.001) {
				dead = true;
			} else {
				dead = false;
			}
		}
		else if (age <= 50) {
			if (d < 0.005) {
				dead = true;
			} else {
				dead = false;
			}
		}
		else if (age <= 60) {
			if (d < 0.010) {
				dead = true;
			} else {
				dead = false;
			}
		}
		else if (age <= 70) {
			if (d < 0.10) {
				dead = true;
			} else {
				dead = false;
			}
		}
		else if (age <= 90) {
			if (d < 0.40) {
				dead = true;
			} else {
				dead = false;
			}
		}
		else if (age <= 100) {
			if (d < 0.50) {
				dead = true;
			} else {
				dead = false;
			}
		}
		else if (age <= 110) {
			if (d < 0.90) {
				dead = true;
			} else {
				dead = false;
			}
		}
		else {
			dead = true;
		}
	}
	public boolean checkDeath(){
		return dead;
	}
	public void commitSuicide() {
		dead = true;
		suicide = true;
	}
	public void setCauseDeath(Event event) throws FileNotFoundException {
		if ((event.checkEventKill() == false)) {
			if (age < 12) {
				cause = "a sudden illness";
				}
			else if (age >= 70) {
				cause = "natural causes";
			}
			else if (suicide == true) {
				cause = "suicide";
			}
			else {
				Scanner a = new Scanner(new File("suddenDeaths.txt"));
				ArrayList<String> a2 = new ArrayList<String>();
				while (a.hasNextLine()){
					a2.add(a.nextLine());
				}
				a.close();
				cause = a2.get(new Random().nextInt(a2.size()));
			}
		}
	}
	public void setCauseDeath(String cause) {
		this.cause = cause;
	}
	public String getCause() {
		return cause;
	}
	public void setPFavor(int a) {
		mother.setFavor(a);
		father.setFavor(a);
	}
	public void whoAttendsFuneral() {
		String pn;
		if (female == true) {
			pn = "Her";
		}else {
			pn = "His";
		}
		fAttendees = "nobody";
		if ( ((mother.getFavor() >= 50) && mother.alive() == true) && ((father.getFavor() >= 50) && father.alive() == true) ) {
			fAttendees =  fAttendees.replaceAll("nobody", "");
			fAttendees = fAttendees + "\n" + pn + " parents";
		}
		else if (mother.getFavor() >= 50 && mother.alive() == true) {
			fAttendees =  fAttendees.replaceAll("nobody", "");
			fAttendees = fAttendees + "\n" + pn + " mother";
		}
		else if (father.getFavor() >= 50 && father.alive() == true) {
			fAttendees = fAttendees.replaceAll("nobody", "");
			fAttendees = fAttendees + "\n" +  pn + " father";
		}
		if (partner != null) {
			fAttendees = fAttendees.replaceAll("nobody", "");
			fAttendees = fAttendees + "\n" + pn + " partner, " + partner.getName();
		}
			
	}
	public String getObituary(int year, String date) {
		String pn;
		if (female == true) {
			pn = "Her";
		}else {
			pn = "His";
		}
		String obituary = " \n######################################################################## \n \nYou died! \n \n" + name + " died at the age of " + age + " on " + date +", " + year + " from " + cause + "." +"\n" + pn +" funeral was attended by: " + fAttendees;
		if (this.isDating == true) {
			obituary = "\n \n" + name + "had been dating " + partner.getName() + "for some time.";
		}
		if (this.hasJob() == true ) {
			obituary = obituary + "\n \n" + name + " worked a job as a " + job + " and left behind $" + money + ".";
		}
		else {
			obituary = obituary + "\n \n" + name + " left behind a fortune of $" + money + ".";
		}
		obituary = obituary +"\n \n######################################################################## \n";
		
		
		return obituary;
	}
}
