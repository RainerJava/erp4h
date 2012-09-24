package com.fas.sapp.system.menu;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import org.apache.log4j.Logger;

import com.fas.common.constants.ApplicationConstants;
import com.fas.common.constants.dbtable.MessageConstants;
import com.fas.common.constants.screen.ColorConstants;
import com.fas.common.constants.screen.FaceContants;
import com.fas.common.exception.BizException;
import com.fas.common.utils.FileUtils;
import com.fas.common.utils.StringUtils;
import com.fas.jface.MessageBox;
import com.fas.jface.button.ActionButton;
import com.fas.jface.gui.BaseFrame;
import com.fas.jface.gui.BasePanel;
import com.fas.jface.gui.InspectDialog;
import com.fas.jface.label.BaseLabel;
import com.fas.jface.textarea.BaseTextArea;
import com.fas.service.system.moutctl.MOutCtlService;
import com.fas.service.system.moutctl.MOutCtlServiceImpl;
import com.fas.vo.outctl.OutCtlVo;

public class MyOutConfigFrame extends InspectDialog {

	/** */
	private static final long serialVersionUID = 1L;
	/** */
	static Logger logger = Logger.getLogger(MyOutConfigFrame.class);
	/** */
	private BaseTextArea txtOut1Path;
	/** */
	private BaseTextArea txtOut2Path;
	/** */
	private BaseTextArea txtOut3Path;
	/** */
	private BaseTextArea txtOut4Path;
	/** */
	private BaseTextArea txtOut5Path;
	/** */
	private ActionButton btnOut1Path;
	/** */
	private ActionButton btnOut2Path;
	/** */
	private ActionButton btnOut3Path;
	/** */
	private ActionButton btnOut4Path;
	/** */
	private ActionButton btnOut5Path;
	/** */
	protected ActionButton btnF8;
	/** */
	protected ActionButton btnF12;
	/** */
	protected MOutCtlService outCtlService;

	/**
	 * <DL>
	 * <DT>コンストラクター記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * </DL>
	 */
	public MyOutConfigFrame() {

		super();
		init();
	}

	/**
	 * <DL>
	 * <DT>コンストラクター記述：</DT>
	 * <DD></DD> <BR>
	 * 
	 * @param pFrame
	 * @param modal
	 *            </DL>
	 */
	public MyOutConfigFrame(BaseFrame pFrame, boolean modal) {
		super(pFrame, modal);
		init();
	}

	/**
	 * <DL>
	 * <DT>メソッド記述：</DT>
	 * <DD></DD>
	 * <BR>
	 * </DL>
	 */
	private void init() {

		setTitle("出力先設定");

		setResizable(false);
		// setIconImage(ImageConstants.TITLE.getImage());

		outCtlService = new MOutCtlServiceImpl();
		loadOutClt(ApplicationConstants.getLoginUser().getUserId());
	}

	private void loadOutClt(String userId) {
		try {
			OutCtlVo dataVo = outCtlService.getOutCtlVoByUserId(userId);
			if (dataVo != null) {
				txtOut1Path.setText(dataVo.getOut1Path());
				txtOut2Path.setText(dataVo.getOut2Path());
			}
		} catch (BizException ex) {
			MessageBox.message(this, MessageConstants.getMstMsgVo("C0000"));
			logger.error(ex.getMessage());
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pipe.jface.AbstractDialog#setFrameSize()
	 */
	protected void setFrameSize() {
		X_FRAME_LENGTH = 500;
		Y_FRAME_LENGTH = 380;
		X_BODY_LENGTH = X_FRAME_LENGTH;
		Y_BODY_LENGTH = Y_FRAME_LENGTH;
		X_HEADER_LENGTH = X_FRAME_LENGTH;
		Y_HEADER_LENGTH = 0;
		X_FOOTER_LENGTH = X_FRAME_LENGTH;
		Y_FOOTER_LENGTH = 0;
	}

	@Override
	protected BasePanel getBodyPanel() {
		int xPos = 7;
		int yPos = 7;
		int space = 4;

		int iBtnWith = 70;
		int iBtnHeight = 50;

		int iBodyWith = X_BODY_LENGTH - xPos;
		int iTextAreaWith = iBodyWith - iBtnWith - 5;

		mainPnl = new BasePanel();

		int iTitleHeight = 50;
		BaseLabel lblTitle = new BaseLabel("Excel, PDFの出力デフォルト先を指定します。");
		lblTitle.setBounds(new Rectangle(xPos, yPos, iBodyWith, iTitleHeight));
		lblTitle.setBorder(FaceContants.LINE_BORDER);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBackground(ColorConstants.LABEL_CD_BACKGROUND_COLOR);
		mainPnl.add(lblTitle);

		yPos = yPos + iTitleHeight + space;
		txtOut1Path = new BaseTextArea();
		txtOut1Path.setBorder(BorderFactory.createTitledBorder(""));
		txtOut1Path.setLocation(xPos, yPos);
		txtOut1Path.setSize(iTextAreaWith, iBtnHeight);
		txtOut1Path.setLineWrap(true);
		mainPnl.add(txtOut1Path);

		btnOut1Path = new ActionButton();
		btnOut1Path.setFocusable(false);
		btnOut1Path.setBounds(new Rectangle(xPos + iTextAreaWith + 5, yPos, iBtnWith, iBtnHeight));
		btnOut1Path.setText("<html><center><font size=-1>参照</font></center></html>");
		btnOut1Path.addAction(new DialogFileAction(txtOut1Path));
		mainPnl.add(btnOut1Path);

		yPos = yPos + iTitleHeight + space;
		txtOut2Path = new BaseTextArea();
		txtOut2Path.setBorder(BorderFactory.createTitledBorder(""));
		txtOut2Path.setLocation(xPos, yPos);
		txtOut2Path.setSize(iTextAreaWith, iBtnHeight);
		txtOut2Path.setLineWrap(true);
		mainPnl.add(txtOut2Path);

		btnOut2Path = new ActionButton();
		btnOut2Path.setFocusable(false);
		btnOut2Path.setBounds(new Rectangle(xPos + iTextAreaWith + 5, yPos, iBtnWith, iBtnHeight));
		btnOut2Path.setText("<html><center><font size=-1>参照</font></center></html>");
		btnOut2Path.addAction(new DialogFileAction(txtOut2Path));
		mainPnl.add(btnOut2Path);

		yPos = yPos + iTitleHeight + space;
		txtOut3Path = new BaseTextArea();
		txtOut3Path.setBorder(BorderFactory.createTitledBorder(""));
		txtOut3Path.setLocation(xPos, yPos);
		txtOut3Path.setSize(iTextAreaWith, iBtnHeight);
		txtOut3Path.setLineWrap(true);
		txtOut3Path.setEditable(false);
		mainPnl.add(txtOut3Path);

		btnOut3Path = new ActionButton();
		btnOut3Path.setFocusable(false);
		btnOut3Path.setBounds(new Rectangle(xPos + iTextAreaWith + 5, yPos, iBtnWith, iBtnHeight));
		btnOut3Path.setText("<html><center><font size=-1>参照</font></center></html>");
		btnOut3Path.setEnabled(false);
		mainPnl.add(btnOut3Path);

		yPos = yPos + iTitleHeight + space;
		txtOut4Path = new BaseTextArea();
		txtOut4Path.setBorder(BorderFactory.createTitledBorder(""));
		txtOut4Path.setLocation(xPos, yPos);
		txtOut4Path.setSize(iTextAreaWith, iBtnHeight);
		txtOut4Path.setLineWrap(true);
		txtOut4Path.setEditable(false);
		mainPnl.add(txtOut4Path);

		btnOut4Path = new ActionButton();
		btnOut4Path.setFocusable(false);
		btnOut4Path.setBounds(new Rectangle(xPos + iTextAreaWith + 5, yPos, iBtnWith, iBtnHeight));
		btnOut4Path.setText("<html><center><font size=-1>参照</font></center></html>");
		btnOut4Path.setEnabled(false);
		mainPnl.add(btnOut4Path);

		yPos = yPos + iTitleHeight + space;
		txtOut5Path = new BaseTextArea();
		txtOut5Path.setBorder(BorderFactory.createTitledBorder(""));
		txtOut5Path.setLocation(xPos, yPos);
		txtOut5Path.setSize(iTextAreaWith, iBtnHeight);
		txtOut5Path.setLineWrap(true);
		txtOut5Path.setEditable(false);
		mainPnl.add(txtOut5Path);

		btnOut5Path = new ActionButton();
		btnOut5Path.setFocusable(false);
		btnOut5Path.setBounds(new Rectangle(xPos + iTextAreaWith + 5, yPos, iBtnWith, iBtnHeight));
		btnOut5Path.setText("<html><center><font size=-1>参照</font></center></html>");
		btnOut5Path.setEnabled(false);
		mainPnl.add(btnOut5Path);

		yPos = yPos + iTitleHeight + space;
		BasePanel pnlButton = new BasePanel();
		pnlButton.setSize(iBodyWith, iTitleHeight);
		pnlButton.setLocation(xPos, yPos);
		pnlButton.setBorder(BorderFactory.createTitledBorder(""));

		F8Action f8Action = new F8Action("f8Action");
		btnF8 = new ActionButton();
		btnF8.setFocusable(false);
		btnF8.setBounds(new Rectangle(xPos + iTextAreaWith + 5, yPos, iBtnWith + 5, iBtnHeight - 10));
		btnF8.setText("<html><center><font size=-1>OK</font></center><center><font size=-1>F8(A)</font></center></html>");
		btnF8.setLocation(iBodyWith / 2 - iBtnWith - 25, 5);
		btnF8.addActionListener(f8Action);
		btnF8.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F8"), f8Action.getValue(Action.NAME));
		btnF8.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("alt A"), f8Action.getValue(Action.NAME));
		btnF8.getActionMap().put("f8Action", f8Action);
		pnlButton.add(btnF8);

		F12Action f12Action = new F12Action("f12Action");
		btnF12 = new ActionButton();
		btnF12.setFocusable(false);
		btnF12.setBounds(new Rectangle(xPos + iTextAreaWith + 5, yPos, iBtnWith + 5, iBtnHeight - 10));
		btnF12.setText("<html><center><font size=-1>終了</font></center><center><font size=-1>F12(Q)</font></center></html>");
		btnF12.setLocation(iBodyWith / 2 + 20, 5);
		btnF12.addActionListener(f12Action);
		btnF12.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F12"), f12Action.getValue(Action.NAME));
		btnF12.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("alt Q"), f12Action.getValue(Action.NAME));
		btnF12.getActionMap().put("f12Action", f12Action);
		pnlButton.add(btnF12);

		mainPnl.add(pnlButton);

		return mainPnl;
	}

	@Override
	protected BasePanel getFooter() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected JSeparator getFooterSeparator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected BasePanel getHeader() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected JSeparator getHeaderSeparator() {
		// TODO Auto-generated method stub
		return null;
	}

	protected boolean directoriesExist(String path) {
		File file = new File(path);
		boolean exist = file.exists();
		return exist;

	}

	protected boolean validateData() {
		boolean isValid = true;
		JComponent comp = null;

		// isValid
		if (isValid && !StringUtils.isValid(txtOut1Path.getText().trim())) {
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0005"));
			isValid = false;
			comp = txtOut1Path;
		}

		// not exist
		if (isValid && !directoriesExist(txtOut1Path.getText().trim())) {
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0179"));
			isValid = false;
			comp = txtOut1Path;
		}

		// check max length
		if (isValid && !StringUtils.checkLenString(txtOut1Path.getText().trim(), 200)) {
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0129"));
			isValid = false;
			comp = txtOut1Path;
		}

		// isValid
		if (isValid && !StringUtils.isValid(txtOut2Path.getText().trim())) {
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0005"));
			isValid = false;
			comp = txtOut2Path;
		}

		// not exist
		if (isValid && !directoriesExist(txtOut2Path.getText().trim())) {
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0179"));
			isValid = false;
			comp = txtOut2Path;
		}

		// check max length
		if (isValid && !StringUtils.checkLenString(txtOut2Path.getText().trim(), 200)) {
			MessageBox.message(this, MessageConstants.getMstMsgVo("E0129"));
			isValid = false;
			comp = txtOut2Path;
		}

		if (comp != null) {
			comp.requestFocusInWindow();
		}

		return isValid;
	}

	private OutCtlVo convertTxtToVo() {
		OutCtlVo dataVo = new OutCtlVo();
		dataVo.setUserId(ApplicationConstants.getLoginUser().getUserId());
		dataVo.setOut1Path(txtOut1Path.getText().trim());
		dataVo.setOut2Path(txtOut2Path.getText().trim());
		return dataVo;
	}

	protected void doF8() {
		if (validateData()) {
			if (MessageBox.message(this, MessageConstants.getMstMsgVo("Q0002")) == MessageBox.YES) {
				OutCtlVo dataVo = convertTxtToVo();
				try {
					if (outCtlService.getOutCtlVoByUserId(ApplicationConstants.getLoginUser().getUserId()) != null) {
						outCtlService.updateOutCtlVo(dataVo);
					} else {
						outCtlService.insertOutCtlVo(dataVo);
					}
					loadOutClt(ApplicationConstants.getLoginUser().getUserId());
				} catch (BizException ex) {
					MessageBox.message(this, MessageConstants.getMstMsgVo("C0000"));
					logger.error(ex.getMessage());
				}
			}
		}
	}

	protected void doF12() {
		// if(MessageBox.message(this, MessageConstants.getMstMsgVo("Q0001")) ==
		// MessageBox.YES){
		// dispose();
		// }

		dispose();
	}

	class F8Action extends AbstractAction {

		/** */
		private static final long serialVersionUID = 1L;

		public F8Action(String name) {
			super(name);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.linc.jface.base.button.Action#execute()
		 */
		public void actionPerformed(ActionEvent e) {
			doF8();
		}
	}

	class F12Action extends AbstractAction {

		/** */
		private static final long serialVersionUID = 1L;

		public F12Action(String name) {
			super(name);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.linc.jface.base.button.Action#execute()
		 */
		public void actionPerformed(ActionEvent e) {
			doF12();
		}
	}

	class DialogFileAction implements com.fas.jface.button.Action {

		/** */
		BaseTextArea txtInputText;

		/**
		 * <DL>
		 * <DT>コンストラクター記述：</DT>
		 * <DD></DD>
		 * <BR>
		 * 
		 * </DL>
		 * 
		 * @param txtInput
		 */
		public DialogFileAction(BaseTextArea txtInput) {
			txtInputText = txtInput;
		}

		/**
		 * <DL>
		 * <DT>メソッド記述：</DT>
		 * <DD></DD>
		 * <BR>
		 * 
		 * </DL>
		 */
		public void execute() {
			JFileChooser fileChoser = new JFileChooser();
			fileChoser.setCurrentDirectory(FileUtils.getFileObj(""));
			fileChoser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			fileChoser.setAcceptAllFileFilterUsed(false);

			File file = new File(txtInputText.getText());
			if (file.exists()) {
				fileChoser.setSelectedFile(file);
				fileChoser.setCurrentDirectory(file);
			}

			int rVal = fileChoser.showSaveDialog(MyOutConfigFrame.this);
			if (rVal == JFileChooser.APPROVE_OPTION) {
				txtInputText.requestFocusInWindow();
				txtInputText.setText(fileChoser.getSelectedFile().getAbsolutePath());
			}
		}
	}

}
