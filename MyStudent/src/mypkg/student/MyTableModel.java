package mypkg.student;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class MyTableModel extends AbstractTableModel{

	private StudentDao dao = null;
	private Vector<String> columnNames;
	private Vector<Student> student;
	
	@Override
	public String getColumnName(int column) {
		//미 오버라이딩시 컬럼이 A,B,C,의 형식으로 보여진다.
		//일반적으로 1차원 배열 또는 컬렉션 형태로 컬럼 이름을 정의해 두고,
		//이 메소드를 오버라이딩하여 구현한다.
		return this.columnNames.get(column);
	}
	public MyTableModel(){
		dao = new StudentDao();
		this.columnNames = dao.GetColumnName();
		this.student = dao.GetStudentList();
	}
	
	@Override
	public int getRowCount() {
		//출력이 될 Table의 행이 몇 개인지를 알려 주는 역할이다.
		return this.student.size();
	}

	@Override
	public int getColumnCount() {
		//출력이 될 Table의 열이 몇 개인지를 알려주는 역할이다.
		return this.columnNames.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		//각 셀에 어떤 값이 출력되여야 하는 지를 알려주는 역할이다.
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
		//테이블 행 삽입을 테이블에게 통보
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
