package org.tech.base.util;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.tech.base.entity.PaginatedCondition;

/**
 * 页面分页（主要用于easyui datagrid表格分页）工具类
 * 
 */
public class PaginatedUtils {
	/**
	 * 
	 * @param request
	 * @param entityName
	 * @param selects
	 * @param filterNames
	 * @param defsort
	 * @param deforder
	 * @param isExport
	 * @param distinct 去重
	 * @return
	 */
	public static PaginatedCondition parsePaginatedCondition(
			HttpServletRequest request, String entityName, String[] selects,
			String[] filterNames, String defsort, String deforder,boolean isExport, boolean distinct) {
		PaginatedCondition page = parsePaginatedCondition(request, entityName, selects, filterNames, defsort, deforder, isExport);
		page.setDistinct(distinct);
		return page;
	}

	/**
	 * 从web请求中解析分页条件
	 * @param request
	 * @param entityName
	 * @param selects
	 * @param filterNames
	 * @param defsort
	 * @param deforder
	 * @return
	 */
	public static PaginatedCondition parsePaginatedCondition(
			HttpServletRequest request, String entityName, String[] selects,
			String[] filterNames, String defsort, String deforder,boolean isExport) {
		PaginatedCondition paginatedCondition = new PaginatedCondition();
		//导出不分页
		if(!isExport){
			paginatedCondition.setPage(Integer.parseInt(request
					.getParameter("page")));
			paginatedCondition.setRows(Integer.parseInt(request
					.getParameter("rows")));
		}

		String sort = request.getParameter("sort");
		if (sort != null) {
			paginatedCondition.setSort(new String[]{sort});
		} else {
			if (defsort != null) {
				paginatedCondition.setSort(new String[]{defsort});
			}
		}

		String order = request.getParameter("order");
		if (order != null) {
			paginatedCondition.setOrder(new String[]{order});
		} else {
			if (deforder != null) {
				paginatedCondition.setOrder(new String[]{deforder});
			}
		}
		paginatedCondition.setSelects(selects);
		paginatedCondition.setEntityName(entityName);

		if (filterNames != null && filterNames.length > 0) {
			List<String> filters = new ArrayList<String>();
			for (String filterName : filterNames) {
				String value = null;
				if (filterName.trim().endsWith("like")) {
					value = request.getParameter(filterName.split("like")[0]
							.trim());
					if (value != null && !"".equals(value.trim())) {
						filters.add(filterName + " '%" + value + "%'");
					}
				} else if (filterName.trim().endsWith("=")) {
					value = request.getParameter(filterName.split("=")[0]
							.trim());
					if (value != null && !"".equals(value.trim())) {
						filters.add(filterName + " '" + value + "'");
					}
				} else if (filterName.trim().endsWith("in")) {
					value = request.getParameter(filterName.split("in")[0]
							.trim());
					if (value != null && !"".equals(value.trim())) {
						filters.add(filterName + " (" + value + ")");
					}
				} else {
					filters.add(filterName);// 内部过滤条件（不需要从页面传递参数）
				}
			}

			if (filters.size() > 0) {
				paginatedCondition.setFilters(filters);
			}

		}
		return paginatedCondition;
	}

	/**
	 * 从web请求中解析分页条件
	 * 
	 * @param request
	 * @param entityName
	 *            实体名
	 * @param selects
	 *            页面显示字段(每个字段用hql查询的对象表达式表示)集
	 * @param filterNames
	 *            过滤字段集
	 * @return
	 */
	public static PaginatedCondition parsePaginatedCondition(
			HttpServletRequest request, String entityName, String[] selects,
			String[] filterNames) {
		PaginatedCondition paginatedCondition = parsePaginatedCondition(request);

		paginatedCondition.setSelects(selects);
		paginatedCondition.setEntityName(entityName);

		if (filterNames != null && filterNames.length > 0) {
			List<String> filters = new ArrayList<String>();
			for (String filterName : filterNames) {
				String value = request.getParameter(filterName);
				if (value != null && value.length() > 0) {
					filters.add(filterName + value);
				} else {
					if (!(filterName.trim().endsWith("=") || filterName.trim()
							.endsWith("like"))) {
						filters.add(filterName);// 内部过滤条件（不需要从页面传递参数）
					}
				}
			}

			if (filters.size() > 0) {
				paginatedCondition.setFilters(filters);
			}
		}

		return paginatedCondition;
	}

	public static PaginatedCondition parsePaginatedCondition(
			HttpServletRequest request) {
		PaginatedCondition paginatedCondition = new PaginatedCondition();

		paginatedCondition.setPage(Integer.parseInt(request
				.getParameter("page")));
		paginatedCondition.setRows(Integer.parseInt(request
				.getParameter("rows")));

		String sort = request.getParameter("sort");
		if (sort != null) {
			paginatedCondition.setSort(new String[]{sort});
		}

		String order = request.getParameter("order");
		if (order != null) {
			paginatedCondition.setOrder(new String[]{order});
		}

		return paginatedCondition;
	}

}
