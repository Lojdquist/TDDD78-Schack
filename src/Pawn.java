/**
 * Created by axelo225 and simho765 on 07/03/16.
 */
public class Pawn extends Piece {

    private boolean hasMoved;
    public String color;

    public String getColor() {
	return color ;
    }

    public Pawn(String color) {
	this.color = color;
	hasMoved = false;
    }

    @Override
    public PieceType getPieceType(){return PieceType.Pawn;}

    @Override public boolean validateMove(int y, int x, int newY, int newX, Board board) {
	if (color == "white"){
	    if (!hasMoved) {
		if ((newY -y == 2 && newX == x) || (newY - y == 1 && newX == x)){
		    hasMoved = true;
		}
		return (newY - y == 2 && newX == x) || (newY - y == 1 && newX == x) ;
	    }
	    return newY - y == 1 && newX == x;
	}
	else {
	    if (!hasMoved){
		if ((y - newY == 2 && newX == x) || (y - newY == 1 && newX == x)){
		    hasMoved = true;
		}
		return (y - newY == 2 && newX == x) || (y - newY == 1 && newX == x) ;
	    }
	    return y - newY == 1 && newX == x;
	}
    }
}
