package oop.conf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import oop.controller.OcwikiApp;

public class Config implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String databaseHost = "localhost";
	private String databasePort = "3306";
	private String databaseName = "ocwiki";
	private String domain = "http://localhost:8080";
	private String username = "root";
	private String password = "root";
	private String homeDir = "http://localhost:8080";
	private String luceneIndexDirectory = "WEB-INF/lucene/indexes";
	private String articlePath = "${homeDir}/article";
	private String actionPath = "${homeDir}/action";
	private String apiPath = "${homeDir}/api";
	private String restPath = "${homeDir}/rest";
	private String templatePath = "${homeDir}/templates";
	private String uploadPath = "${homeDir}/uploads";
	private String userPath = "${homeDir}/user";
	private String mainEntry = "/index.jsp";
	private String siteName = "OCWiki";
	private String copyright = "";
	private String logoPath = "";
	private boolean lazyStartup = false;
	private String tablePrefix = "ocw";
	private String defaultTemplate = "default";
	private String mysqlCommand = "mysql";
	private String uploadDir = "/uploads";
	private String facebookAppId = null;
	private String facebookSecret = null;
	private boolean useCDN = false;
	private String texCgi = "http://www.imathas.com/cgi-bin/mimetex.cgi";
	private boolean recreateDatabaseWhenSetup = false;
	
	private int maxAvatarFileSize = 2; // MB
	private int maxAvatarDimension = 150; // pixels
	private int avatarThumbnailDimension = 32; // pixels
	private int maxUploadFileSize = 10; // MB
	
	private Set<ModuleDescriptor> moduleDescriptors = new HashSet<ModuleDescriptor>();
	private Set<ActionDescriptor> actionDescriptors = new HashSet<ActionDescriptor>();
	private Set<APIDescriptor> apiDescriptors = new HashSet<APIDescriptor>();

	private transient Map<String, List<ModuleDescriptor>> moduleMap = new HashMap<String, List<ModuleDescriptor>>();
	private transient Map<String, ActionDescriptor> actionMap = new HashMap<String, ActionDescriptor>();
	private transient Map<String, APIDescriptor> apiMap = new HashMap<String, APIDescriptor>();
	
	public Config() {
	}
	
	private String replaceMagicWords(String str) {
		str = str.replaceAll("\\$\\{homeDir\\}", getHomeDir());
		str = str.replaceAll("\\$\\{domain\\}", getDomain());
		return str;
	}

	public String getDatabaseHost() {
		return databaseHost;
	}

	public String getDatabasePort() {
		return databasePort;
	}

	public String getDatabaseName() {
		return databaseName;
	}

	public String getUserName() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getHomeDir() {
		return homeDir;
	}

	public String getActionPath() {
		return replaceMagicWords(actionPath);
	}

	public String getTemplatePath() {
		return replaceMagicWords(templatePath);
	}
	
	public String getUploadPath() {
		return replaceMagicWords(uploadPath);
	}
	
	public String getMainEntry() {
		return mainEntry;
	}

	public String getSiteName() {
		return siteName;
	}

	public String getTablePrefix() {
		return tablePrefix;
	}
	
	public Set<ActionDescriptor> getActionDescriptors() {
		return actionDescriptors;
	}
	
	public ActionDescriptor getActionDescriptor(String name) {
		return actionMap.get(name);
	}

	public Set<ModuleDescriptor> getModuleDescriptors() {
		return moduleDescriptors;
	}

	public List<ModuleDescriptor> getModuleDescriptors(String position) {
		return moduleMap.get(position);
	}
	
	public Map<String, List<ModuleDescriptor>> getModuleDescriptorsByPosition() {
		return Collections.unmodifiableMap(moduleMap);
	}

	public String getApiPath() {
		return replaceMagicWords(apiPath);
	}

	public Set<APIDescriptor> getApiDescriptors() {
		return apiDescriptors;
	}

	public APIDescriptor getAPIDescriptor(String name) {
		return apiMap.get(name);
	}

	private static final Comparator<ModuleDescriptor> MODULE_POSITION_COMPARATOR = new Comparator<ModuleDescriptor>() {
		
		@Override
		public int compare(ModuleDescriptor o1, ModuleDescriptor o2) {
			return o1.getOrder() - o2.getOrder();
		}
	};
	
	public static Config get() {
		return OcwikiApp.get().getConfig();
	}
	
	public void setDefaultTemplate(String defaultTemplate) {
		this.defaultTemplate = defaultTemplate;
	}

	public String getDefaultTemplate() {
		return defaultTemplate;
	}

	public void setDatabaseHost(String databaseHost) {
		this.databaseHost = databaseHost;
	}

	public void setDatabasePort(String databasePort) {
		this.databasePort = databasePort;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setHomeDir(String homeDir) {
		this.homeDir = homeDir;
	}

	public void setActionPath(String actionPath) {
		this.actionPath = actionPath;
	}

	public void setApiPath(String apiPath) {
		this.apiPath = apiPath;
	}

	public void setTemplatePath(String templatePath) {
		this.templatePath = templatePath;
	}

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}

	public void setMainEntry(String mainEntry) {
		this.mainEntry = mainEntry;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public void setTablePrefix(String tablePrefix) {
		this.tablePrefix = tablePrefix;
	}

	public void setModuleDescriptors(Set<ModuleDescriptor> moduleDescriptors) {
		this.moduleDescriptors = moduleDescriptors;
		for (ModuleDescriptor descriptor : moduleDescriptors) {
			List<ModuleDescriptor> list = moduleMap.get(descriptor.getPosition());
			if (list == null) {
				list = new ArrayList<ModuleDescriptor>();
				moduleMap.put(descriptor.getPosition(), list);
			}
			list.add(descriptor);
		}
		Comparator<ModuleDescriptor> comparator = new Comparator<ModuleDescriptor>() {
			
			@Override
			public int compare(ModuleDescriptor o1, ModuleDescriptor o2) {
				return o1.getOrder() - o2.getOrder();
			}
		};
		for (Entry<String, List<ModuleDescriptor>> entries : moduleMap.entrySet()) {
			Collections.sort(entries.getValue(), comparator);
		}
	}

	public void setActionDescriptors(Set<ActionDescriptor> actionDescriptors) {
		this.actionDescriptors = actionDescriptors;
	}

	public void setApiDescriptors(Set<APIDescriptor> apiDescriptors) {
		this.apiDescriptors = apiDescriptors;
	}

	public void setRestPath(String restPath) {
		this.restPath = restPath;
	}

	public String getRestPath() {
		return replaceMagicWords(restPath);
	}

	public void setMysqlCommand(String mysqlCommand) {
		this.mysqlCommand = mysqlCommand;
	}

	public String getMysqlCommand() {
		return mysqlCommand;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getDomain() {
		return domain;
	}

	public void setArticlePath(String articlePath) {
		this.articlePath = articlePath;
	}

	public String getArticlePath() {
		return replaceMagicWords(articlePath);
	}

	public String getUploadDir() {
		return uploadDir;
	}
	
	public void setUploadDir(String uploadDir) {
		this.uploadDir = uploadDir;
	}

	public String getUserPath() {
		return replaceMagicWords(userPath);
	}
	
	public void setUserPath(String userPath) {
		this.userPath = userPath;
	}
	
	void doneLoading() {
		// create action map
		actionMap = new HashMap<String, ActionDescriptor>();
		for (ActionDescriptor desc : actionDescriptors) {
			actionMap.put(desc.getName(), desc);
		}
		// create api map
		apiMap = new HashMap<String, APIDescriptor>();
		for (APIDescriptor desc : apiDescriptors) {
			apiMap.put(desc.getName(), desc);
		}
		// create module map
		moduleMap = new HashMap<String, List<ModuleDescriptor>>();
		for (ModuleDescriptor descriptor : moduleDescriptors) {
			List<ModuleDescriptor> list = moduleMap.get(descriptor.getPosition());
			if (list == null) {
				list = new ArrayList<ModuleDescriptor>();
				moduleMap.put(descriptor.getPosition(), list);
			}
			list.add(descriptor);
		}
		for (Entry<String, List<ModuleDescriptor>> entries : moduleMap.entrySet()) {
			Collections.sort(entries.getValue(), MODULE_POSITION_COMPARATOR);
		}
	}

	public void setFacebookAppId(String facebookAppId) {
		this.facebookAppId = facebookAppId;
	}

	public String getFacebookAppId() {
		return facebookAppId;
	}

	public void setFacebookSecret(String facebookSecret) {
		this.facebookSecret = facebookSecret;
	}

	public String getFacebookSecret() {
		return facebookSecret;
	}

	public void setTexCgi(String texCgi) {
		this.texCgi = texCgi;
	}

	public String getTexCgi() {
		return texCgi;
	}

	public void setUseCDN(boolean useCDN) {
		this.useCDN = useCDN;
	}

	public boolean isUseCDN() {
		return useCDN;
	}

	public void setLuceneIndexDirectory(String luceneIndexDirectory) {
		this.luceneIndexDirectory = luceneIndexDirectory;
	}

	public String getLuceneIndexDirectory() {
		return luceneIndexDirectory;
	}

	public void setLazyStartup(boolean lazyStartup) {
		this.lazyStartup = lazyStartup;
	}

	public boolean isLazyStartup() {
		return lazyStartup;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}

	public String getLogoPath() {
		if (!logoPath.startsWith("/")) {
			return "/images/" + logoPath;
		}
		return logoPath;
	}

	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}

	public String getCopyright() {
		return copyright;
	}

	public int getMaxAvatarFileSize() {
		return maxAvatarFileSize;
	}
	
	public long getMaxAvatarFileBytes() {
		return maxAvatarFileSize * (long)1024 * 1024;
	}

	public void setMaxAvatarFileSize(int maxAvatarFileSize) {
		this.maxAvatarFileSize = maxAvatarFileSize;
	}

	public int getMaxAvatarDimension() {
		return maxAvatarDimension;
	}

	public void setMaxAvatarDimension(int maxAvatarDimension) {
		this.maxAvatarDimension = maxAvatarDimension;
	}

	public int getMaxUploadFileSize() {
		return maxUploadFileSize;
	}

	public long getMaxUploadFileBytes() {
		return maxUploadFileSize * (long)1024 * 1024;
	}
	
	public void setMaxUploadFileSize(int maxUploadFileSize) {
		this.maxUploadFileSize = maxUploadFileSize;
	}

	public void setAvatarThumbnailDimension(int avatarThumbnailDimension) {
		this.avatarThumbnailDimension = avatarThumbnailDimension;
	}

	public int getAvatarThumbnailDimension() {
		return avatarThumbnailDimension;
	}

	public void setRecreateDatabaseWhenSetup(boolean recreateDatabaseWhenSetup) {
		this.recreateDatabaseWhenSetup = recreateDatabaseWhenSetup;
	}

	public boolean isRecreateDatabaseWhenSetup() {
		return recreateDatabaseWhenSetup;
	}
	
}
