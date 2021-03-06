/**
 * Created by axelo225 and simho765 on 07/03/16.
 */
public class King extends Piece{
    public PieceColor color;

    public PieceColor getColor() {
        return color;
    }

    public King(PieceColor color) {
        this.color = color;
    }

    @Override
    public PieceType getPieceType(){return PieceType.King;}

    @Override public boolean validateMove(int y, int x, int newY, int newX, Board board) {
        if (!super.validateMove(y, x, newY, newX, board)){
            return false;
        }
        else if (board.isFriendly(newY, newX, color)){
            return false;
        }
        return (y - 1 <= newY && newY <= y + 1) && ( x-1<= newX && newX <= x+1);
    }
}
