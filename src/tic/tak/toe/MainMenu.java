package tic.tak.toe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame implements ActionListener {
    private Button onePlayerButton, twoPlayerButton;
    private Button confirmButton;
    private Label questionLabel, enterNameLabel;
    private JPanel EnterAmountOfPlayersPanel = new JPanel();
    private JPanel EnterNamesPanel = new JPanel();
    private JTextField TF_firstPlayerName = new JTextField("Enter name"), TF_secondPlayerName = new JTextField("Enter name");
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

    public void setAskPanel(){

    }

    public void init() {
        createPanel();
    }

    private Label createLabel(Label label, String text){
        label = new Label(text);
        setComponentColor(label);
        return label;
    }

    private Button createButton(Button button, String text){
        button = new Button(text);
        button.addActionListener(this);
        setComponentColor(button);
        return button;
    }

    private void setComponentColor(Component c){
        c.setBackground(Color.darkGray);
        c.setForeground(Color.WHITE);
    }
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
    private TextField createTextField(TextField textField, String text){
        textField = new TextField(text);
        setComponentColor(textField);
        return textField;
    }
    private void createEnterNamesPanel(){
        EnterNamesPanel.add(createLabel(enterNameLabel, "Enter names"));
        EnterNamesPanel.add(TF_firstPlayerName);
        EnterNamesPanel.add(TF_secondPlayerName);
        EnterNamesPanel.add(createButton(confirmButton, "OK"));
        EnterNamesPanel.setLayout(new GridLayout(4,1));
        this.add(EnterNamesPanel);
        EnterNamesPanel.setVisible(true);
    }

    private void getNames(){
        firstPlayerName = TF_firstPlayerName.getText();
        secondPlayerName = TF_secondPlayerName.getText();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Button button = (Button) e.getSource();

        if(button.getActionCommand().equals("One"))
            Main.isOnePlayer = true;
        else
            Main.twoPlayers = true;

        EnterAmountOfPlayersPanel.setVisible(false);
        createEnterNamesPanel();
        if(Main.isOnePlayer){
            TF_secondPlayerName.setText("Computer");
            TF_secondPlayerName.setEnabled(false);
        }
        if(button.getActionCommand().equals("OK")){
            getNames();
            EnterNamesPanel.setVisible(false);
            this.setSize(500,500);
            this.add(new GameField());
            GameField.buttonNewGame.setEnabled(true);
        }
    }
}
