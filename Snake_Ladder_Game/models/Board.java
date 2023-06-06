package LLD.Snake_Ladder_Game.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Board {
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

    public Map<Integer, Integer> getLadders() {
        return ladders;
    }

    public Map<Integer, Integer> getSnakes() {
        return snakes;
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

