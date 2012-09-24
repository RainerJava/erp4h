package dao.model;

// Generated Sep 22, 2011 1:42:26 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * MCtl generated by hbm2java
 */
@Entity
@Table(name = "M_CTL", schema = "DB2ADMIN")
public class MCtl implements java.io.Serializable {

	private MCtlId id;
	private String CName;
	private String CData;
	private String CHelp;
	private String CBm;
	private Character CDecbm;
	private String CAttr;
	private Character mtnFlg;
	private Character cntFlg;
	private Character updFlg;
	private String addUser;
	private String addPc;
	private Integer addDate;
	private Integer addTime;
	private String lastupUser;
	private String lastupPc;
	private Integer lastupDate;
	private Integer lastupTime;

	public MCtl() {
	}

	public MCtl(MCtlId id) {
		this.id = id;
	}

	public MCtl(MCtlId id, String CName, String CData, String CHelp,
			String CBm, Character CDecbm, String CAttr, Character mtnFlg,
			Character cntFlg, Character updFlg, String addUser, String addPc,
			Integer addDate, Integer addTime, String lastupUser,
			String lastupPc, Integer lastupDate, Integer lastupTime) {
		this.id = id;
		this.CName = CName;
		this.CData = CData;
		this.CHelp = CHelp;
		this.CBm = CBm;
		this.CDecbm = CDecbm;
		this.CAttr = CAttr;
		this.mtnFlg = mtnFlg;
		this.cntFlg = cntFlg;
		this.updFlg = updFlg;
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
			@AttributeOverride(name = "userid", column = @Column(name = "USERID", nullable = false, length = 10)),
			@AttributeOverride(name = "CKey", column = @Column(name = "C_KEY", nullable = false, length = 8)) })
	public MCtlId getId() {
		return this.id;
	}

	public void setId(MCtlId id) {
		this.id = id;
	}

	@Column(name = "C_NAME", length = 50)
	public String getCName() {
		return this.CName;
	}

	public void setCName(String CName) {
		this.CName = CName;
	}

	@Column(name = "C_DATA", length = 50)
	public String getCData() {
		return this.CData;
	}

	public void setCData(String CData) {
		this.CData = CData;
	}

	@Column(name = "C_HELP", length = 200)
	public String getCHelp() {
		return this.CHelp;
	}

	public void setCHelp(String CHelp) {
		this.CHelp = CHelp;
	}

	@Column(name = "C_BM", length = 3)
	public String getCBm() {
		return this.CBm;
	}

	public void setCBm(String CBm) {
		this.CBm = CBm;
	}

	@Column(name = "C_DECBM", length = 1)
	public Character getCDecbm() {
		return this.CDecbm;
	}

	public void setCDecbm(Character CDecbm) {
		this.CDecbm = CDecbm;
	}

	@Column(name = "C_ATTR", length = 10)
	public String getCAttr() {
		return this.CAttr;
	}

	public void setCAttr(String CAttr) {
		this.CAttr = CAttr;
	}

	@Column(name = "MTN_FLG", length = 1)
	public Character getMtnFlg() {
		return this.mtnFlg;
	}

	public void setMtnFlg(Character mtnFlg) {
		this.mtnFlg = mtnFlg;
	}

	@Column(name = "CNT_FLG", length = 1)
	public Character getCntFlg() {
		return this.cntFlg;
	}

	public void setCntFlg(Character cntFlg) {
		this.cntFlg = cntFlg;
	}

	@Column(name = "UPD_FLG", length = 1)
	public Character getUpdFlg() {
		return this.updFlg;
	}

	public void setUpdFlg(Character updFlg) {
		this.updFlg = updFlg;
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
