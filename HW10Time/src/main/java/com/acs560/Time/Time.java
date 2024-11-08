package com.acs560.Time;
/**
 * Copyright (c) 2021
 * Par Training Software Solutions
 * All rights reserved.
 * 
 * All intellectual and technical concepts contained herein are protected
 * by trade secret or copyright laws.
 * 
 * Dissemination of this information or reproduction of this material is
 * strictly forbidden unless prior written permission is obtained from
 * Par Training Software Solutions.
 */

import java.util.Objects;

/**
 * A time for a race distance.
 * Can be in the form MM:SS or HH:MM:SS
 */
public class Time {
	
	private final String strTime;
    private final double dblTime;
    private Integer hours;
    private Integer minutes;
    private Integer seconds;
    
    /**
     * Constructor
     * Example usage: 1:25:37
     * @param time - the time as a string
     * @throws IllegalArgumentException if the time cannot be parsed properly
     */
    public Time(String time) {
    	String[] tokens = time.split(":");
    	
    	if (tokens.length == 2) {
        	try {
        		minutes = Integer.parseInt(tokens[0]);
        		seconds = Integer.parseInt(tokens[1]);
        		
        		if (seconds < 0 || seconds > 59 || minutes < 0 || minutes > 59) {
        			throw new IllegalArgumentException(time + " is not a valid time");
        		}
        		
        		this.dblTime = minutes + seconds / 60.0;
        	} catch (NumberFormatException ex) {
        		throw new IllegalArgumentException(time + " is not a valid time");
        	}
    	} else if (tokens.length == 3) {
    		try {
    			hours = Integer.parseInt(tokens[0]);
        		minutes = Integer.parseInt(tokens[1]);
    			seconds = Integer.parseInt(tokens[2]);
        		
        		if (seconds < 0 || seconds > 59 || minutes < 0 || minutes > 59 || hours < 0) {
        			throw new IllegalArgumentException(time + " is not a valid time");
        		}
        		
    			this.dblTime = 60 * hours + minutes + seconds / 60.0;
    		} catch (NumberFormatException ex) {
    			throw new IllegalArgumentException(time + " is not a valid time");
    		}
    	} else {
    		throw new IllegalArgumentException(time + " is not a valid time");
    	}
    	
    	this.strTime = convert(dblTime);
    }

    /**
     * Constructor. 
     * Example usage: 4.5 = "4:30"
     * @param time - the time as a double value
     */
    public Time(double time){
        this.dblTime = time;
        this.strTime = convert(time);
    }

    /**
     * Get the hours of this time.
     * @return - the hours
     */
    public int getHours(){
        return hours == null ? 0 : hours;
    }

    /**
     * Get the minutes for this time
     * @return - the minutes
     */
    public int getMinutes(){
        return minutes == null ? 0 : minutes;
    }

    /**
     * Get the seconds for this time
     * @return - the seconds
     */
    public int getSeconds(){
        return seconds == null ? 0 : seconds;
    }
    
    /**
     * Get the time as a double
     * Example: 4.5 = "4:30"
     * @return - the time
     */
    public double toValue() {
    	return dblTime;
    }
    
    @Override
    /**
     * Get the time as a string
     * "4:14" or "3:07:48"
     * @return - the string representation
     */
    public String toString() {
        return this.strTime;
    }

    private String convert(double time) {
        String convertedTime;

        if (time < 60){
        	convertedTime = convert(time, false);
        } else {
        	int convertedHours = (int) (time / 60);
            this.hours = convertedHours;
            double convertedMinutes = time - (60 * convertedHours);
            convertedTime = convertedHours + ":" +  convert(convertedMinutes, true);
        }

        return convertedTime;
    }

    private String convert(double time, boolean hasHours){
        if (time > 60){
            throw new IllegalArgumentException("Time exceeds 60 minutes.");
        }

        int convertedMinutes = (int)time;
        double convertedSeconds = (60 * (time - convertedMinutes));

        if ((int)Math.round(convertedSeconds) == 60) {
            convertedMinutes++;
            convertedSeconds = 0;
        }

        convertedSeconds = (int)Math.round(convertedSeconds);
        
        this.minutes = convertedMinutes;
        this.seconds = (int)convertedSeconds;

        return (hasHours ? pad(convertedMinutes) : convertedMinutes) + ":" + pad((int)Math.round(convertedSeconds));
    }

	private String pad(int number) {
		return (number <10 ? "0":"") + number;
	}

}