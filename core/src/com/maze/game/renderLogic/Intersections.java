package com.maze.game.renderLogic;

public class Intersections {
    private double x,y,range;

    public void setRange(Intersections obj) {
        this.range = Math.sqrt(Math.pow(this.x-this.y,2)+Math.pow(obj.getX()-obj.getY(),2));
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Intersections(double x,double y) {
        this.x = x;
        this.y = y;
    }
}
