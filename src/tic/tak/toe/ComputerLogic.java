package tic.tak.toe;

public class ComputerLogic {
    public static void computerMove(int i) {
        GameLogic.isFieldChecked = false;
        while (!GameLogic.isFieldChecked) {
            System.out.println(i);
            int random = (int) (Math.random() * 8);
            if ((GameField.squares[0].getLabel().equals("X")) && (GameField.squares[1].getLabel().equals("X"))) {
                GameField.squares[2].setLabel("0");
                GameField.disableButton(GameField.squares[2]);
                GameLogic.isFieldChecked = true;
            } else if (!GameField.squares[random].getLabel().equals("X")) {
                System.out.println(random);
                GameField.squares[random].setLabel("0");
                GameField.disableButton(GameField.squares[random]);
                GameLogic.isFieldChecked = true;
            } else {
                GameLogic.isFieldChecked = false;
            }
        }
    }
}