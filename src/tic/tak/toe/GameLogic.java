package tic.tak.toe;

import java.awt.*;

class GameLogic {
    public static boolean isOnePlayer = false , twoPlayers = false;
    static boolean turnX = true;
    static boolean turn0 = false;
    static boolean isInGame = true;
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
        if(isOnePlayer)
            onePlayerMakeTurn(button);
        else
            twoPlayersMakeTurns(button);
    }
    private static void onePlayerMakeTurn(Button button) {
        for (int i = 0; i < 9; i++) {
            if (button == GameField.squares[i] && turnX) {
                GameField.squares[i].setLabel("X");
                GameField.disableButton(GameField.squares[i]);
                turnX = false;
                turn0 = true;
                checkWinner();
                computerTurn();
                break;
            }

        }
    }
    private static void computerTurn(){
            for (int i = 0; i < 9; i++) {
            if (turn0) {
                ComputerLogic.computerMove(i);
                turn0 = false;
                turnX = true;
                checkWinner();
                break;
                }
            }
    }

    private static void twoPlayersMakeTurns(Button button) {
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
        System.out.println(GameField.emptySquaresLeft);
        return GameField.emptySquaresLeft == 0;
    }

    private static void checkWinner(){
        checkHorizontal();
        checkVertical();
        checkDiagonal();
        GameField.setScore();
        GameField.emptySquaresLeft--;
        if(isTie() && isInGame){
            GameField.WinnerLabel.setText("Tie!");
            GameField.buttonNewGame.setEnabled(true);
            isInGame = false;
            showWhiteField();
        }
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
