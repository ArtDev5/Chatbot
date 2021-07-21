package chatbot.chatbot;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
public class teste{

    public static void main(String[] args) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        // Supondo que a semana comece no domingo.
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.SUNDAY);
        Calendar cal1 = Calendar.getInstance();
        cal1.setFirstDayOfWeek(Calendar.SATURDAY);
        // Para pegar o primeiro dia desta semana, vamos ver que dia da semana é hoje, e subtrair
        // o número de dias a partir de domingo.
        // Note que a semana pode começar no mês passado.

        int diaSemana = cal.get(Calendar.DAY_OF_WEEK);
        int diaSemana1 = cal1.get(Calendar.DAY_OF_WEEK);

        cal.add (Calendar.DAY_OF_MONTH, Calendar.SUNDAY - diaSemana);
        cal1.add(Calendar.DAY_OF_MONTH, Calendar.SATURDAY - diaSemana1);
        System.out.println (cal.getTime());
        System.out.println(df.format(cal1.getTime()));

    }

}