
/**********************************************************************
 * This Class represents the rook chess piece on the chess board
 *
 * @author Zack Frent, Dylan Vannatter, and Youssef Shalaby
 * @version Winter 2019
 *********************************************************************/
public class Rook extends ChessPiece {

    /******************************************************************
     * Constructor that sets the player using the rook chess piece
     *
     * @param player player that is playing the chess game
     *****************************************************************/
    public Rook(Player player) {
        super(player);
    }

    /******************************************************************
     *  A Method that returns the type of piece it is, which is Rook
     *
     *  @return "Rook" for the type of chess piece that is
     *  being used to move
     *****************************************************************/
    public String type() {
        return "Rook";
    }

    /******************************************************************
     *  A Method that checks to see if what you click is a valid move
     *  for the rook chess piece
     *
     * @param move the move that the user is trying to make
     * @param board the place on the board that the piece is
     * is trying to move to
     * @return true if the rook is allowed to move to that spot
     * @return false if the rook is not allowed to move to that spot
     *****************************************************************/
    @Override
    // determines if the move is valid for a rook piece
    public boolean isValidMove(Move move, IChessPiece[][] board) {
        if (super.isValidMove(move, board)) {
            // Horizontal move
            if (move.fromRow == move.toRow && ( move.fromColumn <
                    move.toColumn)) {
                // Move right
                for (int i = move.fromColumn + 1; i <
                        move.toColumn; i++)
                    if (board[move.fromRow][i] != null)
                        return false;
                return true;
            }
            // Move left
            else if (move.fromRow == move.toRow && (
                    move.fromColumn > move.toColumn)){
                for (int i = move.fromColumn - 1; i
                        > move.toColumn; i--)
                    if (board[move.fromRow][i] != null)
                        return false;
                return true;
            }
            // Vertical move
            else if (move.fromColumn == move.toColumn &&
                    (move.fromRow < move.toRow)) {
                // Move down
                for (int i = move.fromRow + 1; i <
                        move.toRow; ++i)
                    if (board[i][move.fromColumn] != null)
                        return false;
                return true;
            }
            else if (move.fromColumn == move.toColumn &&
                    (move.fromRow > move.toRow)){
                // Move up
                for (int i = move.fromRow - 1; i >
                        move.toRow; --i)
                    if (board[i][move.fromColumn] != null)
                        return false;
                return true;
            }

        }
        return false;
    }
}




