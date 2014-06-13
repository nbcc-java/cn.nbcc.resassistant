package cn.nbcc.resassistant.utils;

import java.io.IOException;
import java.net.URI;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

public class FileUtils {
	
	
	/**
	 * @param zipFile 打包的zip文件路径 如C:/test.zip
	 * @param isCreate 如果该文件不存在是否自动创建，true创建，false不创建
	 * @return 该zip文件系统
	 */
	public static FileSystem createZipFileSystem(Path zipFile,boolean isCreate)
	{
		
		Map<String, String> env = new HashMap<String, String>();
		env.put("create", Boolean.toString(isCreate)); // 如果zip文件不存在则创建

		// 父目录的文件夹必须已创建，否则抛出异常。zip文件可以未创建
		URI uri = URI.create("jar:"+zipFile.toUri());
		System.out.println(uri);

		FileSystem zipfs = null;
		try {
			zipfs = FileSystems.newFileSystem(uri, env);
		}catch (IOException e) {
			e.printStackTrace();
		}
		return zipfs;
	}
	
	
	/**
	 * ZipFileVisitor，遍历用户文件夹，并根据用户文件夹的目录结构创建zip文件
	 * @author 郑哲
	 */
	public static class ZipFileVisitor extends SimpleFileVisitor<Path>{
		private Path root;	//根文件夹
		private FileSystem zipFileSystem ;//zip文件系统

		public ZipFileVisitor(Path root,FileSystem zfs) {
			this.root = root;
			this.zipFileSystem = zfs;
		}
		@Override
		public FileVisitResult preVisitDirectory(Path dir,
				BasicFileAttributes attrs) throws IOException {
			Path relPath = root.relativize(dir);
			System.out.println("Relative:" + relPath);
			Path folderPath = zipFileSystem.getPath(relPath.toString());
			if (folderPath.getNameCount() > 0 && Files.notExists(folderPath)) {
				System.out.println("Create Dir:" + folderPath);
				Files.createDirectories(folderPath);
			}
			return FileVisitResult.CONTINUE;
		}
		@Override
		public FileVisitResult visitFile(Path file,
				BasicFileAttributes attrs) throws IOException {
			if (!Files.isDirectory(file))
			{
				//拷贝文件之前必须指定zip文件系统的path
				Path zipPath = zipFileSystem.getPath(root.relativize(file).toString());
				System.out.println("Copy file"+file+" to "+zipPath);
				Files.copy(file, zipPath,StandardCopyOption.REPLACE_EXISTING);
			}
			return FileVisitResult.CONTINUE;
		}
	}

}

