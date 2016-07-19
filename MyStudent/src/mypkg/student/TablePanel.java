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

//������ ���̴� ����� ����
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
				//System.out.println("ȣȣ");
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
			case MouseEvent.BUTTON1://���� ��ư
				//System.out.println("���� ��ư");
				break;
			case MouseEvent.BUTTON2://���콺 ��
				//System.out.println("���콺 ��");
				break;
			case MouseEvent.BUTTON3://������ ��ư
				//System.out.println("������ ��ư");
				int ret = JOptionPane.showConfirmDialog(null, "�����Ͻ� ���� �����Ͻðڽ��ϱ�?","���� ��ȭ ����",JOptionPane.OK_CANCEL_OPTION);
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
					JOptionPane.showMessageDialog(null, "������ ����Ͽ����ϴ�.","�������",JOptionPane.CANCEL_OPTION);
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
		//JTabbedPanel�� �Ѿ�� �л� ������ �μ�Ʈ ���ִ� �޼ҵ�
		System.out.println("JTable�� �μ�Ʈ�ϼ���");
		System.out.println(student.toString());
		return tableModel.InsertStudent(student);
	}
	public static int UpdateStudent(Student student) {
		//JTabbedPanel�� �Ѿ�� �л� ������ ���� ���ִ� �޼ҵ�
		System.out.println("JTable�� �����ϼ���");
		System.out.println(student.toString());
		
		if(tableModel == null){
			System.out.println("���̾�");
		}else{
			System.out.println("�����̾�");
		}
		return tableModel.UpdateStudent(student);
	}
}
