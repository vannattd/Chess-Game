/**********************************************************************
 * This Class represents the movement of pieces on the chess board
 *
 * @author Zack Frent, Dylan Vannatter, and Youssef Shalaby
 * @version Winter 2019
 *********************************************************************/
public class Move {

    /** fromRow represents the row the chess piece is in */
    public int fromRow;

    /** fromCol represents the column the chess piece is in */
    public int fromColumn;

    /** toRow represents the row the chess piece is in from where you
     * want to move it */
    public int toRow;

    /** toColumn represents the column the chess piece is in from where
     *  you want to move it */
    public int toColumn;


    /******************************************************************
     * Creates a default Constructor for the move class
     *
     *****************************************************************/
    public Move() {
    }

    /******************************************************************
     * Constructor that takes where you click the piece and where you
     * click the second time and moves the chess piece to the desired
     * location
     *
     * @param fromRow the row where you start
     * @param toRow the row where you want to end
     * @param fromColumn the column where you start
     * @param toColumn the column where you end
     *****************************************************************/
    public Move(int fromRow, int fromColumn, int toRow, int toColumn) {
        this.fromRow = fromRow;
        this.fromColumn = fromColumn;
        this.toRow = toRow;
        this.toColumn = toColumn;
    }

    /******************************************************************
     * Method that takes where you click the piece and where you
     * click the second time and moves the chess piece to the desired
     * location
     *
     * @param fromRow the row where you start
     * @param toRow the row where you want to end
     * @param fromColumn the column where you start
     * @param toColumn the column where you end
     *****************************************************************/
    public void setValues(int fromRow, int fromColumn, int toRow,
                          int toColumn)
    {
        this.fromRow = fromRow;
        this.fromColumn = fromColumn;
        this.toRow = toRow;
        this.toColumn = toColumn;
    }


    /*********************************************************************
     * Method that returns a statement where you started, and the location
     * you moved the chess piece to
     *
     **********************************************************************/
    @Override
    public String toString() {
        return "Move [fromRow=" + fromRow + ", fromColumn=" + fromColumn
                + ", toRow=" + toRow + ", toColumn=" + toColumn
                + "]";
    }


}
