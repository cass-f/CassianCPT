//-------------------------------------------
//Name: CassianCPT
//Purpose: Play hangman
//Author: Cassian
//Created: 19/12/2024
//-------------------------------------------
import arc.*;
import java.awt.*;
import java.awt.image.*;


public class CassianCPT { 
    public static void main(String[] args) {
        Console con = new Console("Hangman", 1280, 780);
        
        //Changing the colour of the text
        con.setTextColor(Color.RED);
        
        //Boolean to ensure the game keeps looping
		boolean boolRunning = true;
		
		//Creating and initializing permanent variables (don't change during game)	
		//Images					
		BufferedImage imgGallow = con.loadImage("gallows.png");
		BufferedImage imgBlack = con.loadImage("blackScreen.png");
		
		//The man
		//Right arm
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
				
		//Right Leg
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
		
		//Left Arm
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
		
		//Left Leg
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

			//Creating, initializing, and resetting temporary variables between gameplays (Varibales get updated during gameplay)
			//Main menu
			char chrChoice = 'b';
			
			//Themes
			char chrTheme = '0';
			String strFiles[];
			boolean boolPicking = true;
			
			//Name
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
			String strTempPos = "";
			
			//Word array
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
			String strLetterPos[][];
			int intLengthGuess = 0; 
			String strTempLetter = "";
			int intLetterCount = 0;
			String strSecretWord[];
			int intLetterPos;
			boolean boolPick = false;
			int intNextWord = 0;
				  
			//Scoreboard
			String strScoreboard[][];   
			int intScoreboardLength = 0;  
			String strScoreName = "";
			String strScorePoint = "0";
			String strTempScoreName;
			String strTempScorePoint;
			String strScoreBurn;
			 
			//Replay
			boolean boolGameplay;  
			char chrPlay = ' ';
			
			//Themes 
			//Creating and/or initializing variables
			int intThemeCount = 0;
			String strThemeFile[];
			intCount = 0;
			String strTempTheme;
			int intTempCount;
			
			//Making "themes.txt" into a variable to avoid hardcoding
			String strThemeList = "themes.txt";
			
			//Getting the length of txt file
			intThemeCount = CPTTools.lengthTxt(strThemeList);
			           
			//Create array and open file
			strThemeFile = new String [intThemeCount];
			TextInputFile ThemeList = new TextInputFile(strThemeList);
			
			//Load themes.txt data into array
			while(ThemeList.eof() == false){	
				strTempTheme = ThemeList.readLine();
				strThemeFile[intCount] = strTempTheme;
				intCount++;
			}
			
			//Closing themes.txt
			ThemeList.close();

			//In homescreen, and printing out the homescreen options.
			if(chrChoice == 'b' || chrChoice == 'B'){
				//Clearing the screen of any previous code
				con.clear();
				
				//Showing options to player and asking player what they want to do
				System.out.println("In the homescreen.");
				con.println("					     HANGMAN");
				con.println("PLAY (P)");
				con.println("HELP (H)");
				con.println("SCOREBOARD (S)");
				con.println("QUIT (Q)");
				chrChoice = con.getChar();
				System.out.println("User choice: "+chrChoice);
			}
			
			//Chceking to see if user wants to play
			if(chrChoice == 'p' || chrChoice == 'P'){
				System.out.println("User wants to play.");
				
				//Making boolRunning false to eliminate glitching of the text from constantly being printed
				boolRunning = false;
						
				//Asking user to pick a theme
				while(boolPicking == true){
			
					//Asking user for username	
					while(strName == ""){	
						con.clear();
						con.println("					     PICK A THEME");		
						con.print("Username: ");
						strName = con.readLine();
						//Trimming their name to eliminate any leading and trailing spaces
						strName = strName.trim();						
					}
					
					//Showing user the themes
					intTempCount = 0;
					for(intCount = 1; intCount <= intThemeCount; intCount++){
						con.println("("+intCount+")"+strThemeFile[intTempCount]);
						System.out.println("before:	"+intTempCount);
						
						if(intCount < intThemeCount){
							intTempCount++;
						}
						System.out.println("after:	"+intTempCount);
					}
					//Allowing user to pick a theme and keeps asking until a valid input is entered
					while(chrTheme != ('1') && chrTheme != ('2') && chrTheme != ('3') && chrTheme != ('4') && chrTheme != ('5')){	
						chrTheme = con.getChar();
					}
					
					//Getting the theme they picked in words
					if(chrTheme == '1'){
						strTheme = "Holidays";
						boolPicking = false;
						strFileName = "holidays.txt";
					}else if(chrTheme == '2'){
						strTheme = "City Names";
						boolPicking = false;
						strFileName = "cityNames.txt";
					}else if(chrTheme == '3'){
						strTheme = "Food";
						boolPicking = false;
						strFileName = "food.txt";
					}else if(chrTheme == '4'){
						strTheme = "Video Games";
						boolPicking = false;
						strFileName = "videoGames.txt";
					}else if(chrTheme == '5'){
						strTheme = "Animals";
						boolPicking = false;
						strFileName = "animals.txt";
					}else{
						boolPicking = true;
					}
				}
				
				//Clearing above code
				con.clear();
					
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
				//Printing out the word and random number after bubble sort into terminal window
				for(intRow = 0; intRow < intWordCount; intRow++){
					System.out.println("after: " + strWord[intRow][0] + "	|	" + strWord[intRow][1]);
				}
				
				//initializing variable
				boolGameplay = true;
				
				//Drawing gallow
				con.drawImage(imgGallow, 0, 115);
				
				//initializing array
				strLetterPos = new String[0][0];	
				strSecretWord = new String[0];		
							
				//Keep looping until user wants to quit or until end of txt file
				while(boolGameplay == true){
					
					//Making intNextWord = 0 if user reaches end of the txt file 
					if(intNextWord == intWordCount){
						intNextWord = 0;						
					}
			
					//Getting the word
					System.out.println("Picked word: "+strWord[intNextWord][0]);
					strGuessWord = strWord[intNextWord][0];
					
					//Asking user for guess
					con.clear();
					con.println("Username: "+strName);
					con.println("Theme: "+strTheme);
					con.println("Points: "+intPoints);
					con.println("Games Played: "+intRound);
					con.println("Lives Left: "+intLives);
										
					//Setting it so the word lines are lined up with gallow (made it go bellow the gallows)
					for(intCount = 0; intCount < 14; intCount++){
						con.println(" ");	
					}
					
					//Getting length of word
					intLength = strGuessWord.length();
					
					//Printing the number of needed lines respective to the word
					if(intLives == 6){
						strSecretWord = new String[intCount];
						for(intCount = 0; intCount < intLength; intCount++){
							strSecretWord[intCount] = "_ ";
							con.print(strSecretWord[intCount]);
						}
					}else{
						//Loop to print lines and revealed letter
						for(intCount = 0; intCount < intLength; intCount++){
							con.print(strSecretWord[intCount]);
						}
					}

					//Asking user to guess word
					con.println(" ");
					con.print("Guess your word here: ");
					strGuess = con.readLine();
					
					//Trimming their guess to eliminate any spaces before or after their guess
					strGuess = strGuess.trim();
					
					//Win screen
					if(strGuess.equalsIgnoreCase(strGuessWord) && intLives > 0){
						//Clearing the screen and changing the needed variables
						con.clear();
						System.out.println("User won");
						boolGameplay = false;
						boolPick = true;
						intPoints++;
						intRound++;
						intNextWord++;
						con.println("Congrats! you have won");
						con.println("Plus one point!, You now have "+intPoints+" points");
						//Asking user if they want to replay
						con.print("Do you want to keep playing? (Y/N) ");
						
					//If wrong, user can still continue if lives is less than 7
					}else if(!strGuess.equalsIgnoreCase(strGuessWord) && intLives > 0){
						con.clear();
						System.out.println("Incorrect Guess");
						con.println("Incorrect Guess.");
						chrPlay = ' ';
						intLives = intLives - 1;
						intLengthGuess = strGuessWord.length();
												
						System.out.println("intLives:" + intLives);					

						//If stamtent only allows code to enter letter randomization once
						if(intLives == 5){

							//Initalizing array
							strLetterPos = new String[intCount][3];
							
							//Creating an array for the word, filling array: [letter][random number][position]
							for(intCount = 0; intCount < intLengthGuess; intCount++){
								strLetterPos[intCount][2] = Integer.toString(intCount);
							}							
							
							for(intCount = 0; intCount < intLengthGuess; intCount++){
								strLetterPos[intCount][0] = Character.toString(strGuessWord.charAt(intCount));
								strLetterPos[intCount][1] = Integer.toString((int)(Math.random()*100 + 0));
								System.out.println("before: "+strLetterPos[intCount][0] +" "+strLetterPos[intCount][1] + " "+strLetterPos[intCount][2]);
							}
							
							//Randomizing letters for output
							for(intRow2 = 0; intRow2 < intLengthGuess; intRow2++){		
								for(intRow = 1; intRow < intLengthGuess; intRow++){
									//Checking to see if the left is smaller than that on the right
									if(Integer.parseInt(strLetterPos[intRow][1]) < Integer.parseInt(strLetterPos[intRow - 1][1])){
										//Take the left item
										strTempNum = strLetterPos[intRow][1];
										strTempWord = strLetterPos[intRow][0];
										strTempPos = strLetterPos[intRow][2];
										
										//Right item moves to the left
										strLetterPos[intRow][0] = strLetterPos[intRow - 1][0];
										strLetterPos[intRow][1] = strLetterPos[intRow - 1][1];
										strLetterPos[intRow][2] = strLetterPos[intRow - 1][2];
										
										//Move the left item to the right (the temporary item)
										strLetterPos[intRow - 1][1] = strTempNum;
										strLetterPos[intRow - 1][0] = strTempWord;
										strLetterPos[intRow - 1][2] = strTempPos;
									}	
								}
							}
							//Printing out the word and random number after bubble sort
							for(intRow = 0; intRow < intLengthGuess; intRow++){
								System.out.println("after: " + strLetterPos[intRow][0] + " " + strLetterPos[intRow][1] + " " + strLetterPos[intRow][2]);
							}
						}

						//Drawing the man
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
						
						//Putting random letter into array for printing
						intLetterPos = Integer.parseInt(strLetterPos[intLetterCount][2]);
						strSecretWord[intLetterPos] = strLetterPos[intLetterCount][0];
							
						System.out.print("Random 2 strSecretWord[intLetterPos]: " + strSecretWord[intLetterPos] + "	");
						
						intLetterCount++;
						con.sleep(1000);
					}
				
						
					//Lose screen							
					if(intLives <= 0){
						//Outputting "user lost" to both terminal window and console
						System.out.println("User lost");
						con.println("You have lost.");
						
						//Clearing the screen
						con.clear();
						
						//Showing user the word
						con.println("The word is: "+strWord[intNextWord][0]);
						
						//Updating the needed variables
						boolGameplay = false;
						intRound++;
						intNextWord++;
						
						//Printing out how many points they have to the console
						con.println("You have "+intPoints+" points");
												
						//Asking user if they want to replay
						con.print("Do you want to keep playing? (Y/N) ");
						boolPick = true;
					}
	
					//Adding redudency 
					while(boolPick == true){
						//Getting user input to see if they want to replay
						chrPlay = con.getChar();
						//If user wants to play, a new word is picked and they play again
						if(chrPlay == 'Y' || chrPlay == 'y' && intRound <= intWordCount){
							con.clear();
							con.println("You have chosen to play again.");
							con.println("Picking new word...");
							con.sleep(2000);
							con.clear();
							boolGameplay = true;
							boolPick = false;
							intLives = 6;
							intLetterCount = 1;
							
							//Paiting over to remove image
							con.drawImage(imgBlack, -10, 0);
							
							//Drawing image
							con.drawImage(imgGallow, 0, 115);						
							
						//If the user does not want to play, they are sent back to home screen
						}else if(chrPlay == 'N' || chrPlay == 'n' || intRound > intLength){
							con.clear();
							chrChoice = 'b';
							boolRunning = true;
							boolGameplay = false;
							boolPick = false;
							intLives = 6;
							intLetterCount = 1;
							
							//Checking to see if they have the secret user name. If so, +2 pts
							if(strName.equalsIgnoreCase("statitan")){
								con.clear();
								con.println("You get 2 extra points");
								intPoints = intPoints + 2;
								System.out.println(intPoints);
								con.sleep(2000);
							}		
							
							//Printing player stats to screoboard
							TextOutputFile Scoreboard = new TextOutputFile("Scoreboard.txt", true);
							Scoreboard.println(strName);
							Scoreboard.println(intPoints);
								
							//Closing file
							Scoreboard.close();
							
							//Bringing user back to home screen and resetting everything	
							chrChoice = 'b';
													
							//Paiting over to remove image
							con.drawImage(imgBlack, -10, 0);						
						}else{
							boolPick = true;
							chrPlay = 'p';
						}
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
				con.println("Enter B to go home");
				while(chrChoice != 'b' && chrChoice != 'B'){
					chrChoice = con.getChar();
				}
				
				//Making boolRunning true to have game play continue
				boolRunning = true;
			}
			
			//Checking to see if user wants to check scoreboard
			if(chrChoice == 's' || chrChoice == 'S'){
				
				System.out.println("User wants to check scoreboard");
				
				//Making boolRunning false to eliminate glitching of the text from constantly being printed
				boolRunning = false;
				intCount = 0;
				
				//Get the length of the file and open file
				intScoreboardLength = CPTTools.lengthTxt("Scoreboard.txt");
				TextInputFile score = new TextInputFile("Scoreboard.txt");		
				
				//Divide by 2 because 1 score has 2 records in the file (name and points)
				intScoreboardLength = intScoreboardLength/2;
				
				//Creating array					
				System.out.println(intScoreboardLength);
				strScoreboard = new String[intScoreboardLength][2];
				
				//Filling the array with the text from file
				while(score.eof() == false){
					//Getting values
					strScoreName = score.readLine();
					strScorePoint = score.readLine();
					
					System.out.println("Name: "+strScoreName);
					System.out.println("Points: "+strScorePoint);
					
					//Inputing values into array
					strScoreboard[intCount][0] = strScoreName;
					strScoreboard[intCount][1] = strScorePoint;
					
					//Increasing the count to fill other spots of the array
					intCount++;
				}

				//Bubble sorting
				for(intRow2 = 0; intRow2 < intScoreboardLength; intRow2++){		
					for(intRow = 1; intRow < intScoreboardLength; intRow++){	
						//Checking to see if the left is smaller than that on the right
						if(Integer.parseInt(strScoreboard[intRow][1]) > Integer.parseInt(strScoreboard[intRow - 1][1])){
							//Take the left item
							strTempScoreName = strScoreboard[intRow][0];
							strTempScorePoint = strScoreboard[intRow][1];
							
							//Right item moves to the left
							strScoreboard[intRow][0] = strScoreboard[intRow - 1][0];
							strScoreboard[intRow][1] = strScoreboard[intRow - 1][1];
							
							//Move the left item to the right (the temporary item)
							strScoreboard[intRow - 1][0] = strTempScoreName;
							strScoreboard[intRow - 1][1] = strTempScorePoint;
						}	
					}
				}
				
				//Printing out the scoreboard
				con.clear();
				con.println("					     SCOREBOARD");
				con.println("Place - Name - Points");
				con.sleep(500);
					
				for(intCount = 0; intCount < intScoreboardLength; intCount++){	
					con.println(intCount+1 + " - " + strScoreboard[intCount][0] + " - " + strScoreboard[intCount][1]);
					con.sleep(500);
					
				}					
				//Bringing them back to home after scoreboard is done printing
				con.println("Enter B to go home");
				while(chrChoice != 'b' && chrChoice != 'B'){
					chrChoice = con.getChar();
				}
				
				//Making boolRunning true to get the screen to go back to home screen
				boolRunning = true;
				
				//Closing file
				score.close();							
			}
						
			//Checking to see if user found the secret screen and printing a joke with countdown
			if(chrChoice == '`'){
				//Making boolRunning false to eliminate glitching of the text from constantly being printed
				boolRunning = false;
				
				//Printing a ocuntdown for the joke
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
			
			// Redudency loop, user will still be in homescreen if input is invalid. 
			//If statment is used as else statments would not work
			if(chrChoice != 'q' && chrChoice != 'Q'
			||chrChoice != 'b' && chrChoice != 'B'
			||chrChoice != 's' && chrChoice != 'S'
			||chrChoice != 'p' && chrChoice != 'P'
			||chrChoice != 'h' && chrChoice != 'H'
			||chrChoice != '~'){
				System.out.println("Invalid");
				chrChoice = 'b';
			}
		}
    }
}
