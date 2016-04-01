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
	placeKnights();
	placeBishops();
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

    public void placeRooks(){
	board[0][0] = new Rook("white");
	board[0][7] = new Rook("white");

	board[7][0] = new Rook("black");
	board[7][7] = new Rook("black");
    }

    public void placeKnights(){
	board[0][1] = new Knight("white");
	board[0][6] = new Knight("white");

	board[7][1] = new Knight("black");
	board[7][6] = new Knight("black");
    }

    public void placeBishops(){
	board[0][2] = new Bishop("white");
	board[0][5] = new Bishop("white");

	board[7][2] = new Bishop("black");
	board[7][5] = new Bishop("black");
    }

    public Piece getPiece(int y, int x) {
	return board[y][x];
    }

    public boolean isFriendly(int y, int x, String color) {
	return board[y][x].getColor() == color;
    }

    public boolean isOpponent(int y, int x, String color){return board[y][x].getColor() != color;}

    public void movePiece(int y, int x, int newY, int newX){
	Piece piece = board[y][x];
	board[y][x] = null;
	board[newY][newX] = piece;
    }

    public void printBoard(){
	StringBuilder builder = new StringBuilder();
	for (int row = 0; row < 8; row++) {
	    for (int col = 0; col < 8; col++) {
		if (board[row][col] != null) {
		    builder.append(board[row][col].getPieceType() + board[row][col].getColor());
		}
		else {
		    builder.append("Empty");
		}
	    }
	    builder.append("\n");

	}
	System.out.println(builder);
    }
}

