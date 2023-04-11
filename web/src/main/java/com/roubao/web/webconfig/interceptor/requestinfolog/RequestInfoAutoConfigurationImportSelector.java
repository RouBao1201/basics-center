package com.roubao.web.webconfig.interceptor.requestinfolog;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 自动装配
 * 
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/4/7
 **/
public class RequestInfoAutoConfigurationImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[] {
            RequestInfoWebConfiguration.class.getName()
        };
    }
}
