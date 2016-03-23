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
}
