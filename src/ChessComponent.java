import javafx.scene.shape.*;

import javax.swing.*;
import java.awt.*;


public class ChessComponent extends JComponent {
    private Board board;
    public final static int SQUARE_WIDTH =50;
    private final static int BOARD_SIZE = 8;

    public ChessComponent(Board board) {
	this.board = board;
	setPreferredSize(new Dimension(SQUARE_WIDTH*BOARD_SIZE, SQUARE_WIDTH*BOARD_SIZE));
    }

    @Override protected void paintComponent(final Graphics g) {
	super.paintComponent(g);
	final Graphics2D g2d = (Graphics2D) g;

	for (int row = 0; row < BOARD_SIZE; row++) {
	    for (int col = 0; col < BOARD_SIZE; col++) {
		if (row % 2 == 0){
		    if (col % 2 == 0){
			g2d.setColor(Color.WHITE);
		    }
		    else {
			g2d.setColor(Color.BLACK);
		    }
		}else {
		    if (col % 2 == 0){
			g2d.setColor(Color.BLACK);
		    }
		    else{
			g2d.setColor(Color.WHITE);
		    }
		}
		g2d.drawRect(col*SQUARE_WIDTH, row*SQUARE_WIDTH, SQUARE_WIDTH, SQUARE_WIDTH);
		g2d.fillRect(col*SQUARE_WIDTH, row*SQUARE_WIDTH, SQUARE_WIDTH, SQUARE_WIDTH);

	    }

	}
	drawPieces(g2d);

    }

    public void drawPieces(Graphics2D g2d){
	g2d.setFont(new Font("Monospaced", Font.PLAIN, 40));
	g2d.setColor(Color.GRAY);

	for (int row = 0; row < BOARD_SIZE; row++) {
	    for (int col = 0; col < BOARD_SIZE; col++) {
		if (board.getPiece(row, col) != null){
		    if (board.getPiece(row, col).getPieceType() == PieceType.Bishop){
			g2d.drawString("BI",col*SQUARE_WIDTH, row*SQUARE_WIDTH + SQUARE_WIDTH);
		    }
		    else if (board.getPiece(row,col).getPieceType() == PieceType.King){
			g2d.drawString("K", col*SQUARE_WIDTH, row*SQUARE_WIDTH + SQUARE_WIDTH);
		    }
		    else if (board.getPiece(row, col).getPieceType() == PieceType.Pawn){
			g2d.drawString("P", col*SQUARE_WIDTH, row*SQUARE_WIDTH + SQUARE_WIDTH);
		    }
		    else if (board.getPiece(row, col).getPieceType() == PieceType.Knight){
			g2d.drawString("Kn", col*SQUARE_WIDTH, row*SQUARE_WIDTH + SQUARE_WIDTH);
		    }
		    else if (board.getPiece(row, col).getPieceType() == PieceType.Queen){
			g2d.drawString("Q", col*SQUARE_WIDTH, row*SQUARE_WIDTH + SQUARE_WIDTH);
		    }
		    else{
			g2d.drawString("R", col*SQUARE_WIDTH, row*SQUARE_WIDTH + SQUARE_WIDTH);
		    }
		}
	    }

	}
    }

}
