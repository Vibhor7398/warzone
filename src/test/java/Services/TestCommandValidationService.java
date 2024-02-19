package Services;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestCommandValidationService {
    CommandValidationService d_service;
    @Before
    public void setUp() throws Exception {
        d_service = new CommandValidationService();
    }

    @Test
    public void testCommandValidationService_validateCommand_valid() {
        assertTrue(d_service.validateCommand("loadmap brasil.map"));
    }

    @Test
    public void testCommandValidationService_validateCommand_nonExistentMap(){
        assertFalse(d_service.validateCommand("loadmap asia.map"));
    }

    @Test
    public void testCommandValidationService_validateCommand_incorrectCommand(){
        assertFalse(d_service.validateCommand("loadmap"));
    }

    @Test
    public void testCommandValidationService_validateCommand_incorrectCommand2(){
        assertFalse(d_service.validateCommand("loadmap brasil.map 1"));
    }

    @Test
    public void testCommandValidationService_validateCommand_incorrectCommand3(){
        assertFalse(d_service.validateCommand(""));
    }

    @Test
    public void testValidateShowMapCommand() {
        assertTrue(d_service.validateCommand("showmap"));
        assertFalse(d_service.validateCommand("showmap 1"));
    }

    @Test
    public void testValidateSaveMapCommand() {
        assertTrue(d_service.validateCommand("savemap brasil.map"));
        assertTrue(d_service.validateCommand("savemap asia.map"));
        assertFalse(d_service.validateCommand("savemap"));
        assertFalse(d_service.validateCommand("savemap asia.map 1"));
    }

    @Test
    public void testValidateEditContinentCommand() {
        assertTrue(d_service.validateCommand("editcontinent -add 1 2"));
        assertFalse(d_service.validateCommand("editcontinent -add"));
        assertFalse(d_service.validateCommand("editcontinent -add 1"));
        assertFalse(d_service.validateCommand("editcontinent -add 1 2 3"));

        assertTrue(d_service.validateCommand("editcontinent -remove 1"));
        assertFalse(d_service.validateCommand("editcontinent -remove 1 2"));
        assertFalse(d_service.validateCommand("editcontinent -remove"));

        assertFalse(d_service.validateCommand("editcontinent"));
        assertFalse(d_service.validateCommand("editcontinent 1 2"));
        assertFalse(d_service.validateCommand("editcontinent 1 2 3"));;
    }

    @Test
    public void testValidateEditCountryCommand() {
        assertTrue(d_service.validateCommand("editcountry -add 1 2"));
        assertFalse(d_service.validateCommand("editcountry -add"));
        assertFalse(d_service.validateCommand("editcountry -add 1"));
        assertFalse(d_service.validateCommand("editcountry -add 1 2 3"));

        assertTrue(d_service.validateCommand("editcountry -remove 1"));
        assertFalse(d_service.validateCommand("editcountry -remove"));

        assertFalse(d_service.validateCommand("editcountry"));
        assertFalse(d_service.validateCommand("editcountry 1 2"));
        assertFalse(d_service.validateCommand("editcountry 1 2 3"));;
    }

    @Test
    public void testValidateEditNeighborCommand() {
        assertTrue(d_service.validateCommand("editneighbor -add 1 2"));
        assertFalse(d_service.validateCommand("editneighbor -add"));
        assertFalse(d_service.validateCommand("editneighbor -add 1"));
        assertFalse(d_service.validateCommand("editneighbor -add 1 2 3"));

        assertTrue(d_service.validateCommand("editneighbor -remove 1 2"));
        assertFalse(d_service.validateCommand("editneighbor -remove"));
        assertFalse(d_service.validateCommand("editneighbor -remove 1"));
        assertFalse(d_service.validateCommand("editneighbor -add 1 2 3"));

        assertFalse(d_service.validateCommand("editneighbor"));
        assertFalse(d_service.validateCommand("editneighbor 1 2"));
        assertFalse(d_service.validateCommand("editneighbor 1 2 3"));;
    }

    @Test
    public void testValidateValidateMapCommand() {
        assertTrue(d_service.validateCommand("validatemap"));
        assertFalse(d_service.validateCommand("validatemap 1"));
    }

    @Test
    public void testValidateGamePlayerCommand() {
        assertTrue(d_service.validateCommand("gameplayer -add test"));
        assertFalse(d_service.validateCommand("gameplayer -add"));
        assertFalse(d_service.validateCommand("gameplayer -add test 1"));

        assertTrue(d_service.validateCommand("gameplayer -remove test"));
        assertFalse(d_service.validateCommand("gameplayer -remove"));
        assertFalse(d_service.validateCommand("gameplayer -remove test 1"));

        assertFalse(d_service.validateCommand("gameplayer"));
        assertFalse(d_service.validateCommand("gameplayer test 1"));
        assertFalse(d_service.validateCommand("gameplayer test 1 2"));
    }

    @Test
    public void testValidateAssignCountriesCommand() {
        assertTrue(d_service.validateCommand("assigncountries"));
        assertFalse(d_service.validateCommand("assigncountries 1"));
    }

    @Test
    public void testValidateDeployCommand() {
        assertFalse(d_service.validateCommand("deploy 1 1"));
        assertFalse(d_service.validateCommand("deploy"));
        assertFalse(d_service.validateCommand("deploy 1"));
    }

    @Test
    public void testValidateGetBaseCommand() {
        assertEquals(CommandValidationService.getBaseCommand("loadmap brasil.map"), "loadmap");
        assertEquals(CommandValidationService.getBaseCommand("showmap"), "showmap");
        assertEquals(CommandValidationService.getBaseCommand("savemap brasil.map"), "savemap");
        assertEquals(CommandValidationService.getBaseCommand("editneighbor -remove 1 2"), "editneighbor");
        assertEquals(CommandValidationService.getBaseCommand("deploy 1 2"), "deploy");
    }
}