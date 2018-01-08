package com.maven.auth.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.maven.base.entity.BaseEntity;

/**
 * @author liukai
 * @date 2017年7月25日 下午4:40:57
 * @description 角色权限中间表实体类
 */
@Entity
@Table(name = "SYS_ROLES_AUTHORITIES")
public class RolesAuthorities  extends BaseEntity {

	private static final long serialVersionUID = -7207518044254055503L;

	@Column(name = "ROLE_ID",length=32)
	private String roleId;// 用户ID
	
	@Column(name = "AUTHORITY_ID",length=32)
	private String authorityId;// 权限ID

	/**
	 * @return the roleId
	 */
	public String getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return the authorityId
	 */
	public String getAuthorityId() {
		return authorityId;
	}

	/**
	 * @param authorityId the authorityId to set
	 */
	public void setAuthorityId(String authorityId) {
		this.authorityId = authorityId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((authorityId == null) ? 0 : authorityId.hashCode());
		result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		RolesAuthorities other = (RolesAuthorities) obj;
		if (authorityId == null) {
			if (other.authorityId != null)
				return false;
		} else if (!authorityId.equals(other.authorityId))
			return false;
		if (roleId == null) {
			if (other.roleId != null)
				return false;
		} else if (!roleId.equals(other.roleId))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RolesAuthorities [roleId=" + roleId + ", authorityId=" + authorityId + "]";
	}

}