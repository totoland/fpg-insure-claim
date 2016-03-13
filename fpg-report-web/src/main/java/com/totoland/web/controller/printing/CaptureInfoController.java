/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.totoland.web.controller.printing;

import com.totoland.web.controller.BaseController;
import java.io.File;
import javax.annotation.PostConstruct;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.ServletContext;
import org.primefaces.event.CaptureEvent;
import org.primefaces.model.CroppedImage;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author totoland
 */
@ManagedBean
@ViewScoped
public class CaptureInfoController extends BaseController {

    private static final long serialVersionUID = 1007114614954046849L;

    private static final Logger LOGGER = LoggerFactory.getLogger(CaptureInfoController.class);

    private String filename = "/temp/photocam/no-avatar1.png";

    private StreamedContent regPhoto;

    private CroppedImage croppedImage;

    private String newImageName = "/temp/photocam/no-avatar.png";

    private String searching;

    private boolean allowCapture = false;

    @PostConstruct
    public void init() {

        LOGGER.trace("init...");

    }

    public void searchInfo() {

        LOGGER.info("Search wording : {}", searching);

        allowCapture = searching != null && !searching.isEmpty();

        if (!allowCapture) {
            addError("พบภาพถ่ายในระบบแล้ว!!");
        }
    }

    private String getRandomImageName() {
        int i = (int) (Math.random() * 10000000);
        return String.valueOf(i);
    }

    public String getFilename() {
        return filename;
    }

    public CroppedImage getCroppedImage() {
        return croppedImage;
    }

    public void setCroppedImage(CroppedImage croppedImage) {
        this.croppedImage = croppedImage;
    }

    public String getNewImageName() {
        return newImageName;
    }

    public void setNewImageName(String newImageName) {
        this.newImageName = newImageName;
    }

    public void oncapture(CaptureEvent captureEvent) {
        filename = searching+"_ori";
        byte[] data = captureEvent.getData();

        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String newFileName = servletContext.getRealPath("") + File.separator + "temp"
                + File.separator + "photocam" + File.separator + filename + ".png";

        LOGGER.trace("newFileName : " + newFileName);

        try {
            FileImageOutputStream imageOutput;
            imageOutput = new FileImageOutputStream(new File(newFileName));
            imageOutput.write(data, 0, data.length);
            imageOutput.close();
            
            filename = "/temp/photocam/"+filename + ".png?rd="+getRandomImageName();
        } catch (Exception e) {
            LOGGER.error(e.toString());
            throw new FacesException("Error in writing captured image.", e);
        }
    }

    public void crop() {
        if (croppedImage == null) {
            return;
        }

        newImageName = searching+"_crop";
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String newFileName = servletContext.getRealPath("") + File.separator + "temp"
                + File.separator + "photocam" + File.separator + newImageName + ".png";

        LOGGER.trace("crop FileName : " + newFileName);

        FileImageOutputStream imageOutput;
        try {
            imageOutput = new FileImageOutputStream(new File(newFileName));
            imageOutput.write(croppedImage.getBytes(), 0, croppedImage.getBytes().length);
            imageOutput.close();
            
            newImageName = "/temp/photocam/"+newImageName + ".png?rd="+getRandomImageName();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Cropping failed."));
        }

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Cropping finished."));
    }

    @Override
    public void resetForm() {

    }

    /**
     * @return the regPhoto
     */
    public StreamedContent getRegPhoto() {
        return regPhoto;
    }

    /**
     * @return the searching
     */
    public String getSearching() {
        return searching;
    }

    /**
     * @param searching the searching to set
     */
    public void setSearching(String searching) {
        this.searching = searching;
    }

    /**
     * @return the allowCapture
     */
    public boolean isAllowCapture() {
        return allowCapture;
    }

    /**
     * @param allowCapture the allowCapture to set
     */
    public void setAllowCapture(boolean allowCapture) {
        this.allowCapture = allowCapture;
    }

}
