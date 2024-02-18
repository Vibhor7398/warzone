package Models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Queue;

public class Player {
    private int d_id;
    private String d_name;
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

    public void issue_order() {
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

    public Order nextOrder() {
        if (d_orderList.isEmpty()) {
            return null;
        } else {
            return d_orderList.remove(0);
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
