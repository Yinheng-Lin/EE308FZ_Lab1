package Èí¼þ¹¤³Ì;

import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.BufferedReader;

public class Lab1_2 {

	public static void main(String[] args) throws IOException {
		
		//read the test file
		FileReader file = null;
		try {
			file = new FileReader("C:\\Users\\Lenovo\\Desktop\\test.txt");
			
		} catch (Exception e) {
			e.getStackTrace();
		}
		BufferedReader input = new BufferedReader(file);
		String lineString = "";
		String tempString = input.readLine();
		while(tempString!=null) {
			lineString+=tempString;
			tempString = input.readLine();
		}
		
		solution1(lineString);
		solution2(lineString);
		solution3_4(lineString);

	}
	
	public static void solution1(String input) {
		System.out.println("---------Solution 1---------");
		
		//Import keywords
		String keywords[] = {"auto", "break", "case", "char", "const", "continue", "default", "do", "double", "else", "enum", 
				"extern","float", "for", "goto", "if", "int", "long", "register", "return", "short", "signed", "sizeof", 
				"static","struct", "switch", "typedef", "unsigned", "union", "void", "volatile", "while"};
		int num = 0;
		for(int i = 0; i < keywords.length; i++) {
			//Avoid the effects of characters containing keywords, eg:'interest' cotains 'int'.
			String regExpress = "[^a-zA-Z_]"+keywords[i]+"[^a-zA-Z_]";
			Pattern pattern = Pattern.compile(regExpress);
			Matcher matcher = pattern.matcher(input);
			while (matcher.find()) {
				num++;
			}
		}
		System.out.println("The number of keywords is "+num+".");
		System.out.println();
	}
	
	
	public static void solution2(String input) {
		System.out.println("---------Solution 2---------");
		
		//Calculate number of "switch case" structures
		int num_switch = 0;
		String regExpress = "[^a-zA-Z_]"+"switch"+"[^a-zA-Z_]";
		Pattern pattern1 = Pattern.compile(regExpress);
		Matcher matcher1 = pattern1.matcher(input);
		while(matcher1.find()) {
			num_switch++;
		}
		System.out.println("The number of 'switch' is "+num_switch+".");
		
		//Calculate number of "case" corresponding to each group
		int num_case = 0;
		int num = 0;
		String regExpress2 = "switch.*?}";
		Pattern pattern2 = Pattern.compile(regExpress2);
		Matcher matcher2 = pattern2.matcher(input);
		while(matcher2.find()) {
			String regExpress3 = "[^a-zA-Z_]"+"case"+"[^a-zA-Z_]";
			String tempText=matcher2.toString();
			Pattern pattern3 = Pattern.compile(regExpress3);
			Matcher matcher3 = pattern3.matcher(tempText);
			while(matcher3.find()) {
				++num_case;
			}
			num++;
			System.out.println("The number of 'case' in switch "+ num +" is "+num_case+".");
			num_case = 0;
		}
		System.out.println();
	}
	
	
	public static void solution3_4(String input){
		System.out.println("---------Solution 3 and 4---------");
		
		Stack<String> s = new Stack<String>();
		int num_if_else = 0;
		int num_if_elseif_else = 0;
		
		String regExpress = "else *if|else|if";
		Pattern pattern = Pattern.compile(regExpress);
		Matcher matcher = pattern.matcher(input);
		
		while(matcher.find()) {
			String sub=input.substring(matcher.start(),matcher.end());
			//push 'if' and 'else if'
			if(sub.equals("if")||sub.equals("else if")){
				s.push(sub);
			//if sub is 'else', pop the stack
			}else {
				//s.pop() is 'if'
				if(s.pop().equals("if")) {
					num_if_else++;
				}
				//s.pop() is 'else if'
				else{
					num_if_elseif_else++;
					//Keep popping the top of the stack until the top is 'if'
					while(s.peek().equals("else if")) {
						s.pop();
					}
					//pop the 'if'
					s.pop();
				}
			}
		}
		
		System.out.println("The number of 'if-else' is "+num_if_else+".");
		System.out.println("The number of 'if-elseif-else' is "+num_if_elseif_else+".");
		
	}
	
}

