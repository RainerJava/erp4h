package dao.model;

// Generated Sep 22, 2011 1:42:26 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Pe07Id generated by hbm2java
 */
@Embeddable
public class Pe07Id implements java.io.Serializable {

	private int e0701;
	private byte e0702;

	public Pe07Id() {
	}

	public Pe07Id(int e0701, byte e0702) {
		this.e0701 = e0701;
		this.e0702 = e0702;
	}

	@Column(name = "E0701", nullable = false, precision = 8, scale = 0)
	public int getE0701() {
		return this.e0701;
	}

	public void setE0701(int e0701) {
		this.e0701 = e0701;
	}

	@Column(name = "E0702", nullable = false, precision = 2, scale = 0)
	public byte getE0702() {
		return this.e0702;
	}

	public void setE0702(byte e0702) {
		this.e0702 = e0702;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Pe07Id))
			return false;
		Pe07Id castOther = (Pe07Id) other;

		return (this.getE0701() == castOther.getE0701())
				&& (this.getE0702() == castOther.getE0702());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getE0701();
		result = 37 * result + this.getE0702();
		return result;
	}

}
