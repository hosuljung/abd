package mypkg.mydb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;
//Dao(Data Access Object) 클래스
//데이터 베이스에 접속을 하여 데이터를 처리하기 위한 자바 클래스
//잘 하기 위한 전제 조건
//자바의 예외 처리, 메소드의 사용법, 컬렉션(ArrayList, Vector)에 대한 이해, 
//JDBC 프로그래밍에 대한 이해가 필요하다.

public class StudentDao {
	// 학생 여러 명의 정보를 담고 있는 컬렉션(차후에 DB로 이관 예정)
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
		// 컬럼의 정보를 리턴한다.
		return this.columnList;
	}

	public StudentDao() {
		students = new Vector<Student>();
		// students.add( new Student("kim", "김철수", 30, 40, 50)) ;
		// students.add( new Student("park", "박영희", 60, 70, 80)) ;
		// students.add( new Student("hong", "홍길동", 50, 60, 70)) ;

		try {
			Class.forName(driver);
			this.conn = getConnection();
			if (conn != null) {
				System.out.println("앗싸 성공");

			} else {
				System.out.println("");
			}
		} catch (ClassNotFoundException e) {
			System.out.println("오타 또는 jar 파일 위치 확인 요망");
			e.printStackTrace();
		}
		columnList = new Vector<String>();
		columnList.add("아이디");
		columnList.add("이름");
		columnList.add("국어");
		columnList.add("영여");
		columnList.add("수학");
		columnList.add("총점");
		columnList.add("평균");
	}

	public int DeleteStudent(String id) {
		// 학생 1명의 정보를 컬렉션에 삭제한다.
		for (Student mystudent : students) {
			if (id.equals(mystudent.getId())) {
				this.students.remove(mystudent);
				break;
			}
		}
		return 1;
	}

	public int UpdateStudent(Student student) {
		// 학생 1명의 정보를 컬렉션에 수정한다.
		// 확장 for를 이용하여 요소를 찾아서 개별적으로 수정한다.(id 제외)
		for (Student mystudent : students) {
			// 수정할 id와 낱개 요소의 id를 비교하여 동일하면
			if (student.getId().equals(mystudent.getId())) {
				// id를 제외한 나머지 모다 수정하세요.
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
		// 학생 1명의 정보를 컬렉션에 추가한다.
		// 단, 동일한 id가 들어오면 차단
		//?(place holder): 치환되어야 할 어떤 값
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
			System.out.println("에러코드:"+errCode);
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
		// 모든 학생들의 정보를 컬렉션으로 리턴한다.
		ResultSet rs = null;
		// 결과값에 대한 자료가 여기에 담겨져 있음
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			// 바깥에서 하는 경우는 다 같은 학생으로 나온다
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