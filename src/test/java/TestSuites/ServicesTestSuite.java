/**
 * @author Vibhor Gulati, Apoorva Sharma, Saphal Ghimire, Inderjeet Singh Chauhan, Mohammad Zaid Shaikh
 * @version 2.0
 */
package TestSuites;

import Services.TestCommandValidator;
import Services.TestReinforcement;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * This class represents a test suite for testing services.
 * It includes test cases for CommandValidationService and ReinforcementService.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({TestCommandValidator.class,TestReinforcement.class})
public class ServicesTestSuite {
}
