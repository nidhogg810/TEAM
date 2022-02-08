package com.example.demo.common.config.selector;

import com.example.demo.common.constants.HCacheType;
import com.example.demo.common.utils.HCacheConfigurationsUtil;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class HCachingConfigurationSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        HCacheType[] types = HCacheType.values();
        String[] imports = new String[types.length];
        for (int i = 0; i < types.length; i++) {
            imports[i] = HCacheConfigurationsUtil.getConfiguration(types[i]);
        }
        return imports;
    }
}
