package dao.model;

// Generated Sep 22, 2011 1:42:26 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * FPrinter generated by hbm2java
 */
@Entity
@Table(name = "F_PRINTER", schema = "DB2ADMIN")
public class FPrinter implements java.io.Serializable {

	private FPrinterId id;
	private String prtid;

	public FPrinter() {
	}

	public FPrinter(FPrinterId id) {
		this.id = id;
	}

	public FPrinter(FPrinterId id, String prtid) {
		this.id = id;
		this.prtid = prtid;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "userid", column = @Column(name = "USERID", nullable = false, length = 10)),
			@AttributeOverride(name = "menuCode", column = @Column(name = "MENU_CODE", nullable = false, length = 3)),
			@AttributeOverride(name = "menusCode", column = @Column(name = "MENUS_CODE", nullable = false, length = 3)),
			@AttributeOverride(name = "outctl", column = @Column(name = "OUTCTL", nullable = false, length = 2)) })
	public FPrinterId getId() {
		return this.id;
	}

	public void setId(FPrinterId id) {
		this.id = id;
	}

	@Column(name = "PRTID", length = 100)
	public String getPrtid() {
		return this.prtid;
	}

	public void setPrtid(String prtid) {
		this.prtid = prtid;
	}

}
