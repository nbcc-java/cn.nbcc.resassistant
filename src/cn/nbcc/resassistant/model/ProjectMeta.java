package cn.nbcc.resassistant.model;

import java.io.IOException;
import java.net.URI;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProjectMeta {
	
	private Path pPath;
	private BasicFileAttributes attr = null;
	private String createTime;
	private String lastModifyTime;
	
	public ProjectMeta(Path pPath) {
		this.pPath = pPath;
	}
	public ProjectMeta(String pathStr){
		this.pPath =Paths.get(pathStr);
	}
	public ProjectMeta(URI pUri){
		this.pPath =Paths.get(pUri);
	}
	
	public void loadMeta()
	{
		if (pPath!=null) {
			try {
				attr = Files.readAttributes(pPath, BasicFileAttributes.class);
				SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
				createTime =sdf.format(new Date(attr.creationTime().toMillis()));
				lastModifyTime = sdf.format(new Date(attr.lastModifiedTime().toMillis()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * @return the createTime
	 */
	public String getCreateTime() {
		return createTime;
	}
	
	public String getLastModify(){
		return lastModifyTime;
	}

}

