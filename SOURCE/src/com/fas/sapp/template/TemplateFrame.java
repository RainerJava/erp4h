package com.fas.sapp.template;

import java.awt.Rectangle;

import javax.swing.BorderFactory;

import com.fas.common.constants.screen.FaceContants;
import com.fas.common.utils.StringUtils;
import com.fas.jface.bussiness.BaseMasterFrame;
import com.fas.jface.gui.BaseFrame;
import com.fas.jface.gui.BasePanel;
import com.fas.jface.label.BaseLabel;

public class TemplateFrame extends BaseMasterFrame {

	/** */
	private static final long serialVersionUID = 1L;


	@Override
	protected String getHelpInfor() {
		// TODO Auto-generated method stub
		return "";
	}


	@Override
	protected String getSubName() {
		if (exeMenu != null) {
			return StringUtils.emptyIfNull(exeMenu.getMenuexeName());
		} else {
			return "";
		}
	}


	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public TemplateFrame(BaseFrame frame, String title) {
        super(frame, title);
	}
	
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public TemplateFrame(BaseFrame frame) {
        super(frame);

	}
	
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public TemplateFrame() {
        super();
	}
	
	@Override
	protected void doF1() {
		// TODO Auto-generated method stub
		
	}


	@Override
	protected void doF10() {
		// TODO Auto-generated method stub
		
	}


	@Override
	protected void doF11() {
		// TODO Auto-generated method stub
		
	}


	@Override
	protected void doF2() {
		// TODO Auto-generated method stub
		
	}


	@Override
	protected void doF3() {
		// TODO Auto-generated method stub
		
	}


	@Override
	protected void doF4() {
		// TODO Auto-generated method stub
		
	}


	@Override
	protected void doF5() {
		// TODO Auto-generated method stub
		
	}


	@Override
	protected void doF6() {
		// TODO Auto-generated method stub
		
	}


	@Override
	protected void doF7() {
		// TODO Auto-generated method stub
		
	}


	@Override
	protected void doF8() {
		// TODO Auto-generated method stub
		
	}


	@Override
	protected void doF9() {
		// TODO Auto-generated method stub
		
	}


	@Override
	protected boolean[] enableBtn() {
		boolean[] fBtn = {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true};
		fBtn[5] = false;
		fBtn[7] = false;
		fBtn[9] = false;
		return fBtn;
	}


	@Override
	protected void doFirst() {
		// TODO Auto-generated method stub
		
	}


	@Override
	protected void doLast() {
		// TODO Auto-generated method stub
		
	}


	@Override
	protected void doNext() {
		// TODO Auto-generated method stub
		
	}


	@Override
	protected void doPre() {
		// TODO Auto-generated method stub
		
	}


	@Override
	protected int getRBodyWidth() {
		return 400;
	}



	@Override
	protected void initHeader(BasePanel pnl) {
		BaseLabel lblInfor = new BaseLabel("登録");
		lblInfor.setBounds(new Rectangle(4, 4, 30, 23));
		lblInfor.setBorder(null);
		pnl.add(lblInfor);
	}


	@Override
	protected void initLeftBody(BasePanel pnl) {
		pnl.setBorder(BorderFactory.createTitledBorder(""));
	}


	@Override
	protected void initRightBody(BasePanel pnl) {
		pnl.setBorder(FaceContants.BEVEL_BORDER);
	}
	
 	public static void main(String[] args) {
 		
 		try {
 			
 		} catch (Exception e) {

 		}
    }


	@Override
	protected void doDoubleClick(int row) {
		// TODO Auto-generated method stub
		
	}


	@Override
	protected void doSingleClick(int row) {
		// TODO Auto-generated method stub
		
	}

}
