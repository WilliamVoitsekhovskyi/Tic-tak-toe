package tic.tak.toe;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
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
    static String theWinner = "";
    static Font font = new Font("Bauhaus 93", Font.BOLD, 30);
    private static Panel topPanel = new Panel();
    private static Panel centerPanel = new Panel();
    private static Panel bottomPanel = new Panel();

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
        buttonNewGame.setEnabled(false);
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
            centerPanel.add(squares[i]);
        }
    }
    private void setBackgroundColor(){
        topPanel.setBackground(Color.DARK_GRAY);
        centerPanel.setBackground(Color.DARK_GRAY);
        bottomPanel.setBackground(Color.DARK_GRAY);
    }
    private void createTopPanel(){
        topPanel.add(buttonNewGame);
        this.add(topPanel, "North");
    }

    private void createCenterPanel(){
        centerPanel.setLayout(new GridLayout (3,3));
        this.add(centerPanel, "Center");
    }

    private void createBottomPanel(){
        this.add(bottomPanel,"South");
        createField();
        createWinnerLabel();
        createScoreLabel();
        bottomPanel.add(score);
        bottomPanel.add(WinnerLabel);
    }

    public static void cleanField(){
        emptySquaresLeft = 9;
        if(!GameLogic.isInGame)
            for(int i=0; i<9; i++){
                squares[i].setBackground(Color.YELLOW);
                squares[i].setLabel("");
            }
    }

    public static void disableButton(Button button){
        if(GameLogic.isInGame){
            button.setEnabled(false);
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
        disableButton(buttonNewGame);
        GameLogic.makeTurn(theButton);

    }
}
