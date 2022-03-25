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
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JRadioButton;

public class PlayQuiz extends JFrame {

private JPanel contentPane;
static ArrayList<String> QuizQA=null;
static String[] AttemptQues=new String[7];
static int counterQA=0,CounterAttempt=0;
static String getVal;

/**
* Launch the application.
*/
/*
public static void main(String[] args) {
EventQueue.invokeLater(new Runnable() {
public void run() {
try {
PlayQuiz frame = new PlayQuiz();
frame.setVisible(true);
} catch (Exception e) {
e.printStackTrace();
}
}
});
}
*/


/**
* Create the frame.
*/
static String id;
public PlayQuiz(ArrayList<QuestionHolder> questionHolder,String id) {
	this.id=id;
	
QuizQA  = new ArrayList<String>();


for(QuestionHolder question : questionHolder)
{
	QuizQA.add((question.qNo+","+question.Q+","+question.optA+","+question.optB+","+question.optC+","+question.optD+","+question.optRight));	
}

	
	
setTitle("Play Quiz");
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setBounds(100, 100, 771, 300);
contentPane = new JPanel();
contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
setContentPane(contentPane);
contentPane.setLayout(null);

JLabel Ques_num_lb = new JLabel("_");
Ques_num_lb.setFont(new Font("Times New Roman", Font.BOLD, 13));
Ques_num_lb.setBounds(10, 29, 19, 14);
contentPane.add(Ques_num_lb);

JLabel Question_lb = new JLabel("Question:");
Question_lb.setFont(new Font("Times New Roman", Font.BOLD, 14));
Question_lb.setBounds(42, 17, 707, 37);
contentPane.add(Question_lb);

JButton Save_btn = new JButton("Save");
Save_btn.setBounds(189, 209, 89, 23);
contentPane.add(Save_btn);

JButton Next_btn = new JButton("Next");
Next_btn.setBounds(347, 209, 89, 23);
contentPane.add(Next_btn);


JRadioButton OptionA = new JRadioButton("New radio button");
OptionA.setBounds(10, 73, 361, 23);
contentPane.add(OptionA);

JRadioButton OptionB = new JRadioButton("New radio button");
OptionB.setBounds(373, 73, 376, 23);
contentPane.add(OptionB);

JRadioButton OptionC = new JRadioButton("New radio button");
OptionC.setBounds(10, 121, 339, 23);
contentPane.add(OptionC);

JRadioButton OptionD = new JRadioButton("New radio button");
OptionD.setBounds(373, 121, 376, 23);
contentPane.add(OptionD);


AttemptQues= QuizQA.get(counterQA).split(",");
Ques_num_lb.setText(AttemptQues[0]);
Question_lb.setText(AttemptQues[1]);
OptionA.setText(AttemptQues[2]);
OptionB.setText(AttemptQues[3]);
OptionC.setText(AttemptQues[4]);
OptionD.setText(AttemptQues[5]);


getVal="";
OptionA.addActionListener(new ActionListener(){
   public void actionPerformed(ActionEvent e) {
              if(OptionA.isSelected()) {

getVal=OptionA.getText();

OptionB.setSelected(false);
OptionC.setSelected(false);
OptionD.setSelected(false);

}
             
   }
});
OptionB.addActionListener(new ActionListener(){
   public void actionPerformed(ActionEvent e) {
              if(OptionB.isSelected()) {

getVal=OptionB.getText();

OptionA.setSelected(false);
OptionC.setSelected(false);
OptionD.setSelected(false);

}
             
   }
});

OptionC.addActionListener(new ActionListener(){
   public void actionPerformed(ActionEvent e) {
              if(OptionC.isSelected()) {

getVal=OptionC.getText();

OptionB.setSelected(false);
OptionA.setSelected(false);
OptionD.setSelected(false);

}
             
   }
});
OptionD.addActionListener(new ActionListener(){
   public void actionPerformed(ActionEvent e) {
              if(OptionD.isSelected()) {

getVal=OptionD.getText();

OptionB.setSelected(false);
OptionC.setSelected(false);
OptionA.setSelected(false);

}
             
   }
});
Save_btn.addActionListener(new ActionListener(){


public void actionPerformed(ActionEvent ae)
{  Save_btn.setEnabled(false);
if(getVal.equals(AttemptQues[6])) {

JOptionPane.showMessageDialog(null, "Correct Answer");
CounterAttempt++;

}


}
});

Next_btn.addActionListener(new ActionListener(){


public void actionPerformed(ActionEvent ae)
{   counterQA++;
Save_btn.setEnabled(true);
OptionA.setSelected(false);
OptionB.setSelected(false);
OptionC.setSelected(false);
OptionD.setSelected(false);

if(counterQA<=19) {

AttemptQues= QuizQA.get(counterQA).split(",");
Ques_num_lb.setText(AttemptQues[0]);
Question_lb.setText(AttemptQues[1]);
OptionA.setText(AttemptQues[2]);
OptionB.setText(AttemptQues[3]);
OptionC.setText(AttemptQues[4]);
OptionD.setText(AttemptQues[5]);
}
else {
float percentage=(CounterAttempt/20)*100;
ExecutorService Cthreadpool = Executors.newCachedThreadPool();
Cthreadpool.execute(new UpdateScore(id, CounterAttempt));

JOptionPane.showMessageDialog(null, "Your Score :"+CounterAttempt+"\nPercentage :"+percentage);

}
}
});
    



}



}
class UpdateScore  implements Runnable
{
	  
	static Socket clientSocket;	
	static OutputStream messageToServer;
	static DataOutputStream sendMessage;
	static DataInputStream in ;
	public static  String id;
	static int score;
	
	public UpdateScore(String id,int score)
	{
		this.id=id;
		this.score=score;
		
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
			UpdateScore();
					
	}
@SuppressWarnings("deprecation")

public void UpdateScore(){
		
		try{
			
			sendMessage.writeUTF("UpdateScore:"+id+":"+score);
		
			String response = ""+in.readUTF(); //Here we will get the quize
			
		}catch(Exception ex){
			JOptionPane.showMessageDialog(null,"There is error in getting response from server.. Please try again");
		}
	}
}

