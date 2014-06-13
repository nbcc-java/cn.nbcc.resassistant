package cn.nbcc.resassistant.utils;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

public class DirectoryUtils {
	
	static class FileVisitor extends SimpleFileVisitor<Path> {
		
		private Path des;
		private Path src;

		public FileVisitor(Path src,Path des) {
			this.src = src;
			this.des = des;
		}
		
		@Override
		public FileVisitResult preVisitDirectory(Path dir,
				BasicFileAttributes attrs) throws IOException {
			if (Files.isDirectory(dir)) {
				Path relative=src.relativize(dir);
				Files.createDirectories(des.resolve(relative));
			}
			return super.preVisitDirectory(dir, attrs);
		}
		
		@Override
		public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
				throws IOException {
			Path relative=file.relativize(src);
			Files.copy(src.resolve(relative), file, StandardCopyOption.REPLACE_EXISTING);
			return super.visitFile(file, attrs);
		}
		
	}
	
//	class ListTree extends SimpleFileVisitor<Path> implements ITreeEntry
//	{
//		
//		private Path parent;
//		private DirEntity  dir;
//		
//		public ListTree(Path parent) {
//			this.parent = parent;
//			dir = new DirEntity(parent);
//		}
//		@Override
//		public FileVisitResult postVisitDirectory(Path dir, IOException exc)
//				throws IOException {
//			if (dir.compareTo(parent)! =0) {
//				
//			}
//			return super.postVisitDirectory(dir, exc);
//		}
//		@Override
//		public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
//				throws IOException {
//			new FileEntity(file);
//			return super.visitFile(file, attrs);
//		}
//		@Override
//		public String getName() {
//			return ;
//		}
//		@Override
//		public void setName(String name) {
//			
//		}
//		@Override
//		public void setChildren(List<?> children) {
//			
//		}
//		@Override
//		public List<?> getChildren() {
//			// TODO Auto-generated method stub
//			return null;
//		}
//	}
	
	

	public static boolean createNewDirecotry(String pathStr)
	{
		Path newdir = FileSystems.getDefault().getPath(pathStr);
		try {
			Files.createDirectory(newdir);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static Path[] listDirecotryContent(String pathStr){
		Path dir = FileSystems.getDefault().getPath(pathStr);
		ArrayList<Path> arr = new ArrayList<Path>();
		DirectoryStream<Path> ds= null;
		try {
			ds = Files.newDirectoryStream(dir);
			for (Path file : ds) {
				arr.add(file);
			}
		} catch (Exception e) {
			System.out.println("无法读取文件夹中文件");
		}
		if (arr.isEmpty()) {
			return new Path[0];
		}else
		{
			Path paths[] = new Path[arr.size()]; 
			return arr.toArray(paths);
		}
	}
	
	public static boolean copyDirToDir(Path src,Path des)
	{
		try {
//				Files.copy(src, des,StandardCopyOption.REPLACE_EXISTING);
			Files.walkFileTree(src, new FileVisitor(src, des.resolve(src.getFileName())));
			
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static boolean exists(String pathString) {
		Path p = Paths.get(pathString);
		return Files.exists(p);
		
	}

	public static String getFileName(String pathString) {
		Path p = Paths.get(pathString);
		return p.getFileName().toString();
	}
	
	

}

