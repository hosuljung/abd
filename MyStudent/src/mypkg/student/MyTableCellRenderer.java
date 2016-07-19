package mypkg.student;

import java.awt.Color;
import java.awt.Component;
import java.text.DecimalFormat;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;

public class MyTableCellRenderer extends DefaultTableCellRenderer {
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
	
	public void setBackground(Color c){
		super.setBackground( Color.YELLOW );
	}	
	public void setForeground(Color c){
		super.setForeground( Color.BLUE);
	}

	public void setBorder(Border border){
		super.setBorder(new BevelBorder(BevelBorder.RAISED) );
	}	
}