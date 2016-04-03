/**
 * Created by axelo225 and simho765 on 07/03/16.
 */
public class Rook extends Piece{
    public PieceColor color;

    public PieceColor getColor() {
	return color;
    }

    public Rook(PieceColor color) {
	this.color = color;
    }

    @Override
    public PieceType getPieceType(){return PieceType.Rook;}

    @Override public boolean validateMove(int y, int x, int newY, int newX, Board board) {
	int xDiff = newX -x;
	int yDiff = newY -y;

	if (!super.validateMove(y,x, newY, newX, board)){
	    return false;
	}
	else if (board.isFriendly(newY, newX, color)){
	    return false;
	}
	else if (xDiff > 0 && yDiff == 0){
	    return !isPieceInPathRight(y, x, newX, board);
	}
	else if (xDiff < 0 && yDiff == 0){
	    return !isPieceInPathLeft(y, x, newX, board);
	}
	else if (yDiff > 0 && xDiff == 0){
	    return !isPieceInPathDown(y, x, newY, board);
	}
	else if (yDiff < 0 && xDiff == 0){
	    return !isPieceInPathUp(y, x, newY, board);
	}
	return false;
    }

}
