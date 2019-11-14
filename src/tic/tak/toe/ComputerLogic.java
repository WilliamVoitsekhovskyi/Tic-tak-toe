package tic.tak.toe;

class ComputerLogic {
    static boolean flag = true;

    static void computerMove(int i) {
        GameLogic.isFieldChecked = false;
        int counter = 1;// must be 1
//        while (!GameLogic.isFieldChecked) {
//            counter++;
     //       int random = (int) (Math.random() * 9);
     //       if (counter >= 2) // must be 2
                checkBetterMove();
            if (!GameLogic.isFieldChecked) {
//                if (!(GameLogic.isX(random)) && !(GameLogic.is0(random))) {
//                    GameField.squares[random].setLabel("0");
//                    GameField.disableButton(GameField.squares[random]);
//                    GameLogic.isFieldChecked = true;
//                } else if (GameField.emptySquaresLeft == 0) {
//                    GameLogic.isFieldChecked = true;
//                } else {
//                    GameLogic.isFieldChecked = false;
                }
    //        }
   //     }
    }


    private static void checkBetterMove() {

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





    static int findEmptySquare(String player) {
        int weight[] = new int[9];
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
        if ( weight[0] + weight[1] + weight[2] == twoWeights ) {
            if ( weight[0] == 0 )
                return 0;
            else if ( weight[1] == 0 )
                return 1;
            else
                return 2;
        }
// Проверим, есть ли в ряду 2 две одинаковые клетки и
// одна пустая.
        if (weight[3] +weight[4] + weight[5] == twoWeights) {
            if ( weight[3] == 0 )
                return 3;
            else if ( weight[4] == 0 )
                return 4;
            else
                return 5;
        }
        if (weight[6] + weight[7] +weight[8] == twoWeights ) {
            if ( weight[6] == 0 )
                return 6;
            else if ( weight[7] == 0 )
                return 7;
            else
                return 8;
        }

        if (weight[0] + weight[3] + weight[6] == twoWeights) {
            if ( weight[0] == 0 )
                return 0;
            else if ( weight[3] == 0 )
                return 3;
            else
                return 6;
        }
// Проверим, есть ли в колонке 2 две одинаковые клетки
// и одна пустая.
        if (weight[1] +weight[4] + weight[7] == twoWeights ) {
            if ( weight[1] == 0 )
                return 1;
            else if ( weight[4] == 0 )
                return 4;
            else
                return 7;
        }
// Проверим, есть ли в колонке 3 две одинаковые клетки
// и одна пустая.
        if (weight[2] + weight[5] + weight[8] == twoWeights ){
            if ( weight[2] == 0 )
                return 2;
            else if ( weight[5] == 0 )
                return 5;
            else
                return 8;
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
    } // конец метода findEmptySquare()
    /**
     * Этот метод выбирает любую пустую клетку
     * @return случайно выбранный номер клетки
     */
    static int getRandomSquare() {
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
        /*
        if(i==3||i==6||i==9)
            {
                j++;
                counterX = 0;
                counter0 = 0;
            }
            if (GameLogic.isX(i))
                counterX++;
            else if (GameLogic.is0(i))
                counter0++;
            if ((counterX == 2) || (counter0 == 2)) {
                System.out.println(j);
                for (int k=0; k < 3*j; k++) {
                    if (GameField.squares[j].getLabel().equals("")) {
                        GameField.squares[j].setLabel("0");
                        GameField.disableButton(GameField.squares[j]);
                        counterX = 0;
                        counter0 = 0;
                        GameLogic.isFieldChecked = true;
                        System.out.println("YES1");
                        break;
                    } else {
                        counterX = 0;
                        counter0 = 0;













        for (int i = 3; i < 6 ; i++) {
            if (GameLogic.isX(i))
                counterX++;
            else if (GameLogic.is0(i))
                counter0++;
            if ((counterX == 2) || (counter0 == 2)){
                for (int j = 3; j < 6 ; j++) {
                    if(GameField.squares[j].getLabel().equals("")){
                        GameField.squares[j].setLabel("0");
                        GameField.disableButton(GameField.squares[j]);
                        counterX = 0;
                        counter0 = 0;
                        GameLogic.isFieldChecked = true;
                        flag = false;
                        System.out.println("YES2");
                    }
                    else{
                        counterX = 0;
                        counter0 = 0;
                    }
                }
            }
        }

        for (int i = 6; i < 9 ; i++) {
            if (GameLogic.isX(i))
                counterX++;
            else if (GameLogic.is0(i))
                counter0++;
            if ((counterX == 2) || (counter0 == 2)){
                for (int j = 6; j < 9 ; j++) {
                    if(GameField.squares[j].getLabel().equals("")){
                        GameField.squares[j].setLabel("0");
                        GameField.disableButton(GameField.squares[j]);
                        counterX = 0;
                        counter0 = 0;
                        GameLogic.isFieldChecked = true;
                        flag = false;
                        System.out.println("YES3");
                    }
                    else{
                        counterX = 0;
                        counter0 = 0;
                    }
                }
            }
        }

    }
}

         */