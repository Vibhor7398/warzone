package Models;

public enum Strategy {
    Aggressive(0),
    Benevolent(1),
    Cheater(2),
    Random(3),
    Human(4);

    public int value;

    Strategy(int number) {
        this.value = number;
    }
}
