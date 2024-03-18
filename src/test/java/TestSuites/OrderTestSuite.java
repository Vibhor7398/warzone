/**
 * @author Vibhor Gulati, Apoorva Sharma, Saphal Ghirmire, Inderjeet Singh Chauhan, Mohammad Zaid
 * @version 2.0
 */
package TestSuites;

import Orders.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * This class represents a test suite for testing orders.
 * It includes test cases for Orders.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({TestAdvance.class, TestAirlift.class, TestBomb.class, TestDeploy.class, TestDiplomacy.class, TestBlockade.class})
public class OrderTestSuite {

}
