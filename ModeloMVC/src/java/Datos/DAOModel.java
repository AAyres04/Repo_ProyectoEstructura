package Datos;

import Modelo.BubbleDataModel;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class DAOModel {
    public ArrayList<BubbleDataModel> loadData(String archivo) throws ParseException{
        FileHandler f = new FileHandler("C:\\Users\\tonio\\Documents\\GitHub\\Repo_ProyectoEstructura\\ModeloMVC\\");
        System.out.println(System.getProperty("user.dir"));
        ArrayList<BubbleDataModel> bubble = new ArrayList<>();
        ArrayList<String> datos = f.lectura(archivo);
        datos.remove(0);
        
        String fecha_string;
        String mp_string;
        String hosp_string;
        
        int mp;
        int hosp;
        LocalDate fecha;
        
        for (String lectura : datos) {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm");
            String[] split = lectura.split(";");
            fecha_string = split[0];
            mp_string = split[1];
            hosp_string = split[2];
            
            fecha = formatter.parse(fecha_string).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            mp = Integer.parseInt(mp_string);
            hosp = Integer.parseInt(hosp_string);
            BubbleDataModel bdm = new BubbleDataModel(fecha,mp,hosp);
            bubble.add(bdm);
        }
        return bubble;
    }
}
