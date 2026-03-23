///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:    ArcticTundra.java
// File:               Animal.java
// Quarter:            CSE 11 WI26
//
// Author:             QIANYIXI
// Instructor's Name:
//
///////////////////////////////////////////////////////////////////////////////
//                   STUDENTS WHO GET HELP COMPLETE THIS SECTION
// Persons:
// Online sources:
//////////////////////////// 80 columns wide //////////////////////////////////

package com.QIANYIXI.beiji;

public abstract class Animal extends Creature{
    private final double weeklyLossRate;
    private final double actionRadius;

    protected Animal(){
        super();
        this.weeklyLossRate = 0.0;  // 设置每周生命值减少量为 0.0
        this.actionRadius = 0.0; // 设置行动半径为 0.0
    }
    protected Animal(double mass, double health, double locX,
                  double locY,double weeklyLossRate, double actionRadius) {
        super(mass, health, locX, locY);
        this.weeklyLossRate = weeklyLossRate;
        this.actionRadius = actionRadius;
    }

    public double getActionRadius() {
        return actionRadius;
    }

    public double getWeeklyLossRate() {
        return weeklyLossRate;
    }

    //繁殖
    public abstract boolean triggerReproduce();

    //捕食
    public abstract boolean preyOn(Creature other);

    //进食
    public abstract void feedOn(Creature other);
}
