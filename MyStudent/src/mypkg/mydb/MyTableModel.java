package mypkg.mydb;

import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
//JTable을 위한 테이블 모델 클래스
public class MyTableModel extends AbstractTableModel {
	private StudentDao dao = null ;
	//컬럼 정보를 담고 있는 컬렉션
	private Vector<String> columnNames ; 
	//데이터 정보를 담고 있는 컬렉션
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
		//미 오버라이딩시 컬럼이 A, B, C의 형식으로 보여진다.
		//일반적으로 1차원 배열 또는 컬렉션 형태로 컬럼 이름을 정의해 두고,
		//이 메소드를 오버라이딩하여 구현한다.
		return this.columnNames.get(column) ;
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		//각 셀에 어떤 값이 출력되어야 하는 지를 알려 주는 역할
		Student student = this.students.get( rowIndex ) ;
		switch (columnIndex) {
		case 0 : //아이디
			return student.getId();
		case 1 : //이름 
			return student.getName();
		case 2 : //국어
			return student.getKor();
		case 3 :  //영어
			return student.getEng();
		case 4 : //수학
			return student.getMath();
		case 5 : //총점
			return student.getTotal();
		case 6 : //평균
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
		//출력이 될 Table의 열이 몇 개인지를 알려 주는 역할이다.
		return this.columnNames.size() ;
	}
	@Override
	public int getRowCount() {
		//출력이 될 Table의 행이 몇 개인지를 알려 주는 역할이다.
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
		// 테이블 행 삽입을 테이블에게 통보
		//fireTableRowsInserted(시작행, 끝행)
		if(cnt == -1){
			String message = "무결성 제약 조건 위배";
			System.out.println("무결성 제약 조건 위배");
			JOptionPane.showMessageDialog(null, message);
			cnt = MyInterface.ERROR_DEFAULT;
		}else if(cnt !=MyInterface.ERROR_DEFAULT){
			System.out.println("추가됨");
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