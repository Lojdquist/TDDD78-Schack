/**
 * Created by axelo225 on 07/03/16.
 */
public class Board {
    public Piece[][] board;
    private final static int HEIGTH = 8;
    private final static int WIDTH = 8;

    public Board() {
        board = new Piece[8][8];
    }

    public void createNewBoard(){

        for (int row = 0; row < HEIGTH; row++) {
            for (int col = 0; col < WIDTH ; col++) {
                board[row][col] = null;
            }
        }

        placeKings();
        placePawns();
	placeQueens();
    }

    public void placeKings(){
        board[0][3] = new King("white");
        board[7][3] = new King("black");
    }

    public void placePawns(){
        for (int col = 0; col < HEIGTH ; col++) {
            board[1][col] = new Pawn("white");
        }

        for (int col = 0; col < HEIGTH; col++) {
            board[6][col] = new Pawn("black");

        }
    }

    public void placeQueens(){
	board[0][4] = new Queen("white");
	board[7][4] = new Queen("black");
    }

    public Piece getPiece(int y, int x) {
        return board[y][x];
    }
}
