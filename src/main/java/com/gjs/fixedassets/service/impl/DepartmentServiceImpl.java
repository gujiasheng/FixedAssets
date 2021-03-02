package com.gjs.fixedassets.service.impl;

import com.gjs.fixedassets.entity.Department;
import com.gjs.fixedassets.entity.User;
import com.gjs.fixedassets.mapper.DepartmentMapper;
import com.gjs.fixedassets.service.DepartmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Resource
    private DepartmentMapper departmentMapper;

    @Override
    public List<Department> selectDepartmentByCompanyId(Integer companyId, Integer page, Integer limit, Integer departmentId) {
        int startNum = (page - 1) * limit;
        return departmentMapper.selectDepartmentByCompanyId(companyId, startNum, limit, departmentId);
    }

    @Override
    public String selectDName(Integer departmentId) {
        return departmentMapper.selectDName(departmentId);
    }

    @Override
    public List<Department> selectAllDepartmentCount(Integer companyId, Integer departmentId) {
        return departmentMapper.selectAllDepartmentCount(companyId, departmentId);
    }

    @Override
    public List<Department> selectDepartmentByCompanyId2(Integer companyId) {
        return departmentMapper.selectDepartmentByCompanyId2(companyId);
    }
}
