package mypkg.student;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class MyTableModel extends AbstractTableModel{

	private StudentDao dao = null;
	private Vector<String> columnNames;
	private Vector<Student> student;
	
	@Override
	public String getColumnName(int column) {
		//�� �������̵��� �÷��� A,B,C,�� �������� ��������.
		//�Ϲ������� 1���� �迭 �Ǵ� �÷��� ���·� �÷� �̸��� ������ �ΰ�,
		//�� �޼ҵ带 �������̵��Ͽ� �����Ѵ�.
		return this.columnNames.get(column);
	}
	public MyTableModel(){
		dao = new StudentDao();
		this.columnNames = dao.GetColumnName();
		this.student = dao.GetStudentList();
	}
	
	@Override
	public int getRowCount() {
		//����� �� Table�� ���� �� �������� �˷� �ִ� �����̴�.
		return this.student.size();
	}

	@Override
	public int getColumnCount() {
		//����� �� Table�� ���� �� �������� �˷��ִ� �����̴�.
		return this.columnNames.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		//�� ���� � ���� ��µǿ��� �ϴ� ���� �˷��ִ� �����̴�.
		Student student = this.student.get(rowIndex);
		switch(columnIndex){
		case 0:
			return student.getId();
		case 1:
			return student.getName();
		case 2:
			return student.getKor();
		case 3:
			return student.getEng();
		case 4:
			return student.getMath();
		case 5:
			return student.getTotal();
		case 6:
			return student.getAverage();			
	    
		}
		return null;
	}
	public int UpdateStudent(Student student) {
		this.dao.UpdateStdent(student);
		
		super.fireTableRowsUpdated(0, this.getRowCount()-1);
		
		return 1;
	}
	public int InsertStudent(Student student) {
		this.dao.InsertStudent(student);
		//���̺� �� ������ ���̺��� �뺸
		super.fireTableRowsInserted(0, this.getRowCount()-1);
		
		return 1;
	}
	public int DeleteStudent(String id) {
		this.dao.DeleteStdent(id);
		super.fireTableRowsDeleted(0,0);
		return 1;
	}
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if(columnIndex == 2 || columnIndex == 3 || columnIndex==4){
			return true;
		}else{
			return false;
		}
	}

}
