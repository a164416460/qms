package com.jrs_qms_llh.yueqian.service.impl;

import java.util.List;

import com.jrs_qms_llh.commons.utils.Page;
import com.jrs_qms_llh.yueqian.dao.IBusinessTypeDao;
import com.jrs_qms_llh.yueqian.dao.impl.BusinessTypeDaoImpl;
import com.jrs_qms_llh.yueqian.domain.BusinessType;
import com.jrs_qms_llh.yueqian.service.IBusinessTypeService;

public class BusinessTypeServiceImpl implements IBusinessTypeService {
	private IBusinessTypeDao businessTypeDao = new BusinessTypeDaoImpl();

	

	@Override
	public List<BusinessType> listAll() {
		return businessTypeDao.listAll();
	}

	@Override
	public int addBtType(BusinessType bType) {
		return businessTypeDao.save(bType);
	}

	@Override
	public Page<BusinessType> getPages(String cond, int currentPage,
			int pageSize) {
		Page<BusinessType> page=businessTypeDao.getOnePage(cond, currentPage, pageSize);	
		return page;
	}

	@Override
	public boolean deleteSelectAll(int[] btNos) {
		boolean isDel= false;
		try {
			for (int i = 0; i < btNos.length; i++) {
				businessTypeDao.deleteById(btNos[i]);	
			}
			isDel=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return isDel;
	}

	

	@Override
	public BusinessType getBusinessTypeById(int btNo) {
	
		return businessTypeDao.getById(btNo);
	}

	@Override
	public int update(BusinessType btType) {
		// TODO 自动生成的方法存根
		return businessTypeDao.update(btType);
	}

}
