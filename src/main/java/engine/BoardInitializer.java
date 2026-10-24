package engine;

import domain.Color;
import domain.Piece;
import domain.PieceType;
import domain.Square;

import java.util.HashMap;
import java.util.Map;

public interface BoardInitializer {
    default Map<Square, Piece> initializaBoard(){
        Color white = Color.white;
        Color black = Color.black;
        Map<Square, Piece> pieces = new HashMap<>();
        pieces.put(new Square(0, 0), new Piece(PieceType.ROOK, white));
        pieces.put(new Square(0, 1), new Piece(PieceType.KNIGHT, white));
        pieces.put(new Square(0, 2), new Piece(PieceType.BISHOP, white));
        pieces.put(new Square(0, 3), new Piece(PieceType.QUEEN, white));
        pieces.put(new Square(0, 4), new Piece(PieceType.KING, white));
        pieces.put(new Square(0, 5), new Piece(PieceType.BISHOP, white));
        pieces.put(new Square(0, 6), new Piece(PieceType.KNIGHT, white));
        pieces.put(new Square(0, 7), new Piece(PieceType.ROOK, white));

        for (int col = 0; col < 8; col++) {
            pieces.put(new Square(6, col), new Piece(PieceType.PAWN, black));
        }

        for (int col = 0; col < 8; col++) {
            pieces.put(new Square(1, col), new Piece(PieceType.PAWN, white));
        }

        pieces.put(new Square(7, 0), new Piece(PieceType.ROOK, black));
        pieces.put(new Square(7, 1), new Piece(PieceType.KNIGHT, black));
        pieces.put(new Square(7, 2), new Piece(PieceType.BISHOP, black));
        pieces.put(new Square(7, 3), new Piece(PieceType.QUEEN, black));
        pieces.put(new Square(7, 4), new Piece(PieceType.KING, black));
        pieces.put(new Square(7, 5), new Piece(PieceType.BISHOP, black));
        pieces.put(new Square(7, 6), new Piece(PieceType.KNIGHT, black));
        pieces.put(new Square(7, 7), new Piece(PieceType.ROOK, black));
        return pieces;
    }

}
