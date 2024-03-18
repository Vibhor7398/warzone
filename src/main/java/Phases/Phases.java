package Phases;

import GameEngine.GameEngine;
import Models.Command;

public abstract class Phases {
    public static GameEngine d_ge;

    public static void setD_ge(GameEngine d_ge) {
        Phases.d_ge = d_ge;
    }

    public void showMap(Command p_command) {
        d_ge.getD_gc().executeShowMap();
    }

    abstract public void editMap(Command p_command);

    abstract public void saveMap(Command p_command);

    abstract public void validateMap(Command p_command);

    abstract public void editContinent(Command p_command);

    abstract public void editCountry(Command p_command);

    abstract public void editNeighbor(Command p_command);

    abstract public void loadMap(Command p_command);

    abstract public void assignPlayers(Command p_command);

    abstract public void assignCountries(Command p_command);

    abstract public void deploy(Command p_command);

    abstract public void advance(Command p_command);

    abstract public void bomb(Command p_command);

    abstract public void blockade(Command p_command);

    abstract public void airlift(Command p_command);

    abstract public void negotiate(Command p_command);

    abstract public void endGame(Command p_command);

    abstract public void endTurn(Command p_command);

    abstract public void next();

    public void execute(Command[] p_command) {
        for (Command l_command : p_command) {
            switch (l_command.getD_cmd()) {
                case "showmap":
                    d_ge.getD_phase().showMap(l_command);
                    break;

                case "editmap":
                    d_ge.getD_phase().editMap(l_command);
                    break;

                case "savemap":
                    d_ge.getD_phase().saveMap(l_command);
                    break;

                case "validatemap":
                    d_ge.getD_phase().validateMap(l_command);
                    break;

                case "editcontinent":
                    d_ge.getD_phase().editContinent(l_command);
                    break;

                case "editcountry":
                    d_ge.getD_phase().editCountry(l_command);
                    break;

                case "editneighbor":
                    d_ge.getD_phase().editNeighbor(l_command);
                    break;

                case "loadmap":
                    d_ge.getD_phase().loadMap(l_command);
                    break;

                case "assigncountries":
                    d_ge.getD_phase().assignCountries(l_command);
                    break;

                case "gameplayer":
                    d_ge.getD_phase().assignPlayers(l_command);
                    break;

                case "deploy":
                    d_ge.getD_phase().deploy(l_command);
                    break;

                case "advance":
                    d_ge.getD_phase().advance(l_command);
                    break;

                case "bomb":
                    d_ge.getD_phase().bomb(l_command);
                    break;

                case "blockade":
                    d_ge.getD_phase().blockade(l_command);
                    break;

                case "airlift":
                    d_ge.getD_phase().airlift(l_command);
                    break;

                case "negotiate":
                    d_ge.getD_phase().negotiate(l_command);
                    break;

                case "endturn":
                    d_ge.getD_phase().endTurn(l_command);
                    break;

                case "endgame":
                    d_ge.getD_phase().endGame(l_command);
                    break;

                case "next":
                    d_ge.getD_phase().next();
                    break;

                default:
                    printInvalidMessage();
                    break;
            }
        }
        d_ge.getD_gc().nextUserInput();
    }

    public void printInvalidMessage() {
        System.out.println("Invalid Command in the state " + this.getClass().getSimpleName());
        //d_ge.nextUserInput();
    }

}
