package com.how2java.pojo;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class TestMybatis {

	public static void main(String[] args) throws IOException {
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();

		// add(session);
		/*
		 * listAll(session); delete(session); listAll(session);
		 * 
		 * getCategory(session, 2);
		 */
		// update(session);
		list(session);

		session.commit();
		session.close();
		// productlist(session);

	}

	public static void add(SqlSession session) {
		Category category = new Category();
		category.setName("新增加的Category");
		session.insert("addCategory", category);

	}

	public static void listAll(SqlSession session) {
		List<Category> cs = session.selectList("listCategory");
		for (Category c : cs) {
			System.out.println(c.getName());
		}
	}

	public static void delete(SqlSession session) {
		Category c = new Category();
		c.setId(1);
		session.delete("delCategory", c);

	}

	public static Category getCategory(SqlSession session, int id) {
		Category category = session.selectOne("getCategory", id);
		System.out.println(category.getName());
		return category;
	}

	public static void update(SqlSession session) {
		Category category = getCategory(session, 2);
		category.setName("cate");
		session.update("updateCategory", category);
	}

	public static void list(SqlSession session) {
		List<Category> cs = session.selectList("listCategory2");
		for (Category c : cs) {
			System.out.println(c);
			List<Product> ps = c.getProducts();
			for (Product p : ps) {
				System.out.println("\t" + p);
			}
		}

	}

	public static void productlist(SqlSession session) {
		List<Product> products = session.selectList("listProduct");
		System.out.println("ss");
		for (Product p : products) {
			System.out.println(p + " 对应的分类是 \t " + p.getCategory());
		}

		session.commit();
		session.close();
}

}
