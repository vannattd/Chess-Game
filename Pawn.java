/**********************************************************************
 * This Class represents the pawn chess piece on the chess board
 *
 * @author Zack Frent, Dylan Vannatter, and Youssef Shalaby
 * @version Winter 2019
 *********************************************************************/
public class Pawn extends ChessPiece {

    /** firstTurn represents if pawn has made its first move or not */
    private boolean firstTurn;

    /** enPassant reprsents the special move that a pawn can make to
     * kill another pawn */
    private boolean enPassant;

    /******************************************************************
     * Constructor that sets the player using the pawn chess piece
     *
     * @param player player that is playing the chess game
     *****************************************************************/
    public Pawn(Player player) {
        super(player);
        firstTurn = true;
    }

    public void setFirstTurn(boolean first) {
        firstTurn = first;
    }

    /******************************************************************
     *  A Method that returns the type of piece it is, which is Pawn
     *
     *  @return "Pawn" for the type of chess piece that is
     *  being used to move
     *****************************************************************/
    public String type() {
        return "Pawn";
    }

    /******************************************************************
     *  A Method that checks to see if what you click is a vaild move
     *  for the pawn chess piece
     *
     * @param move the move that the user is trying to make
     * @param board the place on the board that the piece is
     * is trying to move to
     * @return true if the pawn is allowed to move to that spot
     *
     *****************************************************************/
    // determines if the move is valid for a pawn piece

    @Override
    public boolean isValidMove(Move move, IChessPiece[][] board) {
        if (super.isValidMove(move, board))
            if (this.player() == Player.WHITE && move.fromRow > move.toRow ) {
//             if(move.fromColumn + 1 <=7 || move.fromColumn -1 >=0){
//                if((move.fromRow == 4 &&
//                      (board[move.fromRow][move.fromColumn+1].type().equals("Pawn")&&
//                      board[move.fromRow][move.fromColumn+1].player()!=this.player())&&
//                      (move.toRow == 3 && (move.toColumn == move.fromColumn +1
//                            || move.toColumn == move.fromColumn -1))))
//                         enPassant = true;
//                else
//
//
//                if ((board[move.fromRow][move.fromColumn-1].type().equals("Pawn") &&
//                      board[move.fromRow][move.fromColumn-1].player()!=this.player()) &&
//                      (move.toRow == 3 && (move.toColumn == move.fromColumn +1
//                         || move.toColumn == move.fromColumn -1)))
//                   enPassant = true;
//             }
                if (board[move.toRow][move.toColumn] != null) {
                    if (move.fromColumn != 7 &&  board[move.fromRow - 1][move.fromColumn + 1]
                            == board[move.toRow][move.toColumn]  ||
                            move.fromColumn != 0 && board[move.fromRow - 1][move.fromColumn - 1]
                                    ==  board[move.toRow][move.toColumn] ) {
                        return true;
                    }
                    return false;
                }
                else {
                    if (Math.abs(move.fromRow - move.toRow) == 2
                            && Math.abs(move.fromColumn - move.toColumn) == 0
                            && firstTurn) {
                        if (move.fromRow > move.toRow) {
                            setFirstTurn(false);
                            return true;
                        }
                    }
                    else if (Math.abs(move.fromRow - move.toRow) == 1
                            && Math.abs(move.fromColumn - move.toColumn) == 0)
                        if (move.fromRow > move.toRow) {
                            setFirstTurn(false);
                            return true;
                        }
                }
            }
            else if (this.player() == Player.BLACK && move.fromRow < move.toRow && move.fromRow != 0) {
                //             if(move.fromColumn + 1 <=7 || move.fromColumn -1 >=0){
//                if((move.fromRow == 3 &&
//                      (board[move.fromRow][move.fromColumn+1].type().equals("Pawn")&&
//                      board[move.fromRow][move.fromColumn+1].player()!=this.player())&&
//                      (move.toRow == 4 && (move.toColumn == move.fromColumn +1
//                            || move.toColumn == move.fromColumn -1))))
//                         enPassant = true;
//                else
//
//
//                if ((board[move.fromRow][move.fromColumn-1].type().equals("Pawn") &&
//                      board[move.fromRow][move.fromColumn-1].player()!=this.player()) &&
//                      (move.toRow == 4 && (move.toColumn == move.fromColumn +1
//                         || move.toColumn == move.fromColumn -1)))
//                   enPassant = true;
//             }
                if (board[move.toRow][move.toColumn] != null) {
                    if (move.fromColumn != 7 && board[move.fromRow + 1][move.fromColumn + 1] == board[move.toRow][move.toColumn] ||
                            move.fromColumn != 0 && board[move.fromRow + 1][move.fromColumn - 1] == board[move.toRow][move.toColumn]) {
                        return true;
                    }
                    return false;
                } else {
                    if (Math.abs(move.fromRow - move.toRow) == 2
                            && Math.abs(move.fromColumn - move.toColumn) == 0
                            && firstTurn) {
                        if (move.fromRow < move.toRow) {
                            setFirstTurn(false);
                            return true;
                        }
                    } else if (Math.abs(move.fromRow - move.toRow) == 1
                            && Math.abs(move.fromColumn - move.toColumn) == 0)
                        if (move.fromRow < move.toRow) {
                            setFirstTurn(false);
                            return true;
                        }
                }
            }
        return false;
    }

}









