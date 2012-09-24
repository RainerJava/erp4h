package dao.model;

// Generated Sep 22, 2011 1:42:26 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * MMymenu generated by hbm2java
 */
@Entity
@Table(name = "M_MYMENU", schema = "DB2ADMIN")
public class MMymenu implements java.io.Serializable {

	private MMymenuId id;
	private String menugrpCode;
	private String menuexeCode;
	private String menuexeName;
	private String menuexeHelp;
	private Character funType;
	private String className;
	private String pathName;

	public MMymenu() {
	}

	public MMymenu(MMymenuId id) {
		this.id = id;
	}

	public MMymenu(MMymenuId id, String menugrpCode, String menuexeCode,
			String menuexeName, String menuexeHelp, Character funType,
			String className, String pathName) {
		this.id = id;
		this.menugrpCode = menugrpCode;
		this.menuexeCode = menuexeCode;
		this.menuexeName = menuexeName;
		this.menuexeHelp = menuexeHelp;
		this.funType = funType;
		this.className = className;
		this.pathName = pathName;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "userid", column = @Column(name = "USERID", nullable = false, length = 10)),
			@AttributeOverride(name = "menuexeSeq", column = @Column(name = "MENUEXE_SEQ", nullable = false, length = 3)) })
	public MMymenuId getId() {
		return this.id;
	}

	public void setId(MMymenuId id) {
		this.id = id;
	}

	@Column(name = "MENUGRP_CODE", length = 3)
	public String getMenugrpCode() {
		return this.menugrpCode;
	}

	public void setMenugrpCode(String menugrpCode) {
		this.menugrpCode = menugrpCode;
	}

	@Column(name = "MENUEXE_CODE", length = 3)
	public String getMenuexeCode() {
		return this.menuexeCode;
	}

	public void setMenuexeCode(String menuexeCode) {
		this.menuexeCode = menuexeCode;
	}

	@Column(name = "MENUEXE_NAME", length = 42)
	public String getMenuexeName() {
		return this.menuexeName;
	}

	public void setMenuexeName(String menuexeName) {
		this.menuexeName = menuexeName;
	}

	@Column(name = "MENUEXE_HELP", length = 300)
	public String getMenuexeHelp() {
		return this.menuexeHelp;
	}

	public void setMenuexeHelp(String menuexeHelp) {
		this.menuexeHelp = menuexeHelp;
	}

	@Column(name = "FUN_TYPE", length = 1)
	public Character getFunType() {
		return this.funType;
	}

	public void setFunType(Character funType) {
		this.funType = funType;
	}

	@Column(name = "CLASS_NAME", length = 200)
	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	@Column(name = "PATH_NAME", length = 200)
	public String getPathName() {
		return this.pathName;
	}

	public void setPathName(String pathName) {
		this.pathName = pathName;
	}

}
