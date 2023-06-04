import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;


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

class Game {
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

    String playGame() {
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
                if (board.ladders.containsKey(possiblePosition))
                    actualPosition = board.ladders.get(possiblePosition);
                else if (board.snakes.containsKey(possiblePosition))
                    actualPosition = board.snakes.get(possiblePosition);

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

    void displayBoard() {
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

class Board {

    private int size;
    Map<Integer, Integer> snakes;
    Map<Integer, Integer> ladders;
    Map<Integer, List<Player>> players;
    
    public Board(int boardSize) {
        size = boardSize;
    }

    public int getSize() {
        return size;
    }

    public void setLadderPositions(List<List<Integer>> ladderPositions) {
        ladders = new HashMap<>();
        for (List<Integer> ladderPosition: ladderPositions) {
            ladders.put(ladderPosition.get(0), ladderPosition.get(1));
        }
    }

    public void setSnakePositions(List<List<Integer>> snakePositions) {
        snakes = new HashMap<>();
        for (List<Integer> snakePosition: snakePositions) {
            snakes.put(snakePosition.get(1), snakePosition.get(0));
        }
    }

    public void setPlayerPositions(List<Player> players) {
        this.players = new HashMap<>();
        this.players.put(1, new ArrayList<>(players));
    }

    public void updatePlayerPosition(Player player, int oldPosition, int newPosition) {
        if (players.get(oldPosition) != null)
            players.get(oldPosition).remove(player);
        if (players.get(newPosition) == null)
            players.put(newPosition, new ArrayList<>());
        players.get(newPosition).add(player);
    }

}

class Dice {

    public int roll() {
        return new Random().nextInt(6) + 1;
    }
}


class Player {
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