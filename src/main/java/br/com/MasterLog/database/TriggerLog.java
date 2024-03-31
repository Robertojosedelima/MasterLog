package br.com.MasterLog.database;

import org.springframework.stereotype.Service;

import br.com.MasterLog.configuration.Parameters;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Service
public class TriggerLog {
	@PersistenceContext
	private EntityManager entityManager;

	public void createTriggerOracleBD(String Owner, String tableName) {

		String triggerSql = "CREATE OR REPLACE TRIGGER " + Owner + ".trg_audit_" + tableName.toLowerCase() + " "
				+ "BEFORE INSERT OR UPDATE OR DELETE ON " + Owner + "." + tableName + " " + "FOR EACH ROW " + "BEGIN "
				+ "    IF inserting THEN " + "      INSERT INTO " + Parameters.getOwnerLog() + "."
				+ Parameters.getTableLog() + "(EVENT, DATE_TIME, ROUTE, USERNAME, OSUSER, MACHINE, INFO, TABLE_OWNER) "
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

	public void deleteTriggerOracleBD(String Owner, String tableName) {
		String delTriggerSql = "DROP TRIGGER " + Owner + ".trg_audit_" + tableName.toLowerCase() + " ";

		Query query = entityManager.createNativeQuery(delTriggerSql);
		query.executeUpdate();

	}

}
