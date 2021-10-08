package dinhnguyen.techs.testing.forms;

import java.sql.Date;

public class BaseForm {
	
	private Long id;

	private Date version;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getVersion() {
		return version;
	}

	public void setVersion(Date version) {
		this.version = version;
	}
	
	

}
