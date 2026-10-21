package domain;

public class Move {
    private int x = 1;
    private int y = 0;

    public Move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static Move movement(Piece piece){
        int xz;
        int yz;
        Move move = null;
        switch (piece.type()){
            case PAWN -> move = new Move(1, 0);
            case KNIGHT -> move = new Move(2, 1);
        }
        return move;
    }
}
