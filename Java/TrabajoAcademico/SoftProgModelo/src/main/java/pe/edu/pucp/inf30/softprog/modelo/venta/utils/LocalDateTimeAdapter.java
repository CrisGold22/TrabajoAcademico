/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.modelo.venta.utils;

/**
 *
 * @author Cristhian Horacio
 */
import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {
// Huiza, no lo veas por fa
    @Override
    public LocalDateTime unmarshal(String v) throws Exception {
        return (v == null || v.isBlank()) ? null : LocalDateTime.parse(v);
    }

    @Override
    public String marshal(LocalDateTime v) throws Exception {
        return (v == null) ? null : v.toString();
    }

}
