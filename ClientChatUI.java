package pac2;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ClientChatUI extends JFrame{
	private String userName;// ��½�ɹ����û���
	private JTextArea jta_recive = new JTextArea(15, 25);// ��ʾ���յ���Ϣ�����
	private JComboBox jcb_users = new JComboBox();// //�����û�����������
	File logFile=null;
	public ClientChatUI(String userName){
		this.userName=userName;
		initUI();
		showFrame();
	}
	
	// ��ʼ��������ʾ���
	private void initUI() { 
		// �û����ı�ǩ
		JLabel la_name = new JLabel("���յ�����Ϣ:");
		JLabel la_users = new JLabel("���͸�:");
		final JTextField jtf_sned = new JTextField(20);// ���������
		final javax.swing.JButton bu_send = new javax.swing.JButton("Send");
		//���һ����ʷ��ť
		final javax.swing.JButton bu_history = new javax.swing.JButton("��ʷ");
		//��Ӻ��� 
		jcb_users.addItem("����");
		jcb_users.addItem("����");
		this.add(la_name);
		this.add(jta_recive);
		this.add(la_users);
		this.add(jtf_sned);
		this.add(jcb_users);
		this.add(bu_send);
		this.add(bu_history);
		// Ϊ���Ͱ�ť����¼�
		ActionListener sendListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==bu_send) {
				String reciver = (String) jcb_users.getSelectedItem();
				reciver = reciver.trim();// ȥ���ո�
				String content = jtf_sned.getText();
				// ����һ��������Ϣ
				String message = userName+"��"+reciver+"˵:"+content+"\r\n";
				jta_recive.append(message);//��ʾ������
				jtf_sned.setText("");
				writeLog(message);
				}else if (e.getSource()==bu_history){
					try {
						readLog();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}}};
				

//Ϊ��ʷ��ť��� �¼�
		//�˴�������ͬѧ���Լ����
		bu_send.addActionListener(sendListener);
		bu_history.addActionListener(sendListener);
		
	}
    
	//��ʾһ���������ķ���
	public void showFrame(){
		//���ñ���
		this.setTitle("netjava:��ӭ" + this.userName);
		java.awt.FlowLayout fl = new java.awt.FlowLayout(0);
		this.setLayout(fl);
		this.setSize(300, 500);
		this.setDefaultCloseOperation(3); //����ر�ʱ�����˳�
		this.setVisible(true);
	}
	//���������¼
	private void writeLog(String message){
		 logFile=new File("C:\\a.log");
		FileOutputStream fout=null;
		//д�ļ�
		try {
			//��׷�ӵķ�ʽд�ļ�
			fout = new FileOutputStream(logFile,true);
			fout.write(message.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//��ȡ�����¼
	private void readLog() throws Exception{
		//��ȡ�����¼�Ĵ�����ôд����ͬѧ���Լ����
		byte[] b=new byte[1024];
		FileInputStream fin=new FileInputStream(logFile);
		BufferedInputStream buf=new BufferedInputStream(fin);
		while(buf.read(b, 0, 1024)!=-1) {
			
			jta_recive.append("��ʷ�����¼��"+new String(b));	
		}
		 //fin.read(b);
	}
	public static void main(String[] args) {
		new ClientChatUI("ͼͼ");
	}
}

