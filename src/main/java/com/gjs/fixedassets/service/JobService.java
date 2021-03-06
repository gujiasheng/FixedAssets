package com.gjs.fixedassets.service;

import com.gjs.fixedassets.entity.Fixedcard;
import com.gjs.fixedassets.entity.Job;
import com.gjs.fixedassets.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JobService {

    List<Job> selectAllJobByCompanyId(Integer companyId);

    String selectJName(Integer jobId);

    List<Job> selectJobByCompanyId(@Param("companyId") Integer companyId, @Param("page") Integer page, @Param("limit") Integer limit, @Param("jobName") String jobName);

    List<Job> selectJobCount(@Param("companyId") Integer companyId, @Param("jobName") String jobName);

    void addJob(Job job);

    Job selectJobByPk(@Param("jobId") Integer jobId);

    void deleteJob(Integer jobId);

    void updateJob(Job job);

    List<User> selectUserByJob(Integer jobId);
}
