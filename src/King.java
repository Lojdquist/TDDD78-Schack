/**
 * Created by axelo225 and simho765 on 07/03/16.
 */
public class King extends Piece{
    public String color;

    public String getColor() {
        return color;
    }

    public King(String color) {
        this.color = color;
    }

    @Override
    public PieceType getPieceType(){return PieceType.King;}

    @Override public boolean validateMove(int y, int x, int newY, int newX, Board board) {
        if ( x-1 < newX && newX < x+1){
            if (y-1 < newY && newY < y+1){
                return true;
            }
            return false;
        }
        return false;
    }
}
