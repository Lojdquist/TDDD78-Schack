/**
 * Created by axelo225 and simho765 on 07/03/16.
 */
public class King implements Piece{
    public String color;

    public String getColor() {
        return color;
    }

    public King(String color) {
        this.color = color;
    }

    @Override
    public PieceType getPieceType(){return PieceType.King;}
}
