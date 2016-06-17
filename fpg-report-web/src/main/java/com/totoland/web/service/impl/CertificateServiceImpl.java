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
package com.totoland.web.service.impl;

import com.totoland.db.bean.CertifaicationCriteria;
import com.totoland.db.bean.ViewCertificate;
import com.totoland.db.certificate.dao.CertificateDao;
import com.totoland.db.certificate.dao.ImageCertDao;
import com.totoland.db.entity.ClaimInsure;
import com.totoland.db.entity.ImageCertExport;
import com.totoland.web.service.CertificateService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author totoland
 */
@Service(value = "certificateService")
public class CertificateServiceImpl extends GennericServiceImpl<ClaimInsure> implements CertificateService {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyMM",Locale.ENGLISH);

    @Autowired
    CertificateDao certificateDao;

    @Autowired
    ImageCertDao imageCertDao;

    @Override
    public String getCertificateNO(ClaimInsure claimInsure) {
        //F-1606-000001
        //select  CONCAT('F',DATE_FORMAT(SYSDATE(), '%Y%m'),'-', LPAD('1',7,'0'))
        //String certNumber = "F";
//        certNumber = certNumber + "-" + dateFormat.format(new Date()) + "-" + RandomStringUtils.randomNumeric(6);

        return certificateDao.getCertificateNO(claimInsure);
    }

    @Override
    public List<ViewCertificate> searchCertificate(CertifaicationCriteria criteria) {
        return certificateDao.findByCriteria(criteria);
    }

    @Override
    public ClaimInsure findByTrxId(String trxId) {
        return certificateDao.findByTrxId(trxId);
    }

    @Transactional(rollbackFor = {Throwable.class})
    @Override
    public void save(ClaimInsure claimInsure, ImageCertExport imageCertExport) {
        certificateDao.edit(claimInsure);
        imageCertExport.setClaimInsureId(claimInsure.getClaimId());
        imageCertDao.edit(imageCertExport);
    }

    @Override
    public void updateStateCertNo(ClaimInsure claimInsure) {
        certificateDao.updateStateCertNo(claimInsure);
    }
}
