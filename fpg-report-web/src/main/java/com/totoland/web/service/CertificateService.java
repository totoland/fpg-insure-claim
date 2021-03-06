/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.totoland.web.service;

import com.totoland.db.bean.CertifaicationCriteria;
import com.totoland.db.bean.ViewCertificate;
import com.totoland.db.entity.ClaimInsure;
import com.totoland.db.entity.ImageCertExport;
import java.util.List;

/**
 *
 * @author totoland
 */
public interface CertificateService extends GennericService<ClaimInsure>{

    String getCertificateNO(ClaimInsure claimInsure);

    List<ViewCertificate> searchCertificate(CertifaicationCriteria criteria);

    ClaimInsure findByTrxId(String trxId);
    
    void save(ClaimInsure claimInsure,ImageCertExport imageCertExport);
    
    void updateStateCertNo(ClaimInsure claimInsure);
    
    void removeAndBackup(ClaimInsure claimInsure)throws Exception;
    
    int countInvoiceNumberByOpenPolicy(ClaimInsure claimInsure);
}
