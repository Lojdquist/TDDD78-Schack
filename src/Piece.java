/**
 * Created by axelo225 and simho765 on 07/03/16.
 */
public abstract class Piece {
    public abstract PieceColor getColor();
    public abstract PieceType getPieceType();
    public boolean hasMoved = false;

    public boolean validateMove(int y, int x, int newY, int newX, Board board){
	if (newY < 0 || newY > 7 || newX < 0 || newX > 7 || newY == y && newX == x){
	    return false;
	}
	return true;
    }

    public boolean isPieceInPathDown(int y, int x, int newY, Board board){
	for (int i = 1; i < newY - y ; i++) {
	    if (board.getPiece(y +i, x) != null){
		return true;
	    }
	}
	return false;
    }

    public boolean isPieceInPathUp(int y, int x, int newY, Board board){
	for (int i = 1; i < y - newY ; i++) {
	    if (board.getPiece(y -i , x) != null){
		return true;
	    }
	}
	return false;
    }

    public boolean isPieceInPathLeft(int y, int x, int newX, Board board){
	for (int i = 1; i < x- newX ; i++) {
	    if (board.getPiece(y, x -i) != null){
		return true;
	    }
	}
	return false;
    }

    public boolean isPieceInPathRight(int y, int x, int newX, Board board){
	for (int i = 1; i < newX - x; i++) {
	    if (board.getPiece(y, x+i) != null){
		return true;
	    }
	}
	return false;
    }

    public boolean isPieceInPathDownRight(int y, int x, int newY, Board board){
	for (int i = 1; i < newY -y ; i++) {
	    if (board.getPiece(y + i, x +i) != null){
		return true;
	    }

	}
	return false;
    }

    public boolean isPieceInPathDownLeft(int y, int x, int newY, Board board){
	for (int i = 1; i < newY -y ; i++) {
	    if (board.getPiece(y +i, x-i) != null){
		return true;
	    }
	}
	return false;
    }

    public  boolean isPieceInPathUpRight(int y, int x, int newY, Board board){
	for (int i = 1; i < y- newY; i++) {
	    if (board.getPiece(y - i, x +i) != null){
		return true;
	    }
	}
	return false;
    }

    public boolean isPieceInPathUpLeft(int y, int x, int newY, Board board){
	for (int i = 1; i < y- newY ; i++) {
	    if (board.getPiece(y-i, x-i) != null){
		return true;
	    }
	}
	return false;
    }

}
