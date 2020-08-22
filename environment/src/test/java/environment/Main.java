package environment;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.troyforever.env.bean.Base;
import com.troyforever.env.bean.User;
import com.troyforever.env.bean.UserShed;
import com.troyforever.env.dao.BaseDao;
import com.troyforever.env.dao.UserDao;
import com.troyforever.env.dao.UserShedDao;

public class Main {

	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		UserDao userDao = (UserDao) context.getBean("userDao") ;
		
		UserShedDao usDao = (UserShedDao) context.getBean("userShedDao") ;
		
		User user = userDao.findById(1) ;
		
		List<UserShed> list = usDao.findByUser(1) ;
		
		for ( int i = 0 ; i < list.size() ; i ++ )
			System.out.println(list.get(i).getShed().getId());

	}

}
