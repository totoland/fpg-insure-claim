/*
 * Copyright (C) 2016 thanapong.n
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package com.totoland.db.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author thanapong.n
 */
@Entity
@Table(name = "image_cert_export")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ImageCertExport.findAll", query = "SELECT i FROM ImageCertExport i")})
public class ImageCertExport implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "claim_insure_id")
    private Long claimInsureId;
    @Lob
    @Column(name = "image_content")
    private byte[] imageContent;
    @Column(name = "image_name")
    private String imageName;
    @Column(name = "image_type")
    private String imageType;

    public ImageCertExport() {
    }

    public byte[] getImageContent() {
        return imageContent;
    }

    public void setImageContent(byte[] imageContent) {
        this.imageContent = imageContent;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public Long getClaimInsureId() {
        return claimInsureId;
    }

    public void setClaimInsureId(Long claimInsureId) {
        this.claimInsureId = claimInsureId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.claimInsureId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ImageCertExport other = (ImageCertExport) obj;
        if (!Objects.equals(this.claimInsureId, other.claimInsureId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ImageCertExport{" + "claimInsureId=" + claimInsureId + ", imageName=" + imageName + ", imageType=" + imageType + '}';
    }
}
