package com.aselsan.targettracking.test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
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
	
	public static void f2() throws FileNotFoundException{
		Scanner scan = new Scanner(new File("right.txt"));
		PrintStream out = new PrintStream(new File("rightout.txt"));
		ArrayList<Integer> l = new ArrayList<Integer>();
		ArrayList<String> times = new ArrayList<String>();
		while(scan.hasNext()){
			times.add(scan.next());
			l.add(scan.nextInt());
		}
		for(int i=20;i+20<l.size();i++){
			boolean cont = l.get(i) >= 22;
			for(int j=i-20;cont && j<i;j++)
				if(l.get(i) < l.get(j))
					cont  = false;
			for(int j=i+1;cont && j<=i+20;j++)
				if(l.get(i) <= l.get(j))
					cont  = false;
			if(cont)
				out.println(times.get(i)+"\t"+l.get(i));
		}
	}
	
	public static void f3(){
		try {
			Scanner scan = new Scanner(new File("guzeel.txt"));
			
			while(scan.hasNext()){
				String str = scan.next();
				if(str.equals("Point")){
					String s = scan.next();
					int a = Integer.parseInt(s.substring(1,s.length()-1));
					s = scan.next();
					
					int b = Integer.parseInt(s.substring(0,s.length()-1));
					System.out.println(a + " " + b );
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		f3();
		return;/*
		Scanner scan = new Scanner(new File("left.txt"));
		PrintStream out = new PrintStream(new File("leftout.txt"));
		int cnt =0;
		String maxTime="",minTime="";
		int max = 20,min=20;
		while(scan.hasNext()){
			String time = scan.next();
			int x = scan.nextInt();
			if(x > max) {max = x;maxTime = time;}
			if(x < min) {min = x;minTime = time;} 
			if(++cnt == 40){
				if(max - min >= 10)
					out.println(maxTime + "\t" + (max-min));
				max = min = 20;
				cnt = 0;
			}
		}*/

	}
}
