package ulpgc.chess.logic;

import ulpgc.chess.domain.*;

import java.util.Map;

import static java.lang.Math.abs;

public class MoveValidator {
    public static boolean isValidMove(Move move, Board board, Piece piece, Square enPassantTarget) {
        return switch (piece.type()) {
            case PAWN -> validatePawn(move, board, piece, enPassantTarget);
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
        int diffX = abs(move.from().x() - move.to().x());
        int diffY = abs(move.from().y() - move.to().y());
        if (!(diffX == diffY && (diffX >0))) return false;
        return !isPathBlocked(move, board, piece);
    }

    private static boolean validateQueen(Move move, Board board, Piece piece) {
        return validateBishop(move, board, piece) || validateRook(move, board, piece);
    }

    private static boolean validateRook(Move move, Board board, Piece piece) {
        int diffY = abs(move.from().y() - move.to().y());
        int diffX = abs(move.from().x() - move.to().x());
        if (!((diffX >0 && diffY == 0) || (diffX ==0 && diffY > 0))) return false;
        return !isPathBlocked(move, board, piece);
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

    private static boolean validatePawn(Move move, Board board, Piece piece, Square enPassantTarget) {
        Map<Square, Piece> tablero = board.getPieces();
        int diffX = move.to().x() - move.from().x();
        if (isPathBlocked(move, board, piece)) return false;
        if (enPassant(move, piece, enPassantTarget, diffX)) return true;
        if (capturePawn(move, piece, tablero)) return true;
        if (piece.color().equals(Color.white) && move.from().x() == 1) return (diffX == 2 || diffX == 1);
        if (piece.color().equals(Color.black) && move.from().x() == 6) return (diffX == -2 || diffX == -1);
        return ((piece.color().equals(Color.white) && diffX == 1) || (piece.color().equals(Color.black) && diffX == -1));
    }

    private static boolean capturePawn(Move move, Piece piece, Map<Square, Piece> tablero) {
        int diffX = move.to().x() - move.from().x();
        int diffY = abs(move.to().y() - move.from().y());
        if (diffY == 1) {
            if (piece.color().equals(Color.white) && diffX == 1) {
                Piece targetPiece = tablero.get(move.to());
                return targetPiece != null && !targetPiece.color().equals(piece.color());
            }
            if (piece.color().equals(Color.black) && diffX == -1) {
                Piece targetPiece = tablero.get(move.to());
                return targetPiece != null && !targetPiece.color().equals(piece.color());
            }
        }
        return false;
    }

    private static boolean enPassant(Move move, Piece piece, Square enPassantTarget, int diffX) {
        if (move.from().x() == 4 && piece.color() == Color.white && enPassantTarget != null){
            int diffY = enPassantTarget.y() - move.from().y();
            if(abs(diffY) == 1){
               if (piece.color().equals(Color.white) && diffX == 1) return true;
               if (piece.color().equals(Color.black) && diffX == -1) return true;
            }
        }
        return false;
    }

    private static boolean isPathBlocked(Move move, Board board, Piece piece){
        Map<Square, Piece>tablero = board.getPieces();
        int diffX = abs(move.to().x() - move.from().x());
        int diffY = abs(move.to().y() - move.from().y());
        if (diffX != 0 && diffY != 0 && diffX != diffY) {
            return false;
        }
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
            if (diffY == 0 && piece.type().equals(PieceType.PAWN)) return true;
            if (piece1.color().equals(piece.color())) return true;
        }
        return false;
    }
}
