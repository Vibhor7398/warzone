/**
 * @author Vibhor Gulati, Apoorva Sharma, Saphal Ghimire, Inderjeet Singh Chauhan, Mohammad Zaid Shaikh
 * @version 3.0
 */
package TestSuites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * This class represents the global test suite for running all test suites together.
 * It includes the ServicesTestSuite, ControllerTestSuit and OrderTestSuite.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ServicesTestSuite.class, ControllerTestSuit.class, OrderTestSuite.class, StrategyTestSuite.class, PhasesTestSuite.class})
public class GlobalTestSuite {
}
