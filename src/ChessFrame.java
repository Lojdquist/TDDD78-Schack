import javax.swing.*;
import java.awt.*;

public class ChessFrame extends JFrame {
    private Board board;
    public ChessComponent chessComponent;

    public ChessFrame(Board board){
	super("Chess");
	this.board = board;
	this.chessComponent = new ChessComponent(this.board);

	createMenus();

	this.setLayout(new BorderLayout());
	this.add(chessComponent, BorderLayout.CENTER);
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
}
