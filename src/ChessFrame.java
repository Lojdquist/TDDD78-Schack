import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.*;

public class ChessFrame extends JFrame implements MouseListener, MouseMotionListener{
    private Board board;
    private final static int BOARD_WIDTH = 8;
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public final int SQUARE_WIDTH = Integer.parseInt(Math.round(screenSize.getHeight() / 10) + "");
    private int xAdjustment;
    private int yAdjustment;
    private JLayeredPane layeredPane;
    private JPanel chessBoard;
    private JLabel chessPiece = null;
    private Dimension boardSize = new Dimension(SQUARE_WIDTH*BOARD_WIDTH, SQUARE_WIDTH*BOARD_WIDTH);
    private Point oldPosition = null;
    private PieceColor pieceColor;
    private JPanel[][] possibleMoves;

    public ChessFrame(Board board){
	super("Chess");
	this.board = board;
	possibleMoves = new JPanel[8][8];

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
		    PieceColor color = board.getPiece(row, col).getColor();
		    addPiece(pieceType, panel, color);

		}
	    }
	}
    }

    private void addPiece(PieceType pieceType, JPanel panel, PieceColor color){
	JLabel label = null;

	if (pieceType == PieceType.Pawn) {
	    ImageIcon icon = new ImageIcon("src/icons/knight_" + color + ".png");
	    label = new JLabel("P " + color);
	    label.setIcon(icon);
	}
	else if (pieceType == PieceType.Rook){
	    ImageIcon icon = new ImageIcon("src/icons/knight_" + color + ".png");
	    label = new JLabel("R " + color);
	}
	else if (pieceType == PieceType.Knight){
	    ImageIcon icon = new ImageIcon("src/icons/knight_" + color + ".png");
	    label = new JLabel();
	    label.setIcon(icon);
	    label.setIcon(icon);

	}
	else if (pieceType == PieceType.Queen){
	    ImageIcon icon = new ImageIcon("src/icons/knight_" + color + ".png");
	    label = new JLabel("Q " + color);
	    label.setIcon(icon);
	}
	else if (pieceType == PieceType.King){
	    ImageIcon icon = new ImageIcon("src/icons/knight_" + color + ".png");
	    label = new JLabel("K " + color);
	    label.setIcon(icon);
	}
	else if (pieceType == PieceType.Bishop){
	    ImageIcon icon = new ImageIcon("src/icons/knight_" + color + ".png");
	    label = new JLabel("Bi " + color);
	    label.setIcon(icon);
	}
	panel.add(label);
    }


    @Override public void mousePressed(final MouseEvent e) {
	chessPiece = null;
	pieceColor = null;
	oldPosition = null;

	Component c =  chessBoard.findComponentAt(e.getX(), e.getY());

	if (c instanceof JPanel) return;

	oldPosition = c.getParent().getLocation();
	pieceColor = board.getPiece(oldPosition.y / SQUARE_WIDTH, oldPosition.x / SQUARE_WIDTH).getColor();

	if (board.getPlayerTurn() != pieceColor) return;

	xAdjustment = oldPosition.x - e.getX();
	yAdjustment = oldPosition.y - e.getY();

	chessPiece = (JLabel) c;
	chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);

	chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
	layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);

    }

    @Override public void mouseReleased(final MouseEvent e) {

	if (chessPiece == null) return;

	for (int i = 0; i < layeredPane.getComponentsInLayer(JLayeredPane.DRAG_LAYER).length ; i++) {
	    layeredPane.getComponent(i).setVisible(false);
	}
	chessPiece.setVisible(false);
	Component c =  chessBoard.findComponentAt(e.getX(), e.getY());

	int y = oldPosition.y/SQUARE_WIDTH;
	int x = oldPosition.x/SQUARE_WIDTH;
	int newY = e.getY()/SQUARE_WIDTH;
	int newX = e.getX()/SQUARE_WIDTH;

 	if (c instanceof JLabel) {

	    if (!board.hasMovedPiece(y, x, newY, newX)){
		Component oldc = chessBoard.findComponentAt(oldPosition);
		PieceType oldPieceType = board.getPiece(y, x).getPieceType();
		addPiece(oldPieceType, (JPanel) oldc, pieceColor);

	    }

	    else {
		Container parent = c.getParent();
		parent.remove(0);
		parent.add(chessPiece);
		chessPiece.setVisible(true);
		board.changeTurn();
		if (board.isCheckmate()){
		    System.out.println("Checkmate " + pieceColor + " wins!");
		}
	    }
 	}
	else if (!board.hasMovedPiece(y, x, newY, newX)){
	    Component oldc = chessBoard.findComponentAt(oldPosition);
	    PieceType oldPieceType = board.getPiece(y, x).getPieceType();

	    addPiece(oldPieceType, (JPanel) oldc, pieceColor);
	}
	else {
	    Container parent = (Container) c;
	    parent.add(chessPiece);
	    chessPiece.setVisible(true);
	    board.changeTurn();
	    if (board.isCheckmate()){
		System.out.println("Checkmate " + pieceColor + " wins!");
	    }

 	}
	//board.printBoard();
    }

    @Override public void mouseDragged(final MouseEvent e) {
	if (chessPiece == null) return;
	chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
	Piece currentPiece = board.getPiece(oldPosition.y/SQUARE_WIDTH, oldPosition.x/SQUARE_WIDTH);

	for (int row = 0; row < BOARD_WIDTH; row++) {
	    for (int col = 0; col < BOARD_WIDTH; col++) {
		if (currentPiece.validateMove(oldPosition.y/SQUARE_WIDTH, oldPosition.x/SQUARE_WIDTH, row, col, board)){
		    if (!board.isOpponent(row, col, pieceColor)) {
			JPanel square = new JPanel(new BorderLayout());
			Color possibleMoves;

			//chessBoard.add(square).setLocation(row*SQUARE_WIDTH, col*SQUARE_WIDTH);
			square.setLocation(col * SQUARE_WIDTH, row * SQUARE_WIDTH);
			square.setSize(SQUARE_WIDTH, SQUARE_WIDTH);
			square.setBackground(Color.BLUE);
			square.setVisible(true);
			layeredPane.add(square, JLayeredPane.DRAG_LAYER);
		    }
		}
	    }
	}

    }

    @Override public void mouseEntered(final MouseEvent e) {

    }

    @Override public void mouseExited(final MouseEvent e) {

    }

    @Override public void mouseMoved(final MouseEvent e) {

    }

    @Override public void mouseClicked(final MouseEvent e) {
    }
}
