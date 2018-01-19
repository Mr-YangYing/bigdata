package cn.bytd.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 权限实体
 */

public class Permission implements java.io.Serializable {

	// Fields

	private String id;
	private Permission parentPermission;//当前权限的上级权限
	private String name;
	private String code;//关键字
	private String description;
	private String page;//访问路径
	private String generatemenu;//是否生成菜单，1：是 0：否
	private Integer zindex;
	private Set roles = new HashSet(0);//当前权限对应的多个角色
	private Set children = new HashSet(0);//当前权限的下级权限
	
	private String pId;
	
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public Permission getParentPermission() {
		return parentPermission;
	}
	public void setParentPermission(Permission parentPermission) {
		this.parentPermission = parentPermission;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getGeneratemenu() {
		return generatemenu;
	}
	public void setGeneratemenu(String generatemenu) {
		this.generatemenu = generatemenu;
	}
	public Integer getZindex() {
		return zindex;
	}
	public void setZindex(Integer zindex) {
		this.zindex = zindex;
	}
	public Set getRoles() {
		return roles;
	}
	public void setRoles(Set roles) {
		this.roles = roles;
	}
	public Set getChildren() {
		return children;
	}
	public void setChildren(Set children) {
		this.children = children;
	}
	@Override
	public String toString() {
		return "Permission [id=" + id + ", parentPermission=" + parentPermission + ", name=" + name + ", code=" + code
				+ ", description=" + description + ", page=" + page + ", generatemenu=" + generatemenu + ", zindex="
				+ zindex + "]";
	}
	
}