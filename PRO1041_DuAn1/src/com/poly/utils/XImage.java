/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poly.utils;

import java.awt.Image;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.ImageIcon;

/**
 *
 * @author Admin
 */
public class XImage {
//    public static Image getAppIcon(){
//        URL url = XImage.class.getResource("/com/edusys/icon/fpt.png");
//        return new ImageIcon(url).getImage();
//    }
//    public static final Image APP_ICON;
//    static{
//        String file = "/com/edusys/icon/fpt.png";
//        APP_ICON = new ImageIcon(XImage.class.getResource(file)).getImage();
//    }
    public static boolean save(File src){
        File dst = new File("src\\com\\poly\\img", src.getName());
        if (!dst.getParentFile().exists()){
            dst.getParentFile().mkdirs(); // tạo thư mục
        }
        try {
            Path from = Paths.get(src.getAbsolutePath());
            Path to = Paths.get(dst.getAbsolutePath());
            Files.copy(from, to,StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public static ImageIcon read(String fileName){
        File path = new File("src\\com\\poly\\img", fileName);
        return new ImageIcon(path.getAbsolutePath());
    }
    
}
