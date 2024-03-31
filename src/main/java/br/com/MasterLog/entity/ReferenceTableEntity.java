package br.com.MasterLog.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.MasterLog.record.TableRecord;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Entity
@Component
@Table(name = "REF_TABLE_RECORD", uniqueConstraints = { @UniqueConstraint(columnNames = "id"),
		@UniqueConstraint(columnNames = "NameTable") })
@Data
public class ReferenceTableEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String OwnerTable;
	private String NameTable;

	public List<TableRecord> toReferenceTableEntity(List<ReferenceTableEntity> referenceTableEntity) {
		List<TableRecord> tableRecords = new ArrayList<>();
		for (ReferenceTableEntity rownum : referenceTableEntity) {
			TableRecord tableRecord = new TableRecord(rownum.id, rownum.OwnerTable, rownum.NameTable);

			tableRecords.add(tableRecord);

		}

		return tableRecords;
	}

}