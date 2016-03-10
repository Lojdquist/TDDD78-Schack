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
		g2d.drawRect(col*SQUARE_WIDTH, row*SQUARE_WIDTH, SQUARE_WIDTH, SQUARE_WIDTH);
		g2d.fillRect(col*SQUARE_WIDTH, row*SQUARE_WIDTH, SQUARE_WIDTH, SQUARE_WIDTH);
		if (row % 2 == 0){
		    if (col % 2 == 0){
			g2d.setColor(Color.BLACK);
		    }
		    else {
			g2d.setColor(Color.WHITE);
		    }
		}else {
		    if (col % 2 == 0){
			g2d.setColor(Color.WHITE);
		    }
		    else{
			g2d.setColor(Color.BLACK);
		    }
		}

	    }

	}

    }
}
