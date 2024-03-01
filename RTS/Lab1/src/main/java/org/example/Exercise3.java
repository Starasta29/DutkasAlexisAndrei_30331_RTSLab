package org.example;

import java.util.Random;
import java.util.Arrays;
public class Exercise3 {
    public static void main(String[] args) {
        Random nr = new Random();
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = nr.nextInt();
        }
        Arrays.sort(arr);
        for (int i : arr) {
            System.out.println(i + " ");
        }
    }
}
