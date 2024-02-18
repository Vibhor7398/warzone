package Services;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommandValidationServiceTest {

    CommandValidationService service;

    @Before
    public void setUp() throws Exception {
        service = new CommandValidationService();
    }

    @Test
    public void testValidateLoadMapCommand() {
        assertTrue(service.validateCommand("loadmap brasil.map"));
        assertFalse(service.validateCommand("loadmap asia.map"));
        assertFalse(service.validateCommand("loadmap"));
        assertFalse(service.validateCommand("loadmap brasil.map 1"));;
    }

    @Test
    public void testValidateShowMapCommand() {
        assertTrue(service.validateCommand("showmap"));
        assertFalse(service.validateCommand("showmap 1"));
    }

    @Test
    public void testValidateSaveMapCommand() {
        assertTrue(service.validateCommand("savemap brasil.map"));
        assertTrue(service.validateCommand("savemap asia.map"));
        assertFalse(service.validateCommand("savemap"));
        assertFalse(service.validateCommand("savemap asia.map 1"));
    }

    @Test
    public void testValidateGetBaseCommand() {
        assertEquals(service.getBaseCommand("loadmap brasil.map"), "loadmap");
        assertEquals(service.getBaseCommand("showmap"), "showmap");
        assertEquals(service.getBaseCommand("savemap brasil.map"), "savemap");
        assertEquals(service.getBaseCommand("editneighbor -remove 1 2"), "editneighbor");
        assertEquals(service.getBaseCommand("deploy 1 2"), "deploy");
    }
}