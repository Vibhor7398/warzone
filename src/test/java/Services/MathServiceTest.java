/**
 *
 * 
 * @author Vibhor Gulati, Apoorva Sharma, Saphal Ghirmire, Inderjeet Singh Chauhan, Mohammad Zaid
 * @version 1.0
 */
package Services;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test class for the MathService class.
 */
public class MathServiceTest {

    MathService service;

    /**
     * Setup method executed before each test case.
     * It initializes an instance of the MathService class.
     */
    @Before
    public void setUp() throws Exception {
        service = new MathService();
    }

    /**
     * Test method to verify the behavior of the isInteger method.
     */
    @Test
    public void testIsInteger() {
        assertTrue(service.isInteger("1"));
        assertFalse(service.isInteger("test"));
        assertFalse(service.isInteger("999999999999"));
    }
}