//package com.bootcamp.induk.config;
//
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//
//import javax.sql.DataSource;
//
//@Configuration      //설정파일임을 나타내는 어노테이션 , @Bean 등록을 위한 어노테이션(스프링 컨테이너에서 관리 되며 Bean들이 싱글톤을 유지하게 한다)
//@MapperScan(        //Mybatis Mapper를 설정하기 위한 어노테이션
//        basePackages = {"com.bootcamp.induk.mapper"},   //Mapper를 찾을 Package를 명시하는 속성
//        annotationClass = org.apache.ibatis.annotations.Mapper.class,               //@Mapper 어노테이션을 적용하기 위한 속성 basePackage안에 @Mapper 어노테이션 붙은 클래스만 mybatis Mapper로 인식
//        sqlSessionFactoryRef = "sqlSessionFactory")
//public class AppConfig {
//
//    @Bean(name = "sqlSessionFactory")   //sqlSessionFactory라는 이름으로 Bean등록
//    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception {
//
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();      //Bean으로 등록할 SqlSessionFactory객체 생성
//        sqlSessionFactoryBean.setDataSource(dataSource);        //sqlSessionFactoryBean에 dataSource정보를 set함 (Datasource정보는 application.yml에 명시함)
//        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:/mybatis/*/mapper/*Mapper.xml"));    //Mapper xml파일의 위치를 명시
//        return sqlSessionFactoryBean.getObject();
//
//    }
//}
