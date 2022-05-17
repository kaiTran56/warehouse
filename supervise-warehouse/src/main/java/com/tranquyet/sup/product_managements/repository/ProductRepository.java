package com.tranquyet.sup.product_managements.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tranquyet.sup.product_managements.entities.ProductEntity;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
	@Query(value = "select * from pods \r\n" + "inner join products \r\n" + "on pods.id = products.pods_id\r\n"
			+ "where pods.id = :idPod \r\n" + "and products.delete_status = 1;", nativeQuery = true)
	List<ProductEntity> getProductByPodId(@Param("idPod") Long idPod);

	@Query(value = "select * from products where delete_status = 1;", nativeQuery = true)
	List<ProductEntity> getAll();

	@Query(value = "select working from products where id = :idProduct and delete_status = 1;", nativeQuery = true)
	Long isWorking(@Param("idProduct") Long idProduct);

	@Modifying
	@Query(value = "update products set working = :working where id = :idProduct", nativeQuery = true)
	void updateWorking(@Param("idProduct") Long idProduct, @Param("working") Long working);

	@Modifying
	@Query(value = "update products set delete_status = 0 where id = :idProduct", nativeQuery = true)
	void updateDeleteStatus(@Param("idProduct") Long id);
}
