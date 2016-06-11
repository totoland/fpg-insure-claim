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
import com.totoland.db.enums.InsureState;
import java.util.List;

/**
 *
 * @author totoland
 */
public interface CertificateService extends GennericService<ClaimInsure>{

    String getCertificateNO(String insureType);

    List<ViewCertificate> searchCertificate(CertifaicationCriteria criteria);

    ClaimInsure findByTrxId(String trxId);
    
    void save(ClaimInsure claimInsure,ImageCertExport imageCertExport);
    
    void updateStateCertNo(ClaimInsure claimInsure);
}
