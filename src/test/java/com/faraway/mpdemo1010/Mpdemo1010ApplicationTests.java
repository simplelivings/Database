package com.faraway.mpdemo1010;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.faraway.mpdemo1010.entity.User;
import com.faraway.mpdemo1010.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class Mpdemo1010ApplicationTests {

    @Autowired
    private UserMapper userMapper;

    //查询user表中的所有数据
    @Test
    public void findAll() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

    @Test
    public void addUser() {
        User user = new User();
        user.setName("wangqing");
        user.setId(6L);
        user.setAge(30);
        user.setEmail("ljfd@qq.com");

        int insert = userMapper.insert(user);
        System.out.println("insert:" + insert);
    }

    //修改操作
    @Test
    public void updateUser() {
        User user = new User();
        user.setId(2L);
        user.setAge(60);

        int row = userMapper.updateById(user);
        System.out.println(row);
    }

    //测试乐观锁
    @Test
    public void testOptimisticLocker() {
        //根据id先查询数据
        User user = userMapper.selectById(1253508992082874370L);

        //进行修改
        user.setAge(200);
        userMapper.updateById(user);
    }

    //测试多id批量查询
    @Test
    public void testSelectDemo() {
        List<User> users = userMapper.selectBatchIds(Arrays.asList(2L, 1252848314690961409L, 1252857451881598978L));
        System.out.println(users);
    }

    //测试分页查询
    @Test
    public void testPage() {
        //1 创建Page对象，传入 当前页 和 每页显示记录数 2个参数；
        Page<User> page = new Page<>(1, 3);

        //2 调用mp查询方法，把分页所有数据封装到page对象中
        userMapper.selectPage(page, null);

        // 3 通过page对象，获取分页数据；
        System.out.println("+++++++++++++++++");
        System.out.println(page.getCurrent());
        System.out.println(page.getRecords());
        System.out.println(page.getSize());
        System.out.println(page.getTotal());
        System.out.println(page.getPages());
        System.out.println(page.getClass());

        System.out.println("-----------------");
        System.out.println(page.hasNext());
        System.out.println(page.hasPrevious());
    }

    //测试单个删除
    @Test
    public void testDeleteById() {
        int i = userMapper.deleteById(4L);
        System.out.println("delete" + i);
    }

    //测试批量删除
    @Test
    public void testDeleteBatches() {
        int i = userMapper.deleteBatchIds(Arrays.asList(2L, 1252869478779858945L));
    }

    //测试mp实现复杂查询操作
    @Test
    public void testSelectQuery() {
        //创建QueryWrapper对象
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        //通过QueryWrapper设置条件
        //ge大于等于 gt大于 le小于等于 lt小于
        //查询age≥30的数据
//        queryWrapper.ge("age",30);


        //eq等于  ne不等于
//        queryWrapper.eq("name","lixiang");

        //between 范围
//        queryWrapper.between("age",20,30);

        //like 模糊查询
//        queryWrapper.like("name","li");

        //oderByDesc降序排序  oderByAsc升序排序
//        queryWrapper.orderByDesc("id");

        //last直接拼接到sql语句最后
//        queryWrapper.last("limit 1");

        //指定要查询的列
        queryWrapper.select("id", "name");


        List<User> users = userMapper.selectList(queryWrapper);
        System.out.println("users:" + users);
    }


}
