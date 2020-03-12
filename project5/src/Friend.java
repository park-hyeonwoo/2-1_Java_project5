public class Friend {
	String name;
	int group;
	String phone;
	String email;
	String picture;
	
	public Friend() {
		name = getName();
		group = getGroup();
		phone = getPhone();
		email = getEmail();
		picture = getPicture();
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getGroup() {
		return this.group;
	}
	
	public String getPhone() {
		return this.phone;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getPicture() {
		return this.picture;
	}
	
	public void print() {
		System.out.println(this.name + " : " + this.group + " : " + this.phone + " : " + this.email + " : " + this.picture);
	}
}