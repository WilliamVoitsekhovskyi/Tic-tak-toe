package tic.tak.toe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame implements ActionListener {
    Button onePlayerButton, twoPlayerButton;
    Button confirmButton;
    Label questionLabel, enterNameLabel;
    JPanel panel = new JPanel();
    JPanel panel1 = new JPanel();
    JTextField firstPlayerName = new JTextField("Enter name"), secondPlayerName = new JTextField("Enter name");
    MainMenu(){
        init();
        setFocusable(true);
        setTitle("Tic-tak-toe");
        setSize(200, 200);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        add(panel);
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
        panel.add(createLabel(questionLabel, "One or two players?"));
        panel.add(createButton(onePlayerButton, "One"));
        panel.add(createButton(twoPlayerButton, "Two"));
        panel.setLayout(new GridLayout(3,1));
        panel.setSize(200,200);
        panel.setLocation(0, 0);
        panel.setVisible(true);
        this.add(panel);
    }

    private void createTextFields(){
        panel1.add(createLabel(enterNameLabel, "Enter names"));
        panel1.add(firstPlayerName);
        panel1.add(secondPlayerName);
        panel1.add(createButton(confirmButton, "OK"));
        panel1.setLayout(new FlowLayout());
        this.add(panel1);
        panel1.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Button button = (Button) e.getSource();

        if(button.getActionCommand().equals("One"))
            Main.isOnePlayer = true;
        else
            Main.twoPlayers = true;

        panel.setVisible(false);
        createTextFields();;
        if(button.getActionCommand().equals("OK")){
            panel1.setVisible(false);
            this.setSize(500,500);
            this.add(new GameField());
            GameField.buttonNewGame.setEnabled(true);
        }
    }
}
