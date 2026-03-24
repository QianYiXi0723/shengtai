package com.QIANYIXI.beiji;
///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:    ArcticTundra.java
// File:               Fox.java
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
import java.util.ArrayList;
import java.util.Random;
public class Fox extends Animal {
    private ArrayList<Fox> offspring;

    public Fox() {
        super();
        offspring = new ArrayList<>();
    }

    public Fox(double mass, double health, double locX,
               double locY,boolean isOffspring) {
        super(mass, health, locX, locY,
                isOffspring ? 0.04 : 0.02, 1000.0);
        offspring = new ArrayList<>();
        }


    /*
     * 每周活动
     * 减去生命值，并判断是否死亡；减少健康；判定子代
     */
    @Override
    public void advanceWeek() throws CreatureDeathException {
        double newMass= getMass() * (1.0 - getWeeklyLossRate()) - 10.0;
        setMass(newMass);
        if (getMass() <= 0.0) {
            throw new CreatureDeathException("a fox died");
        }
        double newHealth =  Math.max(getHealth() - 0.05, 0.0);
        setHealth(newHealth);
        for (int i = offspring.size() - 1; i >= 0; i--) {
            Fox child = offspring.get(i);
            try {
                child.advanceWeek();
            } catch (CreatureDeathException e) {
                offspring.remove(i);
            }
        }
    }
    /*
     * 判定是否可以繁殖
     * 1.子代数量小于3
     * 2.健康值小于0.5
     * 3.随机数小于0.1
     */
    @Override
    public boolean triggerReproduce() {
        if (offspring.size() >= 3 || getHealth() < 0.5) {
            return false;
        } else {
            Random rand = new Random();
            double r = rand.nextDouble();

            if (r < 0.1) {
                return true;
            } else {
                return false;
            }
        }
    }
    /*
     * 创建子代
     */
    public void giveBirth () {
            double parentMass = getMass();
            double babyMass = parentMass * 0.2;
            Fox baby = new Fox(babyMass, getHealth(),
                    getLocX(), getLocY(), true);
            setMass(parentMass - babyMass);
            offspring.add(baby);
    }
    /*
    * 捕食
    *
    * @param other 捕食对象
    * @return true:捕食成功 false:捕食失败
    */

        @Override
    public boolean preyOn(Creature other){
        if(!(other instanceof Lemming) && !(other instanceof Crowberry)) {
            return false;
    }
            if (!other.isAlive()) {
                return false;
            }
            double successRate= 0.0;
            if (other instanceof Lemming){
                successRate = 0.3 + 0.2 * this.compareTo(other);
            }
            if (other instanceof Crowberry){
                successRate = 0.2 + 0.2 * other.compareTo(this);
            }
            Random rand = new Random();
            double r = rand.nextDouble();
            if (r > successRate){
                return false;
            }
            feedOn(other);
            return true;
        }
    /*
    * 进食
    * 减去其他生物对象质量，并增加自身质量
    *
    * @param other 进食对象
    */
    @Override
    public void feedOn(Creature other) {
        double reducedMass = 0.0;
        if (other instanceof Lemming) {
                reducedMass = other.getMass();
                other.setMass(0.0);
        } else if (other instanceof Crowberry) {
                reducedMass = other.getMass() * 0.3;
                other.setHealth(other.getHealth() * (1.0 - 0.3));
                other.setMass(other.getMass() * (1.0 - 0.3));
        }
        if (offspring.size() >= 1) {
            setMass(getMass() + reducedMass * 0.3);
            for (int i = 0; i < offspring.size(); i++) {
                Fox baby = offspring.get(i);
                baby.setMass(baby.getMass()
                            + reducedMass * 0.3 / offspring.size());
            }
            }else{
                setMass(getMass() + reducedMass*0.5);
            }
            setHealth(getHealth() + 0.2);
            if (getHealth()> 1.0){
                setHealth(1.0);
            }
        }
    /*
    * 添加子代
    *
    * @param other 子代
    */
    public void addOffspring(Fox other){
            offspring.add(other);
    }
    /*
    * 获取所有子代
    *
    * @return 所有子代
    */
    public ArrayList<Fox> getAllOffspring(){
            return new ArrayList<>(offspring);
    }
    }
