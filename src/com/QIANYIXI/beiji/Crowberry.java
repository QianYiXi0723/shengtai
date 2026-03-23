///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:    ArcticTundra.java
// File:               Crowberry.java
// Quarter:            CSE 11 WI26
//
// Author:             QIANYIXI
// Instructor's Name:  (你的导师名字)
//
///////////////////////////////////////////////////////////////////////////////
//                   STUDENTS WHO GET HELP COMPLETE THIS SECTION
// Persons:          None
// Online sources:   None
//////////////////////////// 80 columns wide //////////////////////////////////
package com.QIANYIXI.beiji;

public class Crowberry extends Plant {
    public Crowberry() {
        super();
    }

    public Crowberry(double mass, double health, double locX, double locY) {
        super(mass, health, locX, locY, 0.2);
    }
    /*
     * 每周活动
     * 增加质量；增加健康
     */
    @Override
    public void advanceWeek() throws CreatureDeathException{
        setMass(getMass()*(1+getWeeklyGrowthRate())+20.0);
        if (getMass()>=2000.0){
            setMass(2000.0);
        }
        setHealth(getHealth()+0.2);
        if (getHealth()>=1.0) {
            setHealth(1.0);
        }
    }
}
