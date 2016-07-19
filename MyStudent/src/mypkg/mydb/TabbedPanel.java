package mypkg.mydb;

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

//우측에 놓이는 사용자 정의 JTabbedPane 컴포넌트
public class TabbedPanel extends JPanel {
	private final int XXX = 15;
	private JTabbedPane jTabPane = null;
	private JPanel insert = null;
	private static JPanel update = null;
	private JScrollPane jsPane = null;

	private void compose() {
		// super.setBackground(Color.YELLOW);
		jTabPane = new JTabbedPane(JTabbedPane.TOP);
		insert = new InsertPanel();
		update = new UpdatePanel();

		jTabPane.add("추가", insert);
		jTabPane.add("수정", update);

		jsPane = new JScrollPane(jTabPane);
		jsPane.setPreferredSize(new Dimension(300, 420));

		super.add(jsPane);
	}

	class InsertPanel extends JPanel {
		private String[] slabel = { "아이디", "이름", "국어", "영어", "수학" };
		private JLabel[] jlabel = new JLabel[5];
		private JTextField[] jfield = new JTextField[5];
		JButton btnExe = null;

		class MyKeyHandler extends KeyAdapter {
			@Override
			public void keyPressed(KeyEvent event) {
				Object who = event.getSource();
				int keyCode = event.getKeyCode();
				if (who instanceof JTextField) {
					if (keyCode == KeyEvent.VK_ENTER) {
						if (who == jfield[4]) {
							// System.out.println("호호");
							btnExe.requestFocus();
						} else {
							// System.out.println("하하");
							// 다음 컴포넌트로 포커스 이동
							((Component) who).transferFocus();
						}
					}
				} else if (who instanceof JButton) {
					if (keyCode == KeyEvent.VK_ENTER) {
						if ((JButton) event.getSource() == btnExe) {
							// System.out.println("추가 해야지");							
							Student student = getStudentInfo();
							int cnt = TablePanel.InsertStudent(student);
						}
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent event) {
				Object who = event.getSource();
				if (who == jfield[2] || who == jfield[3] || who == jfield[4]) {
					JTextField jtf = (JTextField) who;
					try {
						int su = Integer.parseInt(jtf.getText());
						if (su > 100 || su < 0) {
							JOptionPane.showMessageDialog(null,
									"점수는 0이상 100이하 이지 말입니다.", 
									"점수 입력 오류",
									JOptionPane.ERROR_MESSAGE);
						}
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null,
								"올바른 숫자 형식이 아닙니다.",
								"점수 입력 오류",
								JOptionPane.ERROR_MESSAGE);
						
						jtf.requestFocus();
						return;
					}
				}else if (who == jfield[0] || who == jfield[1] ) {
					//3 <= id <= 8, 2 <= name <= 10   
				}
			}

			@Override
			public void keyTyped(KeyEvent event) {
				Object who = event.getSource();
				char keyCode = event.getKeyChar();

				if (who instanceof JTextField) {			
					if (who == jfield[2] || who == jfield[3] || who == jfield[4]) {
						if ((keyCode < '0') || (keyCode > '9')) { // 숫자 키가 아닌
							getToolkit().beep(); // 비프 음
							event.consume(); // 입력 거부(이벤트 소멸)
						}
					}
				}
			}
		}

		public InsertPanel() {
			this.compose();
			this.setevent();
		}

		private void compose() {
			// super.setBackground(Color.BLUE);
			super.setLayout(null);
			for (int i = 0; i < jlabel.length; i++) {
				jlabel[i] = new JLabel(slabel[i]);
				jlabel[i].setFont(new Font("굴림체", Font.BOLD, 15));
				jlabel[i].setBounds(XXX, 3 * XXX * (i + 1), 8 * XXX, XXX);
				super.add(jlabel[i]);
			}
			for (int i = 0; i < jfield.length; i++) {
				jfield[i] = new JTextField("" + (10 * (i + 1)));
				jfield[i].setFont(new Font("굴림체", Font.BOLD, 15));
				jfield[i].setBounds(8 * XXX, 3 * XXX * (i + 1), 8 * XXX,
						(int) (1.5 * XXX));
				jfield[i].addKeyListener(new MyKeyHandler());
				super.add(jfield[i]);
			}
			jfield[0].setText("");
			jfield[1].setText("");
			//jfield[0].setText("hong");
			//jfield[1].setText("홍길동");

			btnExe = new JButton("추가하기");
			btnExe.setBounds(XXX, 18 * XXX, 15 * XXX, (int) (1.5 * XXX));

			super.add(btnExe);
		}

		private Student getStudentInfo() {
			// 입력된 데이터를 이용하여 Bean 객체를 생성해주는 메소드
			Student student = new Student(jfield[0].getText(),
					jfield[1].getText(), Integer.parseInt(jfield[2].getText()),
					Integer.parseInt(jfield[3].getText()),
					Integer.parseInt(jfield[4].getText()));
			return student;
		}

		private void setevent() {
			btnExe.addKeyListener(new MyKeyHandler());
			btnExe.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {					
					// System.out.println("행 추가하자");
					//유효성 검사 : 잘못된 데이터 검증 절차
					Student student = getStudentInfo();
					int cnt = TablePanel.InsertStudent(student);
					if (cnt != 0) {
						//모든 JTextFiled 컴포넌트 클리어
					}
				}
			});
		}
	}

	public class UpdatePanel extends JPanel {
		private String[] slabel = { "아이디", "이름", "국어", "영어", "수학" };
		private JLabel[] jlabel = new JLabel[5];
		private JTextField[] jfield = new JTextField[5];
		JButton btnExe = null;

		public void FillData(Student student) {
			// System.out.println("수정 TextFiled에 보여 준다");
			jTabPane.setSelectedIndex(1); // 수정 탭 클릭
			jfield[0].setText(student.getId());
			jfield[1].setText(student.getName());
			jfield[2].setText(String.valueOf(student.getKor()));
			jfield[3].setText(String.valueOf(student.getEng()));
			jfield[4].setText(String.valueOf(student.getMath()));

		}

		public UpdatePanel() {
			this.compose();
			this.setevent();
		}

		private void compose() {
			// super.setBackground(Color.BLUE);
			super.setLayout(null);
			for (int i = 0; i < jlabel.length; i++) {
				jlabel[i] = new JLabel(slabel[i]);
				jlabel[i].setFont(new Font("굴림체", Font.BOLD, 15));
				jlabel[i].setBounds(XXX, 3 * XXX * (i + 1), 8 * XXX, XXX);
				super.add(jlabel[i]);
			}
			for (int i = 0; i < jfield.length; i++) {
				jfield[i] = new JTextField("" + (10 * (i + 1)));
				jfield[i].setFont(new Font("굴림체", Font.BOLD, 15));
				jfield[i].setBounds(8 * XXX, 3 * XXX * (i + 1), 8 * XXX,
						(int) (1.5 * XXX));
				super.add(jfield[i]);
			}
			jfield[0].setText("hong");
			jfield[1].setText("홍길동");
			jfield[0].setEditable(false);

			btnExe = new JButton("수정하기");
			btnExe.setBounds(XXX, 18 * XXX, 15 * XXX, (int) (1.5 * XXX));
			super.add(btnExe);
		}

		private Student getStudentInfo() {
			// 입력된 데이터를 이용하여 Bean 객체를 생성해주는 메소드
			Student student = new Student(jfield[0].getText(),
					jfield[1].getText(), Integer.parseInt(jfield[2].getText()),
					Integer.parseInt(jfield[3].getText()),
					Integer.parseInt(jfield[4].getText()));
			return student;
		}

		private void setevent() {
			btnExe.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("행 수정하자");
					Student student = getStudentInfo();
					int cnt = TablePanel.UpdateStudent(student);
				}
			});
		}
	}

	public TabbedPanel() {
		this.compose();
		this.setevent();
	}

	private void setevent() {
	}

	public static void FillData(Student student) {
		// JTable에서 클릭된 내용이 넘어 왔다.
		((UpdatePanel) update).FillData(student);
	}
}
