///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:    ArcticTundra.java
// File:               ArcticTundra.java
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

import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.io.File;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.Set;
import java.util.HashSet;
public class ArcticTundra {
    private ArrayList<Creature> allCreatures;
    private int currentWeek;
    private Random rand;
    private static final double MAX_LOC_X = 5000.0;
    private static final double MAX_LOC_Y = 5000.0;
    private static final double FOX_STANDARD_MASS = 3000.0;
    private static final double LEMMING_STANDARD_MASS = 50.0;
    private static final double CROWBERRY_STANDARD_MASS = 1000.0;
    private static final double WILLOW_STANDARD_MASS = 80.0;
    private int initialNumLemming;


    public int getCurrentWeek() {
        return currentWeek;
    }

    public void setCurrentWeek(int currentWeek) {
        this.currentWeek = currentWeek;
    }

    public ArcticTundra() {
        allCreatures = new ArrayList<>();
        currentWeek = 0;
        rand = new Random();
    }

    public ArcticTundra(int numFox, int numLemming, int numCrowberry, int numWillow) {
        allCreatures = new ArrayList<>();
        currentWeek = 0;
        rand = new Random();
        for (int i = 0; i < numFox; i++) {
            double x = rand.nextDouble() * MAX_LOC_X;   // 随机X坐标 [0, MAX_LOC_X)
            double y = rand.nextDouble() * MAX_LOC_Y;   // 随机Y坐标 [0, MAX_LOC_Y)
            Fox fox = new Fox(FOX_STANDARD_MASS, 1.0, x, y, false); // 质量用标准值，健康满值1.0
            allCreatures.add(fox);
        }

        // 添加指定数量的旅鼠
        for (int i = 0; i < numLemming; i++) {
            double x = rand.nextDouble() * MAX_LOC_X;
            double y = rand.nextDouble() * MAX_LOC_Y;
            Lemming lemming = new Lemming(LEMMING_STANDARD_MASS, 1.0, x, y);
            allCreatures.add(lemming);
        }

        // 添加指定数量的云莓
        for (int i = 0; i < numCrowberry; i++) {
            double x = rand.nextDouble() * MAX_LOC_X;
            double y = rand.nextDouble() * MAX_LOC_Y;
            Crowberry crowberry = new Crowberry(CROWBERRY_STANDARD_MASS, 1.0, x, y);
            allCreatures.add(crowberry);
        }

        // 添加指定数量的柳树
        for (int i = 0; i < numWillow; i++) {
            double x = rand.nextDouble() * MAX_LOC_X;
            double y = rand.nextDouble() * MAX_LOC_Y;
            Willow willow = new Willow(WILLOW_STANDARD_MASS, 1.0, x, y);
            allCreatures.add(willow);
        }
        this.initialNumLemming = numLemming;

    }

    /*
     *所有 Fox 对象及其所有后代的质量总和
     *
     * @return 总质量
     */
    public double getTotalFoxMass() {
        double TotalMass = 0;
        for (Creature Creatures : allCreatures) {
            if (Creatures instanceof Fox) {
                Fox fox = (Fox) Creatures;
                TotalMass += Creatures.getMass();
                for (Fox samllfox : fox.getAllOffspring()) {
                    TotalMass += samllfox.getMass();
                }
            }
        }
        return TotalMass;
    }

    /*
     *所有 Lemming 对象的质量总和
     *
     * @return 总质量
     */
    public double getTotalLemmingMass() {
        double TotalMass = 0;
        for (Creature Creatures : allCreatures) {
            if (Creatures instanceof Lemming) {
                TotalMass += Creatures.getMass();
            }
        }
        return TotalMass;
    }

    /*
     *所有 Crowberry 对象的质量总和
     *
     * @return 总质量
     */
    public double getTotalCrowberryMass() {
        double TotalMass = 0;
        for (Creature Creatures : allCreatures) {
            if (Creatures instanceof Crowberry) {
                TotalMass += Creatures.getMass();
            }
        }
        return TotalMass;
    }

    /*
     *所有 Willow 对象的质量总和
     *
     * @return 总质量
     */
    public double getTotalWillowMass() {
        double TotalMass = 0;
        for (Creature Creatures : allCreatures) {
            if (Creatures instanceof Willow) {
                TotalMass += Creatures.getMass();
            }
        }
        return TotalMass;
    }
    /*
     *按周数进行模拟
     */
    public void advanceNWeeks(int n) {
        for (int week = 0; week < n; week++) {
            // 创建静态副本，避免在遍历过程中修改 allCreatures
            ArrayList<Creature> copy = new ArrayList<>(allCreatures);
            // 记录本周已经处理过的猎物（防止重复处理）
            Set<Creature> processedPrey = new HashSet<>();
            double totalLemmingMass = getTotalLemmingMass();
            double threshold = 0.2 * initialNumLemming * LEMMING_STANDARD_MASS;
            boolean skipLemmingPrey = totalLemmingMass < threshold;
            for (Creature c : copy) {
                // ---------- 1. 生命周期 advanceWeek ----------
                try {
                    c.advanceWeek();
                } catch (CreatureDeathException e) {
                    allCreatures.remove(c);
                    continue; // 死亡，跳过繁殖和捕食
                }

                // ---------- 2. 繁殖 ----------
                if (c instanceof Fox) {
                    Fox fox = (Fox) c;
                    if (fox.triggerReproduce()) {
                        fox.giveBirth();
                    }
                } else if (c instanceof Lemming) {
                    if (skipLemmingPrey) {
                        continue; // 跳过捕食
                    }
                    Lemming lemming = (Lemming) c;
                    if (lemming.triggerReproduce()) {
                        try {
                            Lemming baby = (Lemming) lemming.clone();
                            allCreatures.add(baby);
                        } catch (CloneNotSupportedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }

                // ---------- 3. 捕食（仅对动物） ----------
                if (c instanceof Animal) {
                    Animal predator = (Animal) c;
                    // 创建猎物副本，避免在遍历 allCreatures 时修改它
                    ArrayList<Creature> preyCopy = new ArrayList<>(allCreatures);
                    for (Creature prey : preyCopy) {
                        // 跳过已经处理过的猎物
                        if (processedPrey.contains(prey)) {
                            continue;
                        }
                        if (predator.distanceTo(prey) < predator.getActionRadius()) {
                            predator.preyOn(prey);
                            if (!prey.isAlive()) {
                                processedPrey.add(prey); // 标记已处理
                                if (predator instanceof Fox) {
                                    // Fox 只会捕食 Lemming（Crowberry 不会死亡）
                                    if (prey instanceof Lemming) {
                                        allCreatures.remove(prey);
                                    }
                                } else if (predator instanceof Lemming) {
                                    // Lemming 捕食 Willow（Crowberry 不会死亡）
                                    if (prey instanceof Willow) {
                                        try {
                                            Willow newWillow = ((Willow) prey).clone();
                                            newWillow.setMass(1.0);
                                            allCreatures.add(newWillow);
                                            allCreatures.remove(prey);
                                        } catch (CloneNotSupportedException e) {
                                            throw new RuntimeException(e);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            currentWeek++;
        }
    }
    /*
     * 将苔原的当前状态保存到文件中
     *
     * @param filePath 输出文件路径
     */
    public void saveToFile(String filePath) {
        // try-with-resources 自动关闭 PrintWriter
        try (PrintWriter writer = new PrintWriter(filePath)) {
            for (Creature c : allCreatures) {
                String species;
                double mass = c.getMass();
                double health = c.getHealth();
                double locX = c.getLocX();
                double locY = c.getLocY();
                double off1 = -1.0, off2 = -1.0, off3 = -1.0;

                if (c instanceof Fox) {
                    species = "Fox";
                    Fox fox = (Fox) c;
                    List<Fox> offspring = fox.getAllOffspring();
                    int size = offspring.size();
                    if (size > 0) off1 = offspring.get(0).getMass();
                    if (size > 1) off2 = offspring.get(1).getMass();
                    if (size > 2) off3 = offspring.get(2).getMass();
                } else if (c instanceof Lemming) {
                    species = "Lemming";
                } else if (c instanceof Crowberry) {
                    species = "Crowberry";
                } else if (c instanceof Willow) {
                    species = "Willow";
                } else {
                    continue; // 忽略未知类型
                }

                String line = String.format("%s %.1f %.1f %.1f %.1f %.1f %.1f %.1f",
                        species, mass, health, locX, locY, off1, off2, off3);
                writer.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("文件无法创建");
            // 文件无法创建时，根据题目要求，无需执行任何操作
            // 可选择性打印错误信息，但不影响程序运行
            // e.printStackTrace();
        }
    }
    /*
     * 通过从文件加载数据构造一个 ArcticTundra 对象。
     *
     * @param filePath 输入文件路径
     */
    public ArcticTundra(String filePath) {
        this(); // 调用无参构造器初始化字段
        try (Scanner fileScanner = new Scanner(new File(filePath))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                // 使用行扫描器解析
                try (Scanner lineScanner = new Scanner(line)) {
                    String species = lineScanner.next();
                    double mass = lineScanner.nextDouble();
                    double health = lineScanner.nextDouble();
                    double locX = lineScanner.nextDouble();
                    double locY = lineScanner.nextDouble();
                    double off1 = lineScanner.nextDouble();
                    double off2 = lineScanner.nextDouble();
                    double off3 = lineScanner.nextDouble();

                    Creature creature = null;
                    switch (species) {
                        case "Fox":
                            Fox fox = new Fox(mass, health, locX, locY, false);
                            // 添加后代
                            double[] offMasses = {off1, off2, off3};
                            for (double offMass : offMasses) {
                                if (offMass > 0) { // 后代质量应为正数
                                    Fox offspring = new Fox(offMass, 0.0, locX, locY, true);
                                    fox.addOffspring(offspring);
                                }
                            }
                            creature = fox;
                            break;
                        case "Lemming":
                            creature = new Lemming(mass, health, locX, locY);
                            break;
                        case "Crowberry":
                            creature = new Crowberry(mass, health, locX, locY);
                            break;
                        case "Willow":
                            creature = new Willow(mass, health, locX, locY);
                            break;
                        default:
                            // 未知物种，跳过
                            continue;
                    }
                    if (creature != null) {
                        allCreatures.add(creature);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            // 按作业说明，捕获异常后不执行任何操作，苔原保持为空
        }
        // currentWeek 保持为 0（未保存，这是允许的）
    }

    /*
     * 单元测试方法。必须覆盖所有构造器和方法至少指定次数。
     */
    @SuppressWarnings("checkstyle:MagicNumber")
    public static void unitTests() {
        // 1. 测试无参构造器
        ArcticTundra tundra1 = new ArcticTundra();
        System.out.println("测试无参构造器完成");

        // 2. 测试带参数的构造器
        ArcticTundra tundra2 = new ArcticTundra(2, 5, 3, 4);
        System.out.println("测试带参数构造器完成");

        // 3. 测试从文件加载的构造器（至少三次）
        // 先创建一些文件用于测试
        ArcticTundra testSave = new ArcticTundra(1, 1, 1, 1);
        testSave.saveToFile("test_save1.txt");
        ArcticTundra loaded1 = new ArcticTundra("test_save1.txt");
        System.out.println("第一次加载测试完成");

        // 再保存一次不同数据，加载第二次
        ArcticTundra testSave2 = new ArcticTundra(3, 0, 0, 2);
        testSave2.saveToFile("test_save2.txt");
        ArcticTundra loaded2 = new ArcticTundra("test_save2.txt");
        System.out.println("第二次加载测试完成");

        // 第三次加载测试，使用空文件或不存在文件（应创建空苔原）
        ArcticTundra loaded3 = new ArcticTundra("nonexistent.txt");
        System.out.println("第三次加载测试完成");

        // 4. 测试 advanceNWeeks 至少三次
        ArcticTundra tundraAdv = new ArcticTundra(2, 5, 3, 4);
        tundraAdv.advanceNWeeks(1);
        tundraAdv.advanceNWeeks(2);
        tundraAdv.advanceNWeeks(3);
        System.out.println("advanceNWeeks 测试完成");

        // 5. 测试 saveToFile 至少三次
        ArcticTundra tundraSave = new ArcticTundra(2, 5, 3, 4);
        tundraSave.saveToFile("save1.txt");
        tundraSave.saveToFile("save2.txt");
        tundraSave.saveToFile("save3.txt");
        System.out.println("saveToFile 测试完成");

        // 可选：打印一些统计验证（根据作业建议，可以打印总质量等）
        System.out.println("总狐狸质量: " + tundra2.getTotalFoxMass());
        System.out.println("总旅鼠质量: " + tundra2.getTotalLemmingMass());
        System.out.println("总岩高兰质量: " + tundra2.getTotalCrowberryMass());
        System.out.println("总柳树质量: " + tundra2.getTotalWillowMass());
    }








    /*
    *自定义初始值
    */
    public static void runInteractive() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("请输入狐狸数量: ");
        int numFox = scanner.nextInt();
        System.out.print("请输入旅鼠数量: ");
        int numLemming = scanner.nextInt();
        System.out.print("请输入岩高兰数量: ");
        int numCrowberry = scanner.nextInt();
        System.out.print("请输入柳树数量: ");
        int numWillow = scanner.nextInt();
        System.out.print("请输入模拟周数: ");
        int weeks = scanner.nextInt();

        ArcticTundra tundra = new ArcticTundra(numFox, numLemming, numCrowberry, numWillow);

        System.out.println("===== 初始状态 (第 0 周) =====");
        printStatus(tundra);

        for (int w = 1; w <= weeks; w++) {
            try {
                tundra.advanceNWeeks(1);
            } catch (Exception e) {
                System.out.println("第 " + w + " 周模拟时发生异常：" + e.getMessage());
                e.printStackTrace();
                break;  // 或者 continue，根据你希望的处理方式决定
            }
            System.out.println("===== 第 " + tundra.getCurrentWeek() + " 周 =====");
            printStatus(tundra);
        }

        scanner.close();
    }

    /*
     *打印每周运行成果
     */
    private static void printStatus(ArcticTundra tundra) {
        System.out.printf("狐狸总质量: %.2f\n", tundra.getTotalFoxMass());
        System.out.printf("旅鼠总质量: %.2f\n", tundra.getTotalLemmingMass());
        System.out.printf("岩高兰总质量: %.2f\n", tundra.getTotalCrowberryMass());
        System.out.printf("柳树总质量: %.2f\n", tundra.getTotalWillowMass());
        System.out.println();
    }









    /*
     * 主方法，调用 unitTests。
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            unitTests();        // 默认运行单元测试（提交时使用）
        } else {
            runInteractive();   // 传任意参数时进入交互模式
        }
    }

}