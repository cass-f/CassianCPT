import arc.*;

public class CPTTools{	
	
	//Here is a method to get the length of a txt file
	public static int lengthTxt (String strFile){
		int intWordCount;
		intWordCount = 0;
		String strBurn;
		
		TextInputFile file = new TextInputFile(strFile);
		
		while (file.eof() == false){
			strBurn = file.readLine();
			intWordCount = intWordCount + 1;
		}
		file.close();
		return intWordCount;
	}		
}


