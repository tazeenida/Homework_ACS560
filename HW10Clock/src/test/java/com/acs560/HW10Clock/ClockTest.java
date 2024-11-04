package com.acs560.HW10Clock;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ClockTest {

	@Test
	public void testHoursNegativeInputException() {
		IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Clock(-1, 0, 0));

		assertEquals("Negative values not permitted", thrown.getMessage());
	}

	@Test
	public void testMinutesNegativeInputException() {
		IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Clock(0, -1, 0));

		assertEquals("Negative values not permitted", thrown.getMessage());
	}

	@Test
	public void testSecondsNegativeInputException() {
		IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Clock(0, 0, -1));

		assertEquals("Negative values not permitted", thrown.getMessage());

	}
	
	@Test
	public void testInvalidTimeInputException() {
		assertThrows(IllegalArgumentException.class, ()-> new Clock (24,0,0));
	}
	
	@Test
	public void testAddHourMethod() {
		Clock clock= new Clock(23,0,0);
		clock.addHour();
		assertEquals("00:00:00", clock.get24HourFormat());	
	}
	
	@Test
	public void testAddMinuteMethod() {
		Clock clock = new Clock(0,32,0);
		clock.addMinute();
		assertEquals("00:33:00", clock.get24HourFormat());
		
	}
	
	@Test
	public void testAddMinuteMethodHourIncrement() {
		Clock clock = new Clock(0,59,0);
		clock.addMinute();
		assertEquals("01:00:00", clock.get24HourFormat());
		
	}
	
	@Test
	public void testAddSecondsMethod() {
		Clock clock = new Clock(0,0,59);
		clock.addSecond();
		assertEquals("00:01:00", clock.get24HourFormat());
		
		clock = new Clock(0,0,30);
		clock.addSecond();
		assertEquals("00:00:31", clock.get24HourFormat());
	}
	
	@Test
	public void testGet24HourFormat() {
        Clock clock = new Clock(9, 10, 0);
        assertEquals("09:10:00", clock.get24HourFormat(), "Expected padded output for 9:10:0");
        
        clock = new Clock(5, 10, 0);
        assertEquals("05:10:00", clock.get24HourFormat(), "Expected padded output for 5:10:0");
        
        clock = new Clock(7, 6, 0);
        assertEquals("07:06:00", clock.get24HourFormat(), "Expected padded output for 7:6:0");
        
        clock = new Clock(8, 4, 3);
        assertEquals("08:04:03", clock.get24HourFormat(), "Expected padded output for 8:4:3");

    }
	
	@Test
	public void testGet12HourAMFormat() {
        Clock clock = new Clock(9, 10, 0);
        assertEquals("9:10:00 AM", clock.get12HourFormat());
        
        clock = new Clock(0, 10, 0);
        assertEquals("12:10:00 AM", clock.get12HourFormat());
        
        clock = new Clock(5, 10, 0);
        assertEquals("5:10:00 AM", clock.get12HourFormat());
        
        clock = new Clock(7, 6, 0);
        assertEquals("7:06:00 AM", clock.get12HourFormat());
        
        clock = new Clock(8, 4, 3);
        assertEquals("8:04:03 AM", clock.get12HourFormat());

    }
	
	@Test
	public void testGet12HourPMFormat() {
        Clock clock = new Clock(19, 10, 0);
        assertEquals("7:10:00 PM", clock.get12HourFormat());
        
        clock = new Clock(15, 10, 0);
        assertEquals("3:10:00 PM", clock.get12HourFormat());
        
        clock = new Clock(17, 6, 0);
        assertEquals("5:06:00 PM", clock.get12HourFormat());
        
        clock = new Clock(18, 4, 3);
        assertEquals("6:04:03 PM", clock.get12HourFormat());

    }
}
