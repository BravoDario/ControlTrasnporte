package org.utl.dsm.examendos;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
import java.util.List;

public class HelloController {
    @FXML
    private TextField nombreConductor;
    @FXML
    private TextField placa;
    @FXML
    private TextField lastTicket;
    @FXML
    private TableView<Bitacora> tblBitacora;
    @FXML
    private TableColumn<Bitacora, > ColidBitacora;
    @FXML
    private TableColumn ColNombre;
    @FXML
    private TableColumn ColFecha;
    @FXML
    private TableColumn ColPlaca;
    @FXML
    private TableColumn colTicket;

    int contador = 0;

    public Conductor crearConductor(String nombres) {
        try {
            Conductor conductor = new Conductor();
            String sql = "SELECT * FROM conductor where nombre like '" + nombres + "'";
            ConexionMySQL connMySQL = new ConexionMySQL();
            Connection conn = connMySQL.open();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                conductor.setIdConductor(rs.getInt(1));
                conductor.setNombre(rs.getString(2));
                conductor.setApellidoPaterno(rs.getString(3));
                conductor.setApellidoMaterno(rs.getString(4));
                System.out.println("Conductor encontrado");
            }
            return conductor;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    @FXML
    protected void crearBitacora() {
        try {
            String nombr = nombreConductor.getText();
            String placaa = placa.getText();
            int lastticket = Integer.parseInt(lastTicket.getText());

            Conductor conductor = crearConductor(nombr);
            Bitacora bitacora = new Bitacora(
                    contador,
                    conductor,
                    "",
                    placaa,
                    lastticket
            );

            String sql = "insert into bitacora(idConductor, fecha, placa, lastTicket) values" +
                    "(?,now(),?,?)";
            ConexionMySQL connMySQL = new ConexionMySQL();
            Connection conn = connMySQL.open();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, conductor.getIdConductor());
            pstmt.setString(2, bitacora.getPlaca());
            pstmt.setInt(3, bitacora.getLastTicket());

            pstmt.execute();
            System.out.println("guardado");
            limpiar();

            pstmt.close();
            connMySQL.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void limpiar() {
        nombreConductor.setText("");
        placa.setText("");
        lastTicket.setText("");
    }


    protected void agregar(ObservableList<Bitacora> bit) {
        colNombre.setCellValueFactory(new PropertyValueFactory<Alumno, String>("nombre"));
        colApePaterno.setCellValueFactory(new PropertyValueFactory<Alumno, String>("ape_paterno"));
        colApeMaterno.setCellValueFactory(new PropertyValueFactory<Alumno, String>("ape_materno"));
        colMatricula.setCellValueFactory(new PropertyValueFactory<Alumno, String>("matricula"));
        colCarrera.setCellValueFactory(new PropertyValueFactory<Alumno, String>("carrera"));
        colIdPersona.setCellValueFactory(new PropertyValueFactory<Alumno, String>("idPersona"));

        tblAlumno.setItems(alm);
    }
    public void todo() throws Exception {
        List<Alumno> list = this.getAll();
        System.out.println(list);
    }

    public ObservableList<Alumno> getAll() throws Exception {
        String sql = "SELECT * FROM v_alumno";

        ConexionMySQL connMysql = new ConexionMySQL();

        Connection conn = connMysql.open();

        PreparedStatement pstmt = conn.prepareStatement(sql);

        ResultSet rs = pstmt.executeQuery();

        ObservableList<Alumno> alumnos = tblAlumno.getItems();
        while (rs.next()){
            alumnos.add(fill(rs));
            agregar(alumnos);
        }
        rs.close();
        pstmt.close();
        connMysql.close();

        return alumnos;
    }

    private Alumno fill(ResultSet rs) throws Exception {
        Alumno a = new Alumno();

        a.setIdPersona(rs.getInt("idPersona"));
        a.setNombre(rs.getString("nombre"));
        a.setApe_materno(rs.getString("ape_materno"));
        a.setApe_paterno(rs.getString("ape_paterno"));

        a.setIdAlumno(rs.getInt("idAlumno"));
        a.setMatricula(rs.getInt("matricula"));
        a.setCarrera(rs.getString("carrera"));

        return a;
    }

}