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
			square.setBackground(Color.GRAY);
		    }
		} else {
		    if (col % 2 == 0) {
			square.setBackground(Color.GRAY);
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

    private void drawPossibleMoves(){
	Piece currentPiece = board.getPiece(oldPosition.y/SQUARE_WIDTH, oldPosition.x/SQUARE_WIDTH);
	for (int row = 0; row < BOARD_WIDTH; row++) {
	    for (int col = 0; col < BOARD_WIDTH; col++) {
		if (currentPiece.validateMove(oldPosition.y/SQUARE_WIDTH, oldPosition.x/SQUARE_WIDTH, row, col, board)){
		    if (!board.isStillCheck(oldPosition.y/SQUARE_WIDTH, oldPosition.x/SQUARE_WIDTH, row, col)) {
			JPanel square = new JPanel(new BorderLayout());

			square.setLocation(col * SQUARE_WIDTH, row * SQUARE_WIDTH);
			square.setSize(SQUARE_WIDTH, SQUARE_WIDTH);
			square.setBackground(new Color(250, 85, 85, 100));
			square.setVisible(true);
			layeredPane.add(square, JLayeredPane.DRAG_LAYER);
		    }
		}
	    }
	}
    }

    private ImageIcon resizeIcons(ImageIcon icon){
	Image img = icon.getImage();
	Image newimg = img.getScaledInstance(SQUARE_WIDTH, SQUARE_WIDTH,  Image.SCALE_SMOOTH);
	return new ImageIcon(newimg);
    }

    private void addPiece(PieceType pieceType, JPanel panel, PieceColor color){
	JLabel label = null;

	if (pieceType == PieceType.Pawn) {
	    ImageIcon icon = new ImageIcon("src/icons/knight_" + color + ".png");
	    label = new JLabel("P " + color);
	    //label.setIcon(icon);
	}
	else if (pieceType == PieceType.Rook){
	    ImageIcon icon = new ImageIcon("src/icons/knight_" + color + ".png");
	    label = new JLabel("R " + color);
	}
	else if (pieceType == PieceType.Knight){
	    ImageIcon icon = new ImageIcon("src/icons/knight_" + color + ".png");
	    label = new JLabel();
	    label.setIcon(resizeIcons(icon));
	}
	else if (pieceType == PieceType.Queen){
	    ImageIcon icon = new ImageIcon("src/icons/knight_" + color + ".png");
	    label = new JLabel("Q " + color);
	    //label.setIcon(icon);
	}
	else if (pieceType == PieceType.King){
	    ImageIcon icon = new ImageIcon("src/icons/knight_" + color + ".png");
	    label = new JLabel("K " + color);
	    //label.setIcon(icon);
	}
	else if (pieceType == PieceType.Bishop){
	    //ImageIcon icon = new ImageIcon("src/icons/knight_" + color + ".png");
	    label = new JLabel("Bi " + color);
	    //label.setIcon(icon);
	}
	panel.add(label);
    }

    private void paintCastling(Component c, boolean leftCastling){
	int kingNewX;
	int rookNewX;
	
	if (leftCastling){
	    kingNewX = 1;
	    rookNewX = 2;
	}
	else { //Right Castling
	    kingNewX = 5;
	    rookNewX = 4;
	}
	
	if (pieceColor == PieceColor.WHITE) {
	    Component kingsNewPos = chessBoard.findComponentAt(kingNewX*SQUARE_WIDTH, 0);
	    Component rookNewPos = chessBoard.findComponentAt(rookNewX*SQUARE_WIDTH, 0);

	    addPiece(PieceType.King, (JPanel) kingsNewPos, pieceColor);

	    addPiece(PieceType.Rook, (JPanel) rookNewPos, pieceColor);

	    c.getParent().remove(0);

	}
	else{
	    Component kingsNewPos = chessBoard.findComponentAt(kingNewX*SQUARE_WIDTH, 7*SQUARE_WIDTH);
	    Component rookNewPos = chessBoard.findComponentAt(rookNewX*SQUARE_WIDTH, 7*SQUARE_WIDTH);

	    addPiece(PieceType.King, (JPanel) kingsNewPos, pieceColor);
	    addPiece(PieceType.Rook, (JPanel) rookNewPos, pieceColor);

	    c.getParent().remove(0);
	}
    }

    private void hidePossibleMoves(){
	for (int i = 0; i < layeredPane.getComponentsInLayer(JLayeredPane.DRAG_LAYER).length ; i++) {
	    layeredPane.getComponent(i).setVisible(false);
	}
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
	drawPossibleMoves();

    }

    @Override public void mouseReleased(final MouseEvent e) {

	if (chessPiece == null) return;

	hidePossibleMoves();
	chessPiece.setVisible(false);
	Component c =  chessBoard.findComponentAt(e.getX(), e.getY());

	int y = oldPosition.y/SQUARE_WIDTH;
	int x = oldPosition.x/SQUARE_WIDTH;
	int newY = e.getY()/SQUARE_WIDTH;
	int newX = e.getX()/SQUARE_WIDTH;
	boolean leftCastling = false;

	if (board.isCastling(y, x, newY, newX)){
	    if (board.tryLeftCastling()){
		leftCastling = true;
		paintCastling(c, leftCastling);
		board.changeTurn();
		return;
	    }
	    else if (board.tryRightCastling()){
		paintCastling(c, leftCastling);
		board.changeTurn();
		return;
	    }
	}

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
		else if (board.isStalemate()){
		    System.out.println("Stalemate draw!");
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
	    else if (board.isStalemate()){
		System.out.println("Stalemate draw!");
	    }

 	}

    }

    @Override public void mouseDragged(final MouseEvent e) {
	if (chessPiece == null) return;
	chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);


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
