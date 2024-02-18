package TestSuites;
import Services.CommandValidationServiceTest;
import Services.MathServiceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({CommandValidationServiceTest.class, MathServiceTest.class})
public class ServicesTestSuite {

}
