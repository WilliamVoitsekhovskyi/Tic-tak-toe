/*package tic.tak.toe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AskQuestion extends JApplet implements ActionListener {

    Button onePlayerButton, twoPlayerButton;
    Label questionLabel;
    JPanel panel = new JPanel();
    JTextField firstPlayerName = new JTextField("Enter name"), secondPlayerName = new JTextField("Enter name");
  //  Panel panel;

    AskQuestion(){
        init();
        setFocusable(true);
    }
    public void init() {
        createNoButton();
        createYesButton();
        createQuestionLabel();
        createPanel();
    }

    private void createYesButton(){
        onePlayerButton = new Button("One");
        onePlayerButton.addActionListener(this);
        setComponentColor(onePlayerButton);
    }

    private void createNoButton(){
        twoPlayerButton = new Button("Two");
        twoPlayerButton.addActionListener(this);
        setComponentColor(twoPlayerButton);
    }

    private void createQuestionLabel(){
        questionLabel = new Label("Choose number of players:");
        setComponentColor(questionLabel);
    }

    private void setComponentColor(Component c){
        c.setBackground(Color.darkGray);
        c.setForeground(Color.WHITE);
    }
    private void createPanel(){
        panel.add(questionLabel);
        panel.add(onePlayerButton);
        panel.add(twoPlayerButton);
        panel.setLayout(new GridLayout(3,1));
        panel.setSize(100,100);
        panel.setLocation(0, 0);
        panel.setVisible(true);
        this.add(panel);
    }

    private void createTextFields(){
        JPanel panel1 = new JPanel();
        firstPlayerName.setSize(50,50);
        secondPlayerName.setSize(50,50);
        panel1.add(firstPlayerName);
        panel1.add(secondPlayerName);
        panel1.setLayout(new FlowLayout());
        this.add(panel1);
        panel1.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Button button = (Button) e.getSource();
        if(button == onePlayerButton){
            MainMenu.onePlayer = true;
        }
        else
            MainMenu.twoPlayers = true;
        GameField.buttonNewGame.setEnabled(true);
        System.out.println(MainMenu.onePlayer);
        panel.setVisible(false);
        createTextFields();
        repaint();
    }
}
*/