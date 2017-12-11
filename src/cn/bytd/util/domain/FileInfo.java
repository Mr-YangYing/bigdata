package cn.bytd.util.domain;

import java.io.File;

import lombok.Data;
/**
 * 
 * 说明:文件上传信息类
 * @author yangying
 * @version 1.0
 * @date 2017-11-7 下午3:08:09
 *
 */

public class FileInfo {
	private File upload;
	private String uploadFileName;
	private String filePath;
	private String fileContentType;
	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileContentType() {
		return fileContentType;
	}
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
	
	
}
