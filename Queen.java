/**********************************************************************
 * This Class represents the queen chess piece on the chess board
 *
 * @author Zack Frent, Dylan Vannatter, and Youssef Shalaby
 * @version Winter 2019
 *********************************************************************/
public class Queen extends ChessPiece {

    /******************************************************************
     * Constructor that sets the player using the queen chess piece
     *
     * @param player player that is playing the chess game
     *****************************************************************/
    public Queen(Player player) {
        super(player);

    }

    /******************************************************************
     *  A Method that returns the type of piece it is, which is King
     *
     *  @return "Queen" for the type of chess piece that is
     *  being used to move
     *****************************************************************/
    public String type() {
        return "Queen";

    }

    /******************************************************************
     *  A Method that checks to see if what you click is a vaild move
     *  for the queen chess piece
     *
     * @param move the move that the user is trying to make
     * @param board the place on the board that the piece is
     * is trying to move to
     * @return true if the queen is allowed to move to that spot
     * @return false if the queen is not allowed to move to that spot
     *****************************************************************/
    public boolean isValidMove(Move move, IChessPiece[][] board) {
        if (super.isValidMove(move, board)) {
            Bishop move1 = new Bishop(board[move.fromRow]
                    [move.fromColumn].player());
            Rook move2 = new Rook(board[move.fromRow]
                    [move.fromColumn].player());
            return (move1.isValidMove(move, board) ||
                    move2.isValidMove(move, board));
        }
        return false;
    }
}

