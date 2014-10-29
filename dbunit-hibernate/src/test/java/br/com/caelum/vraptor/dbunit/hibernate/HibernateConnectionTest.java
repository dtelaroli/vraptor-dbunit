package br.com.caelum.vraptor.dbunit.hibernate;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;

import models.MyModel;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;

public class HibernateConnectionTest {

	private HibernateConnection hconn;
	private Session session;
	
	@Before
	public void setUp() throws Exception {
		hconn = new HibernateConnection();
		session = hconn.getSession();
	}

	@Test
	public void shouldReturnActiveConn() throws SQLException {
		assertThat(session, instanceOf(Session.class));
		assertThat(session.isOpen(), equalTo(true));
	}
	
	@Test
	public void shouldInsertModel() {
		MyModel model = new MyModel();
		model.setName("Name");
		
		Transaction tx = session.beginTransaction();
		MyModel merge = (MyModel) session.merge(model);
		tx.commit();
		assertThat(merge.getId(), equalTo(1L));
	}

}
