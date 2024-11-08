package com.acs560.Time;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link Time} class to validate its constructors and methods.
 * These tests cover valid and invalid time input formats, conversion to double values,
 * and retrieving individual components like hours, minutes, and seconds.
 */
class TimeTest {

    /**
     * Tests the {@link Time} constructor with valid input in the "MM:SS" format.
     * Verifies that hours, minutes, and seconds are correctly parsed.
     */
    @Test
    public void testTimeConstructor_ValidInput_Length2() {
        Time time = new Time("25:37");
        assertEquals(0, time.getHours());  // Hours should be 0
        assertEquals(25, time.getMinutes());  // Minutes should be 25
        assertEquals(37, time.getSeconds());  // Seconds should be 37
    }

    /**
     * Tests the {@link Time} constructor with invalid input in the "MM:SS" format.
     * Verifies that an exception is thrown when the input exceeds valid ranges.
     */
    @Test
    public void testTimeConstructor_InvalidInput_Length2() {
        assertThrows(IllegalArgumentException.class, () -> new Time("60:67"));
    }

    /**
     * Tests the {@link Time} constructor when the input contains non-numeric values in the "MM:SS" format.
     * Verifies that an exception is thrown for invalid numeric input.
     */
    @Test
    public void testTimeConstructor_NumberFormatException_Length2() {
        assertThrows(IllegalArgumentException.class, () -> new Time("MM:SS"));
    }

    /**
     * Tests the {@link Time} constructor with valid input in the "HH:MM:SS" format.
     * Verifies that hours, minutes, and seconds are correctly parsed.
     */
    @Test
    public void testTimeConstructor_ValidInput_Length3() {
        Time time = new Time("1:25:37");
        assertEquals(1, time.getHours());  // Hours should be 1
        assertEquals(25, time.getMinutes());  // Minutes should be 25
        assertEquals(37, time.getSeconds());  // Seconds should be 37
    }

    /**
     * Tests the {@link Time} constructor with invalid input in the "HH:MM:SS" format.
     * Verifies that an exception is thrown for negative values and out-of-range seconds.
     */
    @Test
    public void testTimeConstructor_InvalidInput_Length3() {
        assertThrows(IllegalArgumentException.class, () -> new Time("-1:60:67"));
    }

    /**
     * Tests the {@link Time} constructor when the input contains non-numeric values in the "HH:MM:SS" format.
     * Verifies that an exception is thrown for invalid numeric input.
     */
    @Test
    public void testTimeConstructor_NumberFormatException_Length3() {
        assertThrows(IllegalArgumentException.class, () -> new Time("HH:MM:SS"));
    }

    /**
     * Tests the {@link Time} constructor with invalid input that contains an extra time format (e.g., "HH:MM:SS:ss").
     * Verifies that an exception is thrown for unsupported time formats.
     */
    @Test
    public void testTimeConstructor_InvalidInput_ExtraTimeFormat() {
        assertThrows(IllegalArgumentException.class, () -> new Time("HH:MM:SS:ss"));
    }

    /**
     * Tests the {@link Time} constructor with a double value.
     * Verifies that the time is correctly converted into a string representation in the "HH:MM:SS" format.
     */
    @Test
    public void testTimeConstructor_WithDoubleValue() {
        Time time = new Time("1:25:37");
        assertEquals(85.61666666666666, time.toValue());  // Verifies that the double value conversion is correct
    }

    /**
     * Tests the {@link Time#getHours()} method.
     * Verifies that the correct number of hours is returned.
     */
    @Test
    public void testGetHours() {
        Time time = new Time("1:25:37");
        assertEquals(1, time.getHours());  // Verifies that the hours are correctly returned
    }

    /**
     * Tests the {@link Time#getMinutes()} method.
     * Verifies that the correct number of minutes is returned.
     */
    @Test
    public void testGetMinutes_ReturnsCorrectMinutes() {
        Time time = new Time("25:37");
        assertEquals(25, time.getMinutes());  // Verifies that the minutes are correctly returned
    }

    /**
     * Tests the {@link Time#getSeconds()} method.
     * Verifies that the correct number of seconds is returned.
     */
    @Test
    public void testGetMinutes_ReturnsCorrectSeconds() {
        Time time = new Time("1:25:37");
        assertEquals(37, time.getSeconds());  // Verifies that the seconds are correctly returned
    }

    /**
     * Tests the {@link Time} constructor with a double value that represents time in minutes.
     * Verifies that the time is correctly represented in the "HH:MM:SS" format.
     */
    @Test
    public void testTimeConstructorWithMinutes() {
        Time time = new Time(90.0);  // 90 minutes = 1:30
        assertEquals("1:30:00", time.toString());  // Verifies that 90 minutes is correctly converted to "1:30:00"
    }

}
