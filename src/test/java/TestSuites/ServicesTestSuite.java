package TestSuites;
import Services.TestCommandValidationService;
import Services.TestMathService;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({TestCommandValidationService.class, TestMathService.class})
public class ServicesTestSuite {

}
