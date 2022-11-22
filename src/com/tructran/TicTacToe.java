package com.tructran;

import java.util.Random;
import java.util.Scanner;



public class TicTacToe {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);


        Globals.size = 20;
        if (Globals.size > 5)  {
            Globals.symbol_count = 5;
        } else if (Globals.size > 3) {
            Globals.symbol_count = 4;
        } else {
            Globals.symbol_count = 3;
        }

        char[][] board = new char[Globals.size][Globals.size];
        for (int i = 0; i < Globals.size; i++) {
            for (int j = 0; j < Globals.size; j++) {
                board[i][j] = '-';

            }
        }

        printBoard(board);

        // player turn
        Globals.position = 1;

        while (true) {
            if(isGameOver(board, 'O')) {
                System.out.println("Computer wins!!!");
                break;
            }
            System.out.println("\nPlayer turn\n");
            playTurn(board, scan);
            printBoard(board);

            if (isGameOver(board, 'X')) {
                System.out.println("Player wins!!!");
                break;
            }
            System.out.println("\nComputer turn\n");
            computerTurn(board);
            printBoard(board);
        }
//        scan.close();
    }

    // computerTurn: input is a random number
    private static void computerTurn(char[][] board) {

        Random rd = new Random();
        int total = Globals.size * Globals.size;
        while(true) {
            Globals.position = rd.nextInt(total) + 1;
            System.out.println("Computer selected: " + Globals.position);

            if (isSpaceAvailable(board)) {
                break;
            }
        }
        placeMove(board, 'O');
    }

    // check the input (space) is available
    private static boolean isSpaceAvailable(char[][] board) {
        int div = (Globals.position - 1)/(Globals.size);
        int rem = (Globals.position - 1) % (Globals.size);
        return board[div][rem] == '-';
    }


    private static void playTurn(char[][] board, Scanner scan) {
        int total = Globals.size * Globals.size;
        while (true) {
            System.out.println("Select a number from 1 to " + total +":");
            String userInput = scan.nextLine();
            Globals.position = Integer.parseInt(userInput);
            if (isSpaceAvailable(board)) {
                break;
            } else {
                System.out.println("Input is not valid");
            }
        }
        placeMove(board, 'X');


    }


    // check if player or computer win
    private static boolean isGameOver(char[][] board, char symbol) {
        return checkRow(board, symbol) || checkCol(board, symbol) || checkDigonal1(board,
                symbol) || checkDigonal2(board, symbol) ;

    }

    private static boolean checkRow(char[][] board, char symbol) {
        int div = (Globals.position - 1)/(Globals.size);   // row
        int rem = (Globals.position - 1) % (Globals.size);  // col
        int count = 1;
        int cur_div = div;

        for (int i = cur_div - 1; i >= 0; i--) {
            if (board[i][rem] == symbol) {
                count++;
                if (count == Globals.symbol_count) {
                    return true;
                }
            } else {
                break;
            }
        }
        for (int i = cur_div + 1; i <= Globals.size - 1; i++) {
            if (board[i][rem] == symbol) {
                count++;
                if (count == Globals.symbol_count) {

                    return true;
                }
            } else {
                break;
            }
        }
        return false;
    }

    private static boolean checkCol(char[][] board, char symbol) {
        int div = (Globals.position - 1)/ (Globals.size);   // row
        int rem = (Globals.position - 1) % (Globals.size);  // col
        int count = 1;
        int cur_rem = rem;
        for (int i = cur_rem-1; i >=0; i--) {
            if (board[div][i] == symbol) {
                count++;
                if (count == Globals.symbol_count) {
                    return true;
                }
            } else {
                break;
            }
        }
        for (int i = cur_rem+ 1; i <= Globals.size - 1; i++) {
            if (board[div][i] == symbol) {
                count++;
                if (count == Globals.symbol_count) {
                    return true;
                }
            } else {
                break;
            }
        }
        return false;
    }
    private static boolean checkDigonal1(char[][] board, char symbol) {
        int div = (Globals.position - 1)/ (Globals.size);   // row
        int rem = (Globals.position - 1) % (Globals.size);  // col
        int count = 1;
        int cur_div = div;
        int cur_rem = rem;
        while (cur_div > 0 && cur_rem > 0) {
            if (board[--cur_div][--cur_rem] == symbol) {
                count++;

                if (count == Globals.symbol_count) {

                    return true;
                }

            } else {

                break;
            }
        }
        while (div < Globals.size - 1 && rem < Globals.size - 1) {
            if (board[++div][++rem] == symbol) {
                count++;
                if (count == Globals.symbol_count) {

                    return true;
                }

            } else {
                break;
            }
        }

        return false;
    }

    private static boolean checkDigonal2(char[][] board, char symbol) {
        int div = (Globals.position - 1)/(Globals.size);   // row
        int rem = (Globals.position - 1) % (Globals.size);  // col
        int count = 1;
        int cur_div = div;
        int cur_rem = rem;
        while (cur_div > 0 && cur_rem < Globals.size - 1) {
            if (board[--cur_div][++cur_rem] == symbol) {
                count++;
                if (count == Globals.symbol_count) {
                    return true;
                }

            } else {
                break;
            }
        }
        while (div < Globals.size - 1 && rem > 0) {
            if (board[++div][--rem] == symbol) {
                count++;
                if (count == Globals.symbol_count) {
                    return true;
                }

            } else {

                break;
            }
        }
        return false;
    }

    // fill in the blank space
    private static void placeMove(char[][] board, char symbol) {
        System.out.println(Globals.position);

        int div = (Globals.position - 1)/(Globals.size);
        int rem = (Globals.position - 1) % (Globals.size);
        board[div][rem] = symbol;
    }


    private static void printBoard(char[][] board) {
        for (int i = 0; i < Globals.size; i++) {
            for (int j = 0; j < Globals.size; j++) {
                if (j != Globals.size -1) {
                    System.out.print(board[i][j] + "|");
                } else {
                    System.out.println(board[i][j]);
                }
            }
        }
    }


}
