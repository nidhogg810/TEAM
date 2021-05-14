package com.example.demo.common.config;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.example.demo.common.constants.SentinelConstants;
import com.example.demo.sentinel.DegradeRuleProp;
import com.example.demo.sentinel.FlowRuleProp;
import com.example.demo.sentinel.RulesProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yuhan
 */
@Configuration
@Slf4j
public class SentinelConfig {
    @Autowired
    private RulesProperties properties ;

    @Bean
    public SentinelResourceAspect sentinelResourceAspect(){
        initRules();
        return new SentinelResourceAspect();
    }
    /**
     * 以下进行规则配置，主要有流量控制规则 (FlowRule)、熔断降级规则 (DegradeRule)、系统保护规则 (SystemRule)、访问控制规则 (AuthorityRule)
     */
//    @PostConstruct
    private void initRules(){
        log.info("start load Sentinel Rules!");
        initFlowRules();
        initFlowRules();
    }

    /**
     * 初始化流量控制规则
     */
    private void initFlowRules(){
        List<FlowRuleProp> flowRulePropList = properties.getFlow();
        List<FlowRule> flowRuleList = new ArrayList<>();
        for(FlowRuleProp flowRuleProp: flowRulePropList) {
            FlowRule flowRule = new FlowRule();
            flowRule = (FlowRule) flowRule.setResource(flowRuleProp.getResource());
            flowRule = flowRule.setGrade(flowRuleProp.getGrade());
            flowRule = flowRule.setCount(flowRuleProp.getCount());
            flowRuleList.add(flowRule);
        }
        FlowRuleManager.loadRules(flowRuleList);
    }

    /**
     * 初始化熔断规则
     */
    private void initDegradeRules(){
        List<DegradeRuleProp> degradeRulePropList = properties.getDegrade();
        List<DegradeRule> degradeRuleList = new ArrayList<>();
        for(DegradeRuleProp degradeRuleProp : degradeRulePropList) {
            DegradeRule degradeRule = new DegradeRule(degradeRuleProp.getResource());
            /**
             * 设置降级规则模式：
             * Degrade strategy (0: average RT响应时间, 1: exception ratio, 2: exception count 近1分钟内异常数量).
             */
            degradeRule.setGrade(degradeRuleProp.getGrade());
            /**
             * 可触发熔断的最小请求数（在活动统计时间跨度内）
             * 熔断触发的最小请求数，请求数小于该值时即使异常比率超出阈值也不会熔断
             * 1,7.0引入
             */
            degradeRule.setMinRequestAmount(degradeRuleProp.getMinRequestAmount());
            /**
             * 熔断时长，单位为 s
             */
            degradeRule.setTimeWindow(degradeRuleProp.getTimeWindow());
            /**
             *慢调用比例模式下为慢调用临界 RT（超出该值计为慢调用）；异常比例/异常数模式下为对应的阈值
             */
            degradeRule.setCount(degradeRuleProp.getCount());

            degradeRuleList.add(degradeRule);
        }
        DegradeRuleManager.loadRules(degradeRuleList);
    }
}
