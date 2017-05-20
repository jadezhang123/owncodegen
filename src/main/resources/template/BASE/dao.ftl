<#include "include/head.ftl">
<#include "include/copyright.ftl">

package ${NamespaceDao};

import own.jadezhang.base.common.dao.IBaseDAO;
import ${NamespaceDomain}.${Po};

 /**
 * 《${tableLabel}》 数据访问接口
 *  @author ${copyright.author}
 */
public interface I${Po}DAO extends IBaseDAO<${Po}> {

<#list table.columnList as column>
	<#if (column.primaryKey && column.columnName?lower_case!='id')>
	${Po} findBy${column.columnName}(${column.columnSimpleClassName} ${column.columnName?uncap_first});
	</#if>
</#list>

}