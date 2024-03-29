package dao.model;

// Generated Sep 17, 2011 5:49:33 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * MMenugrp generated by hbm2java
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "M_MENUGRP", schema = "DB2ADMIN")
public class MMenugrp implements java.io.Serializable {
	private String menugrpCode;
	private String menugrpName;
	private String menugrpHelp;
	private String menugrpSeq;

	public MMenugrp() {
	}

	public MMenugrp(String menugrpCode) {
		this.menugrpCode = menugrpCode;
	}

	public MMenugrp(String menugrpCode, String menugrpName, String menugrpHelp,
			String menugrpSeq) {
		this.menugrpCode = menugrpCode;
		this.menugrpName = menugrpName;
		this.menugrpHelp = menugrpHelp;
		this.menugrpSeq = menugrpSeq;
	}

	@Id
	@Column(name = "MENUGRP_CODE", unique = true, nullable = false, length = 3)
	public String getMenugrpCode() {
		return this.menugrpCode;
	}

	public void setMenugrpCode(String menugrpCode) {
		this.menugrpCode = menugrpCode;
	}

	@Column(name = "MENUGRP_NAME", length = 32)
	public String getMenugrpName() {
		return this.menugrpName;
	}

	public void setMenugrpName(String menugrpName) {
		this.menugrpName = menugrpName;
	}

	@Column(name = "MENUGRP_HELP", length = 100)
	public String getMenugrpHelp() {
		return this.menugrpHelp;
	}

	public void setMenugrpHelp(String menugrpHelp) {
		this.menugrpHelp = menugrpHelp;
	}

	@Column(name = "MENUGRP_SEQ", length = 3)
	public String getMenugrpSeq() {
		return this.menugrpSeq;
	}

	public void setMenugrpSeq(String menugrpSeq) {
		this.menugrpSeq = menugrpSeq;
	}

}
