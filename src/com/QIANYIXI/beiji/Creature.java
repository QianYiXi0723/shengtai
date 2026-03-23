///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:    ArcticTundra.java
// File:               Creature.java
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

public abstract class Creature {
    private double mass;
    private double health;
    private double locX;
    private double locY;

    public Creature(double mass, double health, double locX, double locY) {
        this.mass = mass;
        this.health = health;
        this.locX = locX;
        this.locY = locY;
    }

    public Creature() {
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public double getLocX() {
        return locX;
    }

    public void setLocX(double locX) {
        this.locX = locX;
    }

    public double getLocY() {
        return locY;
    }

    public void setLocY(double locY) {
        this.locY = locY;
    }
   /*
    * 判断Creature是否还活着
    *存活定义为质量大于0
    *
    * @return true:Creature还活着 false:Creature已经死亡
    */
    public boolean isAlive(){
        if(mass>0) {
            return true;
        } else{
            return false;}
    }
    /*
     *根据生命值比较当前生物对象与另一生物对象
     *
     * @param other 另一生物对象
     * @return 1:当前生物对象比另一生物对象大 0:当前生物对象与另一生物对象相等
     *            -1:当前生物对象比另一生物对象小
     */
    public int compareTo(Creature other){
        if(this.health>other.health){
            return 1;
        } else if (this.health==other.health) {
            return 0;
        }else {
            return -1;
        }
    }
    /*
     * 计算当前生物对象与另一生物对象之间的距离
     *
     * @param other 另一生物对象
     * @return 两个生物对象之间的距离
     */
    public double distanceTo(Creature other) {
        double dx = this.getLocX() - other.getLocX();
        double dy = this.getLocY() - other.getLocY();
        return Math.sqrt(dx * dx + dy * dy);
    }
    /*
     * 判断当前生物对象与另一生物对象是否是同一种生物
     *
     * @param other 另一生物对象
     * @return true:是同一种生物 false:不是同一种生物
     */
        public boolean sameSpecies(Creature other){
            return this.getClass().getName() .equals(other.getClass().getName()) ;
        }
     /*
      * 获取当前生物对象的信息
      *
      * @return 当前生物对象的信息
      */
    public String toString(){
        return "(" + getClass().getName() + ")" + " mass: " + getMass() +
                "; health: " + getHealth() + "; locX: " + getLocX() +
                "; locY: " + getLocY();
    }
    /*
     * 抽象方法，用于更新生物对象的状态
     *
     * @throws CreatureDeathException 当生物对象死亡时抛出
     */
    public abstract void advanceWeek() throws CreatureDeathException;
}
