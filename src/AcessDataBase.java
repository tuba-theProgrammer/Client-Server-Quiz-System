import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import java.util.concurrent.locks.*;

@SuppressWarnings("unchecked")
public class AcessDataBase {

	String request;
	private ResultSet rs_Login,rs_Quiz;
	PreparedStatement ps;
	
	public AcessDataBase(String request) {
		// TODO Auto-generated constructor stub
		this.request=request;
	}
	
	public void Login(DataOutputStream writer,String request,Connection con,Statement st)
	{
			try {
				String data[] = request.split(":");
				 rs_Login =  st.executeQuery("Select * from students_account where STU_ID ='"+data[1]+"' and STUD_PASS='"+data[2]+"'");
				 
				 if(rs_Login.next())
					 {
					 	rs_Quiz = st.executeQuery("SELECT * FROM QUIZ_ques");
					 	String quizData="Y#"; 
					 	while(rs_Quiz.next())
					        {
					         quizData+=rs_Quiz.getString(1)+":"+rs_Quiz.getString(2)+":"+rs_Quiz.getString(3)+":"+rs_Quiz.getString(4)+":"+rs_Quiz.getString(5)+":"+rs_Quiz.getString(6)+":"+rs_Quiz.getString(7)+"-";
					        }
					 	writer.writeUTF(quizData);	
					 }
				 else
				 {
						writer.writeUTF("N#");		 
				 }
				 
				} catch (Exception e)
			    
				{
						// TODO Auto-generated catch block
						e.printStackTrace();
				}
	}
	public void UpdateScore(DataOutputStream writer,String request,Connection con,Statement st)
	{
		System.out.println("Updating");
			try {
				String data[] = request.split(":");
				 ps=con.prepareStatement("Update STUDENTS_Account set STUD_SCORE='"+data[2]+"' where STU_ID='"+data[1]+"'");
				
		       // ps.setString(2,data[2]);
				
				
				ps.executeUpdate();
				
				writer.writeUTF("Could Not Update");		 
				 
				 
				} catch (Exception e)
			    
				{
						// TODO Auto-generated catch block
						e.printStackTrace();
				}
	}
	
	
}
