package org.example;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Testing for question 1
        String[] testInputs = {"Happy Birthday!", "RACECAR", "amanaplanacanalpanama", "Saippuakivikauppias"};

        for (String input : testInputs) {
            System.out.println("Input: " + input);
            System.out.println("Output: " + checkForPalindrome(input));
            System.out.println("---------------------");
        }


        //Testing for problem 2
        String[] testInputs2 = {"FACE", "Coding", "Feed", "This is a sentence", "Cab!"};

        for (String input : testInputs2) {
            System.out.println("Input: " + input);
            System.out.println("Output: " + createSecretCode(input));
            System.out.println("---------------------");
        }
    }

    //Problem 1
    public static boolean checkForPalindrome(String s) {
        // Reverse the string using a forloop
        String reversed = "";
        for (int i = s.length() - 1; i >= 0; i--) {
            reversed += s.charAt(i);
        }
        return s.equals(reversed);
    }
    //Problem 2
    public static String createSecretCode(String message) {
        String newMessage = "";
        for (int i = 0; i < message.length(); i++) {
            char c = Character.toUpperCase(message.charAt(i));
            String letter;
            switch (c) {
                case 'A':
                    letter = "1"; break;
                case 'B':
                    letter = "2"; break;
                case 'C':
                    letter = "3"; break;
                case 'D':
                    letter = "4"; break;
                case 'E':
                    letter = "5"; break;
                case 'F':
                    letter = "6"; break;
                case 'G':
                    letter = "7"; break;
                default:
                    letter = "?";
            }
            newMessage += letter;
        }
        return newMessage;
    }
}
