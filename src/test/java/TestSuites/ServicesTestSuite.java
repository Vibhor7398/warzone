/**
 * @author Vibhor Gulati, Apoorva Sharma, Saphal Ghirmire, Inderjeet Singh Chauhan, Mohammad Zaid
 * @version 1.0
 */
package TestSuites;
import Services.TestCommandValidationService;
import Services.TestMathService;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * This class represents a test suite for testing services.
 * It includes test cases for CommandValidationService and MathService.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({TestCommandValidationService.class, TestMathService.class})
public class ServicesTestSuite {

}
