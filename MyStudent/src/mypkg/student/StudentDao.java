package mypkg.student;

import java.sql.PreparedStatement;
import java.util.Vector;

import javax.swing.JFrame;

public class StudentDao extends JFrame {
	//�л� ���� ���� ������ ��� �ִ� �÷���(���Ŀ� DB�� �̰� ����)
	Vector<Student> students = null;
	Vector<String> columnList = null;
	
	public StudentDao(){
		students = new Vector<Student>();
		students.add(new Student("kim","��ö��",30,40,50));
		students.add(new Student("park","�ڿ���",60,70,80));
		students.add(new Student("hong","ȫ�浿",50,60,70));
		
		columnList = new Vector<String>();
		columnList.add("���̵�");
		columnList.add("�̸�");
		columnList.add("����");
		columnList.add("����");
		columnList.add("����");
		columnList.add("����");
		columnList.add("���");
		
	}
	public int DeleteStdent(String id){
		//�л� 1���� ������ �÷��ǿ� �����Ѵ�.
		for(Student mystudent:students){
			if(id.equals(mystudent.getId())){
				this.students.remove(mystudent);
				break;
			}
		}
		return 1;
	}
	
	public int UpdateStdent(Student student){
		//�л� 1���� ������ �÷��ǿ� �����Ѵ�.
		//Ȯ�� for�� �̿��Ͽ� ��Ҹ� ã�Ƽ� ���������� �����Ѵ�.(id����)

		for(Student mystudent:students){
			//������ id�� ���� ����� id�� ���Ͽ� �����ϸ�
			if(mystudent.getId().equals(student.getId())){
				//id�� ������ ������ ��δ� �����ϼ���.
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
		
		//�л��Ѹ��� ������ �÷��ǿ� �߰��Ѵ�.
		//��, ������ id�� ������ ����
		//������ ���̽��� 1���� �����͸� �߰��Ѵ�.
		return 1;	
	}
	
	
    public Vector<Student> GetStudentList(){
		
		//��� �л����� ������ �÷������� �����Ѵ�.
		PreparedStatement pstmt = null;
		String sql = "";
			
		
		return this.students;
	}
	public Vector<String> GetColumnName() {
		
		return this.columnList;
	}
	
	
}
