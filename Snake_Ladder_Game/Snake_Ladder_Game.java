package LLD.Snake_Ladder_Game;

import java.util.ArrayList;
import java.util.List;

import LLD.Snake_Ladder_Game.services.Game;


public class Snake_Ladder_Game {
    public static void main(String[] args) {
        List<String> players = new ArrayList<>();
        players.add("Alice");
        players.add("Bob");
        players.add("John");

        List<List<Integer>> snakePositions = new ArrayList<>();
        List<Integer> snakePosition = new ArrayList<>();
        snakePosition.add(5);
        snakePosition.add(33);
        snakePositions.add(snakePosition);
        snakePosition = new ArrayList<>();
        snakePosition.add(16);
        snakePosition.add(63);
        snakePositions.add(snakePosition);
        snakePosition = new ArrayList<>();
        snakePosition.add(34);
        snakePosition.add(54);
        snakePositions.add(snakePosition);
        snakePosition = new ArrayList<>();
        snakePosition.add(61);
        snakePosition.add(97);
        snakePositions.add(snakePosition);
        snakePosition = new ArrayList<>();
        snakePosition.add(74);
        snakePosition.add(93);
        snakePositions.add(snakePosition);

        List<List<Integer>> ladderPositions = new ArrayList<>();
        List<Integer> ladderPosition = new ArrayList<>();
        ladderPosition.add(2);
        ladderPosition.add(38);
        ladderPositions.add(ladderPosition);
        ladderPosition = new ArrayList<>();
        ladderPosition.add(8);
        ladderPosition.add(31);
        ladderPositions.add(ladderPosition);
        ladderPosition = new ArrayList<>();
        ladderPosition.add(21);
        ladderPosition.add(42);
        ladderPositions.add(ladderPosition);
        ladderPosition = new ArrayList<>();
        ladderPosition.add(46);
        ladderPosition.add(84);
        ladderPositions.add(ladderPosition);
        ladderPosition = new ArrayList<>();
        ladderPosition.add(51);
        ladderPosition.add(67);
        ladderPositions.add(ladderPosition);
        ladderPosition = new ArrayList<>();
        ladderPosition.add(71);
        ladderPosition.add(91);
        ladderPositions.add(ladderPosition);
        ladderPosition = new ArrayList<>();
        ladderPosition.add(80);
        ladderPosition.add(99);
        ladderPositions.add(ladderPosition);

        Game game = new Game(100, players, snakePositions, ladderPositions);  
        game.displayBoard();
        String response = game.playGame();
        System.out.println(response);
    }

}
