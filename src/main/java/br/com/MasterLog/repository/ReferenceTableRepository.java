package br.com.MasterLog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.MasterLog.entity.ReferenceTableEntity;

@Repository
public interface ReferenceTableRepository extends JpaRepository<ReferenceTableEntity, Long>{
	
	@Query("SELECT rt FROM ReferenceTableEntity rt WHERE rt.OwnerTable = :OwnerTable AND rt.NameTable = :NameTable")
    ReferenceTableEntity findByOwnerAndTableName(@Param("OwnerTable") String OwnerTable, @Param("NameTable") String NameTable);
    

}
