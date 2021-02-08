/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectoop;
import com.sun.scenario.effect.Blend;
import java.awt.Desktop;
import projectoop.LoginForm;
import javax.swing.*;
import java.io.*;
import java.util.Scanner;
import static java.lang.System.*;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hisham
 */
public  abstract class ClassUser {
    
    public String UserName;
    public String Email;
    private String Password;
    public String Courses[];
    static int  count = 0;
    static int NumOfUsers = 0;
    String TypeOfUser;
    /**
     *
     */
    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }
    
    public  ClassUser(String Email,String Password,String TypeOfUser){
        this.Email =Email;
        this.Password=Password;
        this.TypeOfUser = TypeOfUser;
    }
    
    public  ClassUser(String UserName,String Email,String Password,String TypeOfUser){
        this(Email,Password,TypeOfUser);
        this.UserName=UserName;
        count++;
        NumOfUsers++;
    };
   
    public void WriteFile(){
        try{
               FileWriter fw = new FileWriter("Emails.txt",true);
               PrintWriter pw = new PrintWriter(fw);
               pw.print(UserName+"&");
               pw.print(getEmail()+"&");
               pw.print(getPassword()+"&");
               pw.println(TypeOfUser);
               pw.close();
            }catch(IOException e){
                  
                out.println("error!");
            }
    }

    public static Vector ReadFile(String filename){
            Vector<String>vec = new Vector();
            File file = new File(filename);
     try{
             Scanner scr = new Scanner(file);
             while(scr.hasNext()){
             String data = scr.nextLine();
             vec.add(data);
               
             }  
     }  catch (Exception ex) {
         System.out.println("error");
     }
    
     return vec;
    }
    
    public void readMaterial(int x ){
      
            Vector<String>vect = ReadFile("Material.txt");

            try{ 
               for(int i=0;i<vect.size();i++){ 
             String user1[] = vect.elementAt(i).split("&");
             String str = Integer.toString(x); 
             if(user1[0].equals(str)){
                Desktop.getDesktop().open(new File(user1[1]));
             }
             }
            } 
            catch (FileNotFoundException ex) {
                 Logger.getLogger(HomeForm.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                 Logger.getLogger(HomeForm.class.getName()).log(Level.SEVERE, null, ex);
          }
}
    
    public String check(int x){
          
           Vector<String>vect = ReadFile("Material.txt");
           for(int i=0;i<vect.size();i++){
             String user1[] = vect.elementAt(i).split("&");
             String str = Integer.toString(x); 
             System.out.println(user1[1]);
             if(user1[0].equals(str)){
                return user1[1] ;
              }
           }
            return null;
}

    public boolean Login(String email, String password){
       Vector <String> vect = ReadFile("Emails.txt");
       
       for(int i=0;i<vect.size();i++){
           String user[] = vect.elementAt(i).split("&");
           if(user[1].contains(email)&&user[2].contains(password)){
               return true;
           }
       }
      
        return false;
    }
   
    public void display(){
        System.out.print("user name:"+UserName+"  ");
        System.out.print("Email:"+Email+"  ");
        System.out.print("password:"+Password+"  ");
        System.out.println("type user:"+TypeOfUser);
    }
    
    public void EditProfile() throws IOException{  
               Vector vect =ReadFile("Emails.txt");      
               if(TypeOfUser.contains("Instructor")){
                   ClassUser user = new ClassInstructor(UserName,Email,Password,TypeOfUser);
                            user.WriteFile();
               }
               else{
                   ClassUser user1 = new ClassInstructor(UserName,Email,Password,TypeOfUser);
                            user1.WriteFile();

               }
                FileWriter fww = new FileWriter("Emails.txt",true);
                PrintWriter pw = new PrintWriter(fww); 
                for(int i=0;i<vect.size();i++){ 
                   pw.print(vect.elementAt(i)+"");
                }
                pw.close();

}
    
  //  public abstract void ViewAndEditProfile();
}
