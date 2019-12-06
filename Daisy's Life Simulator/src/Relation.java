import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Relation {
	private String name;
	private boolean female;
	private boolean parent;
	private boolean alive;
	private String who;
	private int age;
	private String lname;
	private int favor;
	private Statistics stats;
	private Player player;
	private int appearance;

// 24 methods 12/3
public Relation(boolean f, boolean p, Statistics stats, String who) throws FileNotFoundException {
	this.stats = stats;
	alive = true;
	this.who = who;
	setParent(p);
	setGender(f);
	setName();
	setFavor();
	setAge();
}
public Relation(Statistics stats, Relation mother) throws FileNotFoundException {
	this.stats = stats;
	alive = true;
	who = "father";
	setParent(true);
	setGender(false);
	setName(mother);
	setFavor();
	setAge(mother);
}
public Relation(Statistics stats, Player player, String sexuality, String who) throws FileNotFoundException {
	this.stats = stats;
	alive = true;
	this.who = who;
	this.player = player;
	setAppearance(player);
	setParent(false);
	setGender(sexuality);
	setName();
	setFavor();
	setAge(player);
}
@Override
public String toString() {
	String s = name + "\n\tAge: " + age + "\n\tAlive: " + alive;
	return s;
}
public void importPlayer(Player player) {
	this.player = player;
}
public void setFavor() {
	favor = 100;
}
public void setFavor(int a) {
	favor = favor + a;
}
public int getFavor() {
	return favor;
}
public boolean alive() {
	return alive;
}
public void setAppearance(Player player) {
	double c = Math.random();
	if (c<=0.5) {
		appearance = player.getAppearance() + (int)(Math.random() * ((30-0) + 1)) + 0;
	}
	else {
		appearance = player.getAppearance() - (int)(Math.random() * ((30-0) + 1)) + 0;
	}
	if (appearance < 0) {
		appearance = 0;
	}
}
public int getAppearance() {
	return appearance;
}
public void setAge() {
	double c = Math.random();
	if (c<=0.2) {
		age = (int)(Math.random() * ((20-13) + 1)) + 13;
	}
	else if (c<=0.80) {
		age = (int)(Math.random() * ((40-21) + 1)) + 21;
	}
	else {
		age = (int)(Math.random() * ((45-41) + 1)) + 41;
	}
}
public void setAge(Relation mother) {
	age = mother.getAge() + (int)(Math.random() * ((12-0) + 1)) + 0;
}
public void setAge(Player player) {
	double c = Math.random();
	if (player.getAge() <= 25) {
		if (c<=0.5) {
			age = player.getAge() + (int)(Math.random() * ((3-0) + 1)) + 0;
		}
		else {
			age = player.getAge() - (int)(Math.random() * ((3-0) + 1)) + 0;
		}
	}
	else {
		if (c<=0.5) {
			age = player.getAge() + (int)(Math.random() * ((12-0) + 1)) + 0;
		}
		else {
			age = player.getAge() - (int)(Math.random() * ((12-0) + 1)) + 0;
		}
	}
}
public void age() {
	if (alive == true) {
		age++;
		}
}
public int getAge() {
	return age;
}
public void setParent(boolean p) {
	if (p == true) {
		parent = true;
	}
	else {
		parent = false;
	}
}
public void setGender(boolean f) {
	if (parent == true) {
		if (f == true) {
			female = true;
		}
		else {
		}
	} else {
		double c = Math.random();
		if (c <= 0.5) {
			female = true;
		}
		else {
			female = false;
		}
	}
}
public void setGender(String sexuality) {
	if ((player.getGender() == false && sexuality.equals("homosexual") || (player.getGender() == true && sexuality.equals("heterosexual")))){
		female = false;
	}
	else if ((player.getGender() == true && sexuality.equals("homosexual") || (player.getGender() == false && sexuality.equals("heterosexual")))) {
		female = true;
	}
	else if (sexuality.equals("bisexual")){
		double c = Math.random();
		if (c <= 0.5) {
			female = true;
		}
		else {
			female = false;
		}
	}
}
public void setName() throws FileNotFoundException {
	String fname, lname = "", suffix;
	if (female == true) {
		suffix = "F.txt";
	}
	else {
		suffix = "M.txt";
	}
	Scanner getFname1 = new Scanner(new File(stats.getCountry() + suffix), "UTF-8");
	ArrayList<String> listFname1 = new ArrayList<String>();
	while (getFname1.hasNext()){
		listFname1.add(getFname1.next());
		}
	getFname1.close();
	fname = listFname1.get(new Random().nextInt(listFname1.size()));
	
	if (parent == false) {
		Scanner getLname = new Scanner(new File(stats.getCountry() + "LastNames.txt"), "UTF-8");
		ArrayList<String> listLname = new ArrayList<String>();
		while (getLname.hasNextLine()){
			listLname.add(getLname.nextLine());
			}
		getLname.close();
		lname = listLname.get(new Random().nextInt(listLname.size()));
	}
	else if (parent == true){
		Scanner getLname = new Scanner(new File(stats.getCountry() + "LastNames.txt"), "UTF-8");
		ArrayList<String> listLname = new ArrayList<String>();
		while (getLname.hasNextLine()){
			listLname.add(getLname.nextLine());
			}
		getLname.close();
		lname = listLname.get(new Random().nextInt(listLname.size()));
		this.lname = lname;
	}
	name = fname + " " + lname;
}
public void setName(Relation mother) throws FileNotFoundException {
	String fname, lname = "", suffix;
	suffix = "M.txt";
	
	Scanner getFname1 = new Scanner(new File(stats.getCountry() + suffix), "UTF-8");
	ArrayList<String> listFname1 = new ArrayList<String>();
	while (getFname1.hasNext()){
		listFname1.add(getFname1.next());
		}
	getFname1.close();
	fname = listFname1.get(new Random().nextInt(listFname1.size()));
	lname = mother.getLname();
	name = fname + " " + lname;
	
}
public String getName() {
	return name;
}
public String getLname() {
	return lname;
}
public void chanceDeath() {
	double c = Math.random();
	if (age >= 50 && c <= 0.02 && alive == true) {
		alive = false;
		System.out.println("My " + who + " died. \nI went to the funeral.");
		player.setHappiness(-30);
	}
	else if (age >=70 && c <= 0.4 && alive == true) {
		alive = false;
		System.out.println("My " + who + " died. \nI went to the funeral.");
		player.setHappiness(-30);
		c = Math.random();
		if (c < 0.3) {
			int inherit = (int)(Math.random() * ((50000-100) + 1)) + 100;
			System.out.println("My " + who + " left me $" + inherit);
			player.giveMoney(-inherit);
		}
	}
	
}
}
	

