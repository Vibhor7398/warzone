/**
 * @author Vibhor Gulati, Apoorva Sharma, Saphal Ghimire, Inderjeet Singh Chauhan, Mohammad Zaid Shaikh
 * @version 3.0
 */
package TestSuites;

import Strategy.TestAggressiveStrategy;
import Strategy.TestBenevolentStrategy;
import Strategy.TestCheaterStrategy;
import Strategy.TestRandomStrategy;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * This class represents a test suite for testing services.
 * It includes test cases for CommandValidationService and ReinforcementService.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({TestCheaterStrategy.class, TestRandomStrategy.class, TestAggressiveStrategy.class, TestBenevolentStrategy.class})
public class StrategyTestSuite {
}