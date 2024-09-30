package edu.hut.fileasedemo.test;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class Solution {

    /* Write Code Here */
    public int numberOfPatrolBlocks(int[][] block) {
        int count = 0;
        for(int i = 0;i<block.length;i++){
            for(int j = 0;j<block[i].length;j++){
                if(block[i][j] == 0){
                    count++;
                }
            }
        }
        return count;

    }
}

public class Main {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int res;
    
        int block_rows = 0;
        int block_cols = 0;
        block_rows = in.nextInt();
        block_cols = in.nextInt();
        
        int[][] block = new int[block_rows][block_cols];
        for(int block_i=0; block_i<block_rows; block_i++) {
            for(int block_j=0; block_j<block_cols; block_j++) {
                block[block_i][block_j] = in.nextInt();
            }
        }

        if(in.hasNextLine()) {
          in.nextLine();
        }


        res = new Solution().numberOfPatrolBlocks(block);
        System.out.println(String.valueOf(res));    

    }
}
