/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：BaseCalendar.java
*
*     記述				：
*     
*     作成日			：2009/10/26   
*
*     作成者			：PC13
*
*     備考				：
*
**************************************************************************************/

package com.fas.jface.calendar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Point;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRootPane;
import javax.swing.JSeparator;
import javax.swing.JToolTip;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import com.fas.common.constants.ApplicationConstants;
import com.fas.common.constants.screen.ColorConstants;
import com.fas.common.utils.StringUtils;
import com.fas.jface.button.ActionButton;
import com.fas.jface.gui.BaseDialog;
import com.fas.jface.gui.BaseFrame;
import com.fas.jface.gui.BasePanel;
import com.fas.jface.label.BaseLabel;
import com.fas.jface.table.CalendarTable;
import com.fas.vo.holiday.HolidayVo;

/**
 * <DL>
 *   <DT>クラス記述：</DT>
 * 	<DD></DD>
 * <BR>
 *   <DT>変更歴史：</DT>
 * 	<DD>著作者: PC13</DD><BR>
 * 	<DD></DD>
 * </DL>
 */

public class BaseCalendar extends BaseDialog {

    /** */
	private static final long serialVersionUID = 1L;
    /** */
    private CalendarTable days = null;
    /** */
    private String[] weeksName = new String[] {"日","月","火","水","木","金","土"};
    /** */
    private int beforeRow = -1;
    /** */
    private int beforeCol = -1;
    /** */
    private int iCMonth = 0;
    /** */
    private int iCYear = 0;
    /** */
    private boolean click = false;
    /** */
    private Date selectedDate = null;
    /** */
    private Component parent;
    /** */
    private Component parentFrame;
    /** */
    private static Font CALENDAR_FONT = new Font("ＭＳ ゴシック", Font.PLAIN, 14);
    /** */
    private ActionButton btnPreM = null;
    /** */
    private ActionButton btnNextM = null;
    /** */
    private ActionButton btnClose = null;
    /** */
    private BaseLabel lblCTime = null;
    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @return
     */
    public boolean isOk() {
        return click;
    }
    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @return
     */
    public Date getSelectedDate() {
        if(isOk()){
            return selectedDate;
        }else{
            return null;
        }
    }
    
    /**
     * <DL>
     *   <DT>コンストラクター記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * * @param baseFrame
     * @param frame
     * @param date
     */
    public BaseCalendar(BaseFrame baseFrame, Component frame, Date date) {
    	super(baseFrame, true);
    	this.parent = frame;
    	setModal(true);
    	setUndecorated(true);
    	setResizable(false);
    	addWindowListener(new WindowAdapter() {
    	    public void windowClosing(WindowEvent we) {
    	    	close();
    	    }
    	});
    	
    	addFocusListener(new FocusAdapter() {
    	    public void focusGained(FocusEvent e) {
    	    	if (parentFrame != null) {
    	    		parentFrame.setVisible(true);
    	    	}
    	    }
		});
    	
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
    	
    	iCYear = cal.get(Calendar.YEAR);
    	iCMonth = cal.get(Calendar.MONTH);
    	
    	BasePanel mainPnl = new BasePanel();
    	mainPnl.setBorder(null);
    	
    	mainPnl.setBackground(ColorConstants.CALENDAR_DEFAULT_BACK_COLOR);
      	getContentPane().add(mainPnl);
        mainPnl.setLayout(new BorderLayout());
        mainPnl.setBorder(new LineBorder(Color.LIGHT_GRAY));
        
        BasePanel header = new BasePanel();
        header.setBackground(ColorConstants.CALENDAR_DEFAULT_HEADER_BACK_COLOR);
        header.setPreferredSize(new Dimension(185, 25));
        header.setLocation(0, 0);
        
        selectedDate = date;
        
        btnPreM = new ActionButton("<<");
        btnPreM.setSize(new Dimension(25, 25));
        btnPreM.setFocusable(false);
        btnPreM.setLocation(0, 0);
        btnPreM.setMargin(new Insets(1, 1, 1, 1));
        btnPreM.setBorder(null);
        btnPreM.setBackground(ColorConstants.CALENDAR_DEFAULT_HEADER_BACK_COLOR);
        btnPreM.addActionListener(new ActionListener() {
        	/* (Javadoc なし)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent e) {
				
				if (iCMonth == 0) {
					iCMonth = 11;
					iCYear = iCYear - 1;
				} else {
					iCMonth = iCMonth - 1;
				}
		        lblCTime.setText(iCYear + "年" + (iCMonth + 1) + "月");
				days.setModel(getCalendarData());
			}
        });
        
        btnClose = new ActionButton("x");
        btnClose.setBackground(ColorConstants.CALENDAR_DEFAULT_HEADER_BACK_COLOR);
        btnClose.setSize(new Dimension(20, 25));
        btnClose.setLocation(140, 0);
        btnClose.setFocusable(false);
        btnClose.setBorder(null);
        btnClose.setMargin(new Insets(1, 1, 1, 1));
        btnClose.addActionListener(new ActionListener() {
        	/* (Javadoc なし)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent e) {
				close();
			}
        });
        
        btnNextM = new ActionButton(">>");
        btnNextM.setBackground(ColorConstants.CALENDAR_DEFAULT_HEADER_BACK_COLOR);
        btnNextM.setSize(new Dimension(25, 25));
        btnNextM.setLocation(160, 0);
        btnNextM.setFocusable(false);
        btnNextM.setBorder(null);
        btnNextM.setMargin(new Insets(1, 1, 1, 1));
        btnNextM.addActionListener(new ActionListener() {
        	/* (Javadoc なし)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent e) {
				if (iCMonth == 11) {
					iCMonth = 0;
					iCYear = iCYear + 1;
				} else {
					iCMonth = iCMonth + 1;
				}
		        lblCTime.setText(iCYear + "年" + (iCMonth + 1) + "月");
				days.setModel(getCalendarData());
			}
        });
        
        BaseLabel lblSpace = new BaseLabel("");
        lblSpace.setLocation(24, 0);
        lblSpace.setSize(new Dimension(20, 25));
        lblSpace.setBorder(null);
        lblSpace.setBackground(ColorConstants.CALENDAR_DEFAULT_HEADER_BACK_COLOR);
        
        lblCTime = new BaseLabel();
        lblCTime.setLocation(44, 0);
        lblCTime.setSize(new Dimension(97, 25));
        lblCTime.setHorizontalAlignment(BaseLabel.CENTER);
        lblCTime.setBorder(null);
        lblCTime.setText(iCYear + "年" + (iCMonth + 1) + "月");
        lblCTime.setBackground(ColorConstants.CALENDAR_DEFAULT_HEADER_BACK_COLOR);
        
        header.setLayout(null);
        header.add(btnPreM);
        header.add(lblSpace);
        header.add(btnNextM);
        header.add(btnClose);
        header.add(lblCTime);
        
        mainPnl.add(header, BorderLayout.NORTH);
        
        days = new CalendarTable();
        days.setModel(getCalendarData());
        days.setRowHeight(25);
        days.setColumnSelectionAllowed(true);
        days.setRowSelectionAllowed(true);
        days.setShowHorizontalLines(false);
        days.setShowVerticalLines(false);
        days.setCellSelectionEnabled(true);
        days.setFocusable(false);
        days.addMouseListener(new MousEvent());
        days.setBorder(null);
        days.setFont(CALENDAR_FONT);

        JTableHeader tableHeader = days.getTableHeader();
        tableHeader.setReorderingAllowed(false);
        tableHeader.setSize(new Dimension(175, 25));
        tableHeader.setFont(CALENDAR_FONT);
        tableHeader.setLocation(5, 0);
        tableHeader.setBackground(ColorConstants.CALENDAR_DEFAULT_BACK_COLOR);
        tableHeader.setBorder(null);

        days.setLocation(5, 26);
        days.setSize(new Dimension(175, 150));
        BasePanel calPnl = new BasePanel();
        calPnl.setBackground(ColorConstants.CALENDAR_DEFAULT_BACK_COLOR);
        calPnl.setPreferredSize(new Dimension(185, 175));
        calPnl.setLocation(0, 25);
        calPnl.setLayout(null);
        calPnl.add(tableHeader);
        JSeparator separator = new JSeparator();
        separator.setLocation(5, 25);
        separator.setSize(175, 1);
        separator.setBackground(SystemColor.GRAY);
        calPnl.add(separator);
        calPnl.add(days);
        
        mainPnl.add(calPnl, BorderLayout.CENTER);
        
        pack();
        
    	if (parent != null) {
	        Point parentP = parent.getLocationOnScreen();
	        Dimension scSize = Toolkit.getDefaultToolkit().getScreenSize();
	        Point p = new Point();
	        
	        if ((parentP.x + this.getWidth()) > scSize.getWidth()) {
	        	p.x = parentP.x + parent.getWidth() - this.getWidth();
	        } else {
	        	p.x = parentP.x;
	        }
	        
	        if ((parentP.y + parent.getHeight() + this.getHeight()) > scSize.getHeight()) {
	        	p.y = parentP.y - this.getHeight();
	        } else {
	        	p.y = parentP.y + parent.getHeight();
	        }
	        setLocation(p);
    	} else {
    		setLocationRelativeTo(parent);
    	}
    	
    }
    
    /**
     * <DL>
     *   <DT>コンストラクター記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param frame
     * @param date
     */
    public BaseCalendar(Component frame, Date date) {
    	
    	this.parent = frame;
    	setModal(true);
    	setUndecorated(true);
    	setResizable(false);
    	addWindowListener(new WindowAdapter() {
    	    public void windowClosing(WindowEvent we) {
    	    	close();
    	    }
    	});
    	
    	addFocusListener(new FocusAdapter() {
    	    public void focusGained(FocusEvent e) {
    	    	if (parentFrame != null) {
    	    		parentFrame.setVisible(true);
    	    	}
    	    }
		});
    	
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
    	
    	iCYear = cal.get(Calendar.YEAR);
    	iCMonth = cal.get(Calendar.MONTH);
    	
    	BasePanel mainPnl = new BasePanel();
    	mainPnl.setBorder(null);
    	
    	mainPnl.setBackground(ColorConstants.CALENDAR_DEFAULT_BACK_COLOR);
      	getContentPane().add(mainPnl);
        mainPnl.setLayout(new BorderLayout());
        mainPnl.setBorder(new LineBorder(Color.LIGHT_GRAY));
        
        BasePanel header = new BasePanel();
        header.setBackground(ColorConstants.CALENDAR_DEFAULT_HEADER_BACK_COLOR);
        header.setPreferredSize(new Dimension(185, 25));
        header.setLocation(0, 0);
        
        selectedDate = date;
        
        btnPreM = new ActionButton("<<");
        btnPreM.setSize(new Dimension(25, 25));
        btnPreM.setFocusable(false);
        btnPreM.setLocation(0, 0);
        btnPreM.setMargin(new Insets(1, 1, 1, 1));
        btnPreM.setBorder(null);
        btnPreM.setBackground(ColorConstants.CALENDAR_DEFAULT_HEADER_BACK_COLOR);
        btnPreM.addActionListener(new ActionListener() {
        	/* (Javadoc なし)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent e) {
				
				if (iCMonth == 0) {
					iCMonth = 11;
					iCYear = iCYear - 1;
				} else {
					iCMonth = iCMonth - 1;
				}
		        lblCTime.setText(iCYear + "年" + (iCMonth + 1) + "月");
				days.setModel(getCalendarData());
			}
        });
        
        btnClose = new ActionButton("x");
        btnClose.setBackground(ColorConstants.CALENDAR_DEFAULT_HEADER_BACK_COLOR);
        btnClose.setSize(new Dimension(20, 25));
        btnClose.setLocation(140, 0);
        btnClose.setFocusable(false);
        btnClose.setBorder(null);
        btnClose.setMargin(new Insets(1, 1, 1, 1));
        btnClose.addActionListener(new ActionListener() {
        	/* (Javadoc なし)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent e) {
				close();
			}
        });
        
        btnNextM = new ActionButton(">>");
        btnNextM.setBackground(ColorConstants.CALENDAR_DEFAULT_HEADER_BACK_COLOR);
        btnNextM.setSize(new Dimension(25, 25));
        btnNextM.setLocation(160, 0);
        btnNextM.setFocusable(false);
        btnNextM.setBorder(null);
        btnNextM.setMargin(new Insets(1, 1, 1, 1));
        btnNextM.addActionListener(new ActionListener() {
        	/* (Javadoc なし)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent e) {
				if (iCMonth == 11) {
					iCMonth = 0;
					iCYear = iCYear + 1;
				} else {
					iCMonth = iCMonth + 1;
				}
		        lblCTime.setText(iCYear + "年" + (iCMonth + 1) + "月");
				days.setModel(getCalendarData());
			}
        });
        
        BaseLabel lblSpace = new BaseLabel("");
        lblSpace.setLocation(24, 0);
        lblSpace.setSize(new Dimension(20, 25));
        lblSpace.setBorder(null);
        lblSpace.setBackground(ColorConstants.CALENDAR_DEFAULT_HEADER_BACK_COLOR);
        
        lblCTime = new BaseLabel();
        lblCTime.setLocation(44, 0);
        lblCTime.setSize(new Dimension(97, 25));
        lblCTime.setHorizontalAlignment(BaseLabel.CENTER);
        lblCTime.setBorder(null);
        lblCTime.setText(iCYear + "年" + (iCMonth + 1) + "月");
        lblCTime.setBackground(ColorConstants.CALENDAR_DEFAULT_HEADER_BACK_COLOR);
        
        header.setLayout(null);
        header.add(btnPreM);
        header.add(lblSpace);
        header.add(btnNextM);
        header.add(btnClose);
        header.add(lblCTime);
        
        mainPnl.add(header, BorderLayout.NORTH);
        
        days = new CalendarTable();
        days.setModel(getCalendarData());
        days.setRowHeight(25);
        days.setColumnSelectionAllowed(true);
        days.setRowSelectionAllowed(true);
        days.setShowHorizontalLines(false);
        days.setShowVerticalLines(false);
        days.setCellSelectionEnabled(true);
        days.setFocusable(false);
        days.addMouseListener(new MousEvent());
        days.setBorder(null);
        days.setFont(CALENDAR_FONT);

        JTableHeader tableHeader = days.getTableHeader();
        tableHeader.setReorderingAllowed(false);
        tableHeader.setSize(new Dimension(175, 25));
        tableHeader.setFont(CALENDAR_FONT);
        tableHeader.setLocation(5, 0);
        tableHeader.setBackground(ColorConstants.CALENDAR_DEFAULT_BACK_COLOR);
        tableHeader.setBorder(null);

        days.setLocation(5, 26);
        days.setSize(new Dimension(175, 150));
        BasePanel calPnl = new BasePanel();
        calPnl.setBackground(ColorConstants.CALENDAR_DEFAULT_BACK_COLOR);
        calPnl.setPreferredSize(new Dimension(185, 175));
        calPnl.setLocation(0, 25);
        calPnl.setLayout(null);
        calPnl.add(tableHeader);
        JSeparator separator = new JSeparator();
        separator.setLocation(5, 25);
        separator.setSize(175, 1);
        separator.setBackground(SystemColor.GRAY);
        calPnl.add(separator);
        calPnl.add(days);
        
        mainPnl.add(calPnl, BorderLayout.CENTER);
        
        pack();
        
    	if (parent != null) {
	        Point parentP = parent.getLocationOnScreen();
	        Dimension scSize = Toolkit.getDefaultToolkit().getScreenSize();
	        Point p = new Point();
	        
	        if ((parentP.x + this.getWidth()) > scSize.getWidth()) {
	        	p.x = parentP.x + parent.getWidth() - this.getWidth();
	        } else {
	        	p.x = parentP.x;
	        }
	        
	        if ((parentP.y + parent.getHeight() + this.getHeight()) > scSize.getHeight()) {
	        	p.y = parentP.y - this.getHeight();
	        } else {
	        	p.y = parentP.y + parent.getHeight();
	        }
	        setLocation(p);
    	} else {
    		setLocationRelativeTo(parent);
    	}
    	
    }
    
    /* (non-Javadoc)
     * @see javax.swing.JDialog#createRootPane()
     */
    protected JRootPane createRootPane() {
    	  KeyStroke stroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
    	  JRootPane rootPane = new JRootPane();
    	  
    	  ActionListener actionListener = new ActionListener() {
    		  public void actionPerformed(ActionEvent actionEvent) {
    		     close();
    		  }
    	  };

    	  rootPane.registerKeyboardAction(actionListener, stroke, JComponent.WHEN_IN_FOCUSED_WINDOW);
    	  return rootPane;
    }

    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     */
    private void close() {
    	dispose();
    }
    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @return
     */
    private boolean check() {
    	
    	Calendar cal = Calendar.getInstance();
    	
        if(selectedDate == null){
        	return false;
        }
        
        cal.setTime(selectedDate);
        
        if(iCYear == cal.get(Calendar.YEAR) && iCMonth==cal.get(Calendar.MONTH)) {
        	return true;
        }
        return false;
    }
    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @return
     */
    protected TableModel getCalendarData() {
    	
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, iCYear);
        cal.set(Calendar.MONTH, iCMonth);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK)-1;
        
        cal.add(Calendar.MONTH, 1);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        
        int lastDay = cal.get(Calendar.DAY_OF_MONTH);
        Object[][] data = new Object[6][7];
        int dayCount = 0;
        
        cal.set(Calendar.YEAR, iCYear);
        cal.set(Calendar.MONTH, iCMonth);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        int preMonthLastDay = cal.get(Calendar.DAY_OF_MONTH);
        preMonthLastDay = preMonthLastDay - dayOfWeek + 1;
        
        cal.setTime(selectedDate);
        int nowDays = cal.get(Calendar.DAY_OF_MONTH);
        int nextMonthFirstDay = 1;
        boolean isNotBlank = false;
        
        for(int x = 0; x<6; x++) {
            Object[] week = new Object[7];
            for(int i=0; i<7; i++) week[i] = new JLabel();
            
            for(int y=0; y<7; y++) {
            	
                if (x==0) {
                    if (y >= dayOfWeek) {
                    	isNotBlank=true;
                    } else {
                        JLabel label = new JLabel(Integer.toString(preMonthLastDay));
                        label.setOpaque(true);
                        label.setHorizontalAlignment(SwingConstants.CENTER);
                        label.setBackground(days.getBackground());
                        label.setForeground(Color.GRAY);
                        preMonthLastDay++;
                        week[y] = label;
                    }
                }
            
                if (isNotBlank) {
                    dayCount++;
                    final JLabel label = new JLabel(Integer.toString(dayCount)) {
                    	/** */
						private static final long serialVersionUID = 1L;

						public JToolTip createToolTip() {
							
                    		return (new MyToolTip(this));
                    	}
                    };
                    
                    label.setOpaque(true);
                    label.setHorizontalAlignment(SwingConstants.CENTER);
                    label.setBackground(days.getBackground());
                    
                    if (dayCount==nowDays && check()) {
                    	label.setBackground(days.getSelectionBackground());
                    	label.setForeground(Color.WHITE);
                    	beforeRow = x;
                    	beforeCol = y;
                    }
                    
                    /**日曜日 */
                    if (y == 0) {
                    	label.setForeground(ColorConstants.CALENDAR_NICHIYOUBI_COLOR);
                    }
                    /**休みの日 */
                    if (isYasumiNohi(iCYear, iCMonth + 1, dayCount)) {
                    	
                    	label.setForeground(ColorConstants.CALENDAR_YASUMINOHI_COLOR);

                    	final int row = x;
                    	final int col = y;
                    	label.addMouseListener(new MouseAdapter() {
                    		public void mouseClicked(MouseEvent e) {
                    			
                    			String value = label.getText();
                    			System.out.println("Mouse click " +value);
                    			
                    			if("".equals(value)) {
                    				return ;
                    			}

                				click = true;
                				
                				beforeRow = row;
                				beforeCol = col;
                				selectedDate = getSelectedDate(value);
                				
                				close();
                    		}		
                    	});
                    	
                    	label.setToolTipText(getYasumiNoTooltip(iCYear, iCMonth + 1, dayCount));
                    	//ToolTipManager.sharedInstance().
                    	//label.createToolTip().dispatchEvent(e)
                    }
                    
                    /**当日 */
                    if (isKyou(iCYear, iCMonth, dayCount)) {
                    	label.setBorder(new LineBorder(Color.RED));
                    }
                    
                    /**土曜日 */
                    if (y == 6) {
                        if (dayCount==nowDays && check()) {
                        	label.setBackground(days.getSelectionBackground());
                        	label.setForeground(Color.WHITE);
                        } else {
                        	label.setForeground(ColorConstants.CALENDAR_DOYOUBI_COLOR);
                        }
                    }
                    
                    week[y] = label;
                } else {
                	if (x > 0) {
	                    JLabel label = new JLabel(Integer.toString(nextMonthFirstDay));
	                    label.setOpaque(true);
	                    label.setHorizontalAlignment(SwingConstants.CENTER);
	                    label.setBackground(days.getBackground());
	                    label.setForeground(Color.GRAY);
	                    week[y] = label;
	                	nextMonthFirstDay++;
                	}
                }
                
                if(dayCount==lastDay) {
                	isNotBlank = false;
                }
            }
            data[x]=week;
        }
        
        DefaultTableModel dataTableModel = new DefaultTableModel(data, weeksName);
        
        return dataTableModel;
    }
    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param strDate
     * @return
     */
    private boolean isYasumiNohi(int yy, int mm, int dd) {
    	
    	boolean isYasumi = false;

    	String strDate = yy + StringUtils.fillNumberMaxLen("" + mm, 2) + StringUtils.fillNumberMaxLen("" + dd, 2);
    	/**祝日の日のチェック */
    	for (int i = 0; i < ApplicationConstants.LST_HOLIDAY_DATE.size(); i ++) {
    		HolidayVo dataVo = ApplicationConstants.LST_HOLIDAY_DATE.get(i);
    		if (strDate.equalsIgnoreCase(dataVo.getShujitsubi().trim())) {
				isYasumi = true;
				break;
    		}
    	}
    	
    	return isYasumi;
    }
    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param yy
     * @param mm
     * @param dd
     * @return
     */
    private String getYasumiNoTooltip(int yy, int mm, int dd) {
    	
    	String strYasumi = "";

    	String strDate = yy + StringUtils.fillNumberMaxLen("" + mm, 2) + StringUtils.fillNumberMaxLen("" + dd, 2);
    	/**祝日の日のチェック */
    	for (int i = 0; i < ApplicationConstants.LST_HOLIDAY_DATE.size(); i ++) {
    		HolidayVo dataVo = ApplicationConstants.LST_HOLIDAY_DATE.get(i);
    		if (strDate.equalsIgnoreCase(dataVo.getShujitsubi().trim())) {
    			strYasumi = dataVo.getShujitsumei();
				break;
    		}
    	}
    	
    	return strYasumi;
    }
    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param strDate
     * @return
     */
    private boolean isKyou(int yy, int mm, int dd) {
    	
    	boolean isToday = false;
    	
    	Date kyou = new Date();
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(kyou);
    	/**当日のチェック */
    	if ((yy == cal.get(Calendar.YEAR)) && (mm == cal.get(Calendar.MONTH)) && (dd == cal.get(Calendar.DAY_OF_MONTH))) {
    		isToday = true;
    	}
    	
    	return isToday;
    }
    
    /**
     * <DL>
     *   <DT>メソッド記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * </DL>
     * @param value
     * @return
     */
    private Date getSelectedDate(String value) {
    	
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, iCYear);
        cal.set(Calendar.MONTH, iCMonth);
        
        int day = Integer.parseInt(value);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        
        if (beforeRow == 0) {
        	if (day > 15) {
        		cal.add(Calendar.MONTH, -1);
        	}
        }
        
        if (beforeRow > 3) {
        	if (day < 15) {
        		cal.add(Calendar.MONTH, 1);
        	}
        }

        cal.set(Calendar.DAY_OF_MONTH,day);
        
        return cal.getTime();
    }
    
    /**
     * <DL>
     *   <DT>クラス記述：</DT>
     * 		<DD></DD>
     * <BR>
     *
     * @author PC13
     *
     */
    private class MousEvent extends MouseAdapter {
    	
		public void mouseClicked(MouseEvent e) {
			
			Point p = e.getPoint();
			int row = days.rowAtPoint(p);
			int col = days.columnAtPoint(p);
			JLabel now = (JLabel) days.getValueAt(row,col);
			String value = now.getText();
			
			if("".equals(value)) {
				return ;
			}
			
			if (e.getClickCount() > 0) {
				click = true;
				beforeRow = row;
				beforeCol = col;
				selectedDate = getSelectedDate(value);
				close();
			} else {
				click = false;
				close();
			}
		}
    }
    
    /**
     * @author PC13
     *
     */
    private class MyToolTip extends JToolTip {
    	
    	/** */
		private static final long serialVersionUID = 1L;

		/**
    	 * <DL>
    	 *   <DT>コンストラクター記述：</DT>
    	 * 		<DD></DD>
    	 * <BR>
    	 *
    	 * </DL>
    	 * @param comp
    	 */
    	public MyToolTip(JComponent comp) {
    		super();
    		setComponent(comp);
    	}
    }
    
    /**
	 * @return the parentFrame
	 */
	public Component getParentFrame() {
		return parentFrame;
	}

	/**
	 * @param parentFrame the parentFrame to set
	 */
	public void setParentFrame(Component parentFrame) {
		this.parentFrame = parentFrame;
	}

	public static void main(String[] args){
    	JFrame frame = new JFrame();
    	frame.setPreferredSize(new Dimension(310, 185));
    	frame.setLocation(175, 175);
    	frame.pack();
    	BaseCalendar calendar = new BaseCalendar(frame, new Date());
        calendar.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        calendar.setVisible(true);
    }

}

