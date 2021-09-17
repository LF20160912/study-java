package com.mike.morphia.domain;

import lombok.*;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

/*
  Author:Mike
  创建时间：2020/1/18
  描述：
*/
@Entity(value = "t_user",noClassnameStored = true)//noClassnameStored表示是否存储类名
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    @Id
    private ObjectId user_id;

    private String account;

    @Property("user_name")
    private String userName;

    @Embedded//子对象
    private Address address;
}
