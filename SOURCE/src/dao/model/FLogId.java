package dao.model;

// Generated Sep 22, 2011 1:42:26 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * FLogId generated by hbm2java
 */
@Embeddable
public class FLogId implements java.io.Serializable {

	private String userUser;
	private int actDate;
	private int actTime;
	private String menugrpCode;
	private String menuexeCode;
	private String actionType;
	private String text;

	public FLogId() {
	}

	public FLogId(String userUser, int actDate, int actTime,
			String menugrpCode, String menuexeCode, String actionType,
			String text) {
		this.userUser = userUser;
		this.actDate = actDate;
		this.actTime = actTime;
		this.menugrpCode = menugrpCode;
		this.menuexeCode = menuexeCode;
		this.actionType = actionType;
		this.text = text;
	}

	@Column(name = "USER_USER", nullable = false, length = 10)
	public String getUserUser() {
		return this.userUser;
	}

	public void setUserUser(String userUser) {
		this.userUser = userUser;
	}

	@Column(name = "ACT_DATE", nullable = false, precision = 8, scale = 0)
	public int getActDate() {
		return this.actDate;
	}

	public void setActDate(int actDate) {
		this.actDate = actDate;
	}

	@Column(name = "ACT_TIME", nullable = false, precision = 8, scale = 0)
	public int getActTime() {
		return this.actTime;
	}

	public void setActTime(int actTime) {
		this.actTime = actTime;
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

	@Column(name = "ACTION_TYPE", nullable = false, length = 2)
	public String getActionType() {
		return this.actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	@Column(name = "TEXT", nullable = false, length = 100)
	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof FLogId))
			return false;
		FLogId castOther = (FLogId) other;

		return ((this.getUserUser() == castOther.getUserUser()) || (this
				.getUserUser() != null && castOther.getUserUser() != null && this
				.getUserUser().equals(castOther.getUserUser())))
				&& (this.getActDate() == castOther.getActDate())
				&& (this.getActTime() == castOther.getActTime())
				&& ((this.getMenugrpCode() == castOther.getMenugrpCode()) || (this
						.getMenugrpCode() != null
						&& castOther.getMenugrpCode() != null && this
						.getMenugrpCode().equals(castOther.getMenugrpCode())))
				&& ((this.getMenuexeCode() == castOther.getMenuexeCode()) || (this
						.getMenuexeCode() != null
						&& castOther.getMenuexeCode() != null && this
						.getMenuexeCode().equals(castOther.getMenuexeCode())))
				&& ((this.getActionType() == castOther.getActionType()) || (this
						.getActionType() != null
						&& castOther.getActionType() != null && this
						.getActionType().equals(castOther.getActionType())))
				&& ((this.getText() == castOther.getText()) || (this.getText() != null
						&& castOther.getText() != null && this.getText()
						.equals(castOther.getText())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUserUser() == null ? 0 : this.getUserUser().hashCode());
		result = 37 * result + this.getActDate();
		result = 37 * result + this.getActTime();
		result = 37
				* result
				+ (getMenugrpCode() == null ? 0 : this.getMenugrpCode()
						.hashCode());
		result = 37
				* result
				+ (getMenuexeCode() == null ? 0 : this.getMenuexeCode()
						.hashCode());
		result = 37
				* result
				+ (getActionType() == null ? 0 : this.getActionType()
						.hashCode());
		result = 37 * result
				+ (getText() == null ? 0 : this.getText().hashCode());
		return result;
	}

}