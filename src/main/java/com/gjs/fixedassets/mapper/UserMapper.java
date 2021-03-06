package com.gjs.fixedassets.mapper;

import com.gjs.fixedassets.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    //登录
    User selectUserByNamePSW(String userName, String password, Integer companyId);

    // 根据分页，条件查询数据
    List<User> selectByCompanyId(Integer companyId, Integer page, Integer limit, String userName, Integer phone, Integer departmentId, Integer roleId, Integer isStatus, String workId);

    //查询用户总数据
    List<User> selectAllUserCount(Integer companyId, String userName, Integer phone, Integer departmentId, Integer roleId, Integer isStatus, String workId);

    //根据人员id查人
    User selectUserByUserId(Integer userId);

    //添加人员
    void addUser(User user);

    int addUser2(User user);
    //修改人员
    void updateUser(User user);

    //下拉列表人员列表
    List<User> selectAllUserByCompanyId(Integer companyId);


    //所有账户名
    List<String> selectAllAccountName(Integer companyId);

    //删除功能
    void deleteUser(Integer userId);

    List<User> selectUserByDepartmentId(Integer departId);

    List<User> selectAllUserX(Integer companyId);
}