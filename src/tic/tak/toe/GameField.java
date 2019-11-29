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
    static Font font = new Font("Bauhaus 93", Font.BOLD, 15);
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

    // встановлюємо колір заднього фону
    private void setBackgroundColor(){
        topPanel.setBackground(Color.DARK_GRAY);
        centerPanel.setBackground(Color.DARK_GRAY);
        bottomPanel.setBackground(Color.DARK_GRAY);
    }

    //створюємо верхню панель і додаєм елементи
    private void createTopPanel(){
        topPanel.add(buttonNewGame);
        this.add(topPanel, "North");
    }

    //створюємо центральну панель і додаєм елементи
    private void createCenterPanel(){
        centerPanel.setLayout(new GridLayout (3,3));
        this.add(centerPanel, "Center");
    }
    //створюємо нижню панель і додаєм елементи
    private void createBottomPanel() {
        this.add(bottomPanel, "South");
        createField();
        createScoreLabel();
        createWinnerLabel();
        bottomPanel.add(score);
        bottomPanel.add(WinnerLabel);
        bottomPanel.setLayout(new GridLayout(1, 3));
    }

    //прибрати символи з поля
    static void cleanField(){
        emptySquaresLeft = 9;
        if(!GameLogic.isInGame)
            for(int i=0; i<9; i++){
                squares[i].setBackground(Color.YELLOW);
                squares[i].setLabel("");
            }
    }

    //зробити неактивною кнопку
    static void disableButton(Button button){
        if(GameLogic.isInGame)
            button.setEnabled(false);
    }

    //показує чій хід
    private void showWhoTurn(){
        if(GameLogic.turnX)
            WinnerLabel.setText("Your turn, " + MainMenu.firstPlayerName + "!");
        else if(GameLogic.turn0 && !GameLogic.isOnePlayer)
            WinnerLabel.setText("Your turn, " + MainMenu.secondPlayerName + "!");
    }

    //встановити рахунок
    static void setScore(){
        score.setText(MainMenu.firstPlayerName + " " + scoreX + " : " + MainMenu.secondPlayerName + " " + score0);
    }

    //перевірка події
    @Override
    public void actionPerformed(ActionEvent e) {
        Button theButton = (Button) e.getSource();
        GameLogic.startNewGame(theButton); //якщо нажата кнопка нова гра
        disableButton(buttonNewGame); //зробити кнопку нова гра недоступною
        GameLogic.makeTurn(theButton); //робим хід і малюємо у нажатому квадраті символ
        showWhoTurn();
    }
}
