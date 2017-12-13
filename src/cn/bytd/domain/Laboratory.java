package cn.bytd.domain;

/**
 * 
 * 说明:实验室类
 * 
 * @author yangying
 * @version 1.0
 * @date 2017年12月12日 上午9:43:54
 *
 *
 */
public class Laboratory {
	private Long id;
	private String labAddress;//实验室地址
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLabAddress() {
		return labAddress;
	}
	public void setLabAddress(String labAddress) {
		this.labAddress = labAddress;
	}

	
}
