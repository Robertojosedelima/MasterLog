package br.com.MasterLog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.MasterLog.Entity.ReferenceTableRecordEntity;

@Repository
public interface ReferenceTableRecordRepository extends JpaRepository<ReferenceTableRecordEntity, Long>{

}
