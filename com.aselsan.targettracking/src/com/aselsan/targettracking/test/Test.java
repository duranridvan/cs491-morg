package com.aselsan.targettracking.test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Test {

	/**
	 * @param args
	 */
	static Queue<Integer> q;
	public static void main(String[] args) {

		Scanner scan = new Scanner("data.txt");
		q = new ArrayDeque<Integer>();
		while(scan.hasNext()){
			q.add(scan.nextInt());
			
		}

	}
}
