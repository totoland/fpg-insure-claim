/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.totoland.db.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author totoland
 */
@Entity
@Table(name = "group_lvl")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GroupLvl.findAll", query = "SELECT g FROM GroupLvl g")})
public class GroupLvl implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "GROUP_LVL_ID")
    private Integer groupLvlId;
    @Column(name = "GROUP_LVL_NAME")
    private String groupLvlName;
    @Lob
    @Column(name = "GROUP_LVL_DESC")
    private String groupLvlDesc;

    public GroupLvl() {
    }

    public GroupLvl(Integer groupLvlId) {
        this.groupLvlId = groupLvlId;
    }

    public Integer getGroupLvlId() {
        return groupLvlId;
    }

    public void setGroupLvlId(Integer groupLvlId) {
        this.groupLvlId = groupLvlId;
    }

    public String getGroupLvlName() {
        return groupLvlName;
    }

    public void setGroupLvlName(String groupLvlName) {
        this.groupLvlName = groupLvlName;
    }

    public String getGroupLvlDesc() {
        return groupLvlDesc;
    }

    public void setGroupLvlDesc(String groupLvlDesc) {
        this.groupLvlDesc = groupLvlDesc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (groupLvlId != null ? groupLvlId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GroupLvl)) {
            return false;
        }
        GroupLvl other = (GroupLvl) object;
        if ((this.groupLvlId == null && other.groupLvlId != null) || (this.groupLvlId != null && !this.groupLvlId.equals(other.groupLvlId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GroupLvl{" + "groupLvlId=" + groupLvlId + ", groupLvlName=" + groupLvlName + ", groupLvlDesc=" + groupLvlDesc + '}';
    }
    
    public static enum GroupLevel {

        SYSTEM_ADMIN(0),
        CENTER(1),
        LEAD(2),
        HEAD(3), 
        OPERATOR(4);
        
        private int level = 0;
        
        GroupLevel(int level) {
            this.level = level;
        }

        public int getLevel() {
            return level;
        }
    }
}
