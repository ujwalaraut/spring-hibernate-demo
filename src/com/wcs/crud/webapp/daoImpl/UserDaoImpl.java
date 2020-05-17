package com.wcs.crud.webapp.daoImpl;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wcs.crud.webapp.dao.UserDao;
import com.wcs.crud.webapp.model.User;

@Repository
public class UserDaoImpl implements UserDao

{

	List<User> list;

	@Autowired
	private SessionFactory sf;

	@Override
	public int saveUser(User user) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		int id = (int) session.save(user);
		tx.commit();
		session.close();
		return id;
	}

	@Override
	public int loginCheck(User user) 
	{
		Session session=sf.openSession();
		Transaction tx=session.beginTransaction();
		int id=0;
		
		Query query=session.createQuery("from User where uname='"+user.getUname()+"' and pass='"+user.getPass()+"'");
		System.out.println(query);
/*		query.setParameter(0, user.getName());
		query.setParameter(1, user.getPass());*/
		
		List li=query.list();
		Iterator itr=li.iterator();
		while(itr.hasNext())
		{
			id=1;
			User s1=(User)itr.next();
			System.out.println(s1.getName());
			System.out.println(s1.getPass());
		}
		tx.commit();
		session.close();
		
		return id;
	}
	@Override
	public List<User> dispalyAll(User user) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		Criteria cr = session.createCriteria(User.class);
		List<User> list = cr.list();
		tx.commit();
		session.close();
		return list;
	}

	@Override
	public int deleteUser(User user) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		Query query=session.createQuery("delete From User user where user.uid="+user.getUid()+"");
		int id = query.executeUpdate();
		tx.commit();
		session.close();
		return id;
	}

	@Override
	public User editUser(User user) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		Criteria cr = session.createCriteria(User.class);
		cr.add(Restrictions.eq("uid", user.getUid()));
		List<User> list = cr.list();
		User user1 = null;
		Iterator<User> itr = list.iterator();
		while (itr.hasNext()) 
		{
			user1 = itr.next();
		}
		tx.commit();
		session.close();
		return user1;
	}

	@Override
	public int updateUser(User user) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		User user1 = new User();
		Query query=session.createQuery("Update User user set user.name='"+user.getName()+"',user.uname='"+user.getUname()+"',user.pass='"+user.getPass()+"',user.mobileno='"+user.getMobileno()+"' where user.uid="+user.getUid()+"");
		System.out.println(query);
		int id = query.executeUpdate();
		// session.update(user);
		tx.commit();
		session.close();
		return id;
	}

}
