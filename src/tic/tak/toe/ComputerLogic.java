package tic.tak.toe;

class ComputerLogic {
    static void computerMove(int i) {
        GameLogic.isFieldChecked = false;
        while (!GameLogic.isFieldChecked) {
            int random = (int) (Math.random() * 9);
            if (!GameField.squares[random].getLabel().equals("X") && !GameField.squares[random].getLabel().equals("0")) {
                System.out.println(random);
                GameField.squares[random].setLabel("0");
                GameField.disableButton(GameField.squares[random]);
                GameLogic.isFieldChecked = true;
            } else if(GameField.emptySquaresLeft == 0){
                GameLogic.isFieldChecked = true;
            }
            else{
                GameLogic.isFieldChecked = false;
            }
        }
    }
}