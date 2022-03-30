/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-springboot-startar
 * FileName:BaseApiResponse.java
 * Author:zhangjian
 * date:2022/3/29 下午11:51
 * Version:0.0.1
 */

package org.bloques.entity.springboot.api;

import lombok.Data;

@Data
public class BaseApiResponse
{
    private boolean success;

    private String errorCode;

    private String errorMessage;

    public BaseApiResponse(String errorCode, String errorMessage)
    {
        this.success = false;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

        public BaseApiResponse()
        {
            this.success = true;
            this.errorCode = "";
            this.errorMessage = "";
        }
}
