/**
 * Created by axelo225 and simho765 on 07/03/16.
 */
public class Bishop extends Piece{
    public String color;

    public String getColor() {
	return color;
    }

    public Bishop(String color) {
	this.color = color;
    }

    @Override
    public PieceType getPieceType(){return PieceType.Bishop;}

    @Override
    public boolean validateMove(int y, int x, int newY, int newX, Board board) {
	int xDiff = newX -x;
	int yDiff = newY -y;

	if (!super.validateMove(y, x, newY, newX, board)){
	    return false;
	}
	else if (board.isFriendly(newY, newX, color)){
	    return false;
	}
	else if (newX - newY == x -y){
	    if (xDiff > 0 && yDiff > 0){
		return !isPieceInPathDownRight(y, x, newY, board);
	    }
	    else return !isPieceInPathUpLeft(y, x, newY, board);
	}
	else if (Math.abs(newX - x) == Math.abs(newY - y)){
	    if (xDiff > 0 && yDiff < 0){
		return !isPieceInPathUpRight(y, x, newY, board);
	    }
	    else return !isPieceInPathDownLeft(y, x, newY, board);
	}
	return false;
    }

}
