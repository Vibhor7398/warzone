package Orders;

public class Bomb implements Order{
    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    public void execute() {

    }

    @Override
    public void print() {

    }
}
