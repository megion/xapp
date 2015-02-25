package org.megion.xapp.core.entity;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;

@Entity(name="user_role")
@AssociationOverrides({
    @AssociationOverride(name = "id.user", joinColumns = @JoinColumn(name = "user_id")),
    @AssociationOverride(name = "id.role", joinColumns = @JoinColumn(name = "role_id"))
})
public class UserRole implements Serializable {

	private static final long serialVersionUID = -1269590340352336094L;
	
	@EmbeddedId
	private UserRolePK id;

	public UserRolePK getId() {
		return id;
	}

	public void setId(UserRolePK id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserRole other = (UserRole) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
