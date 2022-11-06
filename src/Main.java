import java.util.Scanner;


public class Main {


    private static final int column = 3;
    private static final int row = 3;
    private static String board[][] = new String[column][row];

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        boolean gOver = false;
        clearBoard();
        do {

            do
            {
                gOver = false;
                turn(in, "X");
                if (win("X")) {
                    System.out.println("Player X won");
                    gOver = true;
                }

                if (tie())
                {
                    System.out.println("Ohh man! That's a tie!");
                    gOver = true;
                }

                if (!gOver)
                {
                    turn(in, "O");
                    if(win("O")) {
                        System.out.println("Player O won");
                        gOver = true;
                    }
                }


            }while(!gOver);
            clearBoard();
        } while (SafeInput.getYNConfirm(in, "Want to play again?"));

    }

    private static void turn(Scanner in, String player)
    {
        int tempRow;
        int tempCol;
        boolean validMove;
        do

        {

            validMove = false;

            System.out.print(player + "'s turn now!");
            tempCol = SafeInput.getRangedInt(in, "What column do you choose? (1,2,or 3): ", 1, 3) - 1;
            tempRow = SafeInput.getRangedInt(in, "What row do you choose? (1,2,or 3): ", 1, 3) - 1;
            if (isValidMove(tempCol, tempRow))

            {
                board[tempRow][tempCol] = player;
                display();
                System.out.println();

            }

            else

            {
                validMove = true;
                System.out.println("That spot has been taken! Please choose another spot!");
                System.out.println();
            }


        }    while (validMove);
    }

    private static void clearBoard()
    {
        for (int i = 0; i < row; i++)
        {
            for (int x = 0; x < column; x++) {
                board[i][x] = " ";
            }
        }
    }


    private static void display()
    {
        for (int i = 0; i < row; i++)
        {
            for (int x = 0; x < column; x++)
            {
                System.out.print("[" + board[i][x] + "]");
            }
            System.out.println();
        }

    }


    private static boolean isValidMove(int row, int col)
    {
        if (board[col][row].equals(" "))
        {
            return true;
        } else {
            return false;
        }
    }

    private static boolean win(String player)
    {
        if (isCWin(player) || isRWin(player) || isDiagnalWin(player))
        {

            return true;
        }
        return false;


    }

    private static boolean isCWin(String player)
    {

        for (int j = 0; j < board[0].length; j++)
        {
            boolean completeCol = true;

            if (!(board[0][j].equals(player)))
            {
                completeCol = false;
            } else {
                for (int i = 1; i < board.length; i++)
                {
                    if (!(board[i][j].equals(player)))
                    {
                        completeCol = false;
                        break;
                    }
                }
            }
            if (completeCol)
            {
                return true;
            }
        }
        return false;
    }

    private static boolean isRWin(String player)
    {
        for (int i = 0; i < board.length; i++)
        {
            boolean completeRow = true;

            if (!(board[i][0].equals(player)))
            {
                completeRow = false;
            } else {
                for (int j = 1; j < board[i].length; j++)
                {
                    if (!(board[i][j].equals(player)))
                    {
                        completeRow = false;
                        break;
                    }
                }
            }
            if (completeRow)
            {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagnalWin(String player)
    {
        boolean completeDiagonal = true;

        if(!(board[0][0].equals(player)))
        {
            completeDiagonal = false;
        }
        else
        {
            for(int i = 1; i < board.length; i++)
            {
                if (!(board[i][i].equals(player)))
                {
                    completeDiagonal = false;
                    break;
                }
            }
            if (completeDiagonal)
            {
                return true;
            }
        }
        completeDiagonal = true;
        if(!(board[0][board.length -1].equals(player)))
        {

            completeDiagonal = false;

        }
        else
        {

            for(int i = 1; i < board.length; i++)
            {
                if (!(board[i][board.length-1-i].equals(player)))
                {
                    completeDiagonal = false;
                    break;
                }
            }

            if (completeDiagonal)
            {
                return true;
            }

        }

        return false;
    }

    private static boolean tie () {

        boolean fBoard = true;
        for (int i = 0; i < row; i++) {
            for (int x = 0; x < column; x++)
            {
                if (board[i][x].equals(" "))
                {
                    fBoard = false;
                }
            }

        }


        if (fBoard)
        {
            return true;
        }
        return false;
    }

}