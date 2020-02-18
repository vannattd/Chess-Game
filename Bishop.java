
/**********************************************************************
 * This Class represents the bishop chess piece on the chess board
 *
 * @author Zack Frent, Dylan Vannatter, and Youssef Shalaby
 * @version Winter 2019
 *********************************************************************/
public class Bishop extends ChessPiece {

    /******************************************************************
     * Constructor that sets the player using the bishop chess piece
     *
     * @param player player that is playing the chess game
     *****************************************************************/
    public Bishop(Player player) {
        super(player);
    }

    /******************************************************************
     *  A Method that returns the type of piece it is, which is bishop
     *
     *  @return "Bishop" for the type of chess piece that is
     *  being used to move
     *****************************************************************/
    public String type() {
        return "Bishop";
    }

    /******************************************************************
     *  A Method that checks to see if what you click is a valid move
     *  for the bishop chess piece
     *
     * @param move the move that the user is trying to make
     * @param board the place on the board that the piece is
     * is trying to move to
     * @return true if the bishop is allowed to move to that spot
     * @return false if the bishop is not allowed to move to that spot
     *****************************************************************/
    @Override
    public boolean isValidMove(Move move, IChessPiece[][] board) {
        int moveDiagonalRight;
        int moveDiagonalLeft;
        int moveDiagonalUp;
        int moveDiagonalDown;
        if (super.isValidMove(move, board))
            // Moves the bishop right and up diagonally
            if (move.fromRow > move.toRow && move.fromColumn <
                    move.toColumn && (move.fromRow - move.toRow) ==
                    (move.toColumn - move.fromColumn)) {
                for (moveDiagonalUp = (move.fromRow - 1);
                     moveDiagonalUp > move.toRow; moveDiagonalUp--)
                    for (moveDiagonalRight = (move.fromColumn + 1);
                         moveDiagonalRight < move.toColumn;
                         moveDiagonalRight++)
                        if (board[moveDiagonalUp][moveDiagonalRight]
                                != null)
                            if ((move.fromRow - moveDiagonalUp) ==
                                    (moveDiagonalRight -
                                            move.fromColumn) ){
                                return false;
                            }
                return true;
            }
            //Moves the bishop left and up diagonally
            else if (move.fromRow > move.toRow && move.fromColumn >
                    move.toColumn &&
                    (move.fromRow - move.toRow) == (move.fromColumn -
                            move.toColumn)){
                for (moveDiagonalUp = (move.fromRow - 1); moveDiagonalUp >
                        move.toRow; moveDiagonalUp--)
                    for (moveDiagonalLeft = (move.fromColumn - 1);
                         moveDiagonalLeft > move.toColumn;
                         moveDiagonalLeft--)
                        if (board[moveDiagonalUp][moveDiagonalLeft]
                                != null )
                            if ((move.fromRow - moveDiagonalUp) ==
                                    (move.fromColumn - moveDiagonalLeft)) {
                                return false;
                            }
                return true;
            }
            //Moves the bishop to the right and down diagonally
            else if (move.fromRow < move.toRow && move.fromColumn <
                    move.toColumn &&
                    (move.toRow - move.fromRow) ==
                            (move.toColumn - move.fromColumn)) {
                for (moveDiagonalDown = (move.fromRow + 1);
                     moveDiagonalDown < move.toRow; moveDiagonalDown++)
                    for (moveDiagonalRight = (move.fromColumn + 1);
                         moveDiagonalRight < move.toColumn;
                         moveDiagonalRight++)
                        if (board[moveDiagonalDown][moveDiagonalRight]
                                != null )
                            if ((moveDiagonalDown - move.fromRow) ==
                                    (moveDiagonalRight - move.fromColumn)) {
                                return false;
                            }
                return true;
            }
            //Moves the bishop to the left and down diagonally
            else if (move.fromRow < move.toRow && move.fromColumn
                    > move.toColumn &&
                    (move.toRow - move.fromRow) ==
                            (move.fromColumn - move.toColumn)) {
                for (moveDiagonalDown = (move.fromRow + 1); moveDiagonalDown
                        < move.toRow; moveDiagonalDown++)
                    for (moveDiagonalLeft = (move.fromColumn - 1);
                         moveDiagonalLeft > move.toColumn; moveDiagonalLeft--)
                        if (board[moveDiagonalDown][moveDiagonalLeft] != null)
                            if( (moveDiagonalDown - move.fromRow) ==
                                    (move.fromColumn - moveDiagonalLeft)) {
                                return false;
                            }
                return true;
            }
        return false;
    }
}
