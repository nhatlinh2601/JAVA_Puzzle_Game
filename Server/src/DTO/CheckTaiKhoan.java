/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.DatabaseHelper;
import DTO.TaiKhoan;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.io.DataOutputStream;

public class CheckTaiKhoan {
    public TaiKhoan loadTaiKhoan(String tenDangNhap, String matKhau) {
        TaiKhoan taiKhoan = null;
        try {
            Connection con = DatabaseHelper.getConnection();
            PreparedStatement stmt = null;
            String sql = "SELECT * FROM TA_AUT_USER WHERE T_USERNAME=? AND T_PASSWORD=?";

            stmt = con.prepareStatement(sql);
            stmt.setString(1, tenDangNhap);
            stmt.setString(2, matKhau);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String ten = rs.getString("T_USERNAME").trim();
                String mk = rs.getString("T_PASSWORD").trim();
                taiKhoan = new TaiKhoan(ten, mk);
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
        return taiKhoan;
    }
}


    