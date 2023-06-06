package LLD.TwoZeroFourEight.services;

import java.util.Scanner;

import LLD.TwoZeroFourEight.models.Board;

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
        while (isMergePossible && !isWon) {
            displayBoard();
            System.out.print("To contiue press: U/R/D/L; To Abort press: Q key -> ");
            ch = sc.next().toCharArray()[0];
            if (ch == 'U' || ch == 'R' || ch == 'D' || ch == 'L') {
                System.out.print("Shifting and merging to: ");
                System.out.println(ch);
                isShifted = board.shift(ch);
                
                isMerged = board.merge(ch);
                isWon = board.isWon(winningScore);
            
                // either shift or merge will create empty cells
                if (isShifted || isMerged) {
                    board.fillRandomEmptyCell();
                }

                // check if merge is possible if no shift or merge happened earlier
                isMergePossible = board.isMergeable();
            
                if (isWon) {
                    message = "Congratulations! You won :)";
                    displayBoard();
                }

                if (!isMergePossible) {
                    displayBoard();
                    message = "Cannot be merged anymore :(";
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
