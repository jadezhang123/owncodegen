import com.bcs.codgen.model.ColumnModel;
import com.bcs.codgen.model.InOutType;
import com.bcs.codgen.model.OutputModel;
import com.bcs.codgen.service.Builder;
import com.bcs.codgen.service.ColumnHandler;
import com.bcs.codgen.service.impl.CodeBuilder;
import com.bcs.codgen.service.impl.ProjectBuildConfig;
import com.bcs.codgen.util.ProjectConfig;
import com.bcs.codgen.util.ProjectConfigHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Map;
import java.util.Map.Entry;

import static org.junit.Assert.assertTrue;

public class CodeBuilderTest {
	ProjectBuildConfig buildConfig ;
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		Builder builder = new CodeBuilder(buildConfig);
		Map<String,OutputModel> omMap = builder.build();
		for(Entry<String,OutputModel> entry : omMap.entrySet()){
			//System.out.println("生成内容="+entry.getValue().getOutput());
			if(entry.getValue().getType()==InOutType.FILE){
				assertTrue("文件没有生成="+entry.getValue().getOutput()
						, new File(entry.getValue().getOutput()).exists());
			}
		}
	}

	@Test
	public void testBuild() {
		ProjectConfig projectConfig = ProjectConfigHelper.getDefaultProjectConfig("codgen-config.xml");
		
		//增加一个额外的列模型处理器（下划线命名转驼峰式命名）
		projectConfig.getDbProvider().getColumnHandlers().add(new ColumnHandler() {
			public void handle(ColumnModel col) {
				StringBuffer sb = new StringBuffer();  
		        sb.append(col.getColumnName().toLowerCase());  
		        int count = sb.indexOf("_");  
		        while(count!=0){  
		            int num = sb.indexOf("_",count);  
		            count = num+1;  
		            if(num!=-1){  
		                char ss = sb.charAt(count);  
		                char ia = (char) (ss - 32);  
		                sb.replace(count,count+1,ia+"");  
		            }  
		        }  
		        String ss = sb.toString().replaceAll("_","");
				col.setColumnName(ss);
			}
		});
		
		buildConfig = new ProjectBuildConfig(projectConfig);
		buildConfig.setTableName("Sys_UserInfo"); 
	}
}
