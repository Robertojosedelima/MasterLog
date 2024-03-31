package br.com.MasterLog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.MasterLog.entity.LogMasterLogEntity;

@Repository
public interface LogMasterLogRepository extends JpaRepository<LogMasterLogEntity, Long>{

}
