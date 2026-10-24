package rules;

import domain.*;

import javax.swing.*;

import java.util.Map;

import static java.lang.Math.abs;

public class MoveValidator {
    public static boolean isValidMove(Move move, Board board, Piece piece) {
        return switch (piece.type()) {
            case PAWN -> validatePawn(move, board, piece);
            case KNIGHT -> validateKnight(move,board, piece);
            case ROOK -> validateRook(move, board, piece);
            case BISHOP -> validateBishop(move, board, piece);
            case QUEEN -> validateQueen(move, board, piece);
            case KING -> validateKing(move, board, piece);
        };
    }

    private static boolean validateKing(Move move, Board board, Piece piece) {
        if (isPathBlocked(move, board, piece)) return false;
        int diffX = abs(move.to().x() - move.from().x());
        int diffY = abs(move.to().y() - move.from().y());
        return (diffX <=1 && diffY <= 1);
    }

    private static boolean validateBishop(Move move, Board board, Piece piece) {
        if (isPathBlocked(move, board, piece)) return false;
        int diffX = abs(move.from().x() - move.to().x());
        int diffY = abs(move.from().y() - move.to().y());
        return (diffX == diffY && (diffX >0));
    }

    private static boolean validateQueen(Move move, Board board, Piece piece) {
        if (isPathBlocked(move, board, piece)) return false;
        return validateBishop(move, board, piece) ^ validateRook(move, board, piece);
    }

    private static boolean validateRook(Move move, Board board, Piece piece) {
        if (isPathBlocked(move, board, piece)) return false;
        int diffY = abs(move.from().y() - move.to().y());
        int diffX = abs(move.from().x() - move.to().x());
        return ((diffX >0 && diffY == 0) || (diffX ==0 && diffY > 0));
    }

    private static boolean validateKnight(Move move,Board board, Piece piece) {
        Map<Square, Piece>tablero = board.getPieces();
        Piece piece1 = tablero.get(move.to());
        if (piece1 != null) {
            if (piece1.color().equals(piece.color())) return false;
        }
        int diffX = abs(move.to().x() - move.from().x());
        int diffY = abs(move.to().y() - move.from().y());
        return ((diffX == 2 && diffY == 1)|| diffX == 1 && diffY == 2);
    }

    private static boolean validatePawn(Move move, Board board, Piece piece) {
        if (isPathBlocked(move, board, piece)) return false;
        int diffX = move.to().x() - move.from().x();
        if (move.to().y()-move.from().y() != 0){
            Map<Square, Piece> tablero = board.getPieces();
            if ((tablero.get(move.to().y()+1) != null) || (tablero.get(move.to().y()-1) != null)){
                return true;
            }
        }
        if (piece.color().equals(Color.white) && move.from().x() == 1) return (diffX == 2 || diffX == 1);
        if (piece.color().equals(Color.black) && move.from().x() == 6) return (diffX == -2 || diffX == -1);
        return ((piece.color().equals(Color.white) && diffX == 1) || (piece.color().equals(Color.black) && diffX == -1));
    }

    private static boolean isPathBlocked(Move move, Board board, Piece piece){
        Map<Square, Piece>tablero = board.getPieces();
        int stepX = Integer.compare(move.to().x(), move.from().x());
        int stepY = Integer.compare(move.to().y(), move.from().y());

        int currentX = move.from().x() + stepX;
        int currentY = move.from().y() + stepY;

        while (currentX != move.to().x() || currentY != move.to().y()) {
            Piece pieceInPath = tablero.get(new Square(currentX, currentY));
            if (pieceInPath != null) {
                return true;
            }
            currentX += stepX;
            currentY += stepY;
        }
        Piece piece1 = tablero.get(move.to());
        if (piece1 != null) {
            if (move.to().y() - move.from().y() != 0 && piece1.type().equals(PieceType.PAWN)) return false;
            if (piece1.color().equals(piece.color()) || piece1.type().equals(PieceType.PAWN)) return true;
        }
        return false;
    }
}
