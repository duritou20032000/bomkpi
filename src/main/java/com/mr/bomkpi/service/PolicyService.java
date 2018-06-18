package com.mr.bomkpi.service;

import com.mr.bomkpi.entity.User;
import com.mr.bomkpi.repository.PolicyRepository;
import com.mr.bomkpi.repository.UserRepository;
import com.mr.bomkpi.util.PasswordUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PolicyService {
    public static Logger logger = LoggerFactory.getLogger(PolicyService.class);
    @Autowired
    private PolicyRepository policyRepository;




}
