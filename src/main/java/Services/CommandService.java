package Services;

import Constants.AppConstants;
import Controller.GameEngineController;
import Controller.MapsController;
import Models.Maps;
import Views.MapView;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class CommandService {
    CommandValidationService d_cvs = new CommandValidationService();
    MapsController d_maps = new MapsController();
    GameEngineController d_gc;
    public CommandService(){
        d_gc = new GameEngineController();
    }
    public String getNextCommand(){
        System.out.println("Please enter your command");
        Scanner l_sc = new Scanner(System.in);
        String l_command = l_sc.nextLine();
        if(!d_cvs.validateCommand(l_command)){
            System.out.print("Invalid Command! ");
            return getNextCommand();
        }
        return l_command;
    }

    public void start(){
//        d_gc = new GameEngineController();
        while (true){
            String l_command = getNextCommand();
            executeCommand(l_command);
        }
    }

    public void executeCommand(String p_cmd){
        d_gc.executeCommand(p_cmd);
    }
}
