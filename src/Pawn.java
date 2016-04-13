/**
 * Created by axelo225 and simho765 on 07/03/16.
 */
public class Pawn extends Piece {

    private boolean hasMoved;
    public PieceColor color;
    private final static int WHITEPAWNROW = 1;
    private final static int BLACKPAWNROW = 6;

    public PieceColor getColor() {
	return color ;
    }

    public Pawn(PieceColor color) {
	this.color = color;
	hasMoved = false;
    }

    @Override
    public PieceType getPieceType(){return PieceType.Pawn;}

    @Override public boolean validateMove(int y, int x, int newY, int newX, Board board) {
	if (!super.validateMove(y, x, newY, newX, board)) {
	    return false;
	}

	if (color == PieceColor.WHITE) {
	    //if can take one or two steps forward, in starting position
	    if (newY - y == 2 && newX == x && board.getPiece(newY - 1, newX) == null && y == WHITEPAWNROW) {
		return true;
	    }
	    //if can take an opponent, has left starting position
	    else if ((newY - y == 1 && (newX == x + 1 || newX == x - 1)) && board.isOpponent(newY, newX, color)) {
		return true;
	    }
	    //if can move one step, has left starting position
	    return newY - y == 1 && newX == x && board.getPiece(newY, newX) == null;
	}
	else {//Black

	    //if can take one or two steps forward, in starting position
	    if (y - newY == 2 && newX == x && board.getPiece(newY + 1, x) == null && y == BLACKPAWNROW) {
		return true;
	    }
	    //if can take opponent, left starting position
	    else if (y - newY == 1 && (newX == x + 1 || newX == x - 1) && board.isOpponent(newY, newX, color)) {
		return true;
	    }
	    //if can move one step forward, left starting position
	    return y - newY == 1 && newX == x && board.getPiece(newY, newX) == null;
	}
    }
}
