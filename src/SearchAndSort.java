/*
	Author: Thomas Haines
	Course: CSC260.002
	Date: 10/22/2016
	Assignment: #7
	Instructor: Fox
*/

/*
	Description:  
	
*/

import java.io.*;
import java.util.Scanner;


public class SearchAndSort {

	public static void main(String[] args) throws IOException
	{
		File listFile = new File("input2.txt");
		File targetFile = new File("targets2.txt");
		int sortedCount = 0, unsortedCount = 0;
		
		String[] list1;
		String[] list2;
		String[] targets;
		
		list1 = getInput(listFile);
		list2 = sort(list1);
		
		targets = getInput(targetFile);
		for(int i = 0; i < targets.length; i++)
		{
			unsortedCount += sequentialSearch(list1, targets[i]);
			sortedCount += binarySearch(list2, targets[i]);
		}
				
		double sequentialAverage = (double) unsortedCount / targets.length;
		double binaryAverage = (double) sortedCount / targets.length;
		
		System.out.printf("Sequential search: %4.2f", sequentialAverage);
		System.out.println(" searches per item.");
		
		System.out.printf("Binary search: %4.2f", binaryAverage);
		System.out.println(" searches per item.");

	}

	public static int binarySearch(String[] list, String target)
	{
		int low = 0, high = list.length - 1, loop = 0, mid;
		
	    while(high >= low)  
	    {
	         mid = (low + high) / 2;
	         
	         if(list[mid].compareTo(target) == 0) 
	        	 return ++loop;
	        
	         else if(list[mid].compareTo(target) > 0)
	              high = mid - 1;
	         
	         else
	        	 low = mid + 1;
	         
	         loop++;
	    }
	    
	    return loop;
		
	}
	
	public static int sequentialSearch(String[] list, String target)
	{
		int loop = 0;
		
		for(int i = 0; i < list.length; i++)
		{
			if (list[i].compareTo(target) == 0)
				return loop;
			loop++;
		}
		
		return loop;
	}
	
	public static String[] sort(String[] list)
	{
		String[] temp = new String[list.length];
		String swap;
		int numberOfSwaps = -1;	//initialize this to -1 to ensure the loop executes properly
		
		for (int i = 0; i < temp.length; i++)
			temp[i] = list[i];
		
		while(numberOfSwaps != 0)
		{
			numberOfSwaps = 0;
			
			for (int i = 0; i < temp.length - 1; i++)
			{
				if (temp[i].compareTo(temp[i+1]) > 0)
				{
					swap = temp[i];
					temp[i] = temp[i+1];
					temp[i+1] = swap;
					
					numberOfSwaps++;
				}
			}	
		}
		
		return temp;
		
	}
	
	public static String[] getInput(File inFile) throws IOException
	{
		Scanner input = new Scanner(inFile);
		int loop = 0;
		String[] temp = new String[100];
				
		while(input.hasNext() && loop < 100)
		{
			temp[loop] = input.next();
			loop++;
			
		}
		
		input.close();
		
		String[] temp2 = new String[loop];
		
		for (int i = 0; i < temp2.length; i++)
			temp2[i] = temp[i];
		
		return temp2;
		
	}
	
}

/*
	Output:
	
	Sequential search: 19.60 searches per item.
	Binary search: 4.47 searches per item.

*/