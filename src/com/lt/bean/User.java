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

@Entity
@Table(name = "lt_user")
public class User implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;// 主键
	private String account;// 账号
	private String password;// 密码
	private String username;// 用户名
	private Date createTime;// 创建时间
	private List<Role> roles = new ArrayList<Role>(0);//角色

	public User(Integer id) {
		this.id = id;
	}
	
	public User() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "account", length = 100)
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Column(name = "password", length = 80)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "username", length = 50)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "create_time", columnDefinition = "Timestamp")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	//产生了一个中间表，
	@ManyToMany
	@JoinTable(name="lt_user_role",
		joinColumns=@JoinColumn(name="user_id",referencedColumnName="id"),
		inverseJoinColumns=@JoinColumn(name="role_id",referencedColumnName="id")
	)
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
}
