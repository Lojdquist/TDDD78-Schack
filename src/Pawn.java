/**
 * Created by axelo225 and simho765 on 07/03/16.
 */
public class Pawn extends Piece {

    private boolean hasMoved;
    public PieceColor color;

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
	if (!super.validateMove(y, x, newY, newX, board)){
	    return false;
	}

	if (color == PieceColor.WHITE){
	    if (!hasMoved) {
		if (((newY - y == 2 && newX == x && board.getPiece(newY - 1, newX) == null) || (newY - y == 1 && newX == x)) && board.getPiece(newY, newX) == null){
		    hasMoved = true;
		}
		else if (newY - y == 1 && (newX == x + 1 || newX == x-1) && board.isOpponent(newY, newX, color)){
		    hasMoved = true;
		    return true;
		}
		return ((newY - y == 2 && newX == x && board.getPiece(newY - 1, newX) == null) || (newY - y == 1 && newX == x)) && board.getPiece(newY, newX) == null ;
	    }
	    else if ((newY - y == 1 && (newX == x + 1 || newX == x-1)) && board.isOpponent(newY, newX, color)){
		return true;
	    }
	    return newY - y == 1 && newX == x && board.getPiece(newY, newX) == null;
	}

	else {
	    if (!hasMoved){
		if (((y - newY == 2 && newX == x && board.getPiece(newY +1,newX) == null) || (y - newY == 1 && newX == x)) && board.getPiece(newY, newX) == null){
		    hasMoved = true;
		}
		else if ((y - newY == 1 && (newX == x + 1 || newX == x-1)) && board.isOpponent(newY, newX, color)){
		    hasMoved = true;
		    return true;
		}
		return ((y - newY == 2 && newX == x && board.getPiece(newY +1,newX) == null) || (y - newY == 1 && newX == x)) && board.getPiece(newY, newX) == null;
	    }
	    else if (y - newY == 1 && (newX == x + 1 || newX == x-1) && board.isOpponent(newY, newX, color)){
		return true;
	    }
	    return y - newY == 1 && newX == x && board.getPiece(newY, newX) == null;
	}
    }
}
