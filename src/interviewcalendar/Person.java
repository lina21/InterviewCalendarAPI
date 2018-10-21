package interviewcalendar;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;
import java.util.Collection;

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

	public void addAvailableTimeslots(int year, int month, int day, int timeslotStartHour, int timeslotEndHour) {
		for(int startHour = timeslotStartHour; startHour < timeslotEndHour; startHour++)
			addAvailableTimeslot(year, month, day, startHour);
	}

	public void addAvailableTimeslot(Timeslot timeslot) {
		if (!availableTimeslots.contains(timeslot)) availableTimeslots.add(timeslot);
	}

	public void addAvailableTimeslots(Collection<Timeslot> timeslots) {
		availableTimeslots.addAll(timeslots);
	}
	
	public void removeUnavailableTimeslot(int year, int month, int day, int timeslotStartHour) {
		removeUnavailableTimeslot(new Timeslot(year, month, day, timeslotStartHour));
	}

	public void removeUnavailableTimeslots(int year, int month, int day, int timeslotStartHour, int timeslotEndHour) {
		for(int startHour = timeslotStartHour; startHour < timeslotEndHour; startHour++)
			removeUnavailableTimeslot(year, month, day, startHour);
	}

	public void removeUnavailableTimeslot(Timeslot timeslot) {
		if (availableTimeslots.contains(timeslot)) availableTimeslots.remove(timeslot);
	}

	public void removeUnavailableTimeslots(Collection<Timeslot> timeslots) {
		availableTimeslots.removeAll(timeslots);
	}

	public Set<Timeslot> getTimeslotsIntersection(Person person) {
		return getTimeslotsIntersection(Collections.singleton(person));
	}

	public Set<Timeslot> getTimeslotsIntersection(Collection<Person> people) {
		Set<Timeslot> possibleSlots = new TreeSet<Timeslot>();
		possibleSlots.addAll(availableTimeslots);

		for(Person person: people) {
			possibleSlots.retainAll(person.getAvailableTimeslots());
		}

		return possibleSlots;
	}
}
