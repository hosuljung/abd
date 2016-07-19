package mypkg.mydb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;
//Dao(Data Access Object) Ŭ����
//������ ���̽��� ������ �Ͽ� �����͸� ó���ϱ� ���� �ڹ� Ŭ����
//�� �ϱ� ���� ���� ����
//�ڹ��� ���� ó��, �޼ҵ��� ����, �÷���(ArrayList, Vector)�� ���� ����, 
//JDBC ���α׷��ֿ� ���� ���ذ� �ʿ��ϴ�.

public class StudentDao {
	// �л� ���� ���� ������ ��� �ִ� �÷���(���Ŀ� DB�� �̰� ����)
	Vector<Student> students = null;
	Vector<String> columnList = null;
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "oraman";
	private String password = "oracle";
	private Connection conn = null;

	private String driver = "oracle.jdbc.driver.OracleDriver";

	private Connection getConnection() {
		try {
			return DriverManager.getConnection(url, id, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public Vector<String> GetColumnNameList() {
		// �÷��� ������ �����Ѵ�.
		return this.columnList;
	}

	public StudentDao() {
		students = new Vector<Student>();
		// students.add( new Student("kim", "��ö��", 30, 40, 50)) ;
		// students.add( new Student("park", "�ڿ���", 60, 70, 80)) ;
		// students.add( new Student("hong", "ȫ�浿", 50, 60, 70)) ;

		try {
			Class.forName(driver);
			this.conn = getConnection();
			if (conn != null) {
				System.out.println("�ѽ� ����");

			} else {
				System.out.println("");
			}
		} catch (ClassNotFoundException e) {
			System.out.println("��Ÿ �Ǵ� jar ���� ��ġ Ȯ�� ���");
			e.printStackTrace();
		}
		columnList = new Vector<String>();
		columnList.add("���̵�");
		columnList.add("�̸�");
		columnList.add("����");
		columnList.add("����");
		columnList.add("����");
		columnList.add("����");
		columnList.add("���");
	}

	public int DeleteStudent(String id) {
		// �л� 1���� ������ �÷��ǿ� �����Ѵ�.
		for (Student mystudent : students) {
			if (id.equals(mystudent.getId())) {
				this.students.remove(mystudent);
				break;
			}
		}
		return 1;
	}

	public int UpdateStudent(Student student) {
		// �л� 1���� ������ �÷��ǿ� �����Ѵ�.
		// Ȯ�� for�� �̿��Ͽ� ��Ҹ� ã�Ƽ� ���������� �����Ѵ�.(id ����)
		for (Student mystudent : students) {
			// ������ id�� ���� ����� id�� ���Ͽ� �����ϸ�
			if (student.getId().equals(mystudent.getId())) {
				// id�� ������ ������ ��� �����ϼ���.
				mystudent.setName(student.getName());
				mystudent.setKor(student.getKor());
				mystudent.setEng(student.getEng());
				mystudent.setMath(student.getMath());
				mystudent.reCalculate();
				break;
			}
		}
		return 1;
	}

	public int InsertStudent(Student student) {
		// �л� 1���� ������ �÷��ǿ� �߰��Ѵ�.
		// ��, ������ id�� ������ ����
		//?(place holder): ġȯ�Ǿ�� �� � ��
		PreparedStatement pstmt = null;
		String sql = "insert into student(id,name,kor,eng,math)";
		sql+=" values(?,?,?,?,?)";
		int cnt = MyInterface.ERROR_DEFAULT;
		try {
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, student.getId());
			pstmt.setString(2, student.getName());
			pstmt.setInt(3, student.getKor());
			pstmt.setInt(4, student.getEng());
			pstmt.setInt(5, student.getMath());
			cnt = pstmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			int errCode = e.getErrorCode();
			System.out.println("�����ڵ�:"+errCode);
			cnt = -errCode;
			
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return cnt;
	}

	public Vector<Student> GetStudentList() {
		PreparedStatement pstmt = null;
		String sql = "select*from student order by name";
		//Vector<Student> students = new Vector<Student>();
		// ��� �л����� ������ �÷������� �����Ѵ�.
		ResultSet rs = null;
		// ������� ���� �ڷᰡ ���⿡ ����� ����
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			// �ٱ����� �ϴ� ���� �� ���� �л����� ���´�
			while (rs.next()) {
				Student student = new Student();
				student.setId(rs.getString("id"));
				student.setName(rs.getString("name"));
				student.setKor(rs.getInt("kor"));
				student.setEng(rs.getInt("eng"));
				student.setMath(rs.getInt("math"));

				student.reCalculate();
				this.students.add(student);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return this.students;
	}
}