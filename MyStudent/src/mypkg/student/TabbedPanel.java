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

//������ ���̴� ����� ����
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

		jTabPane.add("�߰�",insert);
		jTabPane.add("����",update);
		jsPane = new JScrollPane(jTabPane);
		jsPane.setPreferredSize(new Dimension(300,420));

		//�����̳�.setLayout(null);
		//setSize(����,����);
		//setLocation(����x��ǥ,���y��ǥ);
		//setBounds(����x��ǥ,���y��ǥ,����,����);
		//���� ����
		//JPanel �ǳ� = new JPanel();
		//�ǳ�.setLayout(null);
		//JButton ��ư = new JButton("ȣȣ");
		//��ư.setSize(100,40);
		//��ư.setLocation(50,50);
		//�ǳ�.add(��ư);
		super.add(jsPane);

	}
	class InsertPanel extends JPanel{

		private String[] slabel ={"���̵�","�̸�","����","����","����"};		
		private JLabel[] jlabel = new JLabel[5];
		private JTextField[] jfield = new JTextField[5];
		JButton btnExe = null;

		private void compose(){
			super.setLayout(null);
			for(int i=0;i<jlabel.length;i++){
				jlabel[i] = new JLabel(slabel[i]);
				jlabel[i].setFont(new Font("����ü",Font.BOLD,15));
				jlabel[i].setBounds(XXX,3*XXX*(i+1),8*XXX,XXX);
				super.add(jlabel[i]);
			}
			for(int i=0;i<jfield.length;i++){
				jfield[i] = new JTextField(""+(10*(i+1)));
				jfield[i].setFont(new Font("����ü",Font.BOLD,15));
				jfield[i].setBounds(8*XXX,3*XXX*(i+1),8*XXX,(int)(1.5*XXX));
				jfield[i].addKeyListener(new MykeyHandler());
				super.add(jfield[i]);
			}
			jfield[0].setText("hong");
			jfield[0].setText("ȫ�浿");

			btnExe = new JButton("�߰��ϱ�");
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
						if((keyCode<'0')||(keyCode>'9')){//����Ű�� �ƴѰ�� �����Ѵ�.
							getToolkit().beep();//���� ��
							event.consume();//�Է� �ź�(�̺�Ʈ �Ҹ�)
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
							//System.out.println("����");
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
							JOptionPane.showMessageDialog(null, "������ 0�̻� 100���� ���� ���Դϴ�.","���� �Է� ����",JOptionPane.ERROR_MESSAGE);
							
							jtf.requestFocus();
							return;
						}
					}catch(NumberFormatException e){
						System.out.println("�ùٸ� ���� ������ �ƴմϴ�.");
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
			//�Էµ� �����͸� �̿��Ͽ� Bean ��ü�� �������ִ� �޼ҵ�
			Student student = new Student(jfield[0].getText(), jfield[1].getText(), Integer.parseInt(jfield[2].getText()),Integer.parseInt(jfield[3].getText()),Integer.parseInt(jfield[4].getText()));
			return student;
		}
		private void setevent(){
			btnExe.addKeyListener(new MykeyHandler());
			btnExe.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("���߰�����");
					Student student = getStudentInfo();
					int cnt = TablePanel.InsertStudent(student);
					if(cnt !=0){
						//��� JTextField ������Ʈ Ŭ����
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
		private String[] slabel ={"���̵�","�̸�","����","����","����"};		
		private JLabel[] jlabel = new JLabel[5];
		private JTextField[] jfield = new JTextField[5];
		JButton btnExe = null;

		public void FillData(Student student){
			System.out.println("���� TextFiled�� �����ش�.");
			jTabPane.setSelectedIndex(1);//���� �� Ŭ��\
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
				jlabel[i].setFont(new Font("����ü",Font.BOLD,15));
				jlabel[i].setBounds(XXX,3*XXX*(i+1),8*XXX,XXX);
				super.add(jlabel[i]);
			}
			for(int i=0;i<jfield.length;i++){
				jfield[i] = new JTextField(""+(10*(i+1)));
				jfield[i].setFont(new Font("����ü",Font.BOLD,15));
				jfield[i].setBounds(8*XXX,3*XXX*(i+1),8*XXX,(int)(1.5*XXX));
				super.add(jfield[i]);
			}
			jfield[0].setText("hong");
			jfield[0].setEditable(false);
			jfield[0].setText("ȫ�浿");

			btnExe = new JButton("�����ϱ�");
			btnExe.setBounds(XXX,18*XXX,15*XXX,(int)(1.5*XXX));
			super.add(btnExe);
		}


		private Student getStudentInfo(){
			//�Էµ� �����͸� �̿��Ͽ� Bean ��ü�� �������ִ� �޼ҵ�
			Student student = new Student(jfield[0].getText(), jfield[1].getText(), Integer.parseInt(jfield[2].getText()),Integer.parseInt(jfield[3].getText()),Integer.parseInt(jfield[4].getText()));
			return student;
		}
		private void setevent(){
			btnExe.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("���߰�����");
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
