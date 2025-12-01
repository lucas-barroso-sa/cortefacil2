package cortefacil.unifor.services;

import cortefacil.unifor.models.DTOs.BarberFinancialDTO;
import cortefacil.unifor.models.DTOs.FinancialReportDTO;
import cortefacil.unifor.models.entities.Appointment;
import cortefacil.unifor.models.entities.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReportService {
    private final AppointmentService appointmentService;

    public ReportService(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    public FinancialReportDTO generateFinancialReport(LocalDate startDate, LocalDate endDate) {
        // 1. Pede os dados ao AppointmentService
        LocalDateTime start = startDate.atStartOfDay();
        LocalDateTime end = endDate.atTime(LocalTime.MAX);

        List<Appointment> appointments = appointmentService.findByDateRange(start, end);

        // 2. O resto da lógica de cálculo continua IDÊNTICA
        FinancialReportDTO report = new FinancialReportDTO();

        // Agrupa por Barbeiro
        Map<User, List<Appointment>> appointmentsByBarber = appointments.stream()
                .filter(a -> a.getUser() != null)
                .collect(Collectors.groupingBy(Appointment::getUser));

        // Calcula Totais
        for (Map.Entry<User, List<Appointment>> entry : appointmentsByBarber.entrySet()) {
            User barber = entry.getKey();
            List<Appointment> barberApps = entry.getValue();

            double totalBilled = barberApps.stream()
                    .mapToDouble(Appointment::getTotalValue)
                    .sum();

            BarberFinancialDTO barberDTO = new BarberFinancialDTO(
                    barber.getName(),
                    totalBilled,
                    barberApps.size(),
                    barber.getCommissionPercentage()
            );

            report.getBarberReports().add(barberDTO);
            report.setTotalRevenue(report.getTotalRevenue() + totalBilled);
            report.setTotalCommissions(report.getTotalCommissions() + barberDTO.getCommissionValue());
        }

        report.setNetProfit(report.getTotalRevenue() - report.getTotalCommissions());

        return report;
    }
}


