package com.example.liujishun.mysamples.test.Entity;

import com.litesuits.orm.db.annotation.Mapping;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;
import com.litesuits.orm.db.enums.Relation;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by liujishun on 2016/11/17.
 */
@Table("test1")
public class Test1 implements Serializable{
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    public long id;
    // 指定一对多关系
    @Mapping(Relation.OneToMany)
    public ArrayList<Test2> mList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ArrayList<Test2> getList() {
        return mList;
    }

    public void setList(ArrayList<Test2> list) {
        mList = list;
    }
}
