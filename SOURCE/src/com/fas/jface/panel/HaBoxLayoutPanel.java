/** *********************************************************************************
 *     
 *     会社名			：林兼コンピューター株式会社
 *
 *     プロジェクト名		：
 * 
 *     ファイル名			：HaBoxLayoutPanel.java
 *
 *     記述				：a collection panel with row panel as data
 *     
 *     作成日			：2010/07/23   
 *
 *     作成者			：NQHung
 *
 *     備考				：
 *
 **************************************************************************************/
package com.fas.jface.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

import com.fas.common.constants.screen.ColorConstants;
import com.fas.jface.gui.BasePanel;

/**
 * @author NQHung
 * 
 */
public class HaBoxLayoutPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private BasePanel paneHeader;
	private BasePanel[] paneRows;

	public HaBoxLayoutPanel(int paneSize) {
		super();
		init(paneSize);
	}

	private void init(int paneSize) {
		BoxLayout boxLayout;
		boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		setLayout(boxLayout);
		
		setBorder(null);
        setBackground(ColorConstants.PANEL_DEFAULT_COLOR);

		paneHeader = new BasePanel();
		paneRows = new BasePanel[paneSize];
	}

	public int getRowCount() {
		return paneRows.length;
	}

	public void setPaneAt(BasePanel panel, int row) {
		paneRows[row] = panel;
	}

	public BasePanel getPaneAt(int rowIndex) {
		if (paneRows != null && rowIndex < paneRows.length) {
			BasePanel panel = (BasePanel) paneRows[rowIndex];
			return panel;
		} else {
			return null;
		}
	}

	public BasePanel getPaneHeader() {
		return paneHeader;
	}

	public void setPaneHeader(BasePanel paneHeader) {
		this.paneHeader = paneHeader;
		this.add(paneHeader);
	}

	public BasePanel[] getPaneRows() {
		return paneRows;
	}

	public void setPaneRows(BasePanel[] paneData) {
		this.paneRows = paneData;
		
		for (int row = 0; row < paneData.length; row ++) {
			BasePanel pane = paneData[row];
			paneRows[row] = pane;
			this.add(pane);
		}
	}

//	public void RemovePaneRow()
//	{
//		paneRows
//	}
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception evt) {
		}

		JFrame frame = new JFrame("Panel Collection");

		List<BasePanel> data = new ArrayList<BasePanel>();
		BasePanel tempPanel = new BasePanel();
		// tempPanel.setSize(200, 800);
		tempPanel.setBorder(BorderFactory.createLineBorder(Color.RED));

		BasePanel tempPanel1 = new BasePanel();
		// tempPanel1.setSize(200, 800);
		tempPanel1.setBorder(BorderFactory.createLineBorder(Color.GREEN));

		BasePanel tempPanel2 = new BasePanel();
		// tempPanel2.setSize(200, 800);
		tempPanel2.setBorder(BorderFactory.createLineBorder(Color.BLUE));

		data.add(tempPanel);
		data.add(tempPanel1);
		data.add(tempPanel2);

		HaBoxLayoutPanel gridPanel = new HaBoxLayoutPanel(3);
		gridPanel.setPreferredSize(new Dimension(600, 600));

		JScrollPane jScrollPane = new JScrollPane(gridPanel);

		frame.add(jScrollPane);
		frame.setSize(400, 100);
		frame.setVisible(true);
	}

}
