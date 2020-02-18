
/**********************************************************************
 * This Class represents the Knight chess piece on the chess board
 *
 * @author Zack Frent, Dylan Vannatter, and Youssef Shalaby
 * @version Winter 2019
 *********************************************************************/
public class Knight extends ChessPiece {

    /******************************************************************
     * Constructor that sets the player using the Knight chess piece
     *
     * @param player player that is playing the chess game
     *****************************************************************/
    public Knight(Player player) {
        super(player);
    }

    /******************************************************************
     *  A Method that returns the type of piece it is, which is Knight
     *
     *  @return "Knight" for the type of chess piece that is
     *  being used to move
     *****************************************************************/
    public String type() {
        return "Knight";
    }

    /******************************************************************
     *  A Method that checks to see if what you click is a valid move
     *  for the knight chess piece
     *
     * @param move the move that the user is trying to make
     * @param board the place on the board that the piece is
     * is trying to move to
     * @return valid if the knight is allowed to move to that spot or
     * if it is not allowed to move to the spot
     *****************************************************************/
    @Override
    public boolean isValidMove(Move move, IChessPiece[][] board) {
        boolean valid = false;
        if (super.isValidMove(move, board))
            if((move.fromColumn + 1 == move.toColumn ||
                    move.fromColumn - 1 == move.toColumn)
                    && (move.fromRow + 2 == move.toRow ||
                    move.fromRow - 2 == move.toRow)
                    || (move.fromColumn + 2 == move.toColumn||
                    move.fromColumn -2 == move.toColumn)  &&
                    (move.fromRow + 1 == move.toRow||
                            move.fromRow - 1 == move.toRow))
                valid = true;

        return valid;
    }

}
