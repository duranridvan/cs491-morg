package com.aselsan.targettracking.test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Test {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {

		Scanner scan = new Scanner(new File("data.txt"));
		int cnt =0;
		String maxTime="",minTime="";
		int max = 20,min=20;
		while(scan.hasNext()){
			scan.next();scan.next();
			String time = scan.next();
			int x = scan.nextInt();
			if(x > max) {max = x;maxTime = time;}
			if(x < min) {min = x;minTime = time;} 
			if(++cnt == 40){
				if(max - min >= 8)
					System.out.println(maxTime + "\t" + max);
				max = min = 20;
				cnt = 0;
			}
		}

	}
}
