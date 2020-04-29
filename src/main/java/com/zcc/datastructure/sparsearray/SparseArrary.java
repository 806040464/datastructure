package com.zcc.datastructure.sparsearray;

import java.io.*;

public class SparseArrary {

    public static void main(String[] args) {
        //创建棋盘原始数组
        //0：表示没有棋子；1：表示白棋；2：表示黑棋
        int[][] chessArray = new int[11][11];
        chessArray[1][2] = 1;
        chessArray[2][3] = 2;
        System.out.println("原始数组：");
        for (int[] ints : chessArray) {
            for (int anInt : ints) {
                System.out.printf("%d\t", anInt);
            }
            System.out.println();
        }
        //二维数组转为稀疏数组
        //1、遍历原始的二维数组，得到有效数据的个数sum
        //2、根据sum就可创建稀疏数组sparsearray int[sum+1][3]
        //3、将二维数组有效数据存入稀疏数组
        int sum = 0;
        for (int i = 0; i < chessArray.length; i++) {
            for (int j = 0; j < chessArray.length; j++) {
                if (chessArray[i][j] != 0) {
                    sum++;
                }
            }
        }
        int[][] sparseArray = new int[sum + 1][3];
        sparseArray[0][0] = 11;
        sparseArray[0][1] = 11;
        sparseArray[0][2] = sum;
        int count = 0;
        for (int i = 0; i < chessArray.length; i++) {
            for (int j = 0; j < chessArray.length; j++) {
                if (chessArray[i][j] != 0) {
                    count++;
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = chessArray[i][j];
                }
            }
        }
        System.out.println("稀疏数组：");
        for (int i = 0; i < sparseArray.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n", sparseArray[i][0], sparseArray[i][1], sparseArray[i][2]);
        }
        //将稀疏数组存入文件
        File file = new File(System.getProperty("user.dir") + "/sparseArrary.out");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            for (int i = 0; i < sparseArray.length; i++) {
                for (int j = 0; j < sparseArray.length; j++) {
                    fos.write((sparseArray[i][j] + "\t").getBytes());
                }
                fos.write("\n".getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null == fos) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //将稀疏数组从文件中读取
        int[][] sparseArrayToFile = new int[sum + 1][3];
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(new File(System.getProperty("user.dir") + "/sparseArrary.out")));
            String line;
            int row = 0;
            while ((line = br.readLine()) != null) {
                String[] split = line.split("\t");
                for (int i = 0; i < split.length; i++) {
                    sparseArrayToFile[row][i] = Integer.parseInt(split[i]);
                }
                row++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null == br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("从文件读取稀疏数组：");
        for (int i = 0; i < sparseArrayToFile.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n", sparseArrayToFile[i][0], sparseArrayToFile[i][1], sparseArrayToFile[i][2]);
        }
        //稀疏数组转二维数组
        //1、读取稀疏数组第一行，创建原始二维数组
        //2、读取稀疏数组后几行数据并赋值给原始数组即可
        int[][] ints = new int[sparseArrayToFile[0][0]][sparseArrayToFile[0][1]];
        for (int i = 1; i <= sparseArrayToFile[0][2]; i++) {
            ints[sparseArrayToFile[i][0]][sparseArrayToFile[i][1]] = sparseArrayToFile[i][2];
        }
        System.out.println("从文件读取稀疏数组转二维数组：");
        for (int[] anInt : ints) {
            for (int i : anInt) {
                System.out.printf("%d\t", i);
            }
            System.out.println();
        }
    }
}
