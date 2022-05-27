package ru.bogdanov.diplom.manager.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.bogdanov.diplom.manager.ISalaryManager;
import ru.bogdanov.diplom.mapper.common.TimestampMapper;
import ru.bogdanov.diplom.service.IEmployeeService;
import ru.bogdanov.diplom.service.ISalaryService;

/**
 * @author SBogdanov
 * Менеджер для работы с зарплатой и счетами работника
 */
@Slf4j
@Component
@AllArgsConstructor
public class SalaryManager implements ISalaryManager {

    private final IEmployeeService employeeService;
    private final ISalaryService salaryService;
    private final TimestampMapper timestampMapper;
/*

    @Override
    public Salary calculateTotalEmployeeSalary(FindSalaryByEmployeeRequest request) {
        Employee employee = employeeService.findOne(UUID.fromString(request.getEmployeeId()));

        Tariff tariff = employee.getEmployer()
                .getTariffs()
                .stream()
                .filter(t -> employee.getEmployer().getPaymentProvider() == t.getPaymentProvider())
                .findAny()
                .orElse(Tariff.builder().build());

        List<Salary.Builder> builderList = new ArrayList<>();
        List<Boolean> nowInStopIntervalList = new ArrayList<>();
        List<Boolean> isCheckedByListList = new ArrayList<>();

        boolean isHoliday = holidayService.isHoliday(LocalDate.now());
        List<Position> positions = positionService.findByEmployeeId(employee.getId());

        for (Position position : positions) {
            List<ru.bogdanov.diplom.data.model.Salary> salaries = new ArrayList<>(position.getSalaries());

            Salary.Builder builder = Salary.newBuilder();

            boolean isNowInStopInterval = stopIntervalService.isNowInStopInterval(new ArrayList<>(position.getServiceStopIntervals()));
            boolean isCheckedByList = position.getCheckedByList();

            nowInStopIntervalList.add(isNowInStopInterval);
            isCheckedByListList.add(isCheckedByList);

            if (!CollectionUtils.isEmpty(salaries)) {
                builder.setdate(timestampMapper.mapToProto(salaries.get(0).getdate())) //TODO уточнить требования к полю. Резлизовать задачу по доработке
                        .setEarnedForMonth(0) //TODO уточнить требования к полю. Резлизовать задачу по доработке
                        .setUpdatedAt(timestampMapper.mapToProto(salaries.get(0).getSalaryUpdateAt()));
            } else {
                builder.setdate(timestampMapper.mapToProto(LocalDate.now().atStartOfDay().toLocalDate()))
                        .setEarnedForMonth(0);
            }

            builder = calculateSum(builder, salaries, tariff, isNowInStopInterval, isCheckedByList);

            builderList.add(builder);
        }
        return reduceSalary(employee, builderList, isHoliday, nowInStopIntervalList, isCheckedByListList);
    }
*/

/*

    private Salary reduceSalary(Employee employee, List<Salary.Builder> builderList,
                                boolean isHoliday, List<Boolean> nowInStopIntervalList,
                                List<Boolean> isCheckedByListList) {

        Salary.Builder builder = Salary.newBuilder().setServiceAvailable(false);

        builder.setAvailableCash(builderList.stream()
                        .map(Salary.Builder::getAvailableCash)
                        .reduce(0L, Long::sum)
                        - CommonUtils.calculateHoldSum(employee.getEmployeeHoldSum()))
                .setAvailableCashToView(builderList.stream()
                        .map(Salary.Builder::getAvailableCashToView)
                        .reduce(0L, Long::sum)
                        - CommonUtils.calculateHoldSum(employee.getEmployeeHoldSum()))
                .setEarnedForMonth(builderList.stream()
                        .map(Salary.Builder::getEarnedForMonth)
                        .reduce(0L, Long::sum))
                .setdate(builderList.stream()
                        .map(Salary.Builder::getdate)
                        .reduce(builderList.get(0).getdate(), (a, b) -> timestampMapper.mapToLocalDate(a).isAfter(timestampMapper.mapToLocalDate(b)) ? a : b))
                .setServiceAvailable(!isHoliday
                        && !nowInStopIntervalList.stream().reduce(true, (x, y) -> x && y)
                        && !isCheckedByListList.stream().reduce(true, (x, y) -> !x && !y));

        return builder.build();
    }

    */
/**
     * Метод считает доступную сумму по всем зарплатам сохраняя в 2 параметра
     *          totalAvailableSumToUse - сумма для использования (доступен некий процент общей суммы),
     *          totalAvailableSum  - общая сумма зарплат пользователя
     *//*

    private Salary.Builder calculateSum(Salary.Builder builder, List<ru.bogdanov.diplom.data.model.Salary> salaries, Tariff tariff, boolean isNowInStopInterval, boolean isCheckedByList) {
        long totalAvailableSumToUse = 0;
        long totalAvailableSum = 0;

        if (!isNowInStopInterval && isCheckedByList) {
            totalAvailableSumToUse = CommonUtils.calculateTotalAvailableSum(
                    salaries,
                    BigDecimal.valueOf(tariff.getWithdrawalPercentage()));

            totalAvailableSum = CommonUtils.calculateTotalAvailableSum(
                    salaries,
                    BigDecimal.ONE);
        }
        builder.setAvailableCash(totalAvailableSum)
                .setAvailableCashToView(totalAvailableSumToUse);
        return builder;
    }
*/

}
