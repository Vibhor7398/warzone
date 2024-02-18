
import Models.Player;
import Services.CommandService;
import Models.Maps;
import Services.CommandValidationService;

public class GameEngine {
    private static boolean d_HASGAMESTARTED = false;
    private static Maps map;
    private static Player[] players;
    private static Player currentPlayer;

    public static boolean D_HASGAMESTARTED() {
        return d_HASGAMESTARTED;
    }

    public static void setHasGameStarted(boolean p_HASGAMESTARTED) {
        GameEngine.d_HASGAMESTARTED = p_HASGAMESTARTED;
    }

    public static Maps getMapInstance() {
        return map;
    }

    public static void setMapInstance(Maps p_maps) {
        GameEngine.map = p_maps;
    }

    public static void main(String[] args) {
        start();
    }

    private static void start(){
        CommandValidationService.setD_hasGameStarted(d_HASGAMESTARTED);
        CommandService l_cs = new CommandService();
        String l_command =  l_cs.getNextCommand();
        String l_cmd = CommandValidationService.getBaseCommand(l_command);
        while(!(l_cmd.equals("loadmap") || l_cmd.equals("editmap"))){
            System.out.println("Invalid Command. Need to load map first.");
            l_command = l_cs.getNextCommand();
            l_cmd = CommandValidationService.getBaseCommand(l_command);
        }
        l_cs.executeCommand(l_command);
        l_cs.start();
    }
}