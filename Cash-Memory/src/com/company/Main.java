package com.company;

public class Main {

    public static final int CONST = 50;
    public static final int SIZE_ARRAY = 100000000;

    public static int[] generateArray(int size ) {
        int[] array = new int[size];
        for (int i = 0; i < array.length; i++) {
            array[i] = 128;
        }
        return array;
    }

    public static void main(String[] args) {
        int[] array = generateArray(SIZE_ARRAY);
        int temp;
        long startTime;
        long endTime;
        long checkTime=0;
        long check = 0;
        for (int i = 0; i < array.length; i++) {
            startTime = System.currentTimeMillis();
            temp = array[i];
            temp+=CONST;
            array[i]=temp;
            endTime = System.currentTimeMillis();
            long timeSpent = endTime - startTime;
            if(checkTime<timeSpent) {
                check++;
            }
            checkTime=timeSpent;
        }
        double cacheLength = ((double)array.length/check)*32;
        System.out.println(cacheLength/(1024*8)+" KB");
    }
}
