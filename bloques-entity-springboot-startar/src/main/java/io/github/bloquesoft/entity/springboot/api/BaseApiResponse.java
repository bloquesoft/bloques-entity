/*
 * Copyright (c) 2022-2023 zhangjian Reserved.
 * Project:bloques-entity-springboot-startar
 * FileName:BaseApiResponse.java
 * Author:zhangjian
 * date:2022/4/2 下午9:08
 * Version:0.0.1
 */

package io.github.bloquesoft.entity.springboot.api;

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
