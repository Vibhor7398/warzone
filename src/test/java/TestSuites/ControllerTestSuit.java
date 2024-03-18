/**
 * @author Vibhor Gulati, Apoorva Sharma, Saphal Ghimire, Inderjeet Singh Chauhan, Mohammad Zaid Shaikh
 * @version 2.0
 */
package TestSuites;

import Controller.TestMapsController;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * This class represents a test suite for testing controller.
 * It includes test cases for MapsController.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({TestMapsController.class})
public class ControllerTestSuit {
}
