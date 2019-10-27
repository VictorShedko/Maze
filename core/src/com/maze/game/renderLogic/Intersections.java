package com.maze.game.renderLogic;

public class Intersections implements Comparable<Intersections>{
    private double x,y,range;

    public void setRange(Intersections obj) {
        this.range = Math.sqrt(Math.pow(this.x-obj.getX(),2)+Math.pow(this.y-obj.getY(),2));
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public int compareTo(Intersections o) {
      if(this.range-o.range>0)return 1;
      if(this.range-o.range==0)return 0;
      return -1;
    }

    public Intersections(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
