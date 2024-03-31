package br.com.MasterLog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.MasterLog.database.TriggerLog;
import br.com.MasterLog.entity.ReferenceTableEntity;
import br.com.MasterLog.record.TableRecord;
import jakarta.transaction.Transactional;

@Service
public class RequestLogService {
	
	@Autowired
	ReferenceTableService referenceTableService;
	@Autowired
	TriggerLog triggerLog;

	@Transactional
	public ResponseEntity<String> requestAddService(List<TableRecord> tableRecord) {

		for (TableRecord rownum : tableRecord) {
			triggerLog.createTriggerOracleBD(rownum.Owner(), rownum.tableName());

			ReferenceTableEntity referenceTableEntity = new ReferenceTableEntity();
			referenceTableEntity.setOwnerTable(rownum.Owner());
			referenceTableEntity.setNameTable(rownum.tableName());
			if (referenceTableService.findById(referenceTableEntity.getOwnerTable(),
					referenceTableEntity.getNameTable()) != null) {

				continue;
			}
			referenceTableService.addReferenceTable(referenceTableEntity); 
		}

		return ResponseEntity.status(HttpStatus.CREATED).body("Successfully created triggers!");

	}

	

}
