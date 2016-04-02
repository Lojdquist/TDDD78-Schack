/**
 * Created by axelo225 and simho765 on 07/03/16.
 */
public class Rook extends Piece{
    public String color;

    public String getColor() {
	return color;
    }

    public Rook(String color) {
	this.color = color;
    }

    @Override
    public PieceType getPieceType(){return PieceType.Rook;}

    @Override public boolean validateMove(int y, int x, int newY, int newX, Board board) {
	if (!super.validateMove(y,x, newY, newX, board)){
	    return false;
	}
	return (newX == x && newY != y) || (newY == y && newX != x);
    }
}
