package LLD.TwoZeroFourEight;

import LLD.TwoZeroFourEight.models.Game;

public class TwoZeroFourEight {
    public static void main(String[] args) {
        Game game = new Game(4);
        System.out.println(game.Play());
    }
}
