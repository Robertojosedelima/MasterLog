package br.com.MasterLog.Entity;

import java.io.Serializable;
import java.sql.Timestamp;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Entity
@Table(name = "LOG_MASTER_LOG", uniqueConstraints = @UniqueConstraint(columnNames = "id"))
@Data
public class LogMasterLogEntity implements Serializable {

    private static final long serialVersionUID = 1L;


	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @Column(name = "EVENT", length = 100)
    private String event;

    @Column(name = "DATE_TIME", columnDefinition = "TIMESTAMP(6)") 
    private Timestamp dateTime;

    @Column(name = "ROUTE", length = 4000)
    private String route;

    @Column(name = "USERNAME", length = 30)
    private String username;

    @Column(name = "OSUSER", length = 30)
    private String osUser;

    @Column(name = "MACHINE", length = 64)
    private String machine;

    @Column(name = "INFO", length = 64)
    private String info;

    @Column(name = "TABLE_OWNER", length = 64)
    private String tableOwner;
}
