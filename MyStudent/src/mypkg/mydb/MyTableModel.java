package mypkg.mydb;

import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
//JTable�� ���� ���̺� �� Ŭ����
public class MyTableModel extends AbstractTableModel {
	private StudentDao dao = null ;
	//�÷� ������ ��� �ִ� �÷���
	private Vector<String> columnNames ; 
	//������ ������ ��� �ִ� �÷���
	private Vector<Student> students ;
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if (columnIndex == 2 || columnIndex == 3 || columnIndex == 4 ) {
			return true ;
		} else {
			return false  ;
		}		
	}
	
	@Override
	public String getColumnName(int column) {	
		//�� �������̵��� �÷��� A, B, C�� �������� ��������.
		//�Ϲ������� 1���� �迭 �Ǵ� �÷��� ���·� �÷� �̸��� ������ �ΰ�,
		//�� �޼ҵ带 �������̵��Ͽ� �����Ѵ�.
		return this.columnNames.get(column) ;
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		//�� ���� � ���� ��µǾ�� �ϴ� ���� �˷� �ִ� ����
		Student student = this.students.get( rowIndex ) ;
		switch (columnIndex) {
		case 0 : //���̵�
			return student.getId();
		case 1 : //�̸� 
			return student.getName();
		case 2 : //����
			return student.getKor();
		case 3 :  //����
			return student.getEng();
		case 4 : //����
			return student.getMath();
		case 5 : //����
			return student.getTotal();
		case 6 : //���
			return student.getAverage() ;
		}
		return null;
	}	
	
	public MyTableModel() {
		this.dao = new StudentDao() ; 
		this.columnNames = dao.GetColumnNameList() ;
		this.students = dao.GetStudentList() ; 
	}
	@Override
	public int getColumnCount() {
		//����� �� Table�� ���� �� �������� �˷� �ִ� �����̴�.
		return this.columnNames.size() ;
	}
	@Override
	public int getRowCount() {
		//����� �� Table�� ���� �� �������� �˷� �ִ� �����̴�.
		return this.students.size() ;
	}
	public int DeleteStudent( String id ){
		this.dao.DeleteStudent( id ) ;
		super.fireTableRowsDeleted(0, 0);
		return 1 ;			
	}	
	public int UpdateStudent( Student student ){
		this.dao.UpdateStudent(student) ;		 
		super.fireTableRowsUpdated(0, this.getRowCount() - 1);
		return 1 ;		
	}	
	public int InsertStudent( Student student ){
		int cnt = MyInterface.ERROR_DEFAULT;
		cnt = this.dao.InsertStudent(student) ;
		// ���̺� �� ������ ���̺��� �뺸
		//fireTableRowsInserted(������, ����)
		if(cnt == -1){
			String message = "���Ἲ ���� ���� ����";
			System.out.println("���Ἲ ���� ���� ����");
			JOptionPane.showMessageDialog(null, message);
			cnt = MyInterface.ERROR_DEFAULT;
		}else if(cnt !=MyInterface.ERROR_DEFAULT){
			System.out.println("�߰���");
			this.students.clear();
			this.students = dao.GetStudentList();
			super.fireTableRowsInserted(0, this.getRowCount() - 1);
		}
		return cnt ;
	}	
	public Vector<Student> GetStudentList(){
		return null ;		
	}		
}