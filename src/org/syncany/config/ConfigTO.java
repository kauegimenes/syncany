package org.syncany.config;

import java.io.File;
import java.util.Map;

import org.syncany.util.FileUtil;

import com.google.gson.Gson;


public class ConfigTO {
	//Directory which contains all files which shall be synced with remote repo 
	private String localDir;
	//Home / Config directory of syncany 
	private String appDir;
	//Synched db-versions 
	private String databaseDir;
	//Local cache for chunks/multichunks/dbversions
	private String cacheDir;
	//local identifier for user
	private String machineName;
	private ConnectionSettings connection;
	private EncryptionSettings encryption;
	
	public static ConfigTO load(File file) throws ConfigException {
		try {
			Gson gson = new Gson();		
			return gson.fromJson(FileUtil.readFileToString(file), ConfigTO.class);
		}
		catch (Exception ex) {
			throw new ConfigException("Config file does not exist or is invalid: "+file, ex);
		}
	}	
		
	public String getLocalDir() {
		return localDir;
	}
	
	public String getAppDir() {
		return appDir;
	}
	
	public String getCacheDir() {
		return cacheDir;
	}

	public ConnectionSettings getConnection() {
		return connection;
	}
	
	public String getMachineName() {
		return machineName;
	}

	public EncryptionSettings getEncryption() {
		return encryption;
	}
	
	public static class ConnectionSettings {
	    private String type;
	    private Map<String, String> settings;
	    
	    public ConnectionSettings(String type, Map<String, String> settings) {
			super();
			this.type = type;
			this.settings = settings;
		}
	    
		public String getType() {
			return type;
		}
		public Map<String, String> getSettings() {
			return settings;
		}
	}
	
	public static class EncryptionSettings {
		private Boolean enabled;
	    private String pass;
	    
		public EncryptionSettings(Boolean enabled, String pass) {
			this.enabled = enabled;
			this.pass = pass;
		}

		public boolean isEnabled() {
			return enabled != null && enabled;
		}
		
		public String getPass() {
			return pass;
		}		
	}

	public String getDatabaseDir() {
		return databaseDir;
	}

	public void setLocalDir(String localDir) {
		this.localDir = localDir;
	}

	public void setAppDir(String appDir) {
		this.appDir = appDir;
	}

	public void setDatabaseDir(String databaseDir) {
		this.databaseDir = databaseDir;
	}

	public void setCacheDir(String cacheDir) {
		this.cacheDir = cacheDir;
	}

	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}

	public void setConnection(ConnectionSettings connection) {
		this.connection = connection;
	}

	public void setEncryption(EncryptionSettings encryption) {
		this.encryption = encryption;
	}
}

