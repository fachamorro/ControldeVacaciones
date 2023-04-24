package gob.distrito05d03salud.controlvacaciones.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public final class FormatoFechas {
    // formato fecha yyyy-MM-dd hh:mm:ss
    public Date retornarInicioAnioFechaFormatoCalendario(Date date) throws ParseException {

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            return formatoFecha.parse(calendar.get(Calendar.YEAR) + "-01-01 00:00:00");
    }

    // formato fecha yyyy-MM-dd hh:mm:ss
    public Date retornarFinAnioFechaFormatoCalendario(Date date) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return formatoFecha.parse(calendar.get(Calendar.YEAR) + "-12-31 23:59:59");
    }

    // formato fecha yyyy 
    public int retornarAnioFechaFormatoCalendario(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR) ;
    }


    // formato fecha yyyy-MM-dd hh:mm:ss
    /*public Date retornarInicioExpedienteFechaFormatoCalendarioS(Date date) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return formatoFecha.parse(calendar.getTime()+"");
}*/
   
}
