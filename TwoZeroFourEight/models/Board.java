package LLD.TwoZeroFourEight.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Board {
    private int size;
    private int[][] board;
    private List<int[]> emptyCells;

    public Board(int size) {
        this.size = size;
        emptyCells = new ArrayList<>();
        board = new int[size][size];
        initilize();
    }

    private void initilize() {
        for (int[] row: board) {
            Arrays.fill(row, -1);
        }
        
        int row1 = new Random().nextInt(size);
        int col1 = new Random().nextInt(size);
        board[row1][col1] = 2;
        
        int row2 = new Random().nextInt(size);
        int col2 = new Random().nextInt(size);
        while (row2 == row1 && col2 == col1) {
            row2 = new Random().nextInt(size);
            col2 = new Random().nextInt(size);
        }
        board[row2][col2] = 2;
    }

    public boolean shift(char command) {
        int i, j;
        boolean isShiftPossible = false;
        switch (command) {
            case 'U':
                for (int col=0; col < size; col++) {
                    i = 0; 
                    j = 0;
                    while (i < size) {
                        if (board[i][col] == -1) {
                            i++;
                        } else {
                            if (i != j) {
                                board[j][col] = board[i][col];
                                isShiftPossible = true;
                            }
                            i++;
                            j++;
                        }
                    }
                    for (; j<size; j++) {
                        board[j][col] = -1;
                    }
                }
                break;
            case 'D':
                for (int col=0; col < size; col++) {
                    i = size - 1; 
                    j = size - 1;
                    while (i >= 0) {
                        if (board[i][col] == -1) {
                            i--;
                        } else {
                            if (i != j) {
                                board[j][col] = board[i][col];
                                isShiftPossible = true;
                            }
                            i--;
                            j--;
                        }
                    }
                    for (; j>=0; j--) {
                        board[j][col] = -1;
                    }
                }
                break;
            case 'L':
                for (int row=0; row<size; row++) {
                    i = 0; 
                    j = 0;
                    while (i < size) {
                        if (board[row][i] == -1) {
                            i++;
                        } else {
                            if (i != j) {
                                board[row][j] = board[row][i];
                                isShiftPossible = true;
                            }
                            i++;
                            j++;
                        }
                    }
                    for (; j<size; j++) {
                        board[row][j] = -1;
                    }
                }
                break;
            case 'R':
                for (int row=0; row<size; row++) {
                    i = size-1; 
                    j = size-1;
                    while (i >= 0) {
                        if (board[row][i] == -1) {
                            i--;
                        } else {
                            if (i != j) {
                                isShiftPossible = true;
                                board[row][j] = board[row][i];
                            }
                            i--;
                            j--;
                        }
                    }
                    for (; j>=0; j--) {
                        board[row][j] = -1;
                    }
                }
                break;
            default:
                break;
        }
        return isShiftPossible;
    }

    public boolean merge(char command) {
        int i, j;
        boolean isMerged = false;
        switch (command) {
            case 'U':
                for (int col=0; col < size; col++) {
                    i = 0; 
                    j = 0;
                    while (i < size) {
                        if (board[i][col] == -1) {
                            i++;
                        } else if (i!=size-1 && board[i][col] == board[i+1][col]) {
                            isMerged = true;
                            board[j][col] = board[i][col] * 2;
                            board[i+1][col] = -1;
                            i += 2;
                            j++;
                        } else {
                            if (i != j) {
                                board[j][col] = board[i][col];
                                isMerged = true;
                            }
                            i++;
                            j++;
                        }
                    }
                    for (; j<size; j++) {
                        board[j][col] = -1;
                    }
                }
                break;
            case 'D':
                for (int col=0; col < size; col++) {
                    i = size - 1; 
                    j = size - 1;
                    while (i >= 0) {
                        if (board[i][col] == -1) {
                            i--;
                        } else if (i!= 0 && board[i][col] == board[i-1][col]) {
                            isMerged = true;
                            board[j][col] = board[i][col] * 2;
                            board[i-1][col] = -1;
                            i -= 2;
                            j--;
                        } else {
                            if (i != j) {
                                board[j][col] = board[i][col];
                                isMerged = true;
                            }
                            i--;
                            j--;
                        }
                    }
                    for (; j>=0; j--) {
                        board[j][col] = -1;
                    }
                }
                break;
            case 'L':
                for (int row=0; row<size; row++) {
                    i = 0; 
                    j = 0;
                    while (i < size) {
                        if (board[row][i] == -1) {
                            i++;
                        } else if (i!= size-1 && board[row][i] == board[row][i+1]) {
                            isMerged = true;
                            board[row][j] = board[row][i] * 2;
                            board[row][i+1] = -1;
                            i += 2;
                            j++;
                        } else {
                            if (i != j) {
                                board[row][j] = board[row][i];
                                isMerged = true;
                            }
                            i++;
                            j++;
                        }
                    }
                    for (; j<size; j++) {
                        board[row][j] = -1;
                    }
                }
                break;
            case 'R':
                for (int row=0; row<size; row++) {
                    i = size-1; 
                    j = size-1;
                    while (i >= 0) {
                        if (board[row][i] == -1) {
                            i--;
                        } else if (i!=0 && board[row][i] == board[row][i-1]) {
                            isMerged = true;
                            board[row][j] = board[row][i] * 2;
                            board[row][i-1] = -1;
                            i -= 2;
                            j--;
                        } else {
                            if (i != j) {
                                board[row][j] = board[row][i];
                                isMerged = true;
                            }
                            
                            i--;
                            j--;
                        }
                    }
                    for (; j>=0; j--) {
                        board[row][j] = -1;
                    }
                }
                break;
            default:
                break;
        }
        return isMerged;
    }

    public boolean isWon(int winningScore) {
        emptyCells = new ArrayList<>();
        for (int row=0; row<size; row++) {
            for (int col = 0; col<size; col++) {
                if (board[row][col] == winningScore) {
                    return true;
                }
                else if (board[row][col] == -1) {
                    emptyCells.add(new int[]{row, col});
                }
            }
        }
        return false;
    }

    public int getEmptyCellCnt() {
        return emptyCells.size();
    }

    public void fillRandomEmptyCell() {
        int randomEmptyCell = new Random().nextInt(this.getEmptyCellCnt());
        if (this.getEmptyCellCnt() == 0)
            return;
        int row = emptyCells.get(randomEmptyCell)[0];
        int col = emptyCells.get(randomEmptyCell)[1];
        board[row][col] = (new Random().nextInt(2) + 1) * 2;
    }

    public void displayBoard() {
        for (int row=0; row<this.size; row++) {
            for (int col=0; col<this.size; col++) {
                if (board[row][col] == -1)  {
                    System.out.print("_\t");
                }
                else {
                    System.out.print(board[row][col] + "\t");
                }
            }
            System.out.println();
        }
    }

    public boolean isMergeable() {
        for (int i=0; i<size-1; i++) {
            for (int j=0; j<size-1; j++) {
                if (board[i][j] == board[i+1][j] || board[i][j] == board[i][j+1])
                    return true;
            }
        }
        return getEmptyCellCnt() != 0;
    }
    
}
