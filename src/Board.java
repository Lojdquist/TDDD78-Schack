import java.awt.*;

/**
 * Created by axelo225 and simho765 on 07/03/16.
 */
public class Board {
    public Piece[][] board;
    private final static int HEIGTH = 8;
    private final static int WIDTH = 8;
    private PieceColor playerTurn;
    private Point KingPos = null;
    private Point checkingPiece = null;

    public Board() {
        board = new Piece[8][8];
    }

    public void createNewBoard(){

        for (int row = 0; row < HEIGTH; row++) {
            for (int col = 0; col < WIDTH ; col++) {
                board[row][col] = null;
            }
        }

        placeKings();
        placePawns();
        placeQueens();
	placeRooks();
	placeKnights();
	placeBishops();

	playerTurn = PieceColor.WHITE;
    }

    public void placeKings(){
        board[0][3] = new King(PieceColor.WHITE);
        board[7][3] = new King(PieceColor.BLACK);
    }

    public void placePawns(){
        for (int col = 0; col < HEIGTH ; col++) {
            board[1][col] = new Pawn(PieceColor.WHITE);
        }

        for (int col = 0; col < HEIGTH; col++) {
            board[6][col] = new Pawn(PieceColor.BLACK);

        }
    }

    public void placeQueens(){
        board[0][4] = new Queen(PieceColor.WHITE);
        board[7][4] = new Queen(PieceColor.BLACK);
    }

    public void placeRooks(){
	board[0][0] = new Rook(PieceColor.WHITE);
	board[0][7] = new Rook(PieceColor.WHITE);

	board[7][0] = new Rook(PieceColor.BLACK);
	board[7][7] = new Rook(PieceColor.BLACK);
    }

    public void placeKnights(){
	board[0][1] = new Knight(PieceColor.WHITE);
	board[0][6] = new Knight(PieceColor.WHITE);

	board[7][1] = new Knight(PieceColor.BLACK);
	board[7][6] = new Knight(PieceColor.BLACK);
    }

    public void placeBishops(){
	board[0][2] = new Bishop(PieceColor.WHITE);
	board[0][5] = new Bishop(PieceColor.WHITE);

	board[7][2] = new Bishop(PieceColor.BLACK);
	board[7][5] = new Bishop(PieceColor.BLACK);
    }

    public Piece getPiece(int y, int x) {
	return board[y][x];
    }

    public PieceColor getPlayerTurn() {
	return playerTurn;
    }

    public boolean isFriendly(int y, int x, PieceColor color) {
	if (board[y][x] != null){
	    return board[y][x].getColor() == color;
	}
	return false;
    }

    public boolean isOpponent(int y, int x, PieceColor color){
	if (board[y][x] != null) {
	    return board[y][x].getColor() != color;
	}
	return false;
    }

    public void movePiece(int y, int x, int newY, int newX){
	Piece piece = board[y][x];
	board[y][x] = null;
	board[newY][newX] = piece;
    }

    public boolean hasMovedPiece(int y, int x, int newY, int newX){
	Piece currentPiece = board[y][x];

	if (currentPiece.validateMove(y, x, newY, newX, this)) {
	    Piece savedSquare = board[newY][newX];
	    movePiece(y, x, newY, newX);

	    if (isCheck()) {
		board[newY][newX] = savedSquare;
		board[y][x] = currentPiece;
		return false;
	    }
	    else {
		board[newY][newX].hasMoved = false;
		return true;
	    }
	}

	return false;
    }

    public void printBoard(){
	StringBuilder builder = new StringBuilder();
	for (int row = 0; row < 8; row++) {
	    for (int col = 0; col < 8; col++) {
		if (board[row][col] != null) {
		    builder.append(board[row][col].getPieceType()).append(board[row][col].getColor());
		}
		else {
		    builder.append("Empty");
		}
	    }
	    builder.append("\n");

	}
	System.out.println(builder);
    }

    public void changeTurn(){
	if (playerTurn == PieceColor.BLACK){
	    playerTurn = PieceColor.WHITE;
	 }
	 else {
	    playerTurn = PieceColor.BLACK;
 	}
    }

    public void findKing(){
	for (int row = 0; row < HEIGTH; row++) {
	    for (int col = 0; col < WIDTH; col++) {
		if (board[row][col] != null) {
		    if (board[row][col].getPieceType() == PieceType.King && board[row][col].getColor() == playerTurn) {
			KingPos = new Point(col, row);
		    }
		}
	    }
	}


    }

    public boolean isCheck(){
	findKing();

	for (int row = 0; row < HEIGTH ; row++) {
	    for (int col = 0; col < WIDTH; col++) {
		if (board[row][col] != null) {
		    if (playerTurn != board[row][col].getColor()) {
			if (board[row][col].validateMove(row, col, KingPos.y, KingPos.x, this)) {
			    System.out.println(playerTurn + "king is in check by" + row + " " + col + " " +  board[row][col].getPieceType());
			    System.out.println("king position " + KingPos.y + " " + KingPos.x);
			    checkingPiece = new Point(col, row);
			    return true;
			}
		    }
		}
	    }
	}
	return false;
    }

    public boolean isCheckmate(){

	if (isCheck()){
	    if (!canMoveKing()){
		if (!canTakeCheckingPiece()){
		    if (!canBlockCheckingPiece()){
			return true;
		    }
		}

	    }
	}
	return false;
    }

    public boolean canMoveKing(){
	Piece king = board[KingPos.y][KingPos.x];
	Point kingPosition = KingPos;

	for (int i = -1; i < 2; i++) {
	    for (int j = -1; j < 2; j++) {
		if (king.validateMove(kingPosition.y, kingPosition.x, kingPosition.y +i, kingPosition.x + j, this)){
		    Piece savedSquare = board[kingPosition.y + i][kingPosition.x +j];

		    movePiece(kingPosition.y, kingPosition.x, kingPosition.y +i, kingPosition.x + j);

		    if (isCheck()){
			movePiece(kingPosition.y +i, kingPosition.x + j, kingPosition.y, kingPosition.x);
			board[kingPosition.y +i][kingPosition.x + j] = savedSquare;
		    }
		    else {
			movePiece(kingPosition.y +i, kingPosition.x + j, kingPosition.y, kingPosition.x);
			board[kingPosition.y +i][kingPosition.x + j] = savedSquare;
			return true;
		    }
		}
	    }
	}
	return false;
    }

    public boolean canTakeCheckingPiece(){

	for (int row = 0; row < HEIGTH; row++) {
	    for (int col = 0; col < WIDTH; col++) {
		if (isFriendly(row, col, playerTurn)){
		    if (board[row][col].validateMove(row, col, checkingPiece.y, checkingPiece.x, this )){
			if (!isStillCheck(row, col, checkingPiece.y, checkingPiece.x)){
			    return true;

		    	}
		    }
	    	}
	    }
	}

	return false;
    }

    public boolean canBlockCheckingPiece(){

	for (int row = 0; row < HEIGTH; row++) {
	    for (int col = 0; col < WIDTH; col++) {
		if (board[checkingPiece.y][checkingPiece.x].validateMove(checkingPiece.y, checkingPiece.x, row, col, this)){
		    if (canPieceBlockChess(row, col)){
			return true;
		    }
		}
	    }
	}
	return false;
    }

    public boolean canPieceBlockChess(int blockingY, int blockingX){

	for (int row = 0; row < HEIGTH; row++) {
	    for (int col = 0; col < WIDTH; col++) {
		if (isFriendly(row, col, playerTurn)){
		    if (board[row][col].validateMove(row, col, blockingY, blockingX, this)){
			if (!isStillCheck(row, col, blockingY, blockingX)){
			    return true;
			}
		    }
		}
	    }
	}
	return false;
    }

    public boolean isStillCheck(int y, int x, int newY, int newX){
	Piece opponentPiece = board[newY][newX];

 	movePiece(y, x, newY, newX);
 	if (isCheck()){
	    movePiece(newY, newX, y, x);
	    board[newY][newX] = opponentPiece;
	    return true;
 	}
 	else{
	    movePiece(newY, newX, y, x);
	    board[newY][newX] = opponentPiece;
	    return false;
 	}
    }

    public boolean tryLeftCastling(){

	if (playerTurn == PieceColor.WHITE) {
	    if (board[0][0].getPieceType() == PieceType.Rook && board[0][1] == null &&
		board[0][2] == null && board[0][3].getPieceType() == PieceType.King) {
		if (!isCheck() && !isStillCheck(0, 3, 0, 1) && !board[0][0].hasMoved && !board[0][3].hasMoved) {
		    doLeftCastling();
		    return true;
		}
	    }
	}
	else {
	    if (board[7][0].getPieceType() == PieceType.Rook && board[7][1] == null &&
		board[7][2] == null && board[7][3].getPieceType() == PieceType.King) {
		if (!isCheck() && !isStillCheck(7, 3, 7, 1) && !board[7][0].hasMoved && !board[7][3].hasMoved) {
		    doLeftCastling();
		    return true;
		}
	    }
	}
	return false;
    }

    public boolean tryRightCastling(){

	if (playerTurn == PieceColor.WHITE) {
	    if (board[0][7].getPieceType() == PieceType.Rook && board[0][6] == null &&
		board[0][5] == null && board[0][4] == null && board[0][3].getPieceType() == PieceType.King) {
		if (!isCheck() && !isStillCheck(0, 3, 0, 5) && !board[0][7].hasMoved && !board[0][3].hasMoved) {
		    doRightCastling();
		    return true;
		}
	    }
	}
	else{
	    if (board[7][7].getPieceType() == PieceType.Rook && board[7][6] == null &&
		board[7][5] == null && board[7][4] == null &&  board[7][3].getPieceType() == PieceType.King){
		if (!isCheck() && !isStillCheck(7,3,7,5) && !board[7][7].hasMoved && !board[7][3].hasMoved) {
		    doRightCastling();
		    return true;
		    }
		}
	    }
	return false;
    }

    public boolean isCastling(int y, int x, int newY, int newX){
	if (y == newY && x == newX){
	    return false;
	}
	else if (isFriendly(newY, newX, playerTurn) && (board[y][x].getPieceType() == PieceType.King
		&& board[newY][newX].getPieceType() ==PieceType.Rook)){
	    return true;
	}
	return false;
    }

    private void doLeftCastling(){
	if (playerTurn == PieceColor.WHITE){
	    board[0][2] = board[0][0];
	    board[0][0] = null;
	    board[0][1] = board[0][3];
	    board[0][3] = null;

	    board[0][1].hasMoved = true;
	    board[0][2].hasMoved = true;
	}
	else {
	    board[7][2] = board[7][0];
	    board[7][0] = null;
	    board[7][1] = board[7][3];
	    board[7][3] = null;

	    board[7][2].hasMoved = true;
	    board[7][1].hasMoved = true;
	}
    }

    private void doRightCastling(){
	if (playerTurn == PieceColor.WHITE){
	    board[0][4] = board[0][7];
	    board[0][7] = null;
	    board[0][5] = board[0][3];
	    board[0][3] = null;

	    board[0][4].hasMoved = true;
	    board[0][5].hasMoved = true;
	}
	else {
	    board[7][4] = board[7][7];
	    board[7][7] = null;
	    board[7][5] = board[7][3];
	    board[7][3] = null;

	    board[7][4].hasMoved = true;
	    board[7][5].hasMoved = true;
	}
    }

}



