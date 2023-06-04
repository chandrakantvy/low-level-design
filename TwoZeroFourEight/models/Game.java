package LLD.TwoZeroFourEight.models;

import java.util.Scanner;

public class Game {
    private Board board;
    private int winningScore;
    
    public Game() {

    }

    public Game(int size) {
        board = new Board(size);
        winningScore = 2048;
    }

    public Game(int size, int winningScore) {
        board = new Board(size);
        this.winningScore = winningScore;
    }

    public void displayBoard() {
        board.displayBoard();
    }

    public String Play() {
        char ch;
        Scanner sc = new Scanner(System.in);
        boolean isWon = false;
        boolean isMergePossible = true;
        boolean isShifted = false;
        boolean isMerged = false;
        String message = "";
        while (true) {
            displayBoard();
            System.out.print("To contiue press: U/R/D/L, To Abort pres: any Other key: ");
            ch = sc.next().toCharArray()[0];
            if (ch == 'U' || ch == 'R' || ch == 'D' || ch == 'L') {
                System.out.print("Shifting and merging to: ");
                System.out.println(ch);
                isShifted = board.shift(ch);
                
                isMerged = board.merge(ch);
                isWon = board.isWon(winningScore);
                if (isWon) {
                    message = "Congratulations! You won :)";
                }
                if (isShifted || isMerged) {
                    board.fillRandomEmptyCell();
                } else {
                    isMergePossible = board.isMergeable();
                }
                if (!isMergePossible && !isWon) {
                    displayBoard();
                    message = "Game Over";
                    break;
                }
                if (isWon) {
                    displayBoard();
                    break;
                }
            } else {
                message = "Game Aborted!";
                break;
            }
        }
        sc.close();
        return message;
    }
}
