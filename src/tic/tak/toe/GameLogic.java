package tic.tak.toe;

import java.awt.*;

public class GameLogic {

    public static void startNewGame(Button button){
        if(button == GameField.buttonNewGame){
            for(int i=0; i<9; i++){
                GameField.squares[i].setEnabled(true);
                GameField.squares[i].setFont(GameField.font);
            }
            if(!GameField.isInGame){
                GameField.cleanField();
            }
            GameField.isInGame = true;
        }
    }


    public static void makeTurn(Button button) {
        for (int i = 0; i < 9; i++) {
            if (button == GameField.squares[i] && GameField.turnX) {
                GameField.squares[i].setLabel("X");
                GameField.squares[i].setEnabled(false);
                GameField.turnX = false;
                GameField.turn0 = true;
                checkWinner();
                break;
            }

            if (button == GameField.squares[i] && GameField.turn0) {
                GameField.squares[i].setLabel("0");
                GameField.squares[i].setEnabled(false);
                GameField.turn0 = false;
                GameField.turnX = true;
                checkWinner();
                break;
            }
        }
    }
    public static void checkWinner(){
        GameField.emptySquaresLeft--;
        if(GameField.emptySquaresLeft == 0){
            GameField.WinnerLabel.setText("Tie!");
            GameField.isTie = true;
            showWinner(0,0,0);
        }
        checkHorizontal();
        checkVertical();
        checkDiagonal();
        GameField.setScore();
    }

    public static void checkVertical() {
        for (int i = 0; i < 3; i++) {
            if (!GameField.squares[i].getLabel().equals("") &&
                    GameField.squares[i].getLabel().equals(GameField.squares[(i + 3)].getLabel()) &&
                    GameField.squares[i].getLabel().equals(GameField.squares[(i + 6)].getLabel())) {
                GameField.theWinner = GameField.squares[i].getLabel();
                GameField.WinnerLabel.setText(GameField.theWinner + " WinnerLabel!");
                GameField.isTie = false;
                showWinner(i, i + 3, i + 6);
                writeScore(GameField.theWinner);
            }
        }
    }

    public static void checkDiagonal() {
        // diagonal left
        if (!GameField.squares[0].getLabel().equals("") &&
                GameField.squares[0].getLabel().equals(GameField.squares[4].getLabel()) &&
                GameField.squares[0].getLabel().equals(GameField.squares[8].getLabel())) {
            GameField.theWinner = GameField.squares[0].getLabel();
            GameField.WinnerLabel.setText(GameField.theWinner + " WinnerLabel!");
            GameField.isTie = false;
            showWinner(0, 4, 8);
            writeScore(GameField.theWinner);
        }
        // diagonal right
        if (!GameField.squares[2].getLabel().equals("") &&
                GameField.squares[2].getLabel().equals(GameField.squares[4].getLabel()) &&
                GameField.squares[2].getLabel().equals(GameField.squares[6].getLabel())) {
            GameField.theWinner = GameField.squares[2].getLabel();
            GameField.WinnerLabel.setText(GameField.theWinner + " WinnerLabel!");
            GameField.isTie = false;
            showWinner(2, 4, 6);
            writeScore(GameField.theWinner);
        }
    }

    public static void checkHorizontal() {
        for (int i = 0; i <= 6; i += 3) {
            if (!GameField.squares[i].getLabel().equals("") &&
                    GameField.squares[i].getLabel().equals(GameField.squares[i + 1].getLabel()) &&
                    GameField.squares[i].getLabel().equals(GameField.squares[i + 2].getLabel())) {
                GameField.theWinner = GameField.squares[i].getLabel();
                GameField.WinnerLabel.setText(GameField.theWinner + " WinnerLabel!");
                GameField.isTie = false;
                showWinner(i, i + 1, i + 2);
                writeScore(GameField.theWinner);
            }
        }
    }
    public static void showWinner(int win1, int win2, int win3){


        for(int i=0;i<9;i++){
            GameField.squares[i].setBackground(Color.WHITE);
        }
        checkTie(win1, win2, win3);
        GameField.buttonNewGame.setEnabled(true);
        GameField.isInGame = false;

        for(int i=0;i<9;i++){
            GameField.squares[i].setEnabled(false);
        }
        GameField.turnX = true;
        GameField.turn0 = false;

    }
    public static void writeScore(String winner){
        if("X".equals(winner)){
            GameField.scoreX +=1;
        }
        if("0".equals(winner)){
            GameField.score0 +=1;
        }
    }
    public static void checkTie(int win1, int win2, int win3){
        if(!GameField.isTie){
            GameField.squares[win1].setBackground(java.awt.Color.RED);
            GameField.squares[win2].setBackground(java.awt.Color.RED);
            GameField.squares[win3].setBackground(java.awt.Color.RED);
        }
    }
}
