package tic.tak.toe;

import java.awt.BorderLayout;
import javax.swing.*;


public class Main extends JFrame{

    public static boolean onePlayer = false , twoPlayers = false;

    public Main() {
        setTitle("Tic-tak-toe");
        setSize(500, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        add(new MainMenu());
    }

    public static void main(String[] args) {
        //Main main = new Main();
        MainMenu mainMenu = new MainMenu();
    }
}