package Controller;

import Services.CommandService;

public class GameEngineController {

   public GameEngineController(){
        CommandService Command = new CommandService();
        Command.start();
    }
}