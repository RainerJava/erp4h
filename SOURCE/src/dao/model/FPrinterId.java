package dao.model;

// Generated Sep 22, 2011 1:42:26 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * FPrinterId generated by hbm2java
 */
@Embeddable
public class FPrinterId implements java.io.Serializable {

	private String userid;
	private String menuCode;
	private String menusCode;
	private String outctl;

	public FPrinterId() {
	}

	public FPrinterId(String userid, String menuCode, String menusCode,
			String outctl) {
		this.userid = userid;
		this.menuCode = menuCode;
		this.menusCode = menusCode;
		this.outctl = outctl;
	}

	@Column(name = "USERID", nullable = false, length = 10)
	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@Column(name = "MENU_CODE", nullable = false, length = 3)
	public String getMenuCode() {
		return this.menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	@Column(name = "MENUS_CODE", nullable = false, length = 3)
	public String getMenusCode() {
		return this.menusCode;
	}

	public void setMenusCode(String menusCode) {
		this.menusCode = menusCode;
	}

	@Column(name = "OUTCTL", nullable = false, length = 2)
	public String getOutctl() {
		return this.outctl;
	}

	public void setOutctl(String outctl) {
		this.outctl = outctl;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof FPrinterId))
			return false;
		FPrinterId castOther = (FPrinterId) other;

		return ((this.getUserid() == castOther.getUserid()) || (this
				.getUserid() != null && castOther.getUserid() != null && this
				.getUserid().equals(castOther.getUserid())))
				&& ((this.getMenuCode() == castOther.getMenuCode()) || (this
						.getMenuCode() != null
						&& castOther.getMenuCode() != null && this
						.getMenuCode().equals(castOther.getMenuCode())))
				&& ((this.getMenusCode() == castOther.getMenusCode()) || (this
						.getMenusCode() != null
						&& castOther.getMenusCode() != null && this
						.getMenusCode().equals(castOther.getMenusCode())))
				&& ((this.getOutctl() == castOther.getOutctl()) || (this
						.getOutctl() != null && castOther.getOutctl() != null && this
						.getOutctl().equals(castOther.getOutctl())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUserid() == null ? 0 : this.getUserid().hashCode());
		result = 37 * result
				+ (getMenuCode() == null ? 0 : this.getMenuCode().hashCode());
		result = 37 * result
				+ (getMenusCode() == null ? 0 : this.getMenusCode().hashCode());
		result = 37 * result
				+ (getOutctl() == null ? 0 : this.getOutctl().hashCode());
		return result;
	}

}
