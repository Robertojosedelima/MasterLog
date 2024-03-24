package br.com.MasterLog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.MasterLog.Entity.LogMasterLogEntity;
import br.com.MasterLog.service.LogMasterLogService;

@RestController
@RequestMapping(value = "/logmasterlog")
public class LogMasterLogController {
	
	@Autowired
	LogMasterLogService logMasterLogService;
	
	@GetMapping
	public ResponseEntity<List<LogMasterLogEntity>> findAll(){
		return logMasterLogService.findAll();
	}
	
	@DeleteMapping
	public ResponseEntity<String> dellId(@RequestBody List<LogMasterLogEntity> logMasterLogEntities) {

		return logMasterLogService.delLogMasterLog(logMasterLogEntities);

	}
	
}
