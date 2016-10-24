package org.tech.base.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.tech.base.entity.DataTablesCondition;
import org.tech.base.entity.DataTablesCondition.ColumnCriterias;
import org.tech.base.entity.DataTablesCondition.OrderCriterias;
import org.tech.base.entity.DataTablesCondition.SearchCriterias;
import org.tech.base.entity.PaginatedCondition;

/**
 * 页面分页（主要用于DataTables表格分页）工具类
 * 
 * @author Yang Junping
 * 
 */
public class DataTablesUtils {
	/**
	 * 
	 * @param request
	 * @param condition
	 * @param entityName
	 * @param selects
	 * @param filterNames
	 * @param defsort
	 * @param deforder
	 * @param isExport
	 * @param distinct
	 *            去重
	 * @return
	 */
	public static PaginatedCondition parsePaginatedCondition(
			HttpServletRequest request, DataTablesCondition condition,
			String entityName, String[] selects, String[] filterNames,
			String sort, String order, boolean isExport, boolean distinct) {
		return parsePaginatedCondition(request, condition, entityName, selects, 
				filterNames, null, sort, order, isExport, distinct);
	}
	
	public static PaginatedCondition parsePaginatedCondition(
			HttpServletRequest request, DataTablesCondition condition,
			String entityName, String[] selects, String[] filterNames,
			String[] sort, String[] order, boolean isExport, boolean distinct) {
		return parsePaginatedCondition(request, condition, entityName, selects, 
				filterNames, null, sort, order, isExport, distinct);
	}
	
	/**
	 * 
	 * @param request
	 * @param condition
	 * @param entityName
	 * @param selects
	 * @param filterNames
	 * @param groupBy
	 * @param sort
	 * @param order
	 * @param isExport
	 * @param distinct
	 * @return
	 */
	public static PaginatedCondition parsePaginatedCondition(
			HttpServletRequest request, DataTablesCondition condition,
			String entityName, String[] selects, String[] filterNames, int[] groupBy,
			String sort, String order, boolean isExport, boolean distinct) {
		PaginatedCondition page = parsePaginatedCondition(request, condition,
				entityName, selects, filterNames, groupBy, sort, order, isExport);
		page.setDistinct(distinct);
		return page;
	}
	
	public static PaginatedCondition parsePaginatedCondition(
			HttpServletRequest request, DataTablesCondition condition,
			String entityName, String[] selects, String[] filterNames, int[] groupBy,
			String[] sort, String[] order, boolean isExport, boolean distinct) {
		PaginatedCondition page = parsePaginatedCondition(request, condition,
				entityName, selects, filterNames, groupBy, sort, order, isExport);
		page.setDistinct(distinct);
		return page;
	}

	/**
	 * 从web请求中解析分页条件
	 * 
	 * @param request
	 * @param condition
	 * @param entityName
	 * @param selects
	 * @param filterNames
	 * @param defsort
	 * @param deforder
	 * @return
	 */
	public static PaginatedCondition parsePaginatedCondition(
			HttpServletRequest request, DataTablesCondition condition,
			String entityName, String[] selects, String[] filterNames, int[] groupBy,
			String sort, String order, boolean isExport) {
		PaginatedCondition paginatedCondition = parsePaginatedCondition(
				request, condition, entityName, selects, filterNames, groupBy);
		if (paginatedCondition.getSort() == null && !StringUtil.isEmpty(sort)) {
			paginatedCondition.setSort(new String[]{sort});
		}
		if (paginatedCondition.getOrder() == null && !StringUtil.isEmpty(order)) {
			paginatedCondition.setOrder(new String[]{order});
		}
		// 导出不分页
		paginatedCondition.setDisablePaginated(isExport);
		return paginatedCondition;
	}
	
	public static PaginatedCondition parsePaginatedCondition(
			HttpServletRequest request, DataTablesCondition condition,
			String entityName, String[] selects, String[] filterNames, int[] groupBy,
			String[] sort, String[] order, boolean isExport) {
		PaginatedCondition paginatedCondition = parsePaginatedCondition(
				request, condition, entityName, selects, filterNames, groupBy);
		if (paginatedCondition.getSort() == null && sort != null) {
			paginatedCondition.setSort(sort);
		}
		if (paginatedCondition.getOrder() == null && order != null) {
			paginatedCondition.setOrder(order);
		}
		// 导出不分页
		paginatedCondition.setDisablePaginated(isExport);
		return paginatedCondition;
	}
	
	public static PaginatedCondition parsePaginatedCondition(
			HttpServletRequest request, DataTablesCondition condition,
			String entityName, String[] selects, String[] filterNames,
			String sort, String order, boolean isExport) {
		return parsePaginatedCondition(request, condition, entityName, 
				selects, filterNames, null, sort, order, isExport);
	}
	
	public static PaginatedCondition parsePaginatedCondition(
			HttpServletRequest request, DataTablesCondition condition,
			String entityName, String[] selects, String[] filterNames,
			String[] sort, String[] order, boolean isExport) {
		return parsePaginatedCondition(request, condition, entityName, 
				selects, filterNames, null, sort, order, isExport);
	}

	public static PaginatedCondition parsePaginatedCondition(
			HttpServletRequest request, DataTablesCondition condition,
			String entityName, String[] selects, String[] filterNames) {
		return parsePaginatedCondition(request, condition, entityName, selects,
				filterNames, null);
	}

	/**
	 * 从web请求中解析分页条件
	 * 
	 * @param request
	 * @param condition
	 *            DataTables查询条件
	 * @param entityName
	 *            实体名
	 * @param selects
	 *            页面显示字段(每个字段用hql查询的对象表达式表示)集
	 * @param filterNames 过滤字段集
	 *            
	 * @param groupBy 分组序号（从selects中选择要分组的字段）
	 * @return
	 */
	public static PaginatedCondition parsePaginatedCondition(
			HttpServletRequest request, DataTablesCondition condition,
			String entityName, String[] selects, String[] filterNames,
			int[] groupBy) {
		PaginatedCondition paginatedCondition = parsePaginatedCondition(condition);
		paginatedCondition.setSelects(selects);
		paginatedCondition.setEntityName(entityName);
		paginatedCondition.setGroupBy(groupBy);
		// 添加通用查询条件
		Map<SearchCriterias, String> searchList = condition.getSearch();
		String search = null;
		if (searchList != null) {
			search = searchList.get(SearchCriterias.value);// 获取页面搜索框内的值
		}
		if (filterNames != null && filterNames.length > 0) {
			List<String> filters = new ArrayList<String>();
			for (String filterName : filterNames) {
				if (filterName.trim().endsWith("like")) {
					String key = getActualFilterName(filterName, selects, "like");
					String searchValue = getColumnSearchValue(key, condition);
					if (!StringUtil.isEmpty(searchValue)) {
						filters.add(filterName + " '%" + searchValue + "%'");
					} else if (!StringUtil.isEmpty(search)) {
						filters.add(filterName + " '%" + search + "%'");
					}
				} else if (filterName.trim().endsWith("=")) {
					String key = getActualFilterName(filterName, selects, "=");
					String searchValue = getColumnSearchValue(key, condition);
					if (!StringUtil.isEmpty(searchValue)) {
						filters.add(filterName + " '" + searchValue + "'");
					} else if (!StringUtil.isEmpty(search)) {
						filters.add(filterName + " '" + search + "'");
					}
				} else if (filterName.trim().endsWith("in")) {
					String key = getActualFilterName(filterName, selects, "in");
					String searchValue = getColumnSearchValue(key, condition);
					if (!StringUtil.isEmpty(searchValue)) {
						filters.add(filterName + " (" + searchValue + ")");
					} else if (!StringUtil.isEmpty(search)) {
						filters.add(filterName + " (" + search + ")");
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

	public static PaginatedCondition parsePaginatedCondition(
			DataTablesCondition condition) {
		int page = condition.getStart() / condition.getLength() + 1;// 计算页码
		int pageSize = condition.getLength();// 获取每页数量
		List<Map<OrderCriterias, String>> orderList = condition.getOrder();// 排序map
		List<Map<ColumnCriterias, String>> columnsList = condition.getColumns();// 列map
		List<Map<OrderCriterias, String>> orderColumn = dealOrder(orderList,
				columnsList);// 获取排序列Map

		PaginatedCondition paginatedCondition = new PaginatedCondition();
		paginatedCondition.setPage(page);
		paginatedCondition.setRows(pageSize);
		if (orderColumn != null && !orderColumn.isEmpty()) {
			List<String> orders = new ArrayList<String>();
			List<String> sorts = new ArrayList<String>();
			for(Map<OrderCriterias, String> order: orderColumn){
				orders.add(order.get(OrderCriterias.column));
				sorts.add(order.get(OrderCriterias.dir));
			}
			if(!orders.isEmpty()){
				paginatedCondition.setSort(convertListToArray(orders));
			}
			if(!sorts.isEmpty()){
				paginatedCondition.setOrder(convertListToArray(sorts));
			}
		}
		return paginatedCondition;
	}

	/**
	 * 未为不破坏分页组件内容，暂时只支持单列排序
	 * 
	 * @param orderList
	 *            排序Map
	 * @param columnsList
	 *            列Map
	 * @return
	 */
	public static List<Map<OrderCriterias, String>> dealOrder(
			List<Map<OrderCriterias, String>> orderList,
			List<Map<ColumnCriterias, String>> columnsList) {
		if (orderList == null || orderList.isEmpty() || columnsList == null
				|| columnsList.isEmpty()) {
			return null;
		}
		Map<OrderCriterias, String> tmp = null;
		List<Map<OrderCriterias, String>> list = new ArrayList<Map<OrderCriterias,String>>();
		for(Map<OrderCriterias, String> order: orderList){
			String columnIndex = order.get(OrderCriterias.column);
			Map<ColumnCriterias, String> columnObj = columnsList.get(Integer
					.parseInt(columnIndex));
			if (columnObj == null) {
				continue;
			}
			String columnName = columnObj.get(ColumnCriterias.data);
			if (StringUtil.isEmpty(columnName)) {
				continue;
			}
			tmp = new HashMap<OrderCriterias, String>();
			tmp.put(OrderCriterias.column, columnName);
			tmp.put(OrderCriterias.dir, order.get(OrderCriterias.dir));
			list.add(tmp);
		}
		return list;
	}

	public static String getColumnSearchValue(String column,
			DataTablesCondition condition) {
		List<Map<ColumnCriterias, String>> columns = condition.getColumns();
		if (columns == null) {
			return null;
		}
		for (Map<ColumnCriterias, String> col : columns) {
			String data = col.get(ColumnCriterias.data);
			String searchValue = col.get(ColumnCriterias.searchValue);
			if (!StringUtil.isEmpty(data) && !StringUtil.isEmpty(searchValue)
					&& column.trim().equalsIgnoreCase(data)) {
				return searchValue;
			}
		}
		return null;
	}
	
	private static String getActualFilterName(String filterName, String[] selects, String tag){
		if(StringUtil.isEmpty(filterName) || selects == null || StringUtil.isEmpty(tag)){
			throw new RuntimeException("非法的参数。");
		}
		String name = filterName.split(tag)[0].trim();
		if(StringUtil.isEmpty(name)){
			throw new RuntimeException("非法的过滤参数。");
		}
		if(!name.contains(".")){
			return name;
		}
		for (String sel : selects) {
			String[] preSel = null;
			if(sel.indexOf("as") != -1){
				preSel = sel.split("as");
			}else{
				preSel = sel.split(" ");
			}
			if(preSel == null || preSel.length != 2){
				throw new RuntimeException("非法的select字段。");
			}
			if(name.equals(preSel[0].trim())){
				return preSel[1].trim();
			}
		}
		return null;
	}
	
	private static String[] convertListToArray(List<String> list) {
		String[] arr = new String[list.size()];
		int i = 0;
		for (String str : list) {
			arr[i] = str;
			i++;
		}
		return arr;
	}
}
