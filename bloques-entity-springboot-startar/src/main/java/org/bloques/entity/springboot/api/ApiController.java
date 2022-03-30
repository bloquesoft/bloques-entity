/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-springboot-startar
 * FileName:ApiController.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.springboot.api;

import org.bloques.entity.core.executor.EntityExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/ebase/api/")
public class ApiController {

    @Autowired
    private EntityExecutor entityExecutor;

    @RequestMapping(value = "{packageName}/{entityName}/add", method = RequestMethod.POST)
    public OneResponse add(
            @PathVariable(value = "packageName") String packageName,
            @PathVariable(value = "entityName") String entityName,
            @RequestBody Object value) {

        Object result = entityExecutor.add(packageName, entityName, value);
        return new OneResponse(result);
    }

    @RequestMapping(value = "{packageName}/{entityName}/{id}", method = RequestMethod.POST)
    public OneResponse findById(
            @PathVariable(value = "packageName") String packageName,
            @PathVariable(value = "entityName") String entityName,
            @PathVariable(value = "id") Long id,
            @RequestBody(required = false) String graph) {

        Object result = entityExecutor.findById(packageName, entityName, id, graph);
        return new OneResponse(result);
    }

    @RequestMapping(value = "{packageName}/{entityName}/list", method = RequestMethod.POST)
    public ListResponse findList(
            @PathVariable(value = "packageName") String packageName,
            @PathVariable(value = "entityName") String entityName,
            @RequestBody() FindListRequest request
    ) {
        List<Object> list = entityExecutor.findList(packageName, entityName, request.getMatch(), request.getGraph());
        return new ListResponse(list);
    }

    @RequestMapping(value = "{packageName}/{entityName}/update", method = RequestMethod.POST)
    public BaseApiResponse update(
            @PathVariable(value = "packageName") String packageName,
            @PathVariable(value = "entityName") String entityName,
            @RequestBody Object value) {
        entityExecutor.update(packageName, entityName, value);
        return new BaseApiResponse();
    }

    @RequestMapping(value = "{packageName}/{entityName}/delete/{id}", method = RequestMethod.POST)
    public BaseApiResponse delete(
            @PathVariable(value = "packageName") String packageName,
            @PathVariable(value = "entityName") String entityName,
            @PathVariable(value = "id") Long id) {

        entityExecutor.delete(packageName, entityName, id);
        return new BaseApiResponse();
    }
}