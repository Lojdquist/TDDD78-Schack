import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class ChessComponent extends JComponent implements MouseListener, MouseMotionListener  {
    private Board board;
    public final static int SQUARE_WIDTH =50;
    private final static int BOARD_SIZE = 8;
    private JLayeredPane layeredPane;
    private JPanel chessBoard;
    private JLabel chesspiece;
    private Dimension dimension = new Dimension(SQUARE_WIDTH*BOARD_SIZE, SQUARE_WIDTH*BOARD_SIZE);

    /*

   ---------------------------
   ---------------------------
   ---------------------------
    Denna klass använder vi inte
   ---------------------------
   ---------------------------
   ---------------------------
    
     */


    public ChessComponent(Board board) {
	this.board = board;
	setPreferredSize(dimension);

	layeredPane = new JLayeredPane();
	layeredPane.addMouseListener(this);
	layeredPane.addMouseMotionListener(this);
	layeredPane.setPreferredSize(dimension);

	chessBoard = new JPanel();
	layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
	chessBoard.setLayout(new GridLayout(8, 8));
	chessBoard.setPreferredSize(dimension);
	chessBoard.setBounds(0, 0, dimension.width, dimension.height);


    }

    @Override protected void paintComponent(final Graphics g) {
	super.paintComponent(g);
	final Graphics2D g2d = (Graphics2D) g;

	for (int row = 0; row < BOARD_SIZE; row++) {
		    for (int col = 0; col < BOARD_SIZE; col++) {
			JPanel square = new JPanel(new BorderLayout());
			square.setVisible(true);
			chessBoard.add(square);
			if (row % 2 == 0){
			    if (col % 2 == 0){
				square.setBackground(Color.WHITE);
			    }
			    else {
				square.setBackground(Color.BLACK);
			    }
			}
			else {
			    if (col % 2 == 0){
				square.setBackground(Color.BLACK);
			    }
			    else{
				square.setBackground(Color.WHITE);
			    }
			}

		    }

		}
    }

    /*

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

    */

    @Override public void mouseClicked(final MouseEvent e) {

    }

    @Override public void mousePressed(final MouseEvent e) {


    }

    @Override public void mouseReleased(final MouseEvent e) {

    }

    @Override public void mouseEntered(final MouseEvent e) {

    }

    @Override public void mouseExited(final MouseEvent e) {

    }

    @Override public void mouseDragged(final MouseEvent e) {

    }

    @Override public void mouseMoved(final MouseEvent e) {

    }
}
