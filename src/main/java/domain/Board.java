package domain;

import interfaces.BoardInitializer;

import java.util.Map;

public class Board implements BoardInitializer {
    private final Map<Square, Piece> pieces;

    public Board(){
        this.pieces= initializaBoard();
    }
    public Board(Map<Square, Piece> pieces) {
        this.pieces = Map.copyOf(pieces);
    }
}
