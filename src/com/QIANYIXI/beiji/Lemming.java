///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:    ArcticTundra.java
// File:               Lemming.java
// Quarter:            CSE 11 WI26
//
// Author:             QIANYIXI
// Instructor's Name:
//
///////////////////////////////////////////////////////////////////////////////
//                   STUDENTS WHO GET HELP COMPLETE THIS SECTION
// Persons:          None
// Online sources:   None
//////////////////////////// 80 columns wide //////////////////////////////////
package com.QIANYIXI.beiji;
import java.util.Random;
public class Lemming extends Animal {
    public Lemming() {
        super();
    }

    public Lemming(double mass, double health, double locX, double locY) {
        super(mass, health, locX, locY, 0.1, 500.0);
    }

    /*
     * 每周活动
     * 减去生命值，并判断是否死亡；减少健康；判定子代
     */
    @Override
    public void advanceWeek() throws CreatureDeathException {
        setMass(getMass() * (1.0 - getWeeklyLossRate()) - 2.0);
        if (getMass() <= 0.0) {
            throw new CreatureDeathException("A lemming died");
        }
        setHealth(Math.max(0.0, getHealth() - 0.2));
    }
    /*
     * 判定是否繁殖
     * 随机数小于0.3则返回true
     */
    @Override
    public boolean triggerReproduce(){
        if(getHealth()<0.5){
            return false;
        }else{
            Random rand = new Random();
            double r = rand.nextDouble();
            if (r <= 0.3){
                return true;
            }else{
                return false;
            }
        }
    }
    /*
     *生育幼崽
     */
    @Override
    public Lemming clone() throws CloneNotSupportedException{
        return new Lemming(this.getMass(), this.getHealth(),
                           this.getLocX(), this.getLocY());
    }
    /*
     * 捕食
     *
     * @param other 待捕食的生物对象
     * @return true:捕食成功 false:捕食失败
     * 捕食成功则调用feedOn方法
     */
    @Override
    public boolean preyOn(Creature other) {
        if (!(other instanceof Crowberry) && !(other instanceof Willow)) {
            return false;
        }else if(other.getMass()==0) {
            return false;
        }else if(other instanceof Crowberry){
            double success = Math.random();
            if (success <= ( 0.2 + 0.2 * other.compareTo(this))) {
                feedOn(other);
                return true;
            }else{
                return false;
            }
        }else if(other instanceof Willow){
            double success = Math.random();
            if (success <= 0.5) {
                feedOn(other);
                return true;
            }else{
                return false;
            }
        }else {
            return false;
        }
    }
    /*
     * 进食
     * 减去其他生物对象质量，并增加自身质量
     *
     * @param other 待进食的生物对象
     */
    @Override
    public void feedOn(Creature other){
        double reducedMass = 0.0;
        if (other instanceof Crowberry){
            reducedMass = other.getMass()*0.05;
            other.setMass(other.getMass()*(1-0.05));
            other.setHealth(other.getHealth()*(1-0.05));
        }else{
            reducedMass = other.getMass();
            other.setMass(0.0);
        }
        setMass(getMass() + reducedMass*0.4);
        setHealth(getHealth()+0.2);
        if (getHealth()>1.0){
            setHealth(1.0);
        }
    }
}
