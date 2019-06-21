package dataStructure.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 * 
 * @author XinyuTian
 * @date 2017年6月18日
 **/
public class PoissonDiskDistribution {

    static int size = 30;
    static int startX = 0;
    static int startY = 0;
    static int distance = 3;
    static double allowError = 0.1;

    public static void main(String[] args) {
        // generate dots
//        int[][] matrix = orderedGeneration();
        int[][] matrix = boxRandomGeneration();
        // display result
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (matrix[i][j] == 0) {
                    System.out.print(" - ");
                } else {
                    System.out.print(" " + matrix[i][j] + " ");
                }
            }
            System.out.print("\n");
        }
    }

    /**
     * 复制生成法--平面上的点是某一个子平面中点的横、纵平移复制
     * 0. 给定一个已经填充完毕的'核'
     * 1. 将核放置在matrix的左上角
     * 2. 遍历'核'中的点，同时把这些点按照核的长宽映射到'核'外。例如：设给定的核平面长为len, 
     *      宽为wid，则对于'核'平面中的点(i,j)，它往横向映射的点为(i+len,j),(i+2len,j)...,
     *      往纵向映射的点为(i,j+wid),(i,j+2wid),...
     * 3. 每个点都映射完毕后，清洗边界区域。边界区域为以边界为对称轴，distance为辐射范围的矩形区域
     * 
     * @param coreMatrix
     * @return
     */
    public static int[][] duplicateRandomGeneration(int[][] coreMatrix) {
        int[][] matrix = new int[size][size];
        
        return matrix;
    }
    
    /**
     * 1. 将平面分割成若干子块
     * 2. 在子块中随机生成若干点
     * 3. 以子块中的点为核心填充其余空间
     * 
     * 
     * @return
     */
    public static int[][] boxRandomGeneration2() {
        int[][] matrix = new int[size][size];
        
        return matrix;
    }
    /**
     * 1. 将平面用distance为边长的正方形分割
     * 2. 每个正方形中随机生成一个点，并且保证相邻正方形中的点之间的距离大于等于distance
     * 3. 以子块中的点为核心填充其余空间
     * 
     * @return
     */
    public static int[][] boxRandomGeneration() {
        int[][] matrix = new int[size][size];
        int tempX = -1;
        int tempY = -1;
        int xIdx = 0;
        int yIdx = 0;
        while (true) {
            // generate dots
            tempX = (int) Math.round(distance*xIdx+distance*Math.random());
            tempY = (int) Math.round(distance*yIdx+distance*Math.random());
            if (tempY >= size) {
                break;
            }
            if (tempX >= size) {
                yIdx++;
                xIdx = 0;
                continue;
            }
            // check distance
            boolean tooShortDistance = checkDistance(tempX, tempY, matrix);
            if (tooShortDistance) {
                continue;
            }
            matrix[tempX][tempY] = 1;
            xIdx++;
        }
        // fill empty clot
        while (true) {
            boolean noInsertation = true;
            for(int x=0; x<size; x++) {
                for(int y=0; y<size; y++) {
                    if (matrix[x][y]==0) {
                        boolean tooShortDistance = checkDistance(x, y, matrix);
                        if (!tooShortDistance) {
                            matrix[x][y] = 1;
                            noInsertation = false;
                        }
                    }
                }
            }
            if (noInsertation) {
                break;
            }
        }
        return matrix;
    }
    /**
     * '有序点'生成算法简介
     * 1. 平面上所有的点用一个二维数组'matrix'维护和记录,其中点的下标表示点的坐标,
     * 元素值表示该点周围有几个相邻点,matrix[i][j]=0表示点(i,j)没有被填充
     * 2. 用两个序列'xList','yList'来分别记录已经填充的点的x和y坐标
     * 3. 人为选定一个坐标'(startX, startY)'作为起始中心点
     * 4. 每一轮扫描遍历matrix中的所有点，并且以'xList','yList'中记录的已填充点为中心计算距离，
     * 距离符合要求的点即被填充
     * 5. 如此循环'4',直到matrix在整轮扫描中都没有点可以被填充
     * 
     * @param disLower
     * @param disUpper
     */
    static int[][] orderedGeneration() {
        int[][] matrix = new int[size][size];
        List<Integer> xList = new ArrayList<>();
        List<Integer> yList = new ArrayList<>();
        double disLower = distance - allowError;
        double disUpper = distance + allowError;
        matrix[startX][startY]++;
        xList.add(startX);
        yList.add(startY);
        while (true) {
            boolean thereIsInsertion = false;
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (matrix[i][j] == 0) {
                        List<Integer> eq2CenX = new ArrayList<>();
                        List<Integer> eq2CenY = new ArrayList<>();
                        boolean thereLess2 = false;
                        int s = xList.size();
                        for (int cenIdx = 0; cenIdx < s; cenIdx++) {
                            int xCenter = xList.get(cenIdx);
                            int yCenter = yList.get(cenIdx);
                            double d = Math.sqrt(Math.pow((i - xCenter), 2)
                                    + Math.pow((j - yCenter), 2));
                            if (d >= disLower && d <= disUpper) {
                                eq2CenX.add(xCenter);
                                eq2CenY.add(yCenter);
                            }
                            if (d < disLower) {
                                thereLess2 = true;
                                break;
                            }
                        }
                        if (!thereLess2) {
                            if (eq2CenX.size() > 0) {
                                for (int eq2Idx = 0; eq2Idx < eq2CenX
                                        .size(); eq2Idx++) {
                                    matrix[i][j]++;
                                    matrix[eq2CenX.get(eq2Idx)][eq2CenY
                                            .get(eq2Idx)]++;
                                }
                                xList.add(i);
                                yList.add(j);
                                thereIsInsertion = true;
                            }
                        }
                    }
                }
            }
            if (!thereIsInsertion) {
                break;
            }
        }
        matrix[startX][startY]--;
        return matrix;
    }

    static boolean checkDistance(int centerX, int centerY, int[][] matrix) {
        boolean tooShortDistance = false;
        
        int xLower = (centerX-distance)>0?(int)(centerX-distance):0;
        int xUpper = (centerX+distance)>(size-1)?(size-1):(int)(centerX+distance);
        int yLower = (centerY-distance)>0?(int)(centerY-distance):0;
        int yUpper = (centerY+distance)>(size-1)?(size-1):(int)(centerY+distance);
        checkDistance:
        for(int x=xLower;x<=xUpper;x++){
            for(int y=yLower; y<=yUpper; y++) {
                if (matrix[x][y]==0) {
                    continue;
                }
                if (getDistance(centerX, centerY, x, y)<distance) {
                    tooShortDistance = true;
                    break checkDistance;
                }
            }
        }
        
        return tooShortDistance;
    }
    
    static double getDistance(int centerX, int centerY, int x, int y) {
        return Math.sqrt(Math.pow((x - centerX), 2) + Math.pow((y - centerY), 2)); 
    }
}
