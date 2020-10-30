/**
 * proj12.java
 * 
 * This program takes in a file of sorted integers and creates a new file where
 * any duplicate entries are removed.
 * 
 * Authors: Dillan Lopez and Nicolas de la Fuente
 * Updated: September 10, 2020
 */

import java.io.*;
import java.util.Scanner;

public class proj12 {
	public static Scanner scan = new Scanner(System.in);
    public static BufferedReader inStream; // the input stream
    public static BufferedWriter outStream; // the output stream

    public static void main(String[] args) {
		do {
        	try {
				String inFilename, outFilename;
        		// Get the input and output file names
	            System.out.print("Enter the input file name: ");
    	        inFilename = scan.nextLine();
        	    System.out.print("Enter the output file name: ");
           		outFilename = scan.nextLine();

				// Get user input for a file, and remove the duplicate entries
            	removeDuplicates(inFilename, outFilename);
				System.out.println("\nSuccessfully removed duplicates from '" + inFilename + "'!");
				System.out.println("Updated file can be found in '" + outFilename + "'\n");
			}
			catch (FileNotFoundException ex) {
				System.out.println(ex.getMessage());
        	}
        	catch (EOFException ex) {
        		System.out.println(ex.getMessage());
        	}
			catch (IOException ex) {
           		ex.printStackTrace();
            	System.exit(1);
       		}

		} while (runAgain());
      	System.out.println("Goodbye!");
	}

	public static void removeDuplicates(String inFilename, String outFilename) 
			throws FileNotFoundException,  EOFException, IOException {
		// Create the buffers from the file names
        inStream = new BufferedReader(new FileReader(inFilename));
        outStream = new BufferedWriter(new FileWriter(outFilename));
                    
        // Read each line until the end of the file
        String line = inStream.readLine();
        while (line != null) {
			try {
        		int num = Integer.valueOf(line); // throws exception if not an int
			
				// Write the number to the file
				outStream.write(Integer.toString(num) + '\n');

				// Read the next line
				String nextLine = inStream.readLine();

				// If the next number is a duplicate
				if (line.equals(nextLine)) {
					// Skip to the line after it
					line = inStream.readLine();
				}
				else
					line = nextLine;
			}
        	catch (NumberFormatException ex) {
				System.out.println("Error reading file. Invalid input: " + ex.getMessage());
				System.out.println("Please fix the file before trying again.");
				System.exit(0);
			}
		}
		// Close the stream to save the changes to the file
        outStream.close();
	}

	/**
     * Asks the user if they want to run the program again
     * 
     * @return true if they do, false if they do not
     */
    public static boolean runAgain() {
		System.out.print("Do you want to run the program again (y/n): ");
        String runProgram = scan.nextLine();
        while ( !runProgram.equalsIgnoreCase("y") && !runProgram.equalsIgnoreCase("n") )
        {
			System.out.println("Invalid input.");
            System.out.print("Do you want to run the program again (y/n): ");
            runProgram =  scan.nextLine();
        }

        return runProgram.equalsIgnoreCase("y");
    }
}
