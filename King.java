
/**********************************************************************
 * This Class represents the king chess piece on the chess board
 *
 * @author Zack Frent, Dylan Vannatter, and Youssef Shalaby
 * @version Winter 2019
 *********************************************************************/
public class King extends ChessPiece {

    /******************************************************************
     * Constructor that sets the player using the King chess piece
     *
     * @param player player that is playing the chess game
     *****************************************************************/
    public King(Player player) {
        super(player);
    }

    /******************************************************************
     *  A Method that returns the type of piece it is, which is King
     *
     *  @return "King" for the type of chess piece that is
     *  being used to move
     *****************************************************************/
    public String type() {
        return "King";
    }

    /******************************************************************
     *  A Method that checks to see if what you click is a valid move
     *  for the king chess piece
     *
     * @param move the move that the user is trying to make
     * @param board the place on the board that the piece is
     * is trying to move to
     * @return true if the king is allowed to move to that spot
     * @return false if the king is not allowed to move to that spot
     *****************************************************************/
    @Override
    public boolean isValidMove(Move move, IChessPiece[][] board) {

        if((move.fromColumn==move.toColumn)&&
                (move.fromRow == move.toRow) )
            return false;
        if(Math.abs(move.fromRow - move.toRow) < 2 &&
                Math.abs(move.fromColumn - move.toColumn) < 2 &&
                (super.isValidMove(move, board)) ){
            return true;
        }
        else
            return false;
    }

}


