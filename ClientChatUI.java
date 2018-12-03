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
	private String userName;// 登陆成功的用户名
	private JTextArea jta_recive = new JTextArea(15, 25);// 显示接收到消息的组件
	private JComboBox jcb_users = new JComboBox();// //在线用户名的下拉框
	File logFile=null;
	public ClientChatUI(String userName){
		this.userName=userName;
		initUI();
		showFrame();
	}
	
	// 初始化界面显示组件
	private void initUI() { 
		// 用户名的标签
		JLabel la_name = new JLabel("接收到的消息:");
		JLabel la_users = new JLabel("发送给:");
		final JTextField jtf_sned = new JTextField(20);// 发送输入框
		final javax.swing.JButton bu_send = new javax.swing.JButton("Send");
		//添加一个历史按钮
		final javax.swing.JButton bu_history = new javax.swing.JButton("历史");
		//添加好友 
		jcb_users.addItem("张三");
		jcb_users.addItem("李四");
		this.add(la_name);
		this.add(jta_recive);
		this.add(la_users);
		this.add(jtf_sned);
		this.add(jcb_users);
		this.add(bu_send);
		this.add(bu_history);
		// 为发送按钮添加事件
		ActionListener sendListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==bu_send) {
				String reciver = (String) jcb_users.getSelectedItem();
				reciver = reciver.trim();// 去除空格
				String content = jtf_sned.getText();
				// 发送一条聊天消息
				String message = userName+"对"+reciver+"说:"+content+"\r\n";
				jta_recive.append(message);//显示到界面
				jtf_sned.setText("");
				writeLog(message);
				}else if (e.getSource()==bu_history){
					try {
						readLog();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}}};
				

//为历史按钮添加 事件
		//此处代码请同学们自己完成
		bu_send.addActionListener(sendListener);
		bu_history.addActionListener(sendListener);
		
	}
    
	//显示一个窗体界面的方法
	public void showFrame(){
		//设置标题
		this.setTitle("netjava:欢迎" + this.userName);
		java.awt.FlowLayout fl = new java.awt.FlowLayout(0);
		this.setLayout(fl);
		this.setSize(300, 500);
		this.setDefaultCloseOperation(3); //窗体关闭时程序退出
		this.setVisible(true);
	}
	//保存聊天记录
	private void writeLog(String message){
		 logFile=new File("C:\\a.log");
		FileOutputStream fout=null;
		//写文件
		try {
			//以追加的方式写文件
			fout = new FileOutputStream(logFile,true);
			fout.write(message.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//读取聊天记录
	private void readLog() throws Exception{
		//读取聊天记录的代码怎么写，请同学们自己完成
		byte[] b=new byte[1024];
		FileInputStream fin=new FileInputStream(logFile);
		BufferedInputStream buf=new BufferedInputStream(fin);
		while(buf.read(b, 0, 1024)!=-1) {
			
			jta_recive.append("历史聊天记录："+new String(b));	
		}
		 //fin.read(b);
	}
	public static void main(String[] args) {
		new ClientChatUI("图图");
	}
}

