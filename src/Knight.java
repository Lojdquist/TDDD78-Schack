/**
 * Created by axelo225 and simho765 on 07/03/16.
 */
public class Knight implements Piece{
    public String color;

    public String getColor() {
	return color;
    }

    public Knight(String color) {
	this.color = color;
    }

    @Override
    public PieceType getPieceType(){return PieceType.Knight;}

    @Override public boolean validateMove(int y, int x, int newY, int newX) {
	return false;
    }
}
