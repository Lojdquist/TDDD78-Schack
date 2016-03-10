/**
 * Created by axelo225 and simho765 on 07/03/16.
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
	placeRooks();
	placeKngiht();
	placeBishops();
    }

    public void placeKings(){
        board[0][3] = new King("Kwhite/");
        board[7][3] = new King("Kblack/");
    }

    public void placePawns(){
        for (int col = 0; col < HEIGTH ; col++) {
            board[1][col] = new Pawn("Pwhite/");
        }

        for (int col = 0; col < HEIGTH; col++) {
            board[6][col] = new Pawn("Pblack/");

        }
    }

    public void placeQueens(){
        board[0][4] = new Queen("Qwhite/");
        board[7][4] = new Queen("Qblack/");
    }

    public void placeRooks(){
	board[0][0] = new Rook("Rwhite/");
	board[0][7] = new Rook("Rwhite/");

	board[7][0] = new Rook("Rblack/");
	board[7][7] = new Rook("Rblack/");
    }

    public void placeKngiht(){
	board[0][1] = new Knight("KNwhite/");
	board[0][6] = new Knight("KNwhite/");

	board[7][1] = new Knight("KNblack/");
	board[7][6] = new Knight("KNblack/");
    }

    public void placeBishops(){
	board[0][2] = new Bishop("BIwhite/");
	board[0][5] = new Bishop("BIwhite/");

	board[7][2] = new Bishop("BIblack/");
	board[7][5] = new Bishop("BIblack/");
    }

    public Piece getPiece(int y, int x) {
        return board[y][x];
    }
}
