package com.fas.report.base;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import org.jdesktop.swingx.JXBusyLabel;

import com.fas.common.utils.NumberUtils;

public class SplashScreen extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4774815825510548472L;

	public SplashScreen()
	{
		setTitle("実行中です。しばらくお待ち下さい。");
		setModal(true);
		//super(this,"実行中です。しばらくお待ち下さい。", true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setPreferredSize(new Dimension(250, 90));
		JPanel content = (JPanel) getContentPane();
		content.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
		content.setBackground(Color.blue);
		int width = 250;
		int height = 90;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screen.width - width) / 2;
		int y = (screen.height - height) / 2;
		setBounds(x, y, width, height);
		JXBusyLabel sms = new JXBusyLabel();
		sms.setBusy(true);
		sms.setText("<html><center><font size=5 COLOR=white>只今処理中です。</font></center><center><font size=5 COLOR=white>しばらくお待ちください。</font></center>");
		sms.setHorizontalAlignment(SwingConstants.CENTER);
		content.add(sms,
						BorderLayout.CENTER);
		setUndecorated(true);
	    setLocation(NumberUtils.getIntFromDouble((java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 250)/ 2) - 3, NumberUtils.getIntFromDouble((java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 90) / 2) - 32);
	    pack();
	    setResizable(false);
	}
}
