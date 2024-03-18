package br.com.MasterLog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.MasterLog.record.TableRecord;
import br.com.MasterLog.service.RequestLogService;

@RestController
@RequestMapping(value = "/requestLog")
public class RequestLogController {
    
    @Autowired
	RequestLogService requestLogService;
    
    @PostMapping
	public ResponseEntity<String> requestAdd(@RequestBody List<TableRecord> tableRecord){

		return requestLogService.requestAddService(tableRecord);
 

	}

}
