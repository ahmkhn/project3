/**
 * Represents an area on a coordinate plane.
 * Implemented in an ordered list to determine the shape of the track and
 * the direction Cars travel. Can detect when cars enter its area.
 * @author Ahmed Khan
 * @version 1.0
 */
public class CheckPoint {
    //Attributes
    private int id; // id = unique ID for each checkpoint i.e 1,2,3,...
    private int pathX, pathY; // path = starting range of boundaries. i.e pixel 350X, 450Y
    private int bX1, bY1, bX2, bY2; // checkpoint boundaries; boundaryX1, boundaryY1 ...
    private CheckPoint next, previous;
    //Methods
    /**
     * Default constructor.
     * Passes an id of 0 and x and y values of 100 to the primary constructor.
     */
    public CheckPoint() {
        this(0, 100, 100);
    }
    /**
     * Primary constructor. Sets the coordinate position and bounds of the object.
     * @param idNum int set to id
     * @param x int set to pathX
     * @param y int set to pathY
     */
    public CheckPoint(int idNum, int x, int y) {
        id = idNum;
        pathX = x;
        pathY = y;
        bX1 = x - 20; // setting boundaries...
        bY1 = y - 20;
        bX2 = x + 20;
        bY2 = y + 20;
    }
    /**
     * Basic getter.
     * @return attribute int id
     */
    public int getId() {
        return id;
    }
    /**
     * Basic getter.
     * @return attribute int pathX
     */
    public int getPathX() {
        return pathX;
    }
    /**
     * Basic getter.
     * @return attribute int id
     */
    public int getPathY() {
        return pathY;
    }
    /**
     * Basic getter.
     * @return attribute CheckPoint next
     */
    public CheckPoint getNext() {
        return next;
    }
    /**
     * Basic getter.
     * @return attribute CheckPoint previous
     */
    public CheckPoint getPrevious() {
        return previous;
    }
    /**
     * Setter method used to determine the track's route during track generation.
     * Sets the object's next pointer
     * @param point CheckPoint set to next
     */
    public void setNext(CheckPoint point) {
        next = point;
    }
    /**
     * Setter method used to determine the track's route during track generation.
     * Sets the object's previous pointer
     * @param point CheckPoint set to previous==
     */
    public void setPrevious(CheckPoint point) {
        previous = point;
    }
    /**
     * Determines if the given coordinate pair falls within the CheckPoint's area.
     * @param x x coordinate of pair in question
     * @param y y coordinate of pair in question
     * @return true if the point falls within the Checkpoint's area
     */
    public boolean onPoint(int x, int y) {
        if(x > bX1 && x < bX2 && y > bY1 && y < bY2) {
            return true;
        }
        return false;
    }
    /**
     * Compares this Object to another.
     * @param o Object to be compared
     * @return true if Object o is the same as this Object
     */
    @Override
    public boolean equals(Object o) {
        if(super.equals(o) && getClass() == o.getClass()) {
            if(id == ((CheckPoint) o).getId() && pathX == ((CheckPoint) o).getPathX() && pathY == ((CheckPoint) o).getPathY()
            && next.getId() == ((CheckPoint) o).getNext().getId() && previous.getId() == ((CheckPoint) o).getPrevious().getId()) {
                return true;
            }
        }
        return false;
    }
    /**
     * Returns a String containing information regarding the object's attributes.
     * @return String listing object's key attributes
     */
    @Override
    public String toString() {
        return "Checkpoint " + id + ", center at (" + pathX + ", " + pathY + ").\nPrevious point ID: " + previous.getId() + ". Next point ID: " + next.getId() + ".";
    }
}