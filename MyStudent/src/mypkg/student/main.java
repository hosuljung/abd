package mypkg.student;

import java.io.FileInputStream;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class main {
 static String text[]= new String[100],korean[] = new String[100], input[] = new String[100]; //��� ���� �ܾ �����ϴ� ����
 static int count=0; //�ܾ��� ������ ���� ����
 static boolean[] save = new boolean[100]; //������ �ܾ ���� ����
 static boolean test[]; //Ʋ�� �ܾ ���� ����
 static int gotword; //������ �ܾ��� ��ȣ�� �ӽ������� ����
 static int score; //Ʋ�� ������ ���� ����
 
 
 public static void main(String[] args){
  
  try{//���� ���⸦ �õ��Ѵ�
   FileInputStream ip = new FileInputStream("c:\\study\\MyStudent\\src\\mypkg\\student\\word.txt"); //txt���� ���� �ٸ���ΰ� �������� ������ �ҽ������� �ִ°��� ����
   
   Scanner s = new Scanner(ip); //txt ���� �о���� ��ĳ�� ����
   
   
   
   while(true){
    if(s.hasNext()){    // hasNext()�� ���� ��Ұ� �����ִ��� Ȯ���� ���������� true�� ��ȯ��
     text[count] = s.next();
     korean[count] = s.next();
     count+=1;
     
     //��Ҹ� �о�ͼ� ���� //next()�� �Ѵܾ �о����, nextLine()�� �� ������ �о�´�.
    }else{
     break;
    }
   }
    
   
   }catch(Exception e){//������ ��Ƴ��� ex)������ ������
    e.printStackTrace();
  }
  save = new boolean[count];
  test = new boolean[count];
  
  for(int j =0;j <count;j++){ //������ �����Ѵ�
   random(); //������ �ܾ �̾Ƴ���,
            input[gotword] = JOptionPane.showInputDialog(j+1+": "+korean[gotword]+"             Ʋ��Ƚ�� : "+score);//������ �����Ѵ�
            if(!(text[gotword].equalsIgnoreCase(input[gotword]))){ //���� �Է¹��� �ܾ�� ������ ������ ���� ������,
             test[gotword] = true; //������ Ʋ�ȴٰ� ǥ���Ѵ�
             score+=1; //Ʋ�� ������ �߰��Ѵ�
            }
  }
  
  for(int j=0; j<count; j++){ //Ʋ�� �ܾ Ȯ���ϴ� for ��
   if(test[j]){ //���� �ܾ Ʋ�ȴٸ�
    for(int i =0; i<5;i++) //�ټ�����
    JOptionPane.showInputDialog(korean[j]+" : "+text[j]); //Ʋ���ܾ ������Ų��
   }
  }
 }
 
 
 static void random(){
  int got = (int)(Math.random()*count+0); //�����ϰ� �ܾ �̾Ƴ���.
  if(save[got]){//���� �̹� ������ ��ȣ���
   random(); //�ٽ� ��ȣ�� �̾Ƴ���
  }else{//������ �ܾ �ƴ϶��
   save[got]=true;//������ ��ٰ� ǥ�ø� �ϰ�,
   gotword = got;//������ �ܾ �ӽ������Ѵ�.
  }
  return;
 }
}
