package Services;

import java.util.Scanner;

public class CommandService {
    public void getNextCommand(){
        System.out.println("Please enter your command");
        Scanner l_sc = new Scanner(System.in);
        String s_command = l_sc.nextLine();
        CommandValidationService l_cvs = new CommandValidationService();
        boolean l_isValid = l_cvs.validateCommand(s_command);
    }
}
