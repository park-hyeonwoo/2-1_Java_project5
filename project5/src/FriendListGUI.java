import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class FriendListGUI extends JFrame {
	public FriendListGUI(String fileName) {
		int[] count = {0};
		FriendList friendsList = FriendList.friendListGenerator();
		FriendListFile flf = new FriendListFile();
		flf.readFileToList(fileName);
		
		setTitle("친구 목록");
		setSize(800,400);
		
		JPanel panel = new JPanel();
		JPanel buttons = new JPanel();
		JPanel friends = new JPanel();
		
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout());
		panel.add(friends, BorderLayout.NORTH);
		friends.add(new JLabel(" "));
		friends.add(new JLabel("이름"));
		friends.add(new JLabel("그룹"));
		friends.add(new JLabel("전화번호"));
		friends.add(new JLabel("Email"));
		friends.add(new JLabel("사진"));
		JCheckBox[] cbox = new JCheckBox[100];
		JLabel[] name = new JLabel[100];
		JTextField[][] info = new JTextField[100][4];
		for (int i=0; i<100; i++)
			cbox[i] = new JCheckBox();
		while ( friendsList.friends[count[0]].name != null ) {
		name[count[0]] = new JLabel(friendsList.friends[count[0]].name);
		info[count[0]][0] = new JTextField(Integer.toString(friendsList.friends[count[0]].group));
		info[count[0]][1] = new JTextField(friendsList.friends[count[0]].phone);
		info[count[0]][2] = new JTextField(friendsList.friends[count[0]].email);
		info[count[0]][3] = new JTextField(friendsList.friends[count[0]].picture);
		friends.add(cbox[count[0]]);
		friends.add(name[count[0]]);
		friends.add(info[count[0]][0]);
		friends.add(info[count[0]][1]);
		friends.add(info[count[0]][2]);
		friends.add(info[count[0]][3]);
		count[0]++;
		}
		friends.setLayout(new GridLayout(count[0]+1,6));
		
		add(buttons, BorderLayout.EAST);
		buttons.setLayout(new GridLayout(4,1));
		JButton addbtn = new JButton("Add");
		JButton delbtn = new JButton("Delete");
		JButton modbtn = new JButton("Modify");
		JButton savebtn = new JButton("Save File");
		buttons.add(addbtn);
		buttons.add(delbtn);
		buttons.add(modbtn);
		buttons.add(savebtn);
		
		addbtn.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		       Frame addFrame = new JFrame();
		       addFrame.setTitle("추가될 친구 정보");
		       addFrame.setSize(600,200);
				
		       JPanel addFriend = new JPanel();
				
		       JButton donebtn = new JButton("Done");
		       addFrame.add(donebtn, BorderLayout.EAST);
		       addFrame.add(addFriend, BorderLayout.CENTER);
		       addFriend.setLayout(new GridLayout(2,5));
		       addFriend.add(new JLabel("이름"));
		       addFriend.add(new JLabel("그룹"));
		       addFriend.add(new JLabel("전화번호"));
		       addFriend.add(new JLabel("Email"));
		       addFriend.add(new JLabel("사진"));
		       JTextField[] frd = new JTextField[5];
		       for (int i=0; i<5; i++) {
		    	   frd[i] = new JTextField();
		    	   addFriend.add(frd[i]);
		    	   }
		       
		       donebtn.addActionListener(new ActionListener() {
		    	   public void actionPerformed(ActionEvent e) {
		    		   try {
		    			boolean bool1=true, bool2=true;
						String regex = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$";
						for (int i=0; i<count[0]; i++) {
							if (frd[0].getText().equals(friendsList.friends[i].name)) {
								JOptionPane.showMessageDialog(null, "Name Conflict");
								bool1 = false;
							}}
						if (!(frd[3].getText().matches(regex))) {
							JOptionPane.showMessageDialog(null, "Illegal email address");
							bool2 = false;
						}
					   if (bool1 && bool2) {
		    		   friendsList.friends[count[0]].name = frd[0].getText();
		    		   friendsList.friends[count[0]].group = Integer.parseInt(frd[1].getText());
		    		   friendsList.friends[count[0]].phone = frd[2].getText();
		    		   friendsList.friends[count[0]].email = frd[3].getText();
		    		   friendsList.friends[count[0]].picture = frd[4].getText();
		    		   name[count[0]] = new JLabel(friendsList.friends[count[0]].name);
		    		   info[count[0]][0] = new JTextField(Integer.toString(friendsList.friends[count[0]].group));
		    		   info[count[0]][1] = new JTextField(friendsList.friends[count[0]].phone);
		    		   info[count[0]][2] = new JTextField(friendsList.friends[count[0]].email);
		    		   info[count[0]][3] = new JTextField(friendsList.friends[count[0]].picture);
		    		   friends.add(cbox[count[0]]);
		    		   friends.add(name[count[0]]);
		    		   friends.add(info[count[0]][0]);
		    		   friends.add(info[count[0]][1]);
		    		   friends.add(info[count[0]][2]);
		    		   friends.add(info[count[0]][3]);
		    		   count[0]++;
		    		   friends.setLayout(new GridLayout(count[0]+1,6));
		    		   friends.revalidate();		    		   
		    		   addFrame.setVisible(false);
						}}
						catch(NumberFormatException ne) {
							JOptionPane.showMessageDialog(null, "Illegal value");
						}
		    		   }});
				
		       addFrame.setVisible(true);
		    }});
		
		delbtn.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	for (int i=0; i<100; i++) {
		    		if (cbox[i].isSelected()) {
		    			for (int j=i; j<count[0]; j++) {
		    				friendsList.friends[j].name = friendsList.friends[j+1].name;
		    				friendsList.friends[j].group = friendsList.friends[j+1].group;
		    				friendsList.friends[j].phone = friendsList.friends[j+1].phone;
		    				friendsList.friends[j].email = friendsList.friends[j+1].email;
		    				friendsList.friends[j].picture = friendsList.friends[j+1].picture;
		    				name[j].setText(friendsList.friends[j].name);
		    				info[j][0].setText(Integer.toString(friendsList.friends[j].group));
		    				info[j][1].setText(friendsList.friends[j].phone);
		    				info[j][2].setText(friendsList.friends[j].email);
		    				info[j][3].setText(friendsList.friends[j].picture);
		    			}
	    				friends.remove(cbox[count[0]-1]);
	    				friends.remove(name[count[0]-1]);
	    				friends.remove(info[count[0]-1][0]);
	    				friends.remove(info[count[0]-1][1]);		
	    				friends.remove(info[count[0]-1][2]);
	    				friends.remove(info[count[0]-1][3]);
		    			count[0]--;
		    			friends.setLayout(new GridLayout(count[0]+1,6));
		    			friends.revalidate();
		    			JOptionPane.showMessageDialog(null, "Deletion Completed");
		    			break;
		    			}
		    		}
		    }});

		modbtn.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	for (int i=0; i<100; i++) {
		    		if (cbox[i].isSelected()) {
		    			try {
		    				String regex = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$";
							if (!(info[i][2].getText().matches(regex)))
								JOptionPane.showMessageDialog(null, "Illegal email address");
							else {
								friendsList.friends[i].group = Integer.parseInt(info[i][0].getText());
								friendsList.friends[i].phone = info[i][1].getText();
								friendsList.friends[i].email = info[i][2].getText();
								friendsList.friends[i].picture = info[i][3].getText();
								JOptionPane.showMessageDialog(null, "Modification Completed");
							}}
		    			catch(NumberFormatException ne) {
							JOptionPane.showMessageDialog(null, "Illegal value");
						}
		    			break;
		    			}
		    		}
		    }});
		
		savebtn.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent ev) {
		    	try {
		    	FileOutputStream output = new FileOutputStream(fileName);
		    	PrintWriter pw = new PrintWriter(output);
		    	for (int i=0; i<count[0]; i++) {
		    		String line = friendsList.friends[i].name + " : " + 
		    	friendsList.friends[i].group + " : " + 
		    				friendsList.friends[i].phone + " : " + 
		    	friendsList.friends[i].email + " : " +
		    				friendsList.friends[i].picture;
		    		pw.println(line);
		    	}
		    	
		    	pw.close();
		    	output.close();
		    	JOptionPane.showMessageDialog(null, "File Save Completed");
		    	}
				catch(FileNotFoundException e) {
					System.out.println("Unknown File");
				}
				catch(IOException e) {
					System.out.println("IO Error");
				}
		    }});
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}