/**
 * @author Vibhor Gulati, Apoorva Sharma, Saphal Ghirmire, Inderjeet Singh Chauhan, Mohammad Zaid
 * @version 1.0
 */
package Services;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for CommandValidationService
 */
public class TestCommandValidationService {
    CommandValidationService service;

    /**
     * Set up the context
     * @throws Exception if an error occurs
     */
    @Before
    public void setUp() throws Exception {
        service = new CommandValidationService();
    }

    /**
     * Test to validate the loadmap command
     */
    @Test
    public void testCommandValidationService_validateCommand_valid() {
        assertTrue(service.validateCommand("loadmap brasil.map"));
    }

    /**
     * Test to validate the loadmap command with non existent map
     */
    @Test
    public void testCommandValidationService_validateCommand_nonExistentMap(){
        assertFalse(service.validateCommand("loadmap asia.map"));
    }

    /**
     * Test to validate the loadmap command with invalid number of arguments
     */
    @Test
    public void testCommandValidationService_validateCommand_invalid(){
        assertFalse(service.validateCommand("loadmap"));
        assertFalse(service.validateCommand("loadmap brasil.map 1"));
        assertFalse(service.validateCommand(""));
    }

    /**
     * Test to validate the showmap command with valid number of arguments
     */
    @Test
    public void testCommandValidationService_ShowMapCommand_valid() {
        assertTrue(service.validateCommand("showmap"));
    }

    /**
     * Test to validate the showmap command with invalid number of arguments
     */
    @Test
    public void testCommandValidationService_ShowMapCommand_invalid() {
        assertTrue(service.validateCommand("showmap"));
    }

    /**
     * Test to validate the savemap command with valid number of arguments
     */
    @Test
    public void testCommandValidationService_SaveMapCommand_valid() {
        assertTrue(service.validateCommand("savemap brasil.map"));
        assertTrue(service.validateCommand("savemap asia.map"));
    }

    /**
     * Test to validate the savemap command with invalid number of arguments
     */
    @Test
    public void testCommandValidationService_SaveMapCommand_invalid() {
        assertFalse(service.validateCommand("savemap"));
        assertFalse(service.validateCommand("savemap asia.map 1"));
    }

    /**
     * Test to validate the editmap command with valid number of arguments
     */
    @Test
    public void testCommandValidationService_EditContinentCommand_valid() {
        assertTrue(service.validateCommand("editcontinent -add 1 2"));
        assertTrue(service.validateCommand("editcontinent -remove 1"));
    }

    /**
     * Test to validate the editmap command with invalid number of arguments
     */
    @Test
    public void testCommandValidationService_EditContinentCommand_invalid() {
        assertFalse(service.validateCommand("editcontinent -add"));
        assertFalse(service.validateCommand("editcontinent -add 1"));
        assertFalse(service.validateCommand("editcontinent -add 1 2 3"));
        assertFalse(service.validateCommand("editcontinent -remove 1 2"));
        assertFalse(service.validateCommand("editcontinent -remove"));
        assertFalse(service.validateCommand("editcontinent"));
        assertFalse(service.validateCommand("editcontinent 1 2"));
        assertFalse(service.validateCommand("editcontinent 1 2 3"));
    }

    /**
     * Test to validate the editcountry command with valid number of arguments
     */
    @Test
    public void testCommandValidationService_ValidateEditCountryCommand_valid() {
        assertTrue(service.validateCommand("editcountry -add 1 2"));
        assertTrue(service.validateCommand("editcountry -remove 1"));
    }

    /**
     * Test to validate the editcountry command with invalid number of arguments
     */
    @Test
    public void testCommandValidationService_ValidateEditCountryCommand_invalid() {
        assertFalse(service.validateCommand("editcountry -add"));
        assertFalse(service.validateCommand("editcountry -add 1"));
        assertFalse(service.validateCommand("editcountry -add 1 2 3"));
        assertFalse(service.validateCommand("editcountry -remove"));
        assertFalse(service.validateCommand("editcountry"));
        assertFalse(service.validateCommand("editcountry 1 2"));
        assertFalse(service.validateCommand("editcountry 1 2 3"));
    }

    /**
     * Test to validate the editneighbor command with valid number of arguments
     */
    @Test
    public void testCommandValidationService_ValidateEditNeighborCommand_valid() {
        assertTrue(service.validateCommand("editneighbor -add 1 2"));
        assertTrue(service.validateCommand("editneighbor -remove 1 2"));
    }

    /**
     * Test to validate the editneighbor command with invalid number of arguments
     */
    @Test
    public void testCommandValidationService_ValidateEditNeighborCommand_invalid() {
        assertFalse(service.validateCommand("editneighbor -add"));
        assertFalse(service.validateCommand("editneighbor -add 1"));
        assertFalse(service.validateCommand("editneighbor -add 1 2 3"));
        assertFalse(service.validateCommand("editneighbor -remove"));
        assertFalse(service.validateCommand("editneighbor -remove 1"));
        assertFalse(service.validateCommand("editneighbor -add 1 2 3"));
        assertFalse(service.validateCommand("editneighbor"));
        assertFalse(service.validateCommand("editneighbor 1 2"));
        assertFalse(service.validateCommand("editneighbor 1 2 3"));
    }

    /**
     * Test to validate the validatemap command with valid number of arguments
     */
    @Test
    public void testCommandValidationService_ValidateValidateMapCommand_valid() {
        assertTrue(service.validateCommand("validatemap"));
    }

    /**
     * Test to validate the validatemap command with invalid number of arguments
     */
    @Test
    public void testCommandValidationService_ValidateValidateMapCommand_invalid() {
        assertFalse(service.validateCommand("validatemap 1"));
    }

    /**
     * Test to validate the gameplayer command with valid number of arguments
     */
    @Test
    public void testCommandValidationService_ValidateGamePlayerCommand_valid() {
        assertTrue(service.validateCommand("gameplayer -add test"));
        assertTrue(service.validateCommand("gameplayer -remove test"));
    }

    /**
     * Test to validate the gameplayer command with invalid number of arguments
     */
    @Test
    public void testCommandValidationService_ValidateGamePlayerCommand_invalid() {
        assertFalse(service.validateCommand("gameplayer -add"));
        assertFalse(service.validateCommand("gameplayer -add test 1"));
        assertFalse(service.validateCommand("gameplayer -remove"));
        assertFalse(service.validateCommand("gameplayer -remove test 1"));
        assertFalse(service.validateCommand("gameplayer"));
        assertFalse(service.validateCommand("gameplayer test 1"));
        assertFalse(service.validateCommand("gameplayer test 1 2"));
    }

    /**
     * Test to validate the assigncountries command with valid number of arguments
     */
    @Test
    public void testCommandValidationService_ValidateAssignCountriesCommand_valid() {
        assertTrue(service.validateCommand("assigncountries"));
    }

    /**
     * Test to validate the assigncountries command with invalid number of arguments
     */
    @Test
    public void testCommandValidationService_ValidateAssignCountriesCommand_invalid() {
        assertFalse(service.validateCommand("assigncountries 1"));
    }

    /**
     * Test to validate the deploy command with valid number of arguments
     */
    @Test
    public void testValidateDeployCommand_valid() {
        assertTrue(service.validateDeployCommand("deploy india 1".trim().split("\\ ")));
    }

    /**
     * Test to validate the deploy command with invalid number of arguments
     */
    @Test
    public void testValidateDeployCommand_invalid() {
        assertFalse(service.validateDeployCommand("deploy".trim().split("\\ ")));
        assertFalse(service.validateDeployCommand("deploy 1".trim().split("\\ ")));
    }

    /**
     * Test to validate the getBaseCommand with valid number of arguments
     */
    @Test
    public void testValidateGetBaseCommand() {
        assertEquals(CommandValidationService.getBaseCommand("loadmap brasil.map"), "loadmap");
        assertEquals(CommandValidationService.getBaseCommand("showmap"), "showmap");
        assertEquals(CommandValidationService.getBaseCommand("savemap brasil.map"), "savemap");
        assertEquals(CommandValidationService.getBaseCommand("editneighbor -remove 1 2"), "editneighbor");
        assertEquals(CommandValidationService.getBaseCommand("deploy 1 2"), "deploy");
    }
}