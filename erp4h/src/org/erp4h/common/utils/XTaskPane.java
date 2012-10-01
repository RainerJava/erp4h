package org.erp4h.common.utils;

import java.util.ArrayList;

import javax.swing.JButton;

import org.erp4h.bll.PhanHeBLL;
import org.erp4h.dto.PhanHeDTO;
import org.jdesktop.swingx.JXTaskPane;

public class XTaskPane extends JXTaskPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public XTaskPane() throws Exception {
		setAutoscrolls(true);
		ArrayList<PhanHeDTO> arrPhanHe=new PhanHeBLL().getArrPhanHe(null, null);
		
		for(int i=0;i<arrPhanHe.size();i++){
			JButton lph=new JButton(arrPhanHe.get(i).toString());
			add(lph);
		}
	}
}
