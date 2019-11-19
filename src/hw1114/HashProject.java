/*
 * Philip Cothery
 * COSC311 FA19
 * HW1114 RandomAccessFile Hash
 * url:
 * 
 */

package hw1114;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;

public class HashProject {
	RandomAccessFile hash;

	public HashProject() throws FileNotFoundException {
		hash = new RandomAccessFile("data.raf","rw");
		try {
		hash.setLength(44);
		hash.seek(0);
		for(int i = 0; i< hash.length(); i+=4) {
			hash.writeInt(-2);
		}
		hash.close();
		}catch(IOException e) {
			System.out.println(e);
			System.out.println("error at set length");
		}
		
	}

	public void add(int num) throws FileNotFoundException{
		hash = new RandomAccessFile("data.raf","rw");
		int hashVal = num % 11;
		//DEBUG System.out.println("num= " + num + "          hashVal= " + hashVal);
		try{
		hash.seek(hashVal*4);
		if(hash.readInt() == -2) {
			hash.seek(hashVal*4);
			hash.writeInt(num);
		}
		hash.close();
		}catch(IOException e) {
			System.out.println(e);
			System.out.println("error at adding value" + hashVal +"      " + num);
		}
	}

	public void print() throws FileNotFoundException {
		try {
		hash = new RandomAccessFile("data.raf","rw");
		System.out.println("----------------");
		System.out.println("|  O |  I |  V |");
		System.out.println("|----|----|----|");
		for (int i = 0; i < hash.length(); i+=4) {
			hash.seek(i);
			System.out.printf("| %2d", hash.getFilePointer());
			System.out.printf(" | %2d", i/4);
			if ( hash.readInt()!= -2) {
				hash.seek(i);
				System.out.printf(" | %2d", hash.readInt());
			} else {
				System.out.print(" |   ");
			}
			System.out.println(" |");
			System.out.println("|----|----|----|");
		}
		System.out.println("----------------");
		hash.close();
		}catch(IOException e) {
			System.out.println(e);
			System.out.println("Error at print");
		}
	}

	public static void main(String[] args) {
		Random ran = new Random(97);
		try {
		HashProject hp = new HashProject();
		for (int i = 0; i < 8; i++) {
			hp.add(ran.nextInt(99));
		}
		hp.print();
		}catch(FileNotFoundException e) {
			System.out.println(e);
			System.out.println("File not found in main");
		}
	}
}
