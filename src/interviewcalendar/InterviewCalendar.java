package interviewcalendar;
import java.util.*;

/**
 * Interview Calendar is the main class for adding interviewers and candidates,
 * and operating on their available timeslots
 * 
 * @author Lina Gafurova
 *
 */
public class InterviewCalendar {	
	private int nextUniqueID = 0;
	
	// Storing interviewers and candidates in separate collections
	private HashMap<Integer, Person> interviewers = new HashMap<>();
	private HashMap<Integer, Person> candidates = new HashMap<>();

	/**
	 * Add new person
	 *
	 * @param people collection where the person should be added
	 * @param name name of the person to be added
	 * @return generated person's unique id
	 */
	private int addPerson(HashMap<Integer, Person> people, String name) {
		Person person = new Person(nextUniqueID, name);
		people.put(nextUniqueID, person);
		nextUniqueID++;		
		return person.getId();
	}

	/**
	 * Remove the person
	 *
	 * @param people collection where the person belongs
	 * @param id unique id of the person
	 */
	private void removePerson(HashMap<Integer, Person> people, int id) {
		if (people.containsKey(id)) people.remove(id);
	}

	/**
	 * Get Person by id
	 *
	 * @param people collection where the person belongs
	 * @param id
	 * @return
	 */
	private Person getPerson(HashMap<Integer, Person> people, int id) {
		return people.get(id);
	}

	/**
	 * Get person by his unique id (for both interviewers and candidates)
	 *
	 * @param id
	 * @return
	 */
	public Person getPerson(int id) {
		if (isInterviewer(id)) return interviewers.get(id);
		else if (isCandidate(id)) return candidates.get(id);
		else return null;
	}

	/**
	 * Check if the person is interviewer
	 *
	 * @param id
	 * @return
	 */
	public boolean isInterviewer(int id) {
		if (interviewers.containsKey(id)) return true;
		return false;
	}

	/**
	 * Check if the person is candidate
	 *
	 * @param id
	 * @return
	 */
	public boolean isCandidate(int id) {
		if (candidates.containsKey(id)) return true;
		return false;
	}


	/**
	 * Add new interviewer
	 * 
	 * @param name
	 * @return unique id of the person
	 */
	public int addInterviewer(String name) {
		return addPerson(interviewers, name);
	}

	/**
	 * Remove interviewer
	 * 
	 * @param id
	 */
	public void removeInterviewer(int id) {
		removePerson(interviewers, id);
	}

	/**
	 * Get interviewer by id
	 *
	 * @param id
	 * @return
	 */
	public Person getInterviewer(int id) {
		return getPerson(interviewers, id);
	}

	/**
	 * Get the collection of interviewers
	 *
	 * @return
	 */
	public HashMap<Integer, Person> getInterviewers() {
		return interviewers;
	}

	/**
	 * Get the collection of candidates
	 * 
	 * @return
	 */
	public HashMap<Integer, Person> getCandidates() {
		return candidates;
	}
	
	/**
	 * Add new candidate
	 * 
	 * @param name
	 * @return unique id of the person
	 */
	public int addCandidate(String name) {
		return addPerson(candidates, name);
	}
	
	/**
	 * Remove candidate
	 * 
	 * @param id
	 */
	public void removeCandidate(int id) {
		removePerson(candidates, id);
	}

	/**
	 * Get candidate by id
	 *
	 * @param id
	 * @return
	 */
	public Person getCandidate(int id) {
		return getPerson(candidates, id);
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
		getPerson(personID).addAvailableTimeslot(year, month, day, timeslotStartHour);
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
	    getPerson(personID).addAvailableTimeslots(year, month, day, timeslotStartHour, timeslotEndHour);
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
		getPerson(personID).removeUnavailableTimeslot(year, month, day, timeslotStartHour);
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
	    getPerson(personID).removeUnavailableTimeslots(year, month, day, timeslotStartHour, timeslotEndHour);
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
	 * @param interviewersIDs a collection of interviewers' IDs
	 * @return a set of timeslots
	 */
	public Set<Timeslot> getTimeslotsIntersection(int candidateID, Collection<Integer> interviewersIDs) {
		List<Person> people = new LinkedList<>();
		for(Integer interviewerID : interviewersIDs) {
			Person person = getPerson(interviewerID);
			if (person != null) people.add(person);
		}

		return getPerson(candidateID).getTimeslotsIntersection(people);
	}

}
