<#include "include/head.ftl">
<#include "include/copyright.ftl">
<#assign isCbd = false>
<#assign idJavaType="long">
<#list table.columnList as column>
    <#if column.columnName?lower_case=='creator'>
        <#assign isCbd = true>
    </#if>
    <#if column.columnName?lower_case=='id'>
        <#assign idJavaType = column.columnSimpleClassName>
    </#if>
</#list>

package ${NamespaceDomain};

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
<#if isCbd>
import own.jadezhang.base.common.domain.CreateBaseDomain;
<#else>
import own.jadezhang.base.common.domain.BaseDomain;
</#if>

import java.util.*;

/**
 * 《${tableLabel}》 实体
 * @author ${copyright.author}
 *
 */
public class ${Po} extends <#if isCbd>CreateBaseDomain<${idJavaType}><#else>BaseDomain<${idJavaType}></#if> {
	private static final long serialVersionUID = 1L;
	
	<#list table.columnList as column>
	<#if column.columnName?lower_case='id'||column.columnName?lower_case='creator'||column.columnName?lower_case='createdate'||column.columnName?lower_case='lastmodifier'||column.columnName?lower_case='lastmoddate'||column.columnName?lower_case='status'>
	<#else>
	private ${column.columnSimpleClassName} ${column.columnName?uncap_first}; //${column.columnLabel}
	</#if>
	</#list>
    
	/**
	 *默认空构造函数
	 */
	public ${Po}() {
		super();
	}
	 
	<#list table.columnList as column>
	<#if column.columnName?lower_case='id'||column.columnName?lower_case='creator'||column.columnName?lower_case='createdate'||column.columnName?lower_case='lastmodifier'||column.columnName?lower_case='lastmoddate'||column.columnName?lower_case='status'>
	<#else>
	/**
	 * @return ${column.columnName?uncap_first} ${column.columnLabel}
	 */
	public ${column.columnSimpleClassName} get${column.columnName?cap_first}(){
		return this.${column.columnName?uncap_first};
	}
	/**
	 * @param ${column.columnName?uncap_first} ${column.columnLabel}
	 */
	public void set${column.columnName?cap_first}(${column.columnSimpleClassName} ${column.columnName?uncap_first}){
		this.${column.columnName?uncap_first} = ${column.columnName?uncap_first};
	}
	</#if>
	</#list>
	
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
		<#list table.columnList as column>
			.append("${column.columnName}",get${column.columnName?cap_first}())
		</#list>
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
		<#list table.columnList as column>
			.append(get${column.columnName?cap_first}())
		</#list>
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ${Po} == false) return false;
		if(this == obj) return true;
		${Po} other = (${Po})obj;
		return new EqualsBuilder()
			<#list table.columnList as column>
			<#if column.primaryKey>
			.append(get${column.columnName?cap_first}(),other.get${column.columnName?cap_first}())
			</#if>
			</#list>
			.isEquals();
	}
}
