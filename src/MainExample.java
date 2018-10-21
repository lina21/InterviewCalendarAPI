import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import interviewcalendar.InterviewCalendar;
import interviewcalendar.Timeslot;
import interviewcalendar.Person;

/**
 * Demonstration of Interview Calendar API usage
 * 
 * @author Lina Gafurova
 *
 */
public class MainExample {

	public static void main(String[] args) {
		InterviewCalendar interviewCalendar = new InterviewCalendar();

		// Add a candidate
		Person candidateJohanna = interviewCalendar.getCandidate(interviewCalendar.addCandidate("Johanna"));
		System.out.println("Candidate " + candidateJohanna.getName() + " available for interview:");

		// Available for interview from 9am to 10am any weekday next week
		for (int day = 22; day < 27; day++)
			candidateJohanna.addAvailableTimeslots(2018, 10, day, 9, 10);
		System.out.println("- from 9am to 10am any weekday next week");

		// ...and from 10am to 12pm on Wednesday
		candidateJohanna.addAvailableTimeslots(2018, 10, 24, 10, 12);
		System.out.println("- from 10am to 12pm on Wednesday\n");
		
		// Add interviewer Philipp
		Person interviewerPhilipp = interviewCalendar.getInterviewer(interviewCalendar.addInterviewer("Philipp"));
		System.out.println("Interviewer " + interviewerPhilipp.getName() + " available:");

		// Available next week each day from 9am through 4pm without breaks
		for (int day = 22; day < 27; day++)
			interviewerPhilipp.addAvailableTimeslots(2018, 10, day, 9, 16);
		System.out.println("- next week each day from 9am through 4pm without breaks\n");
		
		// Add interviewer Sarah
		Person interviewerSarah = interviewCalendar.getInterviewer(interviewCalendar.addInterviewer("Sarah"));
		System.out.println("Interviewer " + interviewerSarah.getName() + " available:");

		// Available from 12pm to 6pm on Monday and Wednesday
		interviewerSarah.addAvailableTimeslots(2018, 10, 22, 12, 18);
		interviewerSarah.addAvailableTimeslots(2018, 10, 24, 12, 18);
		System.out.println("- from 12pm to 6pm on Monday and Wednesday");
		// ...and from 9am to 12pm on Tuesday and Thursday
		interviewerSarah.addAvailableTimeslots(2018, 10, 23, 9, 12);
		interviewerSarah.addAvailableTimeslots(2018, 10, 25, 9, 12);
		System.out.println("- from 9am to 12pm on Tuesday and Thursday\n");

		
		// Get collection of possible 1-hour timeslots for the interview
		Set<Timeslot> timeslots = candidateJohanna.getTimeslotsIntersection(interviewCalendar.getInterviewers().values());
		System.out.println("Found the following possible timeslots for an interview:");
		
		// Print the collection to the console in a specific format
		DateFormat formatter1 = new SimpleDateFormat("yyyy/MM/dd");
		DateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy EEE");
		for (Timeslot slot : timeslots) {
			String dateAsStr = slot.getYear() + "/" + slot.getMonth() + "/" + slot.getDay();
			try {
				Date date = formatter1.parse(dateAsStr);
				System.out.println(String.format("%s from %d to %d", formatter2.format(date), slot.getStartHour(), slot.getStartHour()+1));
			} catch (ParseException e) {
				System.out.println(String.format("Error parsing the date %s: %s", dateAsStr, e.getMessage()));
			}
		}
	}

}
