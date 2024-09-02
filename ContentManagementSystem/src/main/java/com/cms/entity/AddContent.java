package com.cms.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class AddContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pId;
    
    private String pTitle;

    private String pAuthor; // Store author email as a String
    
    private String imgName;
    
    @Lob
    @Column(name = "img_data", columnDefinition="LONGBLOB")
    private byte[] imgData;
    
    @Column(name = "content", length = 2000)
    private String content;
    
    @CreationTimestamp
    private LocalDateTime publishedDate;

    public Long getpId() {
        return pId;
    }

    public void setpId(Long pId) {
        this.pId = pId;
    }

    public String getpTitle() {
        return pTitle;
    }

    public void setpTitle(String pTitle) {
        this.pTitle = pTitle;
    }

    public String getpAuthor() {
        return pAuthor; // Correct type
    }

    public void setpAuthor(String pAuthor) { // Correct type
        this.pAuthor = pAuthor;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public byte[] getImgData() {
        return imgData;
    }

    public void setImgData(byte[] imgData) {
        this.imgData = imgData;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDateTime publishedDate) {
        this.publishedDate = publishedDate;
    }
}
