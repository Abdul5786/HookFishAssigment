package com.Abdul.HookiFish.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ApiResponse
{
    private String message;
    private boolean success;
}
