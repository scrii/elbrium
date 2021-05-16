package com.teamname.game.Actor;

import com.badlogic.gdx.graphics.Texture;
import com.teamname.game.GraphicsObj.GraphicsObj;
import com.teamname.game.Main;

import Tools.Circle;
import Tools.Point2D;

public abstract class Actor extends GraphicsObj {

    public Point2D position;
    public float Speed,R;
    public Circle bounds; // границы
    public Point2D direction;

    public Actor(Texture img, Point2D position, float Speed, float R) {
        super(img);
        this.position=new Point2D(position);
        this.Speed=Speed;
        this.R=R;
        bounds = new Circle(position,R);
        direction=new Point2D(0,0);
    }

    public Actor(Texture img, Point2D position) {
        super(img);
        this.position=new Point2D(position);
        direction=new Point2D(0,0);
    }

    public Actor(Point2D position, float Speed){
        super(Main.err);
        this.position=new Point2D(position);
        this.Speed=Speed;
        direction=new Point2D(0,0);
    }

    public void setDirection(Point2D dir){
        direction=dir;
    }
}
