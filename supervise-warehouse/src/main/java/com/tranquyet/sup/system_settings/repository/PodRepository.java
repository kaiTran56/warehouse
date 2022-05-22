package com.tranquyet.sup.system_settings.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tranquyet.sup.system_settings.entities.PodEntity;

@Repository
public interface PodRepository extends JpaRepository<PodEntity, Long> {
	@Query(value = "select working from pods where id = :idPod and delete_status = 1;", nativeQuery = true)
	Long isWorking(@Param("idPod") Long idPod);

	@Query(value = "select * from pods where delete_status = 1;", nativeQuery = true)
	List<PodEntity> getAll();

	@Modifying
	@Query(value = "update pods set delete_status = 0 where id = :idPod", nativeQuery = true)
	void updateDeleteStatus(@Param("idPod") Long id);

	@Query(value = "select * from pods where width = :width and height = :height and length = :length"
			+ " and storage_quantity >= :quantity  and delete_status = 1  ORDER BY created_date LIMIT 1 ;", nativeQuery = true)
	PodEntity searchSuitablePod(@Param("width") Integer width, @Param("height") Integer height,
			@Param("length") Integer length, @Param("quantity") Integer quantity);

	@Modifying
	@Query(value = "update pods set storage_quantity = :quantity where qr_code =:qrCode and delete_status = 1", nativeQuery = true)
	void updateQuantity(@Param("quantity") Integer quantity, @Param("qrCode") String qrCode);

	@Query(value = "select storage_quantity from pods where qr_code =:qrCode and delete_status = 1", nativeQuery = true)
	Integer quantityCapability(@Param("qrCode") String qrCode);
}
