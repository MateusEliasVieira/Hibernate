package model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;


import database.ConnectionFactory;
import entities.Post;

public class PostDaoRepository {

	private EntityManager entityManager;
	
	public PostDaoRepository() {
		entityManager = ConnectionFactory.getEntityManager();
	}
	
	// salva post
	public void save(Post post) {
		entityManager.getTransaction().begin(); // abrimos a transação
		try {
			entityManager.persist(post); // enviamos o objeto para ser salvo
			entityManager.getTransaction().commit(); // fechamos a transação e salvamos as alterações no banco			
		} catch (Exception e) {
			entityManager.getTransaction().rollback(); // desfazemos a inserção caso exista algum erro
			System.out.println(e.getMessage()); // exibindo erro
		}
	}
	
	// buscar post por id
	public Post findPostById(int id) {
		return entityManager.find(Post.class, id); // buscamos o registro passando a classe e o id
	}
	
	// busca todos os registros
	public List<Post> findAll(){
		// Criamos uma consulta HQL(Hibernate Query Linguage) e retornamos a lista dos registros
		List<Post> list = entityManager.createQuery("select p from Post p").getResultList();
		return list;
	}
	
	// atualiza post
	public void update(Post post) {
		entityManager.getTransaction().begin(); // abrimos a transação
		try {
			entityManager.merge(post); // atualizamos o registro
			entityManager.getTransaction().commit(); // salvamos as alterações feitas no banco
		} catch (Exception e) {
			entityManager.getTransaction().rollback(); // desfazemos a atualização caso exista algum erro
			System.out.println(e.getMessage()); // exibindo erro
		}
	}
	
	// deleta post
	public void delete(int id) {
		entityManager.getTransaction().begin(); // abrimos a transação
		try {
			entityManager.remove(this.findPostById(id)); // buscamos o registro pelo seu id e deletamos o registro no banco
			entityManager.getTransaction().commit(); // salvamos as alterações feitas no banco
		} catch (Exception e) {
			entityManager.getTransaction().rollback(); // desfazemos a remoção do registro caso exista algum erro
			System.out.println(e.getMessage()); // exibindo erro
		}
	}
	
	// deleta post com query nativa
	public void deleteNativeQuery(int id) {
		entityManager.getTransaction().begin();
		try {
			Query query = entityManager.createNativeQuery("delete from post where idpost = :idpost"); // criamos a query sql nativa
			query.setParameter(1, id); // passamos parametros para a query
			query.executeUpdate(); // executamos ela no banco
			entityManager.getTransaction().commit(); // salvamos as alterações feitas no banco
		} catch (Exception e) {
			entityManager.getTransaction().rollback(); // desfazemos a remoção do registro caso exista algum erro
			System.out.println(e.getMessage()); // exibindo erro
		}
	}
	
	
}
