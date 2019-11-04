package tic.tak.toe;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.WindowConstants;


public class MainMenu extends JFrame{

    public boolean onePlayer , twoPlayers;

    public MainMenu(){
        setTitle("Tic-tak-toe");
        setSize(500,500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        add(new GameField());
        }

    public static void main(String[] args) {
        MainMenu mainMenu = new MainMenu();
    }
}