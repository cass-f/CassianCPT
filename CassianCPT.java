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
        boolean boolPicking = true;
        
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
        int intLives = 6;
        int intPoints = 0;
        int intCount;
        int intLength = 0;
        String strTheme = "";
        String strWordGuess[][];
        int intLengthGuess = 0; 
              
        //Replay
        boolean boolGameplay;  
        char chrPlay = ' ';
        
        //Game running
        boolean boolRunning = true;
        
        //Themes array                
        strFiles = new String [5];
				
		strFiles[0] = "holidays.txt";
		strFiles[1] = "cityNames.txt";
		strFiles[2] = "food.txt";
		strFiles[3] = "videoGames.txt";
		strFiles[4] = "animals.txt";
		
		//Images					
        BufferedImage imgGallow = con.loadImage("gallows.png");
        BufferedImage imgBlack = con.loadImage("blackScreen.png");
        
        //The man
        int intXValueRArm[]= new int [4];
        intXValueRArm[0] = 137;
        intXValueRArm[1] = 185;
        intXValueRArm[2] = 175;
        intXValueRArm[3] = 125;
	
		int intYValueRArm[] = new int [4];
        intYValueRArm[0] = 200;
        intYValueRArm[1] = 250;
        intYValueRArm[2] = 250;
        intYValueRArm[3] = 200;        
                
        
        int intXValueRLeg[]= new int [4];
        intXValueRLeg[0] = 125;
        intXValueRLeg[1] = 175;
        intXValueRLeg[2] = 185;
        intXValueRLeg[3] = 137;
	
		int intYValueRLeg[] = new int [4];
        intYValueRLeg[0] = 260;
        intYValueRLeg[1] = 310;
        intYValueRLeg[2] = 310;
        intYValueRLeg[3] = 260; 
        
        
        int intXValueLArm[]= new int [4];
        intXValueLArm[0] = 97;
        intXValueLArm[1] = 135;
        intXValueLArm[2] = 125;
        intXValueLArm[3] = 85;
	
		int intYValueLArm[] = new int [4];
        intYValueLArm[0] = 250;
        intYValueLArm[1] = 200;
        intYValueLArm[2] = 200;
        intYValueLArm[3] = 250;        
        
                
        int intXValueLLeg[]= new int [4];
        intXValueLLeg[0] = 135;
        intXValueLLeg[1] = 97;
        intXValueLLeg[2] = 85;
        intXValueLLeg[3] = 125;
	
		int intYValueLLeg[] = new int [4];
        intYValueLLeg[0] = 210;
        intYValueLLeg[1] = 360;
        intYValueLLeg[2] = 360;
        intYValueLLeg[3] = 210; 
        
               
        
        
        
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
				while(boolPicking == true){
					while(chrTheme != ('1') && chrTheme != ('2') && chrTheme != ('3') && chrTheme != ('4') && chrTheme != ('5')){	
						con.clear();	
						con.println("(1) Holidays");
						con.println("(2) City Names");
						con.println("(3) Food");
						con.println("(4) Video Games");
						con.println("(5) Animals");
						chrTheme = con.getChar();
					}
					
					//Getting the theme they picked in words
					if(chrTheme == '1'){
						strTheme = "Holidays";
						boolPicking = false;
					}else if(chrTheme == '2'){
						strTheme = "City Names";
						boolPicking = false;
					}else if(chrTheme == '3'){
						strTheme = "Food";
						boolPicking = false;
					}else if(chrTheme == '4'){
						strTheme = "Video Games";
						boolPicking = false;
					}else if(chrTheme == '5'){
						strTheme = "Animals";
						boolPicking = false;
					}else{
						boolPicking = true;
					}
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
				
				//Closing file
				themes.close();
				
				//Generating a random number for the theme
				for(intRow = 0; intRow < intWordCount; intRow++){
					strWord[intRow][1] = Integer.toString((int)(Math.random()*100 + 0));
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
				}
				//Printing out the word and random number after bubble sort
				for(intRow = 0; intRow < intWordCount; intRow++){
					System.out.println("after: " + strWord[intRow][0] + "	|	" + strWord[intRow][1]);
				}
				
				//Initalizing variable
				boolGameplay = true;
				
				//Drawing image
				con.drawImage(imgGallow, 0, 100);
				
				//Keep looping until user wants to quit or until end of txt file
				while(boolGameplay == true){
					
					//Getting the word
					System.out.println("Picked word: "+strWord[intRound][0]);
					strGuessWord = strWord[intRound][0];
					
					//Asking user for guess
					con.clear();
					con.println("Theme: "+strTheme);
					con.println("Saves: "+intPoints);
					con.println("Games Played: "+intRound);
					con.println("Lives Left: "+intLives);
					
					
					//Setting it so the word lines are lined up with gallow
					for(intCount = 0; intCount < 14; intCount++){
						con.println(" ");	
					}
					
					//Getting length of word
					intLength = strGuessWord.length();
					
					//Printing the number of needed lines respective to the word
					for(intCount = 0; intCount < intLength; intCount++){
						con.print("_ ");
					}
					con.println(" ");
					con.print("Guess your word here: ");
					strGuess = con.readLine();
					
					//Win screen
					if(strGuess.equalsIgnoreCase(strGuessWord) && intLives > 0){
						con.clear();
						System.out.println("User won");
						boolGameplay = false;
						intPoints++;
						intRound++;
						con.println("Congrats! you have won");
						con.println("Plus one point!, You now have "+intPoints+" points");
						//Asking user if they want to replay
						con.print("Do you want to keep playing? (Y/N) ");
						chrPlay = con.getChar();
						
					//If wrong, user can still continue if lives is less than 7
					}else if(!strGuess.equalsIgnoreCase(strGuessWord) && intLives > 0){
						con.clear();
						System.out.println("Incorrect Guess");
						con.println("Incorrect Guess.");
						chrPlay = ' ';
						intLives = intLives - 1;
						intLengthGuess = strGuessWord.length();
						
						//Initalizing array
						strWordGuess = new String[intCount][3];
						
						if(intLives == 5){
							con.fillOval(105, 125, 50, 50);				
						}else if(intLives == 4){
							con.fillRect(125, 173, 12, 100);
						}else if(intLives == 3){
							con.fillPolygon(intXValueRArm, intYValueRArm, 4);
						}else if(intLives == 2){
							con.fillPolygon(intXValueLArm, intYValueLArm, 4);
						}else if(intLives == 1){
							con.fillPolygon(intXValueRLeg, intYValueRLeg, 4);
						}else if(intLives == 0){
							con.fillPolygon(intXValueLLeg, intYValueRLeg, 4);
						}
						
						//If stamtent only allows code to enter letter randomization once
						if(intLives == 6){
							//Creating an array for the word, filling array: [letter][random number][position]
							for(intCount = 0; intCount < intLengthGuess; intCount++){
								strWordGuess[intCount][2] = Integer.toString(intCount);
							}							
							
														
							for(intCount = 0; intCount < intLengthGuess; intCount++){
								strWordGuess[intCount][0] = Character.toString(strGuessWord.charAt(intCount));
								strWordGuess[intCount][1] = Integer.toString((int)(Math.random()*100 + 0));
								System.out.println("before: "+strWordGuess[intCount][0] +" "+strWordGuess[intCount][1]);
							}
							
							for(intRow2 = 0; intRow2 < intLengthGuess; intRow2++){		
								for(intRow = 1; intRow < intLengthGuess; intRow++){
									//Checking to see if the left is smaller than that on the right
									if(Integer.parseInt(strWordGuess[intRow][1]) < Integer.parseInt(strWordGuess[intRow - 1][1])){
										//Take the left item
										strTempNum = strWordGuess[intRow][1];
										strTempWord = strWordGuess[intRow][0];
										
										//Right item moves to the left
										strWordGuess[intRow][0] = strWordGuess[intRow - 1][0];
										strWordGuess[intRow][1] = strWordGuess[intRow - 1][1];
										
										//Move the left item to the right (the temporary item)
										strWordGuess[intRow - 1][1] = strTempNum;
										strWordGuess[intRow - 1][0] = strTempWord;
									}	
								}
							}
							//Printing out the word and random number after bubble sort
							for(intRow = 0; intRow < intLengthGuess; intRow++){
								System.out.println("after: " + strWordGuess[intRow][0] + " " + strWordGuess[intRow][1] + " " + strWordGuess[intRow][2]);
							}
							
						}
						con.sleep(3000);
					}
				
						
					//Lose screen							
					if(intLives <= 0){
						con.clear();
						System.out.println("User lost");
						boolGameplay = false;
						intRound++;
						con.println("You have lost.");
						con.println("You have "+intPoints+" points");
						//Asking user if they want to replay
						con.print("Do you want to keep playing? (Y/N) ");
						chrPlay = con.getChar();
					}
										
					//If user wants to play, a new word is picked and they play again
					if(chrPlay == 'Y' || chrPlay == 'y' && intRound <= intWordCount){
						con.clear();
						con.println("You have chosen to play again.");
						con.println("Picking new word...");
						con.sleep(2000);
						con.clear();
						boolGameplay = true;
						intLives = 6;
					
						//Paiting over to remove image
						con.drawImage(imgBlack, -10, 0);
						
						//Drawing image
						con.drawImage(imgGallow, 0, 100);
						
					//If the user does not want to play, they are sent back to home screen
					}else if(chrPlay == 'N' || chrPlay == 'n' || intRound > intWordCount){
						con.clear();
						chrChoice = 'b';
						boolRunning = true;
						boolGameplay = false;
						intLives = 7;
						
						//Checking to see if they have the secret user name. If so, +2 pts
						if(strName.equalsIgnoreCase("statitan")){
							con.clear();
							con.println("You get 2 extra points");
							intPoints = intPoints + 2;
							System.out.println(intPoints);
							con.sleep(2000);
						}		
						
						//Bringing user back to home screen and resetting everything	
						chrChoice = 'b';
						chrTheme = '0';
						boolPicking = true;
						strName = "";
						intRow = 0;
						intWordCount = 0;
						intTheme = 0;
						strTempWord = "";
						strFileName = "";
						intRound = 0;
						strGuessWord = "";   
						intLives = 7;
						intPoints = 0;
						intLength = 0;
						strTheme = "";
						intLengthGuess = 0; 
						chrPlay = ' ';
						
						//Paiting over to remove image
						con.drawImage(imgBlack, -10, 0);
						
					}			
				}
			}
			
			//Checking to see if user needs help
			if(chrChoice == 'h' || chrChoice == 'H'){
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
			if(chrChoice == 's' || chrChoice == 'S'){
				//Making boolRunning false to eliminate glitching of the text from constantly being printed
				boolRunning = false;
				
				System.out.println("User wants to check scoreboard");
				con.clear();
				con.println("					     SCOREBOARD");
				
				//Making boolRunning true to have game play continue
				boolRunning = true;
				
			}
						
			//Checking to see if user found the secret screen and printing a joke with countdown
			if(chrChoice == '`'){
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
			if(chrChoice == 'q' || chrChoice == 'Q'){
				System.out.println("User quits game");
				con.clear();
				con.println("Thanks for playing!");
				con.println("We hope to see you again.");
				con.sleep(5000);
				boolRunning = false;
			}
			
			//Checking to see if user enterd invalid input
			if(chrChoice == 'q' || chrChoice == 'Q'
			||chrChoice == 'b' || chrChoice == 'B'
			||chrChoice == 'h' || chrChoice == 'H'
			||chrChoice == 's' || chrChoice == 'S'
			||chrChoice == '~'){
				System.out.println("Invalid Input");
				con.clear();
				boolRunning = false;
				chrChoice = 'b';
				boolRunning = true;
				
			}
			
		}
    }
}
