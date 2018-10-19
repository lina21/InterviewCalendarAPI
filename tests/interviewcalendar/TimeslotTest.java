package interviewcalendar;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeslotTest {
    private int id = 2018012216;
    private int year = 2018;
    private int month = 1;
    private int day = 22;
    private int startHour = 16;
    private Timeslot timeslot = new Timeslot(year, month, day, startHour);

    @Test
    void getID() {
        assertEquals(id, timeslot.getID());
    }

    @Test
    void getYear() {
        assertEquals(year, timeslot.getYear());
    }

    @Test
    void getMonth() {
        assertEquals(month, timeslot.getMonth());
    }

    @Test
    void getDay() {
        assertEquals(day, timeslot.getDay());
    }

    @Test
    void getStartHour() {
        assertEquals(startHour, timeslot.getStartHour());
    }

    @Test
    void compareTo() {
        assertEquals(-1, timeslot.compareTo(new Timeslot(year, month, day, startHour + 1)));
        assertEquals(0, timeslot.compareTo(new Timeslot(year, month, day, startHour )));
        assertEquals(1, timeslot.compareTo(new Timeslot(year, month, day, startHour - 1)));
    }
}