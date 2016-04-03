/**
 * Created by axelo225 and simho765 on 07/03/16.
 */
public class Knight extends Piece{
    public PieceColor color;

    public PieceColor getColor() {
	return color;
    }

    public Knight(PieceColor color) {
	this.color = color;
    }

    @Override
    public PieceType getPieceType(){return PieceType.Knight;}

    @Override public boolean validateMove(int y, int x, int newY, int newX, Board board) {
	int diffX = Math.abs(x - newX);
	int diffY = Math.abs(y - newY);

	if (!super.validateMove(y, x, newY, newX, board)){
	    return false;
	}
	else if (board.isFriendly(newY, newX, color)){
	    return false;
	}
	else if ((diffY == 2 && diffX == 1) || (diffY == 1 && diffX == 2)) {
	    return true;
	}
	return false;
    }
}
