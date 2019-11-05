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

    static Label WinnerLabel, score;
    static Button buttonNewGame;
    static Button[] squares;
    static byte emptySquaresLeft = 9;
    static byte scoreX = 0, score0 = 0;
    static boolean isInGame = true;
    static boolean isTie = false;
    static boolean turnX = true;
    static boolean turn0 = false;
    static String theWinner = "";
    static Font font = new Font("Bauhaus 93", Font.BOLD, 30);
    private static Panel top = new Panel();
    private static Panel center = new Panel();
    private static Panel bottom = new Panel();

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

    public static void cleanField(){
        emptySquaresLeft = 9;
        if(!isInGame)
            for(int i=0; i<9; i++){
                squares[i].setBackground(Color.YELLOW);
                squares[i].setLabel("");
            }
    }

    public void disableButtonNewGame(){
        if(isInGame){
            buttonNewGame.setEnabled(false);
            WinnerLabel.setBounds(WIDTH, -40, 135, 135);
            score.setBounds(350, -40, 135, 135);
            WinnerLabel.setText("You turn!");
        }
    }

    public static void setScore(){
        score.setText("X  "+ scoreX + " : " + "0  " + score0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Button theButton = (Button) e.getSource();

        GameLogic.startNewGame(theButton);
        disableButtonNewGame();
        GameLogic.makeTurn(theButton);

    }
}
