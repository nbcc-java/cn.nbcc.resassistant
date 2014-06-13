package cn.nbcc.resassistant.decrecated;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import cn.nbcc.resassistant.model.ITreeEntry;
import cn.nbcc.resassistant.utils.DirectoryUtils;

public class DirEntity implements ITreeEntry {
	private Path path;
	private List<Path> childrens;

	public DirEntity(Path dir) {
		this.path = dir;
	}

	@Override
	public String getName() {
		return path!=null?path.getFileName().toString():"";
	}

	@Override
	public void setName(String name) {

	}

	@Override
	public void setChildren(List<?> children) {
		Path[] subPath = DirectoryUtils.listDirecotryContent(path.getFileName().toUri().getPath());
		childrens = Arrays.asList(subPath);
	}

	@Override
	public List<?> getChildren() {
		return childrens;
	}

}

