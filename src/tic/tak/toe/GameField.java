package tic.tak.toe;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JApplet;

public class GameField extends JApplet implements ActionListener{

    private Label WinnerLabel, score;
    private Button buttonNewGame;
    private Button[] squares;
    private byte emptySquaresLeft = 9;
    private byte scoreX = 0, score0 = 0;
    private boolean isInGame = true;
    private boolean isTie = false;
    private boolean turnX = true;
    private boolean turn0 = false;
    private String theWinner = "";
    private Font font = new Font("Bauhaus 93", Font.BOLD, 30);
    private Panel top = new Panel();
    private Panel center = new Panel();
    private Panel bottom = new Panel();

    GameField(){
        init();
        setFocusable(true);
    }
    public void init() {
        setBackgroundColor();
        createButtonNewGame();
        createScoreLabel();
        createWinnerLabel();
        createTopPanel();
        createCenterPanel();
        createBottomPanel();
    }
    private void createButtonNewGame(){
        buttonNewGame = new Button("New game");
        buttonNewGame.addActionListener(this);
    }
    private void createScoreLabel(){
        score = new Label("");
        score.setForeground(Color.WHITE);
        score.setFont(font);
    }
    private void createWinnerLabel(){
        WinnerLabel = new Label("");
        WinnerLabel.setForeground(java.awt.Color.WHITE);
        WinnerLabel.setFont(font);
    }
    private void createField() {
        squares = new Button[9];
        for (int i = 0; i < 9; i++) {
            squares[i] = new Button();
            squares[i].addActionListener(this);
            squares[i].setBackground(Color.YELLOW);
            squares[i].setEnabled(false);
            center.add(squares[i]);
        }
    }
    private void setBackgroundColor(){
        top.setBackground(Color.DARK_GRAY);
        center.setBackground(Color.DARK_GRAY);
        bottom.setBackground(Color.DARK_GRAY);
    }
    private void createTopPanel(){
        top.add(buttonNewGame);
        this.add(top, "North");
    }

    private void createCenterPanel(){
        center.setLayout(new GridLayout (3,3));
        this.add(center, "Center");
    }

    private void createBottomPanel(){
        this.add(bottom,"South");
        createField();
        createWinnerLabel();
        createScoreLabel();
        bottom.add(score);
        bottom.add(WinnerLabel);
    }


    private void checkWinner(){
        emptySquaresLeft--;
        if(emptySquaresLeft == 0){
            WinnerLabel.setText("Tie!");
            isTie = true;
            showWinner(0,0,0);
        }
        checkHorizontal();
        checkVertical();
        checkDiagonal();
        setScore();
    }

    private void setScore(){
        score.setText("X  "+ scoreX + " : " + "0  " + score0);
    }

    private void checkHorizontal() {
        for (int i = 0; i <= 6; i += 3) {
            if (!squares[i].getLabel().equals("") &&
                    squares[i].getLabel().equals(squares[i + 1].getLabel()) &&
                    squares[i].getLabel().equals(squares[i + 2].getLabel())) {
                theWinner = squares[i].getLabel();
                WinnerLabel.setText(theWinner + " WinnerLabel!");
                isTie = false;
                showWinner(i, i + 1, i + 2);
                writeScore(theWinner);
            }
        }
    }

    private void checkVertical() {
        for (int i = 0; i < 3; i++) {
            if (!squares[i].getLabel().equals("") &&
                    squares[i].getLabel().equals(squares[(i + 3)].getLabel()) &&
                    squares[i].getLabel().equals(squares[(i + 6)].getLabel())) {
                    theWinner = squares[i].getLabel();
                    WinnerLabel.setText(theWinner + " WinnerLabel!");
                    isTie = false;
                    showWinner(i, i + 3, i + 6);
                    writeScore(theWinner);
            }
        }
    }

    private void checkDiagonal() {
        // diagonal left
        if (!squares[0].getLabel().equals("") &&
                squares[0].getLabel().equals(squares[4].getLabel()) &&
                squares[0].getLabel().equals(squares[8].getLabel())) {
            theWinner = squares[0].getLabel();
            WinnerLabel.setText(theWinner + " WinnerLabel!");
            isTie = false;
            showWinner(0, 4, 8);
            writeScore(theWinner);
        }
        // diagonal right
        if (!squares[2].getLabel().equals("") &&
                squares[2].getLabel().equals(squares[4].getLabel()) &&
                squares[2].getLabel().equals(squares[6].getLabel())) {
            theWinner = squares[2].getLabel();
            WinnerLabel.setText(theWinner + " WinnerLabel!");
            isTie = false;
            showWinner(2, 4, 6);
            writeScore(theWinner);
        }
    }

    private void showWinner(int win1, int win2, int win3){


        for(int i=0;i<9;i++){
            squares[i].setBackground(Color.WHITE);
        }
        checkTie(win1, win2, win3);
        buttonNewGame.setEnabled(true);
        isInGame = false;

        for(int i=0;i<9;i++){
            squares[i].setEnabled(false);
        }
        turnX = true;
        turn0 = false;

    }
    private void writeScore(String winner){
        if("X".equals(winner)){
            scoreX +=1;
        }
        if("0".equals(winner)){
            score0 +=1;
        }
    }
    private void checkTie(int win1, int win2, int win3){
        if(!isTie){
            squares[win1].setBackground(java.awt.Color.RED);
            squares[win2].setBackground(java.awt.Color.RED);
            squares[win3].setBackground(java.awt.Color.RED);
        }
    }
    private void cleanField(){
        emptySquaresLeft = 9;
        if(!isInGame)
            for(int i=0; i<9; i++){
                squares[i].setBackground(Color.YELLOW);
                squares[i].setLabel("");
            }
    }

    protected void paintComponent(Graphics g){
        repaint();
    }

    private void startNewGame(Button button){
        if(button == buttonNewGame){
            for(int i=0; i<9; i++){
                squares[i].setEnabled(true);
                squares[i].setFont(font);
            }
            if(!isInGame){
                cleanField();
            }
            isInGame = true;
        }
    }

    private void disableButtonNewGame(){
        if(isInGame){
            buttonNewGame.setEnabled(false);
            WinnerLabel.setBounds(WIDTH, -40, 135, 135);
            score.setBounds(350, -40, 135, 135);
            WinnerLabel.setText("You turn!");

            if(!isInGame){
                buttonNewGame.setEnabled(true);
            }
        }
    }

    private void makeTurn(Button button) {
        for (int i = 0; i < 9; i++) {
            if (button == squares[i] && turnX) {
                squares[i].setLabel("X");
                squares[i].setEnabled(false);
                turnX = false;
                turn0 = true;
                checkWinner();
                break;
            }

            if (button == squares[i] && turn0) {
                squares[i].setLabel("0");
                squares[i].setEnabled(false);
                turn0 = false;
                turnX = true;
                checkWinner();
                break;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Button theButton = (Button) e.getSource();

        startNewGame(theButton);
        disableButtonNewGame();
        makeTurn(theButton);

    }
}
