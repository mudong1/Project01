package com.atguigu.mybatis.test;


import com.atguigu.mybatis.bean.Employee;
import com.atguigu.mybatis.bean.StuDent;
import com.atguigu.mybatis.dao.EmployeeMapperAnnotation;
import com.atguigu.mybatis.dao.EmployeeMapperInter;
import com.atguigu.mybatis.dao.StuDentMapper;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lhd
 */
public class MybatisTest {

    public static void main(String[] args) throws Exception{
        MybatisTest mybatisTest = new MybatisTest();
//        mybatisTest.test0();//有问题-->解决：映射文件与接口同名且同目录下？？？？？？？？？？？
//        mybatisTest.test00();
//        mybatisTest.test();
//        mybatisTest.test1();
//        mybatisTest.test2();
//        mybatisTest.test3();
//        mybatisTest.test4();
//        mybatisTest.test5();
//        mybatisTest.test6();//待解决????????test1可以，test6却报错：Invalid bound statement (not found): com.atguigu.mybatis.dao.EmployeeMapperInter.getEmpByIdAndLastName
        mybatisTest.test7();
    }


    /**
     * 1.根据xml配置文件（全局配置文件）创建一个SqlSessionFactory对象
     *  有数据源一些运行环境信息
     * 2.创建sql映射文件，配置每一个sql，以及sql的封装规则等。
     * 3.将sql映射文件注册到全局配置文件中
     * 4.使用SqlSessionFactory获取sqlSession对象
     * 5.使用sqlSession对象来执行crud,
     *  一个sqlSession就是代表和数据库的一次会话，用完关闭。
     *  使用sql的唯一标识来告诉MyBatis执行哪个sql。sql都是保存在sql映射文件中的
     *
     *  注意：sqlsession和connection一样都是非线程安全的，每次使用都应该去获取新的对象
     *      全局配置文件，包含数据库连接池信息，事务管理器信息，系统运行环境等信息
     */
    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        return  sqlSessionFactory;
    }

    /**
     * test0方法未成功！！！！，待修正！！？？？？？
     * @throws IOException
     */
    public  void test0() throws IOException {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/mybatis";
        String username = "root";
        String password = "root";
        DataSource dataSource = new PooledDataSource(driver,url,username,password);
        JdbcTransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        configuration.addMapper(EmployeeMapperInter.class);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        SqlSession sqlSession = sqlSessionFactory.openSession();
//        EmployeeMapperInter mapper = sqlSession.getMapper(EmployeeMapperInter.class);
        Employee employee = new Employee(null,"许嵩","xusong@vae.com","1");
//        mapper.addEmp(employee);
//        int count = sqlSession.insert("com.atguigu.mybatis.dao.EmployeeMapperInter.addEmp", employee);
//        sqlSession.selectOne("com.atguigu.mybatis.dao.EmployeeMapperInter.getEmployeeById", 1);
        sqlSession.commit();
        sqlSession.close();

    }
    public  void test00() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmployeeMapperInter mapper = sqlSession.getMapper(EmployeeMapperInter.class);
        Employee employee = new Employee();
        employee.setLastName("许嵩");
        employee.setGender("1");
        employee.setEmail("xusong@vae.com");
        mapper.addEmp(employee);
        System.out.println(employee.getId());//自增主键的获取
        sqlSession.commit();
        sqlSession.close();

    }

    public  void test() throws IOException {
        //根据xml配置文件（全局配置文件）创建一个SqlSessionFactory对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //2.获取sqlSession实例，能直接执行已经映射的sql语句
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //映射的sql语句-->EmployeeMapper.xml
        //第一个参数：sql的唯一标识
        //第二个参数：
        try {
            Employee employee = sqlSession.selectOne("com.atguigu.mybatis.dao.EmployeeMapperInter.getEmployeeById", 1);
            System.out.println(employee);
        }finally {
            sqlSession.close();
        }
    }

    public  void test1() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
        EmployeeMapperInter mapper = sqlSession.getMapper(EmployeeMapperInter.class);
//        class com.sun.proxy.$Proxy0
//      会为接口创建一个代理对象，代理对象去执行crud
//            这就是接口式编程
//            好处：1 接口规定的方法拥有更好的类型检查，比如参数要求传的是Integer，就不能传字符串；
//            2 也规定返回值
//            3 接口本就是一个抽象，抽出一个规范
//             4 解耦
        System.out.println(mapper.getClass());
        Employee employee = mapper.getEmployeeById(1);
        System.out.println(employee);
        }finally {
            sqlSession.close();
        }
    }
    public  void test2() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            StuDentMapper mapper = sqlSession.getMapper(StuDentMapper.class);
        System.out.println(mapper.getClass());
            StuDent sd = mapper.getStuDentById(1);
        System.out.println(sd);
        }finally {
            sqlSession.close();
        }
    }

    public  void test3() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperAnnotation mapper = sqlSession.getMapper(EmployeeMapperAnnotation.class);
        System.out.println(mapper.getClass());
            Employee employee = mapper.getEmployeeById(1);
        System.out.println(employee);
        }finally {
            sqlSession.close();
        }
    }
    public  void test4() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
//        SqlSession sqlSession = sqlSessionFactory.openSession();//手动提交
        SqlSession sqlSession = sqlSessionFactory.openSession(true);//自动提交
        try {
            EmployeeMapperInter mapper = sqlSession.getMapper(EmployeeMapperInter.class);
            Employee employee = new Employee(3,"xusong", "xusong@vae.com", "1");
            Long result = mapper.updateEmp(employee);
//            sqlSession.commit();
            System.out.println(result);
        }finally {
            sqlSession.close();
        }
    }
    public  void test5() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        try {
            EmployeeMapperInter mapper = sqlSession.getMapper(EmployeeMapperInter.class);
            boolean result = mapper.deleteEmpById(5);
            System.out.println(result);
        }finally {
            sqlSession.close();
        }
    }

    public  void test6() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperInter mapper = sqlSession.getMapper(EmployeeMapperInter.class);
            Employee employee = mapper.getEmp(1, "tom");
            System.out.println(employee);
        }finally {
            sqlSession.close();
        }
    }
    public  void test7() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperInter mapper = sqlSession.getMapper(EmployeeMapperInter.class);
            Map<String, Object> map = new HashMap<>();
            map.put("id", 1);
            map.put("lastName","tom");

            Employee employee = mapper.getEmpByMap(map);
            System.out.println(employee);
        }finally {
            sqlSession.close();
        }
    }




}
