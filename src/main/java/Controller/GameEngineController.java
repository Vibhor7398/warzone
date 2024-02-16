package Controller;

import Services.CommandService;

public class GameEngineController {

   public GameEngineController(){
        CommandService commandService = new CommandService();
        commandService.start();
    }
}