package mypkg.mydb;
//Bean Ŭ���� : ������ 1���� �ǹ��ϴ� �ڹ� Ŭ����
public class Student {
	private String id ;
	private String name ;
	private int kor ; 
	private int eng ;
	private int math ;
	
	//�Ʒ��� �׸���� ���� �����ͺ��̽��� �÷��� ����.
	private int total ;//����
	private double average  ; //���
	
	public Student() { }
	
	public void reCalculate() {	
		//������ ȣ�� ������ �ܺο��� ���� ���� ���� ������ �߻��ϸ� ȣ���
		this.total = this.kor + this.eng+ this.math ;
		this.average = (double)this.total / 3 ; 
	}	
	public Student(String id, String name, int kor, int eng, int math) {		
		this.id = id;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;		
		this.reCalculate(); 
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
//	public void setTotal(int total) {
//		this.total = total;
//	}
	public double getAverage() {
		return average;
	}
//	public void setAverage(double average) {
//		this.average = average;
//	}

	
	
	
}
