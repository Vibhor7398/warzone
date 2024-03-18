/**
 * @author Vibhor Gulati, Apoorva Sharma, Saphal Ghirmire, Inderjeet Singh Chauhan, Mohammad Zaid
 * @version 1.0
 */
package Services;

import Models.Command;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import static org.junit.Assert.*;

/**
 * Test class for CommandValidationService
 */
public class TestCommandValidator {
    /**
     * The {@link CommandValidator} service instance to be tested.
     */
    CommandValidator service;

    /**
     * Set up the context
     * @throws Exception if an error occurs
     */
    @Before
    public void setUp() throws Exception  {
        service = new CommandValidator();
    }

    /**
     * Tests the validation of a valid "loadmap" command, expecting a successful parsing without exceptions.
     */
    @Test
    public void testCommandValidationService_validateCommand_valid()  {
        Command[] l_expectedCommand= new Command[]{new Command("loadmap", "", new String[]{"brasil.map"})};
        Command[] l_returnedCommand=null;
        try{
            l_returnedCommand = service.validateCommand("loadmap brasil.map");
        }catch (InvalidCommandException e) {
            fail("An unexpected InvalidCommandException was thrown.");
        }
        Assert.assertEquals(Arrays.toString(l_expectedCommand), Arrays.toString(l_returnedCommand));
    }

    /**
     * Tests the validation of "loadmap" command with invalid argument scenarios, expecting specific exceptions
     * to be thrown for each case.
     */
    @Test
    public void testCommandValidationService_validateCommand_invalid(){
        assertThrows(InvalidCommandException.class, () -> {
            service.validateCommand("loadmap");
        });
        assertThrows(InvalidCommandException.class, () -> {
            service.validateCommand("loadmap brasil.map 1");
        });
        assertThrows(InvalidCommandException.class, () -> {
            service.validateCommand("");
        });
    }

    /**
     * Tests the validation of a valid "showmap" command, expecting a successful parsing without exceptions.
     */
    @Test
    public void testCommandValidationService_ShowMapCommand_valid() {
        Command[] l_expectedCommand= new Command[]{new Command("showmap", "", new String[]{})};
        Command[] l_returnedCommand=null;
        try{
            l_returnedCommand = service.validateCommand("showmap");
        }catch (InvalidCommandException e) {
            fail("An unexpected InvalidCommandException was thrown.");
        }
        Assert.assertEquals(Arrays.toString(l_expectedCommand), Arrays.toString(l_returnedCommand));
    }

    /**
     * Test to validate the showmap command with invalid number of arguments
     */
    @Test
    public void testCommandValidationService_ShowMapCommand_invalid() {
        assertThrows(InvalidCommandException.class, () -> {
            service.validateCommand("showmap 1");
        });
    }

    /**
     * Test to validate the savemap command with valid number of arguments
     */
    @Test
    public void testCommandValidationService_SaveMapCommand_valid() {
        Command l_expectedCommand= new Command("savemap", "", new String[]{"brasil.map"});
        Command[] l_returnedCommand=null;
        try {
            l_returnedCommand = service.validateCommand("savemap brasil.map");
        }catch (InvalidCommandException e) {
            fail("An unexpected InvalidCommandException was thrown.");
        }
        Assert.assertEquals(Arrays.toString(new Command[]{l_expectedCommand}), Arrays.toString(l_returnedCommand));
    }

    /**
     * Test to validate the savemap command with invalid number of arguments
     */
    @Test
    public void testCommandValidationService_SaveMapCommand_invalid() {
        assertThrows(InvalidCommandException.class, () -> {
            service.validateCommand("savemap");
        });
        assertThrows(InvalidCommandException.class, () -> {
            service.validateCommand("savemap asia.map 1");
        });
    }

    /**
     * Test to validate the edit continents command with valid number of arguments
     */
    @Test
    public void testCommandValidationService_EditContinentCommand_valid() {
        Command l_expectedCommand= new Command("editcontinent", "-add", new String[]{"Ozil", "2"});
        Command[] l_returnedCommand=null;
        try {
            l_returnedCommand = service.validateCommand("editcontinent -add Ozil 2");
        }catch (InvalidCommandException e) {
            fail("An unexpected InvalidCommandException was thrown.");
        }
        Assert.assertEquals(Arrays.toString(new Command[]{l_expectedCommand}), Arrays.toString(l_returnedCommand));
    }

    /**
     * Test to validate the editmap command with multiple number of arguments
     */
    @Test
    public void testCommandValidationService_EditContinentCommandMultiple_valid() {
        Command[] l_expectedCommand= new Command[]{new Command("editcontinent", "-add", new String[]{"Asia", "2",}),new Command("editcontinent", "-add", new String[]{"Brazil", "3",})};
        Command[] l_returnedCommand=null;
        try {
            l_returnedCommand = service.validateCommand("editcontinent -add Asia 2 Brazil 3");
        }catch (InvalidCommandException e) {
            fail("An unexpected InvalidCommandException was thrown.");
        }
        Assert.assertEquals(Arrays.toString(l_expectedCommand), Arrays.toString(l_returnedCommand));
    }

    /**
     * Test to validate the editmap command with invalid number of arguments
     */
    @Test
    public void testCommandValidationService_EditContinentCommandMultiple_invalid() {
        assertThrows(InvalidCommandException.class, () -> {
            service.validateCommand("editcontinent -add Asia 2 Brazil");
        });
        assertThrows(InvalidCommandException.class, () -> {
            service.validateCommand("editcontinent -add Asia 2 Brazil 3 4");
        });
        assertThrows(InvalidCommandException.class, () -> {
            service.validateCommand("editcontinent -add Asia 2 Brazil 3 4 5");
        });
        assertThrows(InvalidCommandException.class, () -> {
            service.validateCommand("editcontinent -add Asia 2 Brazil 3 4 5 6");
        });
    }



    /**
     * Test to validate the editcountry command with valid number of arguments
     */
    @Test
    public void testCommandValidationService_ValidateEditCountryCommand_valid() {
        Command[] l_expectedCommand= new Command[]{new Command("editcountry", "-add", new String[]{"Asia", "Asia",}),new Command("editcountry", "-add", new String[]{"Brazil", "Europe",})};
        Command[] l_returnedCommand=null;
        try {
            l_returnedCommand = service.validateCommand("editcountry -add Asia Asia Brazil Europe");
        }catch (InvalidCommandException e) {
            fail("An unexpected InvalidCommandException was thrown.");
        }
        Assert.assertEquals(Arrays.toString(l_expectedCommand), Arrays.toString(l_returnedCommand));
    }
    /**
     * Test to validate the editcountry command with invalid number of arguments
     */
    @Test
    public void testCommandValidationService_ValidateEditCountryCommand_invalid() {
        assertThrows(InvalidCommandException.class, () -> {
            service.validateCommand("editcountry -add");
        });
        assertThrows(InvalidCommandException.class, () -> {
            service.validateCommand("editcountry -add 1");
        });
        assertThrows(InvalidCommandException.class, () -> {
            service.validateCommand("editcountry -add 1 2 3");
        });
        assertThrows(InvalidCommandException.class, () -> {
            service.validateCommand("editcountry -remove");
        });
        assertThrows(InvalidCommandException.class, () -> {
            service.validateCommand("editcountry");
        });
        assertThrows(InvalidCommandException.class, () -> {
            service.validateCommand("editcountry 1 2");
        });
        assertThrows(InvalidCommandException.class, () -> {
            service.validateCommand("editcountry 1 2 3");
        });
    }

    /**
     * Test to validate the editneighbor command with valid number of arguments
     */
    @Test
    public void testCommandValidationService_ValidateEditNeighborCommand_valid() {
        Command[] l_expectedCommands = new Command[]{
                new Command("editneighbor", "-add", new String[]{"Brazil", "China"}),
                new Command("editneighbor", "-remove", new String[]{"India", "China"})
        };
        Command[] l_returnedCommands = null;
        try {
            l_returnedCommands = new Command[]{
                    service.validateCommand("editneighbor -add Brazil China")[0],
                    service.validateCommand("editneighbor -remove India China")[0]
            };
        } catch (InvalidCommandException e) {
            fail("An unexpected InvalidCommandException was thrown.");
        }
        Assert.assertEquals(Arrays.toString(l_expectedCommands), Arrays.toString(l_returnedCommands));
    }

    /**
     * Test to validate the editneighbor command with invalid number of arguments
     */
    @Test
    public void testCommandValidationService_ValidateEditNeighborCommand_invalid() {
        assertThrows(InvalidCommandException.class, () -> {
            service.validateCommand("editneighbor -add");
        });
        assertThrows(InvalidCommandException.class, () -> {
            service.validateCommand("editneighbor -add 1");
        });
        assertThrows(InvalidCommandException.class, () -> {
            service.validateCommand("editneighbor -add 1 2 3");
        });
        assertThrows(InvalidCommandException.class, () -> {
            service.validateCommand("editneighbor -remove");
        });
        assertThrows(InvalidCommandException.class, () -> {
            service.validateCommand("editneighbor -remove 1");
        });
        assertThrows(InvalidCommandException.class, () -> {
            service.validateCommand("editneighbor -remove 1 2 3");
        });
        assertThrows(InvalidCommandException.class, () -> {
            service.validateCommand("editneighbor");
        });
        assertThrows(InvalidCommandException.class, () -> {
            service.validateCommand("editneighbor 1 2");
        });
        assertThrows(InvalidCommandException.class, () -> {
            service.validateCommand("editneighbor 1 2 3");
        });
    }

    /**
     * Test to validate the validatemap command with valid number of arguments
     */
    @Test
    public void testCommandValidationService_ValidateValidateMapCommand_valid() {
        Command[] l_expectedCommand = new Command[]{new Command("validatemap", "", new String[]{})};
        Command[] l_returnedCommand = null;
        try {
            l_returnedCommand = service.validateCommand("validatemap");
        } catch (InvalidCommandException e) {
            fail("An unexpected InvalidCommandException was thrown.");
        }
        Assert.assertEquals(Arrays.toString(l_expectedCommand), Arrays.toString(l_returnedCommand));
    }

    /**
     * Test to validate the validatemap command with invalid number of arguments
     */
    @Test
    public void testCommandValidationService_ValidateValidateMapCommand_invalid() {
        assertThrows(InvalidCommandException.class, () -> {
            service.validateCommand("validatemap 1");
        });
    }

    /**
     * Test to validate the gameplayer command with valid number of arguments
     */
    @Test
    public void testCommandValidationService_ValidateGamePlayerCommand_valid() {
        Command[] l_expectedCommands = new Command[]{
                new Command("gameplayer", "-add", new String[]{"test"}),
                new Command("gameplayer", "-remove", new String[]{"test"})
        };
        Command[] l_returnedCommands = null;
        try {
            l_returnedCommands = new Command[]{
                    service.validateCommand("gameplayer -add test")[0],
                    service.validateCommand("gameplayer -remove test")[0]
            };
        } catch (InvalidCommandException e) {
            fail("An unexpected InvalidCommandException was thrown.");
        }
        Assert.assertEquals(Arrays.toString(l_expectedCommands), Arrays.toString(l_returnedCommands));
    }

    /**
     * Test to validate the gameplayer command with invalid number of arguments
     */
    @Test
    public void testCommandValidationService_ValidateGamePlayerCommand_invalid() {
        assertThrows(InvalidCommandException.class, () -> {
            service.validateCommand("gameplayer -add");
        });
        assertThrows(InvalidCommandException.class, () -> {
            service.validateCommand("gameplayer -add test 1");
        });
        assertThrows(InvalidCommandException.class, () -> {
            service.validateCommand("gameplayer -remove");
        });
        assertThrows(InvalidCommandException.class, () -> {
            service.validateCommand("gameplayer -remove test 1");
        });
        assertThrows(InvalidCommandException.class, () -> {
            service.validateCommand("gameplayer");
        });
        assertThrows(InvalidCommandException.class, () -> {
            service.validateCommand("gameplayer test 1");
        });
        assertThrows(InvalidCommandException.class, () -> {
            service.validateCommand("gameplayer test 1 2");
        });
    }

    /**
     * Test to validate the assigncountries command with valid number of arguments
     */
    @Test
    public void testCommandValidationService_ValidateAssignCountriesCommand_valid() {
        Command[] l_expectedCommand = new Command[]{new Command("assigncountries", "", new String[]{})};
        Command[] l_returnedCommand = null;
        try {
            l_returnedCommand = service.validateCommand("assigncountries");
        } catch (InvalidCommandException e) {
            fail("An unexpected InvalidCommandException was thrown.");
        }
        Assert.assertEquals(Arrays.toString(l_expectedCommand), Arrays.toString(l_returnedCommand));
    }

    /**
     * Test to validate the assigncountries command with invalid number of arguments
     */
    @Test
    public void testCommandValidationService_ValidateAssignCountriesCommand_invalid() {
        assertThrows(InvalidCommandException.class, () -> {
            service.validateCommand("assigncountries 1");
        });
    }

    /**
     * Test to validate the deploy command with valid number of arguments
     */
    @Test
    public void testDeployCommand_validArguments() {
        Command[] l_expectedCommand = new Command[]{new Command("deploy", "", new String[]{"India", "5"})};
        Command[] l_returnedCommand = null;
        try {
            l_returnedCommand = service.validateCommand("deploy India 5");
        } catch (InvalidCommandException e) {
            fail("An unexpected InvalidCommandException was thrown.");
        }
        assertEquals(Arrays.toString(l_expectedCommand), Arrays.toString(l_returnedCommand));
    }

    /**
     * Test to validate the deploy command with invalid number of arguments
     */
    @Test
    public void testDeployCommand_invalidArgumentCount() {
        assertThrows(InvalidCommandException.class, () -> {
            service.validateCommand("deploy India");
        });
        assertThrows(InvalidCommandException.class, () -> {
            service.validateCommand("deploy");
        });
    }

    /**
     * Test to validate the deploy command with invalid type of arguments
     */
    @Test
    public void testDeployCommand_invalidArgumentType() {
        assertThrows(InvalidCommandException.class, () -> {
            service.validateCommand("deploy India five");
        });
    }

    /**
     * Test to validate the deploy command with valid number of arguments
     */
    @Test
    public void testAdvanceCommand_validArguments() {
        Command[] l_expectedCommand = new Command[]{new Command("advance", "", new String[]{"India", "Nepal", "3"})};
        Command[] l_returnedCommand = null;
        try {
            l_returnedCommand = service.validateCommand("advance India Nepal 3");
        } catch (InvalidCommandException e) {
            fail("An unexpected InvalidCommandException was thrown.");
        }
        assertEquals(Arrays.toString(l_expectedCommand), Arrays.toString(l_returnedCommand));
    }

    /**
     * Test to validate the deploy command with invalid number of arguments
     */
    @Test
    public void testAdvanceCommand_invalidArgumentCount() {
        assertThrows(InvalidCommandException.class, () -> {
            service.validateCommand("advance India Nepal");
        });
        assertThrows(InvalidCommandException.class, () -> {
            service.validateCommand("advance India");
        });
    }

    /**
     * Test to validate the deploy command with invalid type of arguments
     */
    @Test
    public void testAdvanceCommand_invalidArgumentType() {
        assertThrows(InvalidCommandException.class, () -> {
            service.validateCommand("advance India Nepal three");
        });
    }

}