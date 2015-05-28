package com.nwchecker.server.json;

public class JsonViews {	
	public interface TaskPassView{}
	public interface ViewUsersAdmin extends TaskPassView{}
	public interface ForArchive extends TaskPassView{}
	public interface SingleTask extends ForArchive{}
}
