
/* Implementation of operator precedence grammer*/

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class OperatorPrecedence extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OperatorPrecedence frame = new OperatorPrecedence();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public OperatorPrecedence() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 739, 497);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEnterTheGrammer = new JLabel("Enter the expression");
		lblEnterTheGrammer.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
		lblEnterTheGrammer.setBounds(22, 15, 178, 36);
		contentPane.add(lblEnterTheGrammer);
		
		textField = new JTextField();
		textField.setFont(new Font("Tw Cen MT", Font.PLAIN, 22));
		textField.setBounds(197, 17, 163, 36);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblOperatorGrammar = new JLabel("Operator grammar");
		lblOperatorGrammar.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
		lblOperatorGrammar.setBounds(22, 69, 166, 25);
		contentPane.add(lblOperatorGrammar);
		
		JLabel lblRelationTable = new JLabel("Relation Table");
		lblRelationTable.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
		lblRelationTable.setBounds(241, 64, 131, 30);
		contentPane.add(lblRelationTable);
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
		textArea.setBounds(22, 105, 150, 113);
		contentPane.add(textArea);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setFont(new Font("Tw Cen MT", Font.PLAIN, 18));
		textArea_1.setBounds(241, 115, 408, 311);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(241, 115, 408, 311);
		contentPane.add(scrollPane_1);
		scrollPane_1.setViewportView(textArea_1);
		
		JButton btnImplement = new JButton("Implement");
		btnImplement.setFont(new Font("Tw Cen MT", Font.PLAIN, 18));
		btnImplement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String OG[]=new String[3];        //Operator grammer
				OG[0]="E->E+E";
				OG[1]="E->E*E";                  
				OG[2]="E->id";
				
				char top,ip;
				char t[]={'i','+','*','$'};     //Terminal symbols
				
				int R[][]=new int[4][4];
				R[0][0]=0;
				R[0][1]=1;
				R[0][2]=1;
				R[0][3]=1;
				R[1][0]=-1;					//Relation table
				R[1][1]=1;
				R[1][2]=-1;
				R[1][3]=1;
				R[2][0]=-1;
				R[2][1]=1;
				R[2][2]=1;
				R[2][3]=1;
				R[3][0]=-1;
				R[3][1]=-1;
				R[3][2]=-1;
				R[3][3]=0;
				
				textArea.setText(OG[0]+'\n'+OG[1]+'\n'+OG[2]);  //Print operator grammer
				
				String stk="$";         						//Stack string 
				String input=textField.getText();
				
				int output=-1,cnt=0,l=stk.length(),Done=0,L=input.length();
				textArea_1.append(stk+"   ");
				String temp=new String();
				for(int z=cnt;z<input.length();z++){
					temp+=input.charAt(z);                      //Printing INPUT column
				}
				textArea_1.append(temp);
				
				while(Done!=1)                                //LOOP Till string get accepted
				{
					top=stk.charAt(l-1);
					ip=input.charAt(cnt);
					if(top=='E' && ip=='$' && l==2){
						textArea_1.append("     Accepted"+'\n');		//String get ACCEPTED
						Done=1;
					}
					else if((top=='$' && ip=='$') ||(top=='i' && ip=='i'))  //Checking for ERROR condition
					{
						textArea_1.append("       Error");
						break;
					}
					else if(top=='E' && ip!='$')							//Shift condition
					{	
						stk+=ip;
						cnt++;
						l++;
						textArea_1.append("      Shift"+'\n'+stk+"        ");
						String temp2=new String();
						for(int z=cnt;z<input.length();z++){
							temp2+=input.charAt(z);
						}
						textArea_1.append(temp2);
										
					}
					else if(top=='E' && ip=='$')						//Reduce condition 
					{
						String s=new String();
						char c;
						if(stk.charAt(l-2)=='*') output=1;
						else output=0;
						for(int m=0;m<(l-2);m++)
						{
							c=stk.charAt(m);
							s+=c;
						}
						stk=s;
						l=l-2;
						textArea_1.append("       Reduce by"+OG[output]+'\n'+stk+"     ");
						String temp3=new String();
						for(int z=cnt;z<input.length();z++){
							temp3+=input.charAt(z);
						}
						textArea_1.append(temp3);
					}
					else 									//Checking Relation Table
					{	int m=0,n=0;
						for(int i=0;i<4;i++)
						{
							if(top==t[i])  
								m=i;
						}
						for(int i=0;i<4;i++)
						{
							if(ip==t[i])  
								n=i;
						}
						if(R[m][n]==-1)
						{
							stk+=ip;
							cnt++;
							l++;
							textArea_1.append("      Shift"+'\n'+stk+"       ");
							String temp4=new String();
							for(int z=cnt;z<input.length();z++){
								temp4+=input.charAt(z);
							}
							textArea_1.append(temp4);
						}
						else
						{
							if(top=='i')
							{
								String s=new String();
								char c;
								for(int a=0;a<(l-1);a++)
								{
									c=stk.charAt(a);
									s+=c;
								}
								output=2;
								stk=s;
								stk+='E';
								textArea_1.append("      Reduce by"+OG[output]+'\n'+stk+"      ");
								String temp5=new String();
								for(int z=cnt;z<input.length();z++){
									temp5+=input.charAt(z);
								}
								textArea_1.append(temp5);
							}
							else
							{
								output=-1;
								String s=new String();;
								char c;
								if(stk.charAt(l-2)=='*') output=1;
								else output=0;
								for(int a=0;a<(l-2);a++)
								{
									c=stk.charAt(a);
									s+=c;
								}
								stk=s;
								l=l-2;
								textArea_1.append("      Reduce by"+OG[output]+'\n'+stk+"      ");
								String temp6=new String();
								for(int z=cnt;z<input.length();z++){
									temp6+=input.charAt(z);
								}
								textArea_1.append(temp6);
							}	}
					}	}
			}
		});
		btnImplement.setBounds(371, 16, 142, 36);
		contentPane.add(btnImplement);
		
		JButton btnCheckAnotherString = new JButton("Check another\n string");
		btnCheckAnotherString.setFont(new Font("Tw Cen MT", Font.PLAIN, 16));
		btnCheckAnotherString.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText(null);
				textArea.setText(null);
				textArea_1.setText(null);
			}
		});
		btnCheckAnotherString.setBounds(523, 17, 169, 36);
		contentPane.add(btnCheckAnotherString);
		
		
	}
}
