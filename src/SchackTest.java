/**
 * Created by axelo225 and simho765 on 07/03/16.
 */
public class SchackTest {
    public static void main(String[] args) {
        Board testBoard = new Board();
	testBoard.createNewBoard();
	ChessFrame testFrame = new ChessFrame(testBoard);



	StringBuilder builder = new StringBuilder();
	for (int row = 0; row < 8; row++) {
	    for (int col = 0; col < 8; col++) {
		if (testBoard.getPiece(row, col) != null) {
		    builder.append(testBoard.getPiece(row, col).getColor());
		}
		else {
		    builder.append("Empty");
		}
	    }
	    builder.append("\n");

	}
	System.out.println(builder.toString());

    }
}
