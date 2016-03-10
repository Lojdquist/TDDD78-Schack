/**
 * Created by axelo225 and simho765 on 07/03/16.
 */
public class Knight extends Piece{
    public String color;

    public String getColor() {
	return color;
    }

    public Knight(String color) {
	this.color = color;
    }

    @Override
    public PieceType getPieceType(){return PieceType.Knight;}
}
