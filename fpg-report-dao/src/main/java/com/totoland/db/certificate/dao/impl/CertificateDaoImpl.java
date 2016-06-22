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
import java.math.BigInteger;
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
                + "claim_insure.insured_name, "
                + "claim_insure.method_of_transport_id, "
                + "claim_insure.issue_date, "
                + "claim_insure.trx_id, "
                + "claim_insure.rate, "
                + "claim_insure.premium_rate, "
                + "claim_insure.minimum_premium_rate, "
                + "claim_insure.stamp, "
                + "claim_insure.vat, "
                + "claim_insure.total, "
                + "claim_status.claim_status_name, "
                + "claim_insure.invoice_value, "
                + "claim_insure.insured_value, "
                + "claim_insure.consignee_name, "
                + "claim_insure.amount_of_insurance, "
                + "claim_insure.deductible_amount, "
                + "claim_insure.currency_type, "
                + "claim_insure.exchange_rate, "
                + "claim_insure.local_amount_of_insurance, "
                + "claim_insure.origin_country_code, "
                + "claim_insure.origin_state_prov, "
                + "claim_insure.origin_description, "
                + "claim_insure.transshipment_port, "
                + "claim_insure.destination_country_code, "
                + "claim_insure.destination_state_prov, "
                + "claim_insure.destination_description, "
                + "claim_insure.conveyance_name, "
                + "claim_insure.transshipment_vessel, "
                + "claim_insure.voyage_flight_number, "
                + "claim_insure.invoice_number, "
                + "claim_insure.bill_of_lading_number, "
                + "claim_insure.shipment_date, "
                + "claim_insure.commodity_type_code, "
                + "claim_insure.commodity_description, "
                + "claim_insure.marks_and_numbers, "
                + "claim_insure.coverage_type_id, "
                + "claim_insure.valuation, "
                + "claim_insure.insuring_terms_id, "
                + "claim_insure.additional_infomation, "
                + "claim_insure.claim_surveyor_id, "
                + "claim_insure.claim_payable_at, "
                + "claim_insure.created_date_time, "
                + "claim_insure.updated_date_time, "
                + "claim_insure.created_by, "
                + "claim_insure.updated_by, "
                + "transport_method.transport_name transport_method, "
                + "commodity_type.commodity_type_name, "
                + "open_policy.broker_license, " 
                + "open_policy.broker_name "
                + "FROM "
                + "claim_insure "
                + "LEFT JOIN claim_status ON claim_insure.claim_status_id = claim_status.claim_status_id "
                + "INNER JOIN transport_method ON method_of_transport_id = transport_method.transport_id "
                + "INNER JOIN commodity_type ON claim_insure.commodity_type_code = commodity_type.commodity_type_code "
                + "INNER JOIN open_policy ON claim_insure.policy_number = open_policy.open_policy_no "
                + "WHERE 1=1  ";

        List<Object> params = new ArrayList<>();
        if (criteria.getIssueDateFrom() != null && criteria.getIssueDateTo() != null) {
            SQL += "and (claim_insure.issue_date >= ? and claim_insure.issue_date <= ?) ";
            params.add(criteria.getIssueDateFrom());
            params.add(criteria.getIssueDateTo());
        }

        if (criteria.getCertificateNumber() != null && !criteria.getCertificateNumber().isEmpty()) {
            SQL += "and claim_insure.certification_number LIKE ? ";
            params.add("%"+criteria.getCertificateNumber()+"%");
        }

        if (criteria.getPolicyNumber() != null && !criteria.getPolicyNumber().isEmpty()) {
            SQL += "and claim_insure.policy_number LIKE ?";
            params.add("%"+criteria.getPolicyNumber()+"%");
        }

        if (criteria.getInsuredName() != null && !criteria.getInsuredName().isEmpty()) {
            SQL += "and claim_insure.insured_name LIKE ? ";
            params.add("%"+criteria.getInsuredName()+"%");
        }
        
        if (criteria.getBrokerName()!= null && !criteria.getBrokerName().isEmpty()) {
            SQL += "and open_policy.broker_name LIKE ? ";
            params.add("%"+criteria.getBrokerName()+"%");
        }
        
        if (criteria.getBrokerLicenses()!= null && !criteria.getBrokerLicenses().isEmpty()) {
            SQL += "and open_policy.broker_license LIKE ? ";
            params.add("%"+criteria.getBrokerLicenses()+"%");
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

    @Transactional(rollbackFor = {Throwable.class})
    @Override
    public void updateStateCertNo(ClaimInsure claimInsure) {
        updateNativeQuery("UPDATE claim_insure set certification_number=?,claim_status_id=? where claim_id=?", claimInsure.getCertificationNumber(),
                claimInsure.getClaimStatusId(), claimInsure.getClaimId());

        updateNativeQuery("INSERT INTO cert_no values (?,?,?)", claimInsure.getCertificationNumber(),
                claimInsure.getCreatedBy(), claimInsure.getCreatedDateTime());
    }

    @Transactional(readOnly = true)
    @Override
    public String getCertificateNO(ClaimInsure claimInsure) {
        return oneColumnNativeQuery("select CONCAT('F',DATE_FORMAT(SYSDATE(), '%y%m'),'-', LPAD('" + claimInsure.getClaimId() + "',7,'0'))").toString();
    }

    @Transactional(readOnly = true)
    @Override
    public int countInvoiceNumberByOpenPolicy(ClaimInsure claimInsure) {
        BigInteger count = countNativeQuery("SELECT COUNT(1) FROM claim_insure WHERE invoice_number = ? and policy_number = ?",
                claimInsure.getInvoiceNumber(), claimInsure.getPolicyNumber());

        return count != null ? count.intValue() : 0;
    }
}
