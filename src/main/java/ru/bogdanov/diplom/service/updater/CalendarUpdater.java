package ru.bogdanov.diplom.service.updater;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author SBogdanov
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CalendarUpdater implements ICalendarUpdater {

  /*  private final ICalendarService calendarService;
    private final IHolidayService holidayService;

    @Override
    @PostConstruct
    @Scheduled(cron = "0 0 0 1 * *")
    public void update() {
        log.info("Start updated holidays calendar");
        Document document = calendarService.loadCalendar(LocalDate.now().getYear());
        List<Holiday> holidays = HtmlUtils.parse(document, LocalDate.now().getYear());

        if (LocalDate.now().getMonth().equals(Month.DECEMBER)) {
            document = calendarService.loadCalendar(LocalDate.now().getYear() + 1);
            holidays.addAll(HtmlUtils.parse(document, LocalDate.now().getYear() + 1));
        }
        holidayService.saveAll(holidays);
        log.info("Finish updated holidays calendar");
    }*/
}
