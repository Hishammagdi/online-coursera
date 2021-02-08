/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectoop;

import java.io.*;
import static java.lang.System.out;
import java.util.Scanner;
import java.util.Vector;
import javax.swing.*;

/**
 *
 * @author Mahmoud Mohamed
 */
class ClassStudents extends ClassUser {
        int grade=0;
        int totalgrade=0;

    public ClassStudents(String UserName, String Email, String Password, String TypeOfUser) {
        super(UserName, Email, Password, TypeOfUser);
    }

//    @Override
//    public void ViewAndEditProfile() {
//                
//    }
    public void ViewAnnouncement(JTextArea ta,String str){
            Vector<String>vectornote=ReadFile("Announcement.txt");
               for(int i=0;i< vectornote.size();i++){
                        String arr[] = vectornote.elementAt(i).split("&");
                       ta.setText(str+" : "+arr[1]);
                   }
    }
    
    public void WriteGrade(String str){
            int sum=0;  
            grade = Integer.parseInt(str);
            try{
               FileWriter fw = new FileWriter("Grade.txt",true);
               PrintWriter pw = new PrintWriter(fw);
               pw.print(UserName+"&");
               pw.print("Exam is grade "+"&");
               pw.print(grade);
               Vector<String>collect = ReadFile("Grade.txt");
               for(int i=0;i<collect.size();i++){
                   String arr[]=collect.elementAt(i).split("&");
                   if(arr[0].contains(UserName)){
                      sum+=Integer.parseInt(arr[2].trim());
                     totalgrade = sum;
                   }
               }
               
            //   pw.print(UserName+"&");
               pw.print("Exam is grade "+"&");
               pw.println(totalgrade);

            }catch(IOException e){
                  
                out.println("error!");
            }
    }
    
    public String ReadGrade(){
        String sum=null;
        Vector<String>viewtotal = ReadFile("Grade.txt");
        for(int i=0;i<viewtotal.size();i++){
            String arr[] = viewtotal.elementAt(i).split("&");
            if(UserName.contains(arr[0])){
                sum =arr[4];
            }
        }
        return sum;
    }
    
    public boolean CheckAns(String c1,int numOfQuation){
           
           Vector<String> vector = ReadFile("Exams.txt");
        
           String arr[] = vector.elementAt(numOfQuation).split("&");
           if(arr[5].contains(c1)){
               return true;
           }

        return false;
    }

}
