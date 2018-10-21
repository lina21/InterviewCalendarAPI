package interviewcalendar;

import org.junit.jupiter.api.Test;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class InterviewCalendarTest {
    private InterviewCalendar interviewCalendar = new InterviewCalendar();

    InterviewCalendarTest() {
        interviewCalendar.addInterviewer("Susan");
        interviewCalendar.addCandidate("Blair");
    }

    @Test
    void addInterviewer() {
        int interviewerID = interviewCalendar.addInterviewer("John");
        assertEquals(2, interviewerID);
        assertEquals(true, interviewCalendar.getInterviewers().containsKey(2));
        assertEquals(true, interviewCalendar.getInterviewer(2).getName().equals("John"));
    }

    @Test
    void removeInterviewer() {
        interviewCalendar.removeInterviewer(0);
        assertEquals(false, interviewCalendar.getInterviewers().containsKey(0));
    }

    @Test
    void getInterviewers() {
        assertEquals(true, interviewCalendar.getInterviewers().containsKey(0));
    }

    @Test
    void getCandidates() {
        assertEquals(true, interviewCalendar.getCandidates().containsKey(1));
    }

    @Test
    void addCandidate() {
        int candidateID = interviewCalendar.addCandidate("John");
        assertEquals(2, candidateID);
        assertEquals(true, interviewCalendar.getCandidates().containsKey(candidateID));
    }

    @Test
    void removeCandidate() {
        interviewCalendar.removeCandidate(1);
        assertEquals(false, interviewCalendar.getCandidates().containsKey(1));
    }

    @Test
    void addAvailableTimeslot() {
        Timeslot timeslot = new Timeslot(2018, 10, 22, 9);
        interviewCalendar.addAvailableTimeslot(1, 2018, 10, 22, 9);
        assertEquals(true, interviewCalendar.getCandidate(1).getAvailableTimeslots().contains(timeslot));
    }

    @Test
    void addAvailableTimeslots() {
        Timeslot timeslot1 = new Timeslot(2018, 10, 22, 9);
        Timeslot timeslot2 = new Timeslot(2018, 10, 22, 10);
        interviewCalendar.addAvailableTimeslots(1, 2018, 10, 22, 9, 11);
        assertTrue(interviewCalendar.getCandidate(1).getAvailableTimeslots().contains(timeslot1));
        assertTrue(interviewCalendar.getCandidate(1).getAvailableTimeslots().contains(timeslot2));
    }

    @Test
    void removeUnavailableTimeslot() {
        Timeslot timeslot = new Timeslot(2018, 10, 22, 9);
        interviewCalendar.addAvailableTimeslot(1, 2018, 10, 22, 9);
        interviewCalendar.removeUnavailableTimeslot(1, 2018, 10, 22, 9);
        assertFalse(interviewCalendar.getCandidate(1).getAvailableTimeslots().contains(timeslot));
    }

    @Test
    void removeUnavailableTimeslots() {
        Timeslot timeslot1 = new Timeslot(2018, 10, 22, 9);
        Timeslot timeslot2 = new Timeslot(2018, 10, 22, 10);
        interviewCalendar.removeUnavailableTimeslots(1, 2018, 10, 22, 9, 11);
        assertFalse(interviewCalendar.getCandidate(1).getAvailableTimeslots().contains(timeslot1));
        assertFalse(interviewCalendar.getCandidate(1).getAvailableTimeslots().contains(timeslot2));
    }

    @Test
    void getTimeslotsIntersection() {
        assertTrue(interviewCalendar.getTimeslotsIntersection(0, 1).isEmpty());
        interviewCalendar.addAvailableTimeslots(0, 2018, 10, 22, 9, 14);
        interviewCalendar.addAvailableTimeslots(1, 2018, 10, 22, 14, 18);
        assertTrue(interviewCalendar.getTimeslotsIntersection(1, 0).isEmpty());
        interviewCalendar.addAvailableTimeslots(0, 2018, 10, 22, 14, 16);
        Set<Timeslot> timeslotSet = interviewCalendar.getTimeslotsIntersection(1, 0);
        assertTrue(timeslotSet.contains(new Timeslot(2018, 10, 22, 14)));
        assertTrue(timeslotSet.contains(new Timeslot(2018, 10, 22, 15)));
    }

    @Test
    void getTimeslotsIntersection1() {
        interviewCalendar.addInterviewer("Ursula");
        interviewCalendar.addAvailableTimeslots(0, 2018, 10, 22, 9, 16);
        interviewCalendar.addAvailableTimeslots(1, 2018, 10, 22, 14, 18);
        assertTrue(interviewCalendar.getTimeslotsIntersection(1, interviewCalendar.getInterviewers().keySet()).isEmpty());
        interviewCalendar.addAvailableTimeslots(2, 2018, 10, 22, 14, 16);
        Set<Timeslot> timeslotSet = interviewCalendar.getTimeslotsIntersection(1, interviewCalendar.getInterviewers().keySet());
        assertTrue(timeslotSet.contains(new Timeslot(2018, 10, 22, 14)));
        assertTrue(timeslotSet.contains(new Timeslot(2018, 10, 22, 15)));
    }
}