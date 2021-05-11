package com.example.demo.sentinel;

import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
/**
 * @author yuhan
 */
@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "rules")
public class RulesProperties {
    private List<DegradeRuleProp> degrade;
    private List<FlowRuleProp> flow;
}
