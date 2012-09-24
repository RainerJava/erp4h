package dao.model;

// Generated Sep 22, 2011 1:42:26 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * MClrctl generated by hbm2java
 */
@Entity
@Table(name = "M_CLRCTL", schema = "DB2ADMIN")
public class MClrctl implements java.io.Serializable {

	private MClrctlId id;
	private String clrName;
	private Short clrRed;
	private Short clrGreen;
	private Short clrBlue;
	private Short clrAlpha;
	private String clrHelp;
	private String addUser;
	private String addPc;
	private Integer addDate;
	private Integer addTime;
	private String lastupUser;
	private String lastupPc;
	private Integer lastupDate;
	private Integer lastupTime;

	public MClrctl() {
	}

	public MClrctl(MClrctlId id) {
		this.id = id;
	}

	public MClrctl(MClrctlId id, String clrName, Short clrRed, Short clrGreen,
			Short clrBlue, Short clrAlpha, String clrHelp, String addUser,
			String addPc, Integer addDate, Integer addTime, String lastupUser,
			String lastupPc, Integer lastupDate, Integer lastupTime) {
		this.id = id;
		this.clrName = clrName;
		this.clrRed = clrRed;
		this.clrGreen = clrGreen;
		this.clrBlue = clrBlue;
		this.clrAlpha = clrAlpha;
		this.clrHelp = clrHelp;
		this.addUser = addUser;
		this.addPc = addPc;
		this.addDate = addDate;
		this.addTime = addTime;
		this.lastupUser = lastupUser;
		this.lastupPc = lastupPc;
		this.lastupDate = lastupDate;
		this.lastupTime = lastupTime;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "clrKey", column = @Column(name = "CLR_KEY", nullable = false, length = 10)),
			@AttributeOverride(name = "userid", column = @Column(name = "USERID", nullable = false, length = 10)) })
	public MClrctlId getId() {
		return this.id;
	}

	public void setId(MClrctlId id) {
		this.id = id;
	}

	@Column(name = "CLR_NAME", length = 100)
	public String getClrName() {
		return this.clrName;
	}

	public void setClrName(String clrName) {
		this.clrName = clrName;
	}

	@Column(name = "CLR_RED", precision = 3, scale = 0)
	public Short getClrRed() {
		return this.clrRed;
	}

	public void setClrRed(Short clrRed) {
		this.clrRed = clrRed;
	}

	@Column(name = "CLR_GREEN", precision = 3, scale = 0)
	public Short getClrGreen() {
		return this.clrGreen;
	}

	public void setClrGreen(Short clrGreen) {
		this.clrGreen = clrGreen;
	}

	@Column(name = "CLR_BLUE", precision = 3, scale = 0)
	public Short getClrBlue() {
		return this.clrBlue;
	}

	public void setClrBlue(Short clrBlue) {
		this.clrBlue = clrBlue;
	}

	@Column(name = "CLR_ALPHA", precision = 3, scale = 0)
	public Short getClrAlpha() {
		return this.clrAlpha;
	}

	public void setClrAlpha(Short clrAlpha) {
		this.clrAlpha = clrAlpha;
	}

	@Column(name = "CLR_HELP", length = 100)
	public String getClrHelp() {
		return this.clrHelp;
	}

	public void setClrHelp(String clrHelp) {
		this.clrHelp = clrHelp;
	}

	@Column(name = "ADD_USER", length = 10)
	public String getAddUser() {
		return this.addUser;
	}

	public void setAddUser(String addUser) {
		this.addUser = addUser;
	}

	@Column(name = "ADD_PC", length = 30)
	public String getAddPc() {
		return this.addPc;
	}

	public void setAddPc(String addPc) {
		this.addPc = addPc;
	}

	@Column(name = "ADD_DATE", precision = 8, scale = 0)
	public Integer getAddDate() {
		return this.addDate;
	}

	public void setAddDate(Integer addDate) {
		this.addDate = addDate;
	}

	@Column(name = "ADD_TIME", precision = 6, scale = 0)
	public Integer getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Integer addTime) {
		this.addTime = addTime;
	}

	@Column(name = "LASTUP_USER", length = 10)
	public String getLastupUser() {
		return this.lastupUser;
	}

	public void setLastupUser(String lastupUser) {
		this.lastupUser = lastupUser;
	}

	@Column(name = "LASTUP_PC", length = 30)
	public String getLastupPc() {
		return this.lastupPc;
	}

	public void setLastupPc(String lastupPc) {
		this.lastupPc = lastupPc;
	}

	@Column(name = "LASTUP_DATE", precision = 8, scale = 0)
	public Integer getLastupDate() {
		return this.lastupDate;
	}

	public void setLastupDate(Integer lastupDate) {
		this.lastupDate = lastupDate;
	}

	@Column(name = "LASTUP_TIME", precision = 6, scale = 0)
	public Integer getLastupTime() {
		return this.lastupTime;
	}

	public void setLastupTime(Integer lastupTime) {
		this.lastupTime = lastupTime;
	}

}