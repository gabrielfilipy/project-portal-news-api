package com.monet.portal.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.monet.portal.domain.fotos.NewsFoto;
import com.monet.portal.domain.model.News;

@Repository
public interface NewsRepository extends JpaRepository<News, Long>, NewsRepositoryQueries, 
	JpaSpecificationExecutor<News>{

	@Query("SELECT n FROM News n ORDER BY n.order_id asc")
	public List<News> findByNewsOrderCollection();
	
	@Query("SELECT n FROM News n WHERE n.idNews = :idNews AND n.order_id = :orderId")
	public News findByNewsPositionOrder(Long idNews, Long orderId);
	
	@Query("select n from NewsFoto n join n.news ne where ne.idNews = :idNews")
	public Optional<NewsFoto> findFotoById(Long idNews);
	
}
