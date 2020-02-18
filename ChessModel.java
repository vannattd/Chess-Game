import javax.swing.*;
import java.util.ArrayList;

/**********************************************************************
 * This class creates a model or the game for the chess game
 *
 * @author Zack Frent, Dylan Vannatter, and Youssef Shalaby
 * @version Winter 2019
 *********************************************************************/
public class ChessModel implements IChessModel {

    /** board represents the playing board for the chess game */
    private IChessPiece[][] board;

    /** player represents the user that will be playing the game */
    private Player player;

    /**
     * Boards represents an arraylist of boards created
     */
    ArrayList<IChessPiece[][]> Boards;


    /******************************************************************
     * Constructor that makes a default model for the chess game
     *
     *****************************************************************/
    public ChessModel() {
        board = new IChessPiece[8][8];
        player = Player.WHITE;
        Boards = new ArrayList<>();


        board[7][0] = new Rook(Player.WHITE);
        board[7][1] = new Knight(Player.WHITE);
        board[7][2] = new Bishop(Player.WHITE);
        board[7][3] = new Queen(Player.WHITE);
        board[7][4] = new King(Player.WHITE);
        board[7][5] = new Bishop(Player.WHITE);
        board[7][6] = new Knight(Player.WHITE);
        board[7][7] = new Rook(Player.WHITE);

        board[6][0] = new Pawn(Player.WHITE);
        board[6][1] = new Pawn(Player.WHITE);
        board[6][2] = new Pawn(Player.WHITE);
        board[6][3] = new Pawn(Player.WHITE);
        board[6][4] = new Pawn(Player.WHITE);
        board[6][5] = new Pawn(Player.WHITE);
        board[6][6] = new Pawn(Player.WHITE);
        board[6][7] = new Pawn(Player.WHITE);

//		player = Player.BLACK;

        board[0][0] = new Rook(Player.BLACK);
        board[0][1] = new Knight(Player.BLACK);
        board[0][2] = new Bishop(Player.BLACK);
        board[0][3] = new Queen(Player.BLACK);
        board[0][4] = new King(Player.BLACK);
        board[0][5] = new Bishop(Player.BLACK);
        board[0][6] = new Knight(Player.BLACK);
        board[0][7] = new Rook(Player.BLACK);

        board[1][0] = new Pawn(Player.BLACK);
        board[1][1] = new Pawn(Player.BLACK);
        board[1][2] = new Pawn(Player.BLACK);
        board[1][3] = new Pawn(Player.BLACK);
        board[1][4] = new Pawn(Player.BLACK);
        board[1][5] = new Pawn(Player.BLACK);
        board[1][6] = new Pawn(Player.BLACK);
        board[1][7] = new Pawn(Player.BLACK);


    }

    /******************************************************************
     * Method that checks to see if it is complete
     *
     * @return true returns true if it is complete and false if it
     * not complete
     *****************************************************************/
    public boolean isComplete() {
        Move changing;
        Move m = new Move();
        Player p = currentPlayer();
        IChessPiece saving;

        int frow = 0, fcolumn = 0;
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {

                if (board[i][j] != null && board[i][j].player() == p) {
                    frow = i;
                    fcolumn = j;


                    for (int x = 0; x < 8; x++)
                        for (int y = 0; y < 8; y++) {
                            m.setValues(frow, fcolumn, x, y);
                            if (isValidMove(m)) {
                                saving = board[x][y];

                                move(m);
                                if (!inCheck(p)) {
                                    changing = new Move
                                            (m.toRow, m.toColumn, m.fromRow,
                                                    m.fromColumn);
                                    System.out.println("In Check");
                                    move(changing);
                                    board[x][y] = saving;
                                    return false;
                                }
                                changing = new Move
                                        (m.toRow, m.toColumn, m.fromRow,
                                                m.fromColumn);
                                move(changing);
                                board[x][y] = saving;
                            }
                        }

                }
            }
        return true;
    }


    /******************************************************************
     * Method that sets the parameter as the board
     *
     * @param newBoard represents the newBoard the user is setting
     *****************************************************************/
    public void setBoard(IChessPiece[][] newBoard) {
        board = newBoard;
    }

    /******************************************************************
     * Method that saves the playing board
     *
     *****************************************************************/
    public void saveBoard() {
        IChessPiece[][] temp = new IChessPiece[8][8];
        for (int i = 0; i < numRows(); i++)
            for (int j = 0; j < numColumns(); j++) {
                if (board[i][j] instanceof King) {
                    temp[i][j] = new King(board[i][j].player());
                } else if (board[i][j] instanceof Knight) {
                    temp[i][j] = new Knight(board[i][j].player());
                } else if (board[i][j] instanceof Rook) {
                    temp[i][j] = new Rook(board[i][j].player());
                } else if (board[i][j] instanceof Pawn) {
                    temp[i][j] = new Pawn(board[i][j].player());
                } else if (board[i][j] instanceof Bishop) {
                    temp[i][j] = new Bishop(board[i][j].player());
                } else if (board[i][j] instanceof Queen) {
                    temp[i][j] = new Queen(board[i][j].player());
                } else
                    temp[i][j] = null;
            }
        Boards.add(temp);
    }


    /******************************************************************
     * Method that gets the specific boards
     *
     * @return Boards returns the arraylist of boards that we have
     *****************************************************************/
    public ArrayList<IChessPiece[][]> getBoards() {
        return Boards;
    }


    /******************************************************************
     * Method that checks to see if the piece that user is trying to
     * move is a vaild move
     *
     * @param move the spot the user would like to move their piece to
     * @return valid true if you can move there and false if you can
     * not move to the desired spot.
     *****************************************************************/
    public boolean isValidMove(Move move) {
        boolean valid = false;

        if (board[move.fromRow][move.fromColumn] != null)
            if (board[move.fromRow][move.fromColumn].isValidMove
                    (move, board) == true)
                valid = true;

        return valid;
    }

    /******************************************************************
     * Method that moves the selected chess piece
     *
     * @param move the spot the user would like to move their piece to
     *****************************************************************/
    public void move(Move move) {
        board[move.toRow][move.toColumn] =
                board[move.fromRow][move.fromColumn];
        board[move.fromRow][move.fromColumn] = null;
    }


    /******************************************************************
     * Method that checks to see if the user is in check
     *
     * @param player the player that is in check or not in check
     * @return vaild true if the player is in check and false if the
     * player is not in check
     *****************************************************************/
    //need to override valid moves in each piece
    public boolean inCheck(Player player) {

        int kingXb = 0;
        int kingYb = 0;


        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {

                if (board[i][j] instanceof King && board[i][j].player()
                        == player) {
                    kingXb = i;
                    kingYb = j;

                }

            }

        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {

                if (board[i][j] instanceof Pawn) {
                    Move kingcheck = new Move(i, j, kingXb, kingYb);
                    if ((board[i][j]).isValidMove(kingcheck, board)) {
                        return true;
                    }
                }


                if (board[i][j] instanceof Rook) {
                    Move kingcheck = new Move(i, j, kingXb, kingYb);

                    if ((board[i][j]).isValidMove(kingcheck, board)) {
                        return true;
                    }
                }
                if (board[i][j] instanceof Queen) {
                    Move kingcheck = new Move(i, j, kingXb, kingYb);
                    if ((board[i][j]).isValidMove(kingcheck, board)) {
                        return true;
                    }
                }
                if (board[i][j] instanceof Bishop) {

                    Move kingcheck = new Move(i, j, kingXb, kingYb);

                    if ((board[i][j]).isValidMove(kingcheck, board)) {

                        return true;
                    }
                }
                if (board[i][j] instanceof Knight) {
                    Move kingcheck = new Move(i, j, kingXb, kingYb);

                    if ((board[i][j]).isValidMove(kingcheck, board)) {
                        return true;
                    }
                }

            }


        return false;
    }


    /******************************************************************
     * Method that checks who the current player is
     *
     * @return player the current player of the game
     *****************************************************************/
    public Player currentPlayer() {
        return player;
    }

    /******************************************************************
     * Method that checks to see how many numbers of rows there are
     *
     * @return 8 the number of rows in the game
     *****************************************************************/
    public int numRows() {
        return 8;
    }

    /******************************************************************
     * Method that checks to see how many numbers of columns there are
     *
     * @return 8 the number of columns in the game
     *****************************************************************/
    public int numColumns() {
        return 8;
    }

    /******************************************************************
     * Method that checks where a certain piece is at on the game board
     *
     * @param row the specific row of the chess piece
     * @param column the specific column of the chess piece
     * @return board[row][column] which is the specific location of
     * the chess piece
     *****************************************************************/
    public IChessPiece pieceAt(int row, int column) {
        return board[row][column];
    }

    /******************************************************************
     * Method that sets the next player as the next person to play
     *
     *****************************************************************/
    public void setNextPlayer() {
        player = player.next();
    }

    /******************************************************************
     * Method that sets a certain piece at a specific location on the
     * chess board
     *
     * @param row the specific row where you want the chess piece
     * @param column the specific column you want the chess piece
     *****************************************************************/
    public void setPiece(int row, int column, IChessPiece piece) {
        board[row][column] = piece;
    }

    /******************************************************************
     * Method that creates an AI to play against the user
     *
     *****************************************************************/
    public void AI() {
        for (int r = 0; r < 8; r++)
            for (int c = 0; c < 8; c++) {
                if (inCheck(Player.BLACK)) {
                    if (board[r][c] instanceof King && board[r][c].player()
                            == Player.BLACK) {
                        for (int i = 0; i < 8; i++)
                            for (int j = 0; j < 8; j++) {
                                Move attemptMove = new Move(r, c, i, j);
                                if (new King(Player.BLACK).isValidMove
                                        (attemptMove, board)) {
                                    move(attemptMove);
                                }
                            }
                    }
                }
            }
        for (int r = 0; r < 8; r++)
            for (int c = 0; c < 8; c++) {
                if (board[r][c] != null) {
                    if (board[r][c].player() == Player.BLACK) {
                        for (int i = 0; i < 8; i++)
                            for (int j = 0; j < 8; j++) {
                                Move attemptMove = new Move(r, c, i, j);
                                Move reverseMove = new Move(i, j, r, c);
                                if (board[r][c].isValidMove(attemptMove, board) &&
                                        inCheck(Player.WHITE)) {
                                    move(attemptMove);
                                }
                            }
                    }
                }
            }
        for (int r = 0; r < 8; r++)
            for (int c = 0; c < 8; c++)
                for (int r2 = 0; r2 < 8; r2++)
                    for (int c2 = 0; c2 < 8; c2++) {
                        if (board[r][c] != null && board[r2][c2] != null) {
                            if (board[r][c].player() == Player.BLACK &&
                                    board[r2][c2].player() == Player.WHITE) {
                                for (int i = 0; i < 8; i++)
                                    for (int j = 0; j < 8; j++) {
                                        Move possibleKill = new Move(r2, c2, r, c);
                                        Move reverseMove = new Move(r, c, i, j);
                                        if (board[r2][c2].isValidMove(possibleKill,
                                                board) && board[r][c].isValidMove
                                                (reverseMove, board)) {
                                            move(reverseMove);
                                        }
                                    }
                            }
                        }
                    }
        for (int r = 0; r < 8; r++)
            for (int c = 0; c < 8; c++)
                for (int r2 = 0; r2 < 8; r2++)
                    for (int c2 = 0; c2 < 8; c2++) {
                        if (board[r][c] != null && board[r2][c2] != null) {
                            if (board[r][c].player() == Player.BLACK &&
                                    board[r2][c2].player() == Player.WHITE) {
                                for (int i = 0; i < 8; i++)
                                    for (int j = 0; j < 8; j++) {
                                        Move possibleKill = new Move(r, c, r2, c2);
                                        if (board[r][c].isValidMove
                                                (possibleKill, board)) {
                                            move(possibleKill);
                                        }
                                    }
                            }
                        }
                    }
        for (int r = 0; r < 8; r++)
            for (int c = 0; c < 8; c++) {
                if (board[r][c] != null) {
                    if (board[r][c].player() == Player.BLACK) {
                        for (int i = 0; i < 8; i++)
                            for (int j = 0; j < 8; j++) {
                                Move possibleMove = new Move(r, c, i, j);
                                if (board[r][c] instanceof Pawn &&
                                        board[r][c].isValidMove(possibleMove,
                                                board)) {
                                    move(possibleMove);
                                    return;
                                }
                                if (board[r][c] instanceof Queen &&
                                        board[r][c].isValidMove(possibleMove,
                                                board)) {
                                    move(possibleMove);
                                    return;
                                }
                                if (board[r][c] instanceof Rook &&
                                        board[r][c].isValidMove(possibleMove,
                                                board)) {
                                    move(possibleMove);
                                    return;
                                }
                                if (board[r][c] instanceof Bishop &&
                                        board[r][c].isValidMove(possibleMove,
                                                board)) {
                                    move(possibleMove);
                                    return;
                                }
                                if (board[r][c] instanceof Knight &&
                                        board[r][c].isValidMove(possibleMove,
                                                board)) {
                                    move(possibleMove);
                                    return;
                                }


                            }
                    }
                }
            }

        Move Attack;
        for (int r = 0; r < 8; r++)
            for (int c = 0; c < 8; c++) {
                if ((board[r][c] != null)
                        && (board[r][c].player() == Player.BLACK)) {
                    for (int i = 0; i < 8; i++)
                        for (int j = 0; j < 8; j++) {
                            if (board[r][c] != null) {
                                if (board[r][c].player() == Player.WHITE)
                                    if (board[r][c].isValidMove(Attack = new
                                            Move(r, c, i, j), board)) {
                                        move(Attack);
                                    }
                            }
                        }
                }
            }


    }
}


