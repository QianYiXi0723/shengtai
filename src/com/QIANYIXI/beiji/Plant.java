///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:    ArcticTundra.java
// File:               Plant.java
// Quarter:            CSE 11 WI26
//
// Author:             QIANYIXI
// Instructor's Name:  (你的导师名字)
//
///////////////////////////////////////////////////////////////////////////////
//                   STUDENTS WHO GET HELP COMPLETE THIS SECTION
// Persons:          (如果有同学帮忙，请在此说明)
// Online sources:   (如果参考了网上资料，请在此贴链接)
//////////////////////////// 80 columns wide //////////////////////////////////

package com.QIANYIXI.beiji;

public abstract class Plant extends Creature{
    private final double weeklyGrowthRate;//生长


    public Plant() {
        super();
        weeklyGrowthRate=0.0;
    }

    public Plant(double mass, double health, double locX, double locY, double weeklyGrowthRate) {
        super(mass, health, locX, locY);
        this.weeklyGrowthRate = weeklyGrowthRate;
    }

    public double getWeeklyGrowthRate() {
        return weeklyGrowthRate;
    }
}
