/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-springboot-startar
 * FileName:DefinitionApi.java
 * Author:zhangjian
 * date:2022/4/4 下午9:25
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.springboot.api;

import io.github.bloquesoft.entity.core.definition.EntityDefinition;
import io.github.bloquesoft.entity.core.definition.PackageDefinition;
import io.github.bloquesoft.entity.core.register.EntityDefinitionRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/bloques/entity/definition/api/")
public class DefinitionApiController {

    @Autowired
    private EntityDefinitionRegister register;

    @RequestMapping(value = "allPackages", method = RequestMethod.GET)
    public ListResponse<PackageDefinition> allPackages() {
        List<PackageDefinition> allPackageDefinitions = register.getAllPackages().stream().collect(Collectors.toList());
        return new ListResponse<>(allPackageDefinitions);
    }

    @RequestMapping(value = "entities", method = RequestMethod.GET)
    public ListResponse<EntityDefinition> allEntities(String packageName) {
        List<EntityDefinition> entities = register.getEntities(packageName).stream().collect(Collectors.toList());
        return new ListResponse<EntityDefinition>(entities);
    }
}
