/**
 * @author Vibhor Gulati, Apoorva Sharma, Saphal Ghimire, Inderjeet Singh Chauhan, Mohammad Zaid Shaikh
 * @version 3.0
 */
package TestSuites;

import Controller.TestGameEngineController;
import Controller.TestMapsController;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * This class represents a test suite for testing controller.
 * It includes test cases for MapsController.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({TestMapsController.class, TestGameEngineController.class})
public class ControllerTestSuit {
}
