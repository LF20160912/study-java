package com.mike.morphia.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mongodb.morphia.annotations.Embedded;

/*
  Author:Mike
  创建时间：2020/1/18
  描述：
*/
@Embedded
@Getter
@Setter
@NoArgsConstructor
public class Address {
    private String country;
    private String province;
    private String city;
}
