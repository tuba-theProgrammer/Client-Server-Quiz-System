import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.*;

public class ClientWindow extends JFrame {

	private JPanel contentPane;
	private  JTextField ID_txt;
	private  JTextField Pass_txt;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				try {
					
					new ClientWindow();
					
				} catch (Exception e)
				{
					//e.printStackTrace();
				}
			}
		});
	
	
	}
	public ClientWindow() {
		
				
		setTitle("Quiz System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 264);
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Online Quiz System");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel.setBounds(118, 33, 214, 23);
		contentPane.add(lblNewLabel);
		
		JButton Login_btn = new JButton("Login");
		Login_btn.setFont(new Font("Tahoma", Font.BOLD, 13));
		Login_btn.setBounds(218, 179, 89, 23);
		contentPane.add(Login_btn);
		
		ID_txt = new JTextField();
		ID_txt.setBounds(162, 94, 199, 20);
		contentPane.add(ID_txt);
		ID_txt.setColumns(10);
		
		JLabel ID_lb = new JLabel("Enter ID:");
		ID_lb.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		ID_lb.setBounds(71, 96, 81, 14);
		contentPane.add(ID_lb);
		
		Pass_txt = new JTextField();
		Pass_txt.setColumns(10);
		Pass_txt.setBounds(162, 137, 199, 20);
		contentPane.add(Pass_txt);
		
		JLabel Pass_lb = new JLabel("Enter Pass:");
		Pass_lb.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		Pass_lb.setBounds(71, 140, 81, 14);
		contentPane.add(Pass_lb);
		
		setVisible(true);
		
		//Buttons action Listener
		Login_btn.addActionListener(new ActionListener(){
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent ae)
			{
				
				ConnectToServer client = new ConnectToServer(ID_txt.getText().toString(),Pass_txt.getText().toString());
				ExecutorService executor = Executors.newFixedThreadPool(5);
				executor.execute(client);
				
			}
		});
		
	}
}


class ConnectToServer  implements Runnable
{
	  
	static Socket clientSocket;	
	static OutputStream messageToServer;
	static DataOutputStream sendMessage;
	static DataInputStream in ;
	public static  String id,pass;
	
	public ConnectToServer(String id,String pass)
	{
		
		this.id=id;
		this.pass=pass;
		
		try{
			clientSocket=new Socket("localhost",5600);
			messageToServer=clientSocket.getOutputStream();	
			sendMessage = new DataOutputStream(messageToServer);
			in = new DataInputStream(clientSocket.getInputStream());
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	@Override
	public void run()
	{
			Login();
					
	}
@SuppressWarnings("deprecation")
public void Login(){
		
		try{
			
			sendMessage.writeUTF("Login:"+id+":"+pass);
		
			String response = ""+in.readUTF(); //Here we will get the quize
			
			
			String[] values = response.split("#");
			
			ArrayList<QuestionHolder> questionHolder = new ArrayList<QuestionHolder>();
			
			
			
			String resp = values[0];
			String c = "Y";
			
		
		
			
			if(resp.equals(c))
			{
				String[] Questions = values[1].split("-");
				
				
				
				for(String q : Questions)
				{
					
					String questionData[] = q.split(":");
					questionHolder.add(new QuestionHolder(questionData[0], questionData[1], questionData[2], questionData[3], questionData[4], questionData[5], questionData[6]));	
				}
				
				PlayQuiz frame = new PlayQuiz(questionHolder,id);
				frame.setVisible(true);
				
			}
			else
			{
				JOptionPane.showMessageDialog(null,"Please enter a valid user name and password");
			}
			
			
		}catch(Exception ex){
			JOptionPane.showMessageDialog(null,"There is error in getting response from server.. Please try again");
		}
	}
}

