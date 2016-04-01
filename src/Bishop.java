import java.util.Map;

/**
 * Created by axelo225 and simho765 on 07/03/16.
 */
public class Bishop extends Piece{
    public String color;

    public String getColor() {
	return color;
    }

    public Bishop(String color) {
	this.color = color;
    }

    @Override
    public PieceType getPieceType(){return PieceType.Bishop;}

    @Override
    public boolean validateMove(int y, int x, int newY, int newX, Board board) {
	if (newX - newY == x -y){
	    return true;
	}
	else if (Math.abs(newX - x) == Math.abs(newY - y)){
	    return true;
	}
	return false;
    }
}
