package com.app.model;

import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonIgnore;
	
@MappedSuperclass
public class IbSupport {

	@JsonIgnore
	private long createdAt = System.currentTimeMillis();
	
	@JsonIgnore
	private long updatedAt = System.currentTimeMillis();
	
	@JsonIgnore
	private boolean isDeleted = false;
	
	@JsonIgnore
	private long deletedAt;

	public IbSupport() {
		super();
	}

	public IbSupport(long createdAt, long updatedAt, boolean isDeleted, long deletedAt) {
		super();
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.isDeleted = isDeleted;
		this.deletedAt = deletedAt;
	}

	public long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(long createdAt) {
		this.createdAt = createdAt;
	}

	public long getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(long updatedAt) {
		this.updatedAt = updatedAt;
	}

	@JsonIgnore
	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public long getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(long deletedAt) {
		this.deletedAt = deletedAt;
	}

}
