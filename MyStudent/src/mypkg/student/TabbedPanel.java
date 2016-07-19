package mypkg.student;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

//우측에 놓이는 사용자 정의
public class TabbedPanel extends JPanel {
	private final int XXX=15;
	JTabbedPane jTabPane = null;
	JPanel insert =null;
	static JPanel update=null;
	private JScrollPane jsPane = null;
	public TabbedPanel(){
		this.compose();
		this.setevent();
	}
	private void setevent(){
		
	}
	private void compose(){
		//super.setBackground(Color.YELLOW);
		jTabPane = new JTabbedPane(JTabbedPane.TOP);
		insert = new InsertPanel();
		update = new UpdatePanel();

		jTabPane.add("추가",insert);
		jTabPane.add("수정",update);
		jsPane = new JScrollPane(jTabPane);
		jsPane.setPreferredSize(new Dimension(300,420));

		//컨테이너.setLayout(null);
		//setSize(넓이,높이);
		//setLocation(왼쪽x좌표,상단y좌표);
		//setBounds(왼쪽x좌표,상단y좌표,넓이,높이);
		//샘플 예시
		//JPanel 판넬 = new JPanel();
		//판넬.setLayout(null);
		//JButton 버튼 = new JButton("호호");
		//버튼.setSize(100,40);
		//버튼.setLocation(50,50);
		//판넬.add(버튼);
		super.add(jsPane);

	}
	class InsertPanel extends JPanel{

		private String[] slabel ={"아이디","이름","국어","영어","수학"};		
		private JLabel[] jlabel = new JLabel[5];
		private JTextField[] jfield = new JTextField[5];
		JButton btnExe = null;

		private void compose(){
			super.setLayout(null);
			for(int i=0;i<jlabel.length;i++){
				jlabel[i] = new JLabel(slabel[i]);
				jlabel[i].setFont(new Font("굴림체",Font.BOLD,15));
				jlabel[i].setBounds(XXX,3*XXX*(i+1),8*XXX,XXX);
				super.add(jlabel[i]);
			}
			for(int i=0;i<jfield.length;i++){
				jfield[i] = new JTextField(""+(10*(i+1)));
				jfield[i].setFont(new Font("굴림체",Font.BOLD,15));
				jfield[i].setBounds(8*XXX,3*XXX*(i+1),8*XXX,(int)(1.5*XXX));
				jfield[i].addKeyListener(new MykeyHandler());
				super.add(jfield[i]);
			}
			jfield[0].setText("hong");
			jfield[0].setText("홍길동");

			btnExe = new JButton("추가하기");
			btnExe.setBounds(XXX,18*XXX,15*XXX,(int)(1.5*XXX));
			super.add(btnExe);
		}

		class MykeyHandler extends KeyAdapter{

			@Override
			public void keyTyped(KeyEvent event) {
				Object who = event.getSource();
				char keyCode = event.getKeyChar();
				if(who instanceof JTextField){
					if(who == jfield[2]||who ==jfield[3]||who == jfield[4]){
						if((keyCode<'0')||(keyCode>'9')){//숫자키가 아닌경우 무시한다.
							getToolkit().beep();//비프 옴
							event.consume();//입력 거부(이벤트 소멸)
						}
					}
				}
			}

			@Override
			public void keyPressed(KeyEvent event) {
				Object who = event.getSource();
				int keyCode = event.getKeyCode();
				if(who instanceof JTextField){

					if(keyCode == KeyEvent.VK_ENTER){
						if(who == jfield[4]){
							btnExe.requestFocus();
						}else{
							//System.out.println("하하");
							((Component)who).transferFocus();
						}
					}
				}else if(who instanceof JButton){
					if(keyCode == KeyEvent.VK_ENTER){
						if((JButton)event.getSource()==btnExe){
							Student student = getStudentInfo();
							int cnt = TablePanel.InsertStudent(student);
						}
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent event) {
				Object who = event.getSource();
				if(who == jfield[2]|| who == jfield[3] || who == jfield[4]){
					JTextField jtf = (JTextField)who;
					try{
						int su = Integer.parseInt(jtf.getText());
						if(su>100||su<0){
							JOptionPane.showMessageDialog(null, "점수는 0이상 100이하 이지 말입니다.","점수 입력 오류",JOptionPane.ERROR_MESSAGE);
							
							jtf.requestFocus();
							return;
						}
					}catch(NumberFormatException e){
						System.out.println("올바른 숫자 형식이 아닙니다.");
						jtf.requestFocus();
						return;
					}

				}else if(who == jfield[0]||who==jfield[1]){
					
				}

			}

		}

		public InsertPanel(){
			//super.setBackground(Color.BLUE);
			this.compose();
			this.setevent();
		}
		private Student getStudentInfo(){
			//입력된 데이터를 이용하여 Bean 객체를 생성해주는 메소드
			Student student = new Student(jfield[0].getText(), jfield[1].getText(), Integer.parseInt(jfield[2].getText()),Integer.parseInt(jfield[3].getText()),Integer.parseInt(jfield[4].getText()));
			return student;
		}
		private void setevent(){
			btnExe.addKeyListener(new MykeyHandler());
			btnExe.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("행추가하자");
					Student student = getStudentInfo();
					int cnt = TablePanel.InsertStudent(student);
					if(cnt !=0){
						//모든 JTextField 컴포넌트 클리어
					}
				}

			});
		}
	}
	class UpdatePanel extends JPanel{
		public UpdatePanel(){
			//super.setBackground(Color.YELLOW);
			this.compose();
			this.setevent();
		}
		private String[] slabel ={"아이디","이름","국어","영어","수학"};		
		private JLabel[] jlabel = new JLabel[5];
		private JTextField[] jfield = new JTextField[5];
		JButton btnExe = null;

		public void FillData(Student student){
			System.out.println("수정 TextFiled에 보여준다.");
			jTabPane.setSelectedIndex(1);//수정 탭 클릭\
			jfield[0].setText(student.getId());
			jfield[1].setText(student.getName());
			jfield[2].setText(String.valueOf(student.getKor()));
			jfield[3].setText(String.valueOf(student.getEng()));
			jfield[4].setText(String.valueOf(student.getMath()));

		}

		private void compose(){
			super.setLayout(null);
			for(int i=0;i<jlabel.length;i++){
				jlabel[i] = new JLabel(slabel[i]);
				jlabel[i].setFont(new Font("굴림체",Font.BOLD,15));
				jlabel[i].setBounds(XXX,3*XXX*(i+1),8*XXX,XXX);
				super.add(jlabel[i]);
			}
			for(int i=0;i<jfield.length;i++){
				jfield[i] = new JTextField(""+(10*(i+1)));
				jfield[i].setFont(new Font("굴림체",Font.BOLD,15));
				jfield[i].setBounds(8*XXX,3*XXX*(i+1),8*XXX,(int)(1.5*XXX));
				super.add(jfield[i]);
			}
			jfield[0].setText("hong");
			jfield[0].setEditable(false);
			jfield[0].setText("홍길동");

			btnExe = new JButton("수정하기");
			btnExe.setBounds(XXX,18*XXX,15*XXX,(int)(1.5*XXX));
			super.add(btnExe);
		}


		private Student getStudentInfo(){
			//입력된 데이터를 이용하여 Bean 객체를 생성해주는 메소드
			Student student = new Student(jfield[0].getText(), jfield[1].getText(), Integer.parseInt(jfield[2].getText()),Integer.parseInt(jfield[3].getText()),Integer.parseInt(jfield[4].getText()));
			return student;
		}
		private void setevent(){
			btnExe.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("행추가하자");
					Student student = getStudentInfo();
					int cnt = TablePanel.InsertStudent(student);
				}

			});
		}
	}
	public static void FillData(Student student) {
		((UpdatePanel)update).FillData(student);
	}
}
