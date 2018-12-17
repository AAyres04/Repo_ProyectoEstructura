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

    
    
    public int randBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }
    
    public LinkedList<String> generateData(){
        LocalDate day;
        String hour;
        String fecha;
        String hospitalizados;
        String contaminacion;
        String data;
        
        int cantHospitalizados_year = 0;
        
        int cantHospitalizados_day = 0;
        
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
            
            
               
            fecha = day.toString();
            if(day.getMonthValue()<7){
                cantContaminacion = (int) (randBetween(0,20)*day.getMonthValue());
            }else{
                cantContaminacion = (int) (randBetween(0,20)*(13-day.getMonthValue()));
            }
            //cantHospitalizados_day = (int)randBetween((int)(cantHospitalizados_year*(cantContaminacion/randBetween(5,9))/(365)) - randBetween(30,90),(cantHospitalizados_year*(cantContaminacion/randBetween(2,4))/(365)) - randBetween(30,90))/3;
            
            if(cantContaminacion < 50){
                cantHospitalizados_day = (int)randBetween((cantHospitalizados_year/365)-cantContaminacion*randBetween(5,8),(cantHospitalizados_year/365)-cantContaminacion*4);
            }else{
                cantHospitalizados_day = (int)randBetween((cantHospitalizados_year/365),(cantHospitalizados_year/365)+cantContaminacion/randBetween(2,4));
            }
            contaminacion = Integer.toString(cantContaminacion);
            hospitalizados = Integer.toString(cantHospitalizados_day);
            data = fecha + ";" + contaminacion + ";"  + hospitalizados;
            allData.add(data + "\n");
            
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
