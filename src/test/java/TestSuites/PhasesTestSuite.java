package TestSuites;

import Orders.TestAdvance;
import Orders.TestAirlift;
import Orders.TestBomb;
import Orders.TestDeploy;
import Phases.GamePlay.MainPlay.TestMainPlay;
import Phases.GamePlay.Players.TestPlayers;
import Phases.MapEditor.TestMapEditor;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * This class represents a test suite for testing phases.
 * It includes test cases for Phases and the allowed functions.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({TestMapEditor.class, TestMainPlay.class, TestPlayers.class})
public class PhasesTestSuite {
}
