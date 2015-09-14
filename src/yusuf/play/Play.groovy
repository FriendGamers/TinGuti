package yusuf.play

import yusuf.ai.AiBoard
import yusuf.element.Pair

/**
 * Created with IntelliJ IDEA.
 * User: Yusuf
 * Date: 11/5/13
 * Time: 11:19 PM
 * To change this template use File | Settings | File Templates.
 */
class Play {

    public static void main(String[] argv) {
        AiBoard aiBoard = new AiBoard();
        aiBoard.calculateBoardValue(1);
        println("finished");
    }
}
