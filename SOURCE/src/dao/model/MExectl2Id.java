package dao.model;

// Generated Sep 22, 2011 1:42:26 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * MExectl2Id generated by hbm2java
 */
@Embeddable
public class MExectl2Id implements java.io.Serializable {

	private String userid;
	private String menugrpCode;
	private String menuexeCode;

	public MExectl2Id() {
	}

	public MExectl2Id(String userid, String menugrpCode, String menuexeCode) {
		this.userid = userid;
		this.menugrpCode = menugrpCode;
		this.menuexeCode = menuexeCode;
	}

	@Column(name = "USERID", nullable = false, length = 10)
	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@Column(name = "MENUGRP_CODE", nullable = false, length = 3)
	public String getMenugrpCode() {
		return this.menugrpCode;
	}

	public void setMenugrpCode(String menugrpCode) {
		this.menugrpCode = menugrpCode;
	}

	@Column(name = "MENUEXE_CODE", nullable = false, length = 3)
	public String getMenuexeCode() {
		return this.menuexeCode;
	}

	public void setMenuexeCode(String menuexeCode) {
		this.menuexeCode = menuexeCode;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof MExectl2Id))
			return false;
		MExectl2Id castOther = (MExectl2Id) other;

		return ((this.getUserid() == castOther.getUserid()) || (this
				.getUserid() != null && castOther.getUserid() != null && this
				.getUserid().equals(castOther.getUserid())))
				&& ((this.getMenugrpCode() == castOther.getMenugrpCode()) || (this
						.getMenugrpCode() != null
						&& castOther.getMenugrpCode() != null && this
						.getMenugrpCode().equals(castOther.getMenugrpCode())))
				&& ((this.getMenuexeCode() == castOther.getMenuexeCode()) || (this
						.getMenuexeCode() != null
						&& castOther.getMenuexeCode() != null && this
						.getMenuexeCode().equals(castOther.getMenuexeCode())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUserid() == null ? 0 : this.getUserid().hashCode());
		result = 37
				* result
				+ (getMenugrpCode() == null ? 0 : this.getMenugrpCode()
						.hashCode());
		result = 37
				* result
				+ (getMenuexeCode() == null ? 0 : this.getMenuexeCode()
						.hashCode());
		return result;
	}

}
