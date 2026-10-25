package domain;

import engine.BoardInitializer;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.abs;
import static engine.MoveValidator.isValidMove;

public class Board implements BoardInitializer {
    private final Map<Square, Piece> pieces;
    private final Color currentTurn;
    private boolean whiteKingcastling;
    private boolean whiteQueencastling;
    private boolean blackKingcastling;
    private boolean blackQueencastling;
    private Square enPassantTarget;

    public Board(){
        this.enPassantTarget = null;
        this.blackKingcastling = true;
        this.blackQueencastling = true;
        this.whiteKingcastling = true;
        this.whiteQueencastling = true;
        this.pieces= initializaBoard();
        this.currentTurn = Color.white;
    }
    public Board(Map<Square, Piece> pieces, Color currentTurn, boolean whiteKingcastling, boolean whiteQueencastling, boolean blackKingcastling, boolean blackQueencastling, Square enPassantTarget) {
        this.pieces = Map.copyOf(pieces);
        this.currentTurn = currentTurn;
        this.whiteKingcastling = whiteKingcastling;
        this.whiteQueencastling = whiteQueencastling;
        this.blackKingcastling = blackKingcastling;
        this.blackQueencastling = blackQueencastling;
        this.enPassantTarget = enPassantTarget;
    }

    public Board move(Move move){
        Piece piece = pieces.get(move.from());
        isCorrectTunr(piece);
        if (!isValidMove(move, this, piece, enPassantTarget)) return new Board(pieces, this.currentTurn, this.whiteKingcastling, this.whiteQueencastling, this.blackKingcastling, this.blackQueencastling, enPassantTarget);
        Map<Square, Piece> tablero = new HashMap<>(pieces);
        Color nextTurn = updateBoard(move, piece, tablero);
        piece = isPromotion(move, piece);
        enPassantTarget = null;
        isEnPassant(move, piece);
        return new Board(tablero, nextTurn, this.whiteKingcastling,this.whiteQueencastling,this.blackKingcastling,this.blackQueencastling, enPassantTarget);
    }

    private Color updateBoard(Move move, Piece piece, Map<Square, Piece> tablero) {
        if ((enPassantTarget != null) && (pieces.get(move.to()) == null) && (piece.type() == PieceType.PAWN)){
            tablero.remove(new Square(move.from().x(), move.to().y()));
        }
        tablero.remove(move.from());
        tablero.put(move.to(), piece);
        return (this.currentTurn.equals(Color.white)) ? Color.black : Color.white;
    }

    private void isCorrectTunr(Piece piece) {
        if (!piece.color().equals(currentTurn)){
            throw new IllegalArgumentException("Movimiento ilegal: Le toca mover a las " + this.currentTurn);
        }
    }

    private void isEnPassant(Move move, Piece piece) {
        if (piece.type().equals(PieceType.PAWN) && abs(move.to().x()- move.from().x()) == 2){
            enPassantTarget = new Square(move.to().x()- move.from().x()/2, move.to().y());
        }
    }

    private static Piece isPromotion(Move move, Piece piece) {
        if (piece.type().equals(PieceType.PAWN) && ((piece.color().equals(Color.white) && move.to().x()==7) || (piece.color().equals(Color.black) && move.to().x()==0))){
            piece = new Piece(move.promotion(), piece.color());
        }
        return piece;
    }

    public void printBoard() {
        for (int row = 7; row >= 0; row--) {
            System.out.print(row + " ");
            for (int col = 0; col < 8; col++) {
                Square square = new Square(row, col);
                Piece piece = pieces.get(square);

                if (piece == null) {
                    System.out.print(". ");
                } else {
                    char symbol = getPieceSymbol(piece);
                    System.out.print(symbol + " ");
                }
            }
            System.out.println();
        }
        System.out.println("  A B C D E F G H");
    }

    private char getPieceSymbol(Piece piece) {
        char symbol = switch (piece.type()) {
            case PAWN -> 'P';
            case ROOK -> 'R';
            case KNIGHT -> 'N';
            case BISHOP -> 'B';
            case QUEEN -> 'Q';
            case KING -> 'K';
        };

        return piece.color() == Color.white ? symbol : Character.toLowerCase(symbol);
    }

    public Map<Square, Piece> getPieces() {
        return pieces;
    }
}
