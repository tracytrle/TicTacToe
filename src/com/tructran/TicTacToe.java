package com.tructran;

import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        char[][] board = {{' ', ' ', ' ' }, {' ', ' ', ' '}, {' ', ' ', ' '}};
        printBoard(board);

        // player turn

        while (true) {
            if(isGameOver(board, 'O')) {
                System.out.println("Computer wins!!!");
                break;
            }
            playTurn(board, scan);
            printBoard(board);

            if (isGameOver(board, 'X')) {
                System.out.println("Player wins!!!");
                break;
            }
            computerTurn(board);
            printBoard(board);
        }
//        scan.close();
    }

    // computerTurn: input is a random number from 1 to 9
    private static void computerTurn(char[][] board) {
        Random rd = new Random();
        int computerPlay;
        while(true) {
            computerPlay = rd.nextInt(9) + 1;
            if (isSpaceAvailable(board, computerPlay)) {
                break;
            }
        }
        System.out.println("Computer selected:");
        placeMove(board, computerPlay, 'O');
    }

    // check the input (space) is available
    private static boolean isSpaceAvailable(char[][] board, int position) {
        int div = (position - 1)/3;
        int rem = (position - 1) % 3;
        return board[div][rem] == ' ';
    }

    // Player turn: input is a number from 1 to 9 which represented a space from 0 to 8
    private static void playTurn(char[][] board, Scanner scan) {
        int position;
        while (true) {
            System.out.println("Select a number from 1-9:");
            String userInput = scan.nextLine();
            position = Integer.parseInt(userInput);
            if (isSpaceAvailable(board, position)) {
                break;
            } else {
                System.out.println("Input is not valid");
            }
        }
        placeMove(board, position, 'X');


    }


    // check if player or computer win
    private static boolean isGameOver(char[][] board, char symbol) {
        return checkRow(board, symbol) || checkCol(board, symbol) || checkDigonal(board, symbol);

    }

    private static boolean checkRow(char[][] board, char symbol) {
       return (board[0][0] == symbol && board[0][1] == symbol  && board[0][2] == symbol) ||
        (board[1][0] == symbol && board[1][1] == symbol  && board[1][2] == symbol) ||
                (board[2][0] == symbol && board[2][1] == symbol  && board[2][2] == symbol);
    }

    private static boolean checkCol(char[][] board, char symbol) {
        return (board[0][0] == symbol && board[1][0] == symbol  && board[2][0] == symbol) ||
                (board[0][1] == symbol && board[1][1] == symbol  && board[2][1] == symbol) ||
                (board[0][2] == symbol && board[1][2] == symbol  && board[2][2] == symbol);
    }

    private static boolean checkDigonal(char[][] board, char symbol) {

        if ((board[0][0] == symbol && board[1][1]  == symbol && board[2][2] == symbol) ||
                (board[2][0] == symbol && board[1][1] == symbol && board[0][2] == symbol)) {
            return true;
        }
        return false;
    }

    // fill in the blank space
    private static void placeMove(char[][] board, Integer position, char symbol) {
        System.out.println(position);

        int div = (position - 1)/3;
        int rem = (position - 1) % 3;
        board[div][rem] = symbol;
    }


    private static void printBoard(char[][] board) {
        System.out.println(board[0][0] + "|" + board[0][1] + "|" + board[0][2]);
        System.out.println( "-+-+-");
        System.out.println(board[1][0] + "|" + board[1][1] + "|" + board[1][2]);
        System.out.println( "-+-+-");
        System.out.println(board[2][0] + "|" + board[2][1] + "|" + board[2][2]);
    }
}
