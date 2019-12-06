import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Statistics {
	private int year;
	private String month;
	private int day;
	private boolean leapYear;
	private String country;
	
	//9 methods 12/3
	public Statistics() throws FileNotFoundException {
		setDayMonth();
		setYear();
		setCountry();
		checkLeapYear();
	}
	public void setDayMonth() throws FileNotFoundException {
		//Thirty days hath September, April, June, and November;
		Scanner getMonth = new Scanner(new File("months.txt"));
		ArrayList<String> monthList = new ArrayList<String>();
		while (getMonth.hasNextLine()){
			monthList.add(getMonth.nextLine());
			}
		getMonth.close();
		month = monthList.get(new Random().nextInt(monthList.size()));
		checkLeapYear();
		if (month.contains("September") || month.contains("April") || month.contains("June") || month.contains("November")) {
			day = (int)(Math.random() * ((30-1) + 1)) + 1;
		} else if (month.contains("February") && leapYear == true) {
			day = (int)(Math.random() * ((29-1) + 1)) + 1;
		} else if (month.contains("February") && leapYear == false){
			day = (int)(Math.random() * ((28-1) + 1)) + 1;
		} else {
			day = (int)(Math.random() * ((31-1) + 1)) + 1;
		}
	}
	public void setYear() {
		year = (int)(Math.random() * ((2090-1900) + 1)) + 1900;
}
	public String getDayMonth() {
		String dm = month + " " + day;
		return dm;
	}
	public void increaseYear() {
		year++;
	}
	public int getYear() {
		return year;
	}
	public void checkLeapYear() {
		if (year % 4 == 0) {
			if (year % 100 == 0) {
				if (year % 400 == 0) {
					leapYear = true;
				} else {
					leapYear = false;
				}
			}
		 if (year % 100 != 0) {
			leapYear = true;
		 }
		} else {
			leapYear = false;
		}
	}
	public double chanceRandom() {
		double chance = Math.random();
		return chance;
	}
	/*public String daughter(boolean gender) {
		String d = "daughter", s = "son";
		if (gender == true) {
			return d;
		} else {
			return s;
		}
	}*/
	public void setCountry() throws FileNotFoundException {
		Scanner getCountry = new Scanner(new File("countries.txt"));
		ArrayList<String> countryList = new ArrayList<String>();
		while ( getCountry.hasNextLine()){
			countryList.add( getCountry.nextLine());
			}
		getCountry.close();
		country = countryList.get(new Random().nextInt(countryList.size()));
	}
	public String getCountry() {
		return country;
	}
	
}
