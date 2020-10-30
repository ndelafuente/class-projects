/**
 * proj11.java
 * 
 * This program takes in a string and a list of values. It then usees each of those 
 * values to encrypt and decode the string that was inputed.
 * 
 * Authors: Dillan Lopez and Nicolas de la Fuente
 * Updated: September 9, 2020
 */

import java.util.Scanner;

public class proj11 {
    public static Scanner scan = new Scanner(System.in);
    public static final int MAX_NUMBER_OF_KEYS = 64;

    public static void main(String[] args) {
        System.out.println("This program will encrypt your message using shifting key cipher");
        do {
            // Get the the keys from the user
            int keyValues[] = new int[MAX_NUMBER_OF_KEYS];
            int numberOfKeyValues = -1;
            while (numberOfKeyValues == -1)
                numberOfKeyValues = scanKeys(keyValues);
			
            // Encrypt the message and print it out
            String encryptedPhrase = encrypt(keyValues, numberOfKeyValues);
            System.out.println("Your encrypted message is: "+ encryptedPhrase);

            // Decrypt the message and print it out
            String decryptedPhrase = decrypt(encryptedPhrase, keyValues, numberOfKeyValues);
            System.out.println("Your decrypted message is: "+ decryptedPhrase);
        }
        while(runAgain());
        System.out.println("Goodbye!");
    }

    /**
     * Takes in a message from the user and returns it as an encrypted message
     * 
     * @param keyValues an integer array of the keys
     * @param numberOfKeyValues the number of keys
     * @return the encrypted message
     */
    public static String encrypt(int keyValues[], int numberOfKeyValues){
        // Get the message to decrypt
        System.out.print("Enter the message you wish to encrypt: ");
        String phrase = scan.nextLine();

        String encryptedMessage = "";
        for(int i = 0; i < phrase.length(); i++){
            int c = (int) phrase.charAt(i) + keyValues[i % numberOfKeyValues];
            char character = (char) c;
            encryptedMessage += character;
        }
        return encryptedMessage;
    }

    /**
     * Takes in a encypted message and returns it as a decrypted string
     * 
     * @param phrase the encrypted message
     * @param keyValues an integer array of the keys
     * @param numberOfKeyValues the number of keys
     * @return the decrypted message
     */
    public static String decrypt(String phrase, int keyValues[], int numberOfKeyValues){
        String decryptedMessage = "";
        for(int i = 0; i < phrase.length(); i++){
            int c = (int) phrase.charAt(i) - keyValues[i % numberOfKeyValues];
            char character = (char) c;
            decryptedMessage += character;
        }
        return decryptedMessage;
    }

    /**
     * Get the cipher keys from the user
     * 
     * @param keyValues an array to store the keys
     * @return the number of keys that were read, -1 if 
     */
    public static int scanKeys(int keyValues[]) {
        System.out.println("Enter the individual keys separated by spaces.");
        String line = scan.nextLine();

        String numStr = ""; // the string value of the key
        int numberOfKeyValues = 0;
        for (int i = 0, len = line.length(); i < len; i++) {
            char c = line.charAt(i);
            if (c >= '0' && c <= '9' || c == '-' ) // valid integer character
                numStr += c;
            if (c == ' '|| i == len - 1) {
                // If there is a valid key, add it to the array
                if (numStr.length() > 0) {
                    keyValues[numberOfKeyValues] = Integer.valueOf(numStr);
                    numberOfKeyValues++;
                }
                numStr = ""; // reset the value
            }
            if ( !((c >= '0' && c <= '9') || c == '-' || c == ' ') ) {
                System.out.printf("\nInvalid input (%c). Key values must be whole numbers\n\n", c);
                return -1;
            }
        }
        return numberOfKeyValues;
    }

    /**
     * Asks the user if they want to run the program again
     * 
     * @return true if they do, false if they do not
     */
    public static boolean runAgain() {
        String runProgram = " ";

        while ( !runProgram.equalsIgnoreCase("y") && !runProgram.equalsIgnoreCase("n") )
        {
            System.out.print("Do you want to run the program again (y/n): ");
            runProgram =  scan.nextLine();
        }

        return runProgram.equalsIgnoreCase("y");
    }
}
