package Models;

import Services.CommandValidationService;

import java.util.*;

public class Player {
    private int d_id;
    private String d_name;
    private static int d_reinforcementsCompleted;
    private HashMap<Integer, Country> d_countryList;
    private ArrayList<Order> d_orderList;
    private int d_armiesCount;
    private ArrayList<Country> d_countriesOwned;
    private Order d_currentOrder;
    private Queue<String> d_orderArgs;

    public Player(String p_name) {
        this.setName(p_name);
        d_countryList = new HashMap<Integer, Country>();
        d_armiesCount=0;
        d_orderList = new ArrayList<Order>();
        d_countriesOwned = new ArrayList<Country>();
        d_orderArgs = new LinkedList<>();
    }

    public void setId(int p_id){
        d_id = p_id;
    }

    public int getId(){
        return d_id;
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

    public void setCountriesOwned(ArrayList<Country> p_countriesOwned){
        d_countriesOwned = p_countriesOwned;
    }

    public ArrayList<Country> getCountriesOwned() {
        return d_countriesOwned;
    }

    public void addCountryToCountriesOwned(Country p_country) {
        d_countriesOwned.add(p_country);
    }

    public ArrayList<Order> getOrderList(){
        return d_orderList;
    }

    public void setOrderList(ArrayList<Order> p_orderList){
        d_orderList = p_orderList;
    }

    public Order getCurrentOrder() {
        return d_currentOrder;
    }

    public void setCurrentOrder(Order p_currentOrder) {
        d_currentOrder = p_currentOrder;
    }

    public void setOrder(String p_orderArgs) {
        d_orderArgs.add(p_orderArgs);
    }

    public static int getD_reinforcementsCompleted() {
        return d_reinforcementsCompleted;
    }
    public static void setD_reinforcementsCompleted(int p_reinforcementsCompleted) {
        Player.d_reinforcementsCompleted = p_reinforcementsCompleted;
    }


    public void commandValidate(){

    }

    public void issue_order() {
        Scanner l_sc =  new Scanner(System.in);
        CommandValidationService d_cvs = new CommandValidationService();
        ArrayList<String> countriesOwned = new ArrayList<>();
        for (Country country : this.getCountriesOwned()) {
            countriesOwned.add(country.getName());
        }
        boolean valid = false;

        while (!valid) {
            valid=true;
            if (this.getArmies() != 0) {
                System.out.println(this.getName() + " please issue your orders: ");
                System.out.println("Reinforcement(s) Available for deployment: " + this.getArmies());
                String l_order = l_sc.nextLine();

                if (!d_cvs.validateCommand(l_order)) {
                    System.out.print("Invalid Command! ");
                    valid=false;
                } else {
                    String[] l_orderArgs = l_order.trim().split("\\s+");
                    String l_countryName = l_orderArgs[1];
                    if (!countriesOwned.contains(l_countryName)) {
                        System.out.println("This country is not in the list ....... Please try with your country");
                        valid=false;
                    }else if (Integer.parseInt(l_orderArgs[2]) > this.getArmies()) {
                        System.out.println("Not having enough Armies");
                        valid=false;
                    } else {
                        this.setArmies(this.getArmies() - Integer.parseInt(l_orderArgs[2]));
                        if (this.getArmies() == 0) {
                            d_reinforcementsCompleted++;
                        }
                        setOrder(l_order);
                    }
                }
            }
        }
        System.out.println(d_reinforcementsCompleted);
        System.out.println(this.getArmies()+"   "+this.getName());
    }

    private void handleDeployOrder(ArrayList<String> p_command) {
        System.out.println("Deploying the orders: ");
        Order l_order = new Order();
        l_order.deployOrder(this, p_command.get(0), Integer.parseInt(p_command.get(1)));
        //d_currentOrder = new Deploy(this, command.get(0), Integer.parseInt(command.get(1)));
        //d_orderList.add(d_currentOrder);
    }

    private void handleInvalidCommand() {
        System.out.println("Invalid Command! --- Command is \"deploy countryID num\"");
    }

    public void next_order() {
        while (!d_orderArgs.isEmpty()) {
            String orderCommand_ = d_orderArgs.poll();
            String[] orderCommand = orderCommand_.trim().split("\\s+");
            ArrayList<String> commandList = new ArrayList<>(Arrays.asList(orderCommand));

            String d_orderType = orderCommand[0];

            if (!commandList.isEmpty()) {
                commandList.remove(0);
            }

            switch (d_orderType) {
                case "deploy":
                    handleDeployOrder(commandList);
                    break;
                default:
                    handleInvalidCommand();
                    break;
            }
        }
    }

















































    public void addCountry(Country p_country) {
        this.d_countryList.put(p_country.getId(), p_country);
    }
    public Country getCountry(int p_countryId) {
        return d_countryList.get(p_countryId);
    }
    
    public ArrayList<Country> getCountries(){
        return new ArrayList<Country>(this.d_countryList.values());
    }

    public boolean containsCountry(int p_countryId){
        return d_countryList.containsKey(p_countryId);
    }
















}
