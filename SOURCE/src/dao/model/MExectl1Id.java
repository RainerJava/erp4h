package dao.model;

// Generated Sep 22, 2011 1:42:26 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * MExectl1Id generated by hbm2java
 */
@Embeddable
public class MExectl1Id implements java.io.Serializable {

	private String userid;
	private String menugrpCode;

	public MExectl1Id() {
	}

	public MExectl1Id(String userid, String menugrpCode) {
		this.userid = userid;
		this.menugrpCode = menugrpCode;
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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof MExectl1Id))
			return false;
		MExectl1Id castOther = (MExectl1Id) other;

		return ((this.getUserid() == castOther.getUserid()) || (this
				.getUserid() != null && castOther.getUserid() != null && this
				.getUserid().equals(castOther.getUserid())))
				&& ((this.getMenugrpCode() == castOther.getMenugrpCode()) || (this
						.getMenugrpCode() != null
						&& castOther.getMenugrpCode() != null && this
						.getMenugrpCode().equals(castOther.getMenugrpCode())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUserid() == null ? 0 : this.getUserid().hashCode());
		result = 37
				* result
				+ (getMenugrpCode() == null ? 0 : this.getMenugrpCode()
						.hashCode());
		return result;
	}

}