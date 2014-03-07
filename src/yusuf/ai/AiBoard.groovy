package yusuf.ai
/**
 * Created with IntelliJ IDEA.
 * User: Yusuf
 * Date: 11/5/13
 * Time: 11:04 PM
 * To change this template use File | Settings | File Templates.
 */
/**
 * Not moved player1 is 1
 * Moved player1 2
 * Not moved player2 is 3
 * Moved player2 is 4
 * board value 0 draw
 * board value 1 player1 win
 * board value 2 player2 win
**/
class AiBoard {
    int[] boardValue; //= new int[90];
    int[] markBoard; //= new int[90];
    int[][] board; //= new int[10][10];

    public AiBoard() {
        boardValue = new int[90];
        markBoard = new int[90];
        board = new int[3][3];
        board[0][0] = 1;
        board[0][1] = 1;
        board[0][2] = 1;
        board[2][0] = 3;
        board[2][1] = 3;
        board[2][2] = 3;
    }

    static void calculateBoardValue() {

    }
}
