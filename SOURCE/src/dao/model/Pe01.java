package dao.model;

// Generated Sep 22, 2011 1:42:26 PM by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Pe01 generated by hbm2java
 */
@Entity
@Table(name = "PE01", schema = "DB2ADMIN")
public class Pe01 implements java.io.Serializable {

	private short e0101;
	private Boolean e0102;
	private Byte e0103;
	private String e0104;
	private String e0105;
	private Short e0106;
	private Short e0107;
	private BigDecimal e0108;
	private BigDecimal e0109;
	private Boolean e0110;
	private Boolean e0111;
	private Boolean e0112;
	private Integer e0113;
	private Integer e0114;
	private Integer e0115;
	private Byte e0116;
	private String e01dm;

	public Pe01() {
	}

	public Pe01(short e0101) {
		this.e0101 = e0101;
	}

	public Pe01(short e0101, Boolean e0102, Byte e0103, String e0104,
			String e0105, Short e0106, Short e0107, BigDecimal e0108,
			BigDecimal e0109, Boolean e0110, Boolean e0111, Boolean e0112,
			Integer e0113, Integer e0114, Integer e0115, Byte e0116,
			String e01dm) {
		this.e0101 = e0101;
		this.e0102 = e0102;
		this.e0103 = e0103;
		this.e0104 = e0104;
		this.e0105 = e0105;
		this.e0106 = e0106;
		this.e0107 = e0107;
		this.e0108 = e0108;
		this.e0109 = e0109;
		this.e0110 = e0110;
		this.e0111 = e0111;
		this.e0112 = e0112;
		this.e0113 = e0113;
		this.e0114 = e0114;
		this.e0115 = e0115;
		this.e0116 = e0116;
		this.e01dm = e01dm;
	}

	@Id
	@Column(name = "E0101", unique = true, nullable = false, precision = 4, scale = 0)
	public short getE0101() {
		return this.e0101;
	}

	public void setE0101(short e0101) {
		this.e0101 = e0101;
	}

	@Column(name = "E0102", precision = 1, scale = 0)
	public Boolean getE0102() {
		return this.e0102;
	}

	public void setE0102(Boolean e0102) {
		this.e0102 = e0102;
	}

	@Column(name = "E0103", precision = 2, scale = 0)
	public Byte getE0103() {
		return this.e0103;
	}

	public void setE0103(Byte e0103) {
		this.e0103 = e0103;
	}

	@Column(name = "E0104", length = 32)
	public String getE0104() {
		return this.e0104;
	}

	public void setE0104(String e0104) {
		this.e0104 = e0104;
	}

	@Column(name = "E0105", length = 20)
	public String getE0105() {
		return this.e0105;
	}

	public void setE0105(String e0105) {
		this.e0105 = e0105;
	}

	@Column(name = "E0106", precision = 4, scale = 0)
	public Short getE0106() {
		return this.e0106;
	}

	public void setE0106(Short e0106) {
		this.e0106 = e0106;
	}

	@Column(name = "E0107", precision = 4, scale = 0)
	public Short getE0107() {
		return this.e0107;
	}

	public void setE0107(Short e0107) {
		this.e0107 = e0107;
	}

	@Column(name = "E0108", precision = 8)
	public BigDecimal getE0108() {
		return this.e0108;
	}

	public void setE0108(BigDecimal e0108) {
		this.e0108 = e0108;
	}

	@Column(name = "E0109", precision = 8)
	public BigDecimal getE0109() {
		return this.e0109;
	}

	public void setE0109(BigDecimal e0109) {
		this.e0109 = e0109;
	}

	@Column(name = "E0110", precision = 1, scale = 0)
	public Boolean getE0110() {
		return this.e0110;
	}

	public void setE0110(Boolean e0110) {
		this.e0110 = e0110;
	}

	@Column(name = "E0111", precision = 1, scale = 0)
	public Boolean getE0111() {
		return this.e0111;
	}

	public void setE0111(Boolean e0111) {
		this.e0111 = e0111;
	}

	@Column(name = "E0112", precision = 1, scale = 0)
	public Boolean getE0112() {
		return this.e0112;
	}

	public void setE0112(Boolean e0112) {
		this.e0112 = e0112;
	}

	@Column(name = "E0113", precision = 6, scale = 0)
	public Integer getE0113() {
		return this.e0113;
	}

	public void setE0113(Integer e0113) {
		this.e0113 = e0113;
	}

	@Column(name = "E0114", precision = 6, scale = 0)
	public Integer getE0114() {
		return this.e0114;
	}

	public void setE0114(Integer e0114) {
		this.e0114 = e0114;
	}

	@Column(name = "E0115", precision = 6, scale = 0)
	public Integer getE0115() {
		return this.e0115;
	}

	public void setE0115(Integer e0115) {
		this.e0115 = e0115;
	}

	@Column(name = "E0116", precision = 2, scale = 0)
	public Byte getE0116() {
		return this.e0116;
	}

	public void setE0116(Byte e0116) {
		this.e0116 = e0116;
	}

	@Column(name = "E01DM", length = 16)
	public String getE01dm() {
		return this.e01dm;
	}

	public void setE01dm(String e01dm) {
		this.e01dm = e01dm;
	}

}
