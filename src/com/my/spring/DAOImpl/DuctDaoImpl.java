package com.my.spring.DAOImpl;

import com.my.spring.DAO.BaseDao;
import com.my.spring.DAO.DuctDao;
import com.my.spring.enums.ErrorCodeEnum;
import com.my.spring.model.Duct;
import com.my.spring.utils.DaoUtil;
import com.my.spring.utils.DataWrapper;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.procedure.ProcedureCall;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ParameterMode;

@Repository
public class DuctDaoImpl extends BaseDao<Duct> implements DuctDao {

    @Override
    public boolean addDuct(Duct Duct) {
        return save(Duct);
    }

    @Override
    public boolean deleteDuct(Long id) {
        return delete(get(id));
    }

    @Override
    public boolean updateDuct(Duct Duct) {
        return update(Duct);
    }

    @SuppressWarnings("unchecked")
	@Override
    public DataWrapper<List<Duct>> getDuctList(Integer pageSize,Integer pageIndex,Duct duct,String content) {
    	DataWrapper<List<Duct>> retDataWrapper = new DataWrapper<List<Duct>>();
        List<Duct> ret = new ArrayList<Duct>();
        Session session = getSession();
        Criteria criteria = session.createCriteria(Duct.class);
        criteria.addOrder(Order.desc("date"));
        if(duct.getState()!=null){
        	criteria.add(Restrictions.eq("state", duct.getState()));
        }
        if(duct.getProjectId()!=null){
        	criteria.add(Restrictions.eq("projectId", duct.getProjectId()));
        }
        if(duct.getName()!=null){
        	criteria.add(Restrictions.like("name", "%"+duct.getName()+"%"));
        }
        if(duct.getUserId()!=null){
        	criteria.add(Restrictions.eq("userId", duct.getUserId()));
        }
        if(content!=null){
        	criteria.add(Restrictions.like("name", "%"+content+"%"));
        }
        if(duct.getProjectId()!=null){
        	criteria.add(Restrictions.eq("projectId", duct.getProjectId()));
        }	
        /////////////////////////////////////
        criteria.setProjection(Projections.rowCount());
        criteria.setProjection(Projections.groupProperty("state").as("num"));
        /////////////////////////////////////
   
        if (pageSize == null) {
			pageSize = 10;
		}
        if (pageIndex == null) {
			pageIndex = 1;
		}
        
        // 取总页数
        criteria.setProjection(Projections.rowCount());
        int totalItemNum = ((Long)criteria.uniqueResult()).intValue();
        int totalPageNum = DaoUtil.getTotalPageNumber(totalItemNum, pageSize);

        // 真正取值
        criteria.setProjection(null);
        if (pageSize > 0 && pageIndex > 0) {
            criteria.setMaxResults(pageSize);// 最大显示记录数
            criteria.setFirstResult((pageIndex - 1) * pageSize);// 从第几条开始
        }
        try {
            ret = criteria.list();
        }catch (Exception e){
            e.printStackTrace();
        }
        retDataWrapper.setData(ret);
        retDataWrapper.setTotalNumber(totalItemNum);
        retDataWrapper.setCurrentPage(pageIndex);
        retDataWrapper.setTotalPage(totalPageNum);
        retDataWrapper.setNumberPerPage(pageSize);
        return retDataWrapper;
    }

	@SuppressWarnings("unchecked")
	@Override
	public DataWrapper<List<Duct>> getDuctByProjectId(Long projectId,Duct duct) {
		DataWrapper<List<Duct>> dataWrapper=new DataWrapper<List<Duct>>();
		List<Duct> ret = new ArrayList<Duct>();
        Session session = getSession();
        Criteria criteria = session.createCriteria(Duct.class);
        criteria.add(Restrictions.eq("projectId",projectId));
        if(duct.getId()!=null){
        	criteria.add(Restrictions.eq("id",duct.getId()));
        }
        try {
            ret = criteria.list();
        }catch (Exception e){
            e.printStackTrace();
        }
        if (ret != null && ret.size() > 0) {
        	dataWrapper.setData(ret);
		}else{
			dataWrapper.setErrorCode(ErrorCodeEnum.Target_Not_Existed);
		}
		return dataWrapper;
	}
	@SuppressWarnings("unchecked")
	@Override
	public DataWrapper<Duct> getDuctBySelfId(String selfId) {
		DataWrapper<Duct> dataWrapper=new DataWrapper<Duct>();
		List<Duct> ret = new ArrayList<Duct>();
        Session session = getSession();
        Criteria criteria = session.createCriteria(Duct.class);
        criteria.add(Restrictions.eq("selfId",selfId));
        try {
            ret = criteria.list();
        }catch (Exception e){
            e.printStackTrace();
        }
        if (ret != null && ret.size() > 0) {
        	dataWrapper.setData(ret.get(0));
		}else{
			dataWrapper.setErrorCode(ErrorCodeEnum.Target_Not_Existed);
		}
		return dataWrapper;
	}

	@Override
	public boolean addDuctList(List<Duct> ductList) {
		return saveList(ductList);
	}
	@Override
	public boolean exportQuantity(String filePath,Long projectId) {
		// TODO Auto-generated method stub
		
		Session session=getSession();
		try{
			ProcedureCall procedureCall = session.createStoredProcedureCall("exportDuct");
			procedureCall.registerParameter("file_path", String.class, ParameterMode.IN).bindValue(filePath);
			procedureCall.registerParameter("project_id", Long.class, ParameterMode.IN).bindValue(projectId);
			procedureCall.getOutputs();
	    }catch(Exception e){
	        e.printStackTrace();
	        return false;
	    }
		
		return true;
	}

	@Override
	public Duct getDuctById(Long id) {
		return get(id);
	}


}