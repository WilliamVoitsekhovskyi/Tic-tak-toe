package tic.tak.toe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame implements ActionListener {

    private Button onePlayerButton, twoPlayerButton;
    private Button confirmButton;
    private Label questionLabel, enterNameLabel;
    private static JPanel EnterAmountOfPlayersPanel = new JPanel();
    private JPanel EnterNamesPanel = new JPanel();
    private JTextField TF_firstPlayerName = new JTextField("Player X"), TF_secondPlayerName = new JTextField("Player 0");
    static String firstPlayerName, secondPlayerName;

    MainMenu(){
        init();
        setFocusable(true);
        setTitle("Tic-tak-toe");
        setSize(200, 200);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        add(EnterAmountOfPlayersPanel);
    }

    private void init() {
        createPanel();
    }

    //створити заданий текст
    private Label createLabel(Label label, String text){
        label = new Label(text);
        setComponentColor(label);
        return label;
    }
    //створити заддану кнопку
    private Button createButton(Button button, String text){
        button = new Button(text);
        button.addActionListener(this);
        setComponentColor(button);
        return button;
    }
    //встановити колір для заданного компоненту
    private void setComponentColor(Component c){
        c.setBackground(Color.darkGray);
        c.setForeground(Color.WHITE);
    }
    //Створюємо основну панель(вибір кількості гравців
    private void createPanel(){
        EnterAmountOfPlayersPanel.add(createLabel(questionLabel, "One or two players?"));
        EnterAmountOfPlayersPanel.add(createButton(onePlayerButton, "One"));
        EnterAmountOfPlayersPanel.add(createButton(twoPlayerButton, "Two"));
        EnterAmountOfPlayersPanel.setLayout(new GridLayout(3,1));
        EnterAmountOfPlayersPanel.setSize(200,200);
        EnterAmountOfPlayersPanel.setLocation(0, 0);
        EnterAmountOfPlayersPanel.setVisible(true);
        this.add(EnterAmountOfPlayersPanel);
    }
    //Панель вводу імен
    private void createEnterNamesPanel(){
        EnterNamesPanel.add(createLabel(enterNameLabel, "Enter names"));
        EnterNamesPanel.add(TF_firstPlayerName);
        EnterNamesPanel.add(TF_secondPlayerName);
        EnterNamesPanel.add(createButton(confirmButton, "OK"));
        EnterNamesPanel.setLayout(new GridLayout(4,1));
        this.add(EnterNamesPanel);
        EnterNamesPanel.setVisible(true);
    }
    //Запишемо імена гравців
    private void getNames(){
        firstPlayerName = TF_firstPlayerName.getText();
        secondPlayerName = TF_secondPlayerName.getText();
    }
    //Оброблюємо події нажимання на кнопки
    @Override
    public void actionPerformed(ActionEvent e) {
        Button button = (Button) e.getSource();
        //якщо кнопка з написом "one"
        if(button.getActionCommand().equals("One"))
            GameLogic.isOnePlayer = true;
        //якщо ні
        else
            GameLogic.twoPlayers = true;

        //змінюємо на панель вводу імен
        EnterAmountOfPlayersPanel.setVisible(false);
        createEnterNamesPanel();

        //якщо один гравець то називаємо оппонента "Computer"
        if(GameLogic.isOnePlayer){
            TF_secondPlayerName.setText("Computer");
            TF_secondPlayerName.setEnabled(false);
        }
        //якщо кнопка з написом "OK"
        if(button.getActionCommand().equals("OK")){
            getNames();
            EnterNamesPanel.setVisible(false);
            //змінюємо на панель гри
            this.setSize(500,500);
            this.add(new GameField());
            GameField.buttonNewGame.setEnabled(true);
        }
    }
}
