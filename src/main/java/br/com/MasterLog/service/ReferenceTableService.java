package br.com.MasterLog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.MasterLog.entity.ReferenceTableEntity;
import br.com.MasterLog.record.TableRecord;
import br.com.MasterLog.repository.ReferenceTableRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Service
public class ReferenceTableService {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	ReferenceTableRepository referenceTableRepository;

	@Autowired
	ReferenceTableEntity referenceTableEntity;

	public ResponseEntity<List<TableRecord>> findAll() {
		// stores a list of ReferenceTableEntity
		List<ReferenceTableEntity> newReferenceTableEntity = referenceTableRepository.findAll();
		// Convert a List to type TableRecord
		List<TableRecord> tableRecord = referenceTableEntity.toReferenceTableEntity(newReferenceTableEntity);

		return ResponseEntity.ok(tableRecord);

	}

	public ReferenceTableEntity findById(String OwnerTable, String NameTable) {
		return referenceTableRepository.findByOwnerAndTableName(OwnerTable, NameTable);
		

	}

	public void addReferenceTable(ReferenceTableEntity referenceTableEntity) {
		referenceTableRepository.save(referenceTableEntity);

	}

	@Transactional
	public ResponseEntity<String> delReferenceTable(List<TableRecord> tableRecord) {

		for (TableRecord rownum : tableRecord) {

			ReferenceTableEntity newReferenceTableEntity = new ReferenceTableEntity();
			newReferenceTableEntity.setId(rownum.id());
			newReferenceTableEntity.setOwnerTable(rownum.Owner());
			newReferenceTableEntity.setNameTable(rownum.tableName());
			deleteTriggerOracleBD(rownum.Owner(), rownum.tableName());
			referenceTableRepository.deleteById(newReferenceTableEntity.getId());
		}

		return ResponseEntity.ok().body("Successfully deleted triggers in tables select!");

	}

	private void deleteTriggerOracleBD(String Owner, String tableName) {
		String delTriggerSql = "DROP TRIGGER " + Owner + ".trg_audit_" + tableName.toLowerCase() + " ";

		Query query = entityManager.createNativeQuery(delTriggerSql);
		query.executeUpdate();

	}
}
