package interviewcalendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

/**
 * Interview Calendar is the main class for adding interviewers and candidates,
 * and operating on their available timeslots
 * 
 * @author Lina Gafurova
 *
 */
public class InterviewCalendar {	
	private int nextUniqueID = 0;	
	
	private HashMap<Integer, Person> people = new HashMap<>();
	
	// Arrays for remembering which ids correspond to interviewers and which to candidates
	private Set<Integer> interviewers = new TreeSet<Integer>();
	private Set<Integer> candidates = new TreeSet<Integer>();
	
	/**
	 * Add new person
	 * 
	 * @param name
	 * @return generated person's unique id
	 */
	private int addPerson(String name) {
		Person person = new Person(nextUniqueID, name);
		people.put(nextUniqueID, person);
		nextUniqueID++;		
		return person.getId();
	}
	
	/**
	 * Remove the person
	 * 
	 * @param id
	 */
	private void removePerson(int id) {
		if (people.containsKey(id)) people.remove(id);
	}
	
	/**
	 * Add new interviewer
	 * 
	 * @param name
	 * @return unique id of the person
	 */
	public int addInterviewer(String name) {
		int interviewerID = addPerson(name);
		interviewers.add(interviewerID);
		return interviewerID;
	}
	
	/**
	 * Remove interviewer
	 * 
	 * @param id
	 */
	public void removeInterviewer(int id) {
		if (interviewers.contains(id)) {
			interviewers.remove(id);
			removePerson(id);
		}
	}

	/**
	 * Get a list of interviewers' ids
	 * 
	 * @return
	 */
	public Set<Integer> getInterviewers() {
		return interviewers;
	}
	
	/**
	 * Get a list of candidates' ids
	 * 
	 * @return
	 */
	public Set<Integer> getCandidates() {
		return candidates;
	}
	
	/**
	 * Add new candidate
	 * 
	 * @param name
	 * @return unique id of the person
	 */
	public int addCandidate(String name) {
		int candidateID = addPerson(name);
		candidates.add(candidateID);
		return candidateID;
	}
	
	/**
	 * Remove candidate
	 * 
	 * @param id
	 */
	public void removeCandidate(int id) {
		if (candidates.contains(id)) {
			candidates.remove(id);
			removePerson(id);
		}
	}
	
	/**
	 * Add available timeslot to a person's calendar
	 * 
	 * @param personID
	 * @param year
	 * @param month
	 * @param day
	 * @param timeslotStartHour starting hour of the timeslot in 24-hour format
	 */
	public void addAvailableTimeslot(int personID, int year, int month, int day, int timeslotStartHour) {
		people.get(personID).addAvailableTimeslot(year, month, day, timeslotStartHour);	
	}
	
	/**
	 * Add a range of available timeslots to a person's calendar
	 * 
	 * @param personID
	 * @param year
	 * @param month
	 * @param day
	 * @param timeslotStartHour starting hour of the first available timeslot in 24-hour format
	 * @param timeslotEndHour ending hour of the last available timeslot in 24-hour format
	 */
	public void addAvailableTimeslots(int personID, int year, int month, int day, int timeslotStartHour, int timeslotEndHour) {
		for(int startHour = timeslotStartHour; startHour < timeslotEndHour; startHour++)
			people.get(personID).addAvailableTimeslot(year, month, day, startHour);	
	}
	
	/**
	 * Remove unavailable timeslot from a person's calendar
	 * 
	 * @param personID
	 * @param year
	 * @param month
	 * @param day
	 * @param timeslotStartHour starting hour of the timeslot in 24-hour format
	 */
	public void removeUnavailableTimeslot(int personID, int year, int month, int day, int timeslotStartHour) {
		people.get(personID).removeUnavailableTimeslot(year, month, day, timeslotStartHour);	
	}
	
	/**
	 * Remove a range of unavailable timeslots from a person's calendar
	 * 
	 * @param personID
	 * @param year
	 * @param month
	 * @param day
	 * @param timeslotStartHour starting hour of the first unavailable timeslot in 24-hour format
	 * @param timeslotEndHour ending hour of the last unavailable timeslot in 24-hour format
	 */
	public void removeUnavailableTimeslots(int personID, int year, int month, int day, int timeslotStartHour, int timeslotEndHour) {
		for(int startHour = timeslotStartHour; startHour < timeslotEndHour; startHour++)
			people.get(personID).removeUnavailableTimeslot(year, month, day, startHour);	
	}
	
	/**
	 * Get all timeslots which are available for both the candidate and the interviewer
	 * 
	 * @param candidateID
	 * @param interviewerID
	 * @return a set of timeslots
	 */
	public Set<Timeslot> getTimeslotsIntersection(int candidateID, int interviewerID) {
		return getTimeslotsIntersection(candidateID, Collections.singleton(interviewerID));
	}
	
	/**
	 * Get all timeslots which are available for the candidate and for all of the interviewers
	 * 
	 * @param candidateID
	 * @param interviewersIDs a list of interviewers' IDs
	 * @return a set of timeslots
	 */
	public Set<Timeslot> getTimeslotsIntersection(int candidateID, Set<Integer> interviewersIDs) {
		Set<Timeslot> possibleSlots = new TreeSet<Timeslot>();
		possibleSlots.addAll(people.get(candidateID).getAvailableTimeslots());

		for(int interviewerID : interviewersIDs) {
			possibleSlots.retainAll(people.get(interviewerID).getAvailableTimeslots());
		}
		
		return possibleSlots;
	}

}
