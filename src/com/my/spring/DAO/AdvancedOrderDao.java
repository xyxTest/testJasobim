package com.my.spring.DAO;

import com.my.spring.model.AdvancedOrder;
import com.my.spring.utils.DataWrapper;

import java.util.List;

public interface AdvancedOrderDao {
	boolean addAdvancedOrder(AdvancedOrder ps);
    boolean deleteAdvancedOrder(Long id);
    boolean updateAdvancedOrder(AdvancedOrder ps);
    AdvancedOrder getById(Long id);
    DataWrapper<List<AdvancedOrder>> getAdvancedOrdersList(Integer pageIndex, Integer pageSize, AdvancedOrder advancedOrder, int adminFlag);
    DataWrapper<List<AdvancedOrder>> getAdvancedOrderByUserId(Long userId);
	boolean updateConstructionTask(AdvancedOrder ct);
}
