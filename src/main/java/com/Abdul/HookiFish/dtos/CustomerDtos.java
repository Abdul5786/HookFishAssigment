package com.Abdul.HookiFish.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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
    private List<GeneratedBillDto> generatedBillDto;
}
