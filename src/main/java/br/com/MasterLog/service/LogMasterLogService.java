package br.com.MasterLog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.MasterLog.Entity.LogMasterLogEntity;
import br.com.MasterLog.repository.LogMasterLogRepository;

@Service
public class LogMasterLogService {

	@Autowired
	LogMasterLogRepository logMasterLogRepository;

	public ResponseEntity<List<LogMasterLogEntity>> findAll() {

		return ResponseEntity.ok(logMasterLogRepository.findAll());
	}

	public ResponseEntity<String> delLogMasterLog(List<LogMasterLogEntity> logMasterLogEntities) {

		try {

			for (LogMasterLogEntity rownum : logMasterLogEntities) {

				logMasterLogRepository.deleteById(rownum.getId());

			}

			return ResponseEntity.ok().body("Successfully deleted LOG in tables select!");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Failed to delete logs: " + e.getMessage());
		}
	}
}
