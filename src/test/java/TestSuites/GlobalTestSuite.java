/**
 * @author Vibhor Gulati, Apoorva Sharma, Saphal Ghirmire, Inderjeet Singh Chauhan, Mohammad Zaid
 * @version 1.0
 */
package TestSuites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * This class represents the global test suite for running all test suites together.
 * It includes the ServicesTestSuite and ControllerTestSuit.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ServicesTestSuite.class, ControllerTestSuit.class, OrderTestSuite.class})
public class GlobalTestSuite {
}
