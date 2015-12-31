package com.lt.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.apache.struts2.json.annotations.JSON;

/**
 * 
 * 
 * Role 
 * 
 * @version 1.0.0
 * 
 */
@Entity
@Table(name = "lt_role")
public class Role implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;// 主键
	private String name;// 角色
	private String description;// 描述
	private Date createTime;// 创建时间
	private List<Permission> permissions = new ArrayList<Permission>(0);

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "name", length = 100)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description", length = 1000)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "create_time", columnDefinition = "Timestamp")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	// 产生了一个中间表，
	@JSON(serialize=false)
	@ManyToMany
	@JoinTable(name = "lt_role_permission", 
	joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"), 
	inverseJoinColumns = @JoinColumn(name = "permission_id", 
	referencedColumnName = "id"))
	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}
}
