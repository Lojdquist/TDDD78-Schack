/**
 * Created by axelo225 on 07/03/16.
 */
public class SchackTest {
    public static void main(String[] args) {
        Board testBoard = new Board();
        System.out.println(testBoard.getSquares(0,0));
        System.out.println(testBoard.getSquares(2,2));
        System.out.println(testBoard.getSquares(8,8));
        System.out.println(testBoard.getSquares(9,9));
        System.out.println(testBoard.getSquares(11,11));
    }
}
