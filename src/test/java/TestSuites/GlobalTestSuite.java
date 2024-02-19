package TestSuites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * This class represents the global test suite for running all test suites together.
 * It includes the ServicesTestSuite.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ServicesTestSuite.class})
public class GlobalTestSuite {
}
