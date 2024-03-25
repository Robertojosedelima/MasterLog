package br.com.MasterLog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.MasterLog.Entity.ReferenceTableEntity;
import br.com.MasterLog.configuration.Parameters;
import br.com.MasterLog.record.TableRecord;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Service
public class RequestLogService {
	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	ReferenceTableService referenceTableService;

	@Transactional
	public ResponseEntity<String> requestAddService(List<TableRecord> tableRecord) {

		for (TableRecord rownum : tableRecord) {
			createTriggerOracleBD(rownum.Owner(), rownum.tableName());

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

	private void createTriggerOracleBD(String Owner, String tableName) {

		String triggerSql = "CREATE OR REPLACE TRIGGER " + Owner + ".trg_audit_" + tableName.toLowerCase() + " "
				+ "BEFORE INSERT OR UPDATE OR DELETE ON " + Owner + "." + tableName + " " + "FOR EACH ROW " + "BEGIN "
				+ "    IF inserting THEN " + "      INSERT INTO " + Parameters.getOwnerLog() + "." + Parameters.getTableLog()
				+ "(EVENT, DATE_TIME, ROUTE, USERNAME, OSUSER, MACHINE, INFO, TABLE_OWNER) "
				+ "      VALUES ('INSERT', SYSTIMESTAMP, DBMS_UTILITY.FORMAT_CALL_STACK, sys_context('USERENV','OS_USER'), sys_context('USERENV','HOST'), sys_context('USERENV','MODULE'), sys_context('USERENV','CLIENT_INFO'), "
				+ "'" + Owner + "." + tableName + "'" + "); " + "    ELSIF updating THEN " + "      INSERT INTO "
				+ Parameters.getOwnerLog() + "." + Parameters.getTableLog()
				+ "(EVENT, DATE_TIME, ROUTE, USERNAME, OSUSER, MACHINE, INFO, TABLE_OWNER) "
				+ "      VALUES ('UPDATE', SYSTIMESTAMP, DBMS_UTILITY.FORMAT_CALL_STACK, sys_context('USERENV','OS_USER'), sys_context('USERENV','HOST'), sys_context('USERENV','MODULE'), sys_context('USERENV','CLIENT_INFO'), "
				+ "'" + Owner + "." + tableName + "'" + "); " + "    ELSIF deleting THEN " + "      INSERT INTO "
				+ Parameters.getOwnerLog() + "." + Parameters.getTableLog()
				+ "(EVENT, DATE_TIME, ROUTE, USERNAME, OSUSER, MACHINE, INFO, TABLE_OWNER) "
				+ "      VALUES ('DELETE', SYSTIMESTAMP, DBMS_UTILITY.FORMAT_CALL_STACK, sys_context('USERENV','OS_USER'), sys_context('USERENV','HOST'), sys_context('USERENV','MODULE'), sys_context('USERENV','CLIENT_INFO'), "
				+ "'" + Owner + "." + tableName + "'" + "); " + "    END IF; " + "END;";

		Query query = entityManager.createNativeQuery(triggerSql);
		query.executeUpdate();

	}

}
