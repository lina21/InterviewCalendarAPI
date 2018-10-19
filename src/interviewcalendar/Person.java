package interviewcalendar;

import java.util.Set;
import java.util.TreeSet;

/**
 * Person corresponds to an individual who has a unique id, name and available timeslots in his calendar
 * 
 * @author Lina Gafurova
 *
 */
public class Person {
	private int id;
	private String name;
	private Set<Timeslot> availableTimeslots = new TreeSet<Timeslot>(); 
	
	Person(int aID, String aName) {
		id = aID;
		name = aName;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public Set<Timeslot> getAvailableTimeslots() {
		return availableTimeslots;
	}

	public void addAvailableTimeslot(int year, int month, int day, int timeslotStartHour) {
		addAvailableTimeslot(new Timeslot(year, month, day, timeslotStartHour));
	}

	public void addAvailableTimeslot(Timeslot timeslot) {
		if (!availableTimeslots.contains(timeslot)) availableTimeslots.add(timeslot);
	}
	
	public void removeUnavailableTimeslot(int year, int month, int day, int timeslotStartHour) {
		removeUnavailableTimeslot(new Timeslot(year, month, day, timeslotStartHour));
	}

	public void removeUnavailableTimeslot(Timeslot timeslot) {
		if (availableTimeslots.contains(timeslot)) availableTimeslots.remove(timeslot);
	}
}
