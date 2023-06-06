package LLD.TwoZeroFourEight;

import LLD.TwoZeroFourEight.services.Game;

public class TwoZeroFourEight {
    public static void main(String[] args) {
        Game game = new Game(4, 2048);
        System.out.println(game.Play());
    }
}
