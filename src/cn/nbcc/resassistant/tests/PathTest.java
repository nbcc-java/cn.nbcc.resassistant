package cn.nbcc.resassistant.tests;

import java.nio.file.Path;

import cn.nbcc.resassistant.utils.DirectoryUtils;

public class PathTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		FileSystem fileSystem = FileSystems.getDefault();
//		Set<String> views = fileSy stem.supportedFileAttributeViews();
//		
//		for (String view : views) {
//			System.out.println(view);
//		}
		
		
		String pathStr = "c:\\zz";
//		ProjectMeta meta = new ProjectMeta(pathStr);
//		meta.loadMeta();
//		System.out.println(meta.getCreateTime());
//		System.out.println(meta.getLastModify());
		Path p[]=DirectoryUtils.listDirecotryContent(pathStr);
		
		for (Path path : p) {
			System.out.println(path.getFileName());
		}
		
	}

}

