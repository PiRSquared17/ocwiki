package oop.conf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.Map.Entry;

public class Config implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String databaseHost = "localhost";
	private String databasePort = "3306";
	private String databaseName = "ocwiki";
	private String domain = "http://localhost:8080";
	private String username = "root";
	private String password = "root";
	private String homeDir = "http://localhost:8080";
	private String articlePath = "${homeDir}/article";
	private String actionPath = "${homeDir}/action";
	private String apiPath = "${homeDir}/api";
	private String restPath = "${homeDir}/rest";
	private String templatePath = "${homeDir}/templates";
	private String uploadPath = "${homeDir}/uploads";
	private String mainEntry = "/index.jsp";
	private String siteName = "OCWiki";
	private String tablePrefix = "ocw";
	private String defaultTemplate = "default";
	private String mysqlCommand = "mysql";
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

	private static Config DEFAULT = null;
	
	public static Config get() {
		return DEFAULT;
	}
	
	public static void setDefaultInstance(Config config) {
//		if (DEFAULT != null) {
//			throw new IllegalStateException("Already initialized.");
//		}
		DEFAULT = config;
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
		for (ActionDescriptor desc : actionDescriptors) {
			actionMap.put(desc.getName(), desc);
		}
	}

	public void setApiDescriptors(Set<APIDescriptor> apiDescriptors) {
		this.apiDescriptors = apiDescriptors;
		for (APIDescriptor desc : apiDescriptors) {
			apiMap.put(desc.getName(), desc);
		}
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
	
}
