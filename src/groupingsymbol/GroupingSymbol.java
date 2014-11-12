/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package groupingsymbol;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Stack;

/**
 * This class checks whether a java source-code file has correct pairs of
 * grouping symbols.
 *
 * @author FU, Yun
 * @version 1.0
 * @since 25/10/2014
 */
public class GroupingSymbol {

    /**
     * checks whether a java source-code file has correct pairs of grouping
     * symbols.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        Scanner file = new Scanner(new BufferedReader(new FileReader("Symbols.txt")));
        String fileString = "";
        while (file.hasNext()) {
            fileString = fileString + file.next();
        }

        Stack<Character> stack = new Stack<Character>();

        int errorNumber = 0;
        char[] filechar = fileString.toCharArray();
        for (int i = 0; i < filechar.length; i++) {
            if (filechar[i] == '{' | filechar[i] == '[' | filechar[i] == '(') {
                stack.push(filechar[i]);
            } else {
                if (filechar[i] == '}') {
                    if (stack.empty()) {
                        System.out.println("extra closing brace");
                        errorNumber++;
                    } else {
                        if (stack.peek() == '{') {
                            stack.pop();
                        } else if (stack.peek() == '[' | stack.peek() == '(') {
                            System.out.println("mismatched brace");
                            errorNumber++;
                            stack.pop();
                        }
                    }
                }
                if (filechar[i] == ']') {
                    if (stack.empty()) {
                        System.out.println("extra closing bracket");
                        errorNumber++;
                    } else {
                        if (stack.peek() == '[') {
                            stack.pop();
                        } else if (stack.peek() == '{' | stack.peek() == '(') {
                            System.out.println("mismatched bracket");
                            errorNumber++;
                            stack.pop();
                        }

                    }
                }
                if (filechar[i] == ')') {
                    if (stack.empty()) {
                        System.out.println("extra closing parenthesis");
                        errorNumber++;
                    } else {
                        if (stack.peek() == '(') {
                            stack.pop();
                        } else if (stack.peek() == '{' | stack.peek() == '[') {
                            System.out.println("mismatched parenthesis");
                            errorNumber++;
                            stack.pop();
                        } else {
                            stack.push(filechar[i]);
                        }
                    }
                }
            }
        }

        if (stack.empty() && errorNumber == 0) {
            System.out.println("No errors.");
        } else {
            if (stack.contains('{')) {
                System.out.println("extra opening brace");
            }
            if (stack.contains('[')) {
                System.out.println("extra opening bracket");
            }
            if (stack.contains('(')) {
                System.out.println("extra opening parenthesis");
            }
        }
    }
}
