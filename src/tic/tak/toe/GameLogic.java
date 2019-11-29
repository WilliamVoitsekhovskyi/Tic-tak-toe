package tic.tak.toe;

import java.awt.*;
//Клас що описує ігрову логіку і поведінку при певних подіях на полі
class GameLogic {
    static boolean isOnePlayer = false , twoPlayers = false;
    static boolean turnX = true; //хід Х
    static boolean turn0 = false; // хід 0
    static boolean isInGame = true; //ми у грі
    static final int amountOfSquares = 9; // кількість клітинок на полі

    static void startNewGame(Button button){
        //якщо нажата кнопка "нова гра"
        if(button == GameField.buttonNewGame){
            for(int i=0; i < amountOfSquares; i++){
                GameField.squares[i].setEnabled(true);
                GameField.squares[i].setFont(GameField.font);
            }
            //якщо гра завершилась - очистити поле
            if(!isInGame){
                GameField.cleanField();
            }
            isInGame = true;
        }
    }

    static void makeTurn(Button button) {
        //якщо гра проти комп'ютеру
        if(isOnePlayer)
            onePlayerMakeTurn(button);
        //якщо грає два гравці
        else
            twoPlayersMakeTurns(button);
    }

    private static void onePlayerMakeTurn(Button button) {
        for (int i = 0; i < amountOfSquares; i++) {
            //малюємо  в обраній кнопці символ
            if (button == GameField.squares[i] && turnX) {
                GameField.squares[i].setLabel("X");
                GameField.disableButton(GameField.squares[i]);
                turnX = false;
                turn0 = true;
                checkWinner();
                //комп'ютер робить хід
                computerTurn();
                break;
            }

        }
    }

    private static void computerTurn(){
            for (int i = 0; i < amountOfSquares; i++) {
            if (turn0) {
                ComputerLogic.computerMove();
                turn0 = false;
                turnX = true;
                checkWinner();
                break;
                }
            }
    }

    private static void twoPlayersMakeTurns(Button button) {
        for (int i = 0; i < amountOfSquares; i++) {
            //якщо нажата кнопка поля і хід Х то ставимо Х і робимо кнопку недоступною
            if (button == GameField.squares[i] && turnX) {
                GameField.squares[i].setLabel("X");
                GameField.disableButton(GameField.squares[i]);
                turnX = false;
                turn0 = true;
                checkWinner();
                break;
            }
            //якщо нажата кнопка поля і хід 0 то ставимо 0 і робимо кнопку недоступною
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

    //метод який перівіряє чи нічья
    static boolean isTie(){
        //якщо всі клітинки зайняті тоді true
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
        for(int i = 0; i < amountOfSquares; i++){
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
        // перевіряємо ліву діагональ
        if (!GameField.squares[0].getLabel().equals("") &&
                GameField.squares[0].getLabel().equals(GameField.squares[4].getLabel()) &&
                GameField.squares[0].getLabel().equals(GameField.squares[8].getLabel())) {

            GameField.theWinner = GameField.squares[0].getLabel();
            showWinner(0, 4, 8);
            writeScore(GameField.theWinner);
        }

        // перевіряємо праву діагональ
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
        //якщо не нічья то шукаємо переможця
        if(!isTie())
            highlightWinner(win1, win2, win3);
        //вимкнути кнопку нова гра
        GameField.buttonNewGame.setEnabled(true);
        isInGame = false;
        //зробити поле недоступним
        for(int i=0;i<amountOfSquares;i++)
            GameField.squares[i].setEnabled(false);

        turnX = true;
        turn0 = false;

    }

    private static void writeScore(String winner){
        //якщо виграв Х
        if("X".equals(winner)){
            GameField.scoreX +=1;
        }
        //Якщо виграв 0
        if("0".equals(winner)){
            GameField.score0 +=1;
        }
    }

    //підсвітити клінтинки переможця
    private static void highlightWinner(int win1, int win2, int win3){
        GameField.squares[win1].setBackground(java.awt.Color.RED);
        GameField.squares[win2].setBackground(java.awt.Color.RED);
        GameField.squares[win3].setBackground(java.awt.Color.RED);
    }

    private static void checkHorizontal() {
        for (int i = 0; i <= 6; i += 3) {
            //перевіряємо на виграш горизонталі
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
