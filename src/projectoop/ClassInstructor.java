/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectoop;

import java.io.*;

import static java.lang.System.*;
import javax.swing.*;

/**
 *
 * @author Hisham
 */
public class ClassInstructor extends ClassUser {
    JButton ButAdd[]= new JButton[15] ;
    public ClassInstructor(String UserName, String Email, String Password, String TypeOfUser) {
        super(UserName, Email, Password, TypeOfUser);
    }

    
//    @Override
//    public void ViewAndEditProfile() {
//
//    }
//    public void ShareAnnouncement(String Announcement){
//        
//    }
    
    public String AddMaterial(int x){
    JFileChooser choose = new JFileChooser();
        choose.showOpenDialog(null);
        File file = choose.getSelectedFile();
        try{
               String filename = file.getAbsolutePath();
               FileWriter fw = new FileWriter("Material.txt",true);
        try (PrintWriter pw = new PrintWriter(fw)) {
                String str = Integer.toString(x);
                pw.print(str+"&");
                pw.println(filename);
        }
               return filename;
//               Path.add(filename);
            }catch(IOException e){
                  
                out.println("error!");
            }
        return null;
}
    
    public void SendMaterial(int x){
      if(ButAdd[x-5].getText().contains("Add")&&check(x)==null){
                String s = AddMaterial(x);
                if(s.contains("mp4")){
                   ButAdd[x-5].setText("mp4");
                }
                else if(s.contains("pdf")){
                   ButAdd[x-5].setText("pdf");
                }
                else{
                   ButAdd[x-5].setText("pdf"); 
                }
            }
            else{
                
                readMaterial(x);
            }
  } 
    
    

}
