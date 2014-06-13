package cn.nbcc.resassistant.decrecated;

import java.nio.file.Path;
import java.util.List;

import cn.nbcc.resassistant.model.ITreeEntry;

public class FileEntity implements ITreeEntry {

	private Path file;
	public FileEntity(Path file) {
		this.file = file;
	}
	

	@Override
	public String getName() {
		return file.getFileName().toString();
	}

	@Override
	public void setName(String name) {

	}

	@Override
	public void setChildren(List<?> children) {
		
	}

	@Override
	public List<?> getChildren() {
		return null;
	}

}

