import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

/**********************************************************************
 * This class creates a board or panel for the chess game
 *
 * @author Zack Frent, Dylan Vannatter, and Youssef Shalaby
 * @version Winter 2019
 *********************************************************************/
public class ChessPanel extends JPanel {

    /** board represents the playing board for the chess game */
    private JButton[][] board;

    /** undo represents the undo button in the chess game */
    private JButton undo;

    /** castleRight represents the castleRight button in the chess game */
    private JButton castleRight;

    /** castleLeft represents the button to castleLeft in the chess game */
    private JButton castleLeft;

    /** promotion represents the promotion button in the chess game */
    private JButton promotion;

    /** model represents an object of the chess model class */
    private ChessModel model;

    /** previousMoves represents a list of all the moves done by pieces */
    private ArrayList<Move> previousMoves;

    /** boards represents a list of the boards that we created */
    private  ArrayList<IChessPiece[][]> boards;

    /** types represents a list of the types of chess pieces in the game */
    private ArrayList<IChessPiece> types;

    /** wRook represents the white rook chess piece */
    private ImageIcon wRook;

    /** wBishop represents the white bishop chess piece */
    private ImageIcon wBishop;

    /** wQueen represents the white queen chess piece */
    private ImageIcon wQueen;

    /** wKing represents the white king chess piece */
    private ImageIcon wKing;

    /** wKnight represents the white knight chess piece */
    private ImageIcon wKnight;

    /** wPawn1 represents the first white pawn chess piece */
    private ImageIcon wPawn;

    /** bRook represents the black rook chess piece */
    private ImageIcon bRook;

    /** bBishop represents the black bishop chess piece */
    private ImageIcon bBishop;

    /** bQueen represents the black queen chess piece */
    private ImageIcon bQueen;

    /** bKing represents the black king chess piece */
    private ImageIcon bKing;

    /** bKnight represents the black knight chess piece */
    private ImageIcon bKnight;

    /** bPawn1 represents the first black pawn chess piece */
    private ImageIcon bPawn;

    /** firstTurnFlag represents the first turn */
    private boolean firstTurnFlag;

    /** fromRow represents the row the chess piece is in */
    private int fromRow;

    /** toRow represents the row the chess piece is in from where you
     * want to move it */
    private int toRow;

    /** fromCol represents the column the chess piece is in */
    private int fromCol;

    /** toCol represents the column the chess piece is in from where
     *  you want to move it */
    private int toCol;

    /** listener represents the action listener */
    private listener listener;

    /******************************************************************
     * Constructor that makes a default panel for the chess game
     *
     *****************************************************************/
    public ChessPanel() {
        previousMoves = new ArrayList<>();
        types = new ArrayList<>();
        model = new ChessModel();
        undo = new JButton("Undo");
        castleLeft= new JButton("Castle Left");
        castleRight = new JButton("Castle Right");
        promotion = new JButton("Promotion");
        board = new JButton[model.numRows()][model.numColumns()];
        listener = new listener();
        createIcons();

        JPanel boardpanel = new JPanel();
        JPanel buttonpanel = new JPanel();
        boardpanel.setLayout(new GridLayout(model.numRows(),
                model.numColumns(), 1, 1));

        for (int r = 0; r < model.numRows(); r++) {
            for (int c = 0; c < model.numColumns(); c++) {
                if (model.pieceAt(r, c) == null) {
                    board[r][c] = new JButton("", null);
                    board[r][c].addActionListener(listener);
                } else if (model.pieceAt(r, c).player() == Player.WHITE)
                    placeWhitePieces(r, c);
                else if (model.pieceAt(r, c).player() == Player.BLACK)
                    placeBlackPieces(r, c);



                setBackGroundColor(r, c);
                boardpanel.add(board[r][c]);
            }
        }
        undo.addActionListener(listener);
        promotion.addActionListener(listener);
        castleLeft.addActionListener(listener);
        castleRight.addActionListener(listener);
        buttonpanel.add(undo);
        buttonpanel.add(promotion);
        buttonpanel.add(castleLeft);
        buttonpanel.add(castleRight);
        add(boardpanel, BorderLayout.WEST);
        boardpanel.setPreferredSize(new Dimension(600, 600));
        add(buttonpanel);
        firstTurnFlag = true;
    }

    /*********************************************************************
     * Method that gets the chess model being used
     *
     **********************************************************************/
    public ChessModel getChessModel() {
        return model;
    }

    /*********************************************************************
     * Method that sets the color of the background
     *
     **********************************************************************/
    private void setBackGroundColor(int r, int c) {
        if ((c % 2 == 1 && r % 2 == 0) || (c % 2 == 0 && r % 2 == 1)) {
            board[r][c].setBackground(Color.LIGHT_GRAY);
        } else if ((c % 2 == 0 && r % 2 == 0) || (c % 2 == 1 && r % 2 == 1)) {
            board[r][c].setBackground(Color.WHITE);
        }
    }

    /*********************************************************************
     * Method that sets the white chess pieces on the game board
     *
     **********************************************************************/
    private void placeWhitePieces(int r, int c) {
        if (model.pieceAt(r, c).type().equals("Pawn")) {
            board[r][c] = new JButton(null, wPawn);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Rook")) {
            board[r][c] = new JButton(null, wRook);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Knight")) {
            board[r][c] = new JButton(null, wKnight);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Bishop")) {
            board[r][c] = new JButton(null, wBishop);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Queen")) {
            board[r][c] = new JButton(null, wQueen);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("King")) {
            board[r][c] = new JButton(null, wKing);
            board[r][c].addActionListener(listener);
        }
    }

    /******************************************************************
     * Method that sets the black chess pieces on the game board
     *
     *****************************************************************/
    private void placeBlackPieces(int r, int c) {
        if (model.pieceAt(r, c).type().equals("Pawn")) {
            board[r][c] = new JButton(null, bPawn);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Rook")) {
            board[r][c] = new JButton(null, bRook);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Knight")) {
            board[r][c] = new JButton(null, bKnight);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Bishop")) {
            board[r][c] = new JButton(null, bBishop);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Queen")) {
            board[r][c] = new JButton(null, bQueen);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("King")) {
            board[r][c] = new JButton(null, bKing);
            board[r][c].addActionListener(listener);
        }
    }

    /******************************************************************
     * Method that creates the icons for the black and white pieces
     *
     *****************************************************************/
    private void createIcons() {
        // Sets the Image for white player pieces
        wRook = new ImageIcon("./src/Project3/wRook.png");
        wBishop = new ImageIcon("./src/Project3/wBishop.png");
        wQueen = new ImageIcon("./src/Project3/wQueen.png");
        wKing = new ImageIcon("./src/Project3/wKing.png");
        wPawn = new ImageIcon("./src/Project3/wPawn.png");
        wKnight = new ImageIcon("./src/Project3/wKnight.png");

        // Sets the Image for black player pieces
        bRook = new ImageIcon("./src/Project3/bRook.png");
        bBishop = new ImageIcon("./src/Project3/bBishop.png");
        bQueen = new ImageIcon("./src/Project3/bQueen.png");
        bKing = new ImageIcon("./src/Project3/bKing.png");
        bPawn = new ImageIcon("./src/Project3/bPawn.png");
        bKnight = new ImageIcon("./src/Project3/bKnight.png");

    }

    /******************************************************************
     * Method that displays the board for the chess game
     *
     *****************************************************************/
    // method that updates the board
    private void displayBoard() {

        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++)
                if (model.pieceAt(r, c) == null)
                    board[r][c].setIcon(null);
                else if (model.pieceAt(r, c).player() == Player.BLACK) {
                    if (model.pieceAt(r, c).type().equals("Pawn"))
                        board[r][c].setIcon(bPawn);

                    if (model.pieceAt(r, c).type().equals("Rook"))
                        board[r][c].setIcon(bRook);

                    if (model.pieceAt(r, c).type().equals("Knight"))
                        board[r][c].setIcon(bKnight);

                    if (model.pieceAt(r, c).type().equals("Bishop"))
                        board[r][c].setIcon(bBishop);

                    if (model.pieceAt(r, c).type().equals("Queen"))
                        board[r][c].setIcon(bQueen);

                    if (model.pieceAt(r, c).type().equals("King"))
                        board[r][c].setIcon(bKing);
                }

                else if (model.pieceAt(r, c).player() == Player.WHITE) {
                    if (model.pieceAt(r, c).type().equals("Pawn"))
                        board[r][c].setIcon(wPawn);

                    if (model.pieceAt(r, c).type().equals("Rook"))
                        board[r][c].setIcon(wRook);

                    if (model.pieceAt(r, c).type().equals("Knight"))
                        board[r][c].setIcon(wKnight);

                    if (model.pieceAt(r, c).type().equals("Bishop"))
                        board[r][c].setIcon(wBishop);

                    if (model.pieceAt(r, c).type().equals("Queen"))
                        board[r][c].setIcon(wQueen);

                    if (model.pieceAt(r, c).type().equals("King"))
                        board[r][c].setIcon(wKing);

                }
        }
        repaint();
    }

    /******************************************************************
     * Method that lets the user perform actions like clicking buttons
     *
     *****************************************************************/
    // inner class that represents action listener for buttons
    private class listener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            for (int r = 0; r < model.numRows(); r++)
                for (int c = 0; c < model.numColumns(); c++)
                    if (board[r][c] == event.getSource())
                        if (firstTurnFlag == true) {
                            fromRow = r;
                            fromCol = c;
                            firstTurnFlag = false;
                        } else {
                            toRow = r;
                            toCol = c;
                            firstTurnFlag = true;
                            Move m = new Move(fromRow, fromCol, toRow, toCol);
                            if ((model.isValidMove(m)) == true) {
                                model.saveBoard();
                                if (model.pieceAt(m.toRow, m.toColumn) !=
                                        null) {
                                    types.add(model.pieceAt(m.toRow,
                                            m.toColumn));
                                }
                                model.move(m);
                                model.setNextPlayer();
                                //This checks to see if you are in check
                                // and to see if it is complete
                                if(model.inCheck(model.currentPlayer())) {
                                    JOptionPane.showMessageDialog
                                            (new JFrame(),
                                                    model.currentPlayer() +
                                                            " is in check");
                                    if (model.isComplete())
                                        JOptionPane.showMessageDialog
                                                (new JFrame(), "Check Mate" +
                                                        "Game is over");
                                }
                                displayBoard();
                            }
                        }
            //This calls the AI method in the ChessModel
            if(model.currentPlayer() == Player.BLACK){
                model.AI();
                model.setNextPlayer();
            }



            // This calls the undo button and undo's the last move on the board
            if (undo == event.getSource()) {
                boards = model.getBoards();
                IChessPiece[][] newBoard = boards.get(boards.size()-1);
                model.setBoard(newBoard);
                boards.remove(boards.size() -1);
                displayBoard();
            }

            // This castles the rook and the king from the left for white
            if (castleLeft == event.getSource()) {
                if (model.currentPlayer() == Player.WHITE) {
                    if (model.pieceAt(7, 4).type() == "King" &&
                            model.pieceAt(7, 0).type() == "Rook" &&
                            model.pieceAt(7, 1) == null &&
                            model.pieceAt(7, 2) == null &&
                            model.pieceAt(7, 3) == null) {
                        model.setPiece(7, 3, new Rook(Player.WHITE));
                        model.setPiece(7, 2, new King(Player.WHITE));
                        model.setPiece(7, 0, null);
                        model.setPiece(7, 4, null);
                        displayBoard();
                        model.setNextPlayer();
                        model.saveBoard();
                    } else
                        JOptionPane.showMessageDialog(new JFrame(),
                                "Invalid Move");

                }
                // This castles the rook and the king from the left for black
                else {
                    if (model.pieceAt(0, 4).type() == "King" &&
                            model.pieceAt(0, 0).type() == "Rook" &&
                            model.pieceAt(0, 1) == null &&
                            model.pieceAt(0, 2) == null &&
                            model.pieceAt(0, 3) == null) {
                        model.setPiece(0, 3, new Rook(Player.BLACK));
                        model.setPiece(0, 2, new King(Player.BLACK));
                        model.setPiece(0, 0, null);
                        model.setPiece(0, 4, null);
                        displayBoard();
                        model.setNextPlayer();
                        model.saveBoard();
                    } else
                        JOptionPane.showMessageDialog(new JFrame(),
                                "Invalid Move");

                }
            }
            // This castles the rook and the king from the right for white
            if (castleRight == event.getSource()) {
                if (model.currentPlayer() == Player.WHITE) {
                    if (model.pieceAt(7, 4).type() == "King" &&
                            model.pieceAt(7, 7).type() == "Rook" &&
                            model.pieceAt(7, 5) == null &&
                            model.pieceAt(7, 6) == null) {
                        model.setPiece(7, 5, new Rook(Player.WHITE));
                        model.setPiece(7, 6, new King(Player.WHITE));
                        model.setPiece(7, 7, null);
                        model.setPiece(7, 4, null);
                        displayBoard();
                        model.setNextPlayer();
                    } else
                        JOptionPane.showMessageDialog(new JFrame(),
                                "Invalid Move");
                }
                // This castles the rook and the king from the right for black
                else {
                    if (model.pieceAt(0, 4).type() == "King" &&
                            model.pieceAt(0, 7).type() == "Rook" &&
                            model.pieceAt(0, 5) == null &&
                            model.pieceAt(0, 6) == null) {
                        model.setPiece(0, 5, new Rook(Player.BLACK));
                        model.setPiece(0, 6, new King(Player.BLACK));
                        model.setPiece(0, 7, null);
                        model.setPiece(0, 4, null);
                        displayBoard();
                        model.setNextPlayer();
                    } else
                        JOptionPane.showMessageDialog(new JFrame(),
                                "Invalid Move");
                }
            }
            // This calls the promotion method for white pieces
            if (promotion == event.getSource()) {
                if (model.currentPlayer() == Player.WHITE) {
                    for (int i = 0; i < 8; i++) {
                        if (model.pieceAt(0, i).type() == "Pawn") {
                            String type = JOptionPane.showInputDialog
                                    (new JFrame(),
                                            "What do you want to promote to?");
                            if (type.equalsIgnoreCase("Queen"))
                                model.setPiece(0, i, new Queen(Player.WHITE));
                            else if (type.equalsIgnoreCase("Rook"))
                                model.setPiece(0, i, new Rook(Player.WHITE));
                            else if (type.equalsIgnoreCase("Bishop"))
                                model.setPiece(0, i, new Bishop(Player.WHITE));
                            else if (type.equalsIgnoreCase("Knight"))
                                model.setPiece(0, i, new Knight(Player.WHITE));
                            else {
                                JOptionPane.showMessageDialog(new JFrame(),
                                        "Invalid Piece Type");
                            }
                        }
                    }
                    model.saveBoard();
                    model.setNextPlayer();
                    displayBoard();
                }
                // This calls the promotion method for black pieces
                else {
                    for (int i = 0; i < 8; i++) {
                        if (model.pieceAt(7, i).type() == "Pawn") {
                            String type = JOptionPane.showInputDialog
                                    (new JFrame(),
                                            "What do you want to promote to?");
                            if (type.equalsIgnoreCase("Queen"))
                                model.setPiece(7, i, new Queen(Player.BLACK));
                            else if (type.equalsIgnoreCase("Rook"))
                                model.setPiece(7, i, new Rook(Player.BLACK));
                            else if (type.equalsIgnoreCase("Bishop"))
                                model.setPiece(7, i, new Bishop(Player.BLACK));
                            else if (type.equalsIgnoreCase("Knight"))
                                model.setPiece(7, i, new Knight(Player.BLACK));
                            else {
                                JOptionPane.showMessageDialog(new JFrame(),
                                        "Invalid Piece Type");
                            }
                        }
                    }
                    model.setNextPlayer();
                    displayBoard();
                }
            }
        }
    }
}


