import javafx.scene.layout.GridPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChessFrame extends JFrame implements MouseListener, MouseMotionListener{
    private Board board;
    public final static int SQUARE_WIDTH =80;
    private final static int BOARD_WIDTH = 8;
    private int xAdjustment;
    private int yAdjustment;
    private JLayeredPane layeredPane;
    private JPanel chessBoard;
    private JLabel chessPiece;
    private Dimension boardSize = new Dimension(SQUARE_WIDTH*BOARD_WIDTH, SQUARE_WIDTH*BOARD_WIDTH);

    public ChessFrame(Board board){
	super("Chess");
	this.board = board;

	layeredPane = new JLayeredPane();
	getContentPane().add(layeredPane);
	layeredPane.setPreferredSize(boardSize);
	layeredPane.addMouseMotionListener(this);
	layeredPane.addMouseListener(this);

	chessBoard = new JPanel();
	layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
	chessBoard.setLayout(new GridLayout(8, 8));
	chessBoard.setPreferredSize(boardSize);
	chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);

	createMenus();
	drawBoard();
	drawPieces();


	this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	this.pack();
	this.setVisible(true);


    }

    private void createMenus(){
	final JMenuBar menuBar = new JMenuBar();
	final JMenu file = new JMenu("File");
	final JMenuItem quit = new JMenuItem("Quit");

	file.add(quit);
	menuBar.add(file);
	this.setJMenuBar(menuBar);
    }

    private void drawBoard() {
	for (int row = 0; row < BOARD_WIDTH; row++) {
	    for (int col = 0; col < BOARD_WIDTH; col++) {
		JPanel square = new JPanel(new BorderLayout());
		chessBoard.add(square);
		if (row % 2 == 0) {
		    if (col % 2 == 0) {
			square.setBackground(Color.WHITE);
		    } else {
			square.setBackground(Color.BLACK);
		    }
		} else {
		    if (col % 2 == 0) {
			square.setBackground(Color.BLACK);
		    } else {
			square.setBackground(Color.WHITE);
		    }
		}
	    }

	}
    }


    private void drawPieces(){

	for (int row = 0; row < BOARD_WIDTH; row++) {
	    for (int col = 0; col < BOARD_WIDTH; col++) {
		JPanel panel = (JPanel) chessBoard.getComponent(row*BOARD_WIDTH + col);
		if (board.getPiece(row,col) != null) {
		    PieceType pieceType = board.getPiece(row, col).getPieceType();
		    String color = board.getPiece(row, col).getColor();
		    if (pieceType == PieceType.Pawn) {
			addPanel("P " + color, panel);
		    }
		    else if (pieceType == PieceType.Rook){
			addPanel("R " + color, panel);
		    }
		    else if (pieceType == PieceType.Knight){
			addPanel("Kn " + color, panel);
		    }
		    else if (pieceType == PieceType.Queen){
			addPanel("Q " + color, panel);
		    }
		    else if (pieceType == PieceType.King){
			addPanel("K " + color, panel);
		    }
		    else if (pieceType == PieceType.Bishop){
			addPanel("Bi " + color, panel);
		    }
		}
	    }
	}
    }

    private void addPanel(String piecetype, JPanel panel){
	JLabel label = new JLabel(piecetype);
	panel.add(label);
    }

    @Override public void mouseClicked(final MouseEvent e) {
    }

    @Override public void mousePressed(final MouseEvent e) {
	chessPiece = null;
	Component c =  chessBoard.findComponentAt(e.getX(), e.getY());

	if (c instanceof JPanel) return;

	Point parentLocation = c.getParent().getLocation();
	System.out.println(c.getParent().getLocation());

	xAdjustment = parentLocation.x - e.getX();
	yAdjustment = parentLocation.y - e.getY();

	chessPiece = (JLabel) c;
	chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
	chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
	layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);

    }

    @Override public void mouseReleased(final MouseEvent e) {

	if (chessPiece == null) return;

	chessPiece.setVisible(false);
	Component c =  chessBoard.findComponentAt(e.getX(), e.getY());

 	if (c instanceof JLabel) {
	    Container parent = c.getParent();
	    parent.remove(0);
	    parent.add( chessPiece );
 	}
	else {
	    Container parent = (Container)c;
	    parent.add( chessPiece );
 	}

 	chessPiece.setVisible(true);

    }

    @Override public void mouseEntered(final MouseEvent e) {

    }

    @Override public void mouseExited(final MouseEvent e) {

    }

    @Override public void mouseDragged(final MouseEvent e) {
	if (chessPiece == null) return;
	chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);

    }

    @Override public void mouseMoved(final MouseEvent e) {

    }
}
