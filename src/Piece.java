/**
 * Created by axelo225 and simho765 on 07/03/16.
 */
public interface Piece {

    public abstract String getColor();

    public abstract PieceType getPieceType();

    public abstract boolean validateMove(int y, int x, int newY, int newX);

}
