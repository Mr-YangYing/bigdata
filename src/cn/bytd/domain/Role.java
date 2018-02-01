package cn.bytd.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 角色实体
 */

public class Role implements java.io.Serializable {

	// Fields

	private String id;
	private String name;
	private String code;
	private String description;
	private Set permissions = new HashSet(0);//当前角色对应的多个权限
	private Set users = new HashSet(0);//当前角色对应的多个用户

	// Constructors

	/** default constructor */
	public Role() {
	}

	/** minimal constructor */
	public Role(String id) {
		this.id = id;
	}

	/** full constructor */
	public Role(String id, String name, String code, String description,
			Set permissions, Set users) {
		this.id = id;
		this.name = name;
		this.code = code;
		this.description = description;
		this.permissions = permissions;
		this.users = users;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set getPermissions() {
		return permissions;
	}

	public void setPermissions(Set permissions) {
		this.permissions = permissions;
	}

	public Set getUsers() {
		return this.users;
	}

	public void setUsers(Set users) {
		this.users = users;
	}

}