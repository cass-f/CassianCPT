//-------------------------------------------
//Name: CassianCPT
//Purpose: Play hangman
//Author: Cassian
//Created: 19/12/2024
//-------------------------------------------
import arc.*;
import java.awt.*;
import java.awt.image.*;


public class CassianCPT { // Added 'class' keyword
    public static void main(String[] args) {
        Console con = new Console("Hangman", 1280, 780);
        
        con.setTextColor(Color.RED);
        
        //Creating and initializing variables
        //Main menu
		char chrChoice = 'b';
        
        //Themes
        char chrTheme = '0';
        String strFiles[];
        
        //Names
        String strName = "";
        
        //Bubble sort
        int intRow = 0;
        int intRow2;
        int intRandom;
        int intWordCount = 0;
        int intRand;
        int intTheme = 0;
        String strBurn;
        String strTempWord = "";
        String strTempNum;
        String strFileName = "";
        
        //Array names
        String strWord[][];
                
        //Hangman game play
        int intRound = 0;
        String strGuessWord = "";  
        String strGuess;   
        int intLives = 0;
        int intPoints = 0;
        
        //Replay
        boolean boolGameplay = true;  
        char chrPlay;
        
        //Game running
        boolean boolRunning = true;
        
                
        strFiles = new String [5];
				
		strFiles[0] = "holidays.txt";
		strFiles[1] = "cityNames.txt";
		strFiles[2] = "foods.txt";
		strFiles[3] = "videoGames.txt";
		strFiles[4] = "animals.txt";
				
        
        //Having the whole code in a while loop to always have it runnning 
        while(boolRunning == true){
			//In homescreen, and printing out the homescreen options.
			if(chrChoice == 'b' || chrChoice == 'B'){
				//Clearing the screen of any previous code
				con.clear();
				
				System.out.println("In the homescreen.");
				con.println("					     HANGMAN");
				//Showing options to player and asking player what they want to do
				con.println("PLAY (P)");
				con.println("HELP (H)");
				con.println("SCOREBOARD (S)");
				con.println("QUIT (Q)");
				chrChoice = con.getChar();
			}
			
			//Chceking to see if user wants to play
			else if(chrChoice == 'p' || chrChoice == 'P'){
				System.out.println("User wants to play.");
				
				//Making boolRunning false to eliminate glitching of the text from constantly being printed
				boolRunning = false;
				con.clear();
				
				//Clearing any previously ran code and theme options
				con.println("					     PICK A THEME");
				//Asking user for username
									
				con.print("Username: ");
				strName = con.readLine();
				
				//Asking user to pick a theme
				while(chrTheme != ('1') && chrTheme != ('2') && chrTheme != ('3') && chrTheme != ('4') && chrTheme != ('5')){	
					con.clear();	
					con.println("(1) Holidays");
					con.println("(2) City Names");
					con.println("(3) Food");
					con.println("(4) Video Games");
					con.println("(5) Animals");
					chrTheme = con.getChar();
				}
				
				
				//Convert theme from char to int
				intTheme = Integer.parseInt(String.valueOf(chrTheme));  
				
				//Get file name
				strFileName = strFiles[intTheme -1];	
								
				//Get the length of the file and open file
				intWordCount = CPTTools.lengthTxt(strFileName);
				TextInputFile themes = new TextInputFile(strFileName);
				
				//Creating array					
				System.out.println(intWordCount);
				strWord = new String[intWordCount][2];
				
				//Filling the array with the text from file
				while(themes.eof() == false){
					strWord[intRow][0] = themes.readLine();
					System.out.println(strWord[intRow][0]);
					intRow++;
				}
				//Generating a random number for the theme
				for(intRow = 0; intRow < intWordCount; intRow++){
					strWord[intRow][1] = Integer.toString((int)(Math.random()*100 + 0));
				}
				
				//Printing out the word and random number before bubble sort					
				for(intRow = 0; intRow < intWordCount; intRow++){
					System.out.println("before: " + strWord[intRow][0] + "	|	" + strWord[intRow][1]);
				}
				
				//Getting random number
				for(intRow2 = 0; intRow2 < intWordCount; intRow2++){		
					for(intRow = 1; intRow < intWordCount; intRow++){
						//Checking to see if the left is smaller than that on the right
						if(Integer.parseInt(strWord[intRow][1]) < Integer.parseInt(strWord[intRow - 1][1])){
							//Take the left item
							strTempNum = strWord[intRow][1];
							strTempWord = strWord[intRow][0];
							
							//Right item moves to the left
							strWord[intRow][0] = strWord[intRow - 1][0];
							strWord[intRow][1] = strWord[intRow - 1][1];
							
							//Move the left item to the right (the temporary item)
							strWord[intRow - 1][1] = strTempNum;
							strWord[intRow - 1][0] = strTempWord;
						}	
					}
					//Printing out the word and random number after bubble sort
					for(intRow = 0; intRow < intWordCount; intRow++){
						System.out.println("after: " + strWord[intRow][0] + "	|	" + strWord[intRow][1]);
					}
				}
					
				//Keep looping until user wants to quit or until end of txt file
				while(boolGameplay == true){
					
					//Getting the word
					System.out.println("Picked word: "+strWord[intRound][0]);
					strGuessWord = strWord[intRound][0];
					
					//Getting user ready to play
					con.clear();
					con.println("Get ready to play!");
					con.sleep(2000);
					con.clear();
					
					//Asking user for guess
					con.print("Guess your word here: ");
					strGuess = con.readLine();
					
					//Win screen
					if(strGuess.equalsIgnoreCase(strGuessWord) && intLives < 7){
						boolGameplay = false;
						intPoints++;
						intRound++;
						con.println("Congrats! you have won");
						con.println("Plus one point!, You now have "+intPoints+" points");
						
					//Lose screen							
					}else{
						boolGameplay = false;
						intRound++;
						con.println("You have lost.");
						con.println("You have "+intPoints+" points");
					}
					
					//Asking user if they want to replay
					con.print("Do you want to keep playing? (Y/N) ");
					chrPlay = con.getChar();
					
					//If user wants to play, a new word is picked and they play again
					if(chrPlay == ('Y') || chrPlay == ('y') && intRound <= intWordCount){
						con.println("You have chosen to play again.");
						con.println("Picking new word...");
						con.sleep(5000);
						con.clear();
						boolGameplay = true;
						
					//If the user does not want to play, they are sent back to home screen
					}else if(chrPlay == ('N') || chrPlay == ('n') || intRound > intWordCount){
						chrChoice = 'b';
						boolRunning = true;
					}
				}
			}
			
			//Checking to see if user needs help
			else if(chrChoice == 'h' || chrChoice == 'H'){
				System.out.println("User needs help.");
				//Making boolRunning false to eliminate glitching of the text from constantly being printed
				boolRunning = false;
				
				//Clearing any previously ran code and how to play. There are breaks to make it easier to read
				con.clear();
				con.println("Welcome to HANGMAN! In this game you will have to select a theme of words.");
				con.sleep(1000);
				con.println("");
				con.println("A random word will then be selected from your chosen theme.");
				con.sleep(1000);
				con.println("");
				con.println("You will have 7 guesses, and you must guess a word. NOT letters.");
				con.println("After each incorrect guss, a body part will be addded and a random letter will");
				con.println("appear to the screen.");
				con.sleep(1000);
				con.println("");
				con.println("If you guess the word before the man is drawn, you WIN");
				con.println("If the man is hung before you think of the word, you LOOSE");
				
				//Allowing user to go home
				con.print("To go home, press B. ");
				chrChoice = con.getChar();
				
				//Making boolRunning true to have game play continue
				boolRunning = true;
				
			}
			
			//Checking to see if user wants to check scoreboard
			else if(chrChoice == 's' || chrChoice == 'S'){
				//Making boolRunning false to eliminate glitching of the text from constantly being printed
				boolRunning = false;
				
				System.out.println("User wants to check scoreboard");
				con.clear();
				con.println("					     SCOREBOARD");
				
				//Making boolRunning true to have game play continue
				boolRunning = true;
				
			}
						
			//Checking to see if user found the secret screen and printing a joke with countdown
			else if(chrChoice == 'w' || chrChoice == 'W'){
				//Making boolRunning false to eliminate glitching of the text from constantly being printed
				boolRunning = false;
				
				System.out.println("User found secret screen.");
				con.clear();
				con.println("CONGRATULATIONS! YOU HAVE ENTERED THE SECRET SCREEN");
				con.println("Get ready for a joke!");
				con.sleep(1000);
				con.println("3");
				con.sleep(1000);
				con.println("2");
				con.sleep(1000);
				con.println("1");
				con.sleep(1000);
				con.println("Why does the programmer not like to use light mode?");
				con.sleep(3000);
				con.println("It is because light attracts bugs");	
				
				//Asking user if they want to go back home
				con.print("To go back home, press B. ");
				chrChoice = con.getChar();
				
				//Making boolRunning true to have game play continue
				boolRunning = true;
			}  
					
			//Checking to see if user wants to quit
			else if(chrChoice == 'q' || chrChoice == 'Q'){
				System.out.println("User quits game");
				con.clear();
				con.println("Thanks for playing!");
				con.println("We hope to see you again.");
				con.sleep(5000);
				boolRunning = false;
			}
			
			//Checking to see if user enterd invalid input
			else{
				System.out.println("Invalid Input");
				con.clear();
				boolRunning = false;
				chrChoice = 'b';
				boolRunning = true;
				
			}
			
		}
    }
}
