package mypkg.student;

import java.sql.PreparedStatement;
import java.util.Vector;

import javax.swing.JFrame;

public class StudentDao extends JFrame {
	//학생 여러 명의 정보를 담고 있는 컬렉션(차후에 DB로 이관 예정)
	Vector<Student> students = null;
	Vector<String> columnList = null;
	
	public StudentDao(){
		students = new Vector<Student>();
		students.add(new Student("kim","김철수",30,40,50));
		students.add(new Student("park","박영희",60,70,80));
		students.add(new Student("hong","홍길동",50,60,70));
		
		columnList = new Vector<String>();
		columnList.add("아이디");
		columnList.add("이름");
		columnList.add("국어");
		columnList.add("영어");
		columnList.add("수학");
		columnList.add("총점");
		columnList.add("평균");
		
	}
	public int DeleteStdent(String id){
		//학생 1명의 정보를 컬렉션에 수정한다.
		for(Student mystudent:students){
			if(id.equals(mystudent.getId())){
				this.students.remove(mystudent);
				break;
			}
		}
		return 1;
	}
	
	public int UpdateStdent(Student student){
		//학생 1명의 정보를 컬렉션에 수정한다.
		//확장 for를 이용하여 요소를 찾아서 개별적으로 수정한다.(id제외)

		for(Student mystudent:students){
			//수정할 id와 낱개 요소의 id를 비교하여 동일하면
			if(mystudent.getId().equals(student.getId())){
				//id를 제외한 나머지 모두다 수정하세요.
				mystudent.setEng(student.getEng());
				mystudent.setKor(student.getKor());
				mystudent.setMath(student.getMath());
				mystudent.setName(student.getName());
				mystudent.reCalculte();
				break;
			}else{
				
			}
		}
		
		
		return 1;
	}

	public int InsertStudent(Student student){
		
		//학생한명의 정보를 컬렉션에 추가한다.
		//단, 동일한 id가 들어오면 차단
		//데이터 베이스에 1건의 데이터를 추가한다.
		return 1;	
	}
	
	
    public Vector<Student> GetStudentList(){
		
		//모든 학생들의 정보를 컬렉션으로 리컨한다.
		PreparedStatement pstmt = null;
		String sql = "";
			
		
		return this.students;
	}
	public Vector<String> GetColumnName() {
		
		return this.columnList;
	}
	
	
}
