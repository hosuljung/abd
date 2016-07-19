package mypkg.mydb;

import java.awt.Color;
import java.awt.Component;
import java.text.DecimalFormat;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;

//TableCellRenderer : ���� ������ ȭ�鿡 ��Ÿ���� ���� �������̽�
//DefaultTableCellRenderer Ŭ���� : TableCellRenderer�� ���� ������ ���� Ŭ����
class MyTableCellRenderer extends DefaultTableCellRenderer {
	private static final long serialVersionUID = 1L;
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {

		super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
				row, column);
		if (column == 0 || column == 1) { // ���ڿ��� ��� ����
			setHorizontalAlignment(SwingConstants.CENTER);
		} else { // ���ڴ� ���� �� ����
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