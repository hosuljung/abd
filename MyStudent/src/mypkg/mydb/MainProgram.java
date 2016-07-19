package mypkg.mydb;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
public class MainProgram extends JFrame {
	private TablePanel mytable = null ;
	private TabbedPanel mytabpane = null ;	
	public MainProgram(String title) {
		super( title ) ;
		super.setSize(810, 480);
		this.compose();
		this.setevent() ;
		
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

		int xpos = (int)(screen.getWidth() - super.getWidth()) / 2 ;
		int ypos = (int)(screen.getHeight() - super.getHeight()) / 2 ;

		super.setLocation(xpos, ypos) ; //프레임의 좌측 상단의 좌표를 지정한다.
		
		super.setVisible( true );
	}
	private void setevent() {
	}
	private void compose() {
		mytable = new TablePanel() ;
		mytabpane = new TabbedPanel();		
		
		super.setLayout( new BorderLayout() ); 
		super.add( mytable, BorderLayout.WEST ) ;
		super.add( mytabpane, BorderLayout.CENTER ) ;
	}
	public static void main(String[] args) {
		new MainProgram("성적표 관리");
	}
}