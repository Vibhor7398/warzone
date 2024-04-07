package Strategy;

import Controller.GameEngineController;
import Models.Country;
import Models.Player;
import Orders.Advance;
import Orders.Deploy;
import Orders.Airlift;
import Orders.Order;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestBenevolentStrategy {
    private BenevolentStrategy d_benevolentStrategy;
    private Player d_testPlayer;
    private GameEngineController d_gc;

}
