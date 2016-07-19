package mypkg.mydb;

import java.awt.Color;
import java.awt.Component;
import java.text.DecimalFormat;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;

//TableCellRenderer : 셀의 내용을 화면에 나타내기 위한 인터페이스
//DefaultTableCellRenderer 클래스 : TableCellRenderer를 완전 구현해 놓은 클래스
class MyTableCellRenderer extends DefaultTableCellRenderer {
	private static final long serialVersionUID = 1L;
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {

		super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
				row, column);
		if (column == 0 || column == 1) { // 문자열은 가운데 정렬
			setHorizontalAlignment(SwingConstants.CENTER);
		} else { // 숫자는 오른 쪽 정렬
			setHorizontalAlignment(SwingConstants.RIGHT);
			if (column == 5 || column == 6) {
				super.setText( new DecimalFormat("###.00").format( value ) );
			}
		}
		return this;
	}
	@Override
	public void setBackground(Color c){
		super.setBackground( Color.YELLOW );
	}
	@Override
	public void setForeground(Color c){
		super.setForeground( Color.BLUE);
	}
	@Override
	public void setBorder(Border border){
		super.setBorder(new BevelBorder(BevelBorder.RAISED) );
	}	
}