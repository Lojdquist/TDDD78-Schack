/**
 * Created by axelo225 on 07/03/16.
 */
public class Board {
    public Piece[][] board;
    private final static int HEIGTH = 8;
    private final static int WIDTH = 8;

    public Board() {
        for (int row = 0; row < HEIGTH; row++) {
            for (int col = 0; col < WIDTH ; col++) {
                board[row][col] = null;
            }
        }
    }

    public void createNewBoard(){
        placeKings();

    }

    public void placeKings(){
        board[3][3] = new King("white");
        board[8][8] = new King("black");
    }

}
