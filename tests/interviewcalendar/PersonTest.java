package interviewcalendar;

import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    private int id = 9;
    private String name = "John";
    private Timeslot timeslot1 = new Timeslot(2018, 1, 22, 16);
    private Timeslot timeslot2 = new Timeslot(2018, 1, 23, 11);
    private Timeslot timeslot3 = new Timeslot(2018, 1, 25, 9);
    private Set<Timeslot> availableTimeslots = new TreeSet<Timeslot>();
    private Person person = new Person(id, name);

    PersonTest() {
        availableTimeslots.add(timeslot1);
        availableTimeslots.add(timeslot2);
    }


    @Test
    void getId() {
        assertEquals(id, person.getId());
    }

    @Test
    void getName() {
        assertEquals(name, person.getName());
    }

    @Test
    void getAvailableTimeslots() {
        person.addAvailableTimeslot(timeslot1);
        person.addAvailableTimeslot(timeslot2);
        assertEquals(availableTimeslots, person.getAvailableTimeslots());
    }

    @Test
    void addAvailableTimeslot() {
        person.addAvailableTimeslot(timeslot3);
        assertEquals(true, person.getAvailableTimeslots().contains(timeslot3));
    }

    @Test
    void removeUnavailableTimeslot() {
        person.removeUnavailableTimeslot(timeslot1);
        assertEquals(false, person.getAvailableTimeslots().contains(timeslot1));
    }
}