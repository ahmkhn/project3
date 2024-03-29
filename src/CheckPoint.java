// Ahmed Khan
public class CheckPoint {
    //Attributes
    private int id; // id = unique ID for each checkpoint i.e 1,2,3,...
    private int pathX, pathY; // path = starting range of boundaries. i.e pixel 350X, 450Y
    private int bX1, bY1, bX2, bY2; // checkpoint boundaries; boundaryX1, boundaryY1 ...
    private CheckPoint next, previous;
    //Methods
    public CheckPoint(int idNum, int x, int y) {
        id = idNum;
        pathX = x;
        pathY = y;
        bX1 = x - 40; // setting boundaries...
        bY1 = y - 40;
        bX2 = x + 40;
        bY2 = y + 40;
    }
    public int getId() {
        return id;
    }
    public int getPathX() {
        return pathX;
    }
    public int getPathY() {
        return pathY;
    }
    public CheckPoint getNext() {
        return next;
    }
    public CheckPoint getPrevious() {
        return previous;
    }
    public void setNext(CheckPoint point) {
        next = point;
    }
    public void setPrevious(CheckPoint point) {
        previous = point;
    }
    public boolean withinBoundaries(int x, int y) {
        if(x > bX1 && x < bX2 && y > bY1 && y < bY2) {
            return true;
        }
        return false;
    }

}
