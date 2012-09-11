package Demo;

import javax.swing.*;
public class Java_Break_keyword {
	public static void main(String args[]){
		String str = "roseindia", choice = "", rt = "sorry";
		boolean value = true;
		cerebro:
			while(value){ 
				JOptionPane.showMessageDialog(null, "Either provide Password \n or type sorry and exit safely", "Cerebro zone" ,1); 
				choice = JOptionPane.showInputDialog(null, "First chance", "Password gateway" ,1); 
				if((choice.equals(str))){
					JOptionPane.showMessageDialog(null, "welcome professor\n \twelcome to cerebro", "\tAccess humbly granted",1);
					JOptionPane.showMessageDialog(null, "Congratulations\n good buy \t&\n\t hava a nice day", "\to",1);
					break cerebro;
				}

				if (choice.equals(rt)) {
					JOptionPane.showMessageDialog(null, " Exit Successfully ", "Don,t ever enter this zone" ,1);
					break;
				} 
				else if(!(choice.equals(str)) && !(choice.equals(rt)) ){
					choice = JOptionPane.showInputDialog(null, "Last chance", "Password gateway" ,1);
					if((choice.equals(str))){
						JOptionPane.showMessageDialog(null, "welcome professor\n \twelcome to cerebro", "\tAccess humbly granted",1);
						JOptionPane.showMessageDialog(null, "Congratulations\n good buy \t&\n\t hava a nice day", " \to",1);
					}
					else if ((choice.equals(rt)) || !((choice.equals(rt))) )
						JOptionPane.showMessageDialog(null, "! Security Alert --- \n\tUnauthorised user", "Antivirus Spy Baadshah", 1);
					break cerebro;
				}
			}
	}
}