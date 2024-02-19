package Models;

import Services.CommandValidationService;
import java.util.*;

public class Player {
    private String d_name;
    private static int d_ReinforcementsCompleted;
    private int d_armiesCount;
    private ArrayList<Country> d_countriesOwned;
    private Queue<String> d_orderArgs;

    public Player(String p_name) {
        this.setName(p_name);
        d_armiesCount=0;
        d_countriesOwned = new ArrayList<>();
        d_orderArgs = new LinkedList<>();
    }
    public void setName(String p_name) {
        this.d_name = p_name;
    }
    public String getName(){
        return d_name;
    }
    public void setArmies(int p_armies){
        d_armiesCount = p_armies;
    }
    public int getArmies(){
        return d_armiesCount;
    }
    public ArrayList<Country> getCountriesOwned() {
        return d_countriesOwned;
    }
    public void addCountryToCountriesOwned(Country p_country) {
        d_countriesOwned.add(p_country);
    }
    public void setOrder(String p_orderArgs) {
        d_orderArgs.add(p_orderArgs);
    }
    public static int getD_reinforcementsCompleted() {
        return d_ReinforcementsCompleted;
    }
    public static void setD_reinforcementsCompleted(int p_reinforcementsCompleted) {
        Player.d_ReinforcementsCompleted = p_reinforcementsCompleted;
    }

    public void issue_order() {
        Scanner l_sc =  new Scanner(System.in);
        CommandValidationService l_cvs = new CommandValidationService();
        ArrayList<String> l_countriesOwned = new ArrayList<>();
        for (Country l_country : this.getCountriesOwned()) {
            l_countriesOwned.add(l_country.getName());
        }
        boolean l_valid = false;

        while (!l_valid) {
            l_valid=true;
            if (this.getArmies() != 0) {
                System.out.println(this.getName() + " please issue your orders: ");
                System.out.println("Reinforcement(s) available for deployment: " + this.getArmies());
                String l_order = l_sc.nextLine();
                String[] l_orderArgs = l_order.trim().split("\\s+");

                if (!l_cvs.validateDeployCommand(l_orderArgs)) {
                    System.out.print("Invalid Command! ");
                    l_valid=false;
                }
                else {
                    String l_countryName = l_orderArgs[1];
                    if (!l_countriesOwned.contains(l_countryName)) {
                        System.out.println("This country is not in the list ....... Please try with your country");
                        l_valid=false;
                    }else if (Integer.parseInt(l_orderArgs[2]) > this.getArmies()) {
                        System.out.println("Not having enough armies");
                        l_valid=false;
                    } else {
                        this.setArmies(this.getArmies() - Integer.parseInt(l_orderArgs[2]));
                        if (this.getArmies() == 0) {
                            d_ReinforcementsCompleted++;
                        }
                        setOrder(l_order);
                    }
                }
            }
        }
    }

    private void handleDeployOrder(ArrayList<String> p_command) {
        Order l_order = new Order();
        l_order.deployOrder(this, p_command.get(0), Integer.parseInt(p_command.get(1)));
    }

    private void handleInvalidCommand() {
        System.out.println("Invalid Command! --- Command is \"deploy countryID num\"");
    }

    public void next_order() {
        if (!d_orderArgs.isEmpty()) {
            String l_orderCommand = d_orderArgs.poll();
            if(d_orderArgs.isEmpty()){
                d_ReinforcementsCompleted++;
            }
            String[] l_orderCommandList = l_orderCommand.trim().split("\\s+");
            ArrayList<String> l_commandList = new ArrayList<>(Arrays.asList(l_orderCommandList));
            String d_orderType = l_orderCommandList[0];
            if (!l_commandList.isEmpty()) {
                l_commandList.removeFirst();
            }
            switch (d_orderType) {
                case "deploy":
                    handleDeployOrder(l_commandList);
                    break;
                default:
                    handleInvalidCommand();
                    break;
            }
        }
    }
}