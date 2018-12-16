package datos;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.Random;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 *
 * @author Gabriel
 */
public class Generator {

    public static final int HOURS_OF_DAY = 24;
    /*public String generate() {
        Random rnd = new Random();
        return plateNumberGenerator() + ";"
                + (1 + rnd.nextInt(2)) + ";" + stringRandomizer() + ";"
                + kmGenerator() + ";" + datesGenerator() + ";"
                + (rnd.nextInt(3) + 1);
    }*/

    public int randBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }

   
    
    
    //Funcion que genere fechas aleatorias en el rango de 2015 a 2018, o un reporte diario para mayor precision.
    
    
    public LinkedList<String> generateData(){
        LocalDate day;
        String hour;
        String fecha;
        String hospitalizados;
        String contaminacion;
        String data;
        
        int cantHospitalizados_year = 0;
        double[] cantHospitalizados_month;
        int cantHospitalizados_day = 0;
        int cantHospitalizados_hour = 0;
        int lastDateYear = 0;
        int cantContaminacion;
        
        String start = "2015-01-01";
        String end   = "2018-12-16";
        ArrayList<LocalDate> dates = (ArrayList<LocalDate>) datesBetween(start, end);
        LinkedList<String> allData = new LinkedList<>();

        for(int i = 0; i<dates.size(); i++){
            day = dates.get(i);
            if(lastDateYear!=day.getYear()){
                cantHospitalizados_year = randBetween(120000,150000);
                //cantHospitalizados_month = fragmentarHospitalizados(cantHospitalizados_year);
            }
            
            for(int t = 0; t<HOURS_OF_DAY;t++){
                if(t<10){
                    hour = "0" + t + ":00";
                }else{
                    hour = t + ":00";
                }
                fecha = day.toString() + " " +  hour;
                if(day.getMonthValue()<7){
                    cantContaminacion = (int) (randBetween(0,20)*day.getMonthValue());
                }else{
                    cantContaminacion = (int) (randBetween(0,20)*(13-day.getMonthValue()));
                }
                cantHospitalizados_hour = (int)randBetween((int)(cantHospitalizados_year/(365*24) + cantContaminacion/randBetween(5,9)) - randBetween(0,9),(int)((cantHospitalizados_year/(365*24) + (cantContaminacion)/randBetween(2,4))) - randBetween(0,9))/3;
                contaminacion = Integer.toString(cantContaminacion);
                hospitalizados = Integer.toString(cantHospitalizados_hour);
                data = fecha + ";" + contaminacion + ";"  + hospitalizados;
                allData.add(data + "\n");
            }
            lastDateYear = day.getYear();
        }
        
        return allData;
    }
    
    public List<LocalDate> datesBetween(String start, String end){
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate)+1;
        List<LocalDate> totalDates =  
                LongStream.iterate(0,i -> i+1)
                .limit(daysBetween).mapToObj(i->startDate.plusDays(i))
                .collect(Collectors.toList());
        return totalDates;
    }
    
}
