package LLD.Snake_Ladder_Game.models;

public class Player {
    private String name;
    private int position;

    public Player (String name) {
        this.name = name;
        position = 1;
    }

    public String getName() {
        return name;
    }

    public int rollDice(Dice dice) {
        return dice.roll();
    }

    public void move(int newPosition) {
        position = newPosition;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return getName();
    }
}
