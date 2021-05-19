package com.mygdx.game;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class LeaderBoard {
    public String nickname;
    public double elbrium;
    public LeaderBoard(String nickname,double elbrium){
        this.nickname = nickname;
        this.elbrium = elbrium;
    }
    public LeaderBoard(){}

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public double getElbrium() {
        return elbrium;
    }

    public void setElbrium(double elbrium) {
        this.elbrium = elbrium;
    }

}
