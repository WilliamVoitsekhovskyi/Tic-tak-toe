package tic.tak.toe;

import java.awt.*;
import java.util.Scanner;

class GameLogic {

    private static boolean turnX = true;
    private static boolean turn0 = false;
    static boolean isInGame = true;
    static boolean isFieldChecked = false;
    static boolean flag = true;
    static void startNewGame(Button button){
        if(button == GameField.buttonNewGame){
            for(int i=0; i<9; i++){
                GameField.squares[i].setEnabled(true);
                GameField.squares[i].setFont(GameField.font);
            }
            if(!isInGame){
                GameField.cleanField();
            }
            isInGame = true;
        }
    }

    static void makeTurn(Button button) {
        if(Main.isOnePlayer)
            onePlayerMakeTurn(button);
        else
            twoPlayersMakeTurns(button);
    }
    static void onePlayerMakeTurn(Button button) {
        for (int i = 0; i < 9; i++) {
            if (button == GameField.squares[i] && turnX) {
                GameField.squares[i].setLabel("X");
                GameField.disableButton(GameField.squares[i]);
                turnX = false;
                turn0 = true;
                checkWinner();
                test();
                break;
            }

        }
    }
    public static void test(){
            for (int i = 0; i < 9; i++) {
            if (turn0) {
                ComputerLogic.computerMove(i);
//                GameField.squares[i].setLabel("0");
//                GameField.disableButton(GameField.squares[i]);
                turn0 = false;
                turnX = true;
                checkWinner();
                    break;
                }
            }
    }


        /*flag = true;
        while (flag) {
            System.out.println(GameField.squares[i].getLabel());
            System.out.println("i= "+i );
            if (GameField.squares[i].getLabel().equals("")) {
                GameField.squares[i].setLabel("0");
                flag = false;
            }
            else {
                System.out.println("AGSSGA");
                flag = true;
                i++;
            }
        }

         */
    static void twoPlayersMakeTurns(Button button) {
        for (int i = 0; i < 9; i++) {
            if (button == GameField.squares[i] && turnX) {
                GameField.squares[i].setLabel("X");
                GameField.disableButton(GameField.squares[i]);
                turnX = false;
                turn0 = true;
                checkWinner();
                break;
            }
            if (button == GameField.squares[i] && turn0) {
                GameField.squares[i].setLabel("0");
                GameField.disableButton(GameField.squares[i]);
                turn0 = false;
                turnX = true;
                checkWinner();
                break;
            }
        }
    }


    static boolean isTie(){
        return GameField.emptySquaresLeft == 0;
    }

    private static void checkWinner(){
        GameField.emptySquaresLeft--;
        if(isTie()){
            GameField.WinnerLabel.setText("Tie!");
            GameField.buttonNewGame.setEnabled(true);
            isInGame = false;
            showWhiteField();
        }
        checkHorizontal();
        checkVertical();
        checkDiagonal();
        GameField.setScore();
    }

    private static void showWhiteField(){
        for(int i=0;i<9;i++){
            GameField.squares[i].setBackground(Color.WHITE);
        }
    }

    private static void checkVertical() {
        for (int i = 0; i < 3; i++) {
            if (!GameField.squares[i].getLabel().equals("") &&
                    GameField.squares[i].getLabel().equals(GameField.squares[(i + 3)].getLabel()) &&
                    GameField.squares[i].getLabel().equals(GameField.squares[(i + 6)].getLabel())) {

                GameField.theWinner = GameField.squares[i].getLabel();
                showWinner(i, i + 3, i + 6);
                writeScore(GameField.theWinner);

            }
        }
    }

    private static void checkDiagonal() {
        // diagonal left
        if (!GameField.squares[0].getLabel().equals("") &&
                GameField.squares[0].getLabel().equals(GameField.squares[4].getLabel()) &&
                GameField.squares[0].getLabel().equals(GameField.squares[8].getLabel())) {
            GameField.theWinner = GameField.squares[0].getLabel();
            showWinner(0, 4, 8);
            writeScore(GameField.theWinner);
        }
        // diagonal right
        if (!GameField.squares[2].getLabel().equals("") &&
                GameField.squares[2].getLabel().equals(GameField.squares[4].getLabel()) &&
                GameField.squares[2].getLabel().equals(GameField.squares[6].getLabel())) {
            GameField.theWinner = GameField.squares[2].getLabel();
            showWinner(2, 4, 6);
            writeScore(GameField.theWinner);
        }
    }
    private static void showWinner(int win1, int win2, int win3){
        showWhiteField();

        if(!isTie())
            highlightWinner(win1, win2, win3);

        GameField.buttonNewGame.setEnabled(true);
        isInGame = false;

        for(int i=0;i<9;i++)
            GameField.squares[i].setEnabled(false);

        turnX = true;
        turn0 = false;

    }
    private static void writeScore(String winner){
        if("X".equals(winner)){
            GameField.scoreX +=1;
        }
        if("0".equals(winner)){
            GameField.score0 +=1;
        }
    }
    private static void highlightWinner(int win1, int win2, int win3){
        GameField.squares[win1].setBackground(java.awt.Color.RED);
        GameField.squares[win2].setBackground(java.awt.Color.RED);
        GameField.squares[win3].setBackground(java.awt.Color.RED);
    }

    private static void checkHorizontal() {
        for (int i = 0; i <= 6; i += 3) {
            if (!GameField.squares[i].getLabel().equals("") &&
                    GameField.squares[i].getLabel().equals(GameField.squares[i + 1].getLabel()) &&
                    GameField.squares[i].getLabel().equals(GameField.squares[i + 2].getLabel())) {
                GameField.theWinner = GameField.squares[i].getLabel();
                showWinner(i, i + 1, i + 2);
                writeScore(GameField.theWinner);
            }
        }
    }
}
