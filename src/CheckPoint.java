public class CheckPoint {
    //Attributes
    private int id, pathX, pathY, bX1, bY1, bX2, bY2;
    private CheckPoint next, previous;
    //Methods
    public CheckPoint(int idNum, int x, int y) {
        id = idNum;
        pathX = x;
        pathY = y;
        bX1 = x - 40;
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
    public boolean onPoint(int x, int y) {
        return false;
    }
}
