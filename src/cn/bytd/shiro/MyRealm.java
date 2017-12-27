package cn.bytd.shiro;

import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import cn.bytd.dao.IUserDao;
import cn.bytd.domain.Role;
import cn.bytd.domain.User;

public class MyRealm extends AuthorizingRealm{
	@Resource(name="userDao")
	private IUserDao userDao;
	
	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("认证方法执行");
		UsernamePasswordToken mytoken = (UsernamePasswordToken)token;
		String username = mytoken.getUsername();
		//根据用户名查询数据库中的密码
		User user = userDao.findUserByUserName(username);
		if(user == null){
			//用户名不存在
			return null;
		}
		//如果能查询到，再由框架比对数据库中查询到的密码和页面提交的密码是否一致
		AuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
		return info;
	}
	
	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		System.out.println("授权");
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		Set<Role> roles = user.getRoles();
		String permissionName = null;
		if(roles!=null){
			for (Role role : roles) {
				permissionName = role.getCode();
			}
			//TODO 后期可以通过数据库查询权限
			if("director".equals(permissionName)){
				info.addStringPermission("director");
			}
			if("teacher".equals(permissionName)){
				info.addStringPermission("teacher");
			}
			if("student".equals(permissionName)){
				info.addStringPermission("student");
			}
			if("admin".equals(permissionName)){
				info.addStringPermission("admin");
			}
		}
		return info;
	}


}
