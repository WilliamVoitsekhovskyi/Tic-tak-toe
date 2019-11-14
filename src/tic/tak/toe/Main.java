package tic.tak.toe;

import javax.swing.*;


public class Main extends JFrame{

    public Main() {
        setTitle("Tic-tak-toe");
        setSize(500, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        add(new MainMenu());
    }

    public static void main(String[] args) {
        MainMenu mainMenu = new MainMenu();
    }
}