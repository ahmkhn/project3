
public class CheckPoint {
    private  String id;
    private int pathX;
    private int pathY;
    private int bX1;
    private int bX2;
    private int bY1;
    private int bY2;
    private CheckPoint next;
    private CheckPoint previous;


    CheckPoint(String id) {
        this.id = id;
    }

    String getId() {
        return id;
    }

    public CheckPoint getNext() {
        return next;
    }

    public CheckPoint getPrevious() {
        return previous;
    }

    public void setNext(CheckPoint point) {
        this.next = point;
    }

    public void setPrevious(CheckPoint point) {
        this.previous = point;
    }

    public boolean withinBoundaries(int x, int y) {
        return x >= bX1 && x <= bX2 && y >= bY1 && y <= bY2;
    }
}
