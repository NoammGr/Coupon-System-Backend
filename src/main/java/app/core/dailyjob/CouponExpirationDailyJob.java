package app.core.dailyjob;

import app.core.repositories.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

@Component
@Transactional
public class CouponExpirationDailyJob {

    @Autowired
    CouponRepository couponRepository;

    java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());


    @Scheduled(timeUnit = TimeUnit.HOURS, fixedRate = 12)
    public void CouponExpirationDaily() {
        this.couponRepository.deleteByEndDateBefore(this.date);
    }
}
