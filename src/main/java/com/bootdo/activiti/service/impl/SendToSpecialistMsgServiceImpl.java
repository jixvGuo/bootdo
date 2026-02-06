package com.bootdo.activiti.service.impl;

import com.bootdo.activiti.service.SendToSpecialistMsgService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.impl.persistence.entity.VariableInstance;
import org.activiti.engine.impl.pvm.delegate.ActivityExecution;
import org.activiti.engine.task.*;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.Serializable;
import java.util.*;

/**
 * 发送消息给专家
 */
@Service
public class SendToSpecialistMsgServiceImpl implements SendToSpecialistMsgService, Serializable {


    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

    }
}
