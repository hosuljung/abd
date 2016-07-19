package mypkg.student;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

//좌측에 놓이는 사용자 정의
public class TablePanel extends JPanel{
	private JTable table = null;
	private static MyTableModel tableModel = null;
	private JScrollPane jspane = null;
	
	
	public TablePanel(){
		this.compose();
		this.setevent();
	}
	class MyMouseAdapter extends MouseAdapter{

		@Override
		public void mouseClicked(MouseEvent event) {
			
			Object who = event.getSource();
			if(who == table){
				//System.out.println("호호");
				int row = table.getSelectedRow();
				if(row == -1){
					return ;
				}
				int colsu = table.getColumnCount();
				TableModel model = table.getModel();
				//System.out.println(model.getValueAt(row,1));
				Student student = new Student(model.getValueAt(row,0).toString(),model.getValueAt(row,1).toString(),Integer.parseInt(model.getValueAt(row,2).toString()),Integer.parseInt(model.getValueAt(row,3).toString()),Integer.parseInt(model.getValueAt(row,4).toString()));
				//System.out.println(student);
				TabbedPanel.FillData(student);
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			
			int which =  e.getButton();
			
			//System.out.println(which);
			
			switch(which){
			case MouseEvent.BUTTON1://왼쪽 버튼
				//System.out.println("왼쪽 버튼");
				break;
			case MouseEvent.BUTTON2://마우스 휠
				//System.out.println("마우스 휠");
				break;
			case MouseEvent.BUTTON3://오른쪽 버튼
				//System.out.println("오른쪽 버튼");
				int ret = JOptionPane.showConfirmDialog(null, "선택하신 행을 삭제하시겠습니까?","삭제 대화 상자",JOptionPane.OK_CANCEL_OPTION);
				System.out.println(ret);
				switch(ret){
				case 0:
					int row = table.getSelectedRow();
					if(row == -1){return;}
					TableModel model = table.getModel();
					String id = model.getValueAt(row, 0).toString();
					tableModel.DeleteStudent(id);
					break;
				case 2:
					JOptionPane.showMessageDialog(null, "삭제를 취소하였습니다.","삭제취소",JOptionPane.CANCEL_OPTION);
					break;
				}
				break;
			}
		}
		
		
	}
	private void setevent(){
		this.table.addMouseListener(new MyMouseAdapter());
	}
	private void compose(){
		//super.setBackground(Color.BLUE);
		super.setLayout(new BorderLayout());
		this.tableModel = new MyTableModel();
		this.table = new JTable(this.tableModel);
		this.table.setRowHeight(21);
		
		MyTableCellRenderer renderer = new MyTableCellRenderer();
		this.table.setDefaultRenderer(table.getColumnClass(0), renderer);
		
		this.jspane=new JScrollPane(this.table);
		this.jspane.setPreferredSize(new Dimension(410,420));
		super.add(jspane,BorderLayout.CENTER);
	}
	public static int InsertStudent(Student student) {
		//JTabbedPanel에 넘어온 학생 정보를 인서트 해주는 메소드
		System.out.println("JTable에 인서트하세요");
		System.out.println(student.toString());
		return tableModel.InsertStudent(student);
	}
	public static int UpdateStudent(Student student) {
		//JTabbedPanel에 넘어온 학생 정보를 수정 해주는 메소드
		System.out.println("JTable에 수정하세요");
		System.out.println(student.toString());
		
		if(tableModel == null){
			System.out.println("널이야");
		}else{
			System.out.println("낫널이야");
		}
		return tableModel.UpdateStudent(student);
	}
}
