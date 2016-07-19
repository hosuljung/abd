package mypkg.mydb;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
//좌측에 놓이는 사용자 정의 JTable 컴포넌트
public class TablePanel extends JPanel{
	private static MyTableModel tableModel = null ;
	private JTable table = null ;
	private JScrollPane jspane = null ;
	
	private void compose() {
		super.setLayout( new BorderLayout() ); 
		this.tableModel = new MyTableModel();		
		this.table = new JTable( this.tableModel ) ;
		this.table.setRowHeight( 21 );
		
		MyTableCellRenderer renderer = new MyTableCellRenderer() ;
		this.table.setDefaultRenderer(table.getColumnClass(0), renderer);
		this.jspane = new JScrollPane( this.table )  ;
		this.jspane.setPreferredSize( new Dimension(410, 420));
		super.add( jspane, BorderLayout.CENTER ) ;		
	}
	public TablePanel() {
		this.compose();
		this.setevent();
	}
	
	class MyMouseAdapter extends MouseAdapter{
		@Override
		public void mouseClicked(MouseEvent event) {
			//System.out.println("mouseClicked");
			Object who = event.getSource();
			if (who == table) {
				//System.out.println("호호");
				int row = table.getSelectedRow() ;
				if( row == -1 ){ return ; } 
				int colsu = table.getColumnCount() ; 
				//System.out.println( row + "/" + colsu);			
				TableModel model = table.getModel() ; 
				//System.out.println( model.getValueAt(row, 1));
				Student student 
				= new Student(
						model.getValueAt(row, 0).toString(), 
						model.getValueAt(row, 1).toString(),
						Integer.parseInt( model.getValueAt(row, 2).toString() ),
						Integer.parseInt( model.getValueAt(row, 3).toString() ), 
						Integer.parseInt( model.getValueAt(row, 4).toString() )) ;      
				//System.out.println( student );
				TabbedPanel.FillData( student ) ; 
			}
		} 
		@Override
		public void mousePressed(MouseEvent event) {
			//System.out.println("mousePressed");
			//Object who = event.getSource();
			int which = event.getButton() ;
			//System.out.println( which );
			switch (which) {
			case MouseEvent.BUTTON1 :  
				//System.out.println("왼쪽 버튼");				
				break;
			case MouseEvent.BUTTON2: 
				//System.out.println("마우스 휠");
				break;
			case MouseEvent.BUTTON3: 
				//System.out.println("오른쪽 버튼");
				int ret = JOptionPane.showConfirmDialog(
						null, 
						"선택하신 행을 삭제하시겠습니까?", 
						"삭제 대화 상자", 
						JOptionPane.OK_CANCEL_OPTION);
				switch (ret) {
				case 0:
					int row = table.getSelectedRow() ;
					if( row == -1 ){
						System.out.println("선택 좀 하시지 말입니다.");
						return ; 
					} 
					TableModel model = table.getModel() ; 
					String id = model.getValueAt(row, 0).toString() ;
					tableModel.DeleteStudent(id);
					break;
				case 2:
					JOptionPane.showMessageDialog(
							null, 
							"삭제를 취소하였습니다.", 
							"삭제 취소", 
							JOptionPane.CANCEL_OPTION); 
					
					break;
				} 
				break;
			}
		}
	}

	private void setevent() {
		this.table.addMouseListener( new MyMouseAdapter() );
	}
	public static int InsertStudent(Student student) {
		//JTabbedPanel에 넘어온 학생 정보를 인서트 해주는 메소드
		//System.out.println( "JTable에 인서트하세요.");
		//System.out.println( student.toString() );		
		return tableModel.InsertStudent( student ) ;
	}
	public static int UpdateStudent(Student student) {
		//JTabbedPanel에 넘어온 학생 정보를 수정해주는 메소드
		//System.out.println( "JTable에 수정하세요.");
		//System.out.println( student.toString() );
		return tableModel.UpdateStudent( student ) ;
	}	
	  
}









