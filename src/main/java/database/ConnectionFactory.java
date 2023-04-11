package database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionFactory {

	private static final String PERSISTENCE_NAME = "AprendendoHibernateComJDevTreinamento";
	private static EntityManagerFactory entityManagerFactory = null;
	private static EntityManager entityManager = null;
	
	public static EntityManager getEntityManager() {
		if(entityManagerFactory == null && entityManager == null) {
			entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_NAME);
			entityManager = entityManagerFactory.createEntityManager();
		}
		return entityManager;
	}
	
}
