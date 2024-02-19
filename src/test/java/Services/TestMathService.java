package Services;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for the MathService class.
 */
public class TestMathService {
    MathService service;

    /**
     * Sets up the MathService instance before each test.
     *
     * @throws Exception If an error occurs during setup.
     */
    @Before
    public void setUp() throws Exception {
        service = new MathService();
    }

    /**
     * Test case for checking if a valid integer string is correctly identified as an integer.
     */
    @Test
    public void testIsInteger_valid() {
        assertTrue(service.isInteger("1"));
    }

    /**
     * Test case for checking if invalid integer strings are correctly identified as non-integers.
     */
    @Test
    public void testIsInteger_invalid() {
        assertFalse(service.isInteger("test"));
        assertFalse(service.isInteger("999999999999"));
    }
}