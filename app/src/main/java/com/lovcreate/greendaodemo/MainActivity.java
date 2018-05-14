package com.lovcreate.greendaodemo;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lovcreate.greendaodemo.bean.User;
import com.lovcreate.greendaodemo.gen.UserDao;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    @Bind(R.id.user_id)
    TextView userId;
    @Bind(R.id.go_list)
    TextView goList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
        Log.e("SIGN", "signature= " + getSignature("com.lovcreate.greendaodemo") + "");
    }

    private void initData() {
        List<User> userList = new ArrayList<>();
        User user1 = new User();
        user1.setName("小明");
        user1.setSex("男");
        user1.setPhone("13233322211");
        User user2 = new User();
        user2.setName("小丽");
        user2.setSex("女");
        user2.setPhone("13233333311");
        User user3 = new User();
        user3.setName("小红");
        user3.setSex("女");
        user3.setPhone("13233311111");
        User user4 = new User();
        user4.setName("小美");
        user4.setSex("女");
        user4.setPhone("13233344411");
        User user5 = new User();
        user5.setName("小刚");
        user5.setSex("男");
        user5.setPhone("13233355511");
        User user6 = new User();
        user6.setName("小华");
        user6.setSex("男");
        user6.setPhone("13233366611");

        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);
        userList.add(user5);
        userList.add(user6);

        GreenDaoManager.getInstance().getmDaoSession().getUserDao().insertOrReplaceInTx(userList);
    }

    @OnClick({R.id.query, R.id.go_list})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.query:
                String n = content.getText().toString();
                if (TextUtils.isEmpty(n)) {
                    Toast.makeText(getApplicationContext(), "请输入姓名以查询", Toast.LENGTH_SHORT).show();
                    return;
                }
                List<User> result = GreenDaoManager.getInstance().getmDaoSession().getUserDao()
                        .queryBuilder().where(UserDao.Properties.Name.eq(n)).build().list();
                if (!result.isEmpty()) {
                    User user = result.get(0);
                    userId.setText(String.valueOf(user.getId()));
                    name.setText(user.getName());
                    sex.setText(user.getSex());
                    phone.setText(user.getPhone());
                } else {
                    Toast.makeText(getApplicationContext(), "查无此人", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.go_list:
                startActivity(new Intent(MainActivity.this, SchoolListActivity.class));
                break;
        }
    }

    private int getSignature(String packageName) {
        PackageManager pm = this.getPackageManager();
        PackageInfo pi = null;
        int sign = 0;
        try {
            pi = pm.getPackageInfo(packageName, PackageManager.GET_SIGNATURES);
            Signature[] s = pi.signatures;
            sign = s[0].hashCode();
        } catch (PackageManager.NameNotFoundException e) {
            sign = -1;
            e.printStackTrace();
        }
        return sign;
    }
}
