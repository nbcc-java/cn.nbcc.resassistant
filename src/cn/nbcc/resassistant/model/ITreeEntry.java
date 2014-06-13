package cn.nbcc.resassistant.model;

import java.util.List;

public interface ITreeEntry {
	String getName();
	void setName(String name);
	void setChildren(List<?> children);
	List<?> getChildren();
}

