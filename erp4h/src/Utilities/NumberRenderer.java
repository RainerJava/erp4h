package Utilities;

import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.table.DefaultTableCellRenderer;
/**
 * @author hieulv
 * @since 14/08/2012
 * @Chức_năng dinh dang kieu so cho du lieu theo kieu VietNam
 */
public class NumberRenderer extends DefaultTableCellRenderer{
	private static final long serialVersionUID = 1L;
	
	public NumberRenderer(){
		super();
		setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
	}
	
	@Override
	public void setValue(Object value){
		if((value !=null)&&(value instanceof Number)){
			Number numberValue=(Number)value;
			NumberFormat formater=NumberFormat.getInstance(Locale.forLanguageTag("vi"));
			value=formater.format(numberValue.doubleValue());
		}
		super.setValue(value);
	}
}
