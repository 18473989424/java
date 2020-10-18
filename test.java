import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import shiro.dao.UserinfoMapper;
import shiro.entity.Userinfo;

public class test {

	public static void main(String[] args) {

		SqlSession session = null;

		try {
			String resource = "mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			System.out.println(sqlSessionFactory + "---------------");
			session = sqlSessionFactory.openSession();
			System.out.println(session + "-----------");

			UserinfoMapper mapper = session.getMapper(UserinfoMapper.class);
			Userinfo User = mapper.selectUserinfo("admin");

			System.out.println(User);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
