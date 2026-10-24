package rules;

import domain.Board;
import domain.Color;
import domain.Move;
import domain.Piece;

import javax.swing.*;

import static java.lang.Math.abs;

public class MoveValidator {
    public static boolean isValidMove(Move move, Board board, Piece piece) {
        return switch (piece.type()) {
            case PAWN -> validatePawn(move, board, piece);
            case KNIGHT -> validateKnight(move);
            case ROOK -> validateRook(move, board);
            case BISHOP -> validateBishop(move, board);
            case QUEEN -> validateQueen(move, board);
            case KING -> validateKing(move);
        };
    }

    private static boolean validateKing(Move move) {
        int diffX = abs(move.to().x() - move.from().x());
        int diffY = abs(move.to().y() - move.from().y());
        return (diffX <=1 && diffY <= 1);
    }

    private static boolean validateBishop(Move move, Board board) {
        int diffX = abs(move.from().x() - move.to().x());
        int diffY = abs(move.from().y() - move.to().y());
        return (diffX == diffY && (diffX >0));
    }

    private static boolean validateQueen(Move move, Board board) {
        return validateBishop(move, board) ^ validateRook(move, board);
    }

    private static boolean validateRook(Move move, Board board) {
        int diffY = abs(move.from().y() - move.to().y());
        int diffX = abs(move.from().x() - move.to().x());
        return ((diffX >0 && diffY == 0) || (diffX ==0 && diffY > 0));
    }

    private static boolean validateKnight(Move move) {
        int diffX = abs(move.to().x() - move.from().x());
        int diffY = abs(move.to().y() - move.from().y());
        return ((diffX == 2 && diffY == 1)|| diffX == 1 && diffY == 2;
    }

    private static boolean validatePawn(Move move, Board board, Piece piece) {
        if (move.from().x() == 1 || move.from().x() == 6){
            if(piece.color() == Color.white) return (move.to().x()-move.from().x() == 2 || move.to()-move.from()) == 1);
            return (move.from().x()-move.to().x() == 2 ||move.from()-move.to()) == 1);
        }
        if (piece.color() == Color.white){
            return (move.to().x()-move.from().x() == 1);
        }
        return (move.from()-move.to()) == 1);
    }
}
