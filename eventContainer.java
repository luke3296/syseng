package sample;

import java.time.LocalDateTime;
import java.util.Date;

   public  class eventContainer{
        LocalDateTime date;
        String discription;
        public eventContainer(LocalDateTime timestamp, String discription){
            this.discription = discription;
            this.date = timestamp;
        }
    }

