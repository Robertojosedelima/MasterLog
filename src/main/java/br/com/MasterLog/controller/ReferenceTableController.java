package br.com.MasterLog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.MasterLog.record.TableRecord;
import br.com.MasterLog.service.ReferenceTableService;

@RestController
@RequestMapping(value = "/triggersLog")
public class ReferenceTableController {
	// precisa receber num record e depois converter para entidade
	@Autowired
	ReferenceTableService referenceTableService;

	@GetMapping
	ResponseEntity<List<TableRecord>> findAll() {
		return referenceTableService.findAll();

	}

	@DeleteMapping
	public ResponseEntity<String> dellId(@RequestBody List<TableRecord> tableRecord) {

		return referenceTableService.delReferenceTable(tableRecord);

	}

}
