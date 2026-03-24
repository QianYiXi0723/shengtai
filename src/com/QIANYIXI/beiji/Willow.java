///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:    ArcticTundra.java
// File:               Willow.java
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

public class Willow extends  Plant{
    public Willow() {
        super();
    }

    public Willow(double mass, double health, double locX, double locY) {
        super(mass, health, locX, locY, 0.4);
    }
    /*
     *  clone子代
     *
     *  @return Willow对象
     */
    @Override
    public Willow clone() throws CloneNotSupportedException{
        return new Willow(this.getMass(), this.getHealth(),
                this.getLocX(), this.getLocY());
    }
    /*
     * 每周活动
     * 减去生命值，并判断是否死亡；增加健康
     */
    @Override
    public void advanceWeek() throws CreatureDeathException{
        setMass(getMass()*(1+getWeeklyGrowthRate())+10.0);
        if (getMass()>=160.0){
            setMass(160.0);
        }
        if (getHealth()<=0){
            throw new CreatureDeathException("A willow died");
        }
        setHealth(getHealth()+0.6);
        if (getHealth()>=1.0) {
            setHealth(1.0);
        }
    }
}
