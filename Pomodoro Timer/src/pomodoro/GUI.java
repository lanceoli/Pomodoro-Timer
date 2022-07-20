package pomodoro;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

public class GUI extends RestTimer implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton button;
	JButton resetButton;
	JLabel timeLabel;
	JLabel cycleLabel;
	
	RestTimer animation;
	
	JLabel countDownLabel;
	
	JPanel cyclePanel;
	
	RestTimer timerr2;
	
	int cycle = 1;
	
	int elapsedTime = 0;
	int secondss = 0;
	int minutes = 0;
	int hours = 0;
	boolean started = false;
	String seconds_string = String.format("%02d", secondss);
	String minutes_string = String.format("%02d", minutes);
	String hours_string = String.format("%02d", hours);
	
	JFrame frame;
	
	public GUI() {
		frame = new JFrame();
		timeLabel = new JLabel();
		timeLabel.setLayout(new BorderLayout(10,10));
		
		cycleLabel = new JLabel("Cycle: "+cycle);
		cycleLabel.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		cycleLabel.setBounds(0,0,10,10);
		cycleLabel.setOpaque(true);
		cycleLabel.setBackground(new Color(0x222233));
		cycleLabel.setForeground(new Color(0xAACCFF));
		
		cyclePanel = new JPanel();
		cyclePanel.add(cycleLabel);
		cyclePanel.setBounds(0,0,10,10);
		cyclePanel.setOpaque(true);
		cyclePanel.setBackground(new Color(0x222233));
		
		button = new JButton("START");
		button.addActionListener(this);
		
		resetButton = new JButton("Reset");
		resetButton.addActionListener(this);
		
		JPanel panel = new JPanel();
		panel.add(button);
		
		frame.add(panel, BorderLayout.CENTER);
		frame.setSize(420,420);
	
		
		frame.add(button);
		frame.add(resetButton);
		frame.add(timeLabel);
		frame.add(cyclePanel);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Pomodoro Prompt");
		frame.setResizable(false);
		frame.setVisible(true);
		
		ImageIcon image = new ImageIcon("C:\\Users\\WINDOWS 10\\Documents\\Pomodoro Timer\\a.png");
		frame.setIconImage(image.getImage());
		
		// -------------
		
		timeLabel.setText(hours_string+":"+minutes_string+":"+seconds_string);
		timeLabel.setBounds(100,100,200,100);
		timeLabel.setFont(new Font("Verdana",Font.PLAIN,35));
		timeLabel.setBackground(new Color(0x222233));
		timeLabel.setForeground(new Color(0xAACCFF));
		timeLabel.setOpaque(true);
		timeLabel.setHorizontalAlignment(JTextField.CENTER);
		
		button.setBounds(100,300,100,50);
		button.setFont(new Font("Verdana", Font.PLAIN, 20));
		button.setFocusable(false);
		button.setBackground(new Color(0x222233));
		button.setForeground(new Color(0xAACCFF));

		
		resetButton.setBounds(200,300,100,50);
		resetButton.setFont(new Font("Verdana", Font.PLAIN, 20));
		resetButton.setFocusable(false);
		resetButton.setBackground(new Color(0x222233));
		resetButton.setForeground(new Color(0xAACCFF));
		
		
		}
	
	Timer timer = new Timer(1000,new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
			elapsedTime = elapsedTime + 1000;
			hours = (elapsedTime/3600000);
			minutes = (elapsedTime/60000) % 60;
			secondss = (elapsedTime/1000) % 60;
			seconds_string = String.format("%02d", secondss);
			minutes_string = String.format("%02d", minutes);
			hours_string = String.format("%02d", hours);
			timeLabel.setText(hours_string+":"+minutes_string+":"+seconds_string);
			
			try {
				segs();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		}
		
		public void segs() throws InterruptedException {
			
			int x = 1;
			
			while(x<elapsedTime) {
				x++;
			}
			// 25 minutes
			int r = (minutes*x)/25;
			
			if((r==x) && secondss==0) {
				
				timeLabel.setBackground(new Color(0x320d3e));
				timeLabel.setForeground(new Color(0xd902ee));
						
				button.setBackground(new Color(0x320d3e));
				button.setForeground(new Color(0xf162ff));
				button.setBorder(new LineBorder(new Color(0xf162ff),1,true));
						
				resetButton.setBackground(new Color(0x320d3e));
				resetButton.setForeground(new Color(0xf162ff));
				resetButton.setBorder(new LineBorder(new Color(0xf162ff),1,true));
				
				cyclePanel.setBackground(new Color(0x320d3e));
				cyclePanel.setForeground(new Color(0xf162ff));
				
				cycleLabel.setBackground(new Color(0x320d3e));
				cycleLabel.setForeground(new Color(0xf162ff));
		
				File file = new File("1.wav");
				AudioInputStream audioStream = null;
				try {
					audioStream = AudioSystem.getAudioInputStream(file);
				} catch (UnsupportedAudioFileException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				Clip clip = null;
				try {
					clip = AudioSystem.getClip();
				} catch (LineUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					clip.open(audioStream);
				} catch (LineUnavailableException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				} catch (IOException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
				
				clip.start();
				
				ImageIcon icon = new ImageIcon("stopu.png");
				Image img1 = icon.getImage();
				Image img2 = img1.getScaledInstance(240, 180, java.awt.Image.SCALE_SMOOTH);
				icon = new ImageIcon(img2);
				
				int optionPaneSwitch = cycle%2;
				switch(optionPaneSwitch) {
				default: JOptionPane.showMessageDialog(null,"<html><b style =\"font-size:30px;color:red;font-family:Helvetica;\"> STOPU~desu!</b>\nRest for 5 minutes", "Pomodoro Prompt", JOptionPane.PLAIN_MESSAGE, icon);
				break;
				case 0: JOptionPane.showMessageDialog(null,"<html><b style =\"font-size:30px;color:red;font-family:Helvetica;\"> STOPU~desu!</b>\nRest for 10 minutes", "Pomodoro Prompt", JOptionPane.PLAIN_MESSAGE, icon);
				break;
				}
			
			}
			
			int i = 0;
			
			while (i<elapsedTime) {
				i++;
			}
			
			int s;
			
			int cycleSwitch = cycle%2;
			
			switch(cycleSwitch) {
			// 30 minutes
			default: s = (minutes*x)/30;
			break;
			// 35 minutes
			case 0: s = (minutes*x)/35;
			break;
			}
			
			if ((s==i) && secondss==0) {
				
				timeLabel.setBackground(new Color(0x222233));
				timeLabel.setForeground(new Color(0xAACCFF));
				
				button.setBackground(new Color(0x222233));
				button.setForeground(new Color(0xAACCFF));
				button.setBorder(new LineBorder(new Color(0xAACCFF),1,true));
				
				resetButton.setBackground(new Color(0x222233));
				resetButton.setForeground(new Color(0xAACCFF));
				resetButton.setBorder(new LineBorder(new Color(0xAACCFF),1,true));
				
				cyclePanel.setBackground(new Color(0x222233));
				cyclePanel.setForeground(new Color(0xAACCFF));
				
				cycleLabel.setBackground(new Color(0x222233));
				cycleLabel.setForeground(new Color(0xAACCFF));
				
				File file = new File("2.wav");
				AudioInputStream audioStream = null;
				try {
					audioStream = AudioSystem.getAudioInputStream(file);
				} catch (UnsupportedAudioFileException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				Clip clip = null;
				try {
					clip = AudioSystem.getClip();
				} catch (LineUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					clip.open(audioStream);
				} catch (LineUnavailableException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				} catch (IOException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
				
				clip.start();
				
				ImageIcon icon2 = new ImageIcon("uhh.png");
				Image img1 = icon2.getImage();
				Image img2 = img1.getScaledInstance(341, 191, java.awt.Image.SCALE_SMOOTH);
				icon2 = new ImageIcon(img2);
				JOptionPane.showMessageDialog(null,"<html><b style =\"font-size:30px;font-family:Helvetica;\"> Continue~desu</b></html>", "Pomodoro Prompt", JOptionPane.PLAIN_MESSAGE, icon2);			
		
			cycle++;
			cycleLabel.setText("Cycle: "+cycle);
			
			reset_noCycle();
			
				}
			timer.start();
		}
	
		
		}
		
		
	
	);
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==button) {
			
			if(started==false) {
				started=true;
				button.setText("STOP");
				startTime();
				
			}
			else {
				started=false;
				button.setText("START");
				stop();
			}
			
		}
		if(e.getSource()==resetButton) {
			started=false;
			button.setText("START");
			reset();
		}
		
		
	}
	
	// ----------------------------------


	void startTime() {
		timer.start();
	}
	
	void stop() {
		
		timer.stop();
	}
	
	void reset() {
		timer.stop();
		elapsedTime=0;
		secondss=0;
		minutes=0;
		hours=0;
		seconds_string = String.format("%02d", secondss);
		minutes_string = String.format("%02d", minutes);
		hours_string = String.format("%02d", hours);
		timeLabel.setText(hours_string+":"+minutes_string+":"+seconds_string);
		
		cycle = 1;
		cycleLabel.setText("Cycle: "+cycle);
	}
	
	void reset_noCycle() {
		
		timer.stop();
		elapsedTime=1000;
		secondss=1;
		minutes=0;
		hours=0;
		seconds_string = String.format("%02d", secondss);
		minutes_string = String.format("%02d", minutes);
		hours_string = String.format("%02d", hours);
		timeLabel.setText(hours_string+":"+minutes_string+":"+seconds_string);
	}
	
	public static void main(String[] args) {
		new GUI();
		
	}
	
}