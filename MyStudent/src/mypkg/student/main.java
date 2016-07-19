package mypkg.student;

import java.io.FileInputStream;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class main {
 static String text[]= new String[100],korean[] = new String[100], input[] = new String[100]; //뜻과 영어 단어를 저장하는 변수
 static int count=0; //단어의 개수를 세는 변수
 static boolean[] save = new boolean[100]; //출제된 단어를 적을 변수
 static boolean test[]; //틀린 단어를 적을 변수
 static int gotword; //출제할 단어의 번호를 임시저장할 변수
 static int score; //틀린 개수를 세는 변수
 
 
 public static void main(String[] args){
  
  try{//파일 열기를 시도한다
   FileInputStream ip = new FileInputStream("c:\\study\\MyStudent\\src\\mypkg\\student\\word.txt"); //txt파일 오픈 다른경로가 지정되지 않을시 소스파일이 있는곳에 생성
   
   Scanner s = new Scanner(ip); //txt 파일 읽어오는 스캐너 생성
   
   
   
   while(true){
    if(s.hasNext()){    // hasNext()를 통해 요소가 남아있는지 확인함 남아있으면 true가 반환됨
     text[count] = s.next();
     korean[count] = s.next();
     count+=1;
     
     //요소를 읽어와서 저장 //next()는 한단어를 읽어오고, nextLine()은 한 문장을 읽어온다.
    }else{
     break;
    }
   }
    
   
   }catch(Exception e){//오류를 잡아낸다 ex)파일이 없을시
    e.printStackTrace();
  }
  save = new boolean[count];
  test = new boolean[count];
  
  for(int j =0;j <count;j++){ //문제를 출제한다
   random(); //출제할 단어를 뽑아내고,
            input[gotword] = JOptionPane.showInputDialog(j+1+": "+korean[gotword]+"             틀린횟수 : "+score);//문제를 출제한다
            if(!(text[gotword].equalsIgnoreCase(input[gotword]))){ //만약 입력받은 단어와 출제된 정답이 맞지 않으면,
             test[gotword] = true; //문제를 틀렸다고 표시한다
             score+=1; //틀린 개수를 추가한다
            }
  }
  
  for(int j=0; j<count; j++){ //틀린 단어를 확인하는 for 문
   if(test[j]){ //만약 단어가 틀렸다면
    for(int i =0; i<5;i++) //다섯번을
    JOptionPane.showInputDialog(korean[j]+" : "+text[j]); //틀린단어를 복습시킨다
   }
  }
 }
 
 
 static void random(){
  int got = (int)(Math.random()*count+0); //랜덤하게 단어를 뽑아낸다.
  if(save[got]){//만약 이미 출제된 번호라면
   random(); //다시 번호를 뽑아낸다
  }else{//출제된 단어가 아니라면
   save[got]=true;//출제가 됬다고 표시를 하고,
   gotword = got;//출제될 단어를 임시저장한다.
  }
  return;
 }
}
