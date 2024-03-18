package br.com.MasterLog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.MasterLog.Entity.ReferenceTableRecordEntity;
import br.com.MasterLog.repository.ReferenceTableRecordRepository;

@Service
public class ReferenceTableRecordService {


	@Autowired
	ReferenceTableRecordRepository referenceTableRecordRepository;
	
	public void addReferenceTable(ReferenceTableRecordEntity referenceTableRecordEntity) {
		referenceTableRecordRepository.save(referenceTableRecordEntity);
		
	}
	public void delReferenceTable(ReferenceTableRecordEntity referenceTableRecordEntity) {
		referenceTableRecordRepository.delete(referenceTableRecordEntity);
		
	}
}
