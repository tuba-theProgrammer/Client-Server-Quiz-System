import java.net.*;
import java.io.*;
import java.sql.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class Server {

	public static void main(String[] args) {
		
		Channel channel = new Channel();
		channel.getMessage();
		
	}

}
class Channel{							//This class will entertain all requests of Clients
	
	static ServerSocket serverSocket;
	static String clientRequest="";
	static AcessDataBase acessDataBase;
	static Connection con;
	static ResultSet rs;
	static Statement st;
	static OutputStream messageToClient;
	static DataOutputStream writer;
	static Socket s;
	public Channel(){	
		try{
		 	System.out.println("Initializing the connections");
				 	
			serverSocket=new ServerSocket(5600);
		 	
			
			
		}catch(Exception ex){
			//System.out.println("Port Issue.....Restart Server"+ex.toString());
		}
	}
	
	@SuppressWarnings("deprecation")
	public static void getMessage(){
	    try{
	    System.out.println("Geting message");
	    Class.forName("com.mysql.cj.jdbc.Driver");
	    String url = "jdbc:mysql://localhost:3306/quiz_database?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

	    con=DriverManager.getConnection(url,"root","");
		
		st=con.createStatement();
			
			Socket socket;
			DataInputStream in ;
			
			
				
			ExecutorService Cthreadpool = Executors.newCachedThreadPool();
			
			acessDataBase = new AcessDataBase(clientRequest);
			
		while(true){
			//Keep listening the requests	
			System.out.println("Waiting for cleint reruest");
				
				socket=serverSocket.accept();
				in = new DataInputStream(socket.getInputStream());
				messageToClient=socket.getOutputStream();
				writer=new DataOutputStream(messageToClient);
			
				clientRequest=in.readUTF();
				String request[]=clientRequest.split(":");
				System.out.println("Client request Requested");
			
				if(request[0].equals("Login"))
				{
					
					Cthreadpool.execute(new LoginRequestExecutor(clientRequest));
				}
				else if(request[0].equals("UpdateScore"))
				{
					System.out.println("Going to update score of "+request[1]+" s: "+request[2] );
					Cthreadpool.execute(new UpdateScoreExecutor(clientRequest));
				}
				
		}
		
		
		
	    }catch (Exception e) {
			// TODO: handle exception
	    	
	    	e.printStackTrace();
		}
	    
	    
	}
}

class LoginRequestExecutor extends Channel implements Runnable
{
	String request;
	public LoginRequestExecutor(String request) {
		// TODO Auto-generated constructor stub
		this.request=request;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		acessDataBase.Login(writer, request,con, st);
	}
	
	}

class UpdateScoreExecutor extends Channel implements Runnable
{
	String request;
	public UpdateScoreExecutor(String request) {
		// TODO Auto-generated constructor stub
		this.request=request;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		acessDataBase.UpdateScore(writer, request,con, st);
	}
	
	}



	