<#include "include/head.ftl">
<#include "include/copyright.ftl">

package ${NamespaceService};

import own.jadezhang.base.common.domain.BaseDomain;
import own.jadezhang.base.common.dao.IBaseDAO;
import own.jadezhang.base.common.service.IBaseService;

 /**
 * 《${tableLabel}》 业务逻辑服务接口
 * @author ${copyright.author}
 */
public interface I${Po}Service<D extends IBaseDAO<T>, T extends BaseDomain> extends IBaseService<D, T> {

}