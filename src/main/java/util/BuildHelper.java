package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.commons.lang.StringUtils;

import com.bcs.codgen.util.ClassLoaderUtil;
import com.bcs.codgen.util.ProjectConfig;
import com.bcs.codgen.util.ProjectConfigHelper;

public class BuildHelper {
	/**
	 * 根据文件名取得prettify高亮着色对应的语言：bash”, “c”, “cc”, “cpp”, “cs”, “csh”, “cyc”, “cv”, 
	 * “htm”, “html”, ”java”, “js”, “m”, “mxml”, “perl”, “pl”, “pm”, “py”, “rb”, “sh”, ”xhtml”, “xml”, “xsl”
	 * @param filename
	 * @return 
	 */
	public static String getPrettifyLang(String filename){
		String result = "lang-tex";
		if(StringUtils.isBlank(filename)||filename.contains(".")==false) return result;
		
		String extName = StringUtils.substringAfterLast(filename, ".");
		
		String defaultLangs="bash,c,cc,cpp,cs,csh,cyc,cv,htm,html, java,js,m,mxml,perl,pl,pm,py,rb,sh, xhtml,xml,xsl";
		if(",".concat(defaultLangs).concat(",").contains(",".concat(extName).concat(","))){
			result = "lang-"+extName;
		}else if(extName.equals("jsp")||extName.equals("aspx")){
			result = "lang-html";
		}
		
		return result;
	}
	
	private static String getConfigParams(ServletContext servletContext){	
		String configParams = servletContext.getInitParameter("codgen.config");
		if(StringUtils.isNotBlank(configParams)){
			configParams = configParams.replace("\t","").replace("\n","");
		}
		return StringUtils.trimToEmpty(configParams);
	}
	
	public static void refreshConfig(ServletContext servletContext){
		ProjectConfigHelper.refreshConfig(getConfigParams(servletContext));
	}
	
	public static ProjectConfig getProjectConfig(ServletContext servletContext,String projectName){
		ProjectConfig projectConfig = ProjectConfigHelper.getProjectConfig(getConfigParams(servletContext),projectName);
		return projectConfig;
	}
	
	public static Map<String, ProjectConfig>  getAllProjectConfig(ServletContext servletContext){
		Map<String, ProjectConfig>  projectConfigMap = ProjectConfigHelper.getAllProjectConfig(getConfigParams(servletContext));
		return projectConfigMap;
	}
	
	public static ProjectConfig getDefaultProjectConfig(ServletContext servletContext){
		ProjectConfig projectConfig = ProjectConfigHelper.getDefaultProjectConfig(getConfigParams(servletContext));
		return projectConfig;
	}
}
