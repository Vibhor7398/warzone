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
        assertFalse(service.validateCommand("loadmap brasil.map 1"));
        assertFalse(service.validateCommand(""));
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
    public void testValidateEditContinentCommand() {
        assertTrue(service.validateCommand("editcontinent -add 1 2"));
        assertFalse(service.validateCommand("editcontinent -add"));
        assertFalse(service.validateCommand("editcontinent -add 1 test"));
        assertFalse(service.validateCommand("editcontinent -add test 1"));
        assertFalse(service.validateCommand("editcontinent -add 1"));
        assertFalse(service.validateCommand("editcontinent -add 1 2 3"));

        assertTrue(service.validateCommand("editcontinent -remove 1"));
        assertFalse(service.validateCommand("editcontinent -remove test"));
        assertFalse(service.validateCommand("editcontinent -remove 1 2"));
        assertFalse(service.validateCommand("editcontinent -remove"));

        assertFalse(service.validateCommand("editcontinent"));
        assertFalse(service.validateCommand("editcontinent 1 2"));
        assertFalse(service.validateCommand("editcontinent 1 2 3"));;
    }

    @Test
    public void testValidateEditCountryCommand() {
        assertTrue(service.validateCommand("editcountry -add 1 2"));
        assertFalse(service.validateCommand("editcountry -add"));
        assertFalse(service.validateCommand("editcountry -add 1 test"));
        assertFalse(service.validateCommand("editcountry -add test 1"));
        assertFalse(service.validateCommand("editcountry -add 1"));
        assertFalse(service.validateCommand("editcountry -add 1 2 3"));

        assertTrue(service.validateCommand("editcountry -remove 1"));
        assertFalse(service.validateCommand("editcountry -remove test"));
        assertFalse(service.validateCommand("editcountry -remove 1 2"));
        assertFalse(service.validateCommand("editcountry -remove"));

        assertFalse(service.validateCommand("editcountry"));
        assertFalse(service.validateCommand("editcountry 1 2"));
        assertFalse(service.validateCommand("editcountry 1 2 3"));;
    }

    @Test
    public void testValidateEditNeighborCommand() {
        assertTrue(service.validateCommand("editneighbor -add 1 2"));
        assertFalse(service.validateCommand("editneighbor -add"));
        assertFalse(service.validateCommand("editneighbor -add 1 test"));
        assertFalse(service.validateCommand("editneighbor -add test 1"));
        assertFalse(service.validateCommand("editneighbor -add 1"));
        assertFalse(service.validateCommand("editneighbor -add 1 2 3"));

        assertTrue(service.validateCommand("editneighbor -remove 1 2"));
        assertFalse(service.validateCommand("editneighbor -remove"));
        assertFalse(service.validateCommand("editneighbor -remove 1 test"));
        assertFalse(service.validateCommand("editneighbor -remove test 1"));
        assertFalse(service.validateCommand("editneighbor -remove 1"));
        assertFalse(service.validateCommand("editneighbor -add 1 2 3"));

        assertFalse(service.validateCommand("editneighbor"));
        assertFalse(service.validateCommand("editneighbor 1 2"));
        assertFalse(service.validateCommand("editneighbor 1 2 3"));;
    }

    @Test
    public void testValidateValidateMapCommand() {
        assertTrue(service.validateCommand("validatemap"));
        assertFalse(service.validateCommand("validatemap 1"));
    }

    @Test
    public void testValidateGamePlayerCommand() {
        assertTrue(service.validateCommand("gameplayer -add test"));
        assertFalse(service.validateCommand("gameplayer -add"));
        assertFalse(service.validateCommand("gameplayer -add test 1"));

        assertTrue(service.validateCommand("gameplayer -remove test"));
        assertFalse(service.validateCommand("gameplayer -remove"));
        assertFalse(service.validateCommand("gameplayer -remove test 1"));

        assertFalse(service.validateCommand("gameplayer"));
        assertFalse(service.validateCommand("gameplayer test 1"));
        assertFalse(service.validateCommand("gameplayer test 1 2"));
    }

    @Test
    public void testValidateAssignCountriesCommand() {
        assertTrue(service.validateCommand("assigncountries"));
        assertFalse(service.validateCommand("assigncountries 1"));
    }

    @Test
    public void testValidateDeployCommand() {
        assertTrue(service.validateCommand("deploy 1 1"));
        assertFalse(service.validateCommand("deploy"));
        assertFalse(service.validateCommand("deploy test"));
        assertFalse(service.validateCommand("deploy 1"));
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