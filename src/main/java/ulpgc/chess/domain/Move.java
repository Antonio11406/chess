package ulpgc.chess.domain;

public record Move(Square from, Square to, PieceType promotion) {
    public Move(Square from, Square to){
        this(from, to, null);
    }
}
