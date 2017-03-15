package com.example.liujishun.mysamples.test;

import android.os.Bundle;
import android.widget.Toast;

import com.example.liujishun.mysamples.App;
import com.example.liujishun.mysamples.base.BaseActivity;
import com.example.liujishun.mysamples.di.component.DaggerSqliteComponent;
import com.example.liujishun.mysamples.test.Entity.Test1;
import com.example.liujishun.mysamples.test.Entity.Test2;
import com.mbase.monch.utils.ThreadUtils;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;


/**
 * Created by monch on 15/12/7.
 */
public class SqliteTest extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerSqliteComponent.builder().appComponent(App.getAppComponent()).build().inject(this);
        click(1);
//        click(0);
//        click(0);
//        click(4);
       

    }

    private String[] buttons = new String[]{
            "插入",
            "插入or修改",
            "删除",
            "修改",
            "查询"
    };

    private ExecutorService executor = ThreadUtils.createSingleExecutor();

    private boolean checkTaskExecutor() {
        return executor != null && !executor.isShutdown();
    }


    private volatile Student lastOperationEntity;

    protected void click(int id) {
        if (!checkTaskExecutor())
            return;
        final String name = buttons[id];
        switch (id) {
            case 0:
//                executor.submit(new Runnable() {
//                    @Override
//                    public void run() {
//                        LiteOrm db = MyApplication.getLiteOrm();
//                        Student s = new Student(getStudentName(), getStudentGender());
//                        long id = db.insert(s);
//                        Logger.i("[" + name + "]" + "插入：" + id + "，信息：" + s.toString());
//                        lastOperationEntity = s;
//                    }
//                });

                executor.submit(new Runnable() {
                    @Override
                    public void run() {

                        Test2 test2 = new Test2();
                        test2.setId(3);
                        test2.setName("liujishun");

                        Test1 test1 = new Test1();
                        ArrayList<Test2> list = new ArrayList<Test2>();
                        list.add(test2);
                        
                        test1.setList(list);
                        
                        db.cascade().insert(test1);
                    }
                });
                break;
            case 1:

                executor.submit(new Runnable() {
                    @Override
                    public void run() {

                        Student s = new Student(getStudentName(), getStudentGender());
                        s.id = 1;
                        long id = db.save(s);
                        Logger.i("[" + name + "]" + "插入or修改ID：" + id + "，信息：" + s.toString());
                        lastOperationEntity = s;
                    }
                });

//                executor.submit(new Runnable() {
//                    @Override
//                    public void run() {
//                        LiteOrm db = MyApplication.getLiteOrm();
//                        ArrayList<Test1> list1 = db.query(Test1.class);
//                        ArrayList<Test2> list2 = db.query(Test2.class);
//                        
//                        db.mapping(list1,list2);
//                        
//                        
//                        Logger.e(list1.get(0).getList().get(0).getName());
//                        
//                    }
//                });
                break;
            case 2:
                executor.submit(new Runnable() {
                    @Override
                    public void run() {
                        if (lastOperationEntity == null) {
                            Toast.makeText(mActivity,"请插入一条信息后再执行删除",Toast.LENGTH_LONG).show();
                            return;
                        }

                        int count = db.delete(lastOperationEntity);
                        Logger.i("[" + name + "]" + "共删除数量为：" + count);
                        lastOperationEntity = null;
                    }
                });
                break;
            case 3:
                executor.submit(new Runnable() {
                    @Override
                    public void run() {
                        if (lastOperationEntity == null) {
                            Toast.makeText(mActivity,"请插入一条信息后再执行修改",Toast.LENGTH_LONG).show();
                            return;
                        }
                        Logger.i("[" + name + "前]" + lastOperationEntity.toString());
                        lastOperationEntity.name = getStudentName();
                        lastOperationEntity.gender = getStudentGender();

                        int count = db.update(lastOperationEntity);
                        Logger.i("[" + name + "后]操作数：" + count + "，信息：" + lastOperationEntity.toString());
                    }
                });
                break;
            case 4:
                executor.submit(new Runnable() {
                    @Override
                    public void run() {
                        List<Student> data = db.query(Student.class);
                        Logger.i("[" + name + "]" + "查询结果：" + data);
                    }
                });
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (executor != null)
            executor.shutdown();
    }

    private String getStudentName() {
        return "学生测试姓名";
    }

    private int getStudentGender() {
        return new Random().nextBoolean() ? 1 : 0;
    }

    private void print(String name, Object... objects) {
        Logger.i("[" + name + "]" + objects);
    }

}
