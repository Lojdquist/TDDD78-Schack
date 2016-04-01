/**
 * Created by axelo225 and simho765 on 07/03/16.
 */
public abstract class Piece {

    public abstract String getColor();

    public abstract PieceType getPieceType();

    public boolean validateMove(int y, int x, int newY, int newX, Board board){
        if (newY < 0 || newY > 7 || newX < 0 || newX > 7){
            return false;
        }
        return true;
    }


}
