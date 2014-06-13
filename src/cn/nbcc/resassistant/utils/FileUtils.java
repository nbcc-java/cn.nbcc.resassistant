package cn.nbcc.resassistant.utils;

import java.io.IOException;
import java.net.URI;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

public class FileUtils {
	
	
	/**
	 * @param zipFile �����zip�ļ�·�� ��C:/test.zip
	 * @param isCreate ������ļ��������Ƿ��Զ�������true������false������
	 * @return ��zip�ļ�ϵͳ
	 */
	public static FileSystem createZipFileSystem(Path zipFile,boolean isCreate)
	{
		
		Map<String, String> env = new HashMap<String, String>();
		env.put("create", Boolean.toString(isCreate)); // ���zip�ļ��������򴴽�

		// ��Ŀ¼���ļ��б����Ѵ����������׳��쳣��zip�ļ�����δ����
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
	 * ZipFileVisitor�������û��ļ��У��������û��ļ��е�Ŀ¼�ṹ����zip�ļ�
	 * @author ֣��
	 */
	public static class ZipFileVisitor extends SimpleFileVisitor<Path>{
		private Path root;	//���ļ���
		private FileSystem zipFileSystem ;//zip�ļ�ϵͳ

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
				//�����ļ�֮ǰ����ָ��zip�ļ�ϵͳ��path
				Path zipPath = zipFileSystem.getPath(root.relativize(file).toString());
				System.out.println("Copy file"+file+" to "+zipPath);
				Files.copy(file, zipPath,StandardCopyOption.REPLACE_EXISTING);
			}
			return FileVisitResult.CONTINUE;
		}
	}

}

