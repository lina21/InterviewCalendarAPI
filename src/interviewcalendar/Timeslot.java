package interviewcalendar;

/**
 * Timeslot is a 1-hour period defined by date and start hour, 
 * which together comprise its unique id
 * 
 * @author Lina Gafurova
 *
 */
public class Timeslot implements Comparable<Timeslot> {
	private int id;
	
	Timeslot(int year, int month, int day, int startHour) {
		id = (int) (year * 1e6 + month * 1e4 + day * 1e2 + startHour);
	}
	
	/**
	 * Get timeslot id which reflects the date and start hour in the format yyyymmddhh
	 * 
	 * @return
	 */
	public int getID() {
		return id;
	}
	
	public int getYear() {
		return (int) (id / 1e6);
	}
	
	public int getMonth() {
		return (int) ((id % 1e6) / 1e4);
	}
	
	public int getDay() {
		return (int) ((id % 1e4) / 1e2);
	}
	
	public int getStartHour() {
		return (int) (id % 1e2);
	}
	
	@Override
	public int compareTo(Timeslot timeslot) {
		if (this.id < timeslot.getID()) return -1;
		else if (this.id > timeslot.getID()) return 1;
		
		return 0;
	}

}
