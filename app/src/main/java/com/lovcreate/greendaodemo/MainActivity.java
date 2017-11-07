package com.lovcreate.greendaodemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lovcreate.greendaodemo.bean.User;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @Bind(R.id.content)
    EditText content;
    @Bind(R.id.name)
    TextView name;
    @Bind(R.id.sex)
    TextView sex;
    @Bind(R.id.phone)
    TextView phone;
    @Bind(R.id.query)
    TextView query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        initData();

        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n = content.getText().toString();
                if (TextUtils.isEmpty(n)) {
                    Toast.makeText(getApplicationContext(), "请输入姓名以查询", Toast.LENGTH_SHORT).show();
                    return;
                }
                List<User> result = GreenDaoManager.getInstance().getmDaoSession().getUserDao()
                        .queryBuilder().where(UserDao.Properties.Name.eq(n)).build().list();
                List<User> all = GreenDaoManager.getInstance().getmDaoSession().loadAll(User.class);
                if (!result.isEmpty()) {
                    User user = result.get(0);
                    name.setText(user.getName());
                    sex.setText(user.getSex());
                    phone.setText(user.getPhone());
                } else {
                    Toast.makeText(getApplicationContext(), "查无此人", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initData() {
        List<User> userList = new ArrayList<>();
        User user1 = new User(1, "小明", "男", "13233322211");
        User user2 = new User(2, "小丽", "女", "13233333311");
        User user3 = new User(3, "小红", "女", "13233311111");
        User user4 = new User(4, "小美", "女", "13233344411");
        User user5 = new User(5, "小刚", "男", "13233355511");
        User user6 = new User(6, "小华", "男", "13233366611");

        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);
        userList.add(user5);
        userList.add(user6);

        GreenDaoManager.getInstance().getmDaoSession().getUserDao().insertOrReplaceInTx(userList);
    }
}
