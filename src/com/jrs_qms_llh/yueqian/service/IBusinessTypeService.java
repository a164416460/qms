package com.jrs_qms_llh.yueqian.service;

import java.util.List;


import com.jrs_qms_llh.commons.utils.Page;
import com.jrs_qms_llh.yueqian.domain.BusinessType;

public interface IBusinessTypeService {
    /**
     * 添加业务类型信息
     *
     * @return
     */
    public int addBtType(BusinessType bType);

    /**
     * 把分页信息封装到PageBean中，一直返回到ui用于显示
     *
     * @return
     */
    public Page<BusinessType> getPages(String cond, int currentPage, int pageSize);


    /**
     * 批量删除选中的行
     *
     * @param btNos
     * @return
     */
    public boolean deleteSelectAll(int[] btNos);

    /**
     * 查出所有业务类型
     *
     * @return
     */
    public List<BusinessType> listAll();

    /**
     * 根据员工Id查询业务类型
     *
     * @param btNo
     * @return
     */
    public BusinessType getBusinessTypeById(int btNo);

    /**
     * 修改业务类型信息
     *
     * @return
     */
    public int update(BusinessType btType);

}
