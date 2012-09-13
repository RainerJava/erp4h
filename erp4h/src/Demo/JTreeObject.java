package Demo;

import javax.swing.*;
import java.awt.*;
import javax.swing.JTree.*;
import java.awt.BorderLayout;
import java.util.*;

public class JTreeObject extends JFrame{
  String[][] Data = {
  {"Amar"}, {"BCA", "Address","rohini","Delhi"},
  {"Vinod"}, {"BCA", "Software", "Rohini", "Delhi"},
  {"Chandan"}, {"MCA", "Software", "Programer", "Rohini", "Delhi"}, 
  {"Suman"}, {"MCA", "Deginer", "Web", "Delhi"},
  {"Ravi"},{"MCA","Software","programer"}};

  public JTreeObject(){
  super("JTreeobject frame");
  setSize(300, 250);
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  public void init(){
  Hashtable hash = new Hashtable();
  for (int i = 0; i < Data.length; i+=2){
  hash.put(Data[i][0], Data[i + 1]);
  }
  JTree tree = new JTree(hash);
  getContentPane().add(tree, BorderLayout.CENTER);
  }
  public static void main(String args[]){
  JTreeObject object = new JTreeObject();
  object.init();
  object.setVisible(true);
  }
}