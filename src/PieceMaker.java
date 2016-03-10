/**
 * Created by axelo225 on 07/03/16.
 */
public class PieceMaker {
    public Piece createPiece(SquareType type, SquareType color){
        return new Piece(color,type);
    }
}
