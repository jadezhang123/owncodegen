package columnHandler;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import com.bcs.codgen.model.ColumnModel;
import com.bcs.codgen.service.ColumnHandler;

/**
 * 把oracle带下划线的列名称转换为驼峰式列名称
 * @author tengen
 *
 */
public class CamelColumnHandler implements ColumnHandler,Serializable{

	public void handle(ColumnModel col) {
		//下划线转驼峰式
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
}
