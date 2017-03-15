package com.example.liujishun.mysamples.test.Entity;

import com.litesuits.orm.db.annotation.Mapping;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;
import com.litesuits.orm.db.enums.Relation;

import java.io.Serializable;

/**
 * Created by liujishun on 2016/11/17.
 */
@Table("test2")
public class Test2 implements Serializable{
    @PrimaryKey(AssignType.BY_MYSELF)
    public long id;
    public String name;

    @Mapping(Relation.ManyToOne)
    public Test1 test1;

    public Test1 getTest1() {
        return test1;
    }

    public void setTest1(Test1 test1) {
        this.test1 = test1;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
