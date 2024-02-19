import Services.CommandService;
import Services.CommandValidationService;

public class GameEngine {
    public static void main(String[] args) {
        start();
    }
    private static void start(){
        CommandValidationService.setD_hasGameStarted(false);
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