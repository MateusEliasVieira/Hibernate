import org.junit.jupiter.api.Test;

import database.ConnectionFactory;
import entities.Post;
import model.PostDaoRepository;

public class Testes {

	@Test
	public void teste1() {
		ConnectionFactory.getEntityManager();
	}
	
	@Test
	public void salvando() {
		Post post = new Post();
		post.setTitle("Hibernate");
		post.setContent("Hibernate é um framework de mapeamento objeto relacional (ORM)");
		
		PostDaoRepository postDaoRepository = new PostDaoRepository();
		postDaoRepository.save(post);
		
	}
	
	@Test
	public void buscarPostPorId() {
		Post post = new PostDaoRepository().findPostById(2);
		System.out.println(post.toString());
	}
	
	@Test
	public void atualizarPost() {
		Post post = new Post();
		post.setIdpost(3);
		post.setTitle("Hibernate");
		post.setContent("Hibernate é um framework de mapeamento objeto relacional (ORM) muito bom");
		
		PostDaoRepository postDaoRepository = new PostDaoRepository();
		postDaoRepository.update(post);
	}
	
	
	@Test
	public void deletarPost() { 
		new PostDaoRepository().delete(6);
	}
	
	@Test
	public void deletarPostComQueryNativa() { 
		new PostDaoRepository().delete(1);
	}
	
	
}
