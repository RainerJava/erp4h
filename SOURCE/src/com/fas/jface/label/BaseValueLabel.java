/** *********************************************************************************
*     
*     会社名			：林兼コンピューター株式会社
*
*     プロジェクト名	：
* 
*     ファイル名		：BaseValueLabel.java
*
*     記述				：
*     
*     作成日			：2009/10/14   
*
*     作成者			：PC12
*
*     備考				：
*
**************************************************************************************/
package com.fas.jface.label;

import java.awt.Color;
import java.awt.Dimension;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.SwingConstants;

import com.fas.common.constants.screen.ColorConstants;
import com.fas.common.constants.screen.FontConstants;


/**
 * @author PC12
 *
 */
public class BaseValueLabel extends BaseLabel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int formatMode=0;
    
    private String unitName="";
    public final static int DEFAULT=0;
    public final static int EN=1;//###,###,##0
    public final static int DECIMAL1=2;//###,###,##0.0
    public final static int YYYY_DATE=3;//yyyy
    public final static int YYYY_MM_DATE=4;//yyyy / MM
    public final static int YYYY_MM_DD_DATE=5;// yyyy / MM /dd
    public final static int DECIMAL2=6;//###,###,##0.00
    public final static int MM_DATE=7;//MM
    public final static int DD_DATE=8;//dd
    public final static int MM_DD_DATE=9;//mm/dd
    public final static int YYYY_MM_DD_HH_MM_SS_DATE=10;// yyyy/MM/dd HH:mm:ss
    public final static int M_D_DATE=11;//mm/dd
    
    private Object value;
    private Format format;
    public BaseValueLabel(){
        init();
    }
    public BaseValueLabel(String text){
        this(text,DEFAULT);        
    }
    public BaseValueLabel(Integer text){
        this(text,EN);
    }
    public BaseValueLabel(Long text){
        this(text,EN);
    }
    public BaseValueLabel(BigDecimal text){
        this(text,DECIMAL1);
    }

    public BaseValueLabel(Date text){
        this(text,YYYY_MM_DD_DATE);
    }
    public BaseValueLabel(Object text,int formatMode){
        this.formatMode=formatMode;
        this.value=text;
        format=getFormat();
        if(formatMode!=0 && format==null){
            throw new IllegalArgumentException();
        }        
        init();
        setTextObj(text);
    }

    public int getFormatMode() {
        return formatMode;
    }
    public void setFormatMode(int formatMode) {
        this.formatMode = formatMode;
        switch(formatMode){
        case DEFAULT:;break;
        case EN :setHorizontalAlignment(BaseLabel.RIGHT);break;
        case DECIMAL1:setHorizontalAlignment(BaseLabel.RIGHT);break;
        case DECIMAL2:setHorizontalAlignment(BaseLabel.RIGHT);break;
        case YYYY_DATE:setHorizontalAlignment(BaseLabel.CENTER);break;
        case YYYY_MM_DATE:setHorizontalAlignment(BaseLabel.CENTER);break;
        case MM_DATE:setHorizontalAlignment(BaseLabel.CENTER);break;
        case DD_DATE:setHorizontalAlignment(BaseLabel.CENTER);break;
        case MM_DD_DATE:setHorizontalAlignment(BaseLabel.CENTER);break;
        case YYYY_MM_DD_HH_MM_SS_DATE:setHorizontalAlignment(BaseLabel.CENTER);break;
        case M_D_DATE:setHorizontalAlignment(BaseLabel.CENTER);break;
        }
    }
    public String getUnitName() {
        return unitName;
    }
    public void setUnitName(String unitName) {
        this.unitName = unitName;
        if(value!=null){
            String formattedText=format(value);
            super.setText(formattedText);
        }
    }
    
    public void setText(Date text,int mode) {
        this.formatMode=mode;
        this.value=text;
        format=getFormat();        
        setTextObj(text);
    }
    public void setText(Integer text) {
        this.formatMode=EN;
        this.value=text;
        format=getFormat();        
        setTextObj(text);
    }
    public void setText(Long text) {
        this.formatMode=EN;
        this.value=text;
        format=getFormat();
        setTextObj(text);
    }
    public void setTextObj(Object text) {
        String formattedText=format(text);
        super.setText(formattedText);
    }
    public void setText(Object text,int mode) {
        this.formatMode=mode;
        this.value=text;
        format=getFormat();        
        setTextObj(text);
    }
    
    public void setText(BigDecimal text) {
        this.formatMode=DECIMAL1;
        this.value=text;
        format=getFormat();        
        setTextObj(text);
    }
    
    
    private Format getFormat(){
            Format format=null;
            if(formatMode==EN){
                String formatString="###,###,##0";
                format=new DecimalFormat(formatString);
            }
            else if(formatMode==DECIMAL1){
                String  formatString="###,###,##0.0";
                format=new DecimalFormat(formatString);
            }
            else if(formatMode==YYYY_DATE){
                String formatString="yyyy";
                format=new SimpleDateFormat(formatString);
            }
            else if(formatMode==YYYY_MM_DATE){
                String formatString="yyyy / MM";
                format=new SimpleDateFormat(formatString);
            }
            else if(formatMode==YYYY_MM_DD_DATE){
                String formatString="yyyy / MM / dd";
                format=new SimpleDateFormat(formatString);
            }
            else if(formatMode==DECIMAL2){
                String formatString="###,###,##0.00";
                format=new DecimalFormat(formatString);
            }
            else if(formatMode==MM_DATE){
                String formatString="MM";
                format=new SimpleDateFormat(formatString);
            }
            else if(formatMode==DD_DATE){
                String formatString="dd";
                format=new SimpleDateFormat(formatString);
            }
            else if(formatMode==MM_DD_DATE){
                String formatString="MM/dd";
                format=new SimpleDateFormat(formatString);
            }
            else if(formatMode==YYYY_MM_DD_HH_MM_SS_DATE){
                String formatString="yyyy/MM/dd HH:mm:ss";
                format=new SimpleDateFormat(formatString);
            }
            else if(formatMode==M_D_DATE){
                String formatString="M/d";
                format=new SimpleDateFormat(formatString);
            }
            return format;
    }
    private String format(Object text){
        String formattedText=text.toString();
        if(format!=null){
            formattedText=format.format(text);
            if(!"".equals(unitName)){
                formattedText=formattedText+unitName;
            }
        }        
        return formattedText;
    }
    public Object getTextObj(){
    	return value;
    }
    private void init(){
        setFont(FontConstants.LABEL_TEXT_FONT);
        if(format instanceof DecimalFormat){
            setHorizontalAlignment(BaseLabel.RIGHT);            
        }else{
            setHorizontalAlignment(BaseLabel.LEFT);                        
        }
        setOpaque(true);
        setBackground(ColorConstants.DEFAULT_COMPONENT_BACKGROUND_COLOR);
        setPreferredSize(new Dimension(100,20));
        setForeground(Color.BLACK);
        setBorder(BorderFactory.createEmptyBorder());
        setVerticalAlignment(SwingConstants.CENTER);
    }
       
}
