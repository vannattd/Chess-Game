/**********************************************************************
 * This abstract Class represents a chess piece on the gameboard
 *
 * @author Zack Frent, Dylan Vannatter, and Youssef Shalaby
 * @version Winter 2019
 *********************************************************************/
public abstract class ChessPiece implements IChessPiece {

    /** owner represents the user that has that specific chess piece */
    private Player owner;

    /******************************************************************
     *  A protected Method that sets the param player to the owner
     *
     *  @param player the player that owns the chess piece
     *****************************************************************/
    protected ChessPiece(Player player) {
        this.owner = player;
    }

    /******************************************************************
     *  An abstract Method that shows what type of piece it is
     *
     *****************************************************************/
    public abstract String type();

    /******************************************************************
     *  A method that returns the specific player or owner of the piece
     *
     * @return owner the player that owns the chess piece
     *****************************************************************/
    public Player player() {
        return owner;
    }

    /******************************************************************
     *  A Method that checks to see if what you click is a vaild move
     *  for the specific chess piece
     *
     * @param move the move that the user is trying to make
     * @param board the place on the board that the piece is
     * is trying to move to
     * @return true if the piece is allowed to move to that spot
     *
     *****************************************************************/
    public boolean isValidMove(Move move, IChessPiece[][] board) {
        boolean valid = false;
        if (board[move.toRow][move.toColumn] != null) {
            if (move.fromRow != move.toRow || move.fromColumn
                    != move.toColumn)
                if (board[move.toRow][move.toColumn].player() != owner)
                    if (board[move.fromRow][move.fromColumn].player()
                            == owner)
                        valid = true;
        }
        else
        if (move.fromRow != move.toRow ||
                move.fromColumn != move.toColumn)
            valid = true;
        return valid;
    }
}
