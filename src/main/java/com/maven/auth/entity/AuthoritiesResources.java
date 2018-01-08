package com.maven.auth.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.maven.base.entity.BaseEntity;

/**
 * @author liukai
 * @date 2017年7月25日 下午4:47:54
 * @description 权限资源中间表实体类
 */
@Entity
@Table(name = "SYS_AUTHORITIES_RESOURCES")
public class AuthoritiesResources extends BaseEntity {
	
	private static final long serialVersionUID = 7488429747240670083L;
	
	@Column(name = "AUTHORITY_ID",length=32)
	private String authorityId;// 角色ID
	
	@Column(name = "RESOURCE_ID",length=32)
	private String resourceId;// 资源ID
	
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
	/**
	 * @return the resourceId
	 */
	public String getResourceId() {
		return resourceId;
	}
	/**
	 * @param resourceId the resourceId to set
	 */
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((authorityId == null) ? 0 : authorityId.hashCode());
		result = prime * result + ((resourceId == null) ? 0 : resourceId.hashCode());
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
		AuthoritiesResources other = (AuthoritiesResources) obj;
		if (authorityId == null) {
			if (other.authorityId != null)
				return false;
		} else if (!authorityId.equals(other.authorityId))
			return false;
		if (resourceId == null) {
			if (other.resourceId != null)
				return false;
		} else if (!resourceId.equals(other.resourceId))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AuthoritiesResources [authorityId=" + authorityId + ", resourceId=" + resourceId + "]";
	}
}