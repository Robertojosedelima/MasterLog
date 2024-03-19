package br.com.MasterLog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.MasterLog.Entity.ReferenceTableEntity;

@Repository
public interface ReferenceTableRepository extends JpaRepository<ReferenceTableEntity, Long>{

}
