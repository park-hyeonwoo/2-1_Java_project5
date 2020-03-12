import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.lang.String;
import java.lang.ArrayIndexOutOfBoundsException;
import java.lang.NumberFormatException;

public class FriendListFile {
	
	public FriendListFile() {
	}
	
	public FriendList readFileToList(String fileName) {
		FriendList friendsList = FriendList.friendListGenerator();
		
		try {
			File file = new File(fileName);
			Scanner input = new Scanner(file);
			int idx = 0;
			while (input.hasNext()) {
				String line = input.nextLine();
				if (!(line.startsWith("//"))) {
				String[] token = line.split(":");
				for(int i=0; i<token.length; i++) {
					token[i] = token[i].trim();
				}
				boolean bool1=true, bool2=true;
				String regex = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$";
				for (int i=0; i<idx; i++) {
					if (token[0].equals(friendsList.friends[i].name)) {
						System.out.println("Name Conflict");
						bool1 = false;
					}}
				if (!(token[3].matches(regex))) {
						System.out.println("Illegal email address");
						bool2 = false;
				}
				if (bool1 && bool2) {
				int token1 = Integer.parseInt(token[1]);
				friendsList.friends[idx].name = token[0];
				friendsList.friends[idx].group = token1;
				friendsList.friends[idx].phone = token[2];
				friendsList.friends[idx].email = token[3];
				friendsList.friends[idx].picture = token[4];
				idx++;
				}
				}
			}
			input.close();
		}
		catch(FileNotFoundException e) {
			System.out.println("Unknown File");
		}
		catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Irregular input line");
		}
		catch(NumberFormatException e) {
			System.out.println("Illegal value");
		}
		return friendsList;
	}
}