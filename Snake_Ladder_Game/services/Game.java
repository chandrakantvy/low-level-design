package LLD.Snake_Ladder_Game.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import LLD.Snake_Ladder_Game.models.Board;
import LLD.Snake_Ladder_Game.models.Dice;
import LLD.Snake_Ladder_Game.models.Player;


public class Game {
    private Board board;
    private List<Player> players;
    private List<List<Integer>> snakePositions;
    private List<List<Integer>> ladderPositions;
    private Dice dice;
    private List<Player> winnerList;

    public Game(int boardSize, List<String> players, List<List<Integer>> snakePositions, List<List<Integer>> ladderPositions) {
        board = new Board(boardSize);
        this.players = new ArrayList<>();
        for (String player: players) {
            this.players.add(new Player(player));
        }
        this.snakePositions = snakePositions;
        this.ladderPositions = ladderPositions;
        winnerList = new ArrayList<>();
        dice = new Dice();
        initializeBoard();

    }

    private void initializeBoard() {
    
        board.setPlayerPositions(players);
        board.setSnakePositions(snakePositions);
        board.setLadderPositions(ladderPositions);
    }

    public String playGame() {
        String message = "";
        boolean stopGame = false;
        Scanner sc = new Scanner(System.in);
        while(winnerList.size() < players.size()) {
            for (Player currentPlayer: players) {
                if (winnerList.contains(currentPlayer)) 
                    continue;
                System.out.print("Press, Y to continue; D to dispay player position and continue; Any other key to exit: ");
                String ch = sc.next().trim();
                ch = ch.toLowerCase();
                if (ch.equals("d")) {
                    displayPlayersPosition();
                } else if (!ch.equals("y")) {
                    message = "Game Aborted";
                    stopGame = true;
                    break;
                }

                System.out.println("Player: " + currentPlayer.getName());
                System.out.println("\trolling the dice...");
                int next = currentPlayer.rollDice(dice);
                System.out.println("\tGot " + next + " on the dice");
                int possiblePosition = next + currentPlayer.getPosition();
                if (possiblePosition  > board.getSize())
                    continue;
                int actualPosition = possiblePosition;
                if (board.getLadders().containsKey(possiblePosition))
                    actualPosition = board.getLadders().get(possiblePosition);
                else if (board.getSnakes().containsKey(possiblePosition))
                    actualPosition = board.getSnakes().get(possiblePosition);

                System.out.println("\tmoved from " + currentPlayer.getPosition() + " to " + actualPosition);
                board.updatePlayerPosition(currentPlayer, currentPlayer.getPosition(), actualPosition);
                currentPlayer.move(actualPosition);
                if (actualPosition == board.getSize()) {
                    winnerList.add(currentPlayer);
                }
            }
            if (stopGame)
                break;
        }
        sc.close();
        if (!stopGame) {
            message = "Winners are(winning order from left to right) : " + winnerList.toString();
        }
        return message;
    }

    public void displayBoard() {
        System.out.println("Board Size = " + board.getSize());
        displayPlayersPosition();

        System.out.println("---Snake(~~>) Positions---");
        for (List<Integer> snakePosition: snakePositions) {
            System.out.println(snakePosition.get(0) + " ~~> " + snakePosition.get(1));
        }

        System.out.println("---Ladder(#) Positions---");
        for (List<Integer> ladderPosition: ladderPositions) {
            System.out.println(ladderPosition.get(0) + " ## " + ladderPosition.get(1));
        }
    }

    private void displayPlayersPosition() {
        System.out.println("---Player Positions---"); 
        for (Player player: players) {
            System.out.println("Player: " + player.getName() + " and its Position = " + player.getPosition());
        }
    }

}

