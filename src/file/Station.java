package file;

import java.awt.*;

class Station {
    private int x; // x-coordinate of the station
    private int y; // y-coordinates of the station
    private String name; // Name of the station


    public Station(int x, int y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }

    // Station Drawing Method

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(x - 5, y - 5, 10, 10); // Draw a station in a circle// 원 모양으로 정류장 그리기
        g.drawString(name, x - 5, y - 10); // Show station name // 정류장 이름 표시
    }

    // x Coordinate Return Method

    public int getX() {
        return x;
    }

    // y coordinate return method

    public int getY() {
        return y;
    }
}
