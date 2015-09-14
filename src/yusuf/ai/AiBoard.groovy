package yusuf.ai

import yusuf.element.Pair

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
    Boolean[][][][][][][][][] markBoard;
    Boolean[][][][][][][][][] visited;
    int[][][][][][][][][] boardValue;
    int[] visitedBoard;
    int[][] board;
    HashMap<Pair, ArrayList<Pair>> move;

    public AiBoard() {
        this.boardValue = new int[5][5][5][5][5][5][5][5][5];
        this.markBoard = new Boolean[5][5][5][5][5][5][5][5][5];
        this.visited = new Boolean[5][5][5][5][5][5][5][5][5];
        this.visitedBoard = new int[90];
        this.board = new int[3][3];
        this.move = new HashMap<Pair, ArrayList<Pair>>();
        this.board[0][0] = 1;
        this.board[0][1] = 1;
        this.board[0][2] = 1;
        this.board[2][0] = 3;
        this.board[2][1] = 3;
        this.board[2][2] = 3;
        this.setMoves();
    }

    public int calculateBoardValue(int player) {
        this.visited[this.board[0][0]][this.board[0][1]][this.board[0][2]][this.board[1][0]][this.board[1][1]][this.board[1][2]][this.board[2][0]][this.board[2][1]][this.board[2][2]] = true;
        int curValue = this.checkBoard();
        if(curValue > 0) {
            this.boardValue[this.board[0][0]][this.board[0][1]][this.board[0][2]][this.board[1][0]][this.board[1][1]][this.board[1][2]][this.board[2][0]][this.board[2][1]][this.board[2][2]] = curValue;
            return curValue;                                                                                                                                     ;
        }

        if(this.markBoard[this.board[0][0]][this.board[0][1]][this.board[0][2]][this.board[1][0]][this.board[1][1]][this.board[1][2]][this.board[2][0]][this.board[2][1]][this.board[2][2]]) {
            return this.boardValue[this.board[0][0]][this.board[0][1]][this.board[0][2]][this.board[1][0]][this.board[1][1]][this.board[1][2]][this.board[2][0]][this.board[2][1]][this.board[2][2]];
        }

        int playerValue1, playerValue2, moveValue, nextPlayer;
        if(player == 1) {
            playerValue1 = 1;
            playerValue2 = 2;
            moveValue = 2;
            nextPlayer = 2;
        } else {
            playerValue1 = 3;
            playerValue2 = 4;
            moveValue = 4;
            nextPlayer = 1;
        }
        ArrayList<Integer> results = new ArrayList<Integer>();
        for(int i=0; i < 3; i++ ) {
            for(int j=0; j < 3; j++) {
                if(this.board[i][j] == playerValue1 || this.board[i][j] == playerValue2) {
                    ArrayList<Pair> moves = this.move.get(new Pair(i,j));
                    for(int k = 0; k < moves.size(); k++) {
                        if(this.board[moves[k].x][moves[k].y] == 0) {
                            this.board[i][j] = 0;
                            int prevValue = this.board[moves[k].x][moves[k].y];
                            this.board[moves[k].x][moves[k].y] = moveValue;
                            if(!this.visited[this.board[0][0]][this.board[0][1]][this.board[0][2]][this.board[1][0]][this.board[1][1]][this.board[1][2]][this.board[2][0]][this.board[2][1]][this.board[2][2]]) {
                                results.add(this.calculateBoardValue(nextPlayer));
                            }
                            this.board[moves[k].x][moves[k].y] = prevValue;
                        }
                    }
                }
            }
        }
        int currentValue;
        if(results[0] != 0) {
            int p = results[0];
            boolean pwin = true;
            for(int i = 1; i < results.size(); i++) {
                if(results[i] == 0) {
                    currentValue = 0 ;
                    break;
                }
                if(results[i] != p) {
                    pwin = false;
                    break;
                }
            }
            if(pwin) {
                currentValue = p;
            } else {
                currentValue = 0;
            }
        } else {
            currentValue = 0;
        }

        //marking board value
        this.markBoard[this.board[0][0]][this.board[0][1]][this.board[0][2]][this.board[1][0]][this.board[1][1]][this.board[1][2]][this.board[2][0]][this.board[2][1]][this.board[2][2]] = true;
        this.boardValue[this.board[0][0]][this.board[0][1]][this.board[0][2]][this.board[1][0]][this.board[1][1]][this.board[1][2]][this.board[2][0]][this.board[2][1]][this.board[2][2]] = currentValue;
    }

    private int checkBoard() {
        if((this.board[0][0] == this.board[0][1]) && (this.board[0][1] == this.board[0][2]) && (this.board[0][0] == 2)) {
            return 1;
        } else if((this.board[0][0] == this.board[0][1]) && (this.board[0][1] == this.board[0][2]) && (this.board[0][0] == 4)) {
            return 2;
        } else if((this.board[0][0] == this.board[1][0]) && (this.board[1][0] == this.board[2][0]) && (this.board[0][0] == 2)) {
            return 1;
        } else if((this.board[0][0] == this.board[1][0]) && (this.board[1][0] == this.board[2][0]) && (this.board[0][0] == 4)) {
            return 2;
        } else if((this.board[0][0] == this.board[1][1]) && (this.board[1][1] == this.board[2][2]) && (this.board[0][0] == 2)) {
            return 1;
        } else if((this.board[0][0] == this.board[1][1]) && (this.board[1][1] == this.board[2][2]) && (this.board[0][0] == 4)) {
            return 2;
        } else if((this.board[0][1] == this.board[1][1]) && (this.board[1][1] == this.board[2][1]) && (this.board[0][1] == 2)) {
            return 1;
        } else if((this.board[0][1] == this.board[1][1]) && (this.board[1][1] == this.board[2][1]) && (this.board[0][1] == 4)) {
            return 2;
        } else if((this.board[0][2] == this.board[1][1]) && (this.board[1][1] == this.board[2][0]) && (this.board[0][2] == 2)) {
            return 1;
        } else if((this.board[0][2] == this.board[1][1]) && (this.board[1][1] == this.board[2][0]) && (this.board[0][2] == 4)) {
            return 2;
        } else if((this.board[0][2] == this.board[1][2]) && (this.board[1][2] == this.board[2][2]) && (this.board[0][2] == 2)) {
            return 1;
        } else if((this.board[0][2] == this.board[1][2]) && (this.board[1][2] == this.board[2][2]) && (this.board[0][2] == 4)) {
            return 2;
        } else if((this.board[1][0] == this.board[1][1]) && (this.board[1][1] == this.board[1][2]) && (this.board[1][0] == 2)) {
            return 1;
        } else if((this.board[1][0] == this.board[1][1]) && (this.board[1][1] == this.board[1][2]) && (this.board[1][0] == 4)) {
            return 2;
        } else if((this.board[2][0] == this.board[2][1]) && (this.board[2][1] == this.board[2][2]) && (this.board[2][0] == 2)) {
            return 1;
        } else if((this.board[2][0] == this.board[2][1]) && (this.board[2][1] == this.board[2][2]) && (this.board[2][0] == 4)) {
            return 2;
        } else {
            return 0;
        }
    }

    private void setMoves() {
        ArrayList<Pair> m = new ArrayList<>();
        m[0] = new Pair(1,0);
        m[1] = new Pair(1,1);
        m[2] = new Pair(0,1);
        this.move[new Pair(0,0)] = m;
        ArrayList<Pair> m1 = new ArrayList<>();
        m1[0] = new Pair(0,0);
        m1[1] = new Pair(1,1);
        m1[2] = new Pair(0,2);
        this.move[new Pair(0,1)] = m1;
        ArrayList<Pair> m2 = new ArrayList<>();
        m2[0] = new Pair(0,1);
        m2[1] = new Pair(1,1);
        m2[2] = new Pair(1,2);
        this.move[new Pair(0,2)] = m2;
        ArrayList<Pair> m3 = new ArrayList<>();
        m3[0] = new Pair(0,0);
        m3[1] = new Pair(1,1);
        m3[2] = new Pair(2,0);
        this.move[new Pair(1,0)] = m3;
        ArrayList<Pair> m4 = new ArrayList<>();
        m4[0] = new Pair(0,0);
        m4[1] = new Pair(0,1);
        m4[2] = new Pair(0,2);
        m4[3] = new Pair(1,0);
        m4[4] = new Pair(1,2);
        m4[5] = new Pair(2,0);
        m4[6] = new Pair(2,1);
        m4[7] = new Pair(2,2);
        this.move[new Pair(1,1)] = m4;
        ArrayList<Pair> m5 = new ArrayList<>();
        m5[0] = new Pair(0,2);
        m5[1] = new Pair(1,1);
        m5[2] = new Pair(2,2);
        this.move[new Pair(1,2)] = m5;
        ArrayList<Pair> m6 = new ArrayList<>();
        m6[0] = new Pair(1,0);
        m6[1] = new Pair(1,1);
        m6[2] = new Pair(2,1);
        this.move[new Pair(2,0)] = m6;
        ArrayList<Pair> m7 = new ArrayList<>();
        m7[0] = new Pair(2,0);
        m7[1] = new Pair(1,1);
        m7[2] = new Pair(2,2);
        this.move[new Pair(2,1)] = m7;
        ArrayList<Pair> m8 = new ArrayList<>();
        m8[0] = new Pair(2,1);
        m8[1] = new Pair(1,1);
        m8[2] = new Pair(1,2);
        this.move[new Pair(2,2)] = m5;
    }
}
