package file;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.*;


class Car {
    private Color color;
    private int x;
    private int y;
    private int previousX; // Previous x-coordinates// 이전 x좌표
    private int previousY; // Previous y-coordinates // 이전 y좌표
    private int width;
    private int height;


    public Car(Color color, int x, int y, int width, int height) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    // Draw car method
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }

    // x-coordinate setup method

    public void setX(int x) {
        this.x = x;
    }

    // y-coordinate setup method

    public void setY(int y) {
        this.y = y;
    }

    // Previous x-coordinate setup method

    public void setPreviousX(int previousX) {
        this.previousX = previousX;
    }

    // Previous y-coordinate setup method

    public void setPreviousY(int previousY) {
        this.previousY = previousY;
    }

    // x-coordinate return method

    public int getX() {
        return x;
    }

    // y-coordinate return method

    public int getY() {
        return y;
    }
}