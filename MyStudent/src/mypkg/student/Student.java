package mypkg.student;


//데이터 1건을 의미하는 자바 클래스
public class Student {
	private String id;
	private String name;
	private int kor,eng,math;
	
	//아래의 항목들은 실제 데이터베이스에 컬럼이 없다.
	private int total;
	private double average;
	
	
	
	//public Student(){}
	public Student(String id, String name, int kor, int eng, int math) {
		this.id = id;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
		
		this.reCalculte();
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", kor=" + kor
				+ ", eng=" + eng + ", math=" + math + ", total=" + total
				+ ", average=" + average + "]";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getKor() {
		return kor;
	}
	public void setKor(int kor) {
		this.kor = kor;
	}
	public int getEng() {
		return eng;
	}
	public void setEng(int eng) {
		this.eng = eng;
	}
	public int getMath() {
		return math;
	}
	public void setMath(int math) {
		this.math = math;
	}
	public int getTotal() {
		return total;
	}
	public double getAverage() {
		return average;
	}
	public void reCalculte() {
		//생성자 호출 시점과 외부에서 시험 점수 변경 사항이 발생하면 호출됨
		this.total = this.kor+this.eng+this.math;
		this.average = (double)this.total/3;
	}
	
	
}
