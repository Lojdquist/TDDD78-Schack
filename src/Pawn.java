/**
 * Created by axelo225 and simho765 on 07/03/16.
 */
public class Pawn implements Piece {

    public String color;

    public String getColor() {
	return color ;
    }

    public Pawn(String color) {
	this.color = color;
    }

    @Override
    public PieceType getPieceType(){return PieceType.Pawn;}

    @Override public boolean validateMove(int y, int x, int newY, int newX) {
	if (color == "white"){
	    return y-newY == 1;
	}
	else {
	    return newY-y == 1;
	}
    }
}
