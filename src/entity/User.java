package entity;

/**
 *  用户类
 *
 */
public class User {
	//主键
	private int id;
	//用户名
	private String username;
	//密码
	private String password;
	//类型 1.管理员 2.教师
	private int type;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
}
