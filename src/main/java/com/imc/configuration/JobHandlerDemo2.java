package com.imc.configuration;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;

@JobHandler("demoHandler2")
public class JobHandlerDemo2 extends IJobHandler{
    @Override
    public ReturnT<String> execute(String s) throws Exception {
        XxlJobLogger.log("XXL-JOB, Hello World.");
        return SUCCESS;
    }
}
