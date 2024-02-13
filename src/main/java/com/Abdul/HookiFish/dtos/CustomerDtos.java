package com.Abdul.HookiFish.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDtos
{
    private Long customerId;
    private String name;
    private String email;
    private String contactNo;
}
