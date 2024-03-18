package br.com.MasterLog.Entity;


import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Entity
@Table(name = "REF_TABLE_RECORD", 
uniqueConstraints = {
    @UniqueConstraint(columnNames = "id"),
    @UniqueConstraint(columnNames = "NameTable")
})
@Data
public class ReferenceTableRecordEntity implements Serializable {

    private static final long serialVersionUID = 1L;


	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
	private String OwnerTable;
	private String NameTable;
	
}