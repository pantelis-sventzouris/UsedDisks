package gr.pantelis;

/**
 Calculates the maximum hard disk drives needed to store all the data.
 We have a given number of disks with some capacity and used memory and we want to concatenate all the files
 to as minimum number of disks as possible.
 e.g if we have 3 hard disk drives with total capacities
 {1000,500,250}
 
 and used memories
 {900,50,25}
 
 we will need only 1 disk instead of 3.
 **/

import java.util.Arrays;
import java.util.Scanner;

public class Main
{
	//tests the application
	public static void main(String[] argv)
	{
		int numberOfDisks = readNumberOfDisks();	//gets the total number of hard disk drives
		
		//creates the arrays which contain the total and used capacity of each disk
		int[] used = new int[numberOfDisks];
		int[] total = new int[numberOfDisks];
		
		//calls the method to read the total capacity of each disk
		total = readEachCapacity(numberOfDisks);
		used = readEachUsed(numberOfDisks,total);
		
		//calls the method to read the used memory of each disk (must be less or equal to the corresponding values of total capacity		
		int md = minDrives(used, total);
		
		//Displays the result
		System.out.println("The minimum number of hard disk drives required to store your data is: "+md); 	
	}
	
	
	//initialize the number of hard disk drives. Must be at most 50.
	public static int readNumberOfDisks()
	{
		Scanner scan = new Scanner(System.in);
		int length;
		
		do
		{
			
			System.out.println("Enter a valid number (1-50) of total hard disk drives:\n");
			length = scan.nextInt();
		}
		while(length<=0 || length>50);		//checks if the value is valid
		
		return length;
	}
	
	//method for reading the total capacity of each hard disk drive
	public static int[] readEachCapacity(int l)
	{
		int[] total = new int[l];					//initializes the new array
		Scanner scan = new Scanner(System.in);		//instantiates a scanner input to read the capacity of each disk
		
		for(int i=0; i<l; i++)
		{
			do
			{
				int t = i+1;	//a local variable which displays the corresponding hard disk drive
				System.out.println("Enter a valid number (1-1000) for the total capacity of the "+t+" hard disk drive:\n");
				total[i] = scan.nextInt();
			}
			while(total[i]<=0 || total[i]>1000);	//checks if the capacity value is between 1 and 1000
		}
		
		return total;
	}
	
	//method for reading the used memory of each hard disk drive
	public static int[] readEachUsed(int l, int[] total)
	{
		int[] used = new int[l];					//creates the new array to store the used memory values of each disk
		Scanner scan = new Scanner(System.in);
		
		for(int i=0; i<l; i++)
		{
			do
			{
				int t = i+1;	//a local variable which displays the corresponding hard disk drive
				System.out.println("Enter a valid number (1-1000, and less or equal to the corresponding capacity) for the used memory of the "+t+" hard disk drive:\n");
				used[i] = scan.nextInt();
			}
			while(used[i]<=0 || used[i]>1000 || used[i]>total[i]);	//checks if the value is between 1 and 1000 and less or equal than the corresponding capacity of the disk
		}
		
		return used;
	}
	
	//method for calculating the minimum hard disk drives required to store all the data
	public static int minDrives(int[] used, int[] total)
	{
		//gets the total sum of the used memory of all hard disk drives
		int totalUsed = 0;		
		for(int i=0; i<used.length; i++)
		{
			totalUsed += used[i];
		}
		
		Arrays.sort(total);	//ascending sort of the used memory values
		
		//calculates the minimum number of hard disk drives required
		int counter = 0; //variable that keeps the returned value of the minimum number
		int temp = totalUsed;	//variable that is decreasing in every loop	
		for(int j=used.length-1; j>=0; j--)
		{
			if(temp>total[j])
			{
				temp -= total[j];
				counter++;
			}
			else
			{
				if(temp==0)
				{
					counter++;
					break;
				}
				else
				{
					break;
				}
			}			
		}
		if(temp>=0)		//in case the remainder is above 0 or less than the minimum used memory disk we'll need an extra disk
		{
			counter++;
		}
		
		return counter;		
	}	
}
