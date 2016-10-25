/*
	Author: Thomas Haines
	Course: CSC260.002
	Date: 10/22/2016
	Assignment: #7
	Instructor: Fox
*/

/*
	Description:  
	Takes an input and target file and stores their contents as string arrays.
	The input array is sorted using a bubble sort algorithm, where consecutive
	values are swapped if the first is larger than the second, until the array 
	is fully sorted.
	A sequential search is then performed on the unsorted array, looping until 
	the terms in "targets" are found or the end is reached. A binary search is 
	performed on the sorted array, splitting it in half and comparing the 
	target value with the mid value, going to the next highest or lowest mid 
	point until the string is found or the end is reached.
	The average searches per item values for each search are calculated and 
	printed to the console.
*/

import java.io.*;	//java.io package for dealing with file I/O
import java.util.Scanner;	//import Scanner class


public class SearchAndSort {

	public static void main(String[] args) throws IOException
	{
		File listFile = new File("input1.txt");	//input the input and targets files
		File targetFile = new File("targets1.txt");
		int sortedCount = 0, unsortedCount = 0;	//counts for analyzing searches
		
		String[] list1;	//string array for each line of the input file
		String[] list2;	//list1, sorted alphabetically, descending
		String[] targets;	//string array for each line of targets file
		
		list1 = getInput(listFile);	//get the contents of the input file
		list2 = sort(list1);	//sort list1 into a new array
		
		targets = getInput(targetFile);
		for(int i = 0; i < targets.length; i++)		//for each element in targets
		{
			unsortedCount += sequentialSearch(list1, targets[i]);	//perform searches and add return values to counts
			sortedCount += binarySearch(list2, targets[i]);
		}
				
		double sequentialAverage = (double) unsortedCount / targets.length;	//calculating averages
		double binaryAverage = (double) sortedCount / targets.length;
		
		System.out.printf("Sequential search: %4.2f", sequentialAverage);	//output
		System.out.println(" searches per item.");
		
		System.out.printf("Binary search: %4.2f", binaryAverage);
		System.out.println(" searches per item.");

	}

	//performs a binary search in a sorted array
	public static int binarySearch(String[] list, String target)
	{
		int low = 0, high = list.length - 1, loop = 0, mid;	//starts low/high range from 0 to the highest index value in the list array, loop iterations, and middle value
		
	    while(high >= low)  	//loop is finished once high becomes lower than low 
	    {
	         mid = (low + high) / 2;	//find the mid point using integer division each iteration
	         loop++;	//increase the number of loop iterations 
	         
	         if(list[mid].compareTo(target) == 0) //list[mid] and target are equal
	        	 return loop;	//increment loop number and return this value
	        
	         else if(list[mid].compareTo(target) > 0)	//list[mid] is higher than target alphabetically
	              high = mid - 1;	//high point moves based on previous mid
	         
	         else	//list[mid] is lower than target alphabetically
	        	 low = mid + 1;	//low point moves based on previous mid
	         
	    }
	    
	    return loop;	//returns the number of searches, this will return if the search does not yield a result
		
	}
	
	//performs a sequential search for a given string in a given string array
	public static int sequentialSearch(String[] list, String target)
	{
		int loop = 0;	//initial number of times the loop iterates
		
		for(int i = 0; i < list.length; i++)	//loop through list array
		{
			loop++;	//increment number of iterations
			if (list[i].compareTo(target) == 0)	//returns if list[i] and target are a match
				return loop;
		
		}
		
		return loop;	//returns if target is not in list
		
	}
	
	//bubble sort for alphabetic sorting
	public static String[] sort(String[] list)
	{
		String[] temp = new String[list.length];	//temp array to work with
		String swap;	//value being swapped 
		int numberOfSwaps = -1;	//initialize this to -1 to ensure the loop executes properly
		
		for (int i = 0; i < temp.length; i++)	//copy list into temp
			temp[i] = list[i];
		
		while(numberOfSwaps != 0)	//if this is 0 at the beginning of the loop, the list is sorted as no swaps needed to be performed
		{
			numberOfSwaps = 0;	//reset number of swaps each iteration
			
			for (int i = 0; i < temp.length - 1; i++)	//only loop to length-1 because we are comparing to the next string in the array
			{
				if (temp[i].compareTo(temp[i+1]) > 0)	//if the string at i is higher than the string at i+1
				{
					swap = temp[i];	//swaps values at i and i+1
					temp[i] = temp[i+1];
					temp[i+1] = swap;
					
					numberOfSwaps++;	//increment swaps
					
				}
			}	
		}
		
		return temp;	//return sorted array
		
	}
	
	//returns a string array containing each line of the file, up to 100
	public static String[] getInput(File inFile) throws IOException
	{
		Scanner input = new Scanner(inFile);	//create new scanner object
		int loop = 0;	//loop iterations
		String[] temp = new String[100];	//string array for file input
				
		while(input.hasNext() && loop < 100)	//runs 100 times or until the end of the file
		{
			temp[loop] = input.next();	
			loop++;	//increment loop number, this is to control the loop and size of temp2
			
		}
		
		input.close();
		
		String[] temp2 = new String[loop];	//create a new array of the proper size
		
		for (int i = 0; i < temp2.length; i++)	//copy the elements into temp2
			temp2[i] = temp[i];
		
		return temp2;	//return the input as a string array
		
	}
	
}

/*
	Output:
	
	Sequential search: 20.47 searches per item.
	Binary search: 4.47 searches per item.

*/