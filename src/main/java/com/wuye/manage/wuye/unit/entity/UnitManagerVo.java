package com.wuye.manage.wuye.unit.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class UnitManagerVo extends Unit {

    private String managerName;
}
