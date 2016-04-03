/**
 * Created by axelo225 and simho765 on 07/03/16.
 */
public class Board {
    public Piece[][] board;
    private final static int HEIGTH = 8;
    private final static int WIDTH = 8;
    private PieceColor playerTurn;

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

	playerTurn = PieceColor.WHITE;
    }

    public void placeKings(){
        board[0][3] = new King(PieceColor.WHITE);
        board[7][3] = new King(PieceColor.BLACK);
    }

    public void placePawns(){
        for (int col = 0; col < HEIGTH ; col++) {
            board[1][col] = new Pawn(PieceColor.WHITE);
        }

        for (int col = 0; col < HEIGTH; col++) {
            board[6][col] = new Pawn(PieceColor.BLACK);

        }
    }

    public void placeQueens(){
        board[0][4] = new Queen(PieceColor.WHITE);
        board[7][4] = new Queen(PieceColor.BLACK);
    }

    public void placeRooks(){
	board[0][0] = new Rook(PieceColor.WHITE);
	board[0][7] = new Rook(PieceColor.WHITE);

	board[7][0] = new Rook(PieceColor.BLACK);
	board[7][7] = new Rook(PieceColor.BLACK);
    }

    public void placeKnights(){
	board[0][1] = new Knight(PieceColor.WHITE);
	board[0][6] = new Knight(PieceColor.WHITE);

	board[7][1] = new Knight(PieceColor.BLACK);
	board[7][6] = new Knight(PieceColor.BLACK);
    }

    public void placeBishops(){
	board[0][2] = new Bishop(PieceColor.WHITE);
	board[0][5] = new Bishop(PieceColor.WHITE);

	board[7][2] = new Bishop(PieceColor.BLACK);
	board[7][5] = new Bishop(PieceColor.BLACK);
    }

    public Piece getPiece(int y, int x) {
	return board[y][x];
    }

    public boolean isFriendly(int y, int x, PieceColor color) {
	if (board[y][x] != null){
	    return board[y][x].getColor() == color;
	}
	return false;
    }

    public boolean isOpponent(int y, int x, PieceColor color){
	if (board[y][x] != null) {
	    return board[y][x].getColor() != color;
	}
	return false;
    }

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
		    builder.append(board[row][col].getPieceType() + board[row][col].getColor().toString());
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

