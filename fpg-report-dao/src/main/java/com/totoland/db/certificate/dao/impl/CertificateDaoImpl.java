/*
 * The MIT License
 *
 * Copyright 2016 totoland.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.totoland.db.certificate.dao.impl;

import com.totoland.db.bean.CertifaicationCriteria;
import com.totoland.db.bean.ViewCertificate;
import com.totoland.db.certificate.dao.CertificateDao;
import com.totoland.db.common.dao.hibernate.GennericDaoImpl;
import com.totoland.db.entity.ClaimInsure;
import com.totoland.db.enums.InsureState;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author totoland
 */
@Repository
public class CertificateDaoImpl extends GennericDaoImpl<ClaimInsure> implements CertificateDao {

    private static final long serialVersionUID = -2516485566461068565L;
    
    @Transactional(readOnly = true)
    @Override
    public List<ViewCertificate> findByCriteria(CertifaicationCriteria criteria) {
        String SQL = "SELECT "
                + "claim_insure.claim_id, "
                + "claim_insure.policy_number, "
                + "claim_insure.certification_number, "
                + "claim_insure.insured_id, "
                + "claim_insure.claim_status_id, "
                + "claim_status.claim_status_name, "
                + "insures.insured_name, "
                + "claim_insure.method_of_transport_id, "
                + "claim_insure.issue_date, "
                + "claim_insure.trx_id "
                + "FROM "
                + "claim_insure "
                + "INNER JOIN claim_status ON claim_insure.claim_status_id = claim_status.claim_status_id "
                + "LEFT JOIN insures ON claim_insure.insured_id = insures.insured_id "
                + "WHERE 1=1  ";
        
        List<Object> params = new ArrayList<>();
        if (criteria.getIssueDateFrom() != null && criteria.getIssueDateTo() != null) {
            SQL += "and (claim_insure.issue_date >= ? and claim_insure.issue_date <= ?) ";
            params.add(criteria.getIssueDateFrom());
            params.add(criteria.getIssueDateTo());
        }
        
        if (criteria.getCertificateNumber() != null && !criteria.getCertificateNumber().isEmpty()) {
            SQL += "and claim_insure.certification_number = ? ";
            params.add(criteria.getCertificateNumber());
        }
        
        if (criteria.getPolicyNumber() != null && !criteria.getPolicyNumber().isEmpty()) {
            SQL += "and claim_insure.policy_number = ? ";
            params.add(criteria.getPolicyNumber());
        }
        
        if (criteria.getInsuredName() != null && !criteria.getInsuredName().isEmpty()) {
            SQL += "and claim_insure.insured_id = ? ";
            params.add(criteria.getInsuredName());
        }
        
        if (criteria.getStatus() != null && !criteria.getStatus().isEmpty()) {
            SQL += "and claim_insure.claim_status_id = ? ";
            params.add(criteria.getStatus());
        }
        
        SQL += " ORDER BY claim_insure.issue_date DESC";
        
        return findNativeQuery(SQL, ViewCertificate.class, params);
        
    }
    
    @Transactional(readOnly = true)
    @Override
    public ClaimInsure findByTrxId(String trxId) {
        return (ClaimInsure) findUniqNativeQuery("SELECT * FROM claim_insure where trx_id = ?", ClaimInsure.class, trxId);
    }
    
    @Transactional
    @Override
    public void updateStateCertNo(ClaimInsure claimInsure){
        updateNativeQuery("UPDATE claim_insure set certification_number=?,claim_status_id=? where claim_id=?", claimInsure.getCertificationNumber(),
                claimInsure.getClaimStatusId(),claimInsure.getClaimId());
    }
}
