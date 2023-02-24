package org.utl.dsm.examendos;

public class Bitacora {

    int idBitacora ;
    Conductor Conductor;
    String fecha ;
    String placa ;
    int lastTicket ;

    public Bitacora(int idBitacora, org.utl.dsm.examendos.Conductor conductor, String fecha, String placa, int lastTicket) {
        this.idBitacora = idBitacora;
        Conductor = conductor;
        this.fecha = fecha;
        this.placa = placa;
        this.lastTicket = lastTicket;
    }

    public int getIdBitacora() {
        return idBitacora;
    }

    public void setIdBitacora(int idBitacora) {
        this.idBitacora = idBitacora;
    }

    public org.utl.dsm.examendos.Conductor getConductor() {
        return Conductor;
    }

    public void setConductor(org.utl.dsm.examendos.Conductor conductor) {
        Conductor = conductor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getLastTicket() {
        return lastTicket;
    }

    public void setLastTicket(int lastTicket) {
        this.lastTicket = lastTicket;
    }
}
