package ebooks;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class GuiShow {
	public Frame f;
	public Button btn1,btn2;
	public TextArea artice;
	public Label title;
	public Panel top;
	public ShowBooks book;
	public GuiShow(Frame f) {
		this.f = f;
	}
	public void init() {
		f.setTitle("ebooks");
		f.setVisible(true);
		f.setBackground(Color.darkGray);
		f.setSize(800, 800);
		f.setLocation(300, 50);
		top = new Panel();
		top.setLayout(new GridLayout(2,2));
		title = new Label();
		title.setFont(new Font("Î¢ÈíÑÅºÚ",Font.BOLD,27));
		title.setForeground(Color.WHITE);
		top.add(new Label());
		top.add(new Label());
		top.add(new Label());
		top.add(new Label());
		top.add(title);
		f.add(top,BorderLayout.NORTH);
		Panel middle = new Panel();
		Panel bottom = new Panel();
		artice = new TextArea(40,95);
		artice.setBounds(0, 0, 700, 500);
		artice.setBackground(Color.darkGray);
		artice.setFont(new Font("Î¢ÈíÑÅºÚ",Font.BOLD,12));
		artice.setForeground(Color.WHITE);
		middle.add(artice);
		bottom.setLayout(new GridLayout(1,2));
		btn1 = new Button("ÉÏÒ»ÕÂ");
		btn2 = new Button("ÏÂÒ»ÕÂ");
		btn1.setFont(new Font("Î¢ÈíÑÅºÚ",Font.BOLD,24));
		btn2.setFont(new Font("Î¢ÈíÑÅºÚ",Font.BOLD,24));
		bottom.add(btn1);
		bottom.add(btn2);
		f.add(middle,BorderLayout.CENTER);
		f.add(bottom,BorderLayout.SOUTH);
		windowListener();
	}
	public void onload(String caption){

	}
	public void setArctice(String word) {
		artice.setText(word);
	}
	public void setTitle(String word) {
		title.setText(word);
	}
	private void windowListener() {
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	public void btn1Listener() {
		btn1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
//					onload();
					book.lastChapter();
//					onload();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		});
		
	}
	public void btn2Listener() {
		btn2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
//					onload();
					book.nextChapter();
//
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		});
	}
	
}





















