package dao.model;

// Generated Sep 22, 2011 1:42:26 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * P51050 generated by hbm2java
 */
@Entity
@Table(name = "P51050", schema = "DB2ADMIN")
public class P51050 implements java.io.Serializable {

	private String p51050;

	public P51050() {
	}

	public P51050(String p51050) {
		this.p51050 = p51050;
	}

	@Id
	@Column(name = "P51050", length = 512)
	public String getP51050() {
		return this.p51050;
	}

	public void setP51050(String p51050) {
		this.p51050 = p51050;
	}

}