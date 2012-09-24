/************************************************************************************
*
*	会社名		： 林兼コンピューター株式会社
*
*	プロジェクト名	： fas
*
*	ファイル名		： Pc01Vo.java
*
*	記述			：
*
*	作成日		：  2011/09/26  11:42:07 午前
*
*	作成者		： LENOVO-F23A3B72
*
*	備考			：
*
************************************************************************************/

package com.fas.vo.pc01;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.fas.common.utils.DateUtils;
import com.fas.common.utils.StringUtils;
import com.fas.vo.base.BaseVo;

/**
 * <DL>
 *   <DT>クラス記述：</DT>
 * 	 <DD></DD>
 * <BR>
 *   <DT>変更歴史：</DT>
 * 	 <DD>著作者: LENOVO-F23A3B72 </DD><BR>
 * 	 <DD></DD>
 * </DL>
**/
public class Pc01Vo extends BaseVo {

	/**	 */
	private static final long serialVersionUID = 1L;

	/** C0101 */
	private int c0101 = 0;
	/** C0102 */
	private int c0102 = 0;
	/** C0103 */
	private int c0103 = 0;
	/** C0104 */
	private String c0104 = "";
	/** C0105 */
	private int c0105 = 0;
	/** C0106 */
	private int c0106 = 0;
	/** C0107 */
	private int c0107 = 0;
	/** C0108 */
	private int c0108 = 0;
	/** C0109 */
	private int c0109 = 0;
	/** C0110 */
	private int c0110 = 0;
	/** C0111 */
	private int c0111 = 0;
	/** C0112 */
	private int c0112 = 0;
	/** C0113 */
	private int c0113 = 0;
	/** C0114 */
	private int c0114 = 0;
	/** C0115 */
	private int c0115 = 0;
	/** C0116 */
	private int c0116 = 0;
	/** C0117 */
	private int c0117 = 0;
	/** C0118 */
	private int c0118 = 0;
	/** C0119 */
	private int c0119 = 0;
	/** C0120 */
	private int c0120 = 0;
	/** C0121 */
	private int c0121 = 0;
	/** C0122 */
	private String c0122 = "";
	/** C0123 */
	private String c0123 = "";
	/** C0124 */
	private String c0124 = "";
	/** C0125 */
	private String c0125 = "";
	/** C0126 */
	private int c0126 = 0;
	/** C0127 */
	private String c0127 = "";
	/** C0128 */
	private String c0128 = "";
	/** C0129 */
	private Double c0129 = 0.0;
	/** C0130 */
	private Double c0130 = 0.0;
	/** C0131 */
	private Double c0131 = 0.0;
	/** C0132 */
	private Double c0132 = 0.0;
	/** C0133 */
	private Double c0133 = 0.0;
	/** C0134 */
	private Double c0134 = 0.0;
	/** C0135 */
	private Double c0135 = 0.0;
	/** C0136 */
	private Double c0136 = 0.0;
	/** C0137 */
	private Double c0137 = 0.0;
	/** C0138 */
	private Double c0138 = 0.0;
	/** C0139 */
	private Double c0139 = 0.0;
	/** C0140 */
	private Double c0140 = 0.0;
	/** C0141 */
	private Double c0141 = 0.0;
	/** C0142 */
	private Double c0142 = 0.0;
	/** C0143 */
	private Double c0143 = 0.0;
	/** C0144 */
	private Double c0144 = 0.0;
	/** C0145 */
	private Double c0145 = 0.0;
	/** C0146 */
	private Double c0146 = 0.0;
	/** C0147 */
	private Double c0147 = 0.0;
	/** C0148 */
	private Double c0148 = 0.0;
	/** C0149 */
	private Double c0149 = 0.0;
	/** C0150 */
	private Double c0150 = 0.0;
	/** C0151 */
	private Double c0151 = 0.0;
	/** C0152 */
	private Double c0152 = 0.0;
	/** C0153 */
	private Double c0153 = 0.0;
	/** C0154 */
	private Double c0154 = 0.0;
	/** C0155 */
	private Double c0155 = 0.0;
	/** C0156 */
	private Double c0156 = 0.0;
	/** C0157 */
	private Double c0157 = 0.0;
	/** C0158 */
	private Double c0158 = 0.0;
	/** C0159 */
	private Double c0159 = 0.0;
	/** C0160 */
	private Double c0160 = 0.0;
	/** C0161 */
	private Double c0161 = 0.0;
	/** C0162 */
	private Double c0162 = 0.0;
	/** C0163 */
	private Double c0163 = 0.0;
	/** C0164 */
	private Double c0164 = 0.0;
	/** C0165 */
	private Double c0165 = 0.0;
	/** C0166 */
	private Double c0166 = 0.0;
	/** C0167 */
	private Double c0167 = 0.0;
	/** C0168 */
	private int c0168 = 0;
	/** C0169 */
	private int c0169 = 0;
	/** C0170 */
	private int c0170 = 0;
	/** C0171 */
	private int c0171 = 0;
	/** C0172 */
	private int c0172 = 0;
	/** C0173 */
	private int c0173 = 0;
	/** C0174 */
	private int c0174 = 0;
	/** C0175 */
	private int c0175 = 0;
	/** C0176 */
	private int c0176 = 0;
	/** C0177 */
	private int c0177 = 0;
	/** C0178 */
	private int c0178 = 0;
	/** C0179 */
	private int c0179 = 0;
	/** C0180 */
	private String c0180 = "";
	/** C0181 */
	private String c0181 = "";
	/** C0182 */
	private String c0182 = "";
	/** C0183 */
	private int c0183 = 0;
	/** C0184 */
	private String c0184 = "";
	/** C0185 */
	private String c0185 = "";
	/** C0186 */
	private int c0186 = 0;
	/** C0187 */
	private int c0187 = 0;
	/** C0188 */
	private int c0188 = 0;
	/** C0189 */
	private int c0189 = 0;
	/** C0190 */
	private String c0190 = "";
	/** C0191 */
	private String c0191 = "";
	/** C01DM */
	private String c01dm = "";

	/** 
	* <DL>
	*   <DT>コンストラクター記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	*/
	public Pc01Vo(){
	}

	/* begin: Public Properties */
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0101
	*/
	public int getC0101() {
		return this.c0101;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0101
	*/
	public void setC0101(int c0101) {
		this.c0101 = c0101;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0102
	*/
	public int getC0102() {
		return this.c0102;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0102
	*/
	public void setC0102(int c0102) {
		this.c0102 = c0102;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0103
	*/
	public int getC0103() {
		return this.c0103;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0103
	*/
	public void setC0103(int c0103) {
		this.c0103 = c0103;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0104
	*/
	public String getC0104() {
		return this.c0104;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0104
	*/
	public void setC0104(String c0104) {
		this.c0104 = c0104;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0105
	*/
	public int getC0105() {
		return this.c0105;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0105
	*/
	public void setC0105(int c0105) {
		this.c0105 = c0105;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0106
	*/
	public int getC0106() {
		return this.c0106;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0106
	*/
	public void setC0106(int c0106) {
		this.c0106 = c0106;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0107
	*/
	public int getC0107() {
		return this.c0107;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0107
	*/
	public void setC0107(int c0107) {
		this.c0107 = c0107;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0108
	*/
	public int getC0108() {
		return this.c0108;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0108
	*/
	public void setC0108(int c0108) {
		this.c0108 = c0108;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0109
	*/
	public int getC0109() {
		return this.c0109;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0109
	*/
	public void setC0109(int c0109) {
		this.c0109 = c0109;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0110
	*/
	public int getC0110() {
		return this.c0110;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0110
	*/
	public void setC0110(int c0110) {
		this.c0110 = c0110;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0111
	*/
	public int getC0111() {
		return this.c0111;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0111
	*/
	public void setC0111(int c0111) {
		this.c0111 = c0111;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0112
	*/
	public int getC0112() {
		return this.c0112;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0112
	*/
	public void setC0112(int c0112) {
		this.c0112 = c0112;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0113
	*/
	public int getC0113() {
		return this.c0113;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0113
	*/
	public void setC0113(int c0113) {
		this.c0113 = c0113;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0114
	*/
	public int getC0114() {
		return this.c0114;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0114
	*/
	public void setC0114(int c0114) {
		this.c0114 = c0114;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0115
	*/
	public int getC0115() {
		return this.c0115;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0115
	*/
	public void setC0115(int c0115) {
		this.c0115 = c0115;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0116
	*/
	public int getC0116() {
		return this.c0116;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0116
	*/
	public void setC0116(int c0116) {
		this.c0116 = c0116;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0117
	*/
	public int getC0117() {
		return this.c0117;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0117
	*/
	public void setC0117(int c0117) {
		this.c0117 = c0117;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0118
	*/
	public int getC0118() {
		return this.c0118;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0118
	*/
	public void setC0118(int c0118) {
		this.c0118 = c0118;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0119
	*/
	public int getC0119() {
		return this.c0119;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0119
	*/
	public void setC0119(int c0119) {
		this.c0119 = c0119;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0120
	*/
	public int getC0120() {
		return this.c0120;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0120
	*/
	public void setC0120(int c0120) {
		this.c0120 = c0120;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0121
	*/
	public int getC0121() {
		return this.c0121;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0121
	*/
	public void setC0121(int c0121) {
		this.c0121 = c0121;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0122
	*/
	public String getC0122() {
		return this.c0122;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0122
	*/
	public void setC0122(String c0122) {
		this.c0122 = c0122;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0123
	*/
	public String getC0123() {
		return this.c0123;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0123
	*/
	public void setC0123(String c0123) {
		this.c0123 = c0123;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0124
	*/
	public String getC0124() {
		return this.c0124;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0124
	*/
	public void setC0124(String c0124) {
		this.c0124 = c0124;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0125
	*/
	public String getC0125() {
		return this.c0125;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0125
	*/
	public void setC0125(String c0125) {
		this.c0125 = c0125;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0126
	*/
	public int getC0126() {
		return this.c0126;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0126
	*/
	public void setC0126(int c0126) {
		this.c0126 = c0126;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0127
	*/
	public String getC0127() {
		return this.c0127;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0127
	*/
	public void setC0127(String c0127) {
		this.c0127 = c0127;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0128
	*/
	public String getC0128() {
		return this.c0128;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0128
	*/
	public void setC0128(String c0128) {
		this.c0128 = c0128;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0129
	*/
	public Double getC0129() {
		return this.c0129;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0129
	*/
	public void setC0129(Double c0129) {
		this.c0129 = c0129;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0130
	*/
	public Double getC0130() {
		return this.c0130;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0130
	*/
	public void setC0130(Double c0130) {
		this.c0130 = c0130;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0131
	*/
	public Double getC0131() {
		return this.c0131;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0131
	*/
	public void setC0131(Double c0131) {
		this.c0131 = c0131;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0132
	*/
	public Double getC0132() {
		return this.c0132;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0132
	*/
	public void setC0132(Double c0132) {
		this.c0132 = c0132;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0133
	*/
	public Double getC0133() {
		return this.c0133;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0133
	*/
	public void setC0133(Double c0133) {
		this.c0133 = c0133;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0134
	*/
	public Double getC0134() {
		return this.c0134;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0134
	*/
	public void setC0134(Double c0134) {
		this.c0134 = c0134;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0135
	*/
	public Double getC0135() {
		return this.c0135;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0135
	*/
	public void setC0135(Double c0135) {
		this.c0135 = c0135;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0136
	*/
	public Double getC0136() {
		return this.c0136;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0136
	*/
	public void setC0136(Double c0136) {
		this.c0136 = c0136;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0137
	*/
	public Double getC0137() {
		return this.c0137;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0137
	*/
	public void setC0137(Double c0137) {
		this.c0137 = c0137;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0138
	*/
	public Double getC0138() {
		return this.c0138;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0138
	*/
	public void setC0138(Double c0138) {
		this.c0138 = c0138;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0139
	*/
	public Double getC0139() {
		return this.c0139;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0139
	*/
	public void setC0139(Double c0139) {
		this.c0139 = c0139;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0140
	*/
	public Double getC0140() {
		return this.c0140;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0140
	*/
	public void setC0140(Double c0140) {
		this.c0140 = c0140;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0141
	*/
	public Double getC0141() {
		return this.c0141;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0141
	*/
	public void setC0141(Double c0141) {
		this.c0141 = c0141;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0142
	*/
	public Double getC0142() {
		return this.c0142;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0142
	*/
	public void setC0142(Double c0142) {
		this.c0142 = c0142;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0143
	*/
	public Double getC0143() {
		return this.c0143;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0143
	*/
	public void setC0143(Double c0143) {
		this.c0143 = c0143;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0144
	*/
	public Double getC0144() {
		return this.c0144;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0144
	*/
	public void setC0144(Double c0144) {
		this.c0144 = c0144;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0145
	*/
	public Double getC0145() {
		return this.c0145;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0145
	*/
	public void setC0145(Double c0145) {
		this.c0145 = c0145;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0146
	*/
	public Double getC0146() {
		return this.c0146;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0146
	*/
	public void setC0146(Double c0146) {
		this.c0146 = c0146;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0147
	*/
	public Double getC0147() {
		return this.c0147;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0147
	*/
	public void setC0147(Double c0147) {
		this.c0147 = c0147;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0148
	*/
	public Double getC0148() {
		return this.c0148;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0148
	*/
	public void setC0148(Double c0148) {
		this.c0148 = c0148;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0149
	*/
	public Double getC0149() {
		return this.c0149;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0149
	*/
	public void setC0149(Double c0149) {
		this.c0149 = c0149;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0150
	*/
	public Double getC0150() {
		return this.c0150;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0150
	*/
	public void setC0150(Double c0150) {
		this.c0150 = c0150;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0151
	*/
	public Double getC0151() {
		return this.c0151;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0151
	*/
	public void setC0151(Double c0151) {
		this.c0151 = c0151;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0152
	*/
	public Double getC0152() {
		return this.c0152;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0152
	*/
	public void setC0152(Double c0152) {
		this.c0152 = c0152;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0153
	*/
	public Double getC0153() {
		return this.c0153;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0153
	*/
	public void setC0153(Double c0153) {
		this.c0153 = c0153;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0154
	*/
	public Double getC0154() {
		return this.c0154;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0154
	*/
	public void setC0154(Double c0154) {
		this.c0154 = c0154;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0155
	*/
	public Double getC0155() {
		return this.c0155;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0155
	*/
	public void setC0155(Double c0155) {
		this.c0155 = c0155;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0156
	*/
	public Double getC0156() {
		return this.c0156;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0156
	*/
	public void setC0156(Double c0156) {
		this.c0156 = c0156;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0157
	*/
	public Double getC0157() {
		return this.c0157;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0157
	*/
	public void setC0157(Double c0157) {
		this.c0157 = c0157;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0158
	*/
	public Double getC0158() {
		return this.c0158;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0158
	*/
	public void setC0158(Double c0158) {
		this.c0158 = c0158;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0159
	*/
	public Double getC0159() {
		return this.c0159;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0159
	*/
	public void setC0159(Double c0159) {
		this.c0159 = c0159;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0160
	*/
	public Double getC0160() {
		return this.c0160;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0160
	*/
	public void setC0160(Double c0160) {
		this.c0160 = c0160;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0161
	*/
	public Double getC0161() {
		return this.c0161;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0161
	*/
	public void setC0161(Double c0161) {
		this.c0161 = c0161;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0162
	*/
	public Double getC0162() {
		return this.c0162;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0162
	*/
	public void setC0162(Double c0162) {
		this.c0162 = c0162;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0163
	*/
	public Double getC0163() {
		return this.c0163;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0163
	*/
	public void setC0163(Double c0163) {
		this.c0163 = c0163;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0164
	*/
	public Double getC0164() {
		return this.c0164;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0164
	*/
	public void setC0164(Double c0164) {
		this.c0164 = c0164;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0165
	*/
	public Double getC0165() {
		return this.c0165;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0165
	*/
	public void setC0165(Double c0165) {
		this.c0165 = c0165;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0166
	*/
	public Double getC0166() {
		return this.c0166;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0166
	*/
	public void setC0166(Double c0166) {
		this.c0166 = c0166;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0167
	*/
	public Double getC0167() {
		return this.c0167;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0167
	*/
	public void setC0167(Double c0167) {
		this.c0167 = c0167;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0168
	*/
	public int getC0168() {
		return this.c0168;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0168
	*/
	public void setC0168(int c0168) {
		this.c0168 = c0168;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0169
	*/
	public int getC0169() {
		return this.c0169;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0169
	*/
	public void setC0169(int c0169) {
		this.c0169 = c0169;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0170
	*/
	public int getC0170() {
		return this.c0170;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0170
	*/
	public void setC0170(int c0170) {
		this.c0170 = c0170;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0171
	*/
	public int getC0171() {
		return this.c0171;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0171
	*/
	public void setC0171(int c0171) {
		this.c0171 = c0171;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0172
	*/
	public int getC0172() {
		return this.c0172;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0172
	*/
	public void setC0172(int c0172) {
		this.c0172 = c0172;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0173
	*/
	public int getC0173() {
		return this.c0173;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0173
	*/
	public void setC0173(int c0173) {
		this.c0173 = c0173;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0174
	*/
	public int getC0174() {
		return this.c0174;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0174
	*/
	public void setC0174(int c0174) {
		this.c0174 = c0174;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0175
	*/
	public int getC0175() {
		return this.c0175;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0175
	*/
	public void setC0175(int c0175) {
		this.c0175 = c0175;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0176
	*/
	public int getC0176() {
		return this.c0176;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0176
	*/
	public void setC0176(int c0176) {
		this.c0176 = c0176;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0177
	*/
	public int getC0177() {
		return this.c0177;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0177
	*/
	public void setC0177(int c0177) {
		this.c0177 = c0177;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0178
	*/
	public int getC0178() {
		return this.c0178;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0178
	*/
	public void setC0178(int c0178) {
		this.c0178 = c0178;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0179
	*/
	public int getC0179() {
		return this.c0179;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0179
	*/
	public void setC0179(int c0179) {
		this.c0179 = c0179;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0180
	*/
	public String getC0180() {
		return this.c0180;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0180
	*/
	public void setC0180(String c0180) {
		this.c0180 = c0180;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0181
	*/
	public String getC0181() {
		return this.c0181;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0181
	*/
	public void setC0181(String c0181) {
		this.c0181 = c0181;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0182
	*/
	public String getC0182() {
		return this.c0182;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0182
	*/
	public void setC0182(String c0182) {
		this.c0182 = c0182;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0183
	*/
	public int getC0183() {
		return this.c0183;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0183
	*/
	public void setC0183(int c0183) {
		this.c0183 = c0183;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0184
	*/
	public String getC0184() {
		return this.c0184;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0184
	*/
	public void setC0184(String c0184) {
		this.c0184 = c0184;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0185
	*/
	public String getC0185() {
		return this.c0185;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0185
	*/
	public void setC0185(String c0185) {
		this.c0185 = c0185;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0186
	*/
	public int getC0186() {
		return this.c0186;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0186
	*/
	public void setC0186(int c0186) {
		this.c0186 = c0186;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0187
	*/
	public int getC0187() {
		return this.c0187;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0187
	*/
	public void setC0187(int c0187) {
		this.c0187 = c0187;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0188
	*/
	public int getC0188() {
		return this.c0188;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0188
	*/
	public void setC0188(int c0188) {
		this.c0188 = c0188;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0189
	*/
	public int getC0189() {
		return this.c0189;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0189
	*/
	public void setC0189(int c0189) {
		this.c0189 = c0189;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0190
	*/
	public String getC0190() {
		return this.c0190;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0190
	*/
	public void setC0190(String c0190) {
		this.c0190 = c0190;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c0191
	*/
	public String getC0191() {
		return this.c0191;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c0191
	*/
	public void setC0191(String c0191) {
		this.c0191 = c0191;
	}

	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @return the c01dm
	*/
	public String getC01dm() {
		return this.c01dm;
	}
	/**
	* <DL>
	*   <DT>メソッド記述：</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param c01dm
	*/
	public void setC01dm(String c01dm) {
		this.c01dm = c01dm;
	}

	/* end: Public Properties */
	/**
	* <DL>
	*   <DT>メソッド記述： get list key to log</DT>
	* 	 <DD></DD>
	* <BR>
	*
	* </DL>	 
	* @return String
	*/
	public String getKeyToLog() {
		return "C0101: " + this.c0101 + "; ";
	}

	/**
	* <DL>
	*   <DT>メソッド記述</DT>
	* 		<DD></DD>
	* <BR>
	*
	* </DL>
	* @param rs
	* @return
	*/
	public void getFromSQLServerResultSet(ResultSet rs) throws SQLException {
		this.setC0101(rs.getInt("C0101"));
		this.setC0102(rs.getInt("C0102"));
		this.setC0103(rs.getInt("C0103"));
		this.setC0104(StringUtils.nulToString(rs.getString("C0104")));
		this.setC0105(rs.getInt("C0105"));
		this.setC0106(rs.getInt("C0106"));
		this.setC0107(rs.getInt("C0107"));
		this.setC0108(rs.getInt("C0108"));
		this.setC0109(rs.getInt("C0109"));
		this.setC0110(rs.getInt("C0110"));
		this.setC0111(rs.getInt("C0111"));
		this.setC0112(rs.getInt("C0112"));
		this.setC0113(rs.getInt("C0113"));
		this.setC0114(rs.getInt("C0114"));
		this.setC0115(rs.getInt("C0115"));
		this.setC0116(rs.getInt("C0116"));
		this.setC0117(rs.getInt("C0117"));
		this.setC0118(rs.getInt("C0118"));
		this.setC0119(rs.getInt("C0119"));
		this.setC0120(rs.getInt("C0120"));
		this.setC0121(rs.getInt("C0121"));
		this.setC0122(StringUtils.nulToString(rs.getString("C0122")));
		this.setC0123(StringUtils.nulToString(rs.getString("C0123")));
		this.setC0124(StringUtils.nulToString(rs.getString("C0124")));
		this.setC0125(StringUtils.nulToString(rs.getString("C0125")));
		this.setC0126(rs.getInt("C0126"));
		this.setC0127(StringUtils.nulToString(rs.getString("C0127")));
		this.setC0128(StringUtils.nulToString(rs.getString("C0128")));
		this.setC0129(rs.getDouble("C0129"));
		this.setC0130(rs.getDouble("C0130"));
		this.setC0131(rs.getDouble("C0131"));
		this.setC0132(rs.getDouble("C0132"));
		this.setC0133(rs.getDouble("C0133"));
		this.setC0134(rs.getDouble("C0134"));
		this.setC0135(rs.getDouble("C0135"));
		this.setC0136(rs.getDouble("C0136"));
		this.setC0137(rs.getDouble("C0137"));
		this.setC0138(rs.getDouble("C0138"));
		this.setC0139(rs.getDouble("C0139"));
		this.setC0140(rs.getDouble("C0140"));
		this.setC0141(rs.getDouble("C0141"));
		this.setC0142(rs.getDouble("C0142"));
		this.setC0143(rs.getDouble("C0143"));
		this.setC0144(rs.getDouble("C0144"));
		this.setC0145(rs.getDouble("C0145"));
		this.setC0146(rs.getDouble("C0146"));
		this.setC0147(rs.getDouble("C0147"));
		this.setC0148(rs.getDouble("C0148"));
		this.setC0149(rs.getDouble("C0149"));
		this.setC0150(rs.getDouble("C0150"));
		this.setC0151(rs.getDouble("C0151"));
		this.setC0152(rs.getDouble("C0152"));
		this.setC0153(rs.getDouble("C0153"));
		this.setC0154(rs.getDouble("C0154"));
		this.setC0155(rs.getDouble("C0155"));
		this.setC0156(rs.getDouble("C0156"));
		this.setC0157(rs.getDouble("C0157"));
		this.setC0158(rs.getDouble("C0158"));
		this.setC0159(rs.getDouble("C0159"));
		this.setC0160(rs.getDouble("C0160"));
		this.setC0161(rs.getDouble("C0161"));
		this.setC0162(rs.getDouble("C0162"));
		this.setC0163(rs.getDouble("C0163"));
		this.setC0164(rs.getDouble("C0164"));
		this.setC0165(rs.getDouble("C0165"));
		this.setC0166(rs.getDouble("C0166"));
		this.setC0167(rs.getDouble("C0167"));
		this.setC0168(rs.getInt("C0168"));
		this.setC0169(rs.getInt("C0169"));
		this.setC0170(rs.getInt("C0170"));
		this.setC0171(rs.getInt("C0171"));
		this.setC0172(rs.getInt("C0172"));
		this.setC0173(rs.getInt("C0173"));
		this.setC0174(rs.getInt("C0174"));
		this.setC0175(rs.getInt("C0175"));
		this.setC0176(rs.getInt("C0176"));
		this.setC0177(rs.getInt("C0177"));
		this.setC0178(rs.getInt("C0178"));
		this.setC0179(rs.getInt("C0179"));
		this.setC0180(StringUtils.nulToString(rs.getString("C0180")));
		this.setC0181(StringUtils.nulToString(rs.getString("C0181")));
		this.setC0182(StringUtils.nulToString(rs.getString("C0182")));
		this.setC0183(rs.getInt("C0183"));
		this.setC0184(StringUtils.nulToString(rs.getString("C0184")));
		this.setC0185(StringUtils.nulToString(rs.getString("C0185")));
		this.setC0186(rs.getInt("C0186"));
		this.setC0187(rs.getInt("C0187"));
		this.setC0188(rs.getInt("C0188"));
		this.setC0189(rs.getInt("C0189"));
		this.setC0190(StringUtils.nulToString(rs.getString("C0190")));
		this.setC0191(StringUtils.nulToString(rs.getString("C0191")));
		this.setC01dm(StringUtils.nulToString(rs.getString("C01DM")));
	}
	
	static public boolean equal(Pc01Vo dataVo1, Pc01Vo dataVo2) {
		if (dataVo1 == null && dataVo2 == null) return true;
		if (dataVo1 == null || dataVo2 == null) return false;

		if(dataVo1.getC0101() != dataVo2.getC0101())
			return false;
		if(dataVo1.getC0102() != dataVo2.getC0102())
			return false;
		if(dataVo1.getC0103() != dataVo2.getC0103())
			return false;
		if(!dataVo1.getC0104().trim().equals(dataVo2.getC0104().trim()))
			return false;
		if(dataVo1.getC0105() != dataVo2.getC0105())
			return false;
		if(dataVo1.getC0106() != dataVo2.getC0106())
			return false;
		if(dataVo1.getC0107() != dataVo2.getC0107())
			return false;
		if(dataVo1.getC0108() != dataVo2.getC0108())
			return false;
		if(dataVo1.getC0109() != dataVo2.getC0109())
			return false;
		if(dataVo1.getC0110() != dataVo2.getC0110())
			return false;
		if(dataVo1.getC0111() != dataVo2.getC0111())
			return false;
		if(dataVo1.getC0112() != dataVo2.getC0112())
			return false;
		if(dataVo1.getC0113() != dataVo2.getC0113())
			return false;
		if(dataVo1.getC0114() != dataVo2.getC0114())
			return false;
		if(dataVo1.getC0115() != dataVo2.getC0115())
			return false;
		if(dataVo1.getC0116() != dataVo2.getC0116())
			return false;
		if(dataVo1.getC0117() != dataVo2.getC0117())
			return false;
		if(dataVo1.getC0118() != dataVo2.getC0118())
			return false;
		if(dataVo1.getC0119() != dataVo2.getC0119())
			return false;
		if(dataVo1.getC0120() != dataVo2.getC0120())
			return false;
		if(dataVo1.getC0121() != dataVo2.getC0121())
			return false;
		if(!dataVo1.getC0122().trim().equals(dataVo2.getC0122().trim()))
			return false;
		if(!dataVo1.getC0123().trim().equals(dataVo2.getC0123().trim()))
			return false;
		if(!dataVo1.getC0124().trim().equals(dataVo2.getC0124().trim()))
			return false;
		if(!dataVo1.getC0125().trim().equals(dataVo2.getC0125().trim()))
			return false;
		if(dataVo1.getC0126() != dataVo2.getC0126())
			return false;
		if(!dataVo1.getC0127().trim().equals(dataVo2.getC0127().trim()))
			return false;
		if(!dataVo1.getC0128().trim().equals(dataVo2.getC0128().trim()))
			return false;
		if(dataVo1.getC0129() != dataVo2.getC0129())
			return false;
		if(dataVo1.getC0130() != dataVo2.getC0130())
			return false;
		if(dataVo1.getC0131() != dataVo2.getC0131())
			return false;
		if(dataVo1.getC0132() != dataVo2.getC0132())
			return false;
		if(dataVo1.getC0133() != dataVo2.getC0133())
			return false;
		if(dataVo1.getC0134() != dataVo2.getC0134())
			return false;
		if(dataVo1.getC0135() != dataVo2.getC0135())
			return false;
		if(dataVo1.getC0136() != dataVo2.getC0136())
			return false;
		if(dataVo1.getC0137() != dataVo2.getC0137())
			return false;
		if(dataVo1.getC0138() != dataVo2.getC0138())
			return false;
		if(dataVo1.getC0139() != dataVo2.getC0139())
			return false;
		if(dataVo1.getC0140() != dataVo2.getC0140())
			return false;
		if(dataVo1.getC0141() != dataVo2.getC0141())
			return false;
		if(dataVo1.getC0142() != dataVo2.getC0142())
			return false;
		if(dataVo1.getC0143() != dataVo2.getC0143())
			return false;
		if(dataVo1.getC0144() != dataVo2.getC0144())
			return false;
		if(dataVo1.getC0145() != dataVo2.getC0145())
			return false;
		if(dataVo1.getC0146() != dataVo2.getC0146())
			return false;
		if(dataVo1.getC0147() != dataVo2.getC0147())
			return false;
		if(dataVo1.getC0148() != dataVo2.getC0148())
			return false;
		if(dataVo1.getC0149() != dataVo2.getC0149())
			return false;
		if(dataVo1.getC0150() != dataVo2.getC0150())
			return false;
		if(dataVo1.getC0151() != dataVo2.getC0151())
			return false;
		if(dataVo1.getC0152() != dataVo2.getC0152())
			return false;
		if(dataVo1.getC0153() != dataVo2.getC0153())
			return false;
		if(dataVo1.getC0154() != dataVo2.getC0154())
			return false;
		if(dataVo1.getC0155() != dataVo2.getC0155())
			return false;
		if(dataVo1.getC0156() != dataVo2.getC0156())
			return false;
		if(dataVo1.getC0157() != dataVo2.getC0157())
			return false;
		if(dataVo1.getC0158() != dataVo2.getC0158())
			return false;
		if(dataVo1.getC0159() != dataVo2.getC0159())
			return false;
		if(dataVo1.getC0160() != dataVo2.getC0160())
			return false;
		if(dataVo1.getC0161() != dataVo2.getC0161())
			return false;
		if(dataVo1.getC0162() != dataVo2.getC0162())
			return false;
		if(dataVo1.getC0163() != dataVo2.getC0163())
			return false;
		if(dataVo1.getC0164() != dataVo2.getC0164())
			return false;
		if(dataVo1.getC0165() != dataVo2.getC0165())
			return false;
		if(dataVo1.getC0166() != dataVo2.getC0166())
			return false;
		if(dataVo1.getC0167() != dataVo2.getC0167())
			return false;
		if(dataVo1.getC0168() != dataVo2.getC0168())
			return false;
		if(dataVo1.getC0169() != dataVo2.getC0169())
			return false;
		if(dataVo1.getC0170() != dataVo2.getC0170())
			return false;
		if(dataVo1.getC0171() != dataVo2.getC0171())
			return false;
		if(dataVo1.getC0172() != dataVo2.getC0172())
			return false;
		if(dataVo1.getC0173() != dataVo2.getC0173())
			return false;
		if(dataVo1.getC0174() != dataVo2.getC0174())
			return false;
		if(dataVo1.getC0175() != dataVo2.getC0175())
			return false;
		if(dataVo1.getC0176() != dataVo2.getC0176())
			return false;
		if(dataVo1.getC0177() != dataVo2.getC0177())
			return false;
		if(dataVo1.getC0178() != dataVo2.getC0178())
			return false;
		if(dataVo1.getC0179() != dataVo2.getC0179())
			return false;
		if(!dataVo1.getC0180().trim().equals(dataVo2.getC0180().trim()))
			return false;
		if(!dataVo1.getC0181().trim().equals(dataVo2.getC0181().trim()))
			return false;
		if(!dataVo1.getC0182().trim().equals(dataVo2.getC0182().trim()))
			return false;
		if(dataVo1.getC0183() != dataVo2.getC0183())
			return false;
		if(!dataVo1.getC0184().trim().equals(dataVo2.getC0184().trim()))
			return false;
		if(!dataVo1.getC0185().trim().equals(dataVo2.getC0185().trim()))
			return false;
		if(dataVo1.getC0186() != dataVo2.getC0186())
			return false;
		if(dataVo1.getC0187() != dataVo2.getC0187())
			return false;
		if(dataVo1.getC0188() != dataVo2.getC0188())
			return false;
		if(dataVo1.getC0189() != dataVo2.getC0189())
			return false;
		if(!dataVo1.getC0190().trim().equals(dataVo2.getC0190().trim()))
			return false;
		if(!dataVo1.getC0191().trim().equals(dataVo2.getC0191().trim()))
			return false;
		if(!dataVo1.getC01dm().trim().equals(dataVo2.getC01dm().trim()))
			return false;
		return true;
	}
}