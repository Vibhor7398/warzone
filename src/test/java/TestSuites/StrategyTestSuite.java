package TestSuites;

import Strategy.TestCheaterStrategy;
import Strategy.TestRandomStrategy;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * This class represents a test suite for testing services.
 * It includes test cases for CommandValidationService and ReinforcementService.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({TestCheaterStrategy.class, TestRandomStrategy.class})
public class StrategyTestSuite {
}