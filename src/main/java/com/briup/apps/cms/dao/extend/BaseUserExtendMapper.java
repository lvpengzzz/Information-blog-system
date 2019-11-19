package com.briup.apps.cms.dao.extend;

import java.util.List;

import com.briup.apps.cms.bean.extend.BaseUserExtend;

public interface BaseUserExtendMapper {

	   BaseUserExtend selectById(long id);

	    List<BaseUserExtend> selectAll();
}
