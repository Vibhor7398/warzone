package Services;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestMathService {
    MathService service;
    @Before
    public void setUp() throws Exception {
        service = new MathService();
    }

    @Test
    public void testIsInteger() {
        assertTrue(service.isInteger("1"));
        assertFalse(service.isInteger("test"));
        assertFalse(service.isInteger("999999999999"));
    }
}