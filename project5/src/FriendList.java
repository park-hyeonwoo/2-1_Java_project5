public class FriendList {
	Friend[] friends = new Friend[100];
	
	private FriendList() {
		for(int i=0 ; i<100; i++)
			friends[i] = new Friend();
	}
	
	static FriendList friendsList = new FriendList();
	
	public static FriendList friendListGenerator() {
		return friendsList;
	}
	
	public int numFriends() {
		int count = 0;
		for (int i=0 ; i<100 ; i++)
			if (friends[i].name != null)
				count++;
		return count;
	}
	
	public Friend getFriend(int i) { 
		return friends[i];
	}
}