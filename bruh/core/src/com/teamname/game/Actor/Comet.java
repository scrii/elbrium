package com.teamname.game.Actor;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.teamname.game.GraphicsObj.Animation;
import com.teamname.game.GraphicsObj.GraphicsObj;
import com.teamname.game.Main;
import com.teamname.game.Screens.GameSc;

import java.util.Timer;
import java.util.TimerTask;

import Tools.Log;
import Tools.Point2D;

public class Comet extends Actor {

    private Animation cometAnimation;
    private static final float width = GameSc.entityRad*4;
    private static final float height = GameSc.entityRad*2;
    private boolean isOut;
    private int rand;
    private Application Log = Gdx.app;

    public Comet(Point2D position, float Speed){
        super(position,Speed);
        direction=new Point2D(1,0);
        cometAnimation=new Animation(new TextureRegion(Main.comet_fr1),4,4,3);
        //timerTask();
    }

    public void timerTask(){
        Timer timer=new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if(isOut){updatePosition();
                cometAnimation.setSpriteRotation(rand);}
            }
        };

        timer.scheduleAtFixedRate(task,0,1000);
    }

    private void updatePosition(){
        position.setPoint(new Point2D(-100,(float)(Math.random()*Main.BACKGROUND_HEIGHT)));
    }



    @Override
    public void update(){
        //cometAnimation.update(0.1f);
        position.add(direction.getX()*Speed,direction.getY()*Speed);
        //Gdx.app.log("COMETpos",cometPosX+"");
        isOut = (position.getX()<0 || position.getY()>Main.BACKGROUND_HEIGHT
                || position.getX()>Main.BACKGROUND_WIDTH || position.getY()<0);
        if(!isOut){cometAnimation.update(0.1f);}
        else timerTask();
        Log.log("pos", position.getX()+" "+position.getY());
    }

    @Override
    public void draw(SpriteBatch btch){

        btch.draw(cometAnimation.getFrame(),position.getX(),position.getY(), width,height);
    }

    public static TextureRegion getCometRegion(Animation animation){
        //Gdx.app.error("switch",animation.getSceneCount()+"");
        switch (animation.getSceneCount()){
            case 1: return new TextureRegion(Main.comet_fr1);
            case 2: return new TextureRegion(Main.comet_fr2);
            case 3: return new TextureRegion(Main.comet_fr3);
        }
        return new TextureRegion(Main.err);
    }





}
