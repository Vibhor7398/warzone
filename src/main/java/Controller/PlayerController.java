package Controller;

import Models.Country;
import Models.Player;

public class PlayerController {
    private Player d_player;

    public PlayerController(String p_name) {
        this.d_player = new Player(p_name);
    }

    public void assignCountry(Country country) {
        this.d_player.addCountry(country);
    }
}