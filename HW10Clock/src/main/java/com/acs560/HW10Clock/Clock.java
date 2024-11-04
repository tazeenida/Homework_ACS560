package com.acs560.HW10Clock;
/**
 * The Clock class representing the time in 
 * both 12 hour h:mm:ss AM/PM and 
 * 24 hour hh:mm:ss formats.
 * 
 * @author mcpar
 *
 */
public class Clock {
	
	private int hours;
	private int minutes;
	private int seconds;
	

	/**
	 * Constructor
	 * @param hours - the initial hours
	 * @param minutes - the initial minutes
	 * @param seconds - the initial seconds
	 */
	public Clock(int hours, int minutes, int seconds) {
		if (hours < 0 || minutes < 0 || seconds < 0) { 
			throw new IllegalArgumentException("Negative values not permitted");
		}
		
		if ( hours >= 24 || minutes >= 60 || seconds >= 60) { 
			throw new IllegalArgumentException("Invalid time values: hours must be 0-23, minutes must be 0-59, seconds must be 0-59.");
		}
		
		this.hours = hours;
		this.minutes = minutes;
		this.seconds = seconds;
	}
	
	/**
	 * Add an hour to the clock.
	 */
	public void addHour() {
	    if (hours == 23) {
	        hours = 0;
	    }
	    else {
	        hours++;
	    }
	}
	
	/**
	 * Add a minute to the clock.
	 */
	public void addMinute() {
	    if (minutes == 59) {
	        minutes = 0;
	        addHour();
	    }
	    else {
	        minutes++;
	    }
	}

	/**
	 * Add a second to the clock.
	 */
	public void addSecond() {
	    if (seconds == 59) {
	        seconds = 0;
	        addMinute();
	    }
	    else {
	        seconds++;
	    }
	}
	
	/**
	 * Get the time in 24 hour format: hh:mm:ss
	 * @return - the 24 hour format time
	 */
	public String get24HourFormat() {
	    String format24;

	    format24 = pad(hours) + ":" + pad(minutes) + ":" + pad(seconds);

	    return format24;
	}

	/**
	 * Get the suffix
	 * @return - the suffix
	 */
	private String getSuffix() {
	    String suffix;

	    if (hours < 12) {
	        suffix = "AM";
	    }
	    else {
	        suffix = "PM";
	    }

	    return suffix;
	}

	/**
	 * Get the 12 hour format: h:mm:ss AM/PM
	 * @return - the 12 hour format
	 */
	public String get12HourFormat() {
	    int displayHours = hours;

	    if (displayHours == 0) {
	    	displayHours = 12;
	    }
	    else if (displayHours > 12) {
	    	displayHours = displayHours - 12;
	    }

	    return displayHours + ":" + pad(minutes) + ":" + pad(seconds) + " " + getSuffix();
	}

	/**
	 * Pads a value as a two-digit representation.
	 * e.g. 9 is "09"
	 * @param value - the value to pad
	 * @return - the value as two-digts.
	 */
	private String pad(int value) {
	    String paddedValue;

	    if (value < 10) {
	        paddedValue = "0" + value;
	    }
	    else {
	        paddedValue = String.valueOf(value);
	    }

	    return paddedValue;
	}
	
	

}
