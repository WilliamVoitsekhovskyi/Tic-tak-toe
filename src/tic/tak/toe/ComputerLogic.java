package tic.tak.toe;

class ComputerLogic {

    static void computerMove() {
        int selectedSquare;
        selectedSquare = findEmptySquare("0");

        if ( selectedSquare == -1 )
            selectedSquare = findEmptySquare("X");

        if ( (selectedSquare == -1)
                &&(GameField.squares[4].getLabel().equals("")) ){
            selectedSquare=4;
        }

        if ( selectedSquare == -1 ){
            selectedSquare = getRandomSquare();
        }
        GameField.squares[selectedSquare].setLabel("0");
        GameField.disableButton(GameField.squares[selectedSquare]);
    }

    private static int findEmptySquare(String player) {
        int[] weight = new int[9];
        for ( int i = 0; i < 9; i++ ) {
            if ( GameField.squares[i].getLabel().equals("0") )
                weight[i] = -1;
            else if ( GameField.squares[i].getLabel().equals("X") )
                weight[i] = 1;
            else
                weight[i] = 0;
        }
        int twoWeights = player.equals("0") ? -2 : 2;
// Проверим, есть ли в ряду 1 две одинаковые клетки и
// одна пустая.
        for (int i = 0; i <= 6; i += 3) {
            if ( weight[i] + weight[i+1] + weight[i+2] == twoWeights ) {
                if ( weight[i] == 0 )
                    return i;
                else if ( weight[i+1] == 0 )
                    return i+1;
                else
                    return i+2;
            }
        }

        for (int i = 0; i < 3; i++)
        if (weight[i] + weight[i+3] + weight[i+6] == twoWeights) {
            if ( weight[i] == 0 )
                return i;
            else if ( weight[i+3] == 0 )
                return i+3;
            else
                return i+6;
        }

// Проверим, есть ли в диагонали 1 две одинаковые клетки
// и одна пустая.
        if (weight[0] + weight[4] + weight[8] == twoWeights ){
            if ( weight[0] == 0 )
                return 0;
            else if ( weight[4] == 0 )
                return 4;
            else
                return 8;
        }
// Проверим, есть ли в диагонали 2 две одинаковые клетки
// и одна пустая.
        if (weight[2] + weight[4] + weight[6] == twoWeights ){
            if ( weight[2] == 0 )
                return 2;
            else if ( weight[4] == 0 )
                return 4;
            else
                return 6;
        }
// Не найдено двух одинаковых соседних клеток
        return -1;
    }

    private static int getRandomSquare() {
        boolean gotEmptySquare = false;
        int selectedSquare = -1;
        do {
            selectedSquare = (int) (Math.random() * 9 );
            if (GameField.squares[selectedSquare].getLabel().equals("") || GameLogic.isTie()){
                gotEmptySquare = true; // чтобы закончить цикл
            }
        } while (!gotEmptySquare );
        return selectedSquare;
    }
}