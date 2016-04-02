/**
 * Created by axelo225 and simho765 on 07/03/16.
 */
public class Queen extends Piece {
    public String color;

    public String getColor() {
	return color;
    }

    public Queen(String color) {
	this.color = color;
    }

    @Override
    public PieceType getPieceType(){return PieceType.Queen;}

    @Override public boolean validateMove(int y, int x, int newY, int newX, Board board) {
	if (!super.validateMove(y, x, newY, newX, board)){
	    return false;
	}
	if (newX - newY == x -y){
	    return true;
	}
	else if (Math.abs(newX - x) == Math.abs(newY - y)){
	    return true;
	}
	return (newX == x && newY != y) || (newY == y && newX != x);
    }

}
